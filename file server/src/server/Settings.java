package server;

public class Settings {
	private static int port = 2877;
	private static int sizeFileUploadLimit = 1048576;
	private static int totalUploadLimit = 0;	
	private static int maxConnections = 5;
	private static boolean canUpload = false;
	
	public static int getPort() {
		return port;
	}
	public static void setPort(int port) {
		Settings.port = port;
	}
	public static int getSizeFileUploadLimit() {
		return sizeFileUploadLimit;
	}
	public static void setSizeFileUploadLimit(int sizeFileUploadLimit) {
		Settings.sizeFileUploadLimit = sizeFileUploadLimit;
	}
	public static int getTotalUploadLimit() {
		return totalUploadLimit;
	}
	public static void setTotalUploadLimit(int totalUploadLimit) {
		Settings.totalUploadLimit = totalUploadLimit;
	}
	public static int getMaxConnections() {
		return maxConnections;
	}
	public static void setMaxConnections(int maxConnections) {
		Settings.maxConnections = maxConnections;
	}
	public static boolean isCanUpload() {
		return canUpload;
	}
	public static void setCanUpload(boolean canUpload) {
		Settings.canUpload = canUpload;
	}
	
	
}
