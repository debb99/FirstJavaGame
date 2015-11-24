package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{
		private Handler handler;
		
		public FastEnemy(int x, int y, ID id, Handler handler) {
			super(x, y, id);
			this.handler = handler;
			velX = r.nextInt(5) + 7;
			velY = r.nextInt(5) + 7;
		}
		
		@Override
		public Rectangle getBounds() {
			return new Rectangle((int)x, (int)y, 16, 16);
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
			
			handler.addObject(new Trail(x, y, 16, 16, ID.Trail, Color.YELLOW, 0.075F, handler));
		}

		@Override
		public void render(Graphics g) {
			g.setColor(Color.YELLOW);
			g.fillRect((int)x, (int)y, 16, 16);
		}

	}
