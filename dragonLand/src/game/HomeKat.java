package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dragonComponents.Dragon;
import dragonComponents.DragonArrayInterface;
import guiPractice.components.Action;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Button;
import guiPractice.components.Visible;


public class HomeKat implements DragonArrayInterface {

	private int price;

	private static ArrayList<Integer> locationsX=new ArrayList<Integer>();
	private static ArrayList<Integer> locationsY=new ArrayList<Integer>();
	private static ArrayList<Dragon> dragons=new ArrayList<Dragon>(); 
	private static ArrayList<Dragon> dragonsOnScreen = new ArrayList<Dragon>();
	private boolean clicked;
	
	public HomeKat(ArrayList<Visible> viewObjects, int width,int height) {
		clicked =true;
		Button shop = new Button(width-110-(width*2/100),(height*5/100),  110,  50,  "Shop",DragonLand.DARKER_NUDE,  new Action(){

			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.shopMain);
			}});
		viewObjects.add(shop);
		
		Button minigame = new Button(width-110-(width*2/100),(height*5/100)+53,  110,  50,  "Minigame",DragonLand.DARKER_NUDE,  new Action(){

			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.miniGameScreen);
			}
		
		});
		viewObjects.add(minigame);
		
		Button helpLayer = new Button((int)(width*0.1),(int)(height*0.1),(int)(width*0.8),(int)(height*0.8),  "These will be rules",DragonLand.DARKER_NUDE,  new Action(){

			@Override
			public void act() {
				viewObjects.remove(this);
			}});
		
		Button help = new Button(width-50-(width*2/100),height-50-(height*2/100),  50,  50,  "?",DragonLand.DARKER_NUDE,  new Action(){
			@Override
			public void act() {
					viewObjects.add(helpLayer);			
			}});
		
		viewObjects.add(help);
		Button title = new Button((width*2/100),(height*5/100),  350,  50,  "Welcome to Dragon Land!",DragonLand.DARKER_NUDE,  null);
		title.setSize(26);
		viewObjects.add(title);
		
		makeLocations();
		makeDragons(viewObjects);
		dragonsOnScreen(viewObjects);
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
public static void addAnimation(ArrayList<Visible> viewObjects,int x,int y, String name, int price,String imgSrc) {
		
		AnimatedComponent a = new Dragon(x,y,100,100,name, price, imgSrc);
		
		try{
			ImageIcon icon = new ImageIcon(imgSrc);
			int numberRow =3 ;
			int rows =4;
			int w =48;
			int h = 48;
			for(int i=0;i<numberRow*rows;i++){
				
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
	public static void makeDragons(ArrayList<Visible> viewObjects){
		String[] names = new String[] {"Rowdy","Thorn","Mushu","Falcor","Elliot","Puff","Spyro","Sandy",
				"Scaly","Nessie","Nymph","Sparky","Flambi","Drago","Viper","Moon","Saphira","Scorch","Toothless","Stormfly"};
		
		int price=50;
		
		for(int i=0;i<20;i++){
			addAnimation(viewObjects,0,0, names[i], price+i*50, "img/dragon"+i+".png");
		}
	}


	public void dragonsOnScreen(ArrayList<Visible> viewObjects){
		//String[] purchased = Shop.getNamesOfPurchased();

		String[] purchased = {"Thorn","Mushu","Falcor","Elliot","Puff","Toothless"};
		checkToRemove(purchased, viewObjects);
		addNewDragons(purchased, viewObjects);
	}

	private void addNewDragons(String[] purchased, ArrayList<Visible> viewObjects) {
		boolean exists = false;
		for(int i=0;i<purchased.length;i++){
			for(int j=0;j<dragonsOnScreen.size();j++){
				if(purchased[i]==dragonsOnScreen.get(j).getName())
					exists=true;
			}
			if(!exists){
				addDragon(searchByName(purchased[i]),viewObjects);
			}
			exists=false;
		}
	}

	private Dragon searchByName(String name) {
		for(Dragon d: dragons){
			if(d.getName()==name)
				return d;
		}
		return null;
	}

	private void checkToRemove(String[] purchased,ArrayList<Visible> viewObjects) {
		boolean exist = false;
		for(int i=0;i<dragonsOnScreen.size();i++){
			for(int j=0;j<purchased.length;j++){
				if(dragonsOnScreen.get(i).getName()==purchased[j])
					exist = true;
			}
			if(!exist){
				removeDragon(dragonsOnScreen.get(i),viewObjects);
				i--;
			}
		}
	}
	public void removeDragon(Dragon d,ArrayList<Visible> viewObjects){
		
		locationsX.add(d.getX());
		locationsY.add(d.getY());
		//adds dragons
		dragonsOnScreen.remove(d);
		viewObjects.remove(d);
	}
	public static void addDragon(Dragon d,ArrayList<Visible> viewObjects){
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
	}

	public static ArrayList<Dragon> getDragons() {
		return dragons;
	}

	public ArrayList<Dragon> getDragonsOnScreen() {
		return dragonsOnScreen;
	}

	@Override
	public void removeHungryDragon(Dragon d, ArrayList<Visible> viewObjects) {
		locationsX.add(d.getX());
		locationsY.add(d.getY());
		//adds dragons
		dragonsOnScreen.remove(d);
		viewObjects.remove(d);
		//ShopBuy.addFlownAwayDragon(d);
		//ShopSell.removeFlownAwayDragon(d);
	}

	

	
}
