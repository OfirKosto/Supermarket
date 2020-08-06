package com.supermarket.views;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.supermarket.controllers.*;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField IDField;
	private String m_Job;
	
	
	public UserLogin(String i_Job) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Login Page");
		m_Job=i_Job;
		LoginController i_LoginController = new LoginController(this);
		setBounds(100, 100, 364, 223);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter ID:");
		lblNewLabel.setBounds(31, 55, 92, 26);
		contentPanel.add(lblNewLabel);
		
		IDField = new JTextField();
	
		IDField.setBounds(129, 52, 186, 32);
		contentPanel.add(IDField);
		IDField.setColumns(10);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(66, 132, 65, 23);
			contentPanel.add(okButton);
			okButton.addActionListener(i_LoginController);			
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(238, 132, 77, 23);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(i_LoginController);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public void SelectJobWindow(String i_ID, String i_Job) {
		dispose();
		if(i_Job == "Manager") {
			EmployeeManager i_Manager = new EmployeeManager(i_ID);
		}
		else if (i_Job == "Storekeeper") {
			WarehouseManager i_WarehouseManager = new WarehouseManager(i_ID);
		}
		else if(i_Job == "StoreWorker") {
			StoreManager i_StroeManager = new StoreManager(i_ID);
		}
	}
	public void CancleClicked() {
		MainWindow i_Main = new MainWindow();
		dispose();
	}
	public String getJob() {
		return m_Job;
	}


	public void setJob(String i_Job) {
		this.m_Job = i_Job;
	}


	public JTextField getIDField() {
		return IDField;
	}


	public void setIDField(JTextField iDField) {
		IDField = iDField;
	}
	
}
