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

import dragonComponents.XButton;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Visible;

/**
 * @author Tamanna Hussain and Violetta Jusiega
 *
 */
public class GameScreen extends ClickableScreen implements KeyListener, MouseListener{

	private XButton exit;
	private Button helpButton;
	private Graphic background;

	//private ArrayList<Star> starArray;
	//star will be its own class (made by Tamanna), we will then have an array of stars that will appear on the screen
	//private ArrayList<Dragon> dragonArray;
	//Dragon should be its own array as well, we will have an array of dragons that are on the screen

	public GameScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */

	@Override
	public void initAllObjects(ArrayList<Visible> view) {
		//int xCoord = DragonLand.x * .05; 
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("A key was typed!");	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){ 
			System.out.println("Left Arrow Key pressed");
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){ 
			System.out.println("Right Arrow Key pressed");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public KeyListener getKeyListener(){
		return this;
	}

}
