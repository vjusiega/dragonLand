/**
 * 
 */
package game;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dragonComponents.Background;
import dragonComponents.XButton;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Visible;
import guiPractice.components.TextLabel;

/**
 * @author Veeraj
 *
 */
public class HighScoreScreen extends ClickableScreen implements MouseListener{
	
	private Button title;
	private Background background;
	private Button back;
	private Button layerOne;
	private Button layerTwo;
	/**
	 * 
	 */
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		layerOne = new Button(50,70,getWidth()-100, getHeight()-110,null,DragonLand.LIGHT_NUDE, null);
		layerTwo = new Button(100, 150, getWidth()-200, getHeight()-240, null, DragonLand.BRIGHT_PINK, null);
		title = new Button((getWidth()/2)-125, 40, 250, 60, "High Scores", DragonLand.DARKER_NUDE, null);
		title.setSize(40);
		background = new Background(0,0, getWidth(), getHeight());
		back = new Button(30,40,  40,  40, "X", DragonLand.DARKER_NUDE, new Action(){

			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
			
		});
		viewObjects.add(background);
		viewObjects.add(layerOne);
		viewObjects.add(layerTwo);
		viewObjects.add(title);
		viewObjects.add(back);
	}

}
