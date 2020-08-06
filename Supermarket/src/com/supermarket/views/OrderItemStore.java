package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.supermarket.controllers.OrderItemStoreController;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class OrderItemStore {

	private JFrame frmOrderItem;
	private StoreManager m_Manager;
	private JComboBox comboBox;
	private JLabel lblBarcodeValue;
	private JLabel lblNameValue;
	private int m_AvailableInStore;
	private int m_MaxStockStore;
	
	public OrderItemStore(StoreManager i_Manager) {
		m_Manager = i_Manager;
		initialize();
	}

	
	private void initialize() {
		frmOrderItem = new JFrame();
		frmOrderItem.setType(Type.UTILITY);
		frmOrderItem.setTitle("Order Item");
		frmOrderItem.setBounds(100, 100, 312, 371);
		frmOrderItem.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmOrderItem.getContentPane().setLayout(null);
		frmOrderItem.setVisible(true);
		
		OrderItemStoreController i_Controller = new OrderItemStoreController(this);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(i_Controller);
		btnOrder.setBounds(33, 255, 89, 23);
		frmOrderItem.getContentPane().add(btnOrder);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(i_Controller);
		btnCancel.setBounds(154, 255, 89, 23);
		frmOrderItem.getContentPane().add(btnCancel);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setBounds(47, 65, 65, 14);
		frmOrderItem.getContentPane().add(lblBarcode);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(47, 110, 73, 14);
		frmOrderItem.getContentPane().add(lblName);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(47, 150, 75, 14);
		frmOrderItem.getContentPane().add(lblQuantity);
		
		lblBarcodeValue = new JLabel(m_Manager.getSelectedBarcode());
		lblBarcodeValue.setBounds(138, 65, 105, 14);
		frmOrderItem.getContentPane().add(lblBarcodeValue);
		
		lblNameValue = new JLabel(m_Manager.getSelectedName());
		lblNameValue.setBounds(138, 110, 103, 14);
		frmOrderItem.getContentPane().add(lblNameValue);
		
		m_AvailableInStore= m_Manager.getSelectedAvailableInStore();
		m_MaxStockStore=m_Manager.getSelectedMaxStockStore();
		
		comboBox = new JComboBox();
		comboBox.setBounds(137, 143, 80, 29);
		frmOrderItem.getContentPane().add(comboBox);
		
		try {
		
		for(int i = 0 ;i <= (m_Manager.getSelectedMaxStockStore() - (i_Controller.ReturnMaxOrder(m_Manager.getSelectedBarcode()) + m_Manager.getSelectedAvailableInStore())) ; ++i) {
			
			comboBox.addItem(i);
		}
		}
		catch (Exception ex){
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}

	public int getAvailableInStore() {
		return m_AvailableInStore;
	}

	public int getMaxStockStore() {
		return m_MaxStockStore;
	}

	public StoreManager getManager() {
		return m_Manager;
	}

	public int getAmountToOrder() {
		return Integer.valueOf(comboBox.getSelectedItem().toString());
	}

	public String getBarcodeValue() {
		return lblBarcodeValue.getText();
	}

	public String getNameValue() {
		return lblNameValue.getText();
	}
	public void finished() {
		frmOrderItem.dispose();		
	}
	
	public String GetStoreWorkerID() {
		return m_Manager.getID();
	}
	
}

