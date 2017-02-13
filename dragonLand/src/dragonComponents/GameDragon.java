package dragonComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import guiPractice.components.MovingComponent;

public class GameDragon extends MovingComponent{
	
	//must add picture and animate
	
	public GameDragon(int x, int y, int w, int h) {
		super(x, y, w, h);
		setX(x);
		setY(y);
	}

	@Override
	public void checkBehaviors() {
		
	}

	@Override
	public void drawImage(Graphics2D g) {
		g.setColor(Color.pink);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
