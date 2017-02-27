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
 * @author Tamanna Hussain and Violetta Jusiega
 *
 */
public class GameScreen extends ClickableScreen implements KeyListener {

	private Button exit;
	private Button helpButton;
	private static Button scoreButton;
	private Button highScoreButton;
	private Graphic background;

	private static ArrayList<Star1> starArray;
	private static int score;
	
	private boolean paused;
	
	public GameScreen(int width, int height) {
		super(width, height);
	}
	
	@Override
	public void initAllObjects(ArrayList<Visible> view) {
		//initial score is 0 and it should count the number of stars caught
		score = 0;
		paused = false; 

		background = new Graphic(0,0,getWidth(),getHeight(),"img/forest.jpg");
		viewObjects.add(background);
		
		exit = new Button(30, 50, 40, 40, "X", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.highscoreScreen);
			}
		});

		helpButton = new Button(getWidth()-75, getHeight()-75, 50, 50, "?", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				pause();
				displayInstructions();
			}

		});
		
		//displays the score on screen
		scoreButton = new Button(getWidth()-150, 50, 120, 50, "Score: " + score, DragonLand.DARKER_NUDE, null);
		
		//allows the player to see the high scores
		highScoreButton = new Button(getWidth()-150, 105, 120, 50, "High Scores", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.highscoreScreen);
			}
		});
		
		view.add(exit);
		view.add(helpButton);
		view.add(highScoreButton);
		view.add(scoreButton);
		
		starArray = new ArrayList<Star1>();
		view.add(addStar());
		
		GameVioletta.addDragon("img/dragon1.png");
		
		for(Dragon d : GameVioletta.getDragonArray()){
			view.add(d);
		}
	}
	
	public static ArrayList<Star1> getStarArray(){
		return starArray;
	}
	
	public Star1 addStar(){
		int xPos = (int) (Math.random()*GameScreen.getWidth()); 
		int yPos = 10;
		int starH = 100;
		int starW = 100;
		Star1 starImage = new Star1(xPos, yPos, starW, starH, this);
		addObject(starImage);
		return starImage;
		
		/*
		Random rand = new Random();
		int val = rand.nextInt(4) + 1;
		int xPos = (int) (Math.random()*GameScreen.getWidth()); 
		int yPos = 10;
		int starH = 100;
		int starW = 100;
		Star1 starImage = new Star1(xPos, yPos, starW, starH, this);
		if (val == 1) { 
			//--1/4 of the time
			int chance = (int) ((Math.random() * 10) + 1);
			for(int i = 0; i < chance; i++){
				System.out.println("star" + i + "at" + xPos);
				addObject(starImage);
			}
		} else { 
			//--3/4 of the time
			int chance = (int) ((Math.random() * 8) + 1);
			for(int i = 0; i < chance; i++){
				System.out.println("star star" + i + "at" + xPos);
				addObject(starImage);
			}
		}
		
		//try to constantly produce stars and randomize number of seconds that the stars fall
		return starImage;
		*/
	}

	public void removeStar(Star1 star){
		System.out.println("here");
		starArray.remove(star);
		//If the y-value of the star reaches a certain point
		//This method will remove the star from the screen
		//return(star);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		addObject(addStar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){ 
			GameVioletta.changeDragonPos(-5);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			GameVioletta.changeDragonPos(5);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			addObject(GameVioletta.addDragon("img/dragon1.png"));
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			remove(GameVioletta.removeDragon());
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			System.out.println("The space button");
			pause();
		}
	}

	@Override
	public void keyReleased(KeyEvent e){	
	
	}

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
		scoreButton.setText("Score: " + score);
	}
	
	/*
	 * Methods for instructions
	 */
	
	//Pause is not working
	public void pause() {
		for(Star1 s: starArray){
			s.setVy(0);
		}
	}
	
	public void displayInstructions(){
		
	}
	
	public void unpause(){
		
	}
}
