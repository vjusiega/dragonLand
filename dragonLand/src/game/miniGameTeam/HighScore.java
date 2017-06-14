package game.miniGameTeam;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dragonComponents.Background;
import game.DragonLand;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Banner;
/**
 * 
 * @author Tamanna
 *
 */
public class HighScore extends ClickableScreen implements MouseListener {
	
	private Graphic background;
	
	private static TextLabel back;
	private static TextLabel replayGame;
	
	private static ArrayList<Integer> highScores;
		
	private static TextLabel yourScore;
	private static int roundScore;	
	private static TextLabel coinsWon;
	private static TextLabel totalCoins;
	private static TextLabel score1;
	private static TextLabel score2;
	private static TextLabel score3;
	private TextLabel coinText;
	
	public HighScore(int width, int height){
		super(width,height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		
		highScores = new ArrayList<Integer>();
		coinsWon = new TextLabel(200, 200, 300, 300, "img/opacityPink.jpg");
		yourScore = new TextLabel(500, 500, 300, 300, "img/opacityPink.jpg");
		
		Graphic coinDisplay = new Graphic(DragonLand.WIDTH-155, 100, 175, 50, "img/StraightOneSign.png");		
		Graphic coin = new Graphic(DragonLand.WIDTH-35, 113, 25, 25, "img/Coin.png");	
		coinText = new TextLabel(DragonLand.WIDTH-135, 107, 175, 30, "" + DragonLand.coins);
		System.out.println("coins: " + DragonLand.coins);
		coinText.setColor(DragonLand.TEXT_PINK);
		coinText.setSize(25);
		
		Banner b = new Banner(0, 0, 600, 171, "img/highScoreBanner.png");
		b.setX((getWidth() / 2) - (b.getWidth() / 2)); 
		
		score1 = new TextLabel(200, 300, 200, 350, "img/opacityPink.jpg");	
		coinText.setSize(25);
		score2 = new TextLabel(200, 350, 200, 350, "img/opacityPink.jpg"); 
		coinText.setSize(25);					
		score3 = new TextLabel(200, 400, 200, 350, "img/opacityPink.jpg");
		coinText.setSize(25);
		
		viewObjects.add(background);
		viewObjects.add(b);
		viewObjects.add(coinDisplay);
		viewObjects.add(coin); 
		viewObjects.add(coinText);
		viewObjects.add(coinsWon);
		viewObjects.add(yourScore);
		viewObjects.add(score1);
		viewObjects.add(score2);
		viewObjects.add(score3);
	}

	public static void updateOnEnter() {

		//updates the score and coin values every time the high score screen is shown
				
		//setRoundScore(GameScreen.getScore());
		highScores.add(GameScreen.getScore());
		totalCoins.setText("Total Coins: " + (DragonLand.coins+getCoins(GameScreen.getScore())));
		DragonLand.coins += getCoins(GameScreen.getScore());
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
	
	public static void sortScores(ArrayList<Integer> scores){
		//sorts high score array in decreasing order
		Comparator comparator = Collections.reverseOrder();
		Collections.sort(scores,comparator);
		highScores = scores;
	}

	private static int getCoins(int score) {
		if(score == 0){
			return 0;
		}
		return (((score*5)/2)+14);
	}

	private static void setRoundScore(int score) {
		roundScore = score;
	}
}
