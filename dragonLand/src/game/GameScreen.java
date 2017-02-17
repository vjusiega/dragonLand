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

import dragonComponents.GameDragon;
import dragonComponents.XButton;
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
public class GameScreen extends Screen implements KeyListener {

	private XButton exit;
	private Button helpButton;
	private Button scoreButton;
	private Graphic background;

	private ArrayList<Star> starArray;
	private static int score;
	
	public GameScreen(int width, int height) {
		super(width, height);
	}
	

	@Override
	public void initObjects(ArrayList<Visible> view) {
		score = 100;

		background = new Graphic(0,0,getWidth(),getHeight(),"img/forest.jpg");
		viewObjects.add(background);
		
		exit = new XButton(30, 50, 40, 40, "", null, new Action() {
			@Override
			public void act() {
				// TODO Auto-generated method stub
				DragonLand.game.setScreen(DragonLand.highscoreScreen);
			}
		});

		helpButton = new Button(getWidth()-75, getHeight()-75, 50, 50, "?", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.miniGameScreen);
				//helpScreen
			}

		});
		
		scoreButton = new Button(getWidth()-150, 50, 125, 50, "Score: " + score, DragonLand.DARKER_NUDE, null);
		
		view.add(exit);
		view.add(helpButton);
		view.add(scoreButton);
		
		starArray = new ArrayList<Star>();
		view.add(new Star1(100, 100, 100, 100, this));
		
		GameVioletta.addDragon("img/dragon1.png");
		
		for(GameDragon d : GameVioletta.getDragonArray()){
			view.add(d);
		}

		//System.out.println(GameVioletta.getDragonArray().size());
		
		
	}
	
	public void addStar(){
		Star starImage = new Star(100, 100, 100, 100);
		
	}
	
	public void removeStar(Star1 star){
		//If the y-value of the star reaches a certain point
		//This method will remove the star from the screen
	}

	@Override
	public void keyTyped(KeyEvent e) {
		setScore(score + 1);
		if(score == 105 || score == 205){
			addObject(GameVioletta.addDragon("img/dragon1.png"));
		}
		scoreButton.setText("Score: " + score);
		System.out.print("im here");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){ 
			GameVioletta.changeDragonPos(-5);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			GameVioletta.changeDragonPos(5);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			addObject(GameVioletta.addDragon("img/dragon1.png"));
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			remove(GameVioletta.removeDragon());
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
	
	public void setScore(int x){
		score = x;
	}
	
	public static int getScore(){
		return score; 
	}
}
