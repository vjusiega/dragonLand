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
 * @author Jenniber
 *
 */
public class HighScore extends ClickableScreen implements MouseListener {
	
	private Graphic background;
	private static ArrayList<Integer> highScores;
	private static int roundScore;
	private static TextLabel yourScore;
	private static TextLabel totalCoins;
	private static TextLabel score1;
	private static TextLabel score2;
	private static TextLabel score3;
	
	public HighScore(int width, int height){
		super(width,height);
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		highScores = new ArrayList<Integer>();
		
		
		
		Banner b = new Banner(0, 50, 600, 171, "img/highScoreBanner.png");
		b.setX((getWidth() / 2) - (b.getWidth() / 2)); 
		
		viewObjects.add(b);;
		viewObjects.add(background);
	}

	public static void updateOnEnter() {
		//updates the score and coin values everytime the highscore screen is shown
				setRoundScore(GameScreen.getScore());
				highScores.add(roundScore);
				totalCoins.setText("Total Coins: " + (DragonLand.coins+getCoins(GameScreen.getScore())));
				DragonLand.coins+=getCoins(GameScreen.getScore());
				yourScore.setText("Your Score: " + roundScore);
				//coinsWon.setText("Coins Won: " + getCoins(GameScreen.getScore()));
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

	private static void sortScores(ArrayList<Integer> highScores2) {
		// TODO Auto-generated method stub
		
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
