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
	//private int[] dragonNumbers;
	private ArrayList<Dragon> hungryDragons;
	private ArrayList<HungryTimesInterface> hungryBoxTimes;
	/**
	 * 
	 */
	public HomeJenniber(Dragon d) {
		
		int randNum = getRandDragon();
		createHungryThread();
	}
	
	public void createHungryThread(Dragon d, int dragonNum){
		//d is a dragon from HomeKat.onScreenDragons
		HungryBox hungryDragon = new HungryBox(d.getX(),d.getY()+100, dragonNum);
		hungryBoxTimes.add(hungryDragon);
		Thread hungry = new Thread(hungryDragon);
		hungry.start();
	}
	
	@Override
	public void removeHungryAndDragon(int HungryNum) {
		
		HomeKat.removeDragon(, viewObjects);
		
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
			HungryBox d = HomeKat.getDragonsOnScreen().get(i).getHungryBox();
			if(d.getHungryTime()<=0){
				removeHungryandDragon(i);
			}
		}
		
	}
	
	public int getRandDragon(){
		do{
			int randNum = (int) Math.random()*getDragonsOnScreen().size();
		}
		while(!getDragonsOnScreen().get(randNum).hasHungryBox());
		
		return randNum;
	}

}
