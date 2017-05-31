package game.newShop;

import java.awt.Color;
import java.awt.Component;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import game.DragonLand;
import game.Sound;
import game.dragonTrading.TradingScreen;
import game.mainScreenTeam.Dragon;
import game.mainScreenTeam.HomeKat;
import game.miniGameTeam.GameScreen;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Clickable;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.PolygonButton;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Fog;

public class ShopScreen extends ClickableScreen {
	
	private Action action;
	private Graphic background;
	private boolean shop;
	private boolean trade;
	private int currentPage;
	private int totalPages;
	
	//make this one final
	private int dragonsPerPage;
	
	private ArrayList<Dragon> dragonsToBuy = new ArrayList<Dragon>();
	private ArrayList<Dragon> myDragons = new ArrayList<Dragon>();
	private ArrayList<TextLabel> nameLabels = new ArrayList<TextLabel>();
	private ArrayList<TextLabel> priceLabels = new ArrayList<TextLabel>();
	private ArrayList<Object> dragonsOnDisplay = new ArrayList<Object>();
	private boolean shopEnteredFirstTime = true;
	private ClickableGraphic buyButton;
	private ClickableGraphic sellButton;
	protected boolean sell;
	
	
	public ShopScreen(int width, int height) {
		super(width, height);
		update();
	}
	
	

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		shop = true; 
		currentPage = 1; 
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		setUpFog();
		//adding all buttons
		Graphic post = new Graphic(0, getHeight()-150, 0.6,"img/backSign.png");
		viewObjects.add(post);
		Graphic nextButton = new Graphic(getWidth() - 150, getHeight()-120, 0.6, "img/nextPreviousSign.png");
		viewObjects.add(nextButton);
		Graphic coinDisplay = new Graphic(DragonLand.WIDTH-155, 50, 175, 50, "img/StraightOneSign.png");
		viewObjects.add(coinDisplay);
		Graphic coin = new Graphic(DragonLand.WIDTH-35, 63, 25, 25, "img/Coin.png");
		viewObjects.add(coin);
		
		buyButton = new ClickableGraphic(DragonLand.WIDTH-155, 150, 175, 50, "img/StraightOneSign.png");
		sellButton = new ClickableGraphic(DragonLand.WIDTH-155, 100, 175, 50, "img/StraightOneSign.png");
		
		buyButton.setAction(new Action(){
			public void act(){
				addObject(sellButton);
				remove(buyButton);
			}
		});
		
		sellButton.setAction(new Action(){
			public void act(){
				addObject(buyButton);
				remove(sellButton);
			}
		});
		viewObjects.add(buyButton);
		
