/**
 * 
 */
package game;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dragonComponents.Background;
import dragonComponents.CurvedButton;
import dragonComponents.XButton;

import java.awt.Color;
import java.awt.Font;
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
	private CurvedButton layerOne;
	private Button layerTwo;
	private Button yourScore;
	private Button coinsWon;
	private Button scores;
	private Button totalCoins;
	/**
	 * 
	 */
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		yourScore = new Button(345, 175, 300, 50, "Your score: " + GameScreen.getScore(), DragonLand.DARKER_NUDE, null);
		yourScore.setSize(30);
		layerOne = new CurvedButton(50,70,getWidth()-100, getHeight()-110,null,DragonLand.LIGHT_NUDE, null);
		layerTwo = new Button(100, 150, getWidth()-200, getHeight()-240, null, DragonLand.BRIGHT_PINK, null);
		title = new Button((getWidth()/2)-125, 40, 250, 60, "High Scores", DragonLand.DARKER_NUDE, null);
		title.setSize(40);
		background = new Background(0,0, getWidth(), getHeight());
		coinsWon = new Button(345, 450, 300, 60, "Coins Won: WON", DragonLand.DARKER_NUDE, new Action(){
			@Override
			public void act(){
				
			}
		});
		coinsWon.setSize(30);
		scores = new Button(295,237,400,200,"Scores", DragonLand.DARKER_NUDE,null);
		totalCoins = new Button(700,87,180,50,"Total Coins: COINS", DragonLand.DARKER_NUDE,null);
		back = new Button(50,50, 40, 40, "X", DragonLand.DARKER_NUDE, new Action(){	

			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
			
		});
		viewObjects.add(background);
		viewObjects.add(layerOne);
		viewObjects.add(layerTwo);
		viewObjects.add(yourScore);
		viewObjects.add(title);
		viewObjects.add(back);
		viewObjects.add(coinsWon);
		viewObjects.add(scores);
		viewObjects.add(totalCoins);
	}

}
