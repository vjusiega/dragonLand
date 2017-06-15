package game.mainScreenTeam;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dragonComponents.saveDragons;
import game.DragonLand;
import game.EggIncuabtor.Egg;
import game.EggIncuabtor.IncubatorScreen;
import game.miniGameTeam.GameScreen;
import game.miniGameTeam.NoBorderButton;

import guiPractice.components.PolygonButton;
import game.newShop.ShopScreen;
import guiPractice.components.Action;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Button;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import game.newShop.ShopScreen;
import game.newShop.ShopSelectionScreen;


public class HomeKat implements DragonArrayInterface {

	public static HomeKat dragonHome;
	private static ArrayList<Integer> locationsX=new ArrayList<Integer>();
	private static ArrayList<Integer> locationsY=new ArrayList<Integer>();
	private static ArrayList<Dragon> dragons=new ArrayList<Dragon>(); 
	private static ArrayList<Dragon> dragonsOnScreen = new ArrayList<Dragon>();
	
	public static DragonFood getFood() {
		return food;
	}

	private static ArrayList<Visible> viewObjects;
	//fields for help dialog
	private NoBorderButton help1;
	private String thelp1;
	private NoBorderButton help2;
	private String thelp2;
	private NoBorderButton help3;
	private String thelp3;
	private NoBorderButton help4;
	private String thelp4;
	private NoBorderButton help5;
	private String thelp5;
	private NoBorderButton help7;
	private String thelp7;
	private TextLabel help6;
	private String thelp6;
	private static DragonFood food ;
	private Button helpLayer;
	private boolean show;
	public HomeKat(ArrayList<Visible> viewObjects, int width,int height) {
		//
		this.viewObjects=viewObjects;
		helpLayer(width,height);
	
		Graphic post = new Graphic(10, DragonLand.HEIGHT-270, 1, "img/three_sign.png");
		viewObjects.add(post);
		Graphic helpPost = new Graphic(DragonLand.WIDTH - 150, DragonLand.HEIGHT-120, .6, "img/helpSaveSign.png");
		viewObjects.add(helpPost);
		
		addPostButtons();
		
		food = new DragonFood(130, DragonLand.HEIGHT-103, 75, 50, "img/food.png");
		viewObjects.add(food);
		
		makeLocations();
		makeDragons();
		dragonsOnScreen();
		dragonHome = this;
		}
	private void helpLayer(int width, int height) {
		thelp1 = "Welcome to Dragon Land!";
		help1 = new NoBorderButton(300,75,500,50,  thelp1,DragonLand.LIGHT_PINK,null);
		help1.setSize(30);
		
		thelp2 = "This is your dragon pet land. Living here you can have up to 6";
		help2 = new NoBorderButton(130,150,720,35,  thelp2,DragonLand.LIGHT_PINK,null);
		help2.setSize(20);
		
		thelp3 = "dragons. Be careful, when you see your dragons are hungry, quickly ";
		help3 = new NoBorderButton(130,200,720,35,  thelp3,DragonLand.LIGHT_PINK,null);
		help3.setSize(20);
		
		thelp4 = "click the 'Hungry' button or the dragon will fly away back to the store. ";
		help4 = new NoBorderButton(130,250,720,35,  thelp4,DragonLand.LIGHT_PINK,null);
		help4.setSize(20);
		
		thelp5 = "To buy these dragons, visit the shop and check them out. Now as";
		help5 = new NoBorderButton(130,300,720,35,  thelp5,DragonLand.LIGHT_PINK,null);
		help5.setSize(20);
		
		thelp6 = "you notice you need coins to shop, so play the minigame to earn more.";
		help6 = new NoBorderButton(130,350,740,35,  thelp6,DragonLand.LIGHT_PINK,null);
		help6.setSize(20);

		thelp7 = "Keep trying to beat your score. Have fun taking care of your dragons.";
		help7= new NoBorderButton(130,400,720,35,  thelp7,DragonLand.LIGHT_PINK,null);
		help7.setSize(20);
		
		helpLayer = new Button((int)(width*0.1),(int)(height*0.1),(int)(width*0.8),(int)(height*0.8),  null,DragonLand.LIGHT_PINK,  new Action(){

			@Override
			public void act() {
				viewObjects.remove(this);
			}});

	}
	private void addPostButtons() {
		Polygon shopPol = new Polygon();
		shopPol.addPoint(25, 10);
		shopPol.addPoint(200, 50);
		shopPol.addPoint(220, 65);
		shopPol.addPoint(190, 83);
		shopPol.addPoint(15, 45);
		shopPol.addPoint(25, 10);

	    PolygonButton shop = new PolygonButton(10, DragonLand.HEIGHT-250, 230, 100, shopPol, new Action(){
			@Override
			public void act() {
				GameScreen.isNotHome = true;
				//((HomeShopScreen)DragonLand.shopMain).updateHomeShopLabels();
				DragonLand.game.setScreen(DragonLand.shopMain);
			}
		});
	    viewObjects.add(shop);
	    
	    Polygon gameButton = new Polygon();
	    gameButton.addPoint(30, 20);
	    gameButton.addPoint(205, 45);
	    gameButton.addPoint(200, 85);
	    gameButton.addPoint(30, 60);
	    gameButton.addPoint(12, 40);
	    gameButton.addPoint(30, 20);

	    PolygonButton gameButtn = new PolygonButton(10, DragonLand.HEIGHT-200, 230, 100, gameButton, new Action(){
			@Override
			public void act() {
				GameScreen.isNotHome = true;
				DragonLand.game.setScreen(DragonLand.gameInstructionsScreen);
			}
		});
	    viewObjects.add(gameButtn);
	    
	    Polygon helpBtn = new Polygon();
	    helpBtn.addPoint(20, 18);
	    helpBtn.addPoint(130, 45);
	    helpBtn.addPoint(135, 50);
	    helpBtn.addPoint(120, 62);
	    helpBtn.addPoint(12, 40);
	    helpBtn.addPoint(20, 18);

	    PolygonButton help = new PolygonButton(DragonLand.WIDTH - 150, DragonLand.HEIGHT-120, 150, 100, helpBtn, new Action(){
			@Override
			public void act() {
				
				if(viewObjects.contains(helpLayer)){
					
					viewObjects.remove(help1);
					viewObjects.remove(help2);
					viewObjects.remove(help3);
					viewObjects.remove(help4);
					viewObjects.remove(help5);
					viewObjects.remove(help6);
					viewObjects.remove(help7);
					viewObjects.remove(helpLayer);
				}
				else{
					viewObjects.add(helpLayer);	
					viewObjects.add(help1);
					viewObjects.add(help2);
					viewObjects.add(help3);
					viewObjects.add(help4);
					viewObjects.add(help5);
					viewObjects.add(help6);
					viewObjects.add(help7);
				}
			}});
	    
	    viewObjects.add(help);
	    
	    Polygon exitBtn = new Polygon();
	    exitBtn.addPoint(20, 18);
	    exitBtn.addPoint(120, 30);
	    exitBtn.addPoint(120, 60);
	    exitBtn.addPoint(20, 35);
	    exitBtn.addPoint(5, 25);
	    exitBtn.addPoint(20, 18);

	    PolygonButton exit = new PolygonButton(DragonLand.WIDTH - 150, DragonLand.HEIGHT-80, 150, 100, exitBtn, new Action(){
			@Override
			public void act() {
				ArrayList<Dragon> myDragons = ((ShopScreen)DragonLand.newShopScreen).getMyDragons();
				Egg[] e = ((IncubatorScreen)DragonLand.incubatorScreen).getEggsIncubating();
				saveDragons d = new saveDragons(myDragons,e,"DragonLandSave");
				System.exit(1);
			}});
	    
	    viewObjects.add(exit);
	}
	public static void addBerry(){
		viewObjects.add(food);
		
	}
	public static void removeBerry(){
		viewObjects.remove(food);
		
	}
	public static void makeLocations() {
		locationsX.add(50);
		locationsX.add(160);
		locationsX.add(800);
		locationsX.add(270);
		locationsX.add(380);
		locationsX.add(490);
		locationsX.add(580);
		locationsX.add(710);
		locationsX.add(890);
		
		locationsY.add(100);
		locationsY.add(200);
		locationsY.add(190);
		locationsY.add(530);
		locationsY.add(300);
		locationsY.add(400);
		locationsY.add(170);
		locationsY.add(480);
		locationsY.add(375);
		
	}
	/*
	 * When called this creates a dragon along with the dragon's sprite frames
	 * then the created dragon is added to the ListArray dragons
	 * the x and y coordinates are temprary here
	 * only called when screen intializes
	 */
public static void addAnimation(int x,int y, String name, int price,String imgSrc) {
		
		AnimatedComponent a = new Dragon(x,y,75,75,name, price, imgSrc);
		
		try{
			ImageIcon icon = new ImageIcon(imgSrc);
			int numberRow = 3;
			int rows = 4;
			int w = 48;
			int h = 48;
			for(int i=0; i<numberRow*rows; i++){
				//declare cropped image
				BufferedImage cropped = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				int leftMargin=0;
				int topMargin =0 ;
				int x1 = leftMargin + w*(i%numberRow);
				int y1=topMargin +h*(i/numberRow);
				Graphics g = cropped.createGraphics();
				g.drawImage(icon.getImage(),0,0,w,h,x1,y1,x1+w,y1+h,null);
				a.addFrame(cropped, 300);
				if(i==numberRow*rows-1)
					i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		dragons.add((Dragon) a);
		a.setX(x);
		a.setY(y);
	}

/*
 * called to create the 20 dragons, attaches a price,  name and src folder to the dragon
 */

	public static void makeDragons(){
		String[] names = new String[] {"Rowdy","Thorn","Mushu","Falcor","Elliot","Puff","Spyro","Sandy",
				"Scaly","Nessie","Nymph","Sparky","Flambi","Drago","Viper","Moon","Saphira","Scorch","Toothless","Stormfly"};
		int price=50;
		for(int i=0;i<20;i++){
			addAnimation(0,0, names[i], price+i*50, "img/dragon"+i+".png");
		}
	}

	/* 
	 * updates the dragons displayed based on the purchases from the shop
	 * the purchased array is used to retrieve the names of dragon in shop from their label ListArray
	 */
	public static void dragonsOnScreen(){
		
		String[] purchased = ((ShopScreen) DragonLand.newShopScreen).getNamesOfPurchased();
		checkToRemove(purchased);
		addNewDragons(purchased);
	}
	/*
	 * checks to see if any new dragons were purchased and adds to them to the screen
	 */
	private static void addNewDragons(String[] purchased) {
		boolean exists = false;
		for(int i=0;i<purchased.length;i++){
			for(int j=0;j<dragonsOnScreen.size();j++){
				if(purchased[i].equals(dragonsOnScreen.get(j).getName()))
					exists=true;
			}
			if(!exists){
				addDragon(searchByName(purchased[i]));
				removeBerry();
				addBerry();
			}
			exists=false;
		}
	}
	//returns the dragon with the specific name from the dragon listArray
		//does not need a null exception because in accordance with code above, name will never be null
	private static Dragon searchByName(String name) {
		
		for(Dragon d: dragons){
			if(d.getName().equals(name))
				return d;
		}
		return null;
	}
	
	/*
	 * checks to see if any dragons were sold
	 * and removes them
	 */
	
	private static void checkToRemove(String[] purchased) {
		for(int i=0;i<dragonsOnScreen.size();i++){
			boolean exist = false;
			for(int j=0;j<purchased.length;j++){
				if(dragonsOnScreen.get(i).getName().equals(purchased[j]))
					exist = true;
			}
			if(!exist){
				removeDragon(dragonsOnScreen.get(i));
				i--;
			}
		}
	}
	/*
	 * first it takes the dragons locations and adds it to the two location arrays
	 * these arrays contain available coordinates for dragons to be placed in. 
	 * then the dragon is removed from onScreen listArray and from  viewObjects
	 */
	public static void removeDragon(Dragon d){
		
		locationsX.add(d.getX());
		locationsY.add(d.getY());
		//adds dragons
		dragonsOnScreen.remove(d);
		viewObjects.remove(d);
		HomeScreen.jenCode.editHungryBoxTimes(d);
		
	}
	/*
	 * first it selects a random available x coordinate and then a Y coordinate
	 * the ranomInt is done twice to create more combinations of possible locations if both
	 * arrays have 6 possiblities (since only 6 dragons on screen max)
	 * then it updates these coordinates andsets the animation to play and 
	 * adds dragon to view objects and dragonOnScreen
	 */
	public static void addDragon(Dragon d){
		//adds back the available dragon spot in the field

		int randomInt=(int)(Math.random()*locationsX.size());
		d.setX(locationsX.remove(randomInt));
		d.setY(locationsY.remove(randomInt));

		d.update();
		d.play();
		dragonsOnScreen.add(d);
		viewObjects.add(d);

	}

	public static ArrayList<Dragon> getDragons() {
		return dragons;
	}

	public ArrayList<Dragon> getDragonsOnScreen() {
		return dragonsOnScreen;
	}

	@Override
	public void removeHungryDragon(Dragon d) {
		locationsX.add(d.getX());
		locationsY.add(d.getY());
		//adds dragons
		dragonsOnScreen.remove(d);
		DragonLand.homeScreen.remove(d);
		((ShopScreen) DragonLand.newShopScreen).addToBuyDragons(d);
		((ShopScreen) DragonLand.newShopScreen).removeFromMyDragons(d);
		//((SellShopZheng)DragonLand.sellScreen).removeDragonsInSellShop(d);
		//((BuyScreenInterface)DragonLand.buyScreen).addToDragonsInBuyShop(d);
	}
}