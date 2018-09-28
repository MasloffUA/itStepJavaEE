package fileServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClientSettings extends JFrame{
	private ClientSettings set;
	private JLabel ipLabel = new JLabel("Адрес сервера:");
	private JLabel portLabel = new JLabel("Порт сервера:");
	private JTextField ipText = new JTextField();
	private JTextField portText = new JTextField();
	private JButton ok = new JButton("Save");
	private JButton cancel = new JButton("Cancel");
	
	public ClientSettings() {
		set = this;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		set.setBounds(300,300,280,130);
		Dimension butSize = new Dimension(175, 25);
		ipText.setMaximumSize(butSize);
		ipLabel.setMaximumSize(butSize);
		portLabel.setMaximumSize(butSize);
		portText.setMaximumSize(butSize);
		ok.setMaximumSize(butSize);
		cancel.setMaximumSize(butSize);
		set.setResizable(false);
		Box all = Box.createHorizontalBox();
		this.add(all);
		
		Box text = Box.createVerticalBox();
		text.add(ipLabel);
		text.add(portLabel);
		all.add(text);
		
		Box empty = Box.createHorizontalBox();
		JLabel em = new JLabel("");
		em.setMaximumSize(butSize);
		empty.add(em);
		text.add(empty);
		
		Box field = Box.createVerticalBox();
		field.add(ipText);
		field.add(portText);
		all.add(field);
		
		
		Box buttons = Box.createHorizontalBox();
		buttons.add(ok);
		buttons.add(cancel);
		field.add(buttons);
	
		this.setVisible(true);
		
		ipText.setText(Client.getServerIp());
		portText.setText(Integer.toString(Client.getServerPort()));
		
		ok.addActionListener(new ActionListener() {		
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Client.setServerIp(ipText.getText());
				Client.setServerPort(Integer.parseInt(portText.getText()));
				set.dispose();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				set.dispose();
			}
		});
	}

}
