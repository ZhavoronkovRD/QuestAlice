package com.epam.ekids.base.resource;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {

	public static BufferedImage loadImage(String filePath) {

		BufferedImage image = null;

		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public static BufferedImage loadImageWithoutWhiteBack(String filePath) {
		BufferedImage image = loadImage(filePath);
		return colorToTransparent(image, Color.WHITE);
	}
	
	public static BufferedImage colorToTransparent(BufferedImage raw, Color remove) {
		int WIDTH = raw.getWidth();
		int HEIGHT = raw.getHeight();
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,	BufferedImage.TYPE_INT_ARGB);
		
		int pixels[] = new int[WIDTH * HEIGHT];
		
		raw.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
		
		for (int i = 0; i < pixels.length; i++) {
			if (pixels[i] == remove.getRGB()) {
				pixels[i] = pixels[i] & 0xFFFFFF;
			}
		}
		
		image.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
		
		return image;
	}

}
