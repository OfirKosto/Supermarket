package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.supermarket.views.AddItem;
import com.supermarket.views.DeleteItemWarehouse;
import com.supermarket.views.ErrorWindow;
import com.supermarket.views.GenerateOrderReportWarehouse;
import com.supermarket.views.OrderItemWarehouse;
import com.supermarket.views.UpdateItemWarehouse;
import com.supermarket.views.WarehouseManager;

public class WarehouseManagerController implements ActionListener{
	private WarehouseManager m_WarehouseManager;
	
	public WarehouseManagerController(WarehouseManager i_WarehouseManager) {
		m_WarehouseManager = i_WarehouseManager;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getActionCommand().equals("Add New Item")) {
			AddItem i_addItem = new AddItem(m_WarehouseManager);
		}
		else if(e.getActionCommand().equals("Update Item")) {
			UpdateItemWarehouse i_updateItemWarehouse = new UpdateItemWarehouse(m_WarehouseManager);
		}
		else if(e.getActionCommand().equals("Order Item")) {
			OrderItemWarehouse i_orderItemsWarehouse = new OrderItemWarehouse (m_WarehouseManager);
		}
		else if(e.getActionCommand().equals("Delete Item")) {
			DeleteItemWarehouse i_deleteItem = new DeleteItemWarehouse(m_WarehouseManager);
		}
		else if (e.getActionCommand().equals("Refresh Table")) {
			WarehouseTableController.setTable(m_WarehouseManager, m_WarehouseManager.getID());
		}
		else if(e.getActionCommand().equals("Generate Order Report")) {
			GenerateOrderReportWarehouse i_orderReport = new GenerateOrderReportWarehouse(m_WarehouseManager);
		}
		}
		catch (Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
}
