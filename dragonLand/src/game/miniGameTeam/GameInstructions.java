package game.miniGameTeam;

import java.awt.Color;

/**
 * @author Violetta Jusiega
 *
 */

import java.util.ArrayList;

//import dragonComponents.NoBorderButton;
import game.DragonLand;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;
import introScreens.Fog;

public class GameInstructions extends ClickableScreen {

	public GameInstructions(int width, int height) {
		super(width, height);
	}

	private Graphic background;
	//private Button title;
	private Button instructions;
	private String[] instructionText;
	private ArrayList<Fog> fogs;
	
	private int sequenceNumber;
	
	private Button next;
	private Button playButton;
	//private Button playGame;
	private Button exit;
	
	private NoBorderButton title;
	private NoBorderButton text1;
	private NoBorderButton text2;
	private NoBorderButton text3;
	private NoBorderButton text4;
	private NoBorderButton text5;
	private NoBorderButton text6;
	
	private Button layer;
		
	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		//sequenceNumber = 0;
		//instructionText = new String[6];
		//initiateText();
		
		int startX = (int) (DragonLand.WIDTH*0.15);
		int textWidth = (int) (DragonLand.WIDTH*0.7);
		fogs = new ArrayList<Fog>();
		background = new Graphic(0,0,DragonLand.WIDTH,DragonLand.HEIGHT,"img/sunsetBackground.jpg");
		viewObjects.add(background);

		setUpFog();
		
//		layer = new Button((int) (DragonLand.WIDTH*0.1), (int) (DragonLand.HEIGHT*0.1), (int) (DragonLand.WIDTH*0.8),  (int) (DragonLand.HEIGHT*0.77), "", DragonLand.LIGHT_PINK, null);
//		viewObjects.add(layer);
		
		Graphic back = new Graphic((int) (DragonLand.WIDTH*0.1), (int) (DragonLand.HEIGHT*0.1), (int) (DragonLand.WIDTH*0.8),  (int) (DragonLand.HEIGHT*0.77), "img/opacityPink.png");
		viewObjects.add(back);
		
		
		title = new NoBorderButton(startX,75,textWidth,50, "Star Catch",null,null);
		title.setSize(40);
		viewObjects.add(title);
		
		text1 = new NoBorderButton(startX,150,textWidth,50, "Welcome to the feeding ground",DragonLand.LIGHT_PINK,null);
		text1.setSize(25);
		viewObjects.add(text1);
		
		text2 = new NoBorderButton(startX,200,textWidth,50, "Here your hungry dragon will eat the falling stars",DragonLand.LIGHT_PINK,null);
		text2.setSize(25);
		viewObjects.add(text2);
		
		text3 = new NoBorderButton(startX,250,textWidth,50, "*Stars are the best source of nutrients for dragons*",DragonLand.LIGHT_PINK,null);
		text3.setSize(25);
		viewObjects.add(text3);
		
		text4 = new NoBorderButton(startX,300,textWidth,50, "The more stars you earn the more coins you get",DragonLand.LIGHT_PINK,null);
		text4.setSize(25);
		viewObjects.add(text4);
		
		text5 = new NoBorderButton(startX,350,textWidth,50, "Don't let a star fall or you lose a life",DragonLand.LIGHT_PINK,null);
		text5.setSize(25);
		viewObjects.add(text5);
		
		text6 = new NoBorderButton(startX,400,textWidth,50, "Have fun!",DragonLand.LIGHT_PINK,null);
		text6.setSize(25);
		viewObjects.add(text6);
		
		
		ClickableGraphic playPost = new ClickableGraphic(getWidth()-250, getHeight()-200, 1.0,"img/continueSign.png");
		playPost.setAction(new Action(){
			public void act(){
				DragonLand.game.setScreen(DragonLand.miniGameScreen);
				DragonLand.miniGameScreen.startGame();
			}
		});
		viewObjects.add(playPost);
		
//		playButton = new Button((int) (DragonLand.WIDTH * 0.78), (int) (DragonLand.HEIGHT * 0.9), (int) (DragonLand.WIDTH * 0.2), 50, "Play", DragonLand.LIGHT_PINK, new Action() {
//			@Override
//			public void act() {
//				//GameScreen.tGame.initGame("img/Dragon1.png");
//				DragonLand.game.setScreen(DragonLand.miniGameScreen);
//				DragonLand.miniGameScreen.startGame();
//			}
//		});
//		
//		viewObjects.add(playButton);		
	}

	public void setUpFog(){
		Fog fog; 
		
		for(int i = -10; i < 10; i++){
			fog = new Fog((i*getWidth() / 10), 200, 500, 300, "img/introFog.png", 100);
			viewObjects.add(fog);
			fog.setY(fog.generateYPos());
			fogs.add(fog);
			fog.play();
		}
	}
	

}
