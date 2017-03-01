/**
 * 
 */
package game;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dragonComponents.Background;
import dragonComponents.CurvedButton;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Visible;

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
	private Button help;
	private Button helpBox;
	private ArrayList<Integer> highScores;
	private ArrayList<Button> buttons;
	private int tCoins;
	
	//fields for individual button scores
	private Button score1;
	private Button score2;
	private Button score3;
	
	
	
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		tCoins = 10;
		buttons = new ArrayList<Button>();
		highScores = new ArrayList<Integer>();
		highScores.add(GameScreen.getScore());
		sortScores(highScores);
		//helpBox = new Button()
		help = new Button(getWidth()-90, 580, 40, 40, "?", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				
			}

		});
		yourScore = new Button(345, 183, 300, 60, "Your score: " + GameScreen.getScore(), DragonLand.DARKER_NUDE, null);
		yourScore.setSize(30);
		layerOne = new CurvedButton(50,75,getWidth()-100, getHeight()-100,null,DragonLand.LIGHT_NUDE, null);
		layerTwo = new Button(100, 160, getWidth()-200, getHeight()-225, null, DragonLand.BRIGHT_PINK, null);
		title = new Button((getWidth()/2)-125, 85, 250, 65, "High Scores", DragonLand.DARKER_NUDE, null);
		title.setSize(40);
		background = new Background(0,0, getWidth(), getHeight());
		coinsWon = new Button(345, 480, 300, 60, "Coins Won: " + getCoins(GameScreen.getScore()), DragonLand.DARKER_NUDE, null);
		coinsWon.setSize(30);
		scores = new Button(295,262,400,200,null, DragonLand.DARKER_NUDE,null);
		totalCoins = new Button(100,87,180,55,"Total Coins: " + (getCoins(GameScreen.getScore()) + tCoins), DragonLand.DARKER_NUDE,null);
		clearScores = new Button(795, 33, 150, 40, "Clear Scores?", DragonLand.DARKER_NUDE, new Action(){
			
			@Override
			public void act(){
				viewObjects.remove(score1);
				viewObjects.remove(score2);
				viewObjects.remove(score3);
				yourScore.setText("Scores Cleared");
			}
		});
		back = new Button(50,37, 40, 40, "X", DragonLand.DARKER_NUDE, new Action(){	

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
		viewObjects.add(help);
		createButtons();
		printButtons(buttons);
		viewObjects.add(clearScores);
	}

	public void sortScores(ArrayList<Integer> scores){
		Comparator comparator = Collections.reverseOrder();
		Collections.sort(scores,comparator);
		highScores = scores;
	}
	
	public void createButtons(){
		if(highScores.size() == 0){
			return;
		}
		if(highScores.size() == 1){
			Button temp = new Button(440,337,125,50, "1) " + highScores.get(0).toString(),DragonLand.LIGHT_NUDE,null);
			temp.setSize(30);
			score1 = temp;
			buttons.add(score1);
			return;
		}
		if(highScores.size() == 2){
			for(int i = 0; i < highScores.size(); i++){
				Button temp = new Button(440,305+(60*i),125,50,(i+1) + ") " + highScores.get(i).toString(),DragonLand.LIGHT_NUDE,null);
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
				Button temp = new Button(440,277+(60*i),125,50,(i+1) + ") " + highScores.get(i).toString(),DragonLand.LIGHT_NUDE,null);
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