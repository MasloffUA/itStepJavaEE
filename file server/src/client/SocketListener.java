package client;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import common.Request;

public class SocketListener extends Thread  implements Closeable{
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	@Override
	public void run() {
		try {
			socket = new Socket(Settings.getServerIp(), Settings.getServerPort());
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());		
			Controller.updateStatus();
			Client.getFileList();
			Controller.updateButtons();
			while (!socket.isClosed()) {
				try {
						Request request = (Request) inputStream.readObject();
						checkRequestType(request);	
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		} catch (UnknownHostException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			Client.createMessage("Ошибка соединения", "Сервер не доступен");
			try {
				close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			//Controller.setSocketListener(null);
			Controller.updateButtons();
		}		
	}
	
	public void sendRequest(Request request) {
		try {
			outputStream.writeObject(request);
			outputStream.flush();
			//System.out.println("Запрос на получение файла отправлен клиентом");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private void checkRequestType(Request request) {
		switch(request.getType()) {
		case CONNECTED:
			Controller.getFileList();
			break;
		case SEND_FILE_LIST:
			Controller.reciveFileList(request);
			break;
		case CAN_UPLOAD:
			Controller.changeUploadPermission(true);
			break;
		case FORBID_UPLOAD:
			Controller.changeUploadPermission(false);
			break;
		case SEND_FILE:
		case CONTINUE_SENDING_FILES:
			Controller.reciveFiles(request);
			break;	
		case MESSAGE:
			Controller.createMessage(request);
			break;
		case FORBIDED_UPLOAD_MAX_SIZE:
			break;
		case FORBIDED_UPLOAD_TOTAL_SPACE:
			break;		
		case DELETE_FILE:
			break;
		default:
			
		}
	}

	@Override
	public void close() throws IOException {
		Controller.setSocketListener(null);
		socket.close();
		inputStream.close();
		outputStream.close();
		Controller.updateButtons();
		Controller.updateStatus();
		this.interrupt();
		
	}

	public Socket getSocket() {
		return socket;
	}
	
	
}
