package com.cleanRobot;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Room");
		frame.setBounds(0, 0, 400, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
