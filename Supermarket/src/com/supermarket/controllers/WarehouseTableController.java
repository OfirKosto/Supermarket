package com.supermarket.controllers;

import java.sql.ResultSet;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.Manager;
import com.supermarket.models.Storekeeper;
import com.supermarket.models.job;
import com.supermarket.views.ErrorWindow;
import com.supermarket.views.WarehouseManager;

import net.proteanit.sql.DbUtils;

public class WarehouseTableController {
	public static void setTable(WarehouseManager view, String i_ID){
		try
		{
		Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(i_ID, job.Storekeeper); 
		Storekeeper i_StoreKeeper = (Storekeeper)i_emp;
		ResultSet EmployeeTable = i_StoreKeeper.ReturnWarehouseItemTable();
		
		view.gettable().setModel(DbUtils.resultSetToTableModel(EmployeeTable));
		}
		catch (Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
}
