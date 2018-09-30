package server;


import client.gui.EastPanelBox;
import common.Request;
import server.gui.ButtonBox;
import server.gui.InfoBox;
import server.gui.ServerTable;
import server.gui.ServerTableModel;

public class Controller {
	private static ServerTableModel model;
	private static ServerTable table;
	private static ButtonBox buttonBox;
	private static InfoBox infoBox;
	private static Server server;
	
	public static void sendOneRequest(Request request) {
		// Заготовка
		
	}

	public static void updateConnectionStatus() {
		if (Server.getEastPanelBox() != null) {
			Server.getEastPanelBox().updateConnectionStatus();
		}
	}
	
	public static void setModel(ServerTableModel model) {
		Controller.model = model;
	}

	public static void setTable(ServerTable table) {
		Controller.table = table;
	}

	public static void setButtonBox(ButtonBox buttonBox) {
		Controller.buttonBox = buttonBox;
	}

	public static void setInfoBox(InfoBox infoBox) {
		Controller.infoBox = infoBox;
	}

	public static void setServer(Server server) {
		Controller.server = server;
	}

	public static void updateTable() {
		model.update();
		
	}

	public static void updateButtons() {
		buttonBox.update();
		
	}
	
	public static boolean isUpdatingModel() {
		return model.isUpdating();
	}



	public static ServerTableModel getModel() {
		return model;
	}

	public static boolean isConnected() {
		if (Server.getServerSocket() == null) {
			return false;
		}
		else {
			return true;
		}
		
	}


	


}
