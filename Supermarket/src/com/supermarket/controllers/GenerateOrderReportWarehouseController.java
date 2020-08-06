package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.StoreWorker;
import com.supermarket.models.Storekeeper;
import com.supermarket.models.job;
import com.supermarket.views.ErrorWindow;
import com.supermarket.views.GenerateOrderReportStore;
import com.supermarket.views.GenerateOrderReportWarehouse;

public class GenerateOrderReportWarehouseController implements ActionListener{

	private GenerateOrderReportWarehouse m_GenerateOrderReportWarehouse;
	
	public GenerateOrderReportWarehouseController(GenerateOrderReportWarehouse i_GenerateOrderReportWarehouse) {
		m_GenerateOrderReportWarehouse = i_GenerateOrderReportWarehouse;
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Generate Order Report")) {
				Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_GenerateOrderReportWarehouse.GetStoreWorkerID(), job.Storekeeper); 
				Storekeeper i_Storekeeper = (Storekeeper)i_emp;
				i_Storekeeper.OrderReportWarehouse();
				i_Storekeeper.ClearWarehouseOrder();
				m_GenerateOrderReportWarehouse.finished();
			}
			else if(e.getActionCommand().equals("Cancel")){
				m_GenerateOrderReportWarehouse.finished();
			}
		}
		catch(Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
			}
		}
		
		

}
