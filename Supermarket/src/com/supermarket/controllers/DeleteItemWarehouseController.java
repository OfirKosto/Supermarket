package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.Manager;
import com.supermarket.models.Storekeeper;
import com.supermarket.models.job;
import com.supermarket.views.DeleteItemWarehouse;
import com.supermarket.views.DeleteMessage;
import com.supermarket.views.ErrorWindow;

public class DeleteItemWarehouseController implements ActionListener{
	private DeleteItemWarehouse m_DelItem;
	public DeleteItemWarehouseController(DeleteItemWarehouse i_DelItem) {
		m_DelItem=i_DelItem;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Yes")) {
				
				Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_DelItem.GetManagerID(), job.Storekeeper); 
				Storekeeper i_Storekeeoer = (Storekeeper)i_emp;
				i_Storekeeoer.RemoveItem(m_DelItem.GetSelectedBarcode());
				m_DelItem.finished();
			}
			else if(e.getActionCommand().equals("No")){
				m_DelItem.finished();
			}
		}
		catch(Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
}
