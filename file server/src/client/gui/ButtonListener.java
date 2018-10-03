package client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import client.Client;
import client.Controller;
import client.SettingsFrame;
import client.SocketListener;

public class ButtonListener implements ActionListener {
	private ButtonBox buttonBox;
	
	public ButtonListener(ButtonBox buttonBox) {
		this.buttonBox = buttonBox; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button == buttonBox.getConnect()) {
			Controller.disableButtonConnect();
			Controller.setSocketListener(new SocketListener());
			Controller.startSocketListener();
		}
		else if (button == buttonBox.getDisconnect()) {
			Controller.closeSocketListener();
		}
		else if (button == buttonBox.getUpdate()) {
			Controller.getFileList();
		}
		else if (button == buttonBox.getDownload()) {
			Controller.downloadFiles();
		}
		else if (button == buttonBox.getUpload()) {
			Controller.uploadFiles();
		}
		else if (button == buttonBox.getDelete()) {
			Controller.removeFiles();
		}
		else if (button == buttonBox.getSettings()) {
			Client.setSettings();

		}
		
	}

}
