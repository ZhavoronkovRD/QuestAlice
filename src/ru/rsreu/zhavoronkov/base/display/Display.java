package ru.rsreu.zhavoronkov.base.display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import ru.rsreu.zhavoronkov.base.Game;


public class Display {

	private static boolean created = false;
	private static JFrame window;
	private static Renderer content;
	private static int _width;
	private static int _height;

	private static Game game;

	public static void create(int width, int height, String title, Game _game) {
		if (created)
			return;

		game = _game;
		
		window = new JFrame(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new Renderer();

		Dimension size = new Dimension(width, height);
		_width = width;
		_height = height;
		
		content.setPreferredSize(size);

		window.setResizable(false);
		window.add(content);
		window.pack();
		window.setLocationRelativeTo(null); // ������� ���� �� ������ ������
		window.setVisible(true);

		created = true;
	}

	public static void destroy() {
		if (!created)
			return;
		window.dispose();
	}

	public static void setTitle(String title) {
		window.setTitle(title);
	}

	public static void addMouseListener(MouseListener mouseListener) {
		if (created) {
			window.addMouseListener(mouseListener);
		}
	}

	public static void addKeyListener(KeyListener keyListener) {
		if (created) {
			window.addKeyListener(keyListener);
		}
	}

	public static void repaint(Graphics graphics) {
		game.setGraphics(graphics);
		game.paintInitiated(graphics);
		
	}

	public static void repaintRenderer() {
		content.repaint();
	}

	public static int getWidth() {
		return _width;
	}

	public static void setWidth(int width) {
		Display._width = width;
	}

	public static int getHeight() {
		return _height;
	}

	public static void setHeight(int height) {
		Display._height = height;
	}
	
	public static JFrame getWindow() {
		return window;
	}

}
