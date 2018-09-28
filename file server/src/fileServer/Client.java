package fileServer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;
import javax.swing.RowSorter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Client extends Window {

	private static Map<Integer,ServerFileFormat> sharedFileList = new HashMap<>();
	protected JButton connect = new JButton("Подключиться");
	protected JButton disconnect = new JButton("Отсоединиться");
	protected JButton download = new JButton("Скачать файл/файлы");
	protected JButton upload = new JButton("Загрузить файл");
	protected JButton update = new JButton("Обновить список");
	protected JTable table = new JTable();
	private static DefaultTableModel model;
	private SocketListener socketListener;
	private static boolean connection = false;
	private static Request request;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private static boolean canUpload = false;
	private static Set<Integer> selectedFiles = new HashSet<>();
	private static Set<Integer> canDeleted;
	private static String serverIp = "localhost";
	private static int serverPort = 2877;
	private static JLabel infoIp = new JLabel("Server IP: " + serverIp);
	private static JLabel infoPort = new JLabel("Server PORT: " + serverPort);
	private static JLabel infoFiles = new JLabel("Доступно файлов: " + sharedFileList.size());
	private static JLabel infoUpdateTime = new JLabel("Обновлено: никогда");
	
	
	public Client(String name) {
		super(name);
		
		
		// Кнопка "Соединение" и слушатель
		Dimension butSize = new Dimension(175, 25);
		status.setMaximumSize(butSize);
		connect.setMaximumSize(butSize);
		disconnect.setMaximumSize(butSize);
		download.setMaximumSize(butSize);
		upload.setMaximumSize(butSize);
		update.setMaximumSize(butSize);
		delete.setMaximumSize(butSize);
		
		
		
		
		status.setText("STATUS: DISCONNECTED");
		eastPanel.add(status);
		status.setForeground(Color.red);
		eastPanel.add(connect);
		connect.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				connect.setEnabled(false);
				socketListener = new SocketListener();
				socketListener.start();	
				ClientSettings settings = new ClientSettings();
			}
		});
		
		// Кнопка "Разъединение" и слушатель
		eastPanel.add(disconnect);
		disconnect.setEnabled(false);
		disconnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					socketListener.close();
					disconnect.setEnabled(false);
					connect.setEnabled(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
			}
		});
		
		JLabel block = new JLabel("");
		block.setMaximumSize(butSize);
		eastPanel.add(block);
		
		// Кнопка "Обновить список файлов" и слушатель
		eastPanel.add(update);
		update.setEnabled(false);
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getFileList();
			}
		});
		
		//Кнопка "Скачать" и слушатель
		eastPanel.add(download);
		download.setEnabled(false);
		download.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Request request = new Request(RequestType.GET_FILE);
				request.setFileOperationList(selectedFiles);
				sendRequest(request);
				
			}
		});
		
		// Кнопка "Загрузить" и слушатель
		eastPanel.add(upload);
		upload.setEnabled(false);
		upload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				uploadFiles();
				
			}
		});
		
		// Кнопка "Удалить" и слушатель
		eastPanel.add(delete);
		delete.setEnabled(false);
		
		JLabel block1 = new JLabel("");
		block1.setMaximumSize(butSize);
		eastPanel.add(block1);
		
		
		
		eastPanel.add(infoIp);
		eastPanel.add(infoPort);
		eastPanel.add(infoFiles);
		eastPanel.add(infoUpdateTime);
		
		
		JScrollPane scroll=new JScrollPane();
	    mainPanel.add(scroll);
	    final JTable table=new JTable();
	    scroll.setViewportView(table);
		
		model=new DefaultTableModel() {
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
					return String.class;
				default:
					return String.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				// Оставляем указанные колонки доступными для редактирования
				return column==0;
			}	
		};
		
		model.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent arg0) {
				if (table.getEditingRow()>-1 && table.getEditingColumn()==0) {
					selectedFiles = new HashSet();
					for (int i=0; i<model.getRowCount(); i++) {
						ServerFileFormat sff = sharedFileList.get(model.getValueAt(i, 1));
						boolean choosed = (boolean) model.getValueAt(i, 0);
						if (choosed) {
							selectedFiles.add((Integer) model.getValueAt(i, 1));
						}
					}
					if (selectedFiles.size()>0) {
						if (canDeleted.containsAll(selectedFiles)) {
							delete.setEnabled(true);
						}
						download.setEnabled(true);
					}
					else {
						delete.setEnabled(false);
						download.setEnabled(false);
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
		model.addColumn("Удаление");
		eastPanel.repaint();
	    this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Client client = new Client("Client of FileService");
	}
	
	public static void updateTable() {
		System.out.println("Update Table Client");
		System.out.println(sharedFileList.hashCode());
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		int i = 0;		
		for (ServerFileFormat f : sharedFileList.values()) {
			model.addRow(new Object[0]);
			model.setValueAt(selectedFiles.contains(f.getCode()) ? true : false ,i,0);
			model.setValueAt(f.getCode(), i, 1);
			model.setValueAt(f.getName(), i, 2);
			model.setValueAt(f.getSize(), i, 3);
			model.setValueAt(f.getDescription(), i, 4);
			model.setValueAt(canDeleted.contains(f.getCode()) ? "Разрешено" : "Запрещено",i,5);
		
			System.out.println(f.getFile().getAbsolutePath());
			i++;
		}
		infoFiles.setText("Доступно файлов: " + sharedFileList.size());
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		infoUpdateTime.setText("Обновлено в " + dateFormat.format(date));
		model.fireTableDataChanged();
	}
	
	public void recievFile(byte [] fileInBytes, String fileName) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setSelectedFile(new File(fileName));
        
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			try(FileOutputStream fos = new FileOutputStream(chooser.getSelectedFile())){
				fos.write(fileInBytes);
				fos.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		selectedFiles = new HashSet<>();
		
	}
	
	private void checkRequestType(Request req) {
		//System.out.println("CHECK REQUEST");
		switch(req.getType()) {
		case CONNECTED:
			getFileList();
			break;
		case SEND_FILE_LIST:
			sharedFileList = req.getFileList();
			canDeleted = req.getCanDeleted();
			updateTable();
			break;
		case CAN_UPLOAD:
			upload.setEnabled(true);
			canUpload = true;
			break;
		case FORBID_UPLOAD:
			upload.setEnabled(false);
			canUpload = false;
			break;
		case SEND_FILE:
			recievFile(req.getFileInBytes(), req.getMessage());
			break;
		
		
		case ACCEPTED:
			break;

		case DELETE_FILE:
			break;
		case FILE:
			break;
		case FORBIDED_UPLOAD_MAX_SIZE:
			break;
		case FORBIDED_UPLOAD_TOTAL_SPACE:
			break;

		case GET_FILE:
			break;
		case GET_FILE_LIST:
			break;

		case UPLOAD_FILE:
			break;
		default:
			break;	
		}
	}
	
	public void getFileList() {
			sendRequest(new Request(RequestType.GET_FILE_LIST));
	}
	
	public void uploadFiles() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			Path path = Paths.get(file.getAbsolutePath());
			try {
				byte [] data = Files.readAllBytes(path);
				Request request = new Request(RequestType.UPLOAD_FILE);
				request.setFileInBytes(data);
				request.setMessage(file.getName());
				sendRequest(request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	
	class SocketListener extends Thread implements Closeable{
		private Socket socket;
		ServerFileFormat serverFileFormat;

		@Override
		public void run() {
			try {
				socket = new Socket(serverIp, serverPort);
				inputStream = new ObjectInputStream(socket.getInputStream());
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				status.setForeground(Color.green);
				status.setText("STATUS: CONNECTED");
				disconnect.setEnabled(true);
				update.setEnabled(true);
				
				while (!socket.isClosed()) {
					try {
							System.out.println("Req");
							Request request = (Request) inputStream.readObject();
							checkRequestType(request);	
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

			} catch (UnknownHostException e) {
			
				System.out.println("Error 412");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Сервер не доступен", "Ошибка соединения", JOptionPane.CANCEL_OPTION);
			} finally {
				status.setForeground(Color.RED);
				status.setText("STATUS: DISCONNECTED");
				connect.setEnabled(true);
				disconnect.setEnabled(false);
			}
			
			
		}
		

		
		

		@Override
		public void close() throws IOException {
			socket.close();
			inputStream.close();
			outputStream.close();
			status.setForeground(Color.RED);
			status.setText("STATUS: DISCONNECTED");
			System.out.println("CLOSED");
			
		}
	}

	public static String getServerIp() {
		return serverIp;
	}

	public static void setServerIp(String serverIp) {
		Client.serverIp = serverIp;
	}

	public static int getServerPort() {
		return serverPort;
	}

	public static void setServerPort(int serverPort) {
		Client.serverPort = serverPort;
	}
	
	
	
}
