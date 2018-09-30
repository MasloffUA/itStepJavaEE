package server.gui;

import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import server.Controller;
import server.Server;
import server.ServerFileFormat;

public class ServerTableModelListener implements TableModelListener{
	private ServerTable table;
	private ServerTableModel model;
	
	public ServerTableModelListener(ServerTable table, ServerTableModel model) {
		this.table = table;
		this.model = model;
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		if (Controller.isUpdatingModel()) return;
		// Если изменения произошли в ячейках "описание" или "разрешить/запретить удаление"
		if (table.getEditingRow()>-1 && table.getEditingColumn()>3) {
			ServerFileFormat serverFile = Server.getSharedFileList().get(model.getValueAt(table.getEditingRow(), 1));
			if (table.getEditingColumn()==4) {
				serverFile.setDescription((String) model.getValueAt(table.getEditingRow(), 4));
				System.out.println("Column 4");
			} else if (table.getEditingColumn()==5) {
				boolean selected = (boolean) model.getValueAt(table.getEditingRow(), 5);
				Integer id = (Integer) model.getValueAt(table.getEditingRow(), 1);
				if (Server.getCanDeleted().contains(id)) {
					if (selected) {
						Server.getRemovingSelectedFiles().remove(id);
						Server.getUnRemovingSelectedFiles().remove(id);
					} else {
						Server.getUnRemovingSelectedFiles().add(id);
					}
				} else {
					if (selected) {
						Server.getRemovingSelectedFiles().add(id);
					} else {
						Server.getRemovingSelectedFiles().remove(id);
						Server.getUnRemovingSelectedFiles().remove(id);
					}
				}
				
			}
		}
		else if (table.getEditingColumn()==0) {
			for (int i=0; i<model.getRowCount(); i++) {
				boolean choosed = (boolean) model.getValueAt(i, 0);
				if (choosed) {
					Server.getSelectedFiles().add((Integer) model.getValueAt(i, 1));
				}
				else {
					Server.getSelectedFiles().remove(model.getValueAt(i, 1));
				}
			}
		}
	
		Controller.updateButtons();
		
	}


	
	

}
