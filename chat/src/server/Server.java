package server;


import java.io.IOException;

import java.net.ServerSocket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;



public class Server {
	public static Map<String, SocketListener> users = new HashMap<>();
	private static Window frame = new Window("������ ����");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static ArrayList<String> messageHistory = new ArrayList<>();
	private static JTextField currentFiel = frame.getCurrentText();

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int port = 2806; // ���� ��� �� 21/09 :)
		currentFiel.setEnabled(false);
		currentFiel.setText("������� ��������� � ��������� ����� �� ��������");
		try(ServerSocket serverSocket = new ServerSocket(port)){
			frame.apendAllText(getCurrentTime() + " ������ �������: " + serverSocket.getLocalSocketAddress());
			while(true) {
				SocketListener sl = new SocketListener(serverSocket.accept());
				sl.start();
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getCurrentTime() {
		return dateFormat.format(new Date());
	}

	public static Window getFrame() {
		return frame;
	}

	public static ArrayList<String> getMessageHistory() {
		return messageHistory;
	}
	
	
	
	
	
}
