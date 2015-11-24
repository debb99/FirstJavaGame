package com.debashish.game;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r;
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		r = new Random();
	}
	
	public void tick(){
		scoreKeep++;
		if(scoreKeep > 250){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			if(hud.getLevel() == 2){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			if(hud.getLevel() == 3){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			}
			if(hud.getLevel() == 4){
				handler.addObject(new HomingEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HomingEnemy, handler));
			}
			if(hud.getLevel() == 7){
				handler.clearEnemies();
				handler.addObject(new BossEnemy(Game.WIDTH / 2 - 48, -200, ID.BossEnemy, handler));
			}
		}
		
		
	}
}
