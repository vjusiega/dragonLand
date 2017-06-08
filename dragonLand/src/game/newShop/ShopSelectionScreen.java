package game.newShop;

import java.awt.Color;
import java.awt.Polygon;
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
import guiPractice.components.PolygonButton;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Banner;
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
		
		Banner b = new Banner(0, 50, 600, 171, "img/shopBanner.png");
		b.setX((getWidth() / 2) - (b.getWidth() / 2)); 
		viewObjects.add(b);
		
//		dragons = new ArrayList<Dragon>();
//		viewObjects.add(setUpDragons(1));
//		viewObjects.add(setUpDragons(3));
		
		Graphic post = new ClickableGraphic(0, getHeight()-150, 0.6,"img/backSign.png");
		setUpFog(post);
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
				HomeKat.dragonsOnScreen();
				GameScreen.isNotHome = false;
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}});
	    
	    viewObjects.add(backBtn);
		
		
		ClickableGraphic buyBox = new ClickableGraphic(getWidth(), getHeight(), 1.1, "img/whiteBox.png", 0.25, 0.5);
		buyBox.setAction(new Action(){
			public void act() {
				((ShopScreen)DragonLand.newShopScreen).enterShop();
				DragonLand.game.setScreen(DragonLand.newShopScreen);
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
				DragonLand.game.setScreen(DragonLand.incubatorScreen);
			}
		});
		viewObjects.add(eggBox);
		
		setUpTradeBoxAnimation();
		
	}
	

	public void setUpFog(Graphic post){
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
	
	public int getDragonY(){
		return getHeight()/4;
	}
	
	public void setUpTradeBoxAnimation() {
		ClickableGraphic tradeBox = new ClickableGraphic(getWidth(), getHeight(), 1.1, "img/tradeBox.png", 0.75, 0.5);
		tradeBox.setAction(new Action(){
			public void act(){
				((ShopScreen)DragonLand.newShopScreen).enterTradeSelection();
				DragonLand.game.setScreen(DragonLand.newShopScreen);
			}
		});
		viewObjects.add(tradeBox);
		
		Dragon tradeDrag2 = new Dragon(0,0, (int) (tradeBox.getWidth() *0.5), (int) (tradeBox.getHeight() * 0.45), "img/dragon3.png");
		tradeDrag2.centerDragon(tradeBox.getX() + (tradeBox.getWidth() / 2), tradeBox.getX() + tradeBox.getWidth(), tradeBox.getY(), tradeBox.getY() + (tradeBox.getHeight() /2));
		tradeDrag2.play();
		tradeDrag2.setDirection(1);
		viewObjects.add(tradeDrag2);
		
		Dragon tradeDrag1 = new Dragon(0,0, (int) (tradeBox.getWidth() *0.6), (int) (tradeBox.getHeight() * 0.55), "img/dragon4.png");
		tradeDrag1.centerDragon(tradeBox.getX(), tradeBox.getX() + (int)(tradeBox.getWidth() * 0.75), tradeBox.getY() + (tradeBox.getHeight() /2), tradeBox.getY() + tradeBox.getHeight());
		tradeDrag1.play();
		tradeDrag1.setDirection(2);
		viewObjects.add(tradeDrag1);
		
	}

}
	