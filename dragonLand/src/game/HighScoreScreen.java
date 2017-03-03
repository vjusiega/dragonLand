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
import dragonComponents.NoBorderButton;
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
	
	//fields for help dialog
	private String text1;
	private NoBorderButton btext1;
	private String text2;
	private NoBorderButton btext2;
	private String text3;
	private NoBorderButton btext3;
	private String text4;
	private NoBorderButton btext4;
	private ArrayList<NoBorderButton> helparray;
	
	//fields for individual button scores
	private Button score1;
	private Button score2;
	private Button score3;
	
	
	
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		createHelpDialog();

		tCoins = DragonLand.coins;
		buttons = new ArrayList<Button>();
		highScores = new ArrayList<Integer>();
		highScores.add(GameScreen.getScore());
//		highScores.add(400);
//		highScores.add(32);
//		highScores.add(0);
		sortScores(highScores);
		//helpBox = new Button()
		help = new Button(getWidth()-105, getHeight()-75, getWidth()-960, getHeight()-600, "?", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				if(viewObjects.contains(helpBox)){
					for(NoBorderButton b: helparray){
						viewObjects.remove(b);
					}
					viewObjects.remove(helpBox);
				}
				else{
					viewObjects.add(helpBox);
					for(NoBorderButton b: helparray){
						viewObjects.add(b);
					}
				}
			}

		});
		helpBox = new Button(getWidth()-900, getHeight()-490, getWidth()-200, getHeight()-225, null, DragonLand.BRIGHT_PINK, null);
		helpBox.setSize(40);
		yourScore = new Button(getWidth()-655, getHeight()-467, getWidth()-700, getHeight()-580, "Your score: " + GameScreen.getScore(), DragonLand.DARKER_NUDE, null);
		yourScore.setSize(30);
		layerOne = new CurvedButton(getWidth()-965,getHeight()-590,getWidth()-75, getHeight()-75,null,DragonLand.LIGHT_NUDE, null);
		layerTwo = new Button(getWidth()-900, getHeight()-490, getWidth()-200, getHeight()-225, null, DragonLand.BRIGHT_PINK, null);
		title = new Button((getWidth()/2)-125, getHeight()-575, getWidth()-750, getHeight()-575, "High Scores", DragonLand.DARKER_NUDE, null);
		title.setSize(40);
		background = new Background(0,0, getWidth(), getHeight());
		coinsWon = new Button(getWidth()-655, getHeight()-170, getWidth()-700, getHeight()-580, "Coins Won: " + getCoins(GameScreen.getScore()), DragonLand.DARKER_NUDE, null);
		coinsWon.setSize(30);
		scores = new Button(getWidth()-705,getHeight()-388,getWidth()-600,getHeight()-440,null, DragonLand.DARKER_NUDE,null);
		totalCoins = new Button(getWidth()-880,getHeight()-570,getWidth()-820,getHeight()-585,"Total Coins: " + (getCoins(GameScreen.getScore()) + tCoins), DragonLand.DARKER_NUDE,null);
		clearScores = new Button(getWidth()-205, getHeight()-565, getWidth()-850, getHeight()-600, "Clear Scores?", DragonLand.DARKER_NUDE, new Action(){
			
			@Override
			public void act(){
				viewObjects.remove(score1);
				viewObjects.remove(score2);
				viewObjects.remove(score3);
				yourScore.setText("Scores Cleared");
			}
		});
		back = new Button(50,75, 40, 40, "X", DragonLand.DARKER_NUDE, new Action(){	

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

	public void createHelpDialog(){
		helparray = new ArrayList<NoBorderButton>();
		text1 = "Your top 3 scores will be displayed here";
		btext1 = new NoBorderButton(getWidth()-870,getHeight()-480,getWidth()-250, getHeight()-540, text1, DragonLand.BRIGHT_PINK,null);
		btext1.setSize(30);
		
		text2 = "The coins won and total coins are also displayed";
		btext2 = new NoBorderButton(getWidth()-870,getHeight()-380,getWidth()-250, getHeight()-540, text2, DragonLand.BRIGHT_PINK,null);
		btext2.setSize(30);
		
		text3 = "Click clear scores to reset the list";
		btext3 = new NoBorderButton(getWidth()-870,getHeight()-280,getWidth()-250, getHeight()-540, text3, DragonLand.BRIGHT_PINK,null);
		btext3.setSize(30);
		
		text4 = "Click the X to return to the main screen";
		btext4 = new NoBorderButton(getWidth()-870,getHeight()-180,getWidth()-250, getHeight()-540, text4, DragonLand.BRIGHT_PINK, null);
		btext4.setSize(30);
		
		helparray.add(btext1);
		helparray.add(btext2);
		helparray.add(btext3);
		helparray.add(btext4);
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
			Button temp = new Button(440,327,125,50, "1) " + highScores.get(0).toString(),DragonLand.LIGHT_NUDE,null);
			temp.setSize(30);
			score1 = temp;
			buttons.add(score1);
			return;
		}
		if(highScores.size() == 2){
			for(int i = 0; i < highScores.size(); i++){
				Button temp = new Button(440,395+(60*i),125,50,(i+1) + ") " + highScores.get(i).toString(),DragonLand.LIGHT_NUDE,null);
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