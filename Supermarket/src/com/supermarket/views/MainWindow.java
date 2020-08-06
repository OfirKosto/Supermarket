package com.supermarket.views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.JLabel;

import com.supermarket.models.*;
public class MainWindow {

	private JFrame frmMainMenu;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	
	public MainWindow() {
		initialize();
	}

	
	private void initialize() {
		frmMainMenu = new JFrame();
		frmMainMenu.setResizable(false);
		frmMainMenu.setTitle("Main Menu");
		frmMainMenu.setBounds(100, 100, 345, 382);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.setVisible(true);
		JButton btnManager = new JButton("Manager");
		btnManager.setBounds(67, 90, 194, 59);
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin i_Login = new UserLogin("Manager");
				i_Login.setVisible(true);
				frmMainMenu.dispose();
			}
		});
		frmMainMenu.getContentPane().setLayout(null);
		frmMainMenu.getContentPane().add(btnManager);
		
		JButton btnStorekeeper = new JButton("Storekeeper");
		btnStorekeeper.setBounds(67, 160, 194, 64);
		btnStorekeeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin i_Login = new UserLogin("Storekeeper");
				i_Login.setVisible(true);
				frmMainMenu.dispose();
			}
		});
		frmMainMenu.getContentPane().add(btnStorekeeper);
		
		JButton btnStoreWoker = new JButton("Store Worker");
		btnStoreWoker.setBounds(67, 235, 194, 64);
		btnStoreWoker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin i_Login = new UserLogin("StoreWorker");
				i_Login.setVisible(true);
				frmMainMenu.dispose();
			}
		});
		frmMainMenu.getContentPane().add(btnStoreWoker);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(144, 43, 73, 22);
		frmMainMenu.getContentPane().add(lblWelcome);
	}
}
