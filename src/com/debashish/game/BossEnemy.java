package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossEnemy extends GameObject {
	
	private Handler handler;
	private int timer = 120;
	private int timer2= 120;
	
	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY = 2;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}	

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y > 25){
			velY = 0;
		}
		
		if(timer <= 0){
			if(velX == 0) velX = 3;
			if(timer2 <= 0){
				if(r.nextInt(7) == 0){
					handler.addObject(new BossBullet(x + 48, y + 90, ID.BasicEnemy, handler));
				}
			} else timer2--;
		} else timer--;
		
		if(x <= 20 || x >= Game.WIDTH - 116){
			velX *= -1;
		}
		
		
		//handler.addObject(new Trail(x, y, 96, 96, ID.Trail, Color.RED, 0.035F, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 96, 96);
	}

}
