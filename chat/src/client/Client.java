package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server.Message;
import server.RequestType;
import server.Window;

public class Client implements Closeable{
	private static int port = 2806;
	private static ObjectInputStream inputStream;
	private static ObjectOutputStream outputStream;
	private static boolean connection = false;
	private static Socket clientSocket;
	private static Scanner scanner;
	private static boolean nameAccepted;
	private static boolean nameRequest;
	private static Window frame = new Window("Клиент чата");
	private static JTextField inputTextArea = frame.getCurrentText();
	//private static Message message;
	
	
	public static void main(String[] args) throws InterruptedException {
		scanner = new Scanner(System.in);
		inputTextArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					//System.out.println("pressed enter");
					RequestType type = null;
					if (nameAccepted) {
						type = RequestType.MESSAGE;
						//System.out.println("enter-  name accepted");
					}
					else if (nameRequest) {
						type = RequestType.NAME_ANSWER;
						//System.out.println("enter -  nameRequset");
					}
					if (type!=null) {
						sendMessage(inputTextArea.getText(), type);
						inputTextArea.setText("");
					}
					
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		try {
			clientSocket = new Socket("localhost", port);
			inputStream = new ObjectInputStream(clientSocket.getInputStream());
			outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			connection = true;
			new ListenerServer().start();
/*			while (!nameAccepted) {
				//System.out.println("Name == null");
				Thread.sleep(100);
			}	*/	
			while (connection) {
				//frame.apendAllText(nameRequest + " " + nameAccepted);
				Thread.sleep(1000);
			}
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} 
		catch (ConnectException e) {
			frame.apendAllText("Сервер не доступен");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public static void printMessage(String text) {
		frame.apendAllText(text);
	}
	
	public static void nameRequest(String message) {
			frame.apendAllText(message);
			nameRequest = true;
	}
	
	
	public static void nameAccept(String message) {
		frame.apendAllText(message);
		nameRequest = false;
		sendMessage("", RequestType.GET_LAST_MESSAGES);
		
	}
	
	public static void uploadHistory(String history) {
		if (!nameAccepted) {
			frame.apendAllText(history);
		}
		nameAccepted = true;
	}
	
	
	
	
	
	private static class ListenerServer extends Thread {

		@Override
		public void run() {
			while(true) {
				try {
					Message message = (Message) inputStream.readObject();
					//System.out.println("Получен запрос с сервер" + message.getRequestType());
					switch (message.getRequestType()) {
					case MESSAGE:
						printMessage(message.getMessage());
						break;
					case NAME_ACCEPT:
						nameAccept(message.getMessage());
						break;
					case NAME_REQUEST:
						nameRequest(message.getMessage());
						break;
					case REFRESH_USER_LIST:
						frame.refreshUserList(message.getMessage());
						break;
					case HISTORY:
						uploadHistory(message.getMessage());
						break;
					default:
						break;			
					}
				} catch (ClassNotFoundException | IOException e) {
					frame.apendAllText("Соединение с сервером разорвано");
					break;
				}
			}
		}	
	}
	
	
	private static void sendMessage(String message, RequestType type) {
		try {
			outputStream.writeObject(new Message(message, type));
			outputStream.flush();
			//System.out.println("Сообщение отправлено на сервер");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void close() throws IOException {
		inputStream.close();
		outputStream.close();
		clientSocket.close();
		scanner.close();
	}

}
