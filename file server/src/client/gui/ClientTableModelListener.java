package client.gui;

import javax.swing.event.TableModelEvent;

import client.Client;
import client.Controller;

public class ClientTableModelListener implements javax.swing.event.TableModelListener {
	private ClientTable table;
	private ClientTableModel model;


	public ClientTableModelListener(ClientTable table, ClientTableModel model) {
		this.table = table;
		this.model = model;
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		if (table.getEditingRow()>-1 && table.getEditingColumn()==0) {
			boolean choosed = (boolean) model.getValueAt(table.getEditingRow(), 0);	
				if (choosed) {
					Client.getSelectedFiles().add((Integer) model.getValueAt(table.getEditingRow(), 1));
				}
				else {
					Client.getSelectedFiles().remove((Integer) model.getValueAt(table.getEditingRow(), 1));
				}
			}
			Controller.updateButtons();	
		}	
	
}
