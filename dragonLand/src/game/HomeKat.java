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

	public HomeKat() {
		//flyingLocations=new int[]{100,100,130,800,150,450};
		//walkingLocations = new int[]{120,500,350,350,700,450};
		makeLocations();
		}
	
	private void makeLocations() {
		
		locationsX.add(100);
		locationsX.add(120);
		locationsX.add(800);
		locationsX.add(450);
		locationsX.add(350);
		locationsX.add(700);
	}

	public void makeDragon(){
		for(int i=0;i<20;i++){
			DragonLand.dragonList[i]=(new Dragon(flyingLocations[0], flyingLocations[1], 100, 100, "red", "red", price, "img/dragon"+i));
			price+=100;
		}
	}
	
}
