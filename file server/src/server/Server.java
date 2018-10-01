package server;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import java.net.ServerSocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

import common.Request;
import common.RequestType;
import common.gui.ServerFileFormat;
import common.gui.Window;

import server.gui.EastPanelBox;
import server.gui.ServerTable;

public class Server extends Window {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServerSocket serverSocket;
	private static ArrayList<ClientListener> clientListeners = new ArrayList<>();
	private static Map<Integer,ServerFileFormat> sharedFileList = new HashMap<>();
	private static Set<Integer> canDeleted = new HashSet<>();
	private static Set<Integer> selectedFiles = new HashSet<>();
	private static Set<Integer> removingSelectedFiles = new HashSet<>();
	private static Set<Integer> unRemovingSelectedFiles = new HashSet<>();
	private static File uploadFolder;
	private static File file;
	private static JFileChooser fileChooser = new JFileChooser();
	private static EastPanelBox eastPanelBox;
	private static Set<File> fileList = new HashSet<>();
	
	private static boolean online = false;
	private static ServerLoopThread serverLoop;
	
	
	public Server(String name) {
		super(name);
		ServerTable table = new ServerTable();
		this.add(new JScrollPane(table)); 
		eastPanelBox = new EastPanelBox();
		this.add(eastPanelBox, BorderLayout.EAST);
		this.setBounds(300, 300, 1200, 800);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Server server = new Server("File Server");
		
		Controller.setServer(server);
	}
	
	public static void createServerLoop() {
		if (serverLoop == null) {
			serverLoop = new ServerLoopThread();
			serverLoop.start();
		}
	}
	
	public static void stopServerLoop() {
		if (serverLoop!=null) {
			try {
				serverLoop.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
	}
	
	public static void applyNewPermissions() {
		for (Integer i : removingSelectedFiles) {
			sharedFileList.get(i).setCanDeleted(true);
			canDeleted.add(i);
		}
		for (Integer i : unRemovingSelectedFiles) {
			sharedFileList.get(i).setCanDeleted(false);
			canDeleted.remove(i);
		}
		removingSelectedFiles = new HashSet<>();
		unRemovingSelectedFiles = new HashSet<>();
		Controller.updateTable();
		Controller.updateButtons();
		Server.sendFileListToAll();
	}
	
	public static void chooseUploadFolder() {
		shareFolder();
		uploadFolder = file;
	}
	
	public static void shareFolder() {
		File file = null;
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			ServerFileFormat newFile = new ServerFileFormat(file);
			Server.file = file;
			findFiles(file);		
			
		};
		Controller.updateTable();
		Server.sendFileListToAll();
	}
	
	public static void findFiles(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files)
			findFiles(f);
		}
		else if(file.isFile()) {
			putInSharedFileList(file);
		}
		Controller.updateTable();
	

	}
	
	
	public static void shareFile() {
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			putInSharedFileList(file);
			Controller.updateTable();
		};	
		
	}
	
	
	public static void putInSharedFileList(File file) {
		if (!fileList.contains(file)) {
			ServerFileFormat newFile = new ServerFileFormat(file);
			fileList.add(file);
			sharedFileList.put(newFile.getCode(), newFile);
		}

	}
	
	public static void unShareFile() {
		sharedFileList.keySet().removeAll(selectedFiles);
		Controller.updateTable();
		Controller.updateButtons();
		sendFileListToAll();
		
	}
	
	public static void deleteSelectedFiles(Set<Integer> files) {
		for (Integer i : files) {
			// Дополнительная проверка на стороне сервера
			if (sharedFileList.get(i).isCanDeleted()) {
				File removingFile = sharedFileList.get(i).getFile(Controller.getModel());
				boolean deleted = removingFile.delete();
				if (deleted) {
					sharedFileList.remove(i);
				}
			}
			else {
				//Удаление невозможно
			}
		}
		Controller.updateTable();
		Server.sendFileListToAll();
		
	}
	

	public static void updateLists() {
		Map<Integer, ServerFileFormat> temp = new HashMap<>();
		temp.putAll(sharedFileList);
		sharedFileList = temp;
		Set<Integer> temp2 = new HashSet<>();
		temp2.addAll(canDeleted);
		canDeleted = temp2;
	}
	
	
	public static void sendFileListToAll() {
		updateLists();
		System.out.println("FileShared UFTER update " + Server.getSharedFileList().size());
		for(ClientListener clientListener : clientListeners) {
			clientListener.sendFileList();
		}
	}
	
	
	public static void getFreeSpace() {
		if (uploadFolder!=null) {
			System.out.println(uploadFolder.getUsableSpace()/1024/1024 + " MB");
			System.out.println(uploadFolder.getFreeSpace()/1024/1024 + " MB");
		}
	}
	
	public static void delitingFiles(Request request) {
		
	}
	

	public static Map<Integer, ServerFileFormat> getSharedFileList() {
		return sharedFileList;
	}

	public static ArrayList<ClientListener> getClientListeners() {
		return clientListeners;
	}

	public static Set<Integer> getSelectedFiles() {
		return selectedFiles;
	}

	public static Set<Integer> getCanDeleted() {
		return canDeleted;
	}

	public static void setOnline(boolean online) {
		Server.online = online;
	}

	public static ServerSocket getServerSocket() {
		return serverSocket;
	}

	public static void setServerSocket(ServerSocket serverSocket) {
		Server.serverSocket = serverSocket;
	}

	public static ServerLoopThread getServerLoop() {
		return serverLoop;
	}

	public static void clearServerLoop() {
		serverLoop = null;
	}

	public static File getUploadFolder() {
		return uploadFolder;
	}

	public static Set<Integer> getRemovingSelectedFiles() {
		return removingSelectedFiles;
	}
	
	

	public static Set<Integer> getUnRemovingSelectedFiles() {
		return unRemovingSelectedFiles;
	}

	public static void sendUploadPermission() {
		if (Settings.isCanUpload()) {
			Server.sendRequestAll(new Request(RequestType.CAN_UPLOAD));
		}
		else {
			Server.sendRequestAll(new Request(RequestType.FORBID_UPLOAD));
		}
		Controller.updateButtons();
	}

	private static void sendRequestAll(Request request) {
		for (ClientListener clientListener : clientListeners) {
			clientListener.sendRequest(request);
		}
		
	}

	public static EastPanelBox getEastPanelBox() {
		return eastPanelBox;
	}

	public static Set<File> getFileList() {
		return fileList;
	}
	
	
	
}
