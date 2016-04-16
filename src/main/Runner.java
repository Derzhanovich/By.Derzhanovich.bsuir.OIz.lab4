package main;

import javax.swing.JFrame;

public class Runner {
	public static void main(String[] args) {
		JFrame frame = new Frame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1150, 800);
		frame.setTitle("Image Processing");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}