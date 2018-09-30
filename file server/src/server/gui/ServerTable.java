package server.gui;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import server.Controller;

public class ServerTable extends JTable {
	ServerTableModel model = new ServerTableModel();
	RowSorter<TableModel> sorter;
	
	public ServerTable() {
		Controller.setTable(this);
		this.setModel(model);
		model.addTableModelListener(new ServerTableModelListener(this, model));
		sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		this.getColumnModel().getColumn(0).setPreferredWidth(50);
		this.getColumnModel().getColumn(1).setPreferredWidth(0);
		this.getColumnModel().getColumn(2).setPreferredWidth(300);
		this.getColumnModel().getColumn(3).setPreferredWidth(40);
		this.getColumnModel().getColumn(4).setPreferredWidth(100);
		this.getColumnModel().getColumn(5).setPreferredWidth(30);
	}
	
	public void updateTable() {
		model.update();
	}

}
