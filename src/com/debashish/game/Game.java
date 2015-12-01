package com.debashish.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1665118210041929196L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	
	private Handler handler;
	private HUD hud;
	private Menu menu;
	
	private Spawn spawner;
	
	public enum STATE{
		Menu,
		Game,
		Help,
		EndScreen
	};
	
	public static STATE gameState;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game(){
		gameState = STATE.Menu;
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		menu = new Menu(this, handler);
		this.addMouseListener(menu);
		
		hud = new HUD();
		new Window(WIDTH, HEIGHT, "Debashish's Java Game", this);
		
		
		r = new Random();
		hud = new HUD();
		spawner = new Spawn(handler, hud, this);
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.requestFocus(); //get focus
		long lastTime = System.nanoTime(); //init beginning time
		double tickAmount = 60;	//define new unit of time
		double nanosPerTick = 1000000000 / tickAmount; //define a tick: 60 ticks per second
		double change = 0; //init change to 0
		long timer = System.currentTimeMillis(); //get start time in milliseconds
		int frames = 0, ticks = 0; //start with 0 frames, 0 ticks
		
		while(running){ //begin a second
			long currentTime = System.nanoTime(); //get start time of second
			change += (currentTime - lastTime) / nanosPerTick; //calculate change in time since last iteration
			lastTime = currentTime; //reset last second
			while(change >= 1){ //now iterate based on change in time since last second
				ticks++;
				tick();
				change--;
			}
			if(running){ //output
				render();
			}
			frames++; //increment frames
			
			if(System.currentTimeMillis() - timer > 1000){ //once a second has elapsed, restart
				timer += 1000; //add another second to beginning time
				System.out.println("FPS: " + frames + " ; TICKS: " + ticks); //output framerate
				frames = 0; //reset frame count
				ticks = 0; //reset ticks
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
		if(gameState == STATE.Game){
			hud.tick();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} //give HUD enough time so that spawner won't have null variables
			spawner.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
	
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g2);
		if(gameState == STATE.Game){
			hud.render(g2);
		} else {
			menu.render(g2);
		}
		g2.dispose();
		g.dispose();
		bs.show();
	}
	
	public synchronized void beginGame(){
		if(gameState == STATE.Game){
			handler.addObject(new Player(WIDTH/2 - 16, HEIGHT/2 - 16, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.BasicEnemy, handler));
		}
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max) return var = max;
		if(var <= min) return var = min;
		return var;
	}

}
