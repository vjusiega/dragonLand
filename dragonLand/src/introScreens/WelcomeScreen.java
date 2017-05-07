package introScreens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.DragonLand;
import game.mainScreenTeam.Dragon;
import game.miniGameTeam.GameVioletta;
import guiPractice.ClickableScreen;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Graphic;
import guiPractice.components.GraphicMovingComponent;
import guiPractice.components.Visible;

public class WelcomeScreen extends ClickableScreen{
	
	private ArrayList<Dragon> dragons;
	private Graphic background;

	public WelcomeScreen(int width, int height) {
		super(width, height);
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		dragons = new ArrayList<Dragon>();
		viewObjects.add(setUpDragons(1));
		viewObjects.add(setUpDragons(3));
		
		GraphicMovingComponent fog = new Fog(0, 5*getHeight()/7, 100, 100, "img/introFog.png", 2, 0);
		viewObjects.add(fog);
		
		//changeScreen();
		
		
	}
	
//	private void changeScreen() {
//		try {
//			Thread.sleep(10000);
//			DragonLand.game.setScreen(DragonLand.homeScreen);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//	}

	public Dragon setUpDragons(int drag){
		String imgSrc = "img/dragon" + drag + ".png";
		int dragonHeight = getHeight()/8;
		int dragonWidth = (int) (dragonHeight * 0.8);
		int xPos;
		if(drag == 1){
			xPos = getWidth()/4;
		}else{
			xPos = (3*getWidth()/4) - dragonWidth;
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
	
	public static int getDragonY(){
		return getHeight()/4;
	}

}
