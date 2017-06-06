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
		
		Graphic back = new Graphic((int) (DragonLand.WIDTH*0.1), (int) (DragonLand.HEIGHT*0.1), (int) (DragonLand.WIDTH*0.8),  (int) (DragonLand.HEIGHT*0.77), "img/opacityPink.png");
		viewObjects.add(back);
		
		title = new TextLabel((int) (DragonLand.WIDTH/2 - 100), (int) (DragonLand.HEIGHT*0.15), (int) (DragonLand.WIDTH*0.8),  50, "hello");
		title.setText("Star Catch");
		title.setColor(Color.white);
		title.setSize(40);
		viewObjects.add(title);
		
		text1 = new TextLabel((int) (DragonLand.WIDTH/2 - 50), (int) (DragonLand.HEIGHT*0.1), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text1.setText("Welcome to the feeding ground!");
		text1.setColor(Color.white);
		text1.setSize(25);		
		viewObjects.add(text1);
		
		text2 = new TextLabel((int) (DragonLand.WIDTH/2 - 50), (int) (DragonLand.HEIGHT*0.1), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text2.setText("Here your hungry dragon will eat the falling stars");
		text2.setColor(Color.white);
		text2.setSize(25);		
		viewObjects.add(text2);
		
		text3 = new TextLabel((int) (DragonLand.WIDTH/2 - 50), (int) (DragonLand.HEIGHT*0.1), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text3.setText("Stars are the best source of nutrients for dragons");
		text3.setColor(Color.white);
		text3.setSize(25);		
		viewObjects.add(text3);
		
		text4 = new TextLabel((int) (DragonLand.WIDTH/2 - 50), (int) (DragonLand.HEIGHT*0.1), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text4.setText("The more stars you earn the more coins you get");
		text4.setColor(Color.white);
		text4.setSize(25);	
		viewObjects.add(text4);
		
		text5 = new TextLabel((int) (DragonLand.WIDTH/2 - 50), (int) (DragonLand.HEIGHT*0.1), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text5.setText("Don't let a star fall or you lose a life");
		text5.setColor(Color.white);
		text5.setSize(25);	
		viewObjects.add(text5);
		
		text6 = new TextLabel((int) (DragonLand.WIDTH/2 - 100), (int) (DragonLand.HEIGHT*0.1), 
				      (int) (DragonLand.WIDTH*0.8), 150, "");
		text6.setText("Have fun!");
		text6.setColor(Color.white);
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
