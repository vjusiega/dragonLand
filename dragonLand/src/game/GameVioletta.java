/**
 * 
 */
package game;

import java.util.ArrayList;

import dragonComponents.GameDragon;

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
	
	public static void addDragon(){
		dragonArray = new ArrayList<GameDragon>();
		int dragonHeight = 50;
		
		int xPos = screenWidth / 2;
		int yPos = screenHeight - dragonHeight;
		
		//GameDragon temp = new GameDragon(xPos/2, (int)(yPos*0.75), 200, 200, "dragon1.png");
		GameDragon temp = new GameDragon(100, 100, 200, 200, "dragon1.png");
		dragonArray.add(temp);
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