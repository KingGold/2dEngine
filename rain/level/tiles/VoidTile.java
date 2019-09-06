package com.kg.rain.level.tiles;

import com.kg.rain.graphics.Screen;
import com.kg.rain.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderSprite(x<<4, y<<4, this.sprite);
	}

}
