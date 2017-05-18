package game.dragonTrading;
import game.Client;
import game.DragonLand;
import game.Server;
import game.mainScreenTeam.*;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import game.DragonLand;
import guiPractice.ClickableScreen;
import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;
import introScreens.Fog;

public class TradingScreen extends ClickableScreen implements Runnable{
	
	private Graphic background;
	private ArrayList<Dragon> myDragons;
	private ArrayList<Dragon> myDragonsToTrade;
	private ArrayList<Dragon> theirDragons;
	private TradingScreen thisScreen; 
	
	public TradingScreen(int width, int height) {
		super(width, height);
		myDragons = new ArrayList<Dragon>();
		myDragonsToTrade = new ArrayList<Dragon>();
		theirDragons = new ArrayList<Dragon>(); 
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
	
	public void displayConnectionMessage(String message){
		Button b = new Button(500, 200, 100, 100, "", Color.CYAN);
		b.setText(message);
		addObject(b);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		thisScreen = this;
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		ClickableGraphic post = new ClickableGraphic(0, getHeight()-300, 1.0,"img/oneSignLeft.png");
		post.setAction(new Action(){
			public void act(){
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
		
		Thread fogRun = new Thread(new Runnable(){
			public void run(){
				setUpFog(post);
			}
		});
		fogRun.start();
		
		
		Button b = new Button(100, 100, 100, 100, "connect", Color.BLUE);
		b.setAction(new Action(){
			public void act() {
				Thread trade = new Thread(new Runnable(){
					public void run(){
						Server s = new Server();
						s.startRunning(thisScreen);
					}
				});
				trade.start();
			
				System.out.println("I am running");
				
				Client c = new Client("127.0.0.1");	
				c.startRunning();
				System.out.println("I am runninggg");
			}			
		});
		viewObjects.add(b);
		
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
/**
	your dragons to sell
	
	dragons to buy
		users trading dragons
	
	
	
	
**/