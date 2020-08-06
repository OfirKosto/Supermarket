package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.supermarket.controllers.CalculateWageController;
import com.supermarket.controllers.DeleteEmployeeController;
import com.supermarket.controllers.EmployeeTabelController;

import javax.swing.JButton;
import java.awt.Window.Type;

public class CalculateWage {

	private JFrame frmCalculateWages;
	private EmployeeManager m_Manager;

	public CalculateWage(EmployeeManager i_Manager) {
		m_Manager= i_Manager;
		initialize();
	} 

	
	private void initialize() {
		frmCalculateWages = new JFrame();
		frmCalculateWages.setTitle("Calculate Wages");
		frmCalculateWages.setType(Type.UTILITY);
		frmCalculateWages.setBounds(100, 100, 329, 178);
		frmCalculateWages.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmCalculateWages.getContentPane().setLayout(null);
		CalculateWageController i_CalculateController = new CalculateWageController(this); 
		JLabel lblCalculateMsg = new JLabel("Are you sure?");
		lblCalculateMsg.setBounds(122, 42, 164, 14);
		frmCalculateWages.getContentPane().add(lblCalculateMsg);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(i_CalculateController);
		btnCalculate.setBounds(56, 81, 89, 23);
		frmCalculateWages.getContentPane().add(btnCalculate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(i_CalculateController);
		btnCancel.setBounds(173, 81, 89, 23);
		frmCalculateWages.getContentPane().add(btnCancel);
		this.frmCalculateWages.setVisible(true);
	}
	public String GetManagerID() {
		return m_Manager.getID();
	}
	public String GetSelectedId() {
		return m_Manager.getSelectedID();
	}
	public void finished() {
		frmCalculateWages.dispose();
		EmployeeTabelController.setTable(m_Manager, m_Manager.getID());
		
	}
}
