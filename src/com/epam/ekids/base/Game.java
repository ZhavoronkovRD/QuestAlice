package com.epam.ekids.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.epam.ekids.base.display.Display;
import com.epam.ekids.base.elements.Element;
import com.epam.ekids.base.resource.PropertyLoader;
import com.epam.ekids.base.utils.ElementDrawer;
import com.epam.ekids.base.utils.GameInput;
import com.epam.ekids.base.utils.RestrictionManager;

public abstract class Game extends ImgLoader implements ActionListener {

	private Random random;
	private Graphics currentGraphics;
	private int width;
	private int height;
	private boolean initiated;
	private Timer timer;

	public Game() {
		Boolean restriction = Boolean.valueOf(PropertyLoader.getMessage("restriction"));
		if(restriction) {
			RestrictionManager.switchOn();
		}
	}
	
	public void setCode(String code) {
		RestrictionManager.checkCode(code);
	}
	
	public void initiate() {
		initiated = true;
	}
	
	public void createWindow() {
		Display.create(200, 200, "Test frame", this);
		this.width = 200;
		this.height = 200;
	}

	public void createWindow(int width, int height, String title) {
		Display.create(width, height, title, this);
		this.width = width;
		this.height = height;
	}
	
	public void createWindow(String title) {
		Display.create(900, 600, title, this);
		this.width = 1000;
		this.height = 700;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public void addInput(GameInput gameInput) {
		Display.addMouseListener(gameInput);
		Display.addKeyListener(gameInput);
	}

	public void runTimer(int delay) {
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void stopGame(String caption, String text, int errorMessage) {
		if(timer != null) {
			timer.stop();
		}
		JOptionPane.showMessageDialog(Display.getWindow(), text, caption, errorMessage);
	}
	
	public void stopGame() {
		if(timer != null) {
			timer.stop();
		}
	}

	public void repaint() {
		Display.repaintRenderer();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(initiated){
			performAction();
		}
		Display.repaintRenderer();
	}

	public abstract void performAction();

	/////////////////////////////////////////////////////////////////////////////
//	public BufferedImage loadImage(String path) {
//		return ResourceLoader.loadImage(path);
//	}
//
//	public BufferedImage loadImageWithoutBack(String path, Color backColor) {
//		BufferedImage image = ResourceLoader.loadImage(path);
//		return ResourceLoader.colorToTransparent(image, backColor);
//	}
//
//	//TODO
//	public List<BufferedImage> loadHeroImage(String name) {
//		String pathPrefix = PropertyLoader.getMessage("heropath");
//		return loadImage(pathPrefix, name);
//	}
//	
//	public List<BufferedImage> loadEnemyImage(String name) {
//		String pathPrefix = PropertyLoader.getMessage("enemypath");
//		return loadImage(pathPrefix, name);
//	}
//	
//	private List<BufferedImage> loadImage(String pathPrefix, String name) {
//		ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
//		imgs.add(loadImageWithoutBack(pathPrefix + name + ".png", Color.WHITE));
//		imgs.add(loadImageWithoutBack(pathPrefix + name + "L.png", Color.WHITE));
//		imgs.add(loadImageWithoutBack(pathPrefix + name + "U.png", Color.WHITE));
//		imgs.add(loadImageWithoutBack(pathPrefix + name + "D.png", Color.WHITE));
//		return imgs;
//	}
	/////////////////////////////////////////////////////////////////////////////

	public int getRandomNumber(int limit) {
		if (random == null) {
			random = new Random();
		}
		return random.nextInt(limit);
	}

	public void paintElement(Element element) {
		paintElement(element, currentGraphics);
	}
	
	public void paintElement(Element element, Graphics graphics) {
		if (element != null && graphics != null) {
			ElementDrawer.paintElement(element, graphics);
		}
	}
	
	public void paintBackgroundPart(int x, int y, int width, int height, Color color, Graphics graphics) {
		graphics.setColor(color);
		graphics.fillRect(x, y, width, height);
	}
	
	public void paintBackgroundPart(int x, int y, int width, int height, Color color) {
		paintBackgroundPart(x, y, width, height, color, currentGraphics);
	}
	
	public void paintBackground(Color color) {
		paintBackground(color, currentGraphics);
	}
	
	public void paintBackground(Color color, Graphics graphics) {
		graphics.setColor(color);
		graphics.fillRect(0, 0, Display.getWidth(), Display.getHeight());
	}
	
	public void paintBackground(BufferedImage image) {
		paintBackground(image, currentGraphics);
	}
	
	public void paintBackground(BufferedImage image, Graphics graphics) {
		if(image != null && graphics != null) {
			ElementDrawer.paintImage(new Element(0,0, image), graphics);
		}
	}
	
	public void paintString(String text, int x, int y, int size, Color color) {
		paintString(text, x, y, size, color, currentGraphics);
	}
	
	public void paintString(String text, int x, int y, int size, Color color, Graphics graphics) {
		graphics.setColor(color);
		graphics.setFont(new Font("Arial", 1, size));
		graphics.drawString(text, x, y + size);
	}
	
	public void paintString(String text, int x, int y, int size) {
		paintString(text, x, y, size, currentGraphics);
	}
	
	public void paintString(String text, int x, int y, int size, Graphics graphics) {
		paintString(text, x, y, size, Color.WHITE, graphics);
	}

	public void paintInitiated(Graphics graphics){
		if(initiated){
			paint(graphics);
		}
	}
	
	public abstract void paint(Graphics graphics);
	
	public void setGraphics(Graphics graphics) {
		currentGraphics = graphics;
	}
	
	public <T> List<T> createList() {
		return new CopyOnWriteArrayList<T>();
	}
}
