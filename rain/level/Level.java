package com.kg.rain.level;

import java.util.ArrayList;
import java.util.List;

import com.kg.rain.entity.Entity;
import com.kg.rain.graphics.Screen;
import com.kg.rain.level.tiles.Tile;

public class Level {
	
	protected int[] tiles;
	protected int width, height;
	protected int[] intTiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		intTiles = new int[width*height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
		
	}
	
	public void update() {
		for(int i=0; i<entities.size(); i++) {
			entities.get(i).update();
		}
		
	}
	
	public boolean add(Entity e) {
		entities.add(e);
		return true;
	}
	
	public boolean remove(Entity e) {
		for(int i=0; i<entities.size(); i++) {
			if(entities.get(i).equals(e)) {
				entities.remove(i);
				return true;
			}
		}
		return false;
	}
	
	private void time() {
		
	}
	
	//render tiles to the corners of visible screen
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y=y0; y<y1; y++) {
			for(int x=x0; x<x1; x++) {
				getTile(x, y).render(x, y, screen);
				//Tile.pointTile.render(x, y, screen);
			}
		}
		for(int i=0; i<entities.size(); i++) {
			entities.get(i).render(screen);
		}
		
	}
	
	
	//Grass = 0xff00ff00
	//flower = 0xffffff00
	//rock = 0xff7f7f00
	public Tile getTile(int x, int y) {
		if(x<0||y<0||x>=width||y>=height) return Tile.voidTile;
		if(tiles[x+y*width] == 0xff00ff00) return Tile.grass;
		if(tiles[x+y*width] == 0xffffff00) return Tile.flower;
		if(tiles[x+y*width] == 0xff7f7f00) return Tile.rock;
		if(tiles[x+y*width] == 0xffea0808) return Tile.woodPlank;
		return Tile.voidTile;
	}

}
