/**
 * 
 */
package game;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;

import dragonComponents.Dragon;
import dragonComponents.HungryBox;
import dragonComponents.HungryTimesInterface;

/**
 * @author Jenniber
 *
 */
public class HomeJenniber implements Runnable{
	
	private static int hungryTime;
	private ArrayList<HungryTimesInterface> hungryBoxTimes;
	/**
	 * 
	 */
	public HomeJenniber() {
		// TODO Auto-generated constructor stub
	}
	
	public void createHungryThread(Dragon d, int dragonNum){
		//d is a dragon from HomeKat.onScreenDragons
		HungryBox hungryDragon = new HungryBox(d.getX(),d.getY()+100, dragonNum);
		hungryBoxTimes.add(hungryDragon);
		Thread hungry = new Thread(hungryDragon);
		hungry.start();
	}
	
	private void changeHungryTime(){
		try{
			while(hungryTime>=0){
				Thread.sleep(1000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		changeHungryTime();
		hungryTime--;
		update();
		checkRemoveDragon();
	}
	
	private void checkRemoveDragon() {
		for(int i=0; i< hungryBoxTimes.size();i++){
			if(hungryBoxTimes.get(i).getHungryTime()<=0){
				removeDragon(, null);
			}
		}
		
	}
	
	public int getRandDragon(){
		int randNum = (int) Math.random()*getDragonsOnScreen().size();
		return randNum;
	}

}
