package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.Item;
import com.supermarket.models.ItemFactory;
import com.supermarket.models.StoreWorker;
import com.supermarket.models.job;
import com.supermarket.views.ErrorWindow;
import com.supermarket.views.OrderItemStore;
import com.supermarket.views.UserLogin;



public class OrderItemStoreController implements ActionListener {
	private  OrderItemStore m_OrderItemStore;
	
	public OrderItemStoreController(OrderItemStore i_OrderItemStore) {
		m_OrderItemStore = i_OrderItemStore;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getActionCommand().equals("Order")) { 
			Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_OrderItemStore.GetStoreWorkerID(), job.StoreWorker); 
			StoreWorker i_StoreWorker = (StoreWorker)i_emp;
			Item i_Item = ItemFactory.CreateNewStoreItem(m_OrderItemStore.getBarcodeValue(),m_OrderItemStore.getNameValue(),m_OrderItemStore.getManager().getSelectedPrice(),m_OrderItemStore.getAvailableInStore(),m_OrderItemStore.getMaxStockStore());
			i_StoreWorker.OrderItem(i_Item, m_OrderItemStore.getAmountToOrder());
			m_OrderItemStore.finished();
		}
		else if(e.getActionCommand().equals("Cancel")) {
			m_OrderItemStore.finished();
		}
		}
		catch(Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
	public int ReturnMaxOrder(String i_Barcode) throws Exception{
		Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_OrderItemStore.GetStoreWorkerID(), job.StoreWorker); 
		StoreWorker i_StoreWorker = (StoreWorker)i_emp;
		return i_StoreWorker.ReturnMaxOrder(i_Barcode);
	}
}
