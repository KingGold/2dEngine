package com.kg.rain.graphics;

public class Sprite {
	//public final int SIZE;
	private int x, y, width, height;
	public int[] pixels;
	private SpriteSheet sheet;
	
	//void tiles (for debug or null areas of play
	public static Sprite voidTile = new Sprite(16, 16, 0);
	public static Sprite pointTile = new Sprite(1, 1, 0);
	
	//player sprites
	public static Sprite playerForward = new Sprite(16, 16, 2, 8, SpriteSheet.tiles);
	public static Sprite playerBack = new Sprite(16, 16, 0, 8, SpriteSheet.tiles);
	public static Sprite playerLeft = new Sprite(16, 16, 3, 8, SpriteSheet.tiles);
	public static Sprite playerRight = new Sprite(16, 16, 1, 8, SpriteSheet.tiles);
	public static Sprite playerInventory = new Sprite(64, 32, 0x00ffffff);
	
	//floor sprites
	public static Sprite woodPlank = new Sprite(16, 16, 0, 1, SpriteSheet.tiles);
	public static Sprite grass = new Sprite(16, 16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 16, 1, 0, SpriteSheet.tiles);
	
	//wall sprites
	public static Sprite rock = new Sprite(16, 16, 2, 0, SpriteSheet.tiles);
	
	//particles
	public static Sprite particalNormal = new Sprite(3, 3, 0xffaaaaaa);
	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	//sprites loaded from sheet
	public Sprite(int w, int h, int x, int y, SpriteSheet sheet) {
		width = w;
		height = h;
		pixels = new int[width*height];
		this.x = x*width;
		this.y = y*height;
		this.sheet = sheet;
		load();
	}
	
	//sprites with only a solid color
	public Sprite(int w, int h, int color) {
		width = w;
		height = h;
		pixels = new int[width*height];
		setColor(color);
	}
	
	//makes all pixels one color
	private void setColor(int color) {
		for(int i=0; i<width*height; i++) {
			pixels[i] = color;
		}
	}

	//retrieves sprite image from the sheet
	public void load() {
		for(int y=0; y < height; y++) {
			for(int x=0; x < width; x++) {
				pixels[x+y*width] = sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
			}
		}
	}
}
