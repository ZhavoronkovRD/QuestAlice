package com.epam.ekids.base.utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.epam.ekids.base.elements.Construct;
import com.epam.ekids.base.resource.LevelMapParser;
import com.epam.ekids.base.resource.PropertyLoader;
import com.epam.ekids.base.resource.ResourceLoader;

public class LevelLoader {
	
	private HashMap<Integer, BufferedImage> pictureConnections = new HashMap<Integer, BufferedImage>();
	private HashMap<Integer, Type> typeConnections = new HashMap<Integer, Type>();
	private CopyOnWriteArrayList<Construct> elementsList = new CopyOnWriteArrayList<Construct>();
	private final String separator = ";";
	private String prefix = PropertyLoader.getMessage("levelpath");
	private String CSV = ".csv";
	
	public LevelLoader() {
		ImgType[] types = ImgType.values();
		for(int i = 1; i < types.length; i++) {
			ImgType type = types[i];
			BufferedImage bi = ResourceLoader.loadImageWithoutWhiteBack(type.getPath());
			align(i, bi, type.getType());
		}
	}
	
	public CopyOnWriteArrayList<Construct> loadLevel(String path) {
		List<List<Integer>> res = LevelMapParser.parseCSV(prefix + path + CSV, separator);
		
		for(int i = 0; i < res.size(); i++) {
			List<Integer> string = res.get(i);
			
			for(int j = 0; j < string.size(); j++) {
				Construct el = createElement(string.get(j), i, j);
				
				if(el != null) {
					elementsList.add(el);
				}
			}
		}

		return elementsList;
	}
	
	public void align(int number, BufferedImage img, Type type) {
		pictureConnections.put(number, img);
		typeConnections.put(number, type);
	}
	
	private Construct createElement(int number, int i, int j) {
		Construct el = null;
		BufferedImage img = pictureConnections.get(number);
		
		if(img != null) {
			int X = (j * img.getTileHeight()) + 1;
			int Y = (i * img.getTileWidth()) + 1;
			
			el = new Construct(X, Y, img);
			el.constructType = typeConnections.get(number);
		}
		
		return el;
	}

}
