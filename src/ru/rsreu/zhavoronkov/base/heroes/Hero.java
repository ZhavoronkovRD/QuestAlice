package ru.rsreu.zhavoronkov.base.heroes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import ru.rsreu.zhavoronkov.base.elements.Ammo;
import ru.rsreu.zhavoronkov.base.elements.Element;
import ru.rsreu.zhavoronkov.base.utils.Direction;
import ru.rsreu.zhavoronkov.base.utils.ElementType;
import ru.rsreu.zhavoronkov.base.utils.RestrictionManager;

public class Hero extends Element {
	
	public Direction gaze;
	private int bulletQuantity;
	private int bulletDistance;
	private Ammo bullet;
	public Direction direction;
	public int dungeon;
	public int life = 3;
	private Ammo ammo;

	public Hero(int x, int y, BufferedImage image) {
		super(x, y, image);
		this.gaze = Direction.RIGHT;

	}

	public Hero(int x, int y, int height, int width, Color color,
			ElementType type) {
		super(x, y, height, width, color, type);
	}

	public Hero(int x, int y, int height, int width) {
		super(x, y, height, width);
	}
	
	public Hero(int x, int y, List<BufferedImage> imgs) {
		super(x, y, imgs.get(0));
		this.gaze = Direction.RIGHT;//подумать надо ли это
		direction = Direction.STOP;
		setAdditionslImage(imgs.get(1), imgs.get(2), imgs.get(3));
	}
	
	public void changeImgRight() {
		super.changeImgRight();
		gaze = Direction.RIGHT;
	}
	
	public void changeImgLeft() {
		super.changeImgLeft();
		gaze = Direction.LEFT;
	}
	
	public void changeImgUp() {
		super.changeImgUp();
		gaze = Direction.UP;
	}
	
	public void changeImgDown() {
		super.changeImgDown();
		gaze = Direction.DOWN;
	}
	
		
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
		switch (direction) {
		case LEFT:
			changeImgLeft();
			break;
		case RIGHT:
			changeImgRight();
			break;
		case UP:
			changeImgUp();
			break;
		case DOWN:
			changeImgDown();
			break;
		default:
			break;
		}
	}

	public void clip(int quantity, int distance, Ammo bullet) {
		this.bulletQuantity = quantity;
		this.bulletDistance = distance;
		this.bullet = bullet;
	}
	
	public void shot() {
		if(bullet != null && !bullet.isShooting() && bulletQuantity != 0) {
			int bulletX = x + (width / 2);
			int bulletY = y + (height / 2);
			
			System.out.println(bulletX + ":" + bulletY);
			
			bullet.shot(gaze, bulletX, bulletY, bulletDistance);
			bulletQuantity--;
		}
	}
	
	public void collectDungeon() {
		dungeon++;
	}
	
	public int getDungeon() {
		return dungeon;
	}
	
	public void setDungeon(int dungeon) {
		this.dungeon = dungeon;
	}
	
	
	@Override
	protected boolean checkAdditionalImg(BufferedImage image) {
		boolean noNull = image != null;
		boolean allow = RestrictionManager.isChangeImgAllow();
		
		return noNull & allow;
	}
	
	public void arm(Ammo ammo) {
		this.ammo = ammo;
	}
	
	public Ammo getAmmo() {
		return ammo;
	}

}
