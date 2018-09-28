package server;

import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window {
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JTextArea allText = new JTextArea(20, 39);
	private JTextArea userList = new JTextArea(20, 12);
	private JTextField currentText = new JTextField(53);
	private JScrollPane scroll = new JScrollPane(allText);
	
	public Window(String s) {
		frame.setTitle(s);
		frame.setSize(new Dimension(600, 390));
		frame.setLocation(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);
		userList.setText("Онлайн (0):");
		userList.setEditable(false);
		allText.setEditable(false);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		allText.setLineWrap(true);
		
		panel.add(scroll);
		panel.add(userList);
		panel.add(currentText);

		frame.setVisible(true);
	}
	
	public void apendAllText(String message) {
		allText.append(message+"\n");
	}

	public JTextField getCurrentText() {
		return currentText;
	}
	
	public void refreshUserList(String list) {
		userList.setText(list);
	}

}
