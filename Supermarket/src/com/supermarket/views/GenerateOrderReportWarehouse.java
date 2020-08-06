package com.supermarket.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.supermarket.controllers.GenerateOrderReportWarehouseController;
import com.supermarket.controllers.WarehouseTableController;
import java.awt.Window.Type;

public class GenerateOrderReportWarehouse {
	private JFrame frmGenerateOrderReport;
	private WarehouseManager m_Manager;
	
	public GenerateOrderReportWarehouse(WarehouseManager i_Manager) {
		m_Manager = i_Manager;
		initialize();
	}
	
	private void initialize() {
		frmGenerateOrderReport = new JFrame();
		frmGenerateOrderReport.setType(Type.UTILITY);
		frmGenerateOrderReport.setTitle("Generate Order Report Warehouse");
		frmGenerateOrderReport.setBounds(100, 100, 422, 251);
		frmGenerateOrderReport.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGenerateOrderReport.getContentPane().setLayout(null);
		frmGenerateOrderReport.setVisible(true);
		
		GenerateOrderReportWarehouseController i_OrderController = new GenerateOrderReportWarehouseController(this);
		
		JLabel lblNewLabel = new JLabel("Do you want to create an order report?");
		lblNewLabel.setBounds(93, 39, 236, 40);
		frmGenerateOrderReport.getContentPane().add(lblNewLabel);
		
		JButton btnCalculate = new JButton("Generate Order Report");
		btnCalculate.addActionListener(i_OrderController);
		btnCalculate.setBounds(10, 102, 185, 40);
		frmGenerateOrderReport.getContentPane().add(btnCalculate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(i_OrderController);
		btnCancel.setBounds(205, 102, 192, 40);
		frmGenerateOrderReport.getContentPane().add(btnCancel);
	}
	
	
	public String GetStoreWorkerID() {
		return m_Manager.getID();
	}
	
	public void finished() {
		frmGenerateOrderReport.dispose();
		WarehouseTableController.setTable(m_Manager, m_Manager.getID());
	}
}


