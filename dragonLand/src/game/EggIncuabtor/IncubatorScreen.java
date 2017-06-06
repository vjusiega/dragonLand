package game.EggIncuabtor;

import java.awt.Container;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import game.DragonLand;
import game.mainScreenTeam.Dragon;
import game.mainScreenTeam.HomeKat;
import game.newShop.ShopDragon;
import guiPractice.ClickableScreen;
import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.Clickable;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.PolygonButton;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Fog;

public class IncubatorScreen extends ClickableScreen {
	
	private final int EGGS_ON_SCREEN = 3;
	private int currentPage;
	private Graphic background;
	private ArrayList<Egg> eggsIncubating;
	private int totalPages;
	private ArrayList<Egg> eggsToBuy;
	private ArrayList<Object> itemsOnDisplay;
	private ArrayList<TextLabel> categoryLabels;
	private ArrayList<TextLabel> priceLabels ;
	private ArrayList<Graphic> actionLabels ;
	private ArrayList<Incubator> incubators;
	private ArrayList<Object> incubatorsOnDisplay;
	public IncubatorScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	public boolean canBuy(){
		if(HomeKat.dragonHome.getDragonsOnScreen().size()+1>9){
			return false;
		}
		return true;
	}
	
	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects){
		incubators = new ArrayList<Incubator>();
		categoryLabels = new ArrayList<TextLabel>();
		actionLabels = new ArrayList<Graphic>();
		priceLabels = new ArrayList<TextLabel>();
		itemsOnDisplay = new ArrayList<Object>();
		incubatorsOnDisplay = new ArrayList<Object>();
		eggsIncubating = new ArrayList<Egg>();
		eggsToBuy = new ArrayList<Egg>();
		setUpEggs();
		
		currentPage = 1; 
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		setUpFog();
		Graphic post = new Graphic(0, getHeight()-150, 0.6,"img/backSign.png");
		viewObjects.add(post);

		addPostButtons();
		setUpIncubators();
		drawEggs();
		fillIncubator();
	}
	
	private void fillIncubator() {
		//for(int i = 0; )
	}

	private void setUpIncubators() {
		System.out.println("dd");
		for(int i=0; i<3;i++){
			Incubator inc = new Incubator(0,0, 100, 100, "img/incubator.png", null);;
			//adds incubators
			incubators.add(inc);
			IncubatorBox box = new IncubatorBox(getDisplayX(0, i), 0.333333333, getWidth(), getHeight(), inc);
			addObject(box.getBackdrop());
			System.out.println(viewObjects.contains(box.getBackdrop()));
			addObject(box.getIncubator());

		}
	}

	public void setUpEggs(){
		String[] category= {"Common","Rare","Legendary"};
		for (int i = 1; i <4; i++){
			eggsToBuy.add(new Egg(0, 0, 100, 100, "img/egg"+i+".png", category[i-1], 100*i, 10*i));
		}
	}
	
	public void drawEggs(){
		clearEggsFromScreen();

		for(int i = 0; i < 3; i++){
			//adds eggs to buy
			Egg temp = eggsToBuy.get(i);
			BuyEgg e = new BuyEgg(getDisplayX(0, i), 0.66666666, getWidth(), getHeight(), temp);
			addObject(e.getBackdrop());
			addObject(e.getEgg());
			itemsOnDisplay.add(e.getBackdrop());
			itemsOnDisplay.add(e.getEgg());
			Thread eggShake = new Thread(e.getEgg());
			eggShake.start();
			
			//hover labels
			int xcoord = (e.getBackdrop()).getX(); 
			int ycoord = (e.getBackdrop()).getY();
			int width = (e.getBackdrop()).getWidth();
			String category = e.getEgg().getCategory();
			int price = e.getEgg().getPrice();
			TextLabel categoryL = new TextLabel( xcoord + 3, ycoord , width , 50, "  Category: "+ category );
			TextLabel priceL = new TextLabel( xcoord + 3, ycoord + 25 , width , 50, "  Price: $"+ price );
			String labelSrc = new String("");
			Graphic buySellTrade = new Graphic(xcoord + 40, ycoord +  100, 75, 40, "img/buyButton.png");
			priceL.setSize(16);
			categoryL.setSize(12);
			categoryLabels.add(categoryL);
		 	priceLabels.add(priceL);
			actionLabels.add(buySellTrade);
		}
	}
	

	@Override
	public void mouseMoved(MouseEvent e) {
		for(int i = 0; i < itemsOnDisplay.size()-1 ; i+=2){
			
			if(((ClickableGraphic) itemsOnDisplay.get(i)).isHovered(e.getX(), e.getY()) && !viewObjects.contains(categoryLabels.get(i/2))){
				remove((Visible)itemsOnDisplay.get(i+1));
				addObject(categoryLabels.get(i/2));
				addObject(priceLabels.get(i/2));
				addObject(actionLabels.get(i/2));
				
			}
			else if(!viewObjects.contains(itemsOnDisplay.get(i+1)) && !((ClickableGraphic) itemsOnDisplay.get(i)).isHovered(e.getX(), e.getY())){
				addObject((Visible)itemsOnDisplay.get(i+1));
				remove(categoryLabels.get(i/2));
				remove(priceLabels.get(i/2));
				remove(actionLabels.get(i/2));
			}
		}
	}

	public double getDisplayX(int startEgg, int i) {
		int pos = i - startEgg;
		if(pos == 0){
			return 0.25;
		}
		if(pos == 1){
			return 0.5;
		}
		if(pos == 2){
			return 0.75;
		}
		else return 0.333;
	}

	
	public void clearEggsFromScreen() {
		for(Object o: itemsOnDisplay){
			remove((Visible) o);
		}
		actionLabels.clear();
		itemsOnDisplay.clear();
		categoryLabels.clear();
		priceLabels.clear();
		
	
	}
	public void addEggToIncubator(Egg e){
		for(int i = 0; i< incubators.size() ; i++){
			if(!incubators.get(i).isBusy()){
				//incubators.get(i).addEgg(e);
				eggsIncubating.add(e);
				drawEggs();
				break;
			}
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
	
	private void addPostButtons() {
		
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
	    
	    viewObjects.add(backBtn);
	    
	    
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


}
