package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	private Handler handler;
	private int collisionGrace = 120; //2 seconds
	public Player(float f, float g, ID id, Handler handler) {
		super(f, g, id);
		this.handler = handler;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	@Override
	public void tick(){
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 32);
		
		collision();
		handler.addObject(new Trail(x, y, 32, 32, ID.Trail, renderColor, 0.1F, handler));
	}
	
	private void collision(){
		if(collisionGrace <= 0){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.id == ID.BasicEnemy || tempObject.id == ID.FastEnemy || tempObject.id == ID.HomingEnemy){
					if(getBounds().intersects(tempObject.getBounds())){
						HUD.PLAYER_HEALTH -= 1;
					}
				}
				if(tempObject.getId() == ID.BossEnemy){
					if(getBounds().intersects(tempObject.getBounds())){
						HUD.PLAYER_HEALTH = 0;
					}
				}
			}
		} else collisionGrace--;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(renderColor);
		g.fillRect((int)x, (int)y, 32, 32);
	}
	
	private Color renderColor = Color.WHITE;
	
	public int xSpeed = 0;
	public int ySpeed = 0;
}
