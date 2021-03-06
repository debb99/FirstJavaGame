package com.debashish.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.debashish.game.Game.STATE;

public class HUD {

	public static float PLAYER_HEALTH = 100;
	private int greenMin = 66;
	private int yellowMin = 33;

	public static int score = 0;
	public static int level = 1;
	private int timer = 60;

	public void tick() {
		PLAYER_HEALTH = Game.clamp(PLAYER_HEALTH, 0, 100);
		score++;
		if (PLAYER_HEALTH <= 0) {
			Game.gameState = STATE.EndScreen;
		}
	}

	Font hudFont = new Font(Font.DIALOG_INPUT, Font.PLAIN, 20);

	public void render(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		g2.fillRect(15, 15, 200, 32);

		g2.setColor(PLAYER_HEALTH > greenMin ? Color.GREEN : PLAYER_HEALTH > yellowMin ? Color.YELLOW : Color.RED);
		g2.fillRect(15, 15, (int) PLAYER_HEALTH * 2, 32);

		g2.setColor(Color.WHITE);
		g2.drawRect(15, 15, 200, 32);

		g2.setFont(hudFont);
		g2.drawString("SCORE: " + score, 15, 70);
		g2.drawString("LEVEL: " + level, 15, 90);
	}

	public static int getScore() {
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
