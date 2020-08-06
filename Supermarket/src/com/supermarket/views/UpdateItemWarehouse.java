package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.supermarket.controllers.UpdateItemWarehouseController;
import com.supermarket.controllers.WarehouseTableController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class UpdateItemWarehouse {

	private JFrame frmUpdateItem;
	private WarehouseManager m_Manager;
	private JTextField textName;
	private JTextField textAvailableInWarehouse;
	private JTextField textMaxStockWarehouse;
	private JLabel lblBarcodeNum;
	public UpdateItemWarehouse(WarehouseManager i_Manager) {
		m_Manager=i_Manager;
		initialize();
	}


	private void initialize() {
		UpdateItemWarehouseController i_Controller = new UpdateItemWarehouseController(this);
		frmUpdateItem = new JFrame();
		frmUpdateItem.setType(Type.UTILITY);
		frmUpdateItem.setTitle("Update Item");
		frmUpdateItem.setBounds(100, 100, 350, 359);
		frmUpdateItem.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmUpdateItem.getContentPane().setLayout(null);
		frmUpdateItem.setVisible(true);
		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setBounds(29, 51, 102, 14);
		frmUpdateItem.getContentPane().add(lblBarcode);
		
		lblBarcodeNum = new JLabel(m_Manager.getBarcode());
		lblBarcodeNum.setBounds(193, 51, 120, 14);
		frmUpdateItem.getContentPane().add(lblBarcodeNum);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(29, 90, 46, 14);
		frmUpdateItem.getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.setText(m_Manager.getName());
		textName.setBounds(193, 87, 86, 20);
		frmUpdateItem.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblAvailableInWarehouse = new JLabel("Available In Warehouse:");
		lblAvailableInWarehouse.setBounds(29, 131, 159, 14);
		frmUpdateItem.getContentPane().add(lblAvailableInWarehouse);
		
		textAvailableInWarehouse = new JTextField();
		textAvailableInWarehouse.setText(String.valueOf(m_Manager.getAvailableInWarehouse()));
		textAvailableInWarehouse.setBounds(193, 128, 86, 20);
		frmUpdateItem.getContentPane().add(textAvailableInWarehouse);
		textAvailableInWarehouse.setColumns(10);
		
		JLabel lblMaxStockWarehouse = new JLabel("Max Stock Warehouse:");
		lblMaxStockWarehouse.setBounds(29, 170, 159, 14);
		frmUpdateItem.getContentPane().add(lblMaxStockWarehouse);
		
		textMaxStockWarehouse = new JTextField();
		textMaxStockWarehouse.setText(String.valueOf(m_Manager.getMaxStockWarehouse()));
		textMaxStockWarehouse.setBounds(193, 167, 86, 20);
		frmUpdateItem.getContentPane().add(textMaxStockWarehouse);
		textMaxStockWarehouse.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(i_Controller);
		btnUpdate.setBounds(42, 248, 89, 23);
		frmUpdateItem.getContentPane().add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(i_Controller);
		btnCancel.setBounds(190, 248, 89, 23);
		frmUpdateItem.getContentPane().add(btnCancel);
	}
	
	public void finished() {
		frmUpdateItem.dispose();
		WarehouseTableController.setTable(m_Manager, m_Manager.getID());
		
	}

	public String getBarcode() {
		return lblBarcodeNum.getText();
	}


	public WarehouseManager getManager() {
		return m_Manager;
	}


	public String getTextName() {
		return textName.getText();
	}


	public int getTextAvailableInWarehouse() {
		return Integer.valueOf(textAvailableInWarehouse.getText());
	}


	public int getTextMaxStockWarehouse() {
		return Integer.valueOf(textMaxStockWarehouse.getText());
	}
}
