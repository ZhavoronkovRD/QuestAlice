package ru.rsreu.zhavoronkov.base.elements;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ru.rsreu.zhavoronkov.base.utils.ElementType;

public class Element {
	
	public int x;
	public int y;
	
	public BufferedImage image;
	private BufferedImage imageLeft;
	private BufferedImage imageUp;
	private BufferedImage imageDown;
	private BufferedImage imageRight;
	
	public int height;
	public int width;
	public Color color;
	public ElementType type;
	
	public Element(int x, int y, int height, int width, Color color, ElementType type){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.color = color;
		this.type = type;
	}
	
	public Element(int x, int y, int height, int width){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	public Element(int x, int y, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.imageRight = image;
		this.height = image.getTileHeight();
		this.width = image.getTileWidth();
	}
	
	public boolean intersects(Element element) {
		return element.width > 0 && element.height > 0 && width > 0 && height > 0
				 && element.x < x + width && element.x + element.width > x
				 && element.y < y + height && element.y + element.height > y;
	}
	
	public void setAdditionslImage(BufferedImage imageLeft, BufferedImage imageUp, BufferedImage imageDown) {
		this.imageLeft = imageLeft;
		this.imageUp = imageUp;
		this.imageDown = imageDown;
	}

	public void changeImgRight() {
		this.image = checkAdditionalImg(this.imageRight) ? this.imageRight : this.image;
	}
	
	public void changeImgLeft() {
		this.image = checkAdditionalImg(this.imageLeft) ? this.imageLeft : this.image;
	}
	
	public void changeImgUp() {
		this.image = checkAdditionalImg(this.imageUp) ? this.imageUp : this.image;
	}
	
	public void changeImgDown() {
		this.image = checkAdditionalImg(this.imageDown) ? this.imageDown : this.image;
	}
	
	protected boolean checkAdditionalImg(BufferedImage image) {
		return true;
	}

}
