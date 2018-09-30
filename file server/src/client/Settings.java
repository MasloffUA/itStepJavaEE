package client;

public class Settings {
	private static int serverPort;
	private static String serverIp;
	
	static {
		serverPort = 2877;
		serverIp = "localhost";
	}

	public static int getServerPort() {
		return serverPort;
	}

	public static void setServerPort(int serverPort) {
		Settings.serverPort = serverPort;
	}

	public static String getServerIp() {
		return serverIp;
	}

	public static void setServerIp(String serverIp) {
		Settings.serverIp = serverIp;
	}
	
}
