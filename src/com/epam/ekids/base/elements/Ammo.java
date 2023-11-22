package com.epam.ekids.base.elements;

import java.awt.image.BufferedImage;
import java.util.List;

import com.epam.ekids.base.heroes.Enemy;
import com.epam.ekids.base.utils.Direction;
import com.epam.ekids.base.utils.RestrictionManager;

public class Ammo extends Enemy {

	private boolean isShooting;
	private Direction direction;
	private int distance;
	
	private int defaultX;
	private int defaultY;

	public Ammo(int x, int y, BufferedImage image) {
		super(x, y, image);
		defaultX = x;
		defaultY = y;
	}
	
	public Ammo(BufferedImage image) {
		super(0, -10, image);
		defaultX = 0;
		defaultY = -10;
	}
	
	public Ammo(BufferedImage image, int distance) {
		super(0, -10, image);
		defaultX = 0;
		defaultY = -10;
		this.distance = distance;
	}
	
	public Ammo(int x, int y, BufferedImage leftImg, BufferedImage rightImg) {
		super(x, y, rightImg);
		setAdditionslImage(leftImg, null, null);
	}
	
	public Ammo(int x, int y, List<BufferedImage> imgs) {
		super(x, y, imgs.get(0));
		setAdditionslImage(imgs.get(1), null, null);
	}

	@Override
	protected boolean checkAdditionalImg(BufferedImage image) {
		boolean noNull = image != null;
		boolean allow = RestrictionManager.isChangeImgAllowAmmo();

		return noNull & allow;
	}
	
	public void shot(Direction direction, int x, int y) {
		shot(direction, x, y, this.distance);
	}

	public void shot(Direction direction, int x, int y, int distance) {
		switch (direction) {
		case UP:
			changeImgUp();
			break;
		case DOWN:
			changeImgDown();
			break;
		case LEFT:
			changeImgLeft();
			break;
		case RIGHT:
			changeImgRight();
			break;
		default:
			break;
		}
		isShooting = true;
		this.direction = direction;
		this.distance = distance;
		this.x = x;
		this.y = y;
	}

	public void go() {
		if (isShooting) {
			switch (direction) {
			case UP:
				y--;
				break;
			case DOWN:
				y++;
				break;
			case LEFT:
				x--;
				break;
			case RIGHT:
				x++;
				break;
			default:
				break;
			}
			distance--;
			
			if(distance == 0) {
				isShooting = false;
				goToDefault();
			}
		}

	}
	
	private void goToDefault() {
		x = defaultX;
		y = defaultY;
	}
	
	public boolean isShooting(){
		return isShooting;
	}
	
	public void hide() {
		isShooting = false;
		goToDefault();
	}

}
