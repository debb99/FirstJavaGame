package com.debashish.game;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r;
	
	private int scoreKeep = 0;
	private Game game;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		r = new Random();
	}
	
	public void tick(){
		scoreKeep++;
		if(scoreKeep > 250){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			if(hud.getLevel() == 2){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.BasicEnemy, handler));
			}
			if(hud.getLevel() == 3){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.FastEnemy, handler));
			}
			if(hud.getLevel() == 4){
				handler.addObject(new HomingEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.HomingEnemy, handler));
			}
			if(hud.getLevel() == 5){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.BasicEnemy, handler));
			}
			if(hud.getLevel() == 8){
				handler.clearEnemies();
				handler.addObject(new BossEnemy(Game.WIDTH / 2 - 48, -200, 70, 96, ID.BossEnemy, handler));
			}
			if(hud.getLevel() == 11){
				handler.clearEnemies();
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 16, 16, ID.FastEnemy, handler));
			}
		}
		
		
	}
}
