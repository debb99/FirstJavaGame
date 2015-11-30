 package com.debashish.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		for(boolean i : keyDown){
			i = false;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.id == ID.Player){
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
					tempObject.setVelY(-8);
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
					tempObject.setVelY(8);
					keyDown[1] = true;
				}
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
					tempObject.setVelX(-8);
					keyDown[2] = true;
				}
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
					tempObject.setVelX(8);
					keyDown[3] = true;
				}
			}
			if(key == KeyEvent.VK_Q){
				HUD.PLAYER_HEALTH--;
			}
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.id == ID.Player){
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
					//tempObject.setVelY(0);
					keyDown[0] = false;
				}
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
					//tempObject.setVelY(0);
					keyDown[1] = false;
				}
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
					//tempObject.setVelX(0);
					keyDown[2] = false;
				}
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
					//tempObject.setVelX(0);
					keyDown[3] = false;
				}
				
				
				//vertical
				if(!keyDown[0] && !keyDown[1]){
					tempObject.setVelY(0);
				}
				
				//horiz movement
				if(!keyDown[2] && !keyDown[3]){
					tempObject.setVelX(0);
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
	}
}
