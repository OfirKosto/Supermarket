package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.EmployeeFactory;
import com.supermarket.models.Item;
import com.supermarket.models.ItemFactory;
import com.supermarket.models.StoreWorker;
import com.supermarket.models.job;
import com.supermarket.views.ErrorWindow;
import com.supermarket.views.UpdateItemStore;



public class UpdateItemStoreConroller implements ActionListener {
	private UpdateItemStore m_UpdateItemStore;
	public UpdateItemStoreConroller(UpdateItemStore i_UpdateItemStore) {
		m_UpdateItemStore = i_UpdateItemStore;		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getActionCommand().equals("Update")) {
			Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_UpdateItemStore.GetStoreWorkerID(), job.StoreWorker); 
			StoreWorker i_StoreWorker = (StoreWorker)i_emp;
			Item i_Item = ItemFactory.CreateNewStoreItem(m_UpdateItemStore.getStoreWorker().getSelectedBarcode(),m_UpdateItemStore.getTextName(),m_UpdateItemStore.getTextPrice(),m_UpdateItemStore.getTextAvailableInStore(),m_UpdateItemStore.getTextMaxStockStore());
			i_StoreWorker.UpdateItem(i_Item);
			m_UpdateItemStore.finished();
		}
		else if(e.getActionCommand().equals("Cancel")) {
			m_UpdateItemStore.finished();
		}
		}
		catch(Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
}
