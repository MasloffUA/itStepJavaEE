package client;

import java.awt.BorderLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import client.gui.ClientTable;
import client.gui.EastPanelBox;

import common.Request;
import common.RequestType;
import common.gui.ServerFileFormat;
import common.gui.Window;

public class Client extends Window{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<Integer,ServerFileFormat> sharedFileList = new HashMap<>();
	private static boolean canUpload = false;
	private static Set<Integer> selectedFiles = new HashSet<>();
	private static Set<Integer> canDeleted;
	private static EastPanelBox eastPanelBox;
	private static SocketListener socketListener;
	private static Client client;
	private static File downloadDirectory;
	
	private Client(String name){
		super(name);
		ClientTable table = new ClientTable();
		this.add(new JScrollPane(table)); 
		eastPanelBox = new EastPanelBox();
		this.add(eastPanelBox, BorderLayout.EAST);
		this.setBounds(300, 300, 1000, 800);
	}

	public static void main(String[] args) {
		client  = new Client("Client for FileServer");
		client.setVisible(true);
	}
	
	
	public static void reciveFile(Request request) {
		JFileChooser chooser = new JFileChooser();
		if (request.getType() == RequestType.SEND_FILE) {
			chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				downloadDirectory = chooser.getSelectedFile();
				writeRecivedFile(request);
			}		
		}
		else if (request.getType() == RequestType.CONTINUE_SENDING_FILES) {
			writeRecivedFile(request);
		}
		Controller.resetSelectedFiles();	
	}
	
	// Дописать проверку на существование файла
	public static void  writeRecivedFile(Request request) {
		System.out.println(downloadDirectory.getAbsolutePath());
		File writingFile = new File(downloadDirectory.getAbsolutePath() + "\\" + request.getMessageData());
		try(FileOutputStream fos = new FileOutputStream(writingFile)){
			fos.write(request.getFileInBytes());
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getFileList() {
			Controller.sendRequest(new Request(RequestType.GET_FILE_LIST));
	}
	
	public static void downloadFiles() {
		Request request = new Request(RequestType.GET_FILE);
		request.setFileOperationList(selectedFiles);
		Set<Integer> temp = new HashSet<>();
		temp.addAll(selectedFiles);
		selectedFiles = temp;
		Controller.sendRequest(request);
	}
	
	public static void uploadFiles() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			Path path = Paths.get(file.getAbsolutePath());
			try {
				byte [] data = Files.readAllBytes(path);
				Request request = new Request(RequestType.UPLOAD_FILE);
				request.setFileInBytes(data);
				request.setMessageData(file.getName());
				Controller.sendRequest(request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void reciveFileList(Request request) {
		System.out.println("Recieve File List");
		sharedFileList = request.getFileList();
		System.out.println(sharedFileList.size());
		canDeleted = request.getCanDeleted();
		Controller.updateTable();
	}
	
	
	public static void setSettings() {
		SettingsFrame settingsFrame = new SettingsFrame();
		settingsFrame.setLocationRelativeTo(client);
		settingsFrame.setAlwaysOnTop(true);
		settingsFrame.setVisible(true);	
	}
	
	public static void createMessage(String head, String message) {
		JOptionPane.showMessageDialog(client, message, head, JOptionPane.CANCEL_OPTION);
	}
	
	public static void setCanUpload(boolean canUpload) {
		Client.canUpload = canUpload;
		Controller.updateButtons();
	}

	public static Map<Integer, ServerFileFormat> getSharedFileList() {
		return sharedFileList;
	}

	public static boolean isCanUpload() {
		return canUpload;
	}

	public static Set<Integer> getCanDeleted() {
		return canDeleted;
	}

	public static Set<Integer> getSelectedFiles() {
		return selectedFiles;
	}

	public static EastPanelBox getEastPanelBox() {
		return eastPanelBox;
	}

	public static SocketListener getSocketListener() {
		return socketListener;
	}

	public static void setSocketListener(SocketListener socketListener) {
		Client.socketListener = socketListener;
	}


	
}
