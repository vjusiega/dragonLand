/**
 * 
 */
package game;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	/**
	 * 
	 */
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		title = new Button((getWidth()/2)-60, 40, 120, 50, "High Scores", DragonLand.DARKER_NUDE, null);
		background = new Background(0,0, getWidth(), getHeight());
		back = new Button(getWidth()-110-(getWidth()*2/100),(getHeight()*5/100),  120,  50, "Go Back", DragonLand.DARKER_NUDE, new Action(){

			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
			
		});
		viewObjects.add(background);
		viewObjects.add(title);
		viewObjects.add(back);
	}

}
