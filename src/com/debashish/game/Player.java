package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	private Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	@Override
	public void tick(){
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 32);
		
		collision();
		
		handler.addObject(new Trail(x, y, 32, 32, ID.Trail, Color.WHITE, 0.05F, handler));
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.id == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.PLAYER_HEALTH -= 2;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);
	}
	
	public int xSpeed = 0;
	public int ySpeed = 0;
}
