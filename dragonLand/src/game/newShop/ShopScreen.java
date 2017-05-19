package game.newShop;

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

	
	private int currentPage;
	private int totalPages;
	
	//make this one final
	private int dragonsPerPage = 6;
	
	private ArrayList<Dragon> dragonsToBuy;
	private ArrayList<Dragon> myDragons;
	
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
		currentPage = 1; 
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		setUpFog();
		
		generateInitialDragons();
		
		totalPages = dragonsToBuy.size() / dragonsPerPage;
	}
	
	public void generateInitialDragons() {
		String[] names = new String[] {"Rowdy","Thorn","Mushu","Falcor","Elliot","Puff","Spyro","Sandy",
				"Scaly","Nessie","Nymph","Sparky","Flambi","Drago","Viper","Moon","Saphira","Scorch","Toothless","Stormfly"};
		int price=50;
		for(int i = 0; i < names.length; i++){
			Dragon d = new Dragon(0,0,0,0, names[i], price + (i*50), "img/dragon"+i+".png");
			dragonsToBuy.add(d);
		}
	}

	public void drawDragons(){
		for(int i = 0; i <= dragonsPerPage; i++){
			
		}
	}
		
	public void enterShop(){
		//should update the shop and return to the first page
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
		public ArrayList<Dragon> getMyDragons(){
			return myDragons;
		}
	
		public void buyDragon(Dragon d){
			dragonsToBuy.remove(d);
			myDragons.add(d);
		}
		
		public void addToMyDragons(Dragon d){
			myDragons.add(d);
		}
	
		public void removeFromBuyDragons(Dragon d){
			dragonsToBuy.remove(d);
		}
	
		public void removeFromMyDragons(Dragon d){
			myDragons.remove(d);
			dragonsToBuy.add(d);
		}
	
		public String[] getNamesOfPurchased(){
			String[] output = new String[myDragons.size()];
			for(int i = 0; i < myDragons.size(); i++){
				output[i] = myDragons.get(i).getName();
			}
			return output;
		}
	
}
