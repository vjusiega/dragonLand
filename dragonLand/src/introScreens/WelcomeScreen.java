package introScreens;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.DragonLand;
import game.Sound;
import game.mainScreenTeam.Dragon;
import game.miniGameTeam.GameScreen;
import game.miniGameTeam.GameVioletta;
import game.miniGameTeam.HighScoreScreen;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.GraphicMovingComponent;
import guiPractice.components.PolygonButton;
import guiPractice.components.Visible;

public class WelcomeScreen extends ClickableScreen{
	
	private ArrayList<Dragon> dragons;
	private ArrayList<Fog> fogs;
	private Graphic background;

	public WelcomeScreen(int width, int height) {
		super(width, height);
		Sound.AMBIANCE.loop();

	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		Banner b = new Banner(0, -171, 600, 171, "img/dragonLandBanner.png");
		b.setBannerHeight((getHeight() / 4) - 100); 
		//b.setWidth(700);
		//b.setHeight(200);
		b.setX((getWidth() / 2) - (b.getWidth() / 2)); 
		b.play();
		viewObjects.add(b);
		
		dragons = new ArrayList<Dragon>();
		fogs = new ArrayList<Fog>();
		viewObjects.add(setUpDragons(1));
		viewObjects.add(setUpDragons(3));

		
		Graphic post = new ClickableGraphic(getWidth()-250, getHeight()-200, 1.0,"img/continueSign.png");
		
		setUpFog(post);
		Polygon next = new Polygon();
		next.addPoint(25, 10);
		next.addPoint(200, 50);
		next.addPoint(220, 65);
		next.addPoint(190, 83);
		next.addPoint(20, 55);
		next.addPoint(25, 10);

	    PolygonButton nextBtn = new PolygonButton(DragonLand.WIDTH-250, DragonLand.HEIGHT-185, 230, 100, next, new Action(){
			@Override
			public void act() {
				stopThreads();
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
	    viewObjects.add(nextBtn);
	}

	public void setUpFog(Graphic post){
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
		int dragonHeight = getHeight()/6;
		int dragonWidth = (int) (dragonHeight * 0.75);
		int xPos;
// 		if(drag == 1){
// 			xPos = getWidth()/4 - dragonWidth;
// 		}else{
// 			xPos = (3*getWidth()/4) - dragonWidth * 2;
// 		}
		
		int yPos = (-1)*dragonHeight;
		Dragon d = new Dragon(0, 0, dragonHeight, dragonWidth, imgSrc, 15, 0.7);
		if(drag == 1){
			d.placeDragonOnXLine(getWidth(), 0.25);
		}else{
			d.placeDragonOnXLine(getWidth(), 0.75);
		}
		dragons.add(d);
		d.setDragonAnimation(d, imgSrc);
		//d.setX(xPos);
		d.setY(yPos);
		d.setInitialY((getHeight()/4) - 75);
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
