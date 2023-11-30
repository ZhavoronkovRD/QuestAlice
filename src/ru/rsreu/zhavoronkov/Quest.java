package ru.rsreu.zhavoronkov;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JOptionPane;

import ru.rsreu.zhavoronkov.base.Game;
import ru.rsreu.zhavoronkov.base.InputListener;
import ru.rsreu.zhavoronkov.base.elements.Construct;
import ru.rsreu.zhavoronkov.base.heroes.Hero;
import ru.rsreu.zhavoronkov.base.utils.Direction;
import ru.rsreu.zhavoronkov.base.utils.LevelLoader;
import ru.rsreu.zhavoronkov.base.utils.Type;

public class Quest extends Game {

	private static InputListener inListener;
	Hero hero;
	List<Construct> levelEl;

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
		LevelLoader levelLoader = new LevelLoader();
		levelEl = levelLoader.loadLevel("blankLevel");
		
		// 04 Load pictures
		List<BufferedImage> imgs = loadHeroImage("alice");

		// 05 Create hero
		hero = new Hero(50, 50, imgs);

		// 11 Create enemy

		// 15 Sword

		// 17 Super enemy
	}

	public void setDirection(Direction direction) {
		hero.setDirection(direction);
	}

	@Override
	public void performAction() {
		//08 Go!
		// TODO: Написать логику движения героя
		// чтобы заставить героя двигаться - нужно менять его координаты hero.x, hero.y

		// 13 Fail game

		// 14 Win game
	}
	
	public boolean isWalls() {
		// TODO: Написать логику столкновения со стеной
		return false;
	}

	@Override
	public void paint(Graphics graphics) {
		// 03 Paint level background

		// 06 Paint hero
		paintElement(hero);
		
		// 07 Paint static elements
		for(Construct elm: levelEl) {
			paintElement(elm);
		}

		// 09 Score
		//TODO: Вывести на экран счет

		// 10 Life
		//TODO: Вывести на экран жизни

		// 12 Paint enemies

		// 16 Paint sword

	}
}
