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
public class GameScreen extends Screen implements KeyListener{

	private XButton exit;
	private Button helpButton;
	private Button scoreButton;
	private Graphic background;
	private ArrayList<Star> starArray;
	private int score;
	private boolean running;
	
	private double vy; //the vertical velocity
	private int posx; //the actual x-coordinate of the object
	private int posy;
	private long moveTime;//time when the image last moved
	
	public static final int REFRESH_RATE = 20;
	//Tamanna's fields
	//star will be its own class (made by Tamanna), we will then have an array of stars that will appear on the screen
	
	
	public GameScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initObjects(ArrayList<Visible> view) {
		score = 0;
		background = new Graphic(0,0,getWidth(),getHeight(),"img/forest.jpg");
		viewObjects.add(background);
		
		exit = new XButton(30, 50, 40, 40, "", null, new Action() {
			@Override
			public void act() {
				// TODO Auto-generated method stub
				DragonLand.game.setScreen(DragonLand.miniGameScreen);
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
		
		//temporary
		int randomNum = (int)(Math.random() * 5 + 1);
		for (int i = 0; i < 5; i++){
			view.add(new Star(100, 100, 100, 100));
		}
		//run();
		
		GameVioletta.addDragon("img/dragon1.png");
		
		for(GameDragon d : GameVioletta.getDragonArray()){
			view.add(d);
		}

		//System.out.println(GameVioletta.getDragonArray().size());
		
		//stars = new ArrayList<Star>();
	}
	
	public void addStar(){
	
	}
	
	public void removeStar(){
		//If the y-value of the star reaches a certain point
		//This method will remove the star from the screen
	}
	
	
	public void updateStars(Graphics2D g) {
		
		long currentTime = System.currentTimeMillis();
		//calculates time since last move
		long difference = currentTime - moveTime;
		if(difference >= REFRESH_RATE){
			//an update is happening, so update moveTime
			moveTime = currentTime;
			//calculate new position
			posy += vy*(double)difference/REFRESH_RATE;
			//set only the location on the screen
			//NOT the actual position
			setY((int)posy);	
		}
		drawImage(g);
	}
	
	private void drawImage(Graphics2D g) {
		Graphic star = new Graphic(posx, posy, "img/star.png"); 
	}

	private void checkBehaviors() {
		if(posy > 100){
			//setY((getY() + getVy()));
		}
	}

	public int getX(){
		posx = (int) (Math.random()*GameScreen.getWidth());
		return posx; 
	}
	
	public void setX(int x){
		posx = x;
	}
	
	public void setY(int y){
		posy = y;
	}
	
	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void play() {
		if(!running){
			Thread go = new Thread((Runnable) this);
			go.start();
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		setScore(score + 1);
		if(score == 10 || score == 20){
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
	
	public int getScore(){
		return score; 
	}
}
