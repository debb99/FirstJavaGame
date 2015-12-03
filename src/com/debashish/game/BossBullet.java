package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossBullet extends GameObject {
	
	private Handler handler;
	
	public BossBullet(float f, float g, float wid, float hei, ID id, Handler handler) {
		super(f, g, wid, hei, id);
		this.handler = handler;
		velX = r.nextInt(5 - -5) - 5;
		velY = 5;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}	

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y >= Game.HEIGHT){
			handler.removeObject(this);
		}
		//handler.addObject(new Trail(x, y, width, height, ID.Trail, Color.ORANGE, 0.09F, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

}
