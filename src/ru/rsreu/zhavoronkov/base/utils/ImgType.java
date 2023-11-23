package ru.rsreu.zhavoronkov.base.utils;

public enum ImgType {
	
	EMPTY("res/level/el0.png", null, 0), WALL1("res/level/el1.png", Type.WALL, 1),
	WALL2("res/level/el2.png", Type.WALL, 2), WALL5("res/level/el5.png", Type.WALL, 5),
	WALL6("res/level/el6.png", Type.WALL, 6), DUNGEON("res/level/dungeon.png", Type.DUNGEON, 7),
	DUNGEON2("res/level/dungeon2.png", Type.DUNGEON, 8), DOOR("res/level/door.png", Type.DOOR, 9);
	
	private String path;
	private Type type;
	private int number;

	private ImgType(String path, Type type, int number) {
		this.path = path;
		this.type = type;
		this.number = number;
	}

	public String getPath() {
		return path;
	}

	public Type getType() {
		return type;
	}

	public int getNumber() {
		return number;
	}

}
