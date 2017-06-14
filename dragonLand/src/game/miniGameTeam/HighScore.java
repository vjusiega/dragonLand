package game.miniGameTeam;

import java.awt.Polygon;
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
import guiPractice.components.PolygonButton;
import guiPractice.components.TextArea;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Banner;
import introScreens.Fog;
/**
 * 
 * @author Tamanna
 *
 */
public class HighScore extends ClickableScreen implements MouseListener {
	
	private ArrayList<Fog> fogs;
	
	private Graphic background;
	private Graphic score;
	private Graphic coins;
	private Graphic scoreOne;
	private Graphic scoreTwo;
	private Graphic scoreThree;
	
	private static ArrayList<Integer> highScores;
		
	private static TextArea yourScore;
	private static int roundScore;	
	private static TextLabel coinsWon;
	private static TextLabel score1;
	private static TextLabel score2;
	private static TextLabel score3;
	private TextLabel coinText;
	
	public HighScore(int width, int height){
		super(width,height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		highScores = new ArrayList<Integer>();
		background = new Graphic(0, 0, getWidth(), getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		fogs = new ArrayList<Fog>();
		setUpFog();
		
		Polygon back = new Polygon();
	    back.addPoint(20, 18);
	    back.addPoint(120, 30);
	    back.addPoint(120, 60);
	    back.addPoint(20, 35);
	    back.addPoint(5, 25);
	    back.addPoint(20, 18);

	    PolygonButton backBtn = new PolygonButton( 0, DragonLand.HEIGHT-110, 150, 100, back, new Action(){
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});	    
	    viewObjects.add(backBtn);
	    
	    Polygon helpBtn = new Polygon();
	    helpBtn.addPoint(20, 18);
	    helpBtn.addPoint(130, 45);
	    helpBtn.addPoint(135, 50);
	    helpBtn.addPoint(120, 62);
	    helpBtn.addPoint(12, 40);
	    helpBtn.addPoint(20, 18);

	    PolygonButton cont = new PolygonButton(DragonLand.WIDTH - 150, DragonLand.HEIGHT-120, 150, 100, helpBtn, new Action(){
			@Override
			public void act() {
					DragonLand.game.setScreen(DragonLand.miniGameScreen);
					DragonLand.miniGameScreen.startGame();
				}
		});	    
	    viewObjects.add(cont);
		
		score = new Graphic(85, 250, 200, 150, "img/opacityPink.png");		
		viewObjects.add(score);
		yourScore = new TextArea(score.getX()+30, score.getY(), 200, 150, "");
		viewObjects.add(yourScore);
		
		coins = new Graphic(getWidth()/2 + 200, 250, 200, 150, "img/opacityPink.png");
		coinsWon = new TextLabel(coins.getX()+35, coins.getY()-100, 200, 150, "");
		viewObjects.add(coins);
		viewObjects.add(coinsWon);
		
		Graphic coinDisplay = new Graphic(DragonLand.WIDTH-155, 100, 175, 50, "img/StraightOneSign.png");		
		Graphic coin = new Graphic(DragonLand.WIDTH-35, 113, 25, 25, "img/Coin.png");	
		coinText = new TextLabel(DragonLand.WIDTH-135, 107, 175, 30, "" + DragonLand.coins);
		coinText.setColor(DragonLand.TEXT_PINK);
<<<<<<< HEAD
		coinText.setSize(20);
=======
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
>>>>>>> branch 'demo2.0' of https://github.com/katsemenova/dragonLand.git
		viewObjects.add(coinDisplay);
		viewObjects.add(coin);
		viewObjects.add(coinText);
		
		Banner b = new Banner(0, 25, 600, 171, "img/highScoreBanner.png");
		b.setX((getWidth() / 2) - (b.getWidth() / 2)); 
		viewObjects.add(b);
		
		scoreOne = new Graphic(getWidth()/2 - 200, 185, 395, 100, "img/opacityPink.png");
		viewObjects.add(scoreOne);
		score1 = new TextLabel(200, 300, 200, 350, "1.");
		score1.setText("1)");
		score1.setSize(25);
		viewObjects.add(score1);
		
		scoreTwo = new Graphic(getWidth()/2 - 200, 320, 395, 100, "img/opacityPink.png");
		viewObjects.add(scoreTwo);
		score2 = new TextLabel(200, 350, 200, 350, "2."); 
		score2.setText("2)");
		score2.setSize(25);					
		viewObjects.add(score2);
		
		scoreThree = new Graphic(getWidth()/2 - 200, 455, 395, 100, "img/opacityPink.png");
		viewObjects.add(scoreThree);
		score3 = new TextLabel(200, 400, 200, 350, "3.");
		score3.setText("3)");
		score3.setSize(25);
		viewObjects.add(score3);
		
	}

	public static void updateOnEnter() {

		//updates the score and coin values every time the high score screen is shown
				
		setRoundScore(GameScreen.getScore());
		highScores.add(GameScreen.getScore());
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
	
	public void setUpFog(){
		Fog fog; 
		for(int i = -10; i < 10; i++){
			fog = new Fog((i*getWidth() / 10), 200, 500, 300, "img/introFog.png", 100);
			addObject(fog);
			fog.setY(fog.generateYPos());
			fog.play();
		}
	}
	
	public void replayGame(){
		
		
		
	}
}
