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
	
	public static GameDragon addDragon(String imgSrc){
		//dragonArray = new ArrayList<GameDragon>();
		int xPos;
		int dragonHeight = 500;
		xPos = screenWidth / 2;
		int yPos = screenHeight - 125;
		if(dragonArray.size() >= 1){
			dragonHeight = 250;
			yPos = screenHeight - 70;
			if(dragonArray.size() == 1){
				xPos = dragonArray.get(0).getX() - 50;
				
			}
			else{
				xPos = dragonArray.get(0).getX() + 135;
				//System.out.println("This is the dragon width: " + dragonArray.get(1).getWidth());
			}
		}
		
		GameDragon temp = new GameDragon(xPos, yPos, dragonHeight, dragonHeight, imgSrc);
		
		dragonArray.add(temp);
		
		return temp;
	}
	
	public static GameDragon removeDragon(){
		GameDragon deadDragon = dragonArray.get(dragonArray.size() - 1);
		dragonArray.remove(dragonArray.size() - 1);
		return(deadDragon);
	}

	public static void changeDragonPos(int x) {
		int leadDragon = findLeadDragon(x);
		int leadDragonPos = (dragonArray.get(leadDragon)).getX();
		if(leadDragonPos + x > 0 && leadDragonPos + x < screenWidth){
			for(GameDragon d : dragonArray){
				d.setX(d.getX() + x);
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
			if(dragonArray.size() == 1 || dragonArray.size() == 2){
				return 0; 
			}
			else return 2; 
		}
		else return 0;
	}
}