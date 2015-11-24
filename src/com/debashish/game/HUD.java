package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class HUD {
	
	public static float PLAYER_HEALTH = 100;
	private int greenMin = 66;
	private int yellowMin = 33;
	
	private int score = 0, level = 1, timer = 60;
	
	public void tick(){
		PLAYER_HEALTH = Game.clamp(PLAYER_HEALTH, 0, 100);
		score++;
		if(PLAYER_HEALTH <= 0){
			if(timer <= 0){
				System.exit(0); //TEMPORARY END GAME
			} else timer--;
			
		}
	}
	
	public void render(Graphics2D g2){
		g2.setColor(Color.GRAY);
		g2.fillRect(15, 15, 200, 32);
		
		g2.setColor(PLAYER_HEALTH > greenMin ? Color.GREEN : PLAYER_HEALTH > yellowMin ? Color.YELLOW : Color.RED);
		g2.fillRect(15, 15, (int)PLAYER_HEALTH * 2, 32);
		
		g2.setColor(Color.WHITE);
		g2.drawRect(15, 15, 200, 32);
		
		g2.drawString("SCORE: " + score, 15, 65);
		g2.drawString("LEVEL: " + level, 15, 80);
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
