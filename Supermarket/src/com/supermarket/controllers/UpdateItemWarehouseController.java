package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.EmployeeFactory;
import com.supermarket.models.ItemFactory;
import com.supermarket.models.Manager;
import com.supermarket.models.Storekeeper;
import com.supermarket.models.job;
import com.supermarket.views.ErrorWindow;
import com.supermarket.views.UpdateEmployee;
import com.supermarket.views.UpdateItemWarehouse;

public class UpdateItemWarehouseController implements ActionListener {
	private UpdateItemWarehouse m_updateItem;
	
	public UpdateItemWarehouseController(UpdateItemWarehouse i_updateItem) {
		m_updateItem = i_updateItem;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getActionCommand().equals("Update")) {
			
			Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_updateItem.getManager().getID(), job.Storekeeper); 
			Storekeeper i_Storekeeper = (Storekeeper)i_emp;
			i_Storekeeper.UpdateItem(ItemFactory.CreateNewItemWarehouse(m_updateItem.getBarcode(), m_updateItem.getTextName(), m_updateItem.getTextAvailableInWarehouse(), m_updateItem.getTextMaxStockWarehouse()));
			m_updateItem.finished();
		}
		else if(e.getActionCommand().equals("Cancel")) {
			m_updateItem.finished();
		}
		}
		catch(Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
}
