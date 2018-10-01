package client.gui;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

import client.Client;
import client.Controller;
import common.gui.ServerFileFormat;

public class ClientTableModel extends DefaultTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, ServerFileFormat> sharedFileList = Client.getSharedFileList();
	private Set<Integer> selectedFiles = Client.getSelectedFiles();
	private Set<Integer> canDeleted = Client.getCanDeleted();
	private boolean updating = false;
	
	
	public ClientTableModel() {
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
			return String.class;
		default:
			return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// Для клиента к редактированию доступна только первая колонка
		return column==0;
	}	
	
	public void update() {
		updating = true;
		sharedFileList = Client.getSharedFileList();
		canDeleted = Client.getCanDeleted();
		this.getDataVector().removeAllElements();
		this.fireTableDataChanged();
		int i = 0;
		System.out.println("Updating Table " + sharedFileList.size());
		for (ServerFileFormat f : sharedFileList.values()) {
			this.addRow(new Object[0]);
			this.setValueAt(selectedFiles.contains(f.getCode()) ? true : false ,i,0);
			this.setValueAt(f.getCode(), i, 1);
			this.setValueAt(f.getName(), i, 2);
			this.setValueAt(f.getSize(), i, 3);
			this.setValueAt(f.getDescription(), i, 4);
			this.setValueAt(canDeleted.contains(f.getCode()) ? "Разрешено" : "Запрещено",i,5);
			i++;
		}
		updating = false;
		this.fireTableDataChanged();
		
	}
	
	public void resetSelectedFiles() {
		selectedFiles = new HashSet<>();
	}

	public Set<Integer> getSelectedFiles() {
		return selectedFiles;
	}

	public Set<Integer> getCanDeleted() {
		return canDeleted;
	}

	public Map<Integer, ServerFileFormat> getSharedFileList() {
		return sharedFileList;
	}

	public boolean isUpdating() {
		return updating;
	}
	
	
	
	
}
