package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.supermarket.controllers.EmployeeTabelController;
import com.supermarket.controllers.OrderItemWarehouseController;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class OrderItemWarehouse {

	private JFrame frmOrderItem;
	private WarehouseManager m_Manager;
	private JComboBox comboBox;
	private JLabel lblBarcodeValue;
	private JLabel lblNameValue;
	private int m_AvailableInWarehouse;
	private int m_MaxStockWarehouse;
	public OrderItemWarehouse(WarehouseManager i_Manager) {
		m_Manager = i_Manager;
		initialize();
	}


	private void initialize() {
		frmOrderItem = new JFrame();
		frmOrderItem.setType(Type.UTILITY);
		frmOrderItem.setTitle("Order Item");
		frmOrderItem.setBounds(100, 100, 313, 360);
		frmOrderItem.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmOrderItem.getContentPane().setLayout(null);
		frmOrderItem.setVisible(true);
		
		OrderItemWarehouseController i_Controller = new OrderItemWarehouseController(this);
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(i_Controller);
		btnOrder.setBounds(39, 213, 89, 23);
		frmOrderItem.getContentPane().add(btnOrder);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(i_Controller);
		btnCancel.setBounds(164, 213, 89, 23);
		frmOrderItem.getContentPane().add(btnCancel);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setBounds(50, 65, 63, 14);
		frmOrderItem.getContentPane().add(lblBarcode);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(50, 110, 46, 14);
		frmOrderItem.getContentPane().add(lblName);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(50, 150, 63, 14);
		frmOrderItem.getContentPane().add(lblQuantity);
		
		lblBarcodeValue = new JLabel(m_Manager.getBarcode());
		lblBarcodeValue.setBounds(123, 65, 99, 14);
		frmOrderItem.getContentPane().add(lblBarcodeValue);
		
		lblNameValue = new JLabel(m_Manager.getName());
		lblNameValue.setBounds(124, 110, 98, 14);
		frmOrderItem.getContentPane().add(lblNameValue);
		m_AvailableInWarehouse= m_Manager.getAvailableInWarehouse();
		m_MaxStockWarehouse=m_Manager.getMaxStockWarehouse();
		comboBox = new JComboBox();
		comboBox.setBounds(122, 143, 89, 29);
		frmOrderItem.getContentPane().add(comboBox);
		
		try {
		
		for(int i = 0 ;i <= (m_Manager.getMaxStockWarehouse() - (i_Controller.ReturnMaxOrder(m_Manager.getBarcode()) + m_Manager.getAvailableInWarehouse())) ; ++i) {
			
			comboBox.addItem(i);
		}
		}
		catch (Exception ex){
			ErrorWindow i_Error = new ErrorWindow(ex.getMessage());
			i_Error.getFrame().setVisible(true);
		}
	}

	public int getAvailableInWarehouse() {
		return m_AvailableInWarehouse;
	}

	public int getMaxStockWarehouse() {
		return m_MaxStockWarehouse;
	}

	public WarehouseManager getManager() {
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
	
}
