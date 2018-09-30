package server.gui;

import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import server.Server;
import server.Settings;


public class InfoBox extends Box {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JLabel connections = new JLabel("Connections: " + Server.getClientListeners().size() + " of " + Settings.getMaxConnections());
	private static AtomicInteger downloads = new AtomicInteger(0);
	private static AtomicInteger uploads = new AtomicInteger(0);
	private static JLabel totalSharedFiles = new JLabel("Shared files: " + Server.getSharedFileList().size());
	private static JLabel downloadsLabel = new JLabel("Downloads: 0");
	private static JLabel uploadsLabel = new JLabel("Uploads: 0");
	private static JLabel uploadFileLimitLabel = new JLabel("Max size file: œ–Œœ»—¿“‹ KB");
	private static JLabel totalUploadLimitLabel = new JLabel("Total limit: œ–Œœ»—¿“‹");

	public InfoBox() {
		super(BoxLayout.PAGE_AXIS);
		// TODO Auto-generated constructor stub
	}

}
