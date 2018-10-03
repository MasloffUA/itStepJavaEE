package server.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import server.Controller;
import server.Server;
import server.Settings;
import server.gui.ButtonListener;

public class ButtonBox extends Box{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Dimension buttonSize = new Dimension(270, 25);
	private ButtonListener listener;
	private JButton start = new JButton("Запустить сервер");
	private JButton stop = new JButton("Остановить сервер");
	private JButton settings = new JButton("Настройки");
	private JButton apply = new JButton("Применить разрешения на удаление");
	private JButton uploadFolder = new JButton("Выбрать папку для загрузок");
	private JButton upload = new JButton("Разрешить загрузу");	
	private JButton shareFolder = new JButton("Открыть общий доступ к папке");
	private JButton shareFile = new JButton("Открыть общий доступ к файлу");
	private JButton stopShare = new JButton("Отключить доступ к выбранным фалам");
	private JButton delete = new JButton("Удалить с диска");
	private JButton empty = new JButton("");
	private List<JButton> buttonList = new ArrayList<>();
	
	public ButtonBox() {
		super(BoxLayout.PAGE_AXIS);
		Controller.setButtonBox(this);
		listener = new ButtonListener(this);
		
		buttonList.add(start);
		buttonList.add(stop);
		buttonList.add(settings);
		buttonList.add(apply);
		buttonList.add(uploadFolder);
		buttonList.add(upload);
		buttonList.add(shareFolder);
		buttonList.add(shareFile);
		buttonList.add(stopShare);
		buttonList.add(delete);
		buttonList.add(empty);
		
		for (JButton button : buttonList) {
			button.setMaximumSize(buttonSize);
			button.addActionListener(listener);
			this.add(button);
		}
		
		buttonList.remove(settings);
		buttonList.remove(uploadFolder);
		buttonList.remove(shareFolder);
		buttonList.remove(shareFile);
		
		// Заготовка с СЕТИИНГС
	
		stop.setEnabled(false);
		apply.setEnabled(false);
		upload.setEnabled(false);
		stopShare.setEnabled(false);
		delete.setEnabled(false);
		delete.setToolTipText("Удалить выбранные файлы с жёсткого диска. Для безопасности - функция доступна только если удялаяемые файлы отмечены, как \"Разрешить удалять\"");
		
		empty.setEnabled(false);
		empty.setBorderPainted(false);
			
	}
	
	public void update() {	
		// Ниже - супер строчка :) Если её закомментировать - время предоставления доступа к папкам с количеством файлов от 50 увеличится в разы.
		if (Controller.isUpdatingModel()) return;
		for (JButton button : buttonList) {
			button.setEnabled(false);
		}
		if (Server.getServerLoop()==null) {
			start.setEnabled(true);
		}
		else if (Server.getServerLoop().isAlive()) {
			stop.setEnabled(true);
		}
		if (Server.getUploadFolder()!=null) {
			upload.setEnabled(true);
			if (Settings.isCanUpload()) {
				upload.setText("Запретить загрузку");
			}
			else {
				upload.setText("Разрешить загрузку");
			}
		}
		if (Server.getSelectedFiles().size()>0 && Server.getCanDeleted().containsAll(Server.getSelectedFiles())) {
			delete.setEnabled(true);
		}
		if (Server.getSelectedFiles().size()>0) {
			stopShare.setEnabled(true);
		}
		if (Server.getRemovingSelectedFiles().size()>0 || Server.getUnRemovingSelectedFiles().size()>0) {
			apply.setEnabled(true);
		}

		
	}

	public ButtonListener getListener() {
		return listener;
	}

	public JButton getStart() {
		return start;
	}

	public JButton getStop() {
		return stop;
	}

	public JButton getSettings() {
		return settings;
	}

	public JButton getApply() {
		return apply;
	}

	public JButton getUploadFolder() {
		return uploadFolder;
	}

	public JButton getUpload() {
		return upload;
	}

	public JButton getShareFolder() {
		return shareFolder;
	}

	public JButton getShareFile() {
		return shareFile;
	}

	public JButton getStopShare() {
		return stopShare;
	}

	public JButton getDelete() {
		return delete;
	}
	
	
}
