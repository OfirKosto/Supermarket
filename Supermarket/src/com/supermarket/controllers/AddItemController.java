package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.ItemFactory;
import com.supermarket.models.Storekeeper;
import com.supermarket.models.job;
import com.supermarket.views.AddItem;
import com.supermarket.views.ErrorWindow;

public class AddItemController  implements ActionListener {
	private AddItem m_addItem;
	public AddItemController(AddItem i_addItem) {
		m_addItem = i_addItem;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
		if(e.getActionCommand().equals("Add")) {
			
			Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_addItem.getID(), job.Storekeeper); 
			Storekeeper i_Storekeeper = (Storekeeper)i_emp;
			i_Storekeeper.AddItem(ItemFactory.CreateNewItemWarehouse(m_addItem.getBarcode(), m_addItem.getName(), m_addItem.getAvailableInWarehouse(), m_addItem.getMaxStockWarehouse()));
			m_addItem.finished();
		}
		else if(e.getActionCommand().equals("Cancel")) {
			m_addItem.finished();
		}
		}
		catch (Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
}
