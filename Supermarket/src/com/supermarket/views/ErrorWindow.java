package com.supermarket.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class ErrorWindow {

	private JFrame frame;

	
	public ErrorWindow(String i_ErrorMsg) {
		initialize(i_ErrorMsg);
	}

	
	private void initialize(String i_ErrorMsg) {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 374, 218);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(i_ErrorMsg);
		lblNewLabel.setBounds(47, 25, 310, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(118, 98, 112, 43);
		frame.getContentPane().add(btnNewButton);
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	
}
