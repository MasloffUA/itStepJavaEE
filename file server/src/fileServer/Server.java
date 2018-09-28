package fileServer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.SecondaryLoop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.ShapeGraphicAttribute;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.FileChooserUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Server extends Window {
	private static Map<Integer,ServerFileFormat> sharedFileList = new HashMap<>();
	private static ArrayList<ClientListener> clientListeners = new ArrayList<>(); 
	private static Set<Integer> canDeleted = new HashSet<>();
	private static Set<Integer> selectedFiles = new HashSet<>();
	private JButton start = new JButton("Запустить сервер");
	private JButton stop = new JButton("Остановить сервер");
	private JButton folder = new JButton("Папка для загрузок");
	private JButton settings = new JButton("Настройки");
	private static JButton upload = new JButton("Разрешить загрузу");
	private JButton share = new JButton("Расшарить файл");
	private static JButton stopShare = new JButton("Отключить доступ");
	private JButton apply = new JButton("Применить разрешения");
	private static File sharedDirectory;	
	protected static JTable table = new JTable();
	private static DefaultTableModel model;
	private static ServerSocket serverSocket;
	private Server server;
	private static boolean offline = true;
	private static boolean canUpload = false;
	private static Request request;
	private static int maxSize = 1048576;
	private static int maxConnections = 5;
	private static int port = 2877;
	private static JLabel connections = new JLabel("Connections: " + clientListeners.size() + " of " + maxConnections);
	private static AtomicInteger downloads = new AtomicInteger(0);
	private static AtomicInteger uploads = new AtomicInteger(0);
	private static JLabel totalSharedFiles = new JLabel("Shared files: " + sharedFileList.size());
	private static JLabel downloadsLabel = new JLabel("Downloads: 0");
	private static JLabel uploadsLabel = new JLabel("Uploads: 0");
	private static int uploadedFileLimit = 0;
	private static JLabel uploadFileLimitLabel = new JLabel("Max size file: " + uploadedFileLimit + "KB");
	private static int totalUploadLimit = 0;
	private static JLabel totalUploadLimitLabel = new JLabel("Total limit: " + totalUploadLimit);
	
	//private static Map<Integer, 	
	
	public Server(String name) {
		super(name);
		Dimension butSize = new Dimension(175, 25);
		//eastPanel.setSize(300, 100);
		start.setMaximumSize(butSize);
		stop.setMaximumSize(butSize);
		folder.setMaximumSize(butSize);
		upload.setMaximumSize(butSize);
		share.setMaximumSize(butSize);
		stopShare.setMaximumSize(butSize);
		apply.setMaximumSize(butSize);
		delete.setMaximumSize(butSize);
		start.setMaximumSize(butSize);
		status.setForeground(Color.red);
		eastPanel.add(status);
		//start.setSize(300, 100);
		eastPanel.add(start);
		start.setToolTipText("Запустить сервер");
		start.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					serverSocket = new ServerSocket(port);
					offline = false;
					start.setEnabled(false);
					stop.setEnabled(true);
					status.setText("STATUS: ONLINE");
					status.setForeground(Color.GREEN);
					System.out.println("BUTTON START");
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});
		eastPanel.add(stop);
		stop.setEnabled(false);
		stop.setToolTipText("Остановить сервер");
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (ClientListener client : clientListeners) {
					client.interrupt();
				}
				offline = true;
				stop.setEnabled(false);
				start.setEnabled(true);
				try {
					serverSocket.close();
					status.setText("STATUS: OFFLINE");
					status.setForeground(Color.RED);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		settings.setMaximumSize(butSize);
		eastPanel.add(settings);
		settings.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerSettings s = new ServerSettings();
				
			}
		});
		
		eastPanel.add(folder);
		folder.setToolTipText("Выбрать папку для загрузок");
		folder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseFolder();
				getFreeSpace();
			}
		});
		
		eastPanel.add(upload);
		upload.setEnabled(false);
		upload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Request request;
				if (canUpload) {
					upload.setText("Разрешить загрузку");
					canUpload = false;
					request = new Request(RequestType.FORBID_UPLOAD);
				}
				else {
					upload.setText("Запретить загрузку");
					canUpload = true;
					request = new Request(RequestType.CAN_UPLOAD);
				}
				
				for (ClientListener cl : clientListeners) {
					cl.sendRequest(request);
				}
				
			}
		});
		
		
		
		
		eastPanel.add(apply);
		apply.setEnabled(false);
		apply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canDeleted = new HashSet();
				for (int i=0; i<model.getRowCount(); i++) {
					ServerFileFormat sff = sharedFileList.get(model.getValueAt(i, 1));
					//sff.setDescription(new String((String) model.getValueAt(i, 4)));
					sff.setCanDeleted((boolean) model.getValueAt(i, 5));
					if (sff.isCanDeleted()) {
						canDeleted.add(sff.getCode());
					}
				
				}
				
				updateTable();
				sendFileListToAll();
				
