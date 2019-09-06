package com.kg.rain.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.kg.rain.Game;
import com.kg.rain.entity.projectile.PlayerProjectile;
import com.kg.rain.entity.projectile.Projectile;
import com.kg.rain.graphics.Screen;
import com.kg.rain.graphics.Sprite;
import com.kg.rain.input.Keyboard;
import com.kg.rain.input.Mouse;

public class Player extends Mob{
	
	protected List<Projectile> projectiles =  new ArrayList<Projectile>();
	private Sprite sprite = Sprite.playerForward;
	
	public Player(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	public void update() {
		int dx = 0, dy = 0;
		//input change variables
		if(Keyboard.up) dy--;
		if(Keyboard.down) dy++;
		if(Keyboard.left) dx--;
		if(Keyboard.right) dx++;
		//move player if dx or dy != 0
		if(dx!=0||dy!=0) move(dx, dy);
		
		updateProjectile();
		
	}

	private void updateProjectile() {
		//create projectile in direction of mouse form player
		if(Mouse.getButton() == 1) {
			double dx = Mouse.getX()-Game.windowWidth/2;
			double dy = Mouse.getY()-Game.windowHeight/2;
			double angleDir = Math.atan2(dy, dx);
			createProjectile(x, y, angleDir);
			//System.out.println(projectiles.size());
			//System.out.println(Math.toDegrees(angleDir));
		}
		
		//deletes all projectiles that has removed == true
		removeNullProjectiles();
	}
	
	//deletes all projectiles that has removed == true
	private void removeNullProjectiles(){
		for(int i=0; i<projectiles.size(); i++) {
			if(projectiles.get(i).isRemoved()) {
				level.remove(projectiles.get(i));
				projectiles.remove(i);
				i--;
			}
		}
		
	}
	
	private void createProjectile(int x, int y, double angleDir) {
		Projectile p = new PlayerProjectile(x, y, angleDir);
		//adding new projectile to the player's and level's list of entities
		projectiles.add(p);
		level.add(p);
	}

	public void render(Screen screen) {
		int xCenter = x-8;
		int yCenter = y-8;
		//changes sprite look depending on direction input
		if(Keyboard.up) sprite = Sprite.playerBack;
		if(Keyboard.right) sprite = Sprite.playerRight;
		if(Keyboard.down) sprite = Sprite.playerForward;
		if(Keyboard.left) sprite = Sprite.playerLeft;
		screen.renderSprite(xCenter, yCenter, sprite);
	}
}
