package com.supermarket.controllers;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.supermarket.views.*;

public class StoreManagerController implements ActionListener {
	private StoreManager m_StoreManager;
	
	public StoreManagerController(StoreManager i_StoreManager) {
		m_StoreManager = i_StoreManager;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getActionCommand().equals("Update Item")) {
			UpdateItemStore i_UpdateItem = new UpdateItemStore(m_StoreManager);
		}
		else if(e.getActionCommand().equals("Order Item")) {
			OrderItemStore i_OrderItemStore = new OrderItemStore(m_StoreManager);
		}
		else if(e.getActionCommand().equals("Generate Order Report")) {
			GenerateOrderReportStore i_Report = new GenerateOrderReportStore(m_StoreManager);
		}
		}
		catch (Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}

}
