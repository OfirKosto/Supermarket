package com.supermarket.controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import com.supermarket.models.*;
import com.supermarket.views.*;

public class AddEmployeeController implements ActionListener {
	private AddEmployee m_addEmployee;
	
	public AddEmployeeController(AddEmployee i_addEmployee) {
		m_addEmployee = i_addEmployee;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
		if(e.getActionCommand().equals("Add")) {
			
			Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_addEmployee.getID(), job.Manager); 
			Manager i_Manager = (Manager)i_emp;
			i_Manager.AddEmployee(EmployeeFactory.creatEmployee(m_addEmployee.getTextNameField().getText(), 
					                      m_addEmployee.getTextIDField().getText(),Float.parseFloat(m_addEmployee.getTextHourlyWageField().getText()), 0.0f,job.valueOf(m_addEmployee.getComboBoxJob().getSelectedItem().toString())));
			
			m_addEmployee.finished();
		}
		else if(e.getActionCommand().equals("Cancel")) {
			m_addEmployee.finished();
		}
		}
		catch (Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
}
