package game.shopScreen;

import java.awt.Color;
import java.util.ArrayList;

import game.DragonLand;
import game.mainScreenTeam.Dragon;
import game.mainScreenTeam.HomeKat;
import game.miniGameTeam.GameScreen;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Fog;
import introScreens.WelcomeScreen;

/*
 * Violetta
 */
public class ShopSelectionScreen extends ClickableScreen{

	private ArrayList<Dragon> dragons;
	private Graphic background;
	
	public ShopSelectionScreen(int width, int height) {
		super(width, height);
	}
	
	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		dragons = new ArrayList<Dragon>();
		viewObjects.add(setUpDragons(1));
		viewObjects.add(setUpDragons(3));
		
		ClickableGraphic post = new ClickableGraphic(0, getHeight()-250, 1.0,"img/backSign.png");
		post.setAction(new Action(){
			public void act(){
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
		
		setUpFog(post);
		
		ClickableGraphic buyBox = new ClickableGraphic(getWidth(), getHeight(), 1.1, "img/whiteBox.png", 0.25, 0.5);
		buyBox.setAction(new Action(){
			public void act() {
				DragonLand.game.setScreen(DragonLand.buyScreen);
			}
		});
		viewObjects.add(buyBox);
		Dragon displayDragon = new Dragon(0, 0, (int) (buyBox.getWidth() *0.70), (int) (buyBox.getHeight() * 0.65), "img/dragon1.png");
		displayDragon.centerDragon(buyBox.getX(), buyBox.getX() + buyBox.getWidth(), buyBox.getY(), buyBox.getY() + buyBox.getHeight());
		displayDragon.play();
		viewObjects.add(displayDragon);
		
		
		ClickableGraphic eggBox = new ClickableGraphic(getWidth(), getHeight(), 1.1, "img/whiteBox.png", 0.5, 0.5);
		eggBox.setAction(new Action(){
			public void act(){
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
		ClickableGraphic tradeBox = new ClickableGraphic(getWidth(), getHeight(), 1.1, "img/tradeBox.png", 0.75, 0.5);
		viewObjects.add(eggBox);
		viewObjects.add(tradeBox);
		
		Dragon tradeDrag2 = new Dragon(0,0, (int) (tradeBox.getWidth() *0.5), (int) (tradeBox.getHeight() * 0.45), "img/dragon2.png");
		tradeDrag2.centerDragon(tradeBox.getX() + (tradeBox.getWidth() / 2), tradeBox.getX() + tradeBox.getWidth(), tradeBox.getY(), tradeBox.getY() + (tradeBox.getHeight() /2));
		tradeDrag2.play();
		tradeDrag2.setDirection(1);
		viewObjects.add(tradeDrag2);
		
		Dragon tradeDrag1 = new Dragon(0,0, (int) (tradeBox.getWidth() *0.6), (int) (tradeBox.getHeight() * 0.55), "img/dragon6.png");
		tradeDrag1.centerDragon(tradeBox.getX(), tradeBox.getX() + (int)(tradeBox.getWidth() * 0.75), tradeBox.getY() + (tradeBox.getHeight() /2), tradeBox.getY() + tradeBox.getHeight());
		tradeDrag1.play();
		tradeDrag1.setDirection(2);
		viewObjects.add(tradeDrag1);
		
		
		
	}
	
	
	
	public void setUpFog(ClickableGraphic post){
		Fog fog; 
		
		for(int i = -5; i < 5; i++){
			fog = new Fog((i*getWidth() / 10), 200, 500, 300, "img/introFog.png", 100);
			if(i == 3){
				viewObjects.add(post);
			}
			viewObjects.add(fog);
			fog.setY(fog.generateYPos());
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
		d.setInitialY(getHeight()/9);
		d.setDirection(4);
		d.play();
		
		return d;
	}
	
	public int getDragonY(){
		return getHeight()/4;
	}

}
	