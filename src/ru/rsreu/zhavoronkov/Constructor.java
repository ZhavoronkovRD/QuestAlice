package ru.rsreu.zhavoronkov;

import java.awt.EventQueue;

import ru.rsreu.zhavoronkov.constructor.View;

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
