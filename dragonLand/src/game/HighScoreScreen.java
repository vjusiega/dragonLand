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
	private Button clearScores;
	private ArrayList<Integer> highScores;
	private ArrayList buttons;
	
	//fields for individual button scores
	private Button score1;
	private Button score2;
	private Button score3;
	/**
	 * 
	 */
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		buttons = new ArrayList<Button>();
		highScores = new ArrayList<Integer>();
		highScores.add(GameScreen.getScore());
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
		coinsWon = new Button(345, 465, 300, 60, "Coins Won: " + getCoins(GameScreen.getScore()), DragonLand.DARKER_NUDE, null);
		coinsWon.setSize(30);
		scores = new Button(295,252,400,200,null, DragonLand.DARKER_NUDE,null);
		totalCoins = new Button(100,87,180,50,"Total Coins: COINS", DragonLand.DARKER_NUDE,null);
		clearScores = new Button(800, 50, 150, 40, "Clear Scores?", DragonLand.DARKER_NUDE, new Action(){
			
			@Override
			public void act(){
				viewObjects.remove(score1);
				viewObjects.remove(score2);
				viewObjects.remove(score3);
			}
		});
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
		createButtons();
		printButtons(buttons);
		viewObjects.add(clearScores);
		System.out.println(viewObjects);
		
	}

	public void sortScores(ArrayList<Integer> scores){
		Comparator comparator = Collections.reverseOrder();
		Collections.sort(scores,comparator);
		highScores = scores;
		System.out.println(scores);
	}
	
	public void createButtons(){
		if(highScores.size() == 0){
			return;
		}
		if(highScores.size() == 1){
			Button temp = new Button(440,327,125,50, "1) " + highScores.get(0).toString(),DragonLand.LIGHT_NUDE,null);
			temp.setSize(30);
			score1 = temp;
			buttons.add(score1);
			return;
		}
		if(highScores.size() == 2){
			for(int i = 0; i < highScores.size(); i++){
				Button temp = new Button(440,295+(60*i),125,50,(i+1) + ") " + highScores.get(i).toString(),DragonLand.LIGHT_NUDE,null);
				temp.setSize(30);
				if(i == 0){
					score1 = temp;
					buttons.add(score1);
				}
				if(i == 1){
					score2 = temp;
					buttons.add(score2);
				}
			}
			return;
		}
		if(highScores.size() >= 3){
			for(int i = 0; i < 3; i++){
				Button temp = new Button(440,267+(60*i),125,50,(i+1) + ") " + highScores.get(i).toString(),DragonLand.LIGHT_NUDE,null);
				temp.setSize(30);
				if(i == 0){
					score1 = temp;
					buttons.add(score1);
				}
				if(i == 1){
					score2 = temp;
					buttons.add(score2);
				}
				if(i == 2){
					score3 = temp;
					buttons.add(score3);
				}
			}			
			return;
		}
	}
	
	public void printButtons(ArrayList<Button> buttons){
		for(int i = 0; i < buttons.size(); i++){
			viewObjects.add(buttons.get(i));
		}
	}
	
	public int getCoins(int score){
		return (((score*5)/2)+14);
	}
}
