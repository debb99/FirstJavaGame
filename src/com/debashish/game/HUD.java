package com.debashish.game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int PLAYER_HEALTH = 100;
	private int greenMin = 66;
	private int yellowMin = 33;
	
	private int score = 0, level = 1;
	
	public void tick(){
		PLAYER_HEALTH = Game.clamp(PLAYER_HEALTH, 0, 100);
		score++;
	}
	
	public void render(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 32);
		
		g.setColor(PLAYER_HEALTH > greenMin ? Color.GREEN : PLAYER_HEALTH > yellowMin ? Color.YELLOW : Color.RED);
		g.fillRect(15, 15, PLAYER_HEALTH * 2, 32);
		
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("SCORE: " + score, 15, 65);
		g.drawString("LEVEL: " + level, 15, 80);
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
