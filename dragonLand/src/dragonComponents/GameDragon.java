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
import guiPractice.components.Visible;

public class GameDragon extends DragonGameGraphic implements Visible{
	
	//private String imgSrc;
	
	//must add picture and animate
	
	public GameDragon(int x, int y, int w, int h, String imgSrc) {
		super(x, y, w, h, imgSrc);
	}

	

}
