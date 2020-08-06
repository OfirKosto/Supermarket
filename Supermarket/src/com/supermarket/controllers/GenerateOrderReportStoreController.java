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
import com.supermarket.models.job;
import com.supermarket.views.*;

public class GenerateOrderReportStoreController implements ActionListener{
	private GenerateOrderReportStore m_GenerateOrderReportStore;
	
	public GenerateOrderReportStoreController(GenerateOrderReportStore i_GenerateOrderReportStore) {
		m_GenerateOrderReportStore = i_GenerateOrderReportStore;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try { 
			if(e.getActionCommand().equals("Create")) {
				Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_GenerateOrderReportStore.GetStoreWorkerID(), job.StoreWorker); 
				StoreWorker i_StoreWorker = (StoreWorker)i_emp;
				i_StoreWorker.OrderReportStore();
				i_StoreWorker.ClearStoreOrder();
				m_GenerateOrderReportStore.finished();
			}
			else if(e.getActionCommand().equals("Cancel")){
				m_GenerateOrderReportStore.finished();
			}
		}
		catch(Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
			}
		}
		
}
