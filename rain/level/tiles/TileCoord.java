package com.kg.rain.level.tiles;

public class TileCoord {
	
	private int x, y;
	
	public TileCoord(int x, int y, int tileSize) {
		this.x = x*tileSize;
		this.y = y*tileSize;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public int[] xy() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}

}
