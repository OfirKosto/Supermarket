package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.ItemFactory;
import com.supermarket.models.Storekeeper;
import com.supermarket.models.job;
import com.supermarket.views.ErrorWindow;
import com.supermarket.views.OrderItemWarehouse;
import com.supermarket.views.UpdateItemWarehouse;

public class OrderItemWarehouseController implements ActionListener {
private OrderItemWarehouse m_orderItem;
	
	public OrderItemWarehouseController(OrderItemWarehouse i_orderItem) {
		m_orderItem = i_orderItem;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getActionCommand().equals("Order")) {
			int i_AmountToOrder = m_orderItem.getAmountToOrder();
			
			Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_orderItem.getManager().getID(), job.Storekeeper); 
			Storekeeper i_Storekeeper = (Storekeeper)i_emp;
			i_Storekeeper.OrderItem(ItemFactory.CreateNewItemWarehouse(m_orderItem.getBarcodeValue(), m_orderItem.getNameValue(), m_orderItem.getAvailableInWarehouse(), m_orderItem.getMaxStockWarehouse()), i_AmountToOrder);
			m_orderItem.finished();
		}
		else if(e.getActionCommand().equals("Cancel")) {
			m_orderItem.finished();
		}
		}
		catch(Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	
	}
	public int ReturnMaxOrder(String i_Barcode) throws Exception{
		Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_orderItem.getManager().getID(), job.Storekeeper); 
		Storekeeper i_Storekeeper = (Storekeeper)i_emp;
		return i_Storekeeper.ReturnMaxOrder(i_Barcode);
	}
}
