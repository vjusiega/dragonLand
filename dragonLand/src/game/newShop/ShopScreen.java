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
	
	private int maxDragons;
	
	//make this one final
	private int dragonsPerPage;
	
	private ArrayList<Dragon> dragonsToBuy = new ArrayList<Dragon>();
	private ArrayList<Dragon> myDragons = new ArrayList<Dragon>();
	private ArrayList<TextLabel> nameLabels = new ArrayList<TextLabel>();
	private ArrayList<TextLabel> priceLabels = new ArrayList<TextLabel>();
	private ArrayList<Object> dragonsOnDisplay = new ArrayList<Object>();
	private boolean shopEnteredFirstTime = true;
	private ClickableGraphic toggleButtonBuy;
	private ClickableGraphic toggleButtonSell;
	protected boolean sell;
	private ArrayList<Graphic> actionLabels = new ArrayList<Graphic>();
	
	public ShopScreen(int width, int height) {
		super(width, height);
		update();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		maxDragons = 9; 
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
		
		
		Graphic coinDisplay = new Graphic(DragonLand.WIDTH-155, 100, 175, 50, "img/StraightOneSign.png");
		viewObjects.add(coinDisplay);
		Graphic coin = new Graphic(DragonLand.WIDTH-35, 113, 25, 25, "img/Coin.png");
		viewObjects.add(coin);
		
		toggleButtonBuy = new ClickableGraphic(DragonLand.WIDTH-155, 50, 175, 50, "img/buySellToggleBuy.png");
		toggleButtonBuy.setAction(new Action(){
			public void act(){
				remove(toggleButtonBuy);
				enterSell();
			}
		});
		
		toggleButtonSell = new ClickableGraphic(DragonLand.WIDTH-155, 50, 175, 50, "img/buySellToggleSell.png");
		toggleButtonSell.setAction(new Action(){
			public void act(){
				remove(toggleButtonSell);
				enterShop();
			}
		});
		
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
					if(trade || !shop){
						drawDragons(myDragons);
					}
					else if(shop)
						drawDragons(dragonsToBuy);
						
				}}});

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
					if(trade || !shop){
						drawDragons(myDragons);
					}
					else if(shop)
						drawDragons(dragonsToBuy);
						
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
				if(!shop){
					shop = true;
					remove(toggleButtonSell);
				}else{
					remove(toggleButtonBuy);
				}
				DragonLand.game.setScreen(DragonLand.shopMain);
			}});
	    
	    viewObjects.add(forwardBtn);
	    viewObjects.add(previousBtn);
	    viewObjects.add(backBtn);
	    
	    
	}

	public void drawDragons(ArrayList<Dragon> array){
		removeDisplayDragon();
		int startDragon;
		if(currentPage == 1)
			startDragon = 0; 
		else
			startDragon = ((currentPage - 1) * 6); 
		
		for(int i = 0; i < nameLabels.size(); i++){
			remove(nameLabels.get(i));
			remove(priceLabels.get(i));
			remove(actionLabels.get(i));
		}
		nameLabels.clear();
		priceLabels.clear();
		actionLabels.clear();
		
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
				
				if(trade){
					d.getBackdrop().setAction(new Action(){
						public void act(){
							((TradingScreen)DragonLand.tradingScreen).setMyDragon(disD);
							DragonLand.game.setScreen(DragonLand.tradingScreen);
							setTrade(false);
							addObject(toggleButtonBuy);
						}});
				}
				else if(shop){
					d.getBackdrop().setAction(new Action(){
						public void act(){
							Sound.BOUGHT.play();
							buyDragon(disD);
							updateNumberOfPages(dragonsToBuy);
							drawDragons(dragonsToBuy);
						}});
				}
				else{
					d.getBackdrop().setAction(new Action(){
						public void act(){
							Sound.BOUGHT.play();
							sellDragon(disD);
							updateNumberOfPages(myDragons);
							drawDragons(myDragons);
						}});
				}
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
				TextLabel nameL = new TextLabel( xcoord + 10, ycoord , width , 50, "  Name: "+ name );
				TextLabel priceL = new TextLabel( xcoord + 10, ycoord + 25 , width , 50, "  Price: $"+ price );
				String labelSrc = new String("");
				if(trade)
					labelSrc = "img/tradeButton.png";
				else if(shop)
					labelSrc = "img/buyButton.png";
				else if(!shop)
					labelSrc = "img/sellButton.png";
					
				
				Graphic buySellTrade = new Graphic(xcoord + 40, ycoord +  100, 75, 40, labelSrc);
				priceL.setSize(16);
				nameL.setSize(16);
				nameLabels.add(nameL);
				priceLabels.add(priceL);
				actionLabels.add(buySellTrade);
				
				
		}
	}
	
	public void removeDisplayDragon(){
		for(Object o: dragonsOnDisplay)
			remove((Visible) o);
		
		dragonsOnDisplay.clear();
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
				addObject(actionLabels.get(i/2));
				
			}
			else if(!viewObjects.contains(dragonsOnDisplay.get(i+1)) && !((ClickableGraphic) dragonsOnDisplay.get(i)).isHovered(e.getX(), e.getY())){
				addObject((Visible)dragonsOnDisplay.get(i+1));
				remove(nameLabels.get(i/2));
				remove(priceLabels.get(i/2));
				remove(actionLabels.get(i/2));
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
	
	
		
	public void enterShop(){
		//should update the shop and return to the first page
		trade = false; 
		//for first time u enter shop and everything initializes
		if(shopEnteredFirstTime){
			ArrayList<Dragon> temp = HomeKat.getDragons();
			for(int i = 0; i<temp.size();i++){
				dragonsToBuy.add(i,temp.get(i));
			
			}
			updateNumberOfPages(dragonsToBuy);

			//to not initialize again
			shopEnteredFirstTime = false;
		}
		shop = true;
		currentPage = 1;
		addObject(toggleButtonBuy);
		updateNumberOfPages(dragonsToBuy);
		drawDragons(dragonsToBuy);
	}
	
	public void enterSell(){
		shop = false; 
		currentPage = 1;
		addObject(toggleButtonSell);
		updateNumberOfPages(myDragons);
		drawDragons(myDragons);
			/*
			 * Need an if statement to show text if no dragons have been bought
			 */
	}
	
	public void enterTradeSelection(){
		trade = true;
		currentPage = 1; 
		updateNumberOfPages(myDragons);
		drawDragons(myDragons);
		remove(toggleButtonBuy);
			/*
			 * Need an if statement to show text if no dragons have been bought
			 */
	}
	
	public void updateNumberOfPages(ArrayList<Dragon> array){
		totalPages = array.size() / dragonsPerPage;
		if((array.size() % dragonsPerPage) != 0){
			totalPages++;
		}
		if(currentPage > totalPages && totalPages > 1){
			currentPage--;
		}
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
				if(d.getName().equals(dFind.getName()))
					return d;
			}
			return null; 
		}
	
		public ArrayList<Dragon> getMyDragons(){
			return myDragons;
		}
	
		
		public ArrayList<Dragon> getDragonsToBuy() {
			return dragonsToBuy;
		}

		public void setDragonsToBuy(ArrayList<Dragon> dragonsToBuy) {
			this.dragonsToBuy = dragonsToBuy;
		}

		public void setMyDragons(ArrayList<Dragon> myDragons) {
			this.myDragons = myDragons;
		}

		//official method to buy a dragon for money
		public void buyDragon(Dragon d){
			Dragon found = findInList(d, dragonsToBuy);
			if(found != null){
				if(myDragons.size() >= maxDragons){
					System.out.println("Too many dragons");
					//show message that you cannot buy more dragons
				}
				else if(DragonLand.coins < found.getPrice()){
					System.out.println("Not enough money");
					//show message that you don't have enough money
				}else{
					System.out.println("you originally had " + DragonLand.coins);
					DragonLand.coins -= found.getPrice();
					System.out.println("the dragon cost " +  found.getPrice() + " you now have " + DragonLand.coins);
					myDragons.add(found);
					dragonsToBuy.remove(found);
				}
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
				myDragons.remove(found);
				addDragonToPosition(found, dragonsToBuy);
			}
		}
		
		public void addDragonToPosition(Dragon d, ArrayList<Dragon> array){
			boolean placed = false;
			for(int i = 0; i < array.size(); i++){
				if(!placed && array.get(i).getPrice() > d.getPrice()){
					array.add(i, d);
					placed = true;
				}
			}
			if(!placed){
				array.add(d);
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
		
		public void trade(Dragon myDragon, Dragon theirDragon){
			//puts myDragon back in the shop
				Dragon mine = findInList(myDragon, myDragons);
				myDragons.remove(mine);
				addDragonToPosition(mine, dragonsToBuy);
			//adds dragon
				Dragon theirs = findInList(theirDragon, dragonsToBuy);	
				dragonsToBuy.remove(theirs);
				myDragons.add(theirs);
		}
		
}
