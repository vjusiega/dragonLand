/**
 * 
 */
package game.miniGameTeam;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dragonComponents.Background;
//<<<<<<< HEAD
import game.DragonLand;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;
import introScreens.Banner;

/**
 * @author Veeraj
 *
 */
public class HighScoreScreen extends ClickableScreen implements MouseListener{
	
	//fields for layout
	private Button title;
	private Graphic background;
	private Button back;
	private CurvedButton layerOne;
	private Button layerTwo;
	private static Button yourScore;
	private static int roundScore;
	private static Button coinsWon;
	private Button scores;
	private static Button totalCoins;
	private Button clearScores;
	private Button help;
	private Button helpBox;
	private static ArrayList<Integer> highScores;
	private static ArrayList<Button> buttons;
	private int tCoins;
	private Button replayGame;
	
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
	private static Button score1;
	private static Button score2;
	private static Button score3;
	
	
	
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		//initializing fields
		createHelpDialog();

		tCoins = DragonLand.coins;
		buttons = new ArrayList<Button>();
		highScores = new ArrayList<Integer>();
		roundScore =0;
		replayGame = new Button(getWidth()-365, getHeight()-565, getWidth()-850, getHeight()-600, "Replay Game", DragonLand.DARKER_NUDE, new Action(){
			@Override
			public void act(){
				DragonLand.game.setScreen(DragonLand.gameInstructionsScreen);
			}
		});
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
		score1 = new Button(440,265,125,50, "1) -",DragonLand.LIGHT_NUDE,null);
		score1.setSize(30);
		score2 = new Button(440,327,125,50,"2) --",DragonLand.LIGHT_NUDE,null);
		score2.setSize(30);
		score3 = new Button(440,389,125,50,"3) --",DragonLand.LIGHT_NUDE,null);
		score3.setSize(30);
		helpBox = new Button(getWidth()-900, getHeight()-490, getWidth()-200, getHeight()-225, null, DragonLand.BRIGHT_PINK, null);
		helpBox.setSize(40);
		yourScore = new Button(getWidth()-655, getHeight()-467, getWidth()-700, getHeight()-580, "Your score: " + GameScreen.getScore(), DragonLand.DARKER_NUDE, null);
		yourScore.setSize(30);
		layerOne = new CurvedButton(getWidth()-965,getHeight()-590,getWidth()-75, getHeight()-75,null,DragonLand.LIGHT_NUDE, null);
		layerTwo = new Button(getWidth()-900, getHeight()-490, getWidth()-200, getHeight()-225, null, DragonLand.BRIGHT_PINK, null);
		title = new Button((getWidth()/2)-125, getHeight()-575, getWidth()-750, getHeight()-575, "High Scores", DragonLand.DARKER_NUDE, null);
		title.setSize(40);
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		coinsWon = new Button(getWidth()-655, getHeight()-170, getWidth()-700, getHeight()-580, "Coins Won: " + getCoins(GameScreen.getScore()), DragonLand.DARKER_NUDE, null);
		coinsWon.setSize(30);
		scores = new Button(getWidth()-705,getHeight()-388,getWidth()-600,getHeight()-440,null, DragonLand.DARKER_NUDE,null);
		totalCoins = new Button(getWidth()-880,getHeight()-570,getWidth()-820,getHeight()-585,"Total Coins: " + (getCoins(GameScreen.getScore()) + tCoins), DragonLand.DARKER_NUDE,null);
		clearScores = new Button(getWidth()-205, getHeight()-565, getWidth()-850, getHeight()-600, "Clear Scores?", DragonLand.DARKER_NUDE, new Action(){
			
			@Override
			public void act(){
				//removes all scores from viewObjects as to clear the scores from the display
				viewObjects.remove(score1);
				viewObjects.remove(score2);
				viewObjects.remove(score3);
				yourScore.setText("Scores Cleared");
			}
		});
		back = new Button(50,75, 40, 40, "X", DragonLand.DARKER_NUDE, new Action(){	
			//goes back to the home screen
			@Override
			public void act() {
				GameScreen.isNotHome = false;
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
			
		});
		//elements are added into viewObjects in such an order than they seem layered
		viewObjects.add(background);
		
		Banner b = new Banner(0, 50, 600, 171, "img/highScoreBanner.png");
		b.setX((getWidth() / 2) - (b.getWidth() / 2)); 
		viewObjects.add(b);
		
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
		viewObjects.add(score1);
		viewObjects.add(score2);
		viewObjects.add(score3);
		viewObjects.add(replayGame);
	}
	
	public static void updateOnEnter(){
		//updates the score and coin values everytime the highscore screen is shown
		setRoundScore(GameScreen.getScore());
		highScores.add(roundScore);
		totalCoins.setText("Total Coins: " + (DragonLand.coins+getCoins(GameScreen.getScore())));
		DragonLand.coins+=getCoins(GameScreen.getScore());
		yourScore.setText("Your Score: " + roundScore);
		coinsWon.setText("Coins Won: " + getCoins(GameScreen.getScore()));
		sortScores(highScores);
		for(int i = 0; i < 3; i++)
		{
			if(i < highScores.size())
			{
				if(i == 0)
					score1.setText("1) " + highScores.get(0));
				if(i == 1 && highScores.get(1) != 0)
					score2.setText("2) " + highScores.get(1));
				if(i == 2 && highScores.get(2) != 0)
					score3.setText("3) " + highScores.get(2));
			}
		}
	}
	
	public void createHelpDialog(){
		//creates the text and layout for the help screen
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
	
	public static void sortScores(ArrayList<Integer> scores){
		//sorts high score array in decreasing order
		Comparator comparator = Collections.reverseOrder();
		Collections.sort(scores,comparator);
		highScores = scores;
	}
	
	public void createButtons(){
		//creates highscore buttons based on the length of highscore array and adds them to button array
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
	
	public static void setRoundScore(int i){
		roundScore=i;
	}
	
	public void printButtons(ArrayList<Button> buttons){
		for(int i = 0; i < buttons.size(); i++){
			viewObjects.add(buttons.get(i));
		}
	}
	
	public static int getCoins(int score){
		//converts score value into coin value
		if(score == 0){
			return 0;
		}
		return (((score*5)/2)+14);
	}
}