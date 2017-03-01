package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dragonComponents.Dragon;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Button;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import guiPractice.sampleGames.MouseFollower;

/**
 * @author Kat 
 * @author Jenniber
 *
 */
public class HomeScreen extends ClickableScreen implements Runnable{

	private Graphic background;

	public HomeScreen(int width, int height) {
		super(width, height);
		Thread play = new Thread(this);
		play.start();
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/Grassland.png");
		viewObjects.add(background);
		HomeKat katCode = new HomeKat(viewObjects, getWidth(), getHeight());
	}

	
	@Override
	public void run() {
	}

}