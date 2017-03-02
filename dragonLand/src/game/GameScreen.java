package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import dragonComponents.Dragon;
import dragonComponents.gameDragonInterface;
import guiPractice.ClickableScreen;
import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Visible;
//import miniGames.GameDragon;
//import miniGames.GameVioletta;

/**
 * @author Tamanna Hussain
 *
 */
public class GameScreen extends ClickableScreen implements KeyListener {

	private Button exit;
	private static Button scoreDisplay;
	private Button highScoreButton;
	private Graphic background;
	private int time;
	//private int numOfStars;
	
	private static ArrayList<Star1> starArray;
	private static int score;
	
	public static GameScreen tGame;
	
	public GameScreen(int width, int height) {
		super(width, height);
		tGame = this;
	}
	
	@Override
	public void initAllObjects(ArrayList<Visible> view) {
		//initial score is 0 and it should count the number of stars caught
		score = 0;
		time = 2000;
		starArray = new ArrayList<Star1>();
	
		background = new Graphic(0,0,getWidth(),getHeight(),"img/forest.jpg");
		viewObjects.add(background);
		
		exit = new Button(30, 50, 40, 40, "X", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.highscoreScreen);
				stopGame();
			}
		});
		
		scoreDisplay = new Button(getWidth()-150, 50, 120, 50, "Score: " + score, DragonLand.DARKER_NUDE, null);
		
		view.add(exit);
		view.add(scoreDisplay);
		
		GameVioletta vGameObject = new GameVioletta();	
		//initDragonsOnScreen("img/dragon1.png");
	}
	
	protected void stopGame() {
		
		GameVioletta.vGame.setPlaying(false);
		
		ArrayList<Dragon> dragonArray = GameVioletta.vGame.getDragonArray();
		if(dragonArray.size() != 0){
			for(Dragon d: dragonArray){
				remove(d);
			}
			
			GameVioletta.vGame.eraseDragons();
		}
		
		if(starArray.size() != 0){
			for(Star1 s: starArray){
				remove(s);
			}
		}
		starArray = new ArrayList<Star1>();
		
		//GameVioletta.vGame.stillPlaying(true);
	}

	public void startGame(){
		Thread start = new Thread(new Runnable() {
			@Override
			public void run() {
				fallingStars();
			}
		});
		start.start();
	}
	
	public static ArrayList<Star1> getStarArray(){
		return starArray;
	}
	
	public int randomX(){
		//80 through getWidth()-175
		int max = getWidth()-175;
		int min = 80;
		int xPos = (int) (Math.random()*(max - min) + min);
		return xPos;
	}
	
	public void addStar(){
		//adds one star object to the screen and the array
		int yPos = 0;
		int starH = 100;
		int starW = 100;
		Star1 starImage = new Star1(randomX(), yPos, starW, starH, this);
		starImage.play();
		starArray.add(starImage);
		addObject(starImage);
	}

	public void removeStar(Star1 star){
		starArray.remove(star);
		remove(star);
	}
	
	public void fallingStars(){
		GameVioletta.vGame.setPlaying(true);
		try{
			while(GameVioletta.vGame.getPlaying()){
			//setTime();
			Thread.sleep(time);
			addStar();
			}
			DragonLand.game.setScreen(DragonLand.highscoreScreen);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	public void setTime(){
		if (score >= 5 && score < 10){
			time = 10;
		}
		if (score >= 10 && score < 15){
			time = 1000;
		}
		if (score >= 15 && score < 20){
			time = 500;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){ 
			GameVioletta.vGame.changeDragonPos(-7);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			GameVioletta.vGame.changeDragonPos(7);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			addObject(GameVioletta.vGame.addDragon("img/dragon1.png"));
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			remove(GameVioletta.vGame.removeDragon());
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e){	
	}

	@Override
	public KeyListener getKeyListener(){
		return this;
	}
	
	/*
	 * Getter and setter for score
	 */
	
	public static void setScore(int x){
		score = x;
	}
	
	public static int getScore(){
		return score; 
	}
	
	public static void setScoreDisplay(){
		scoreDisplay.setText("Score: " + score);
	}

	public void initGame(String imgSrc){
		//GameVioletta.vGame.setPlaying(true);
		score = 0;
		addObject(GameVioletta.vGame.addDragon(imgSrc));
		GameVioletta.vGame.setPlaying(true);
	}
}
