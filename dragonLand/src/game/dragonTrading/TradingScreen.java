package game.dragonTrading;

import java.util.ArrayList;

import game.DragonLand;
import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;
import introScreens.Fog;

public class TradingScreen extends Screen {
	
	private Graphic background;
	private ArrayList<Dragon> myDragons;
	private ArrayList<Dragon> myDragonsToTrade;
	private ArrayList<Dragon> theirDragons;
	
	public TradingScreen(int width, int height) {
		super(width, height);
		myDragons = new ArrayList<Dragon>();
		myDragonsToTrade = new ArrayList<Dragon>();
		theirDragons = new ArrayList<Dragon>();
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects2) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		ClickableGraphic post = new ClickableGraphic(0, getHeight()-300, 1.0,"img/oneSignLeft.png");
		post.setAction(new Action(){
			public void act(){
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
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
			fog.play();
		}
	}
	

}
/**
	your dragons to sell
	
	dragons to buy
		users trading dragons
	
	
	
	
**/
