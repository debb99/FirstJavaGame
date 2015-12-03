package com.debashish.game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{
	
	private float alpha = 1F;
	private Handler handler;
	private Color color;
	
	private float life;
		//life = 0.001 -> 0.1
	
	public Trail(float x, float y, float width, float height, ID id, Color color, float life, Handler handler) {
		super(x, y, width, height, id);
		this.handler = handler;
		this.color = color;
		this.life = life;
	}

	@Override
	public void tick() {
		if(alpha > life){
			alpha -= (life - 0.001F);
		} else {
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setComposite(makeTransparent(alpha));
		g2.setColor(color);
		g2.fillRect((int)x, (int)y, (int)width, (int)height);
		g2.setComposite(makeTransparent(1));
		
	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	@Override
	public Rectangle getBounds() {
		return null; //no collision with trail
	}

}
