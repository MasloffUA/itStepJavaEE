package ua.step.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * Код сервера
 * 
 */
public class Task02 {
	public static void main(String[] args) throws IOException {
		int portNumber = 9995;
		ServerSocket serverSocket = new ServerSocket(portNumber);
		int nPort = serverSocket.getLocalPort();
		System.out.println("Server adress: "
				+ serverSocket.getInetAddress().getHostAddress());
		System.out.println("Local Port: " + nPort);
		Socket socket = serverSocket.accept();
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		while (true) {
			/*
			 * Метод accept приостанавливает работу вызвавшего потока до тех
			 * пор, пока клиентское приложение не установит канал связи с
			 * сервером.
			 */
			
			// проверка наличия байт в потоке
			while(is.available()==0) {
				
			}
			if (is.available() > 0) {
				byte[] bytes = new byte[is.available()]; 
				is.read(bytes);
				String message = new String(bytes);
				//System.out.println(message);
				message = "Hello " + message;	
				os.write(message.getBytes());
			}
		}
	}
}
