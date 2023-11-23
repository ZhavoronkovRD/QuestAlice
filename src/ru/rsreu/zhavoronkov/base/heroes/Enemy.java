package ru.rsreu.zhavoronkov.base.heroes;

import java.awt.image.BufferedImage;
import java.util.List;

import ru.rsreu.zhavoronkov.base.elements.Element;
import ru.rsreu.zhavoronkov.base.utils.Direction;
import ru.rsreu.zhavoronkov.base.utils.RestrictionManager;

public class Enemy extends Element {
	
	public Direction direction;

	public Enemy(int x, int y, BufferedImage image) {
		super(x, y, image);
		direction = Direction.STOP;
	}
	
	public Enemy(int x, int y, List<BufferedImage> imgs) {
		super(x, y, imgs.get(0));
		setAdditionslImage(imgs.get(1), imgs.get(2), imgs.get(3));
	}

	@Override
	protected boolean checkAdditionalImg(BufferedImage image) {
		boolean noNull = image != null;
		boolean allow = RestrictionManager.isChangeImgAllowEnemy();
		
		return noNull & allow;
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

	
}
