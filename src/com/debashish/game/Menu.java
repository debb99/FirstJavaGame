package com.debashish.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{
	@Override
	public void mousePressed(MouseEvent e) {
		float mx = e.getX();
		float my = e.getY();
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public boolean isInside(float x, float y, float mx, float my, float width, float height){
		if(mx >= x && mx <= x + width){
			if(my >= y && my <= y + height){
				return true;
			}
		}
		return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.drawRect(0, 0, Game.WIDTH, Game.HEIGHT);
		Font myFont = new Font(Font.DIALOG, Font.BOLD, 50);
		//FontMetrics fm = getFontMetrics(myFont);
		g.setColor(Color.WHITE);
		g.drawString("MENU", 250, Game.HEIGHT / 2);;
	}
}
