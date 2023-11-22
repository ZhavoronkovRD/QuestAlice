package com.epam.ekids.base.resource;

import java.util.ResourceBundle;

public class PropertyLoader {
	
	private static final ResourceBundle PROPERTIES = ResourceBundle.getBundle("settings.main");
	
	public static String getMessage(String key) {
		return PROPERTIES.getString(key);
	}

}
