package ru.rsreu.zhavoronkov.base;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.rsreu.zhavoronkov.base.elements.Construct;
import ru.rsreu.zhavoronkov.base.elements.Element;
import ru.rsreu.zhavoronkov.base.heroes.Enemy;
import ru.rsreu.zhavoronkov.base.heroes.Hero;

public abstract class Level extends ImgLoader {
	
	public Hero hero;
	public boolean loaded;

	public abstract void enterHero(Hero hero);
	
	public abstract void loadLevel();
	
	public abstract void perform();
	
	public abstract List<Construct> getStaticElements();
	
	public abstract List<Enemy> getEnemies();
	
	public abstract BufferedImage getBack();
	
	public abstract boolean exit();
	
	public int getExitNumber() {
		return 0;
	}
	
	public String getMessage() {
		return "";
	}
	
	public <T> List<T> createList() {
		return new CopyOnWriteArrayList<T>();
	}
	
	public List<Element> getElements() {
		return new ArrayList<>();
	}
	
	public boolean isLoaded() {
		return loaded;
	}

}
