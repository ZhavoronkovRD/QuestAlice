package com.epam.ekids;

import java.awt.EventQueue;

import com.epam.ekids.constructor.View;

public class Constructor {
	View view;

	public static void main(String[] args) {
		new Constructor().init();

	}
	
	private void init() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				view = new View();
				view.setTitle("Constructor");
				
			}
		});
		
		//TODO - your code here
	}

}
