package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import com.supermarket.controllers.EmployeeManagerController;
import com.supermarket.controllers.StoreManagerController;
import com.supermarket.controllers.StoreTableController;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoreManager {

	private JFrame frmStoreManager;
	private JTable table;
	private String m_ID;
	private JButton btnUpdate;
	private String m_SelectedBarcode;
	private String m_SelectedName;
	private float m_SelectedPrice;
	private int m_SelectedMaxStockStore;
	private int m_SelectedAvailableInStore;
	private JButton btnOrder;
	private JButton btnOrderReport;
	private JButton btnCancel;
	public StoreManager(String i_ID) {
		m_ID = i_ID;
		initialize();
	}

	
	private void initialize() {
		StoreManagerController i_StoreManagerController = new StoreManagerController(this);
		frmStoreManager = new JFrame();
		frmStoreManager.setTitle("Store Manager");
		frmStoreManager.setBounds(100, 100, 858, 543);
		frmStoreManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStoreManager.setResizable(false);
		frmStoreManager.getContentPane().setLayout(null);
		frmStoreManager.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 36, 545, 408);
		frmStoreManager.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				m_SelectedBarcode=table.getModel().getValueAt(row, 0).toString();
				m_SelectedName=table.getModel().getValueAt(row, 1).toString();
				m_SelectedPrice= Float.valueOf(table.getModel().getValueAt(row, 2).toString());
				m_SelectedAvailableInStore = Integer.valueOf(table.getModel().getValueAt(row, 3).toString());
				m_SelectedMaxStockStore=Integer.valueOf(table.getModel().getValueAt(row, 4).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		btnUpdate = new JButton("Update Item");
		btnUpdate.addActionListener(i_StoreManagerController);
		
		btnUpdate.setBounds(632, 66, 165, 39);
		frmStoreManager.getContentPane().add(btnUpdate);
		
		btnOrder = new JButton("Order Item");
		btnOrder.addActionListener(i_StoreManagerController);		
		btnOrder.setBounds(632, 116, 165, 39);
		frmStoreManager.getContentPane().add(btnOrder);
		
		btnOrderReport = new JButton("Generate Order Report");
		btnOrderReport.addActionListener(i_StoreManagerController);
		btnOrderReport.setBounds(632, 226, 165, 39);
		frmStoreManager.getContentPane().add(btnOrderReport);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmStoreManager.dispose();
				MainWindow i_Main = new MainWindow();
			}
		});
		btnCancel.setBounds(632, 365, 165, 39);
		frmStoreManager.getContentPane().add(btnCancel);
		StoreTableController.setTable(this, m_ID);
	}
	
	public JTable gettable(){
		return this.table;
	}
	

	public String getID() {
		return m_ID;
	}


	public String getSelectedBarcode() {
		return m_SelectedBarcode;
	}


	public String getSelectedName() {
		return m_SelectedName;
	}


	public float getSelectedPrice() {
		return m_SelectedPrice;
	}


	public int getSelectedMaxStockStore() {
		return m_SelectedMaxStockStore;
	}


	public int getSelectedAvailableInStore() {
		return m_SelectedAvailableInStore;
	}
	
}
