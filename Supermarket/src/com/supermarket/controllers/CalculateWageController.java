package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.Manager;
import com.supermarket.models.job;
import com.supermarket.views.CalculateWage;
import com.supermarket.views.ErrorWindow;

public class CalculateWageController implements ActionListener {
	private CalculateWage m_CalcWage;
	public CalculateWageController(CalculateWage i_CalcWage) {
		m_CalcWage=i_CalcWage;
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Calculate")) {
				
				Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_CalcWage.GetManagerID(), job.Manager); 
				Manager i_Manager = (Manager)i_emp;
				i_Manager.CreateWageReport();
				m_CalcWage.finished();
			}
			else if(e.getActionCommand().equals("Cancel")){
				m_CalcWage.finished();
			}
		}
		catch(Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
	


}
