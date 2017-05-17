package introScreens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.DragonLand;
import game.mainScreenTeam.Dragon;
import game.miniGameTeam.GameVioletta;
import game.miniGameTeam.HighScoreScreen;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.GraphicMovingComponent;
import guiPractice.components.Visible;

public class WelcomeScreen extends ClickableScreen{
	
	private ArrayList<Dragon> dragons;
	private ArrayList<Fog> fogs;
	private Graphic background;

	public WelcomeScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		dragons = new ArrayList<Dragon>();
		fogs = new ArrayList<Fog>();
		viewObjects.add(setUpDragons(1));
		viewObjects.add(setUpDragons(3));
		
		ClickableGraphic post = new ClickableGraphic(getWidth()-250, getHeight()-300, 1.0,"img/oneSignLeft.png");
		post.setAction(new Action(){
			public void act(){
				stopThreads();
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
		
//		Button exit = new Button(getWidth()-135, getHeight()-60, 120, 50, "Continue...", DragonLand.DARKER_NUDE, new Action() {
//			@Override
//			public void act() {
//				DragonLand.game.setScreen(DragonLand.homeScreen);
//			}
//		});
//		viewObjects.add(exit);
		
		setUpFog(post);
	
	}

	public void setUpFog(ClickableGraphic post){
		Fog fog; 
		
		for(int i = -10; i < 10; i++){
			fog = new Fog((i*getWidth() / 10), 200, 500, 300, "img/introFog.png", 100);
			if(i == 8){
				viewObjects.add(post);
			}
			viewObjects.add(fog);
			fog.setY(fog.generateYPos());
			fogs.add(fog);
			fog.play();
		}
	}

	public Dragon setUpDragons(int drag){
		String imgSrc = "img/dragon" + drag + ".png";
		int dragonHeight = getHeight()/8;
		int dragonWidth = (int) (dragonHeight * 0.8);
		int xPos;
		if(drag == 1){
			xPos = getWidth()/4 - dragonWidth;
		}else{
			xPos = (3*getWidth()/4) - dragonWidth * 2;
		}
		
		int yPos = (-1)*dragonHeight;
		Dragon d = new Dragon(xPos, yPos, dragonHeight, dragonWidth, imgSrc, 15, 0.7);
		dragons.add(d);
		d.setDragonAnimation(d, imgSrc);
		d.setX(xPos);
		d.setY(yPos);
		d.setInitialY(getHeight()/4);
		d.setDirection(4);
		d.play();
		
		return d;
	}
	
	public int getDragonY(){
		return getHeight()/4;
	}
	
	public void stopThreads() {
		for(int i = 0; i < dragons.size(); i++){
			dragons.get(i).setRunning(false);
		}
		for(int i = 0; i < fogs.size(); i++){
			fogs.get(i).setRunning(false);
		}
	}

}