		addPostButtons();
		dragonsPerPage = 6;
	}
	
	private void addPostButtons() {
		Polygon forward = new Polygon();
		forward.addPoint(20, 18);
		forward.addPoint(130, 45);
		forward.addPoint(135, 50);
		forward.addPoint(120, 62);
		forward.addPoint(12, 40);
		forward.addPoint(20, 18);

		PolygonButton forwardBtn = new PolygonButton(DragonLand.WIDTH - 150, DragonLand.HEIGHT-120, 150, 100, forward, new Action(){
			@Override
			public void act() {
				if(currentPage < totalPages){
					currentPage++;
					removeDisplayDragon();
					if(shop)
						drawDragons(dragonsToBuy);
					else if(sell)
						drawDragons(myDragons);
					else
						drawDragons(myDragons);
			
				}
			}});

	    Polygon previous = new Polygon();
	    previous.addPoint(20, 18);
	    previous.addPoint(120, 30);
	    previous.addPoint(120, 60);
	    previous.addPoint(20, 35);
	    previous.addPoint(5, 25);
	    previous.addPoint(20, 18);

	    PolygonButton previousBtn = new PolygonButton(DragonLand.WIDTH - 150, DragonLand.HEIGHT-80, 150, 100, previous, new Action(){
			@Override
			public void act() {
				if(currentPage > 1){
					currentPage--;
					removeDisplayDragon();
					if(shop)
						drawDragons(dragonsToBuy);
					else
						drawDragons(myDragons);
					
				}}});

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
			}});
	    
	    viewObjects.add(forwardBtn);
	    viewObjects.add(previousBtn);
	    viewObjects.add(backBtn);
	    
	    
	}

	public void drawDragons(ArrayList<Dragon> array){
		int startDragon;
		if(array == null){
			array = dragonsToBuy;
		}
		removeDisplayDragon();
		if(currentPage == 1)
			startDragon = 0; 
		else
			startDragon = ((currentPage - 1) * 6); 
		
		for(int i = 0; i < nameLabels.size(); i++){
			remove(nameLabels.get(i));
			remove(priceLabels.get(i));
		}
		nameLabels.clear();
		priceLabels.clear();
		
		for(int i = startDragon; (i < startDragon + 6) && (i < array.size()); i++){
			Dragon temp = array.get(i);
			Dragon temp2 = new Dragon(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), temp.getName(),temp.getPrice(),temp.getImgSrc());
			temp2.setDragonAnimation(temp2, temp.getImgSrc());
			ShopDragon d = new ShopDragon(getDisplayX(startDragon, i), getDisplayY(startDragon, i), getWidth(), getHeight(), temp2);
		
			//some dragon setups
				Dragon disD = d.getDragon();
				disD.setBounce(false);
				disD.setConstantVY(0.05);
				disD.play();
				disD.setDirection(0);
				disD.setCurrentFrame(0);
				
				if(trade)
					d.getBackdrop().setAction(new Action(){
						public void act(){
							DragonLand.game.setScreen(DragonLand.tradingScreen);
							((TradingScreen)DragonLand.tradingScreen).setMyDragon(disD);
							setTrade(false);
							addObject(buyButton);
						}});
				
				else if(shop)
					d.getBackdrop().setAction(new Action(){
						public void act(){
							Sound.BOUGHT.play();
							buyDragon(disD);
							drawDragons(null);
						}});
				else
					d.getBackdrop().setAction(new Action(){
						public void act(){
							Sound.BOUGHT.play();
							sellDragon(disD);
							drawDragons(null);
						}});
				
			

				addObject(d.getBackdrop());
				addObject(disD);
				dragonsOnDisplay.add(d.getBackdrop());
				dragonsOnDisplay.add(disD);
			
				int xcoord = (d.getBackdrop()).getX(); 
				int ycoord = (d.getBackdrop()).getY();
				int width = (d.getBackdrop()).getWidth();
				int height = (d.getBackdrop()).getHeight();
				String name = disD.getName();
				int price = disD.getPrice();
				TextLabel nameL = new TextLabel( xcoord+10, ycoord , width , 50, "  Name: "+ name );
				TextLabel priceL = new TextLabel( xcoord+10, ycoord+25 , width , 50, "  Price: $"+ price );
				priceL.setSize(16);
				nameL.setSize(16);
				nameLabels.add(nameL);
				priceLabels.add(priceL);
				
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		for(int i=0; i<clickables.size();i++){
			Clickable c = clickables.get(i);
			if(c.getAction() != null && c.isHovered(e.getX(), e.getY())){
				c.act();
				break;
			}
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		for(int i = 0; i < dragonsOnDisplay.size()-1 ; i+=2){
			
			if(((ClickableGraphic) dragonsOnDisplay.get(i)).isHovered(e.getX(), e.getY()) && !viewObjects.contains(nameLabels.get(i/2))){
				remove((Visible)dragonsOnDisplay.get(i+1));
				addObject(nameLabels.get(i/2));
				addObject(priceLabels.get(i/2));
			}
			else if(!viewObjects.contains(dragonsOnDisplay.get(i+1)) && !((ClickableGraphic) dragonsOnDisplay.get(i)).isHovered(e.getX(), e.getY())){
				addObject((Visible)dragonsOnDisplay.get(i+1));
				remove(nameLabels.get(i/2));
				remove(priceLabels.get(i/2));
			}
		}
	}


	public double getDisplayX(int start, int i){
		int pos = i - start;
		if(pos == 0 || pos == 3)
			return 0.25;
		if(pos == 1 || pos == 4)
			return 0.5;
		if(pos == 2 || pos == 5)
			return 0.75;
		else return 0.333;
	}
	
	public double getDisplayY(int start, int i){
		int pos = i - start;
		if(pos < 3)
			return 0.333333333;
		else return 0.66666666;
	}
	
	public void removeDisplayDragon(){
		for(Object o: dragonsOnDisplay)
			remove((Visible) o);
		
		dragonsOnDisplay.clear();
	}
		
	public void enterShop(){
		//should update the shop and return to the first page
		trade = false; 
		//for first time u enter shop and everything initializes
		if(shopEnteredFirstTime){
			ArrayList<Dragon> temp = HomeKat.getDragons();
			for(int i = 0; i<temp.size();i++)
				dragonsToBuy.add(i,temp.get(i));
			
			totalPages = dragonsToBuy.size() / dragonsPerPage;
			
			if((dragonsToBuy.size() % dragonsPerPage) > 0)
				totalPages++;
			//to not initialize again
			shopEnteredFirstTime = false;
		}
		shop = true;
		currentPage = 1;
		removeDisplayDragon();
		drawDragons(dragonsToBuy);
	}
	
	public void enterTradeSelection(){
		trade = true;
		drawDragons(myDragons);
		remove(buyButton);
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
	
	/*
	 * getters and setters for dragon arrays
	 */
		public Dragon findInList(Dragon dFind, ArrayList<Dragon> array){
			for(Dragon d: array){
				if(d.getName() == dFind.getName())
					return d;
			}
			return null; 
		}
	
		public ArrayList<Dragon> getMyDragons(){
			return myDragons;
		}
	
		public void buyDragon(Dragon d){
			Dragon found = findInList(d, dragonsToBuy);
			if(found != null){
				myDragons.add(found);
				dragonsToBuy.remove(found);
			}
		}
		
		public void addToBuyDragons(Dragon d){
			dragonsToBuy.add(d);
		}
	
		public void removeFromMyDragons(Dragon d){
			Dragon found = findInList(d, myDragons);
			if(found != null)
				myDragons.remove(found);
		}
	
		public void sellDragon(Dragon d){
			Dragon found = findInList(d, myDragons);
			if(found != null){
				myDragons.remove(d);
				dragonsToBuy.add(d);
			}
		}
	
		public  String[] getNamesOfPurchased(){
			String[] output = new String[myDragons.size()];
			for(int i = 0; i < myDragons.size(); i++)
				output[i] = myDragons.get(i).getName();
			return output;
		}
		
		public void setTrade(boolean b){
			trade = b;
		}
	
}
