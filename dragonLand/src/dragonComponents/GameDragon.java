package dragonComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import guiPractice.components.AnimatedComponent;
import guiPractice.components.Graphic;
import guiPractice.components.MovingComponent;

public class GameDragon extends Graphic{
	
	//private String imgSrc;
	
	//must add picture and animate
	
	public GameDragon(int x, int y, int w, int h, String imgSrc) {
		super(10, 10, 200, 200, imgSrc);
		System.out.println("star");
		//setX(getRandomX());
		setY(y);
//		vy = 0;
	}

}
