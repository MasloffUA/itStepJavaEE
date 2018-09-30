package client.gui;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.JLabel;

import client.Controller;

public class EastPanelBox extends Box {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel status = new JLabel();
	
	public EastPanelBox() {
		super(BoxLayout.PAGE_AXIS);
		this.add(status);
		status.setText("STATUS: DISCONNECTED");
		status.setForeground(Color.red);
		this.add(new ButtonBox());
		
		// Заготовка на будущее
		// this.add(new InfoBox());
	}
	
	public void updateConnectionStatus () {
		if (Controller.isConnected()) {
			status.setText("STATUS: CONNECTED");
			status.setForeground(Color.GREEN);
		}
		else {
			status.setText("STATUS: DISCONNECTED");
			status.setForeground(Color.red);
		}

	}

}
