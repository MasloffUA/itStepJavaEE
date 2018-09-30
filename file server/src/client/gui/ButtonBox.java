package client.gui;



import java.awt.Dimension;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import client.Client;
import client.Controller;
import client.SocketListener;


public class ButtonBox extends Box {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Dimension buttonSize = new Dimension(175, 25);
	private ButtonListener listener;
	private JButton connect = new JButton("Подключиться");;
	private JButton disconnect = new JButton("Отсоединиться");
	private JButton settings = new JButton("Настройки");
	private JButton update = new JButton("Обновить список");
	private JButton download = new JButton("Скачать файл/файлы");
	private JButton upload = new JButton("Загрузить файл");
	private JButton delete = new JButton("Удалить");
	private JButton empty = new JButton("");

	
	public ButtonBox() {
		super(BoxLayout.PAGE_AXIS);
		Controller.setButtonBox(this);
		listener = new ButtonListener(this);
		
		connect.setMaximumSize(buttonSize);
		disconnect.setMaximumSize(buttonSize);
		settings.setMaximumSize(buttonSize);
		update.setMaximumSize(buttonSize);
		download.setMaximumSize(buttonSize);
		upload.setMaximumSize(buttonSize);
		delete.setMaximumSize(buttonSize);
		empty.setMaximumSize(buttonSize);
		
		connect.addActionListener(listener);
		disconnect.addActionListener(listener);
		settings.addActionListener(listener);
		update.addActionListener(listener);
		download.addActionListener(listener);
		upload.addActionListener(listener);
		delete.addActionListener(listener);
		
		disconnect.setEnabled(false);
		update.setEnabled(false);
		download.setEnabled(false);
		upload.setEnabled(false);
		delete.setEnabled(false);
		
		empty.setEnabled(false);
		empty.setBorderPainted(false);
				
		this.add(connect);
		this.add(disconnect);
		this.add(settings);
		this.add(update);
		this.add(download);
		this.add(upload);
		this.add(delete);
		this.add(empty);	
	}
	
	public void update() {
		System.out.println("Client: update buttons");
		System.out.println("Get Selected Files " + Client.getSelectedFiles());
		if (Controller.isUpdatingModel()) return;
		connect.setEnabled(true);
		disconnect.setEnabled(false);
		update.setEnabled(false); 
		download.setEnabled(false);
		upload.setEnabled(false);
		delete.setEnabled(false);
		if (Controller.isConnected()) {
			connect.setEnabled(false);
			disconnect.setEnabled(true);
			update.setEnabled(true);
			if (Client.getSelectedFiles()!= null && Client.getSelectedFiles().size()>0) {
				download.setEnabled(true);
				if (Client.getCanDeleted()!=null && Client.getCanDeleted().containsAll(Client.getSelectedFiles())) {
					delete.setEnabled(true);
				}
			}
			if (Client.isCanUpload()) {
				upload.setEnabled(true);
			}

		}
		else {
			connect.setEnabled(true);
		}
		Controller.updateStatus();
	}
	
	public void disabledButtonConnect() {
		connect.setEnabled(false);
	}

	public JButton getConnect() {
		return connect;
	}

	public JButton getDisconnect() {
		return disconnect;
	}

	public JButton getUpdate() {
		return update;
	}

	public JButton getDownload() {
		return download;
	}

	public JButton getUpload() {
		return upload;
	}

	public JButton getDelete() {
		return delete;
	}

	public JButton getSettings() {
		return settings;
	}
		
}
