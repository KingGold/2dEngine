package com.kg.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kg.rain.level.tiles.Tile;

public class SpawnLevel extends Level{

	public SpawnLevel(String path) {
		super(path);
	}
	
	
	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage img = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = img.getWidth();
			int h = height = img.getHeight();
			tiles =  new int[w*h];
			img.getRGB(0, 0, w, h, tiles, 0, w);
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("Execption! Can't Load File!");
		}
	}

}
