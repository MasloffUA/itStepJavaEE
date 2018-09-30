package client.gui;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import client.Controller;

public class ClientTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ClientTableModel model = new ClientTableModel();
	RowSorter<TableModel> sorter;
    
	public ClientTable(){
		Controller.setTable(this);
		this.setModel(model);
		model.addTableModelListener(new ClientTableModelListener(this, model));
		sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
	}

	public void updateTable() {
		model.update();
	}
	
}
