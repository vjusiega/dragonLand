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
	private boolean running;

	private static ArrayList<Star1> starArray;
	private static int score;
	
	private boolean paused;
	
	public GameScreen(int width, int height) {
		super(width, height);
//		running = true;
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
		//view.add(addStar());
		fallingStars();
		
		//get dragon image from either Jen or Kat here
		GameVioletta vGameObject = new GameVioletta("img/dragon1.png");
		
		for(Dragon d : GameVioletta.vGame.getDragonArray()){
			view.add(d);
		}
	}
	
	public static ArrayList<Star1> getStarArray(){
		return starArray;
	}
	
	public Star1 addStar(){
		int yPos = 0;
		int starH = 100;
		int starW = 100;
		Star1 starImage = new Star1(randomX(), yPos, starW, starH, this);
		starImage.play();
		addObject(starImage);
		return starImage;
		
		/*
		while(running){
			Random rand = new Random();
			int val = rand.nextInt(4) + 1;
			//int xPos = (int) (Math.random()*GameScreen.getWidth()); 
			int yPos = 0;
			int starH = 100;
			int starW = 100;
			Star1 starImage = new Star1(randomX(), yPos, starW, starH, this);
			//addObject(starImage);
			starImage.play();
			try{
				Thread.sleep(10);
				if (val == 1) { 
					//--1/4 of the time
					int num = (int) ((Math.random() * 5) + 1);
					for(int i = 0; i < num; i++){
						System.out.println("star" + i + "at" + randomX());
						addObject(starImage);
					}
				} else { 
					//--3/4 of the time
					int num = (int) ((Math.random() * 3) + 1);
					System.out.print(num);
					for(int i = 0; i < num; i++){
						System.out.println("star star" + i + "at" + randomX());
						addObject(starImage);
					}
				}
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		*/
	}

	public void removeStar(Star1 star){
		System.out.println("here");
		starArray.remove(star);
		remove(star);
		//If the y-value of the star reaches a certain point
		//This method will remove the star from the screen
		//return(star);
	}
	
	public void fallingStars(){
		try{
			Thread.sleep(1000);
			addStar();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public int randomX(){
		int xPos = (int) (Math.random()*GameScreen.getWidth());
		return xPos;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		addObject(addStar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){ 
			GameVioletta.vGame.changeDragonPos(-5);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			GameVioletta.vGame.changeDragonPos(5);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			addObject(GameVioletta.vGame.addDragon("img/dragon1.png"));
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			remove(GameVioletta.vGame.removeDragon());
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
		running = false;
		for(Star1 s: starArray){
			s.setVy(0);
		}
	}
	
	public void displayInstructions(){
		
	}
	
	public void unpause(){
		
	}
}
