package com.kg.rain.level.tiles;

import com.kg.rain.graphics.Screen;
import com.kg.rain.graphics.Sprite;

public class SolidTile extends Tile {

	public SolidTile(Sprite sprite) {
		super(sprite);
		
	}
	public void render(int x, int y, Screen screen) {
		screen.renderSprite(x<<4, y<<4, this.sprite);
	}
	
	public boolean solid() {
		return true;
	}

}
