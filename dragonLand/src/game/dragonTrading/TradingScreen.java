package game.dragonTrading;
import game.DragonLand;

import game.mainScreenTeam.*;
import game.newShop.ShopScreen;

import java.awt.Color;
import java.awt.Polygon;
import java.io.IOException;
import java.util.ArrayList;

import game.DragonLand;
import guiPractice.ClickableScreen;
import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.PolygonButton;
import guiPractice.components.Visible;
import introScreens.Fog;

public class TradingScreen extends ClickableScreen implements Runnable{
	
	private Graphic background;
	private Dragon myDragon;
	private String theirDragon;
	private ArrayList<Dragon> myDragonsToTrade;
	private ArrayList<Dragon> theirDragons;
	private TradingScreen thisScreen; 
	
	public void setTheirDragon(String name){
		theirDragon = name;
	}
	
	public TradingScreen(int width, int height) {
		super(width, height);
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
	
	public void setMyDragon(Dragon d){
		myDragon = d;
	}
	
	public void displayConnectionMessage(String message){
		Button b = new Button(500, 200, 300, 300, "", Color.CYAN);
		b.setText(message);
		addObject(b);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		//myDragons = HomeKat.getDragons();
		thisScreen = this;
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		setUpFog();
//		ClickableGraphic post = new ClickableGraphic(0, getHeight()-300, 1.0,"img/oneSignLeft.png");
//		post.setAction(new Action(){
//			public void act(){
//				DragonLand.game.setScreen(DragonLand.homeScreen);
//			}
//		});
//		
//		setUpFog(post);
		
		
		ClickableGraphic backBtn = new ClickableGraphic(0, DragonLand.HEIGHT-110, 150, 100, "img/backSign.png");
		backBtn.setAction(new Action(){
			public void act(){
				DragonLand.game.setScreen(DragonLand.shopMain);
			}
		});
		viewObjects.add(backBtn);
		
		Button b = new Button(100, 100, 100, 100, "server", Color.BLUE);
		b.setAction(new Action(){
			public void act() {
				Thread Server = new Thread(new Runnable(){
					public void run(){
						Server s = new Server();
						s.startRunning(thisScreen);
					}
				});
				Server.run();	
			}
		});
		
//		b.setAction(new Action(){
//			public void act(){
//				Server s = new Server();
//				s.startRunning(thisScreen);
//			}
//		});
		
		Button client = new Button(300, 300, 100, 100, "client", Color.pink);
		client.setAction(new Action(){
			public void act(){
				Thread tradeClient = new Thread(new Runnable(){
					public void run(){
						Client c = new Client("2600:1017:b404:ff23:85c5:9da2:f7d:5a9f");	
						c.startRunning(thisScreen);
					}
				});
				tradeClient.run();
			}
		});
		
		viewObjects.add(b);
		viewObjects.add(client);
		
//		for(Dragon d : myDragons){
//			viewObjects.add(d);
//		}
	
	
		
	}
	
	public void setUpFog(){
		Fog fog; 
		
		for(int i = -10; i < 10; i++){
			fog = new Fog((i*getWidth() / 10), 200, 500, 300, "img/introFog.png", 100);
			viewObjects.add(fog);
			fog.setY(fog.generateYPos());
			fog.play();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public Dragon getMyDragon(){
		return myDragon;
	}
	
	public void trade(){
		System.out.println("This is happening");
		((ShopScreen)DragonLand.newShopScreen).sellDragon(myDragon);
		Dragon temp = new Dragon(theirDragon);
		((ShopScreen)DragonLand.newShopScreen).buyDragon(temp);
	}
}

/**
	your dragons to sell
	
	dragons to buy
		users trading dragons
	
	
	
	
**/
