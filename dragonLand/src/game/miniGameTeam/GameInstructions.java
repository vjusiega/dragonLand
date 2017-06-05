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
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Fog;

public class GameInstructions extends ClickableScreen {

	private static final long serialVersionUID = 1L;

	public GameInstructions(int width, int height) {
		super(width, height);
	}

	private Graphic background;
	private static TextLabel title;
	private static TextLabel instructions;
	private String[] instructionText;
	private ArrayList<Fog> fogs;
	
	private int sequenceNumber;
	
	private Button next;
	private Button playButton;
	//private Button playGame;
	private Button exit;
	
//	private NoBorderButton title;
	private NoBorderButton text1;
	private NoBorderButton text2;
	private NoBorderButton text3;
	private NoBorderButton text4;
	private NoBorderButton text5;
	private NoBorderButton text6;
	
	private Button layer;
		
	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {		
		int startX = (int) (DragonLand.WIDTH*0.15);
		int textWidth = (int) (DragonLand.WIDTH*0.7);
		
		fogs = new ArrayList<Fog>();
		background = new Graphic(0,0,DragonLand.WIDTH,DragonLand.HEIGHT,"img/sunsetBackground.jpg");
		viewObjects.add(background);
		setUpFog();
		
		Graphic back = new Graphic((int) (DragonLand.WIDTH*0.1), (int) (DragonLand.HEIGHT*0.1), (int) (DragonLand.WIDTH*0.8),  (int) (DragonLand.HEIGHT*0.77), "img/opacityPink.png");
		viewObjects.add(back);
		
		
		title = new TextLabel((int) (DragonLand.WIDTH/2 - 100), (int) (DragonLand.HEIGHT*0.15), (int) (DragonLand.WIDTH*0.8),  50, "hello");
		instructions = new TextLabel((int) (DragonLand.WIDTH*0.15), (int) (DragonLand.HEIGHT*0.1), (int) (DragonLand.WIDTH*0.8), 150, "lalalla");
		title.setText("Star Catch");
		title.setColor(Color.white);
		title.setSize(40);
		instructions.setText("Welcome to the feeding ground!"
				+ "Here your hungry dragon will eat the falling stars"
				+ "Stars are the best source of nutrients for dragons"
				+ "The more stars you earn the more coins you get"
				+ "Don't let a star fall or you lose a life"
				+ "Have fun!");
		
		instructions.setColor(Color.white);
		instructions.setSize(25);
		viewObjects.add(title);
		viewObjects.add(instructions);
		ClickableGraphic playPost = new ClickableGraphic(getWidth()-250, getHeight()-200, 1.0,"img/continueSign.png");
		playPost.setAction(new Action(){
			public void act(){
				DragonLand.game.setScreen(DragonLand.miniGameScreen);
				DragonLand.miniGameScreen.startGame();
			}
		});
		viewObjects.add(playPost);
	}
	
	public void updateOnEnter() {
//		title.setText("Star Catch");
//		title.setSize(40);
//		title.setColor(Color.white);
//		instructions.setText("Welcome to the feeding ground! Here your hungry dragon will eat the falling stars \n"
//				+ "*Stars are the best source of nutrients for dragons* \n"
//				+ "The more stars you earn the more coins you get \n"
//				+ "Don't let a star fall or you lose a life \n"
//				+ "Have fun!");
//		instructions.setSize(25);
//		instructions.setColor(Color.white);
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
