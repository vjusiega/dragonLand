package game.miniGameTeam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import guiPractice.components.AnimatedComponent;
import guiPractice.components.MovingComponent;

public class GameDragon extends AnimatedComponent{
	
	private String imgSrc;
	
	//must add picture and animate
	
	public GameDragon(int x, int y, int w, int h, String imgSrc) {
		super(x, y, w, h);
		setX(x);
		setY(y);
		this.imgSrc=imgSrc;
	}

	@Override
	public void checkBehaviors() {
		if(true){
			if(currentFrame==2)
				currentFrame=0;
		}
	}

	@Override
	public void drawImage(Graphics2D g){
		super.drawImage(g);
	}
}
