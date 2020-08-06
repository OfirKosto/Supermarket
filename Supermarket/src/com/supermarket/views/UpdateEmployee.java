package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.supermarket.controllers.EmployeeTabelController;
import com.supermarket.controllers.UpdateEmployeeController;

import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;

public class UpdateEmployee {

	private JFrame frmU;
	private EmployeeManager m_Manager;
	private JTextField textName;
	private JTextField textHourlyWage;
	private JTextField textMonthlyHours;
	private String [] Jobs=  {"Manager" , "Storekeeper","StoreWorker"};
	private JComboBox comboBoxJob;
	public UpdateEmployee(EmployeeManager i_Manager) {
		m_Manager = i_Manager;
		initialize();
	}

	
	private void initialize() {
		frmU = new JFrame();
		frmU.setTitle("Update Employee");
		frmU.setResizable(false);
		frmU.setBounds(100, 100, 511, 378);
		frmU.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmU.getContentPane().setLayout(null);
		UpdateEmployeeController i_UpdateController= new UpdateEmployeeController(this);
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(42, 58, 46, 14);
		frmU.getContentPane().add(lblID);
		
		JLabel lblEmploeeID = new JLabel(m_Manager.getSelectedID());
		lblEmploeeID.setBounds(98, 58, 102, 14);
		frmU.getContentPane().add(lblEmploeeID);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(42, 101, 46, 14);
		frmU.getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.setText(m_Manager.getSelectedName());
		textName.setBounds(98, 98, 86, 20);
		frmU.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblJob = new JLabel("Job:");
		lblJob.setBounds(42, 141, 46, 14);
		frmU.getContentPane().add(lblJob);
		
		comboBoxJob = new JComboBox();
		comboBoxJob.addItem(Jobs[0]);
		comboBoxJob.addItem(Jobs[1]);
		comboBoxJob.addItem( Jobs[2]);
		comboBoxJob.setSelectedItem(m_Manager.getSelectedJob());
		comboBoxJob.setBounds(99, 138, 101, 20);
		frmU.getContentPane().add(comboBoxJob);
		
		JLabel lblHourlyWage = new JLabel("Hourly Wage:");
		lblHourlyWage.setBounds(286, 58, 77, 20);
		frmU.getContentPane().add(lblHourlyWage);
		
		textHourlyWage = new JTextField();
		textHourlyWage.setText(Float.toString(m_Manager.getSelectHourlyWage()));
		textHourlyWage.setBounds(389, 58, 86, 20);
		frmU.getContentPane().add(textHourlyWage);
		textHourlyWage.setColumns(10);
		
		JLabel lblMonthlyHours = new JLabel("Monthly Hours:");
		lblMonthlyHours.setBounds(286, 104, 92, 20);
		frmU.getContentPane().add(lblMonthlyHours);
		
		textMonthlyHours = new JTextField();
		textMonthlyHours.setText(Float.toString(m_Manager.getSelectMonthlyHours()));
		textMonthlyHours.setBounds(389, 104, 86, 20);
		frmU.getContentPane().add(textMonthlyHours);
		textMonthlyHours.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(98, 248, 89, 23);
		frmU.getContentPane().add(btnUpdate);
		btnUpdate.addActionListener(i_UpdateController);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(286, 248, 89, 23);
		frmU.getContentPane().add(btnCancel);
		btnCancel.addActionListener(i_UpdateController);
		frmU.setVisible(true);
	}
	
	public String getTextName() {
		return textName.getText();
	}

	public float getTextHourlyWage() {
		return Float.parseFloat(textHourlyWage.getText());
	}

	public float getTextMonthlyHours() {
		return Float.parseFloat(textMonthlyHours.getText());
	}

	public String getComboBoxJob() {
		return comboBoxJob.getSelectedItem().toString();
	}

	public EmployeeManager getManager() {
		return m_Manager;
	}
	public String GetManagerID() {
		return m_Manager.getID();
	}
	public void finished() {
		frmU.dispose();
		EmployeeTabelController.setTable(m_Manager, m_Manager.getID());
	}
	
}
