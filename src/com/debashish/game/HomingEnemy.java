package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HomingEnemy extends GameObject{
		private Handler handler;
		private GameObject player;
		
		public HomingEnemy(float f, float g, ID id, Handler handler) {
			super(f, g, id);
			this.handler = handler;
			for(int i = 0; i < handler.object.size(); i++){
				if(handler.object.get(i).id == ID.Player){
					player = handler.object.get(i);
				}
			}
		}
		
		@Override
		public Rectangle getBounds() {
			return new Rectangle((int)x, (int)y, 16, 16);
		}	

		@Override
		public void tick() {
			x += velX;
			y += velY;
			
			float diffX = x - player.getX() - 16;
			float diffY = y - player.getY() - 16;
			
			//float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
			float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX())
					+ (y - player.getY()) * (y - player.getY()) );
			
			setVelX((-1 / distance) * diffX);
			setVelY((-1 / distance) * diffY);
			
			if(y <= 0 || y >= Game.HEIGHT - 16){
				velY *= -1;
			}
			if(x <= 0 || x >= Game.WIDTH - 16){
				velX *= -1;
			}
			
			handler.addObject(new Trail(x, y, 16, 16, ID.Trail, Color.PINK, 0.05F, handler));
		}

		@Override
		public void render(Graphics g) {
			g.setColor(Color.PINK);
			g.fillRect((int)x, (int)y, 16, 16);
		}

	}
