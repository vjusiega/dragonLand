package game.miniGameTeam;

import java.awt.Color;
import java.awt.Polygon;

/**
 * @author Violetta Jusiega and Tamanna Hussain
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
import guiPractice.components.PolygonButton;
import guiPractice.components.TextArea;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Banner;
import introScreens.Fog;

public class GameInstructions extends ClickableScreen {

	private static final long serialVersionUID = 1L;

	public GameInstructions(int width, int height) {
		super(width, height);
	}

	private static TextArea instructions;
	private Graphic background;
	private static TextLabel title;
	private static TextLabel text1;
	private static TextLabel text2;
	private static TextLabel text3;
	private static TextLabel text4;
	private static TextLabel text5;
	private static TextLabel text6;
	private ArrayList<Fog> fogs;
	
	private int sequenceNumber;
	
	private Button next;
	private Button playButton;
	private Button exit;
	
	private Button layer;
		
	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {		
		int startX = (int) (DragonLand.WIDTH*0.15);
		int textWidth = (int) (DragonLand.WIDTH*0.7);
		
		fogs = new ArrayList<Fog>();
		background = new Graphic(0,0,DragonLand.WIDTH,DragonLand.HEIGHT,"img/sunsetBackground.jpg");
		viewObjects.add(background);
		setUpFog();
		
		//Graphic back = new Graphic((int) (DragonLand.WIDTH*0.1), (int) (DragonLand.HEIGHT*0.2), (int) (DragonLand.WIDTH*0.8),  (int) (DragonLand.HEIGHT*0.65), "img/opacityPink.png");
		//Graphic back = new Graphic((int) (DragonLand.WIDTH*0.1), 25, (int) (DragonLand.WIDTH*0.8),  (int) (DragonLand.HEIGHT * 0.9), "img/opacityPink.png");

		//viewObjects.add(back);
		
		Banner b = new Banner(0, 50, 600, 171, "img/gameBanner.png");
		b.setX((getWidth() / 2) - (b.getWidth() / 2)); 
		viewObjects.add(b);
		
		/*
		instructions = new TextArea((int) (DragonLand.WIDTH/2 - 290), (int) (DragonLand.HEIGHT*0.1 + 100), 
				      (int) (DragonLand.WIDTH*0.6), 260, "");
		instructions.setText("Welcome to the feeding ground! "
				+ "Here your hungry dragon will eat the falling stars. "
				+ "Stars are the best source of nutrients for dragons. "
				+ "The more stars you earn the more coins you get. "
				+ "Don't let a star fall or you lose a life! "
				+ "Have fun!");
		instructions.setColor(Color.white);
		instructions.setSize(16);		
		viewObjects.add(instructions);
		*/
		
		text1 = new TextLabel((int) (DragonLand.WIDTH/2 - 180), (int) (DragonLand.HEIGHT*0.1 - 15), (int) (DragonLand.WIDTH*0.8), 150, "");
		text1.setText("Welcome to the feeding ground!");
		text1.setColor(Color.white);
		text1.setSize(16);		
		viewObjects.add(text1);
		
		text2 = new TextLabel((int) (DragonLand.WIDTH/2 - 295), (int) (DragonLand.HEIGHT*0.1 + 30), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text2.setText("Here your hungry dragon will eat the falling stars.");
		text2.setColor(Color.white);
		text2.setSize(16);		
		viewObjects.add(text2);
		
		text3 = new TextLabel((int) (DragonLand.WIDTH/2 - 300), (int) (DragonLand.HEIGHT*0.1 + 75), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text3.setText("Stars are the best source of nutrients for dragons.");
		text3.setColor(Color.white);
		text3.setSize(16);		
		viewObjects.add(text3);
		
		text4 = new TextLabel((int) (DragonLand.WIDTH/2 - 285), (int) (DragonLand.HEIGHT*0.1 + 115), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text4.setText("The more stars you earn the more coins you get.");
		text4.setColor(Color.white);
		text4.setSize(16);	
		viewObjects.add(text4);
		
		text5 = new TextLabel((int) (DragonLand.WIDTH/2 - 225), (int) (DragonLand.HEIGHT*0.1 + 155), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text5.setText("Don't let a star fall or you lose a life!");
		text5.setColor(Color.white);
		text5.setSize(16);	
		viewObjects.add(text5);
		
		text6 = new TextLabel((int) (DragonLand.WIDTH/2 - 50), (int) (DragonLand.HEIGHT*0.1 + 195), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text6.setText("Have fun!");
		text6.setColor(Color.white);
		text6.setSize(25);
		viewObjects.add(text6);
		Graphic playPost = new ClickableGraphic(DragonLand.WIDTH - 150, DragonLand.HEIGHT-120, .6,"img/continueSign.png");
//		playPost.setAction(new Action(){
//			public void act(){
//				DragonLand.game.setScreen(DragonLand.miniGameScreen);
//				DragonLand.miniGameScreen.startGame();
//			}
//		});
		viewObjects.add(playPost);
		Graphic post = new Graphic(0, getHeight()-150, 0.6,"img/backSign.png");
		viewObjects.add(post);
		addPostButtons();
	}
	private void addPostButtons() {
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
			}});
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
//	    PolygonButton continue = new PolygonButton(DragonLand.WIDTH - 150, DragonLand.HEIGHT-120, 150, 100, helpBtn, new Action(){
//			@Override
//			public void act() {
//				DragonLand.game.setScreen(DragonLand.miniGameScreen);
//				DragonLand.miniGameScreen.startGame();
//			
//				}
//			});
	    
	   viewObjects.add(cont);
	}
	public void updateOnEnter() {
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
