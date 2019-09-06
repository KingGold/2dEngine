package com.kg.rain.entity;

import java.util.Random;

import com.kg.rain.graphics.Screen;
import com.kg.rain.graphics.Sprite;
import com.kg.rain.level.Level;

public class Entity {

	public int x, y;
	private boolean removed = false;
	protected final Random rand = new Random();
	protected Sprite sprite;
	protected Screen screen;
	
	public Entity(int x, int y, Sprite sprite, Screen screen) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.screen = screen;
	}
	
	public void update() {
		
	}
	public void render() {
		
	}
	public void remove() {
		removed = true;
	}
	public boolean isRemoved() {
		return removed;
	}
}
