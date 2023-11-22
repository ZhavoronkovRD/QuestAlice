package com.epam.ekids;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.epam.ekids.base.Level;
import com.epam.ekids.base.elements.Construct;
import com.epam.ekids.base.heroes.Enemy;
import com.epam.ekids.base.heroes.Hero;
import com.epam.ekids.base.utils.Direction;
import com.epam.ekids.base.utils.LevelLoader;
import com.epam.ekids.base.utils.Type;

public class CentralLevel extends Level {

	private BufferedImage back;
	private CopyOnWriteArrayList<Construct> levelEl;
	boolean exit = false;
	int exitNumber = 0;

	@Override
	public void enterHero(Hero hero) {
		this.hero = hero;
		hero.x = 50;
		hero.y = 250;
	}

	@Override
	public void loadLevel() {
		back = loadBackImage("back2");
		LevelLoader levelLoader = new LevelLoader();
		levelEl = levelLoader.loadLevel("centralLevel");
	}

	@Override
	public void perform() {
		if (hero.direction == Direction.DOWN) {
			hero.y++;
		} else if (hero.direction == Direction.UP) {
			hero.y--;
		} else if (hero.direction == Direction.RIGHT) {
			hero.x++;
		} else if (hero.direction == Direction.LEFT) {
			hero.x--;
		}
		
		checkDoor();
	}
	
	

	private void checkDoor() {
		for(Construct st:levelEl) {
			if(hero.intersects(st) && st.constructType == Type.DOOR) {
				exit = true;
				if(st.y < 105) {
					if(st.x > 100 && st.x < 130) exitNumber = 1;
					if(st.x > 270 && st.x < 305) exitNumber = 2;
					if(st.x > 450 && st.x < 475) exitNumber = 3;
					if(st.x > 625 && st.x < 650) exitNumber = 4;					
				} 
				if(st.y > 450) {
					if(st.x > 100 && st.x < 130) exitNumber = 5;
					if(st.x > 270 && st.x < 305) exitNumber = 6;
					if(st.x > 450 && st.x < 475) exitNumber = 7;
					if(st.x > 625 && st.x < 650) exitNumber = 8;					
				} 
				if(st.x > 775) {
					exitNumber = 9;
				}
			}
		}
	}

	@Override
	public List<Construct> getStaticElements() {
		return levelEl;
	}

	@Override
	public List<Enemy> getEnemies() {
		return new ArrayList<>();
	}

	@Override
	public BufferedImage getBack() {
		return back;
	}

	@Override
	public boolean exit() {
		// TODO Auto-generated method stub
		return exit;
	}

	@Override
	public int getExitNumber() {
		return exitNumber;
	}

	
}
