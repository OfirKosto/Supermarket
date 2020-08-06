package com.supermarket.views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import com.supermarket.controllers.*;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeManager {

	private JFrame frmEmployeeManager;
	private JTable table;
	private String m_ID;
	private String m_SelectedID;
	private String m_SelectedJob;
	private String m_SelectedName;
	private float m_SelectHourlyWage;
	private float m_SelectMonthlyHours;
	
	public EmployeeManager(String i_ID) {
		m_ID = i_ID;
		initialize();
	}

	
	private void initialize() {
		EmployeeManagerController i_employeeManagerController = new EmployeeManagerController(this);
		frmEmployeeManager = new JFrame();
		frmEmployeeManager.setTitle("Employee Manager");
		frmEmployeeManager.setBounds(100, 100, 887, 673);
		frmEmployeeManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployeeManager.setResizable(false);
		frmEmployeeManager.setVisible(true);
		JButton btnCalculateWage = new JButton("Calculate Wages");
		btnCalculateWage.setBounds(616, 191, 210, 35);
		btnCalculateWage.addActionListener(i_employeeManagerController);
		frmEmployeeManager.getContentPane().setLayout(null);
		frmEmployeeManager.getContentPane().add(btnCalculateWage);
		
		JButton btnUpdateEmployeeInfo = new JButton("Update Employee Information");
		btnUpdateEmployeeInfo.setBounds(616, 100, 210, 35);
		btnUpdateEmployeeInfo.addActionListener(i_employeeManagerController);
		frmEmployeeManager.getContentPane().add(btnUpdateEmployeeInfo);
		
		JButton btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.addActionListener(i_employeeManagerController);
		btnAddEmployee.setBounds(616, 54, 210, 35);
		frmEmployeeManager.getContentPane().add(btnAddEmployee);
		
		JButton btnDeleteEmployee = new JButton("Delete Employee");
		btnDeleteEmployee.addActionListener(i_employeeManagerController);
		btnDeleteEmployee.setBounds(616, 145, 210, 35);
		frmEmployeeManager.getContentPane().add(btnDeleteEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 41, 582, 515);
		frmEmployeeManager.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				 m_SelectedID = table.getModel().getValueAt(row, 0).toString();
				 m_SelectedJob =table.getModel().getValueAt(row, 1).toString();
				 m_SelectedName = table.getModel().getValueAt(row, 2).toString();
				 m_SelectHourlyWage = Float.parseFloat(table.getModel().getValueAt(row, 3).toString());
				 m_SelectMonthlyHours= Float.parseFloat(table.getModel().getValueAt(row, 4).toString());	
			}
		});
		scrollPane.setViewportView(table);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEmployeeManager.dispose();
				MainWindow i_Main = new MainWindow();
			}
		});
		btnCancel.setBounds(616, 359, 210, 35);
		frmEmployeeManager.getContentPane().add(btnCancel);
		
		EmployeeTabelController.setTable(this,m_ID);
	}
	 
	public JTable gettable(){
		return this.table;
	}
	public void settable(JTable i_table) {
		this.table = i_table;
	}

	public String getID() {
		return m_ID;
	}

	public String getSelectedID() {
		return m_SelectedID;
	}


	public String getSelectedJob() {
		return m_SelectedJob;
	}


	public String getSelectedName() {
		return m_SelectedName;
	}


	public float getSelectHourlyWage() {
		return m_SelectHourlyWage;
	}


	public float getSelectMonthlyHours() {
		return m_SelectMonthlyHours;
	}

}
