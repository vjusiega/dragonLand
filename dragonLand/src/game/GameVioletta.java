/**
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dragonComponents.Dragon;
import dragonComponents.GameScreenFromVioletta;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Visible;

/**
 * @author Violetta Jusiega
 *
 */
//
/*
 * Rules of the game:
 * 		Player starts off with one dragon
 * 		Next dragon is placed on the player's right side
 * 		Next dragon after that is placed on the left
 * 		Max number of extra dragons is two (three in total)
 */

public class GameVioletta implements GameScreenFromVioletta{
	
	private static ArrayList<Dragon> dragonArray = new ArrayList<Dragon>();
	
	private static int screenWidth = DragonLand.miniGameScreen.getWidth();
	private static int screenHeight = DragonLand.miniGameScreen.getHeight();
	
	private boolean play;
	
	public static ArrayList<Dragon> getDragonArray(){
		return dragonArray;
	}
	
	public static Dragon addDragon(String imgSrc){
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
		
		Dragon d = new Dragon(xPos, yPos, dragonHeight, dragonHeight, "whatever", 0, imgSrc);
		//d.setInGame(true);
		//System.out.println(d.getInGame());
		
		AnimatedComponent a = d;
		setDragonAnimation(a, imgSrc);
		
		dragonArray.add(d);
		a.setX(xPos);
		a.setY(yPos);
		
		return d;
	}
	
	
	public static Dragon removeDragon(){
		Dragon deadDragon = dragonArray.get(dragonArray.size() - 1);
		dragonArray.remove(dragonArray.size() - 1);
		return(deadDragon);
	}

	public static void changeDragonPos(int x) {
		int leadDragon = findLeadDragon(x);
		int leadDragonPos = (dragonArray.get(leadDragon)).getX();
		if(leadDragonPos + x > 0 && leadDragonPos + x < screenWidth){
			for(Dragon d : dragonArray){
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
	
	public void setPlay(boolean b){
		play = b;
	}
	
	public boolean getPlay(){
		return play; 
	}
	
	public static void setDragonAnimation(AnimatedComponent a, String imgSrc){
		try{
			ImageIcon icon = new ImageIcon(imgSrc);
			int numberRow = 3;
			int rows = 4;
			int w = 48;
			int h = 48;
			for(int j = 0; j < 2; j ++){
				for(int i = 0; i < numberRow*rows; i++){
					if(i > 8 && i < 12){
						BufferedImage cropped = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
						int leftMargin = 0;
						int topMargin = 0;
						int x1 = leftMargin + w*(i%numberRow);
						int y1 = topMargin +h*(i/numberRow);
						Graphics g = cropped.createGraphics();
						g.drawImage(icon.getImage(),0,0,w,h,x1,y1,x1+w,y1+h,null);
						a.addFrame(cropped, 300);
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}