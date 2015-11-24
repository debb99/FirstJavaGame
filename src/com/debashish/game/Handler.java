package com.debashish.game;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			object.get(i).tick();
		}
	}
	
	public void render(Graphics2D g2){
		for(int i = 0; i < object.size(); i++){
			object.get(i).render(g2);
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}

	public synchronized void clearEnemies() {
		GameObject tempObject;
		for(int i = 0; i < object.size(); i++){
			tempObject = object.get(i);
			if(tempObject.id == ID.Player){
				object.clear();
				addObject(new Player(tempObject.getX(), tempObject.getY(), ID.Player, this));
			}
		}
	}
	
}
