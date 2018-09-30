package server;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import common.Request;
import common.RequestType;

public class ClientListener extends Thread implements Closeable {
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
	}
	
	@Override
	public void run() {
		Controller.sendOneRequest(new Request(RequestType.CONNECTED));
		System.out.println("Сокет запущен (Листенер)");
		while(!socket.isClosed()) {
			try {
				Request request = (Request) inputStream.readObject();
				checkRequest(request);
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Server: client listener Прервано (Catch)");
				try {
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		Server.getClientListeners().remove(this);
		System.out.println("Clietn listener removed");

	}
	
	public void sendFileList() {
		//System.out.println("Send fileList " + Server.getSharedFileList().size());
		
		Request request = new Request(RequestType.SEND_FILE_LIST);
		request.setFileList(Server.getSharedFileList());
		request.setCanDeleted(Server.getCanDeleted());
		sendRequest(request);
	}
	
	public void sendRequest(Request request) {
		try {
			outputStream.writeObject(request);
			outputStream.flush();
		} catch (IOException e) {
			System.out.println("ClientListener: send request CATCH");
			e.printStackTrace();
		}
		
	}
	
	public void sendFiles(Set<Integer> list) {
		for (Integer i : list) {
			if (Server.getSharedFileList().containsKey(i)) {
				File file = Server.getSharedFileList().get(i).getFile(Controller.getModel());
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
	
	public void reciveFile(Request request) {
		
		File file = new File(Server.getUploadFolder()+"\\"+request.getMessage());
			try(FileOutputStream fos = new FileOutputStream(file)){
				//System.out.println(file.getAbsolutePath());
				fos.write(request.getFileInBytes());
				fos.flush();
				Server.putInSharedFileList(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("Приём файла окончен");
		Controller.updateTable();
		Server.sendFileListToAll();
	}
		
	private void checkRequest(Request request) {
		switch(request.getType()) {
		case GET_FILE_LIST:
			System.out.println("Запрос на получение списка файлов получен сервером");
			sendFileList();
			if (Settings.isCanUpload()) {
				sendRequest(new Request(RequestType.CAN_UPLOAD));
			}
			break;		
		case GET_FILE:
			sendFiles(request.getFileOperationList());
			break;	
		case CONNECTED:
			break;
		case DELETE_FILE:
			Server.deleteSelectedFiles(request.getFileOperationList());
			break;
		case FORBIDED_UPLOAD_MAX_SIZE:
			break;
		case FORBIDED_UPLOAD_TOTAL_SPACE:
			break;
		case SEND_FILE_LIST:
			break;
		case UPLOAD_FILE:
			reciveFile(request);
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
