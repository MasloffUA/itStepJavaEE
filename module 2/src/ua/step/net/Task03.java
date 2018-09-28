package ua.step.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * Клиентский код
 *
 */
public class Task03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			// открываем сокет и коннектимся к localhost:3128
			// получаем сокет сервера
			Socket socket = new Socket("localhost", 9990);
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			String resp = null;	
			System.out.println("Please input string");
			String message = scanner.nextLine();	
			System.out.println("Client connected");
			
			while(true)
			{

	
				os.write(message.getBytes());
				while (is.available()==0) {
					
				}
				
				if (is.available()>0) {
					byte[] bytes = new byte[is.available()]; 
					is.read(bytes);
					resp = new String(bytes);
					System.out.println(resp);
				}
			}

			
		} catch (Exception e) {
			System.out.println("init error: " + e);
		} 
	}
}
