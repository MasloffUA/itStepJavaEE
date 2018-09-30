package client.gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import client.Client;
import client.Controller;
import client.Settings;

public class InfoBox extends Box {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JLabel infoIp = new JLabel("Server IP: " + Settings.getServerIp());
	private static JLabel infoPort = new JLabel("Server PORT: " + Settings.getServerPort());
	private static JLabel infoFiles = new JLabel("Доступно файлов: " + Client.getSharedFileList().size());
	private static JLabel infoUpdateTime = new JLabel("Обновлено: никогда");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	public InfoBox() {
		super(BoxLayout.PAGE_AXIS);
		Controller.setInfoBox(this);
		
		this.add(infoIp);
		this.add(infoPort);
		this.add(infoFiles);
		this.add(infoUpdateTime);
	}

	public void update() {
		infoIp = new JLabel("Server IP: " + Settings.getServerIp());
		infoPort = new JLabel("Server PORT: " + Settings.getServerPort());
		infoFiles = new JLabel("Доступно файлов: " + Client.getSharedFileList().size());
		Date date = new Date();
		infoUpdateTime = new JLabel("Обновлено в " + dateFormat.format(date));
	}
	
	
}
