package ru.rsreu.zhavoronkov.base;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import ru.rsreu.zhavoronkov.Quest;
import ru.rsreu.zhavoronkov.base.utils.Direction;
import ru.rsreu.zhavoronkov.base.utils.GameInput;

public class InputListener extends GameInput {

	private Quest quest;

	public InputListener(Quest quest) {
		this.quest = quest;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			quest.setDirection(Direction.DOWN);
		} else if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			quest.setDirection(Direction.UP);
		} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			quest.setDirection(Direction.RIGHT);
		} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			quest.setDirection(Direction.LEFT);
		} 
	}
	
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		quest.setDirection(Direction.STOP);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}


}
