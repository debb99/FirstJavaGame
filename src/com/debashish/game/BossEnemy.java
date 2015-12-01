package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class BossEnemy extends GameObject {
	
	private Handler handler;
	private int timer = 150;
	private int timer2= 120;
	private Game game;
	
	public BossEnemy(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		velX = 0;
		velY = 2;
		sprite = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ActualBossTransparent.png"));
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
				if(r.nextInt(5) == 0){ //BULLET SPAWNS
					handler.addObject(new BossBullet(x + 35, y + 85, ID.BasicEnemy, handler));
				}
			} else timer2--; //moving horizontally
		} else timer--; //moving vertically
		
		if(x <= 20 || x >= Game.WIDTH - 116){
			velX *= -1;
		}
		
		
		//handler.addObject(new Trail(x, y, 96, 96, ID.Trail, Color.RED, 0.035F, handler));
	}

	@Override
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.drawImage(sprite, (int)x, (int)y, 96, 96, null);
	}
	
	private Image sprite;

}
