package server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import server.Controller;
import server.Server;
import server.Settings;

public class ButtonListener implements ActionListener {
	private ButtonBox buttonBox;
	
	public ButtonListener(ButtonBox buttonBox) {
		this.buttonBox = buttonBox; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button == buttonBox.getStart()) {
			Server.createServerLoop();
		}
		else if (button == buttonBox.getStop()) {
			Server.stopServerLoop();
		}
		else if (button == buttonBox.getSettings()) {
			// Написать код
		}
		else if (button == buttonBox.getApply()) {
			Server.applyNewPermissions();
		}
		else if (button == buttonBox.getUploadFolder()) {
			Server.chooseUploadFolder();
		}
		else if (button == buttonBox.getUpload()) {
			Settings.setCanUpload(!Settings.isCanUpload());
			Server.sendUploadPermission();
		}
		else if (button == buttonBox.getShareFolder()) {
			Server.shareFolder();
		}
		else if (button == buttonBox.getShareFile()) {
			Server.shareFile();
		}
		else if (button == buttonBox.getStopShare()) {
			Server.unShareFile();
		}
		else if (button == buttonBox.getDelete()) {
			Server.deleteSelectedFiles(Server.getSelectedFiles());
		}
		Controller.updateButtons();
		
	}
}
