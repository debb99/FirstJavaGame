package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HomingEnemy extends GameObject{
		private Handler handler;
		private GameObject player;
		
		public HomingEnemy(int x, int y, ID id, Handler handler) {
			super(x, y, id);
			this.handler = handler;
			
			for(int i = 0; i < handler.object.size(); i++){
				if(handler.object.get(i).id == ID.Player){
					player = handler.object.get(i);
				}
			}
			
			
			
		}
		
		@Override
		public Rectangle getBounds() {
			return new Rectangle(x, y, 16, 16);
		}	

		@Override
		public void tick() {
			x += velX;
			y += velY;
			
			float diffX = getX() - player.getX() - 8;
			float diffY = getY() - player.getY() - 8;
			
			float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
			
			if(y <= 0 || y >= Game.HEIGHT - 16){
				velY *= -1;
			}
			if(x <= 0 || x >= Game.WIDTH - 16){
				velX *= -1;
			}
			
			handler.addObject(new Trail(x, y, 16, 16, ID.Trail, Color.MAGENTA, 0.15F, handler));
		}

		@Override
		public void render(Graphics g) {
			g.setColor(Color.MAGENTA);
			g.fillRect(x, y, 16, 16);
		}

	}
