package game.shopScreen;

import java.awt.Color;
import java.util.ArrayList;

import game.DragonLand;
import game.mainScreenTeam.Dragon;
import game.miniGameTeam.GameScreen;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import introScreens.Fog;
/*
 * @author Wendy and Zheng
 * */
public abstract class ShopScreen extends ClickableScreen {
	
	private Action action;
	private Graphic background;

	
	private int currentPage = 1;
	private int totalPages;
	
	//make this one final
	private int dragonsPerPage = 6;
	
	

	
	private ArrayList<Dragon> dragonsToBuy;
	private ArrayList<Dragon> myDragons;
	
	public ArrayList<Dragon> getMyDragons(){
		return myDragons;
	}
	
	public void addToMyDragons(Dragon d){
		myDragons.add(d);
	}
	
	public void removeFromBuyDragons(d){
		dragonsToBuy.remove(d);
	}
	
	public void removeFromMyDragons(Dragon d){
		dragonsToBuy.add(myDragons.remove(d));
	}
	
	public String[] getNamesOfPurchased(){
		String[] output = new String[myDragons.size()];
		for(int i = 0; i < myDragons.size(); i++){
			output[i] = myDragons.get(i).getName();
		}
		return output;
	}
	
	public void drawDragons(){
	}
			
	

	
	public ShopScreen(int width, int height) {
		super(width, height);
		update();
	}
	
	//what does this do
	public ShopScreen(int width, int height, /*ArrayList<Dragon> dl,*/ Action act) {
		super(width, height);
		//dragonList = dl;
		action = act;
		update();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		setUpFog();
		
		generateInitialDragons();
		
		totalPages = dragonsToBuy.size() / dragonsPerPage;
		drawDragons();
		
	}
	
	public void generateInitialDragons(){
			
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
	
	
	public abstract void addDragonLabels(ArrayList<Visible> viewObjects);
	
	public CoinLabel getCoins()
	{
		return coins;
	}
	
	public ClickableGraphic getArrowRight()
	{
		return arrowRight;
	}
	
	public ClickableGraphic getArrowLeft()
	{
		return arrowLeft;

	}
	
	public ShopLabel getDragonAmount(){
		return dragonAmount;
		
	}
	
	public ShopLabel getPage(){
		return page;
	}
	
	public void updateShopLabels()
	{
		coins.setCoins();
		updateDragonAmount();
	}
	
	public abstract void updateDragonAmount();
}
