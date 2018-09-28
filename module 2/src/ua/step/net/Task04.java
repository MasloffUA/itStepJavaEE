package ua.step.net;

import java.io.IOException;
import java.net.UnknownHostException;

import ua.step.net.model.MultiSocketServer;

/**
 * 
 * Многопоточный сервер
 *
 */
public class Task04 {
	public static void main(String[] args) {
		MultiSocketServer server = null;
		try {
			server = new MultiSocketServer(9990);
			server.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if (server != null ) server.finish();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
