package server.gui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import common.gui.ServerFileFormat;
import server.Controller;
import server.Server;

public class ServerTableModel extends DefaultTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, ServerFileFormat> sharedFileList = Server.getSharedFileList();
	private Set<Integer> selectedFiles = Server.getSelectedFiles();
	private Set<Integer> canDeleted = Server.getCanDeleted();
	private boolean updating = false;
	
	public ServerTableModel() {
		Controller.setModel(this);

		this.addColumn("Выбранные");
		this.addColumn("id");
		this.addColumn("Файл");
		this.addColumn("Размер файла, КБ");
		this.addColumn("Описание");
		this.addColumn("Удаление");
	}
	
	public Class<?> getColumnClass(int column)
	{
		switch(column)
		{
		case 0:
			return Boolean.class;
		case 1:
			return Integer.class;
		case 2:
			return String.class;
		case 3:
			return Integer.class;
		case 4:
			return String.class;
		case 5:
			return Boolean.class;
		default:
			return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column==4 || column==0 || column ==5;
	}
	
	public void update() {
		updating = true;
		Server.updateLists();
		sharedFileList = Server.getSharedFileList();
		
		this.getDataVector().removeAllElements();
		this.fireTableDataChanged();
		
		int row = 0;
		for (ServerFileFormat f : sharedFileList.values()) {
			System.out.println(sharedFileList.size());
			this.addRow(new Object[0]);
			this.setValueAt(false,row,0);
			this.setValueAt(f.getCode(), row, 1);
			this.setValueAt(f.getFile(this).getAbsolutePath(), row, 2);
			this.setValueAt(f.getSize(), row, 3);
			this.setValueAt(f.getDescription(), row, 4);
			this.setValueAt(f.isCanDeleted(),row,5);
			row++;

		}
		
		this.fireTableDataChanged();
		updating = false;
	}

	public boolean isUpdating() {
		return updating;
	}
	
	
}
