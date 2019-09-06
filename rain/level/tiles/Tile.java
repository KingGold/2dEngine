package com.kg.rain.level.tiles;

import com.kg.rain.graphics.Screen;
import com.kg.rain.graphics.Sprite;

public class Tile {
	public int x, y;
	public Sprite sprite;
	
	//floor tiles
	public static Tile grass =  new FloorTile(Sprite.grass);
	public static Tile flower =  new FloorTile(Sprite.flower);
	public static Tile woodPlank =  new FloorTile(Sprite.woodPlank);
	
	//solid tiles
	public static Tile rock =  new SolidTile(Sprite.rock);
	
	//void tiles
	public static Tile voidTile =  new VoidTile(Sprite.voidTile);
	public static Tile pointTile =  new VoidTile(Sprite.pointTile);
	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean solid() {
		return false;
	}
}
