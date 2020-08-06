package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.supermarket.controllers.AddItemController;
import com.supermarket.controllers.EmployeeTabelController;
import com.supermarket.controllers.WarehouseTableController;

import javax.swing.JButton;
import java.awt.Window.Type;

public class AddItem {

	private JFrame frmAddItem;
	
	private JTextField textBarcode;
	private JTextField textName;
	private JTextField textAvailableInWarehouse;
	private JTextField textMaxStockWarehouse;
	private WarehouseManager m_Manager;
	public AddItem(WarehouseManager i_Manager) {
		m_Manager = i_Manager;
		initialize();
	} 

	private void initialize() {
		AddItemController i_Controller = new AddItemController(this);
		frmAddItem = new JFrame();
		frmAddItem.setType(Type.UTILITY);
		frmAddItem.setResizable(false);
		frmAddItem.setTitle("Add Item");
		frmAddItem.setBounds(100, 100, 330, 336);
		frmAddItem.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAddItem.getContentPane().setLayout(null);
		frmAddItem.setVisible(true);
		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setBounds(27, 44, 68, 14);
		frmAddItem.getContentPane().add(lblBarcode);
		
		textBarcode = new JTextField();
		textBarcode.setBounds(214, 41, 86, 20);
		frmAddItem.getContentPane().add(textBarcode);
		textBarcode.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(27, 78, 68, 14);
		frmAddItem.getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.setBounds(214, 72, 86, 20);
		frmAddItem.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblAvailableInWarehouse = new JLabel("Available In Warehouse:");
		lblAvailableInWarehouse.setBounds(27, 109, 177, 14);
		frmAddItem.getContentPane().add(lblAvailableInWarehouse);
		
		textAvailableInWarehouse = new JTextField();
		textAvailableInWarehouse.setBounds(214, 103, 86, 20);
		frmAddItem.getContentPane().add(textAvailableInWarehouse);
		textAvailableInWarehouse.setColumns(10);
		
		JLabel lblMaxStockWarehouse = new JLabel("Max Stock In Warehouse:");
		lblMaxStockWarehouse.setBounds(27, 134, 177, 14);
		frmAddItem.getContentPane().add(lblMaxStockWarehouse);
		
		textMaxStockWarehouse = new JTextField();
		textMaxStockWarehouse.setBounds(214, 134, 86, 20);
		frmAddItem.getContentPane().add(textMaxStockWarehouse);
		textMaxStockWarehouse.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(i_Controller);
		btnAdd.setBounds(46, 218, 89, 23);
		frmAddItem.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(i_Controller);
		btnCancel.setBounds(175, 218, 89, 23);
		frmAddItem.getContentPane().add(btnCancel);
	}
	public void finished() {
		frmAddItem.dispose();
		WarehouseTableController.setTable(m_Manager, m_Manager.getID());
		
	}
	public String getID() {
		return m_Manager.getID();
	}

	
	public String getBarcode() {
		return textBarcode.getText();
	}

	public String getName() {
		return textName.getText();
	}

	public int getAvailableInWarehouse() {
		return Integer.valueOf(textAvailableInWarehouse.getText());
	}

	public int getMaxStockWarehouse() {
		return Integer.valueOf(textMaxStockWarehouse.getText());
	}
	
}
