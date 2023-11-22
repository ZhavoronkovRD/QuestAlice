package com.epam.ekids;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JOptionPane;

import com.epam.ekids.base.Game;
import com.epam.ekids.base.InputListener;
import com.epam.ekids.base.Level;
import com.epam.ekids.base.elements.Ammo;
import com.epam.ekids.base.elements.Construct;
import com.epam.ekids.base.heroes.Enemy;
import com.epam.ekids.base.heroes.Hero;
import com.epam.ekids.base.utils.Direction;
import com.epam.ekids.base.utils.LevelLoader;
import com.epam.ekids.base.utils.Type;

public class Quest extends Game {

	private static InputListener inListener;
	Hero hero;
	Direction direction;
	List<Construct> levelEl;
	List<Enemy> enemyList = createList();
	int score = 0;
	int dungeonCollected = 0;

	Level currentLevel;
	private BufferedImage back;
	private Ammo sword;
	private Enemy dragon;

	private void init() {
		inListener = new InputListener(this);
	}

	public static void main(String[] args) {
		Quest quest = new Quest();
		quest.init();
		quest.start();
		quest.addInput(inListener);
		quest.runTimer(10);
		quest.initiate();
	}

	private void start() {
		// 01 Create new window
		createWindow("My game");

		// 02 Load level
		back = loadBackImage("back");
		LevelLoader levelLoader = new LevelLoader();
		levelEl = levelLoader.loadLevel("myLevel");
		
		// 04 Load pictures
		List<BufferedImage> imgs = loadHeroImage("alice");

		// 05 Create hero
		hero = new Hero(50, 50, imgs);

		// 11 Create enemy
		List<BufferedImage> enimgs = loadEnemyImage("enemy");
		Enemy enemy1 = new Enemy(610, 50, enimgs);
		enemy1.setDirection(Direction.DOWN);
		
		List<BufferedImage> enimgs2 = loadEnemyImage("enemy2");
		Enemy enemy2 = new Enemy(660, 400, enimgs2);
		enemy2.setDirection(Direction.UP);
		
		enemyList.add(enemy1);
		enemyList.add(enemy2);
		
		// 15 Sword
		List<BufferedImage> swordimgs = loadSwordImage("sword");
		sword = new Ammo(50, 500, swordimgs);
		
		// 17 Super enemy
		List<BufferedImage> dragonImg = loadEnemyImage("dragon");
		dragon = new Enemy(750, 80, dragonImg);
		enemyList.add(dragon);
	}

	public void setDirection(Direction direction) {
		hero.setDirection(direction);
	}

	@Override
	public void performAction() {
		//08 Go!
		if(hero.direction == Direction.DOWN) {
			hero.y++;
			if(isWalls()) {
				hero.y--;
			}
		} else if (hero.direction == Direction.UP) {
			hero.y--;
			if(isWalls()) {
				hero.y++;
			}
		} else if (hero.direction == Direction.RIGHT) {
			hero.x++;
			if(isWalls()) {
				hero.x--;
			}
		} else if (hero.direction == Direction.LEFT) {
			hero.x--;
			if(isWalls()) {
				hero.x++;
			}
		}
		
		checkDungeon();
		enemyBehavior();
		checkIfHeroMeetEnemy();
		swordBehavior();
		dragonBehavior();
		
		// 13 Fail game		
		if(hero != null && hero.life == 0) {
			stopGame("Внимание!", "Это конец", JOptionPane.ERROR_MESSAGE);
		}
		
		// 14 Win game
		if(hero.dungeon == 11) {
			stopGame("Внимание!", "Победа", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void dragonBehavior() {
		int diffX = dragon.x - hero.x;
		int diffY = dragon.y - hero.y;
		
		if(Math.abs(diffX) < 270 && Math.abs(diffY) < 270) {
			if(Math.abs(diffX) > Math.abs(diffY)) {
				if(diffX > 0) {
					dragon.x--;
					dragon.setDirection(Direction.LEFT);
				} else {
					dragon.x++;
					dragon.setDirection(Direction.RIGHT);
				}
			} else {
				if(diffY > 0) {
					dragon.y--;
					dragon.setDirection(Direction.UP);
				} else {
					dragon.y++;
					dragon.setDirection(Direction.DOWN);
				}
			}
		}
	}
	
	
	public void swordBehavior() {
		if(hero.intersects(sword)) {
			hero.arm(sword);
		}
		
		if(hero.getAmmo() != null) {
			sword.y = hero.y;
			
			if(hero.direction == Direction.RIGHT) {
				sword.x = hero.x + hero.width;
				sword.changeImgRight();
			} else if(hero.direction == Direction.LEFT) {
				sword.x = hero.x - sword.width;
				sword.changeImgLeft();
			}
		}
	}

	public boolean isWalls() {
		for (Construct st : levelEl) {
			if (hero.intersects(st) && st.constructType == Type.WALL) {
				return true;
			}
		}

		return false;
	}
	
	public void checkDungeon() {
		for (Construct ct : levelEl) {
			if (hero.intersects(ct) && ct.constructType == Type.DUNGEON) {
				hero.collectDungeon();
				levelEl.remove(ct);
			}
		}
	}
	
	public void enemyBehavior() {
		for (Enemy en : enemyList) {
			if (en != dragon) {
				if (en.direction == Direction.UP) {
					en.y--;
				} else if (en.direction == Direction.DOWN) {
					en.y++;
				}

				if (en.y > 400) {
					en.setDirection(Direction.UP);
				} else if (en.y < 50) {
					en.setDirection(Direction.DOWN);
				}
			}
		}
	}
	
	public void checkIfHeroMeetEnemy() {
		for (Enemy en : enemyList) {
			if (hero.intersects(en)) {
				hero.life--;
				hero.x = 50;
				hero.y = 50;
			}
			
			if(sword.intersects(en)) {				
				enemyList.remove(en);				
				hero.collectDungeon();
			}
		}
	}
	

	@Override
	public void paint(Graphics graphics) {
		// 03 Paint level background
		paintBackground(back);
	
		// 06 Paint hero
		paintElement(hero);
		
		// 07 Paint static elements
		for(Construct elm: levelEl) {
			paintElement(elm);
		}

		// 09 Score
		paintString("Score: " + hero.getDungeon(), 5, 5, 14);
		
		// 10 Life
		paintString("Life: " + hero.life, 150, 5, 14);
		
		// 12 Paint enemies
		for(Enemy enm: enemyList) {
			paintElement(enm);
		}
		
		// 16 Paint sword
		paintElement(sword);

	}

}
