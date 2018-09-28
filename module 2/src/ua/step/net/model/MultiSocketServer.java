package ua.step.net.model;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiSocketServer {
	private final ServerSocket serverSocket;

	public MultiSocketServer(int portNumber) throws IOException {
		this.serverSocket = new ServerSocket(portNumber);
	}

	public void start()
	{
		while(true)
		{
			try {
				new ServerTask(serverSocket.accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void finish() throws IOException
	{
		serverSocket.close();
	}
}
