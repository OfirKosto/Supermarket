package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.supermarket.controllers.GenerateOrderReportStoreController;
import com.supermarket.controllers.StoreTableController;
import com.supermarket.controllers.UpdateItemStoreConroller;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class GenerateOrderReportStore {

	private JFrame frmGenerateOrderReport;
	private StoreManager m_StoreManager;

	public GenerateOrderReportStore(StoreManager i_StoreManager) {
		m_StoreManager = i_StoreManager;
		initialize();
	}

	
	private void initialize() {
		frmGenerateOrderReport = new JFrame();
		frmGenerateOrderReport.setTitle("Generate Order Report Store");
		frmGenerateOrderReport.setType(Type.UTILITY);
		frmGenerateOrderReport.setBounds(100, 100, 316, 196);
		frmGenerateOrderReport.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGenerateOrderReport.getContentPane().setLayout(null);
		
		GenerateOrderReportStoreController i_GenerateOrderReportStoreController= new GenerateOrderReportStoreController(this);
		JLabel lblMessage = new JLabel("Do you want to create an order report?");
		lblMessage.setBounds(51, 42, 268, 36);
		frmGenerateOrderReport.getContentPane().add(lblMessage);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(i_GenerateOrderReportStoreController);
		btnCreate.setBounds(39, 89, 89, 23);
		frmGenerateOrderReport.getContentPane().add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(i_GenerateOrderReportStoreController);
		btnCancel.setBounds(181, 89, 89, 23);
		frmGenerateOrderReport.getContentPane().add(btnCancel);
		frmGenerateOrderReport.setVisible(true);
	}
	
	public String GetStoreWorkerID() {
		return m_StoreManager.getID();
	}
	
	public void finished() {
		frmGenerateOrderReport.dispose();
	}
}
