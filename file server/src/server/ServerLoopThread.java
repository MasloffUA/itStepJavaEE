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
		try {
			serverSocket = new ServerSocket(Settings.getPort(), Settings.getMaxConnections());
			Server.setServerSocket(serverSocket);
			Controller.updateConnectionStatus();
		} catch (IOException e) {
			Server.createMessage("������ ������� �������", "��������� ���� �����. ���������� ������� ������ ���� ��� �������.");
			try {
				close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		while(true) {
			ClientListener clientListener;
			if (serverSocket!=null) {
				try {
					clientListener = new ClientListener(serverSocket.accept());
					clientListeners.add(clientListener);
					clientListener.start();
				} catch (IOException e) {
					break;
				} 
			} else {
				break;
			}
		}
	}

	@Override
	public void close() throws IOException {
		Server.setServerSocket(null);
		if (serverSocket!=null) {
			serverSocket.close();
		}
		Server.clearServerLoop();
		//System.out.println("����� ������ closable");
		Controller.updateButtons();
		Controller.updateConnectionStatus();
		this.interrupt();
	}

	
}
