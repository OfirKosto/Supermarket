package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import com.supermarket.controllers.DeleteEmployeeController;
import com.supermarket.controllers.DeleteItemWarehouseController;
import com.supermarket.controllers.WarehouseTableController;
import java.awt.Window.Type;

public class DeleteItemWarehouse {

	private JFrame frmDeleteitem;
	private WarehouseManager m_Manager;
	
	public DeleteItemWarehouse(WarehouseManager i_Manager) {
		m_Manager=i_Manager;
		initialize();
	}

	
	private void initialize() { 
		
		frmDeleteitem = new JFrame();
		frmDeleteitem.setType(Type.UTILITY);
		frmDeleteitem.setTitle("Delete Item");
		frmDeleteitem.setBounds(100, 100, 329, 202);
		frmDeleteitem.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmDeleteitem.getContentPane().setLayout(null);
		frmDeleteitem.setVisible(true);
		DeleteItemWarehouseController i_Controller = new DeleteItemWarehouseController(this);
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(i_Controller);
		btnYes.setBounds(52, 93, 89, 23);
		frmDeleteitem.getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(i_Controller);
		btnNo.setBounds(178, 93, 89, 23);
		frmDeleteitem.getContentPane().add(btnNo);
		
		JLabel lblDeleteItem = new JLabel("Are you sure you want to delete item?");
		lblDeleteItem.setBounds(67, 45, 266, 23);
		frmDeleteitem.getContentPane().add(lblDeleteItem);
	}
	public String GetManagerID() {
		return m_Manager.getID();
	}
	public String GetSelectedBarcode() {
		return m_Manager.getBarcode();
	}
	public void finished() {
		frmDeleteitem.dispose();
		WarehouseTableController.setTable(m_Manager, m_Manager.getID());
		
	}

}
