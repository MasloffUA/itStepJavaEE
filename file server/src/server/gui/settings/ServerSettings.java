package server.gui.settings;


/**
 * 
 * 
 * 
 * 
 * 
 * 
 *  Заготовка на будущее
 *  Не активна
 * 
 * 
 * 
 * 
 * 
 * 
 */


import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ServerSettings extends JFrame {
	private ServerSettings set;
	private JLabel portLabel = new JLabel("Port: ");
	private JTextField portText = new JTextField();
	private JLabel maxSize = new JLabel("Max uploaded file size in KB: ");
	private JTextField maxSizeText = new JTextField();
	private JLabel maxConnections = new JLabel("Max connections: ");
	private JTextField maxConnectionsText = new JTextField();	
	private JButton ok = new JButton("Save");
	private JButton cancel = new JButton("Cancel");
	
	public ServerSettings() {
		set = this;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//portText.setText(Integer.toString(Server.getPort()));
		//maxSizeText.setText(Integer.toString(Server.getMaxSize()));
		//maxConnectionsText.setText(Integer.toString(Server.getMaxConnections()));
		set.setResizable(false);
		this.setBounds(300, 300, 350, 150);
		Dimension labelSize = new Dimension(175, 25);
		Dimension fieldSize = new Dimension(175, 25);
		Dimension buttonSize = new Dimension(175, 25);
		
		portLabel.setMaximumSize(labelSize);
		maxSize.setMaximumSize(labelSize);
		maxConnections.setMaximumSize(labelSize);

		portText.setMaximumSize(fieldSize);
		maxSizeText.setMaximumSize(fieldSize);
		maxConnectionsText.setMaximumSize(fieldSize);
		
		
		ok.setMaximumSize(buttonSize);
		cancel.setMaximumSize(buttonSize);
		
		Box container = Box.createHorizontalBox();
		this.add(container);
		
		Box text = Box.createVerticalBox();
		text.add(portLabel);
		text.add(maxSize);
		text.add(maxConnections);
			// Заглушка
			Box empty = Box.createHorizontalBox();
			JLabel em = new JLabel("");
			em.setMaximumSize(buttonSize);
			empty.add(em);
			text.add(empty);
		container.add(text);
		
		Box f = Box.createVerticalBox();
		f.add(portText);
		f.add(maxSizeText);
		f.add(maxConnectionsText);
		
		Box buttons = Box.createHorizontalBox();
		buttons.add(ok);
		buttons.add(cancel);
		f.add(buttons);
		container.add(f);
		
		this.setVisible(true);
	}
	
	
	
}
