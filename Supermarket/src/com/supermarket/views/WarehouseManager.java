package com.supermarket.views;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JTable;

import com.supermarket.controllers.EmployeeTabelController;
import com.supermarket.controllers.WarehouseManagerController;
import com.supermarket.controllers.WarehouseTableController;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WarehouseManager {

	private JFrame frmWarehouseManager;
	private JTable table;
	private String m_ID;
	private String m_Barcode;
	private String m_Name;
	private int m_MaxStockWarehouse;
	private int m_AvailableInWarehouse;
	
	public WarehouseManager(String i_ID) {
		m_ID=i_ID;
		initialize();
	}

	
	private void initialize() {
		WarehouseManagerController i_Controller = new WarehouseManagerController(this);
		frmWarehouseManager = new JFrame();
		frmWarehouseManager.setTitle("Warehouse Manager");
		frmWarehouseManager.setBounds(100, 100, 917, 565);
		frmWarehouseManager.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmWarehouseManager.setResizable(false);
		frmWarehouseManager.getContentPane().setLayout(null);
		frmWarehouseManager.setVisible(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 46, 628, 458);
		frmWarehouseManager.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				m_Barcode=table.getModel().getValueAt(row, 0).toString();
				m_Name=table.getModel().getValueAt(row, 1).toString();
				m_AvailableInWarehouse = Integer.valueOf(table.getModel().getValueAt(row, 2).toString());
				m_MaxStockWarehouse=Integer.valueOf(table.getModel().getValueAt(row,3).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("Add New Item");
		btnAdd.addActionListener(i_Controller);
		btnAdd.setBounds(679, 60, 166, 23);
		frmWarehouseManager.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update Item");
		btnUpdate.addActionListener(i_Controller);
		btnUpdate.setBounds(679, 94, 166, 23);
		frmWarehouseManager.getContentPane().add(btnUpdate);
		
		JButton btnOrder = new JButton("Order Item");
		btnOrder.addActionListener(i_Controller);
		btnOrder.setBounds(679, 162, 166, 23);
		frmWarehouseManager.getContentPane().add(btnOrder);
		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.addActionListener(i_Controller);
		btnDeleteItem.setBounds(679, 128, 166, 23);
		frmWarehouseManager.getContentPane().add(btnDeleteItem);
		
		JButton btnGenerateOrderWarehouse = new JButton("Generate Order Report");
		btnGenerateOrderWarehouse.addActionListener(i_Controller);
	
		btnGenerateOrderWarehouse.setBounds(679, 307, 166, 23);
		frmWarehouseManager.getContentPane().add(btnGenerateOrderWarehouse);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmWarehouseManager.dispose();
				MainWindow i_Main = new MainWindow();
			}
		});
		btnCancel.setBounds(679, 463, 166, 23);
		frmWarehouseManager.getContentPane().add(btnCancel);
		WarehouseTableController.setTable(this,m_ID);
	}
		
	public String getID() {
		return m_ID;
	}


	public String getBarcode() {
		return m_Barcode;
	}


	public String getName() {
		return m_Name;
	}


	public int getMaxStockWarehouse() {
		return m_MaxStockWarehouse;
	}


	public int getAvailableInWarehouse() {
		return m_AvailableInWarehouse;
	}


	public JTable gettable(){
		return this.table;
	}
}
