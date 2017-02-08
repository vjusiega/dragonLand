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
	
	private TextLabel title;
	private ColoredBox test;
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
		title = new TextLabel((getWidth()/2)-90, 60, 150, 20, "High Scores");
		test = new ColoredBox((getWidth()/2)-100,45,150,50);
		background = new Background(0,0, getWidth(), getHeight());
		back = new Button(40, 100, 100, 50, "Go Back", DragonLand.DARKER_NUDE, new Action(){

			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
			
		});
		viewObjects.add(background);
		viewObjects.add(test);
		viewObjects.add(title);
		viewObjects.add(back);
	}

}
