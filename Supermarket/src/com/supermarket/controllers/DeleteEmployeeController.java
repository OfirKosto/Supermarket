package com.supermarket.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.DBSingleton;
import com.supermarket.models.Employee;
import com.supermarket.models.Manager;
import com.supermarket.models.job;
import com.supermarket.views.DeleteMessage;
import com.supermarket.views.ErrorWindow;

public class DeleteEmployeeController implements ActionListener  {
	private DeleteMessage m_delMsg;
	public DeleteEmployeeController(DeleteMessage i_delMsg) {
		m_delMsg=i_delMsg;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Yes")) {
				Employee i_emp = DBSingleton.getInstance().Database.SearchEmployee(m_delMsg.GetManagerID(), job.Manager);
				Manager i_Manager = (Manager)i_emp;
				i_Manager.RemoveEmployee(m_delMsg.GetSelectedId());
				
				m_delMsg.finished();
			}
			else if(e.getActionCommand().equals("No")){
				m_delMsg.finished();
			}
		}
		catch(Exception ex) {
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}
}
