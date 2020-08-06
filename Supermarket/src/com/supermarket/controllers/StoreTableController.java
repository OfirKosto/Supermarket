package com.supermarket.controllers;

import java.sql.ResultSet;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.StoreWorker;
import com.supermarket.models.job;
import com.supermarket.views.EmployeeManager;
import com.supermarket.views.ErrorWindow;
import com.supermarket.views.StoreManager;

import net.proteanit.sql.DbUtils;

public class StoreTableController {
	public static void setTable(StoreManager view, String i_ID){
		try
		{
		Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(i_ID, job.StoreWorker); 
		StoreWorker i_StoreWorker = (StoreWorker)i_emp;
		ResultSet ItemTable = i_StoreWorker.ReturnStoreItemTable();
		
		view.gettable().setModel(DbUtils.resultSetToTableModel(ItemTable));
		}
		catch (Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
}
