package com.epam.ekids.base;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.epam.ekids.base.resource.PropertyLoader;
import com.epam.ekids.base.resource.ResourceLoader;

public class ImgLoader {
	
	public BufferedImage loadImage(String path) {
		return ResourceLoader.loadImage(path);
	}
	
	public BufferedImage loadBackImage(String path) {
		String pathPrefix = PropertyLoader.getMessage("levelpath");
		return ResourceLoader.loadImage(pathPrefix + path + ".png");
	}

	public BufferedImage loadImageWithoutBack(String path, Color backColor) {
		BufferedImage image = ResourceLoader.loadImage(path);
		return ResourceLoader.colorToTransparent(image, backColor);
	}

	public List<BufferedImage> loadHeroImage(String name) {
		String pathPrefix = PropertyLoader.getMessage("heropath");
		return loadImage(pathPrefix, name);
	}
	
	public List<BufferedImage> loadEnemyImage(String name) {
		String pathPrefix = PropertyLoader.getMessage("enemypath");
		return loadImage(pathPrefix, name);
	}
	
	public List<BufferedImage> loadSwordImage(String name) {
		String pathPrefix = PropertyLoader.getMessage("heropath");
		return loadImageLR(pathPrefix, name);	
	}
	
	private List<BufferedImage> loadImage(String pathPrefix, String name) {
		ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
		imgs.add(loadImageWithoutBack(pathPrefix + name + ".png", Color.WHITE));
		imgs.add(loadImageWithoutBack(pathPrefix + name + "L.png", Color.WHITE));
		imgs.add(loadImageWithoutBack(pathPrefix + name + "U.png", Color.WHITE));
		imgs.add(loadImageWithoutBack(pathPrefix + name + "D.png", Color.WHITE));
		return imgs;
	}
	
	private List<BufferedImage> loadImageLR(String pathPrefix, String name) {
		ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
		imgs.add(loadImageWithoutBack(pathPrefix + name + "R.png", Color.WHITE));
		imgs.add(loadImageWithoutBack(pathPrefix + name + "L.png", Color.WHITE));
		return imgs;
	}

}
