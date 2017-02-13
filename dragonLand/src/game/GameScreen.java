package game;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import dragonComponents.GameDragon;
import dragonComponents.XButton;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Visible;
//import miniGames.GameDragon;
//import miniGames.GameVioletta;

/**
 * @author Tamanna Hussain and Violetta Jusiega
 *
 */
public class GameScreen extends ClickableScreen implements KeyListener{

	private XButton exit;
	private Button helpButton;
	private Graphic background;
	
	private static int score; 
	
	
	//Tamanna's fields
	//private ArrayList<Star> starArray;
	//star will be its own class (made by Tamanna), we will then have an array of stars that will appear on the screen
	
	
	public GameScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void initAllObjects(ArrayList<Visible> view) {
	
		GameVioletta.addDragon();
		
		background = new Graphic(0,0,getWidth(),getHeight(),"img/forest.jpg");
		viewObjects.add(background);
		
		System.out.println("This is working");
		exit = new XButton(30, 50, 40, 40, "", null, new Action() {
			@Override
			public void act() {
				// TODO Auto-generated method stub
				DragonLand.game.setScreen(DragonLand.miniGameScreen);
			}
		});

		helpButton = new Button(getWidth()-100, 50, 50, 50, "?", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.miniGameScreen);
				//helpScreen
			}

		});
		
		view.add(exit);
		view.add(helpButton);
		view.add(new Star(100, 100, 100, 100));
		
		
		//System.out.println(GameVioletta.dragonArray.size());
		for(GameDragon d : GameVioletta.getDragonArray()){
			view.add(d);
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//This only responds to when a letter is typed not a key, so 
		//we can add a popup that says "Please use the arrow keys"
		//but we would have to pause the entire thing and idk how to do that
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){ 
			GameVioletta.changeDragonPos(-5);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			GameVioletta.changeDragonPos(5);
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


}