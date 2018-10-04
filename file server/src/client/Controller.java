package client;

import java.io.IOException;

import client.gui.ButtonBox;
import client.gui.ClientTable;
import client.gui.ClientTableModel;
import client.gui.InfoBox;
import common.Request;
import common.RequestType;
import server.gui.EastPanelBox;

public class Controller {
	private static ClientTableModel model;
	private static ClientTable table;
	private static ButtonBox buttonBox;
	private static InfoBox infoBox;
	private static SocketListener socketListener;
	
	public static void startSocketListener() {
		socketListener.start();
	}
	
	public static void closeSocketListener() {
		try {
			socketListener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendRequest(Request request) {
		socketListener.sendRequest(request);
	}
	
	public static void updateButtons() {
		buttonBox.update();
	}
	
	public static void updateInfoBox() {
		// написать
	}
	
	public static void updateStatus() {
		Client.getEastPanelBox().updateConnectionStatus();
	}
	
	public static void updateTable() {
		table.updateTable();
	}
	
	public static void changeUploadPermission(boolean permission) {
		Client.setCanUpload(permission);
		updateButtons();
	}
	
	public static void createMessage(Request request) {
		// написать
		Client.createMessage(request.getMessageHead(), request.getMessageData());
		
	}
	
	public static void uploadFiles() {
		Client.uploadFiles();
		
	}

	public static void removeFiles() {
		Request request = new Request(RequestType.DELETE_FILE);
		request.setFileOperationList(Client.getSelectedFiles());
		socketListener.sendRequest(request);
		
	}
	
	public static void disableButtonConnect() {
		buttonBox.disabledButtonConnect();
	}
	
	public static void getFileList() {
		Client.getFileList();
	}
	
	public static void reciveFileList(Request request) {
		Client.reciveFileList(request);
	}
	
	public static void downloadFiles() {
		Client.downloadFiles();
	}
	
	public static void reciveFiles(Request request) {
		Client.reciveFile(request);
	}
	

	public static void setModel(ClientTableModel model) {
		Controller.model = model;
	}

	public static void setButtonBox(ButtonBox buttonBox) {
		Controller.buttonBox = buttonBox;
	}

	public static void setInfoBox(InfoBox infoBox) {
		Controller.infoBox = infoBox;
	}
	
	public static void resetSelectedFiles() {
		model.resetSelectedFiles();
	}

	public static void setSocketListener(SocketListener socketListener) {
		Controller.socketListener = socketListener;
	}

	public static void setTable(ClientTable table) {
		Controller.table = table;
	}

	public static boolean isUpdatingModel() {
		return model.isUpdating();
	}
	
	public static boolean isConnected() {
		if (socketListener == null) {
			System.out.println("SocketListener: null");
			return false;
		}
		else {
			return socketListener.getSocket().isConnected();
		}
		
	}	
}
