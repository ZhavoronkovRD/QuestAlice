package ru.rsreu.zhavoronkov.base.utils;

import java.awt.Color;
import java.awt.Graphics;

import ru.rsreu.zhavoronkov.base.elements.Element;

public class ElementDrawer {
	
	public static void paintElement(Element element, Graphics graphics) {
		if(element.image != null) {
			paintImage(element, graphics);
		} else {
			paintFigure(element, graphics);
		}
	}

	public static void paintFigure(Element element, Graphics graphics) {
		ElementType type = element.type;
		Color color = element.color;
		if(color != null) {
			graphics.setColor(color);
		} else {
			graphics.setColor(Color.black);
		}
		
		if(type == ElementType.RECTANGLE) {
			graphics.fillRect(element.x, element.y, element.width, element.height);
		} else if (type == ElementType.CIRCLE) {
			graphics.fillOval(element.x, element.y, element.width, element.height);
		}	
	}

	public static void paintImage(Element element, Graphics graphics) {
		graphics.drawImage(element.image, element.x, element.y, null);		
	}
	
}
