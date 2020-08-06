package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.supermarket.controllers.DeleteEmployeeController;
import com.supermarket.controllers.EmployeeTabelController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class DeleteMessage {

	private JFrame frmDeleteEmployee;
	private EmployeeManager m_Manager;

	
	public DeleteMessage(EmployeeManager i_Manager) {
		m_Manager= i_Manager;
		initialize();
	}

	
	private void initialize() {
		frmDeleteEmployee = new JFrame();
		frmDeleteEmployee.setTitle("Delete Employee");
		frmDeleteEmployee.setType(Type.UTILITY);
		frmDeleteEmployee.setBounds(100, 100, 373, 232);
		frmDeleteEmployee.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmDeleteEmployee.getContentPane().setLayout(null);
		DeleteEmployeeController i_DeleteController = new DeleteEmployeeController(this); 
		JLabel lblDeleteMsg = new JLabel("Are you sure you want to delete employee?");
		lblDeleteMsg.setBounds(68, 43, 259, 39);
		frmDeleteEmployee.getContentPane().add(lblDeleteMsg);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(i_DeleteController);
		btnYes.setBounds(62, 103, 99, 23);
		frmDeleteEmployee.getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(i_DeleteController);
		btnNo.setBounds(187, 103, 118, 23);
		frmDeleteEmployee.getContentPane().add(btnNo);
		frmDeleteEmployee.setVisible(true);
	}
	public String GetManagerID() {
		return m_Manager.getID();
	}
	public String GetSelectedId() {
		return m_Manager.getSelectedID();
	}
	public void finished() {
		frmDeleteEmployee.dispose();
		EmployeeTabelController.setTable(m_Manager, m_Manager.getID());
		
	}
}
