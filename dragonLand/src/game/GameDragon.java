package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import guiPractice.components.MovingComponent;

public class GameDragon extends MovingComponent{
	
	//this field should eventually be replaced with a field from the main class
		private int screenWidth;
	
	private static int xSpeed;
	
	
	public int getxSpeed(){
		return xSpeed;
	}

	public GameDragon(int x, int y, int w, int h) {
		super(x, y, w, h);
		setX(x);
		setY(y);
	}

	@Override
	public void checkBehaviors() {
		if(getX() > screenWidth + xSpeed || getX() < 0){			
			setVx(0);
		}
		
	}

	@Override
	public void drawImage(Graphics2D g) {
		g.setColor(Color.pink);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
	}
	
	
//	
//	public static void setXSpeed(boolean positive){
//		if(positive){
//			xSpeed = Math.abs(xSpeed);
//		}
//		else{
//			xSpeed = -1 * Math.abs(xSpeed);
//		}
//	}
	
	
	/**
	 * Key controls
	 */
	
		
}
