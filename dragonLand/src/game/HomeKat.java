package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dragonComponents.Dragon;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Visible;

public class HomeKat {

	private int price;
	private ArrayList<Integer> locationsX;
	private ArrayList<Integer> locationsY;
	private static ArrayList<AnimatedComponent> dragonList; 
	private static ArrayList<Dragon> dragonsOnScreen;
	
	public HomeKat() {
		makeLocations();
		}
	
	private void makeLocations() {
		
		locationsX.add(100);
		locationsX.add(250);
		locationsX.add(400);
		locationsX.add(550);
		locationsX.add(700);
		locationsX.add(850);
		
		locationsY.add(300);
		locationsY.add(200);
		locationsY.add(250);
		locationsY.add(400);
		locationsY.add(500);
		locationsY.add(600);
		
		
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
		viewObjects.add(a);
		//dragonList.add(a);
		a.update();
		a.setX(x);
		a.setY(y);
		a.play();
		
	}
	public void makeDragons(ArrayList<Visible> viewObjects){
		String[] names = new String[] {"Rowdy","Thorn","Mushu","Falcor","Elliot","Puff","Spyro","Sandy",
				"Scaly","Nessie","Nymph","Sparky","Flambi","Drago","Viper","Moon","Saphira","Scorch","Toothless","Stormfly"};
		price=50;
		
		for(int i=0;i<20;i++){
			addAnimation(viewObjects,0,0, names[i], price+i*50, "img/dragon"+i+".png");
		}
	}
	public void dragonsOnScreen(ArrayList<Visible> viewObjects){
		String[] purchased = StoreSellInterfaceK.getNamesOfPurchased();
		checkToRemove(purchased, viewObjects);
		addNewDragons(purchased,viewObjects);
	}

	private void addNewDragons(String[] purchased, ArrayList<Visible> viewObjects) {
		for(int i=0;i<purchased.length;i++){
			for(int j=0;j<getDragonsOnScreen().size();j++){
				
			}
		}
	}

	private void checkToRemove(String[] purchased,ArrayList<Visible> viewObjects) {
		for(int i=0;i<getDragonsOnScreen().size();i++){
			for(int j=0;j<purchased.length;j++){
				if(getDragonsOnScreen().get(i).getName()!=purchased[j]){
					viewObjects.remove(getDragonsOnScreen().get(i));
					getDragonsOnScreen().remove(getDragonsOnScreen().get(i));
					j=-1;
				}
			}	
		}
	}

	public static ArrayList<Dragon> getDragonsOnScreen() {
		return dragonsOnScreen;
	}

	public void setDragonsOnScreen(ArrayList<Dragon> dragonsOnScreen) {
		this.dragonsOnScreen = dragonsOnScreen;
	}

	
}
