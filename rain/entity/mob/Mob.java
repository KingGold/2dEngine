package com.kg.rain.entity.mob;


import com.kg.rain.entity.Entity;
import com.kg.rain.graphics.Screen;
import com.kg.rain.graphics.Sprite;
import com.kg.rain.level.Level;

public abstract class Mob extends Entity{
	public Mob(int x, int y, Sprite sprite, Screen screen) {
		super(x, y, sprite, screen);
	}

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	
	
	public void move(int dx, int dy) {
		
		//setting dx and dy on different channels if they are both != 0
		if(dx != 0 && dy != 0) {
			move(dx, 0);
			move(0, dy);
			return;
		}
		
		//otherwise following this chart
		// dir 0=north 1=east 2=south 3=west
		if(dx>0) dir = 1;
		if(dx<0) dir = 3;
		if(dy>0) dir = 2;
		if(dx<0) dir = 0;
		
		if(!collision(dx, dy)) {
			x += dx;
			y += dy;
		}
	}
	public void update() {
		
	}
	
	//returns true if the tile directly in front of the player's
	//direction is solid
	private boolean collision(int dx, int dy) {
		boolean solid = false;
		for(int c=0; c<4; c++) {
			int postDx = ((x+dx) + c % 2 * 14 - 7)>>4;
			int postDy = ((y+dy) + c / 2 * 14 - 7)>>4;
			if(level.getTile(postDx, postDy).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(Screen screen) {
		
	}
	
	public void init(Level level) {
		this.level = level;
	}
}
