package com.debashish.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.debashish.game.Game.STATE;

public class Menu extends MouseAdapter {
	Font myFont1;
	Font myFont2;
	Font myFont3;
	Font myFont4;
	Rectangle playBox;
	Rectangle helpBox;
	Rectangle exitBox;
	Rectangle backBox;
	Rectangle retryBox;

	private Game game;
	private Handler handler;

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;

		myFont1 = new Font(Font.DIALOG_INPUT, Font.BOLD, 70);
		myFont2 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 60);
		myFont3 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 40);
		myFont4 = new Font(Font.DIALOG_INPUT, Font.ITALIC, 30);

		playBox = new Rectangle(Game.WIDTH / 4, Game.HEIGHT / 4, Game.WIDTH / 2, Game.HEIGHT / 5);
		helpBox = new Rectangle(Game.WIDTH / 3, Game.HEIGHT / 2, Game.WIDTH / 3, Game.HEIGHT / 7);
		exitBox = new Rectangle(Game.WIDTH / 3, Game.HEIGHT / 2 + 90, Game.WIDTH / 3, Game.HEIGHT / 7);
		backBox = new Rectangle(Game.WIDTH / 3, Game.HEIGHT / 2 + 120, Game.WIDTH / 3, Game.HEIGHT / 7);
		retryBox = new Rectangle(Game.WIDTH / 4, Game.HEIGHT / 2, Game.WIDTH / 2, Game.HEIGHT / 7);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		float mx = e.getX();
		float my = e.getY();
		if (Game.gameState == STATE.Menu) {
			if (isInside(playBox, mx, my)) {
				Game.gameState = STATE.Game;
				game.beginGame();
			}

			if (isInside(helpBox, mx, my)) {
				Game.gameState = STATE.Help;
			}

			if (isInside(exitBox, mx, my)) {
				System.exit(0);
			}
		} else if (Game.gameState == STATE.Help) {
			if (isInside(backBox, mx, my)){
				Game.gameState = STATE.Menu;
			}
		} else if (Game.gameState == STATE.EndScreen) {
			if (isInside(retryBox, mx, my)) {
				Game.gameState = STATE.Menu;
			}
			if (isInside(exitBox, mx, my)) {
				System.exit(0);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	public boolean isInside(float x, float y, float mx, float my, float width, float height) {
		if (mx >= x && mx <= x + width) {
			if (my >= y && my <= y + height) {
				return true;
			}
		}
		return false;
	}

	public boolean isInside(Rectangle box, float mx, float my) {
		if (mx >= box.x && mx <= box.x + box.width) {
			if (my >= box.y && my <= box.y + box.height) {
				return true;
			}
		}
		return false;
	}

	public void render(Graphics2D g2) {
		if (Game.gameState == STATE.Menu) {
			g2.setColor(Color.BLACK);
			g2.drawRect(0, 0, Game.WIDTH, Game.HEIGHT);

			g2.setFont(myFont1);
			g2.setColor(Color.WHITE);
			drawCenteredString("Bullet Time", 0, 0, Game.WIDTH, Game.HEIGHT / 4, g2);

			g2.setFont(myFont2);
			g2.setColor(Color.GREEN);
			g2.draw(playBox);
			drawCenteredString("Play", playBox.x, playBox.y, playBox.width, playBox.height, g2);

			g2.setFont(myFont3);
			g2.setColor(Color.ORANGE);
			g2.draw(helpBox);
			drawCenteredString("Help", helpBox.x, helpBox.y, helpBox.width, helpBox.height, g2);

			g2.setColor(Color.RED);
			g2.draw(exitBox);
			drawCenteredString("Exit", exitBox.x, exitBox.y, exitBox.width, exitBox.height, g2);
			
			g2.setColor(Color.LIGHT_GRAY);
			g2.setFont(myFont4);
			drawCenteredString("Programming by: Debashish Biswas", 0, Game.HEIGHT / 3 + 25, Game.WIDTH, Game.HEIGHT, g2);
			drawCenteredString("'Art'work by: Jarren McBride", 0, Game.HEIGHT / 3 + 56, Game.WIDTH, Game.HEIGHT, g2);
		} else if (Game.gameState == STATE.Help) {
			g2.setFont(myFont1);
			g2.setColor(Color.ORANGE);
			drawCenteredString("Help", 0, 0, Game.WIDTH, Game.HEIGHT / 4, g2);
			
			g2.setColor(Color.WHITE);
			g2.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 30));
			drawCenteredString("Use WASD or arrow keys to move.", 0, 100, Game.WIDTH, Game.HEIGHT / 4, g2);
			drawCenteredString("Press ESC at any time to exit.", 0, 140, Game.WIDTH, Game.HEIGHT / 4, g2);
			drawCenteredString("See how long you can survive!", 0, 220, Game.WIDTH, Game.HEIGHT / 4, g2);
			
			g2.setFont(myFont3);
			g2.setColor(Color.ORANGE);
			g2.draw(backBox);
			drawCenteredString("Back", backBox.x, backBox.y, backBox.width, backBox.height, g2);
		} else if (Game.gameState == STATE.EndScreen) {
			handler.clearEnemies();

			g2.setFont(myFont1);
			g2.setColor(Color.RED);
			drawCenteredString("GAME OVER", 0, 0, Game.WIDTH, Game.HEIGHT / 3, g2);

			g2.setColor(Color.WHITE);
			g2.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 30));
			drawCenteredString("Your final level: " + HUD.level, 0, 60, Game.WIDTH, Game.HEIGHT / 3, g2);
			drawCenteredString("Your final score: " + HUD.score, 0, 100, Game.WIDTH, Game.HEIGHT / 3, g2);

			g2.setColor(Color.GREEN);
			g2.setFont(myFont3);
			g2.draw(retryBox);
			drawCenteredString("Main Menu", retryBox.x, retryBox.y, retryBox.width, retryBox.height, g2);

			g2.setColor(Color.RED);
			g2.draw(exitBox);
			drawCenteredString("Exit", exitBox.x, exitBox.y, exitBox.width, exitBox.height, g2);
		}

	}

	public static void drawCenteredString(String s, int xOffset, int yOffset, int w, int h, Graphics2D g2) {
		FontMetrics fm = g2.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
		g2.drawString(s, x + xOffset, y + yOffset);
	}
}
