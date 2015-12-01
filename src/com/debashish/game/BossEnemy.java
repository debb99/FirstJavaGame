package com.debashish.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class BossEnemy extends GameObject {
	
	private Handler handler;
	private int timer = 120;
	private int timer2= 120;
	private Game game;
	
	public BossEnemy(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		velX = 0;
		velY = 2;
		sprite = Toolkit.getDefaultToolkit().getImage("ActualBoss.png");
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
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D)g;	
		//g2.drawImage(sprite, (int)x, (int)y, (int)x + 96, (int)y + 96, 0, 0, 96, 96, game);
	}
	
	private Image sprite;

}
