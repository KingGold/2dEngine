package com.kg.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.kg.rain.entity.mob.Player;
import com.kg.rain.graphics.Screen;
import com.kg.rain.graphics.Sprite;
import com.kg.rain.input.Keyboard;
import com.kg.rain.input.Mouse;
import com.kg.rain.level.Level;
import com.kg.rain.level.RandomLevel;
import com.kg.rain.level.SpawnLevel;

public class Game extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int width = 300;
	private static int height = width / 16 * 9;
	
	private static int scale = 3;
	public static String title = "Rain";
	public static int wW = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();//width*scale;
	public static int wH = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();//height*scale;
	public static int windowWidth = width*scale;//(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();//width*scale;
	public static int windowHeight = height*scale;//(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();//height*scale;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	private Screen screen;
	private Keyboard key;
	private Level level;
	private BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	private Player player;
	Dimension size;
	
	
	public Game() {
		//creating window
		//NOTE: for fullscreen; uncomment screen = new Screen(windowWidth, windowHeight);
		size = new Dimension(windowWidth, windowHeight);
		setPreferredSize(size);
		screen = new Screen(width, height);
		//screen = new Screen(windowWidth, windowHeight);
		frame = new JFrame();
		
		//creating input
		key = new Keyboard();
		frame.addKeyListener(key);
		addKeyListener(key);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		//creating level and player
		level = new SpawnLevel("/textures/grassLevel.png");
		player = new Player();
		player.init(level);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		key.update();
		player.update();
		level.update();
		if(Keyboard.end) System.exit(0);
		
		
		
		//dead code, trying to toggle fullscreen
		/*if(key.minus) {
			windowWidth = width*scale;
			windowHeight = height*scale;
			resizeWindow(frame);
		}
		if(key.equals) {
			windowWidth = wW;//width*scale;
			windowHeight = wH;//height*scale;
			resizeWindow(frame);
			//frame.setUndecorated(false);
		}*/
		
	}
	
	/*public void resizeWindow(Frame frame) {
		size = new Dimension(windowWidth, windowHeight);
		frame.setSize(size);
		JFrame newFrame = new JFrame();
		if(frame.isUndecorated()) {
			newFrame.setTitle(Game.title);
			newFrame.setResizable(false);
			newFrame.setUndecorated(false);
			newFrame.add(game);
			newFrame.pack();
			newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			newFrame.setVisible(true);
			newFrame.addKeyListener(key);
			frame = newFrame;
		}else {
			newFrame.setResizable(false);
			newFrame.setUndecorated(true);
			newFrame.add(game);
			newFrame.pack();
			newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			newFrame.setVisible(true);
			newFrame.addKeyListener(key);
			frame = newFrame;
		}
		
	}*/
	
	
	public void render() {
		//creating 3 frames to be loaded at any time
		BufferStrategy bs =  getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		//setting scroll ability to window
		int xScroll = player.x - screen.width/2;
		int yScroll = player.y - screen.height/2;
		screen.clear();
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for(int i=0; i<pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		//creating graphics and showing image
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		frame.requestFocus();
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + "   |   " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
				
			}
		}
		stop();
	}
	
	public static void main(String[] args) {
		
		//creating a new Game and setting the frame
		//NOTE: for fullscreen; uncomment setUndercorated(true);
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		//game.frame.setUndecorated(true);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
}
