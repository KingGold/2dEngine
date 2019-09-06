package com.kg.rain.level;

import java.util.Random;

public class RandomLevel extends Level{
	
	private static Random rand =  new Random();

	public RandomLevel(int width, int height) {
		super(width, height);
		generateLevel();
	}
	
	protected void generateLevel() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				intTiles[x+y*width] = rand.nextInt(4);
			}
		}
	}

}
