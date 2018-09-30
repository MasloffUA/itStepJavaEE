package server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class ServerLoopThread extends Thread implements Closeable {
	List<ClientListener> clientListeners = Server.getClientListeners();
	ServerSocket serverSocket = Server.getServerSocket(); 
	
	@Override
	public void run() {
		super.run();
		//int count = 0;
		try {
			serverSocket = new ServerSocket(Settings.getPort(), Settings.getMaxConnections());
			Server.setServerSocket(serverSocket);
			Controller.updateConnectionStatus();
		} catch (IOException e) {
			System.out.println("Порт занят");
			e.printStackTrace();
		}
		while(true) {
			//System.out.println("Сервер запущен");
			ClientListener clientListener;
			try {
				clientListener = new ClientListener(serverSocket.accept());
				//System.out.println("Соединение " + ++count);
				clientListeners.add(clientListener);
				clientListener.start();
			} catch (IOException e) {
				break;
			} finally {
				//System.out.println("Сокет закрыт");
				
			}

		}
	}

	@Override
	public void close() throws IOException {
		Server.setServerSocket(null);
		serverSocket.close();
		Server.clearServerLoop();
		//System.out.println("Сокет закрыт closable");
		Controller.updateButtons();
		Controller.updateConnectionStatus();
		this.interrupt();
	}

	
}
