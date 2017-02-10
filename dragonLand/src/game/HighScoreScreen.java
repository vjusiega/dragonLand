/**
 * 
 */
package game;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	private ArrayList highScores;
	/**
	 * 
	 */
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		highScores = new ArrayList<Integer>();
		highScores.add(50);
		highScores.add(400);
		highScores.add(32);
		highScores.add(0);
		sortScores(highScores);
		yourScore = new Button(345, 190, 300, 50, "Your score: " + GameScreen.getScore(), DragonLand.DARKER_NUDE, null);
		yourScore.setSize(30);
		layerOne = new CurvedButton(50,70,getWidth()-100, getHeight()-95,null,DragonLand.LIGHT_NUDE, null);
		layerTwo = new Button(100, 150, getWidth()-200, getHeight()-225, null, DragonLand.BRIGHT_PINK, null);
		title = new Button((getWidth()/2)-125, 80, 250, 60, "High Scores", DragonLand.DARKER_NUDE, null);
		title.setSize(40);
		background = new Background(0,0, getWidth(), getHeight());
		coinsWon = new Button(345, 465, 300, 60, "Coins Won: WON", DragonLand.DARKER_NUDE, null);
		coinsWon.setSize(30);
		scores = new Button(295,252,400,200,printScores(highScores), DragonLand.DARKER_NUDE,null);
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

	public void sortScores(ArrayList<Integer> scores){
		Comparator comparator = Collections.reverseOrder();
		Collections.sort(scores,comparator);
		System.out.println(scores);
	}
	
	public void printScores(ArrayList<Integer> sortedScores){
		for(int i = 0; i < sortedScores.size(); i++){
			String score = "";
			Button temp = new Button(20+(100*i), 20+(100*i), 50, 50, score+=sortedScores.get(i), Color.black, null);
			viewObjects.add(temp);		
		}
		
//		String printedScores = "";
//		String placeholder = "";
//		for(int i = 0; i < 10; i++){
//			placeholder += " ";
//		}
//		for(int i = 0; i < sortedScores.size(); i++){
//			printedScores += (i+1) + ") " + sortedScores.get(i) + placeholder;
//		}
//		System.out.println(printedScores);
//		return printedScores;
	}
}
