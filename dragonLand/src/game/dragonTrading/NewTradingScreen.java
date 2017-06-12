package game.dragonTrading;

import java.awt.Color;
import java.awt.Component;
import java.awt.Polygon;
import java.util.ArrayList;

import game.DragonLand;
import game.Sound;
import game.mainScreenTeam.Dragon;
import game.mainScreenTeam.HomeKat;
import game.miniGameTeam.GameScreen;
import game.newShop.ShopDragon;
import game.newShop.ShopScreen;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.PolygonButton;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Banner;

public class NewTradingScreen extends ClickableScreen implements Runnable{
	
	
	//most important dragon info
		private Dragon myDragon;
		private String theirDragonName;
		private Dragon theirDragon;
		
	//other
		private Graphic myPost;
		private TextLabel myDragonsName;
		
		//private Graphic theirPost;
		//private TextLabel theirDragonNameDisplay;
		
		private ClickableGraphic serverButton;
		private ClickableGraphic clientButton;
		
		private NewTradingScreen thisScreen; 
		
		private Button whatHappen;
		
		private ArrayList<Visible> removableObjects;

	
	public NewTradingScreen(int width, int height) {
		super(width, height);
	}
	
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		removableObjects = new ArrayList<Visible>();
		thisScreen = this;
		
		Graphic background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		Banner b = new Banner(0, 50, 600, 171, "img/tradeBanner.png");
		b.setX((getWidth() / 2) - (b.getWidth() / 2)); 
		viewObjects.add(b);
		
		Graphic post = new ClickableGraphic(0, getHeight()-150, 0.6,"img/backSign.png");
		viewObjects.add(post);
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
				DragonLand.game.setScreen(DragonLand.shopMain);
				exitTrade();
			}});
	    viewObjects.add(backBtn);
		
		whatHappen = new Button((int)(getWidth() * 0.4), (int)(getHeight() * 0.4), 100, 100, "", Color.white);
		viewObjects.add(whatHappen);
		
		serverButton = new ClickableGraphic((int)(getWidth() * 0.6), (int)(getHeight() * 0.4), 1.0, "img/serverSign.png");
		serverButton.setAction(new Action(){
			public void act(){
				Thread Server = new Thread(new Runnable(){
					public void run(){
						Server s = new Server();
						s.startRunning(thisScreen);
					}
				});
				Server.run();
				remove(clientButton);
				remove(serverButton);
			}
		});
		clientButton = new ClickableGraphic((int)(getWidth() * 0.6), (int)(getHeight() * 0.6), 1.0, "img/clientSign.png");
		clientButton.setAction(new Action(){
			public void act(){
				Thread Client = new Thread(new Runnable(){
					public void run(){
						Client c = new Client("127.0.0.1");
						c.startRunning(thisScreen);
					}
				});
				Client.run();
				remove(clientButton);
				remove(serverButton);
			}
		});
	}
	
	public void addConnectionButtons(){
		addObject(serverButton);
		addObject(clientButton);
	}
	
	public void enterTrade(Dragon d){
		myDragon = d;
		drawMyDragon();
		addConnectionButtons();
	}

	public void drawMyDragon(){
		ClickableGraphic background = new ClickableGraphic(getWidth(), getHeight(), 1.0, "img/whiteBox.png", 0.25, 0.5);
		addObject(background);
		removableObjects.add(background);
		
		myDragon.setWidth((int)(background.getWidth() * 0.7));
		myDragon.setHeight((int)(background.getHeight() * 0.65));
		myDragon.placeDragonOnLines(getWidth(), getHeight(), 0.25, 0.5);
		addObject(myDragon);
		removableObjects.add(myDragon);
		
		myPost = new Graphic(myDragon.getX() - 27, myDragon.getY() + myDragon.getHeight() + 10, background.getWidth(), 50, "img/blankNoWay.png");
		
		myDragonsName = new TextLabel(myDragon.getX(), myDragon.getY() + myDragon.getHeight(), background.getWidth(), 50, "" + myDragon.getName()); 
		myDragonsName.setColor(DragonLand.TEXT_PINK);
		myDragonsName.setSize(25);
		addObject(myPost);
		addObject(myDragonsName);
		removableObjects.add(myPost);
		removableObjects.add(myDragonsName);
	}
	
	public void drawTheirDragon(Dragon d){
		ClickableGraphic background = new ClickableGraphic(getWidth(), getHeight(), 1.0, "img/whiteBox.png", 0.75, 0.5);
		removableObjects.add(background);
		addObject(background);
		
		d.setWidth((int)(background.getWidth() * 0.7));
		d.setHeight((int)(background.getHeight() * 0.65));
		d.placeDragonOnLines(getWidth(), getHeight(), 0.75, 0.5);
		d.setBounce(false);
		d.play();
		addObject(d);
		removableObjects.add(d);
		
		Graphic theirPost = new Graphic(d.getX() - 27, d.getY() + d.getHeight() + 10, background.getWidth(), 50, "img/blankNoWay.png");
		TextLabel theirDragonNameDisplay = new TextLabel(d.getX(), d.getY() + d.getHeight(), background.getWidth(), 50, "" + d.getName()); 
		theirDragonNameDisplay.setColor(DragonLand.TEXT_PINK);
		theirDragonNameDisplay.setSize(25);
		addObject(theirPost);
		addObject(theirDragonNameDisplay);
		removableObjects.add(theirPost);
		removableObjects.add(theirDragonNameDisplay);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void trade(){
		theirDragon = new Dragon(theirDragonName);
		theirDragon = ((ShopScreen)DragonLand.newShopScreen).findInList(theirDragon, ((ShopScreen)DragonLand.newShopScreen).getDragonsToBuy());
		drawTheirDragon(theirDragon);
		((ShopScreen)DragonLand.newShopScreen).trade(myDragon, theirDragon);
		remove(whatHappen);
		dragonsSwitchSpots();
	}
	
	public void dragonsSwitchSpots(){
		//myDragon.setI
	}

	public void displayConnectionMessage(String string) {
		whatHappen.setText(string);
		
	}

	public void setTheirDragon(String inputDrag) {
		theirDragonName = inputDrag;
		
	}

	public Dragon getMyDragon() {
		return myDragon;
	}
	
	public void exitTrade(){
		remove(theirDragon);
		for(Visible o : removableObjects){
			remove(o);
		}
		removableObjects.clear();
	}

}
