package common.gui;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.*;

public class Window extends JFrame {
	protected JPanel mainPanel = new JPanel();
	protected JPanel westPanel = new JPanel();
	protected JLabel head = new JLabel("Список файлов:");
	protected JButton delete = new JButton("Удалить");	
	private static AtomicInteger code = new AtomicInteger(1099);
	protected Box buttonBox;
	protected static JLabel status = new JLabel("STATUS: OFFLINE");
	
	
	public Window(String name) {
	    super(name);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //eastPanel = Box.createVerticalBox();
	    mainPanel.setLayout(new BorderLayout());
	    this.add(mainPanel); 
	    mainPanel.add(head, BorderLayout.NORTH);
	}
	
}
