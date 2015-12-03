package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {
	
	private Handler handler;
	
	public BasicEnemy(int x, int y, float wid, float hei, ID id, Handler handler) {
		super(x, y, wid, hei, id);
		this.handler = handler;
		velX = (r.nextInt(2) + 5);
		velY = (r.nextInt(2) + 5);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}	

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 16){
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16){
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, width, height, ID.Trail, Color.RED, 0.05F, handler));
	}

	@Override
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

}
