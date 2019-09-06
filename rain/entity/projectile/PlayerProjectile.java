package com.kg.rain.entity.projectile;

import com.kg.rain.graphics.Screen;
import com.kg.rain.graphics.Sprite;

public class PlayerProjectile extends Projectile {

	public PlayerProjectile(int x, int y, double dir, Sprite sprite, Screen screen) {
		super(x, y, dir, sprite, screen);
		range = 200;
		damage = 20;
		rateOfFire = 15;
		speed = 4;
		dx = Math.cos(angle)*speed;
		dy = Math.sin(angle)*speed;
	}

	public void update() {
		move();
		if(getDistance()>range) {
			this.remove();
		}
	}
	
	//returns distance from origin
	private double getDistance() {
		double dist = 0;
		dist = Math.sqrt((xOrigin-x)*(xOrigin-x)+(yOrigin-y)*(yOrigin-y));
		//System.out.println(dist);
		return dist;
	}

	protected void move() {
		x += dx;
		y += dy;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int)x-8, (int)y-7, sprite);
	}
}