/*				for (Integer i : canDeleted) {
					System.out.println(i);
				}*/
				apply.setEnabled(false);
				delete.setEnabled(false);
				stopShare.setEnabled(false);
				
			}
		});
		
		eastPanel.add(share);
		share.setToolTipText("Выбрать файл для предоставления общего доступа к нему");
		//share.setSize(butSize);
		share.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shareFile();
				
			}
		});
		
		eastPanel.add(stopShare);
		stopShare.setToolTipText("Прекратить общий доступ к файлам");
		stopShare.setEnabled(false);
		stopShare.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				unShareFile();
				
			}
		});
		
		eastPanel.add(delete);
		delete.setToolTipText("Удалить выбранные файлы с жёсткого диска. Для безопасности - функция доступна только если удялаяемые файлы отмечены, как \"Разрешить удалять\"");
		//delete.setSize(butSize);
		delete.setEnabled(false);
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSelectedFiles(selectedFiles);
				
			}
		});
		
		eastPanel.add(connections);
		eastPanel.add(downloadsLabel);
		eastPanel.add(uploadsLabel);
		eastPanel.add(uploadFileLimitLabel);
		eastPanel.add(totalUploadLimitLabel);
		
		
		
	
	    JScrollPane scroll=new JScrollPane();
	    mainPanel.add(scroll);

	    final JTable table=new JTable();
	    scroll.setViewportView(table);
		
		
		model=new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column)
			{
				switch(column)
				{
				case 0:
					return Boolean.class;
				case 1:
					return Integer.class;
				case 2:
					return String.class;
				case 3:
					return Integer.class;
				case 4:
					return String.class;
				case 5:
					return Boolean.class;
				default:
					return String.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				// Оставляем указанные колонки доступными для редактирования
				return column==4 || column==0 || column ==5;
			}
			
			
		};
		
		model.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// Если изменения произошли в ячейках "описание" или "разрешить/запретить удаление"
				if (table.getEditingRow()>-1 && table.getEditingColumn()>3) {
					apply.setEnabled(true);
					
				}
				else if (table.getEditingColumn()==0) {
					for (int i=0; i<model.getRowCount(); i++) {
						ServerFileFormat sff = sharedFileList.get(model.getValueAt(i, 1));
						boolean choosed = (boolean) model.getValueAt(i, 0);
						if (choosed) {
							selectedFiles.add((Integer) model.getValueAt(i, 1));
						}
						else {
							selectedFiles.remove(model.getValueAt(i, 1));
						}
					}
					if (selectedFiles.size()>0) {
						if (canDeleted.containsAll(selectedFiles)) {
							delete.setEnabled(true);
						}
						stopShare.setEnabled(true);
					}
					else {
						delete.setEnabled(false);
						stopShare.setEnabled(false);
						
					}
				}
				
			}
		});
		
		table.setModel(model);
		
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	    table.setRowSorter(sorter);
	    
		model.addColumn("Выбранные");
		model.addColumn("id");
		model.addColumn("Файл");
		model.addColumn("Размер файла, КБ");
		model.addColumn("Описание");
		model.addColumn("Разрешить удалять");

	    this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Server server = new Server("File Server");
		while(true) {
			if (offline) {
				//System.out.println("OFFLINE");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				ClientListener clientListener;
				try {
					clientListener = new ClientListener(serverSocket.accept());
					clientListeners.add(clientListener);
					connections.setText("Connections: " + clientListeners.size());
					clientListener.start();
				} catch (IOException e) {
					System.out.println("Сокет закрыт");
					//e.printStackTrace();
				}

				System.out.println("clientListener STARTED");
			}
		}
		
	}
	
	public void deleteSelectedFiles(Set<Integer> files) {
		for (Integer i : files) {
			// Дополнительная проверка на стороне сервера
			if (sharedFileList.get(i).isCanDeleted()) {
				File removingFile = sharedFileList.get(i).getFile();
				boolean deleted = removingFile.delete();
				if (deleted) {
					sharedFileList.remove(i);
					updateTable();
				}
			}
			else {
				//Удаление не возможно
			}
		}
		
	}
	
	public static void chooseFolder() {
		JFileChooser folderChooser = new JFileChooser();
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		File file = null;
		if (folderChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = folderChooser.getSelectedFile();
			ServerFileFormat newFile = new ServerFileFormat(file);
			findFiles(file);
			sharedDirectory = file;
			upload.setEnabled(true);
			updateTable();
		};			
	}
	
	public static void findFiles(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files)
			findFiles(f);
		}
		else if(file.isFile()) {
			ServerFileFormat newFile = new ServerFileFormat(file);
			sharedFileList.put(newFile.getCode(), newFile);
		}

	}
	
	
	public static void shareFile() {
		JFileChooser fileChooser = new JFileChooser();
		File file = null;		
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			ServerFileFormat newFile = new ServerFileFormat(file);
			sharedFileList.put(newFile.getCode(), newFile);
			updateTable();

		};	
		
	}
	
	public static void updateFileList() {
		Map<Integer, ServerFileFormat> temp = new HashMap<>();
		temp.putAll(sharedFileList);
		sharedFileList = temp;
		Set<Integer> temp2 = new HashSet<>();
		temp2.addAll(canDeleted);
		canDeleted = temp2;
	}
	
	
	public static void updateTable() {
		updateFileList();
		// Очищаеи таблицу
		model.getDataVector().removeAllElements();
		// Применяем изменения. Без этого метода из таблицы никогда не удалялся последний элемент
		model.fireTableDataChanged();
		
		// Обновляем список расшареных файлов в таблице
		int i = 0;		
		for (ServerFileFormat f : sharedFileList.values()) {
			model.addRow(new Object[0]);
			model.setValueAt(false,i,0);
			model.setValueAt(f.getCode(), i, 1);
			model.setValueAt(f.getName(), i, 2);
			model.setValueAt(f.getSize(), i, 3);
			model.setValueAt(f.getDescription(), i, 4);
			model.setValueAt(f.isCanDeleted(),i,5);
			i++;
		}
		model.fireTableDataChanged();
		sendFileListToAll();
	}
	
	
	public static void unShareFile() {
		// Лист, для добавления ID файлов, убираемых из доступа
		Set<Integer> list = new HashSet<>();
		for (int i=0; i<model.getRowCount(); i++) {
			if ((boolean) model.getValueAt(i, 0)) {
				list.add((Integer) model.getValueAt(i, 1));
			};
		}
		stopShare.setEnabled(false);
		
		
		// Убираем файлы мапы
		sharedFileList.keySet().removeAll(list);

		// Обновляем таблицу
		updateTable();
		// Отправляем изменения всем
		sendFileListToAll();
		
	}
	
	public static void sendFileListToAll() {
		//System.out.println(clientListeners.size());
		for(ClientListener cl : clientListeners) {
			cl.sendFileList();
		}
	}
	
	private static class ClientListener extends Thread implements Closeable{
		private Socket socket;
		private ObjectInputStream inputStream;
		private ObjectOutputStream outputStream;
		public ClientListener(Socket socket) {
			this.socket = socket;
			
			try {
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				inputStream = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("CONSTRUCTOR: " + socket.getRemoteSocketAddress());
			//System.out.println("CONSTRUCTOR server client listener");
		}
		
		@Override
		public void run() {
			sendRequest(new Request(RequestType.CONNECTED));

			while(!socket.isClosed()) {
				try {
					Request request = (Request) inputStream.readObject();
					checkRequest(request);
					
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Прервано");
					
					try {
						this.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//break;
			}
			clientListeners.remove(this);
			connections.setText("Connections: " + clientListeners.size());
		}
		
		public void sendFileList() {
			System.out.println(socket.getRemoteSocketAddress());
			Request request = new Request(RequestType.SEND_FILE_LIST);
			//byte[] list = sharedFileList.asBy
			request.setFileList(sharedFileList);
			request.setCanDeleted(canDeleted);
			sendRequest(request);
		}
		
		public void sendRequest(Request request) {
			try {
				outputStream.writeObject(request);
				outputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void sendFiles(Set<Integer> list) {
			for (Integer i : list) {
				if (sharedFileList.containsKey(i)) {
					File file = sharedFileList.get(i).getFile();
					synchronized (file) {
					Path currentFile = Paths.get(file.getAbsolutePath());
						try {
							byte[] data = Files.readAllBytes(currentFile);
							Request request = new Request(RequestType.SEND_FILE);
							request.setFileInBytes(data);
							request.setMessage(file.getName());
							sendRequest(request);
						} catch (IOException e) {

						}
					}
				}
			}
		}
		
		public void recievFile(byte [] fileInBytes, String fileName) {
			File file = new File(sharedDirectory+"\\"+fileName);
				try(FileOutputStream fos = new FileOutputStream(file)){
					//System.out.println(file.getAbsolutePath());
					fos.write(fileInBytes);
					fos.flush();
					ServerFileFormat newFile = new ServerFileFormat(file);
					sharedFileList.put(newFile.getCode(), newFile);
					updateTable();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
			
		private void checkRequest(Request req) {
			switch(req.getType()) {
			case GET_FILE_LIST:
				sendFileList();
				if (canUpload) {
					sendRequest(new Request(RequestType.CAN_UPLOAD));
				}
				break;		
			case GET_FILE:
				sendFiles(req.getFileOperationList());
				break;	
				
				
				
			case ACCEPTED:
				break;
			case CONNECTED:
				break;
			case DELETE_FILE:
				break;
			case FILE:
				break;
			case FORBIDED_UPLOAD_MAX_SIZE:
				break;
			case FORBIDED_UPLOAD_TOTAL_SPACE:
				break;

			case SEND_FILE:
				break;
			case SEND_FILE_LIST:
				break;
			case UPLOAD_FILE:
				recievFile(req.getFileInBytes(), req.getMessage());
				break;
			default:
				break;
			
			}
		}

		@Override
		public void close() throws IOException {
			socket.close();
			inputStream.close();
			outputStream.close();
			
		}
			
	}

	public static int getMaxSize() {
		return maxSize;
	}

	public static void setMaxSize(int maxSize) {
		Server.maxSize = maxSize;
	}

	public static int getMaxConnections() {
		return maxConnections;
	}

	public static void setMaxConnections(int maxConnections) {
		Server.maxConnections = maxConnections;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		Server.port = port;
	}
	
	public static void getFreeSpace() {
		if (sharedDirectory!=null) {
			System.out.println(sharedDirectory.getUsableSpace()/1024/1024 + " MB");
			System.out.println(sharedDirectory.getFreeSpace()/1024/1024 + " MB");
		}
	}
	
	
	
}
