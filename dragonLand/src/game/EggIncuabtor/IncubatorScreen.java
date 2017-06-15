package game.EggIncuabtor;

import java.awt.Polygon;
import java.util.ArrayList;

import game.DragonLand;
import game.mainScreenTeam.Dragon;
import game.mainScreenTeam.HomeKat;
import game.newShop.ShopDragon;
import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.Graphic;
import guiPractice.components.PolygonButton;
import guiPractice.components.Visible;
import introScreens.Fog;

public class IncubatorScreen extends Screen {
	
	private final int EGGS_ON_SCREEN = 3;
	private int currentPage;
	private Graphic background;
	private Egg[] eggsIncubating;
	private int totalPages;
	private ArrayList<Egg> eggsToBuy;
	private ArrayList<Object> itemsOnDisplay;
	

	public IncubatorScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	public boolean dragonHatch(){
		if(HomeKat.dragonHome.getDragonsOnScreen().size()+1>9){
			return false;
		}
		return true;
	}
	
	@Override
	public void initObjects(ArrayList<Visible> viewObjects2) {
		itemsOnDisplay = new ArrayList<Object>();
		eggsIncubating = new Egg[3];
		eggsToBuy = new ArrayList<Egg>();
		setUpEggs();
		currentPage = 1; 
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		setUpFog();
		Graphic post = new Graphic(0, getHeight()-150, 0.6,"img/backSign.png");
		viewObjects.add(post);
		
		Graphic nextButton = new Graphic(getWidth() - 150, getHeight()-120, 0.6, "img/nextPreviousSign.png");
		viewObjects.add(nextButton);
		
		addPostButtons();
		
		drawEggs();
	}
	
	public void setUpEggs(){
		for (int i = 0; i < 10; i++){
			eggsToBuy.add(new Egg(0, 0, 100, 100, "img/tempEgg.png", "eggy" + i, 10, 10));
		}
	}
	
	public void drawEggs(){
		clearEggsFromScreen();
		for(int i = 0; i < eggsIncubating.length; i++){
			if(eggsIncubating != null){
				
			}
			else{
				
			}
		}
		int startEgg;
		if(currentPage == 1){
			startEgg = 0; 
		}else{
			startEgg = ((currentPage - 1) * 3); 
		}
		for(int i = 0; (i < startEgg + 3) && (i < eggsToBuy.size()); i++){
			Egg temp = eggsToBuy.get(i);
			Egg temp2 = new Egg(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), temp.getImgSrc(), temp.getName(), temp.getPrice(),temp.getTime());
			BuyEgg e = new BuyEgg(getDisplayX(startEgg, i), 0.66666666, getWidth(), getHeight(), temp2);
			addObject(e.getBackdrop());
			addObject(e.getEgg());
			itemsOnDisplay.add(e.getBackdrop());
			itemsOnDisplay.add(e.getEgg());
		}
	}
	

//	public double getDisplayY(int startEgg, int i) {
//		if()
//		return 0;
//	}

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
		itemsOnDisplay.clear();
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
//					removeDisplayDragon();
//					drawDragons();
				}
			}});
	    
	    viewObjects.add(forwardBtn);
	    
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
//					removeDisplayDragon();
//					drawDragons();
				}
			}});
	    
	    viewObjects.add(previousBtn);
	    
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

	public void setEggsIncubating(Egg[] arr){
		for(int i=0;i<eggsIncubating.length;i++){
			eggsIncubating[i]=arr[i];
		}
	}
	public Egg[] getEggsIncubating(){
		return eggsIncubating;
	}
}
