package game.mainScreenTeam;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.DragonLand;
import game.miniGameTeam.GameScreen;
import game.miniGameTeam.NoBorderButton;
import game.shopScreen.BuyScreenInterface;
import game.shopScreen.HomeShopScreen;
import game.shopScreen.SellShopZheng;
import guiPractice.components.Action;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Button;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;


public class HomeKat implements DragonArrayInterface {

	public static HomeKat dragonHome;
	private static ArrayList<Integer> locationsX=new ArrayList<Integer>();
	private static ArrayList<Integer> locationsY=new ArrayList<Integer>();
	private static ArrayList<Dragon> dragons=new ArrayList<Dragon>(); 
	private static ArrayList<Dragon> dragonsOnScreen = new ArrayList<Dragon>();
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
	
	public HomeKat(ArrayList<Visible> viewObjects, int width,int height) {
		//
		this.viewObjects=viewObjects;
		
		thelp1 = "Welcome to Dragon Land!";
		help1 = new NoBorderButton(300,75,500,50,  thelp1,DragonLand.DARKER_NUDE,null);
		help1.setSize(30);
		
		thelp2 = "This is your dragon pet land. Living here you can have up to 6";
		help2 = new NoBorderButton(130,150,720,35,  thelp2,DragonLand.DARKER_NUDE,null);
		help2.setSize(20);
		
		thelp3 = "dragons. Be careful, when you see your dragons are hungry, quickly ";
		help3 = new NoBorderButton(130,200,720,35,  thelp3,DragonLand.DARKER_NUDE,null);
		help3.setSize(20);
		
		thelp4 = "click the 'Hungry' button or the dragon will fly away back to the store. ";
		help4 = new NoBorderButton(130,250,720,35,  thelp4,DragonLand.DARKER_NUDE,null);
		help4.setSize(20);
		
		thelp5 = "To buy these dragons, visit the shop and check them out. Now as";
		help5 = new NoBorderButton(130,300,720,35,  thelp5,DragonLand.DARKER_NUDE,null);
		help5.setSize(20);
		
		thelp6 = "you notice you need coins to shop, so play the minigame to earn more.";
		help6 = new NoBorderButton(130,350,740,35,  thelp6,DragonLand.DARKER_NUDE,null);
		help6.setSize(20);

		thelp7 = "Keep trying to beat your score. Have fun taking care of your dragons.";
		help7= new NoBorderButton(130,400,720,35,  thelp7,DragonLand.DARKER_NUDE,null);
		help7.setSize(20);
		
		Button shop = new Button(width-110-(width*2/100),(height*5/100),  110,  50,  "Shop",DragonLand.DARKER_NUDE,  new Action(){

			@Override
			public void act() {
				GameScreen.isNotHome = true;
				((HomeShopScreen)DragonLand.shopMain).updateHomeShopLabels();
				DragonLand.game.setScreen(DragonLand.shopMain);
			}});
		viewObjects.add(shop);
		
		Button minigame = new Button(width-110-(width*2/100),(height*5/100)+53,  110,  50,  "Minigame",DragonLand.DARKER_NUDE,  new Action(){

			@Override
			public void act() {
				GameScreen.isNotHome = true;
				DragonLand.game.setScreen(DragonLand.gameInstructionsScreen);
			}
		
		});
		viewObjects.add(minigame);
		
		Button helpLayer = new Button((int)(width*0.1),(int)(height*0.1),(int)(width*0.8),(int)(height*0.8),  null,DragonLand.DARKER_NUDE,  new Action(){

			@Override
			public void act() {
				viewObjects.remove(this);
			}});
		Button help = new Button(width-50-(width*2/100),height-50-(height*2/100),  50,  50,  "?",DragonLand.DARKER_NUDE,  new Action(){
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
		Button title = new Button((width*2/100),(height*5/100),  350,  50,  "Welcome to Dragon Land!",DragonLand.DARKER_NUDE,  null);
		title.setSize(26);
		viewObjects.add(title);
		DragonFood food = new DragonFood(100, 100, 75, 75, "img/food.png");
		viewObjects.add(food);
			
		
		makeLocations();
		makeDragons();
		dragonsOnScreen();
		dragonHome = this;
		}
	
	public static void makeLocations() {
		
		locationsX.add(100);
		locationsX.add(250);
		locationsX.add(400);
		locationsX.add(550);
		locationsX.add(700);
		locationsX.add(850);
		
		locationsY.add(150);
		locationsY.add(200);
		locationsY.add(250);
		locationsY.add(360);
		locationsY.add(420);
		locationsY.add(480);
		
		
	}
	/*
	 * When called this creates a dragon along with the dragon's sprite frames
	 * then the created dragon is added to the ListArray dragons
	 * the x and y coordinates are temprary here
	 * only called when screen intializes
	 */
public static void addAnimation(int x,int y, String name, int price,String imgSrc) {
		
		AnimatedComponent a = new Dragon(x,y,100,100,name, price, imgSrc);
		
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
		String[] purchased =((SellShopZheng)DragonLand.sellScreen).getNamesOfPurchased();
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
				if(purchased[i]==dragonsOnScreen.get(j).getName())
					exists=true;
			}
			if(!exists){
				addDragon(searchByName(purchased[i]));
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
		d.setX(locationsX.get(randomInt));
		locationsX.remove(randomInt);
		
		randomInt=(int)(Math.random()*(locationsY.size()));
		d.setY(locationsY.get(randomInt));
		locationsY.remove(randomInt);
		
		//adds dragons
		d.update();
		d.play();
		dragonsOnScreen.add(d);
		viewObjects.add(d);
//		if(dragonsOnScreen.size()==1)DragonLand.beginHunger();
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
		((SellShopZheng)DragonLand.sellScreen).removeDragonsInSellShop(d);
		((BuyScreenInterface)DragonLand.buyScreen).addToDragonsInBuyShop(d);
	}

	

	
}