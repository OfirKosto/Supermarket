package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.supermarket.controllers.StoreTableController;
import com.supermarket.controllers.UpdateItemStoreConroller;

import javax.swing.JButton;
import java.awt.Window.Type;

public class UpdateItemStore {

	private JFrame frmUpdateItem;
	private StoreManager m_StoreManager;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textMaxStockStore;
	private JTextField textAvailableInStore;
	public UpdateItemStore(StoreManager i_StoreManager) {
		m_StoreManager = i_StoreManager;
		initialize();
	}

	
	private void initialize() {
		frmUpdateItem = new JFrame();
		frmUpdateItem.setType(Type.UTILITY);
		frmUpdateItem.setResizable(false);
		frmUpdateItem.setTitle("Update Item");
		frmUpdateItem.setBounds(100, 100, 348, 436);
		frmUpdateItem.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmUpdateItem.getContentPane().setLayout(null);
		UpdateItemStoreConroller i_UpdateController= new UpdateItemStoreConroller(this);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setBounds(37, 58, 94, 14);
		frmUpdateItem.getContentPane().add(lblBarcode);
		
		JLabel lbltemBarcode = new JLabel(m_StoreManager.getSelectedBarcode());
		lbltemBarcode.setBounds(171, 58, 86, 14);
		frmUpdateItem.getContentPane().add(lbltemBarcode);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(37, 101, 46, 14);
		frmUpdateItem.getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.setText(m_StoreManager.getSelectedName());
		textName.setBounds(171, 98, 86, 20);
		frmUpdateItem.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(37, 138, 77, 20);
		frmUpdateItem.getContentPane().add(lblPrice);
		
		textPrice = new JTextField();
		textPrice.setText(Float.toString(m_StoreManager.getSelectedPrice()));
		textPrice.setBounds(171, 138, 86, 20);
		frmUpdateItem.getContentPane().add(textPrice);
		textPrice.setColumns(10);
		
		JLabel lblMaxStockStore = new JLabel("Max Stock Store:");
		lblMaxStockStore.setBounds(37, 204, 113, 20);
		frmUpdateItem.getContentPane().add(lblMaxStockStore);
		
		textMaxStockStore = new JTextField();
		textMaxStockStore.setText(Integer.toString(m_StoreManager.getSelectedMaxStockStore()));
		textMaxStockStore.setBounds(171, 204, 86, 20);
		frmUpdateItem.getContentPane().add(textMaxStockStore);
		textMaxStockStore.setColumns(10);
		
		JLabel lblAvailableInStore = new JLabel("Available In Store:");
		lblAvailableInStore.setBounds(37, 179, 122, 14);
		frmUpdateItem.getContentPane().add(lblAvailableInStore);
		
		textAvailableInStore = new JTextField();
		textAvailableInStore.setText(Integer.toString(m_StoreManager.getSelectedAvailableInStore()));
		textAvailableInStore.setBounds(171, 176, 86, 20);
		frmUpdateItem.getContentPane().add(textAvailableInStore);
		textAvailableInStore.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(25, 309, 89, 23);
		frmUpdateItem.getContentPane().add(btnUpdate);
		btnUpdate.addActionListener(i_UpdateController);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(171, 309, 89, 23);
		frmUpdateItem.getContentPane().add(btnCancel);
		
		
		btnCancel.addActionListener(i_UpdateController);
		frmUpdateItem.setVisible(true);
	}
	
	public String getTextName() {
		return textName.getText();
	}

	public float getTextPrice() {
		return Float.parseFloat(textPrice.getText());
	}

	public int getTextMaxStockStore() {
		return Integer.parseInt(textMaxStockStore.getText());
	}
	
	public int getTextAvailableInStore() {
		return Integer.parseInt(textAvailableInStore.getText());
	}

	public StoreManager getStoreWorker() {
		return m_StoreManager;
	}
	
	public String GetStoreWorkerID() {
		return m_StoreManager.getID();
	}
	

	public void finished() {
		frmUpdateItem.dispose();
		StoreTableController.setTable(m_StoreManager, m_StoreManager.getID());
	}
}
