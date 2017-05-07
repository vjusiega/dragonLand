/**
 * 
 */
package game.miniGameTeam;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;


import game.DragonLand;
import game.mainScreenTeam.Dragon;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Visible;

/**
 * @author Violetta Jusiega
 *
 */
//
/*
 * Rules of the game:
 * 		Player starts off with one dragon,
 * 		Next dragon is placed on the player's right side
 * 		Next dragon after that is placed on the left
 * 		Max number of extra dragons is two (three in total)
 */
//
public class GameVioletta implements gameDragonInterface{
	
	public static GameVioletta vGame;
	private static ArrayList<Dragon> dragonArray;
	
	private boolean playing;
	
	public GameVioletta(){
		vGame = this;
		dragonArray = new ArrayList<Dragon>();
		playing = true;
	}
	
	private static int screenWidth = DragonLand.miniGameScreen.getWidth();
	private static int screenHeight = DragonLand.miniGameScreen.getHeight();
	
	public ArrayList<Dragon> getDragonArray(){
		return dragonArray;
	}
	
	public void eraseDragons(){
		dragonArray.clear();
	}
	
	public Dragon addDragon(String imgSrc){
		int random = (int) (Math.random() * 19);
		imgSrc = "img/dragon" + random + ".png";
		
		int xPos;
		int dragonHeight = 100;
		xPos = screenWidth / 2;
		if(dragonArray.size() >= 1){
			dragonHeight = (int) (dragonHeight * 0.65);
			if(dragonArray.size() == 1){
				xPos = dragonArray.get(0).getX() - dragonHeight;
			}
			else{
				xPos = dragonArray.get(0).getX() + dragonHeight + 25;
			}
		}
		
		int yPos = screenHeight - dragonHeight;
		
		Dragon d = new Dragon(xPos, yPos, dragonHeight, dragonHeight, "", 0, imgSrc);
		
		d.setDragonAnimation(d, imgSrc);
		dragonArray.add(d);
		d.setX(xPos);
		d.setY(yPos);
		d.setDirection(5);
		d.play();
		d.setCurrentFrame(0);
		return d;
	}
	
	public Dragon removeDragon(){
		if(dragonArray.size() > 0){
			Dragon deadDragon = dragonArray.get(dragonArray.size() - 1);
			dragonArray.remove(dragonArray.size() - 1);
			if(dragonArray.size() == 0){
				playing = false;
				DragonLand.game.setScreen(DragonLand.highscoreScreen);
				GameScreen.tGame.stopGame();
				//stopGame();
			}
			return(deadDragon);
		}
		return null;
	}

	public void changeDragonPos(int x) {
		if(dragonArray.size() > 0){
			int leadDragon = findLeadDragon(x);
			int leadDragonPos = (dragonArray.get(leadDragon)).getX();
			if(leadDragonPos + x > 0 && leadDragonPos + x < screenWidth){
				for(Dragon d : dragonArray){
					d.setX(d.getX() + x);
				}
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
	
	public boolean checkStarContact(Star1 star){
		int starX = star.getX();
		int starWidth = star.getWidth();
		int starEnd = starX + starWidth;
		int dragonStart = (dragonArray.get(findLeadDragon(-1))).getX();
		int dragonEnd = ((dragonArray.get(findLeadDragon(1))).getX() + (dragonArray.get(findLeadDragon(1))).getWidth());
		if((starX > dragonStart && starX < dragonEnd) || (starEnd > dragonStart && starEnd < dragonEnd)){

			return true; 
			
		}
		else{
			return false; 
		}
	}

	public boolean getPlaying() {
		return playing;
	}

	@Override
	public void setPlaying(boolean b) {
		playing = b;
		
	}

}