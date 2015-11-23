package com.debashish.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	
	private static final long serialVersionUID = 9032132422685871447L;
	
	public Window(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		Dimension size = new Dimension(width, height);
		frame.getContentPane().setPreferredSize(size); //USING THE REAL VALUES WITH NO DECORATION
		frame.getContentPane().setMinimumSize(size);
		frame.getContentPane().setMaximumSize(size);
		frame.setResizable(false);
		frame.pack();
		frame.setAutoRequestFocus(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(game);
//		frame.setUndecorated(true);
		frame.setVisible(true);
		
		game.start();
	}
}
