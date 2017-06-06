package game.dragonTrading;

import java.util.ArrayList;

import game.DragonLand;
import game.Sound;
import game.mainScreenTeam.Dragon;
import game.newShop.ShopDragon;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Banner;

public class NewTradingScreen extends ClickableScreen implements Runnable{
	
	
	//most important dragon info
		private Dragon myDragon;
		private Dragon theirDragon;
		
	//other
		private Graphic myPost;
		private TextLabel myDragonsName;
		
		private Graphic theirPost;
		private TextLabel theirDragonName;

	
	public NewTradingScreen(int width, int height) {
		super(width, height);
	}
	
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		Graphic background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		Banner b = new Banner(0, 50, 600, 171, "img/shopBanner.png");
		b.setX((getWidth() / 2) - (b.getWidth() / 2)); 
		viewObjects.add(b);
	}
	
	public void enterTrade(Dragon d){
		myDragon = d;
		drawMyDragon();
	}

	public void drawMyDragon(){
		ClickableGraphic background = new ClickableGraphic(getWidth(), getHeight(), 1.0, "img/whiteBox.png", 0.333, 0.5);
		addObject(background);
		
		myDragon.setWidth((int)(background.getWidth() * 0.7));
		myDragon.setHeight((int)(background.getHeight() * 0.65));
		myDragon.placeDragonOnLines(getWidth(), getHeight(), 0.25, 0.5);
		addObject(myDragon);
		
		myPost = new Graphic(myDragon.getX(), myDragon.getY() + myDragon.getHeight(), background.getWidth(), 50, "img/straightOneSign.png");
		myDragonsName = new TextLabel(myDragon.getX(), myDragon.getY() + myDragon.getHeight(), background.getWidth(), 50, "" + myDragon.getName()); 
		myDragonsName.setColor(DragonLand.TEXT_PINK);
		myDragonsName.setSize(25);
		addObject(myPost);
		addObject(myDragonsName);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
