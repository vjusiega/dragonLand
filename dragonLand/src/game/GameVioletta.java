/**
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dragonComponents.GameDragon;
import guiPractice.components.Visible;

/**
 * @author Violetta Jusiega
 *
 */

/*
 * Rules of the game:
 * 		Player starts off with one dragon
 * 		Next dragon is placed on the player's right side
 * 		Next dragon after that is placed on the left
 * 		Max number of extra dragons is two (three in total)
 */

public class GameVioletta {
	
	private static ArrayList<GameDragon> dragonArray = new ArrayList<GameDragon>();
	
	private static int screenWidth = DragonLand.miniGameScreen.getWidth();
	private static int screenHeight = DragonLand.miniGameScreen.getHeight();
	
	public static ArrayList<GameDragon> getDragonArray(){
		return dragonArray;
	}
	
	public static void addDragon(ArrayList<Visible> viewObjects, String imgSrc){
		dragonArray = new ArrayList<GameDragon>();
		int dragonHeight = 50;
		
		int xPos = screenWidth / 2;
		int yPos = screenHeight - dragonHeight;
		
		//GameDragon temp = new GameDragon(xPos/2, (int)(yPos*0.75), 200, 200, "dragon1.png");
		GameDragon temp = new GameDragon(100, 100, 200, 200, imgSrc);
		
		addAnimation(viewObjects, imgSrc, temp);
		
		dragonArray.add(temp);
	}

	private static void addAnimation(ArrayList<Visible> viewObjects, String imgSrc, GameDragon temp) {
		//Kat code
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
						temp.addFrame(cropped, 300);
						if(i==numberRow*rows-1)
							i++;
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				viewObjects.add(temp);
				temp.update();
				//temp.setX(100);
				//temp.setY(100);
				
				temp.play();
	}

	public static void changeDragonPos(int i) {
		int leadDragon = findLeadDragon(i);
		int leadDragonPos =  (dragonArray.get(leadDragon)).getX();
		if(leadDragonPos + i > 0 && leadDragonPos + i < screenWidth){
			for(GameDragon d : dragonArray){
				d.setX(d.getX() + i);
			}
		}
	}
	
	public static int findLeadDragon(int direction) {
		if(direction < 0){
			if(dragonArray.size() == 1){
				return 0; 
			}
			else return 1; 
		}
		if(direction > 0){
			if(dragonArray.size() == 1){
				return 0; 
			}
			else return 2; 
		}
		else return 0;
	}
}