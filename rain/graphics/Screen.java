package com.kg.rain.graphics;

import java.util.Random;

import com.kg.rain.level.tiles.Tile;

public class Screen {

	public int width, height, xOffset, yOffset;
	public int[] pixels;
	
	private Random rand =  new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void clear() {
		for(int i = 0; i<pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void update() {
		
	}
	
	public void render(int xOffset, int yOffset) {
	
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y=0; y<sprite.getHeight(); y++) {
			int ya = y+yp;
			for(int x=0; x<sprite.getWidth(); x++) {
				int xa = x+xp;
				//if(xa<-sprite.getWidth()||xa>=width||ya<0||ya>=height) break;
				//if(xa<0) xa=0;
				if(xa<0||xa>=width||ya<0||ya>=height) continue;
				//draws sprite while removing transparent purple
				int col = sprite.pixels[x+y*sprite.getWidth()];
				if(col!=0xffff00ff) pixels[xa+ya*width] = col;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
}
