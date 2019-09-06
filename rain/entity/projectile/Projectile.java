package com.kg.rain.entity.projectile;

import com.kg.rain.entity.Entity;
import com.kg.rain.graphics.Screen;
import com.kg.rain.graphics.Sprite;

public abstract class Projectile extends Entity {

	final protected int xOrigin, yOrigin;
	protected Sprite sprite;
	protected double dx, dy, speed, rateOfFire, range, damage, x, y, angle;
	
	public Projectile(int x, int y, double dir, Sprite sprite, Screen screen) {
		super(x, y, sprite, screen);
		xOrigin = x;
		yOrigin = y;
		angle = dir;
	}
	
	protected void move() {
		
		
	}
	
}
