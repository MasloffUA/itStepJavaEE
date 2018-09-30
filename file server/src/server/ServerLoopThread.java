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
			System.out.println("���� �����");
			e.printStackTrace();
		}
		while(true) {
			//System.out.println("������ �������");
			ClientListener clientListener;
			try {
				clientListener = new ClientListener(serverSocket.accept());
				//System.out.println("���������� " + ++count);
				clientListeners.add(clientListener);
				clientListener.start();
			} catch (IOException e) {
				break;
			} finally {
				//System.out.println("����� ������");
				
			}

		}
	}

	@Override
	public void close() throws IOException {
		Server.setServerSocket(null);
		serverSocket.close();
		Server.clearServerLoop();
		//System.out.println("����� ������ closable");
		Controller.updateButtons();
		Controller.updateConnectionStatus();
		this.interrupt();
	}

	
}
