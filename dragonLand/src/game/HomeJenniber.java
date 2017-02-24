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
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dragonComponents.Dragon;
import dragonComponents.DragonInterface;
import dragonComponents.HungryBox;
import dragonComponents.HungryTimesInterface;
import guiPractice.components.Visible;

/**
 * @author Jenniber Franco
 *
 */
public class HomeJenniber implements Runnable{
	
	private static int hungryTime;
	//private int[] dragonNumbers;
	//private ArrayList<Dragon> hungryDragons;
	private ArrayList<HungryTimesInterface> hungryBoxTimes;
	/**
	 * 
	 */
	public HomeJenniber(Dragon d) {
		createHungryThread(getRandDragon());
	}
	
	public void createHungryThread(Dragon d){
		//d is a dragon from HomeKat.onScreenDragons
		HungryBox hungryDragon = getHungryBox();
		hungryBoxTimes.add((HungryTimesInterface) hungryDragon);
		Thread hungry = new Thread(hungryDragon);
		hungry.start();
	}
	
	@Override
	public void run() {
		sleepHungryTime();
		hungryTime--;
		DragonLand.homeScreen.update();
		checkRemoveDragon(null);
	}
	
	private void sleepHungryTime(){
		try{
			while(hungryTime>=0){
				Thread.sleep(1000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void removeHungryAndDragon(int HungryNum, ArrayList<Visible> viewObjects) {
		
		Dragon d = HomeKat.getDragonsOnScreen().get(HungryNum);
		for(int i=0; i<hungryBoxTimes.size();i++){
			HungryTimesInterface hungry = hungryBoxTimes.get(i);
			if(hungry.getX()+30==d.getX() && hungry.getY()-100==d.getY()){
				hungryBoxTimes.remove(hungry);
			}
		}
		HomeKat.removeDragon(d,viewObjects);
		
	}

	//fix this
	private void checkRemoveDragon(ArrayList<Visible> viewObjects) {
		for(int i=0; i< HomeKat.getDragonsOnScreen().size();i++){
			Dragon d = HomeKat.getDragonsOnScreen().get(i);
			if(d.hasHungryBox()){
				for(int j=0; j<hungryBoxTimes.size(); j++){
					if(hungryBoxTimes.get(j).getX()+30==d.getX() && hungryBoxTimes.get(j).getY()-100==d.getY()){
						removeHungryAndDragon(i,viewObjects);
					}
				}
			}
		}
		
	}
	
	Dragon getRandDragon(){
		int randNum = 1;
		do{
			randNum = (int) Math.random()*HomeKat.getDragonsOnScreen().size();
		}
		while(!HomeKat.getDragonsOnScreen().get(randNum).hasHungryBox());
		
		return HomeKat.getDragonsOnScreen().get(randNum);
	}

	public void removeDragon(Dragon d, ArrayList<Visible> viewObjects) {
		HomeKat.removeDragon(d,viewObjects);	
	}

	public int getHungryTime() {
		return hungryTime;
	}

	public void setHungryTime(int num) {
		hungryTime = num;
	}


	public HungryBox getHungryBox() {
		Dragon d = getRandDragon();
		return new HungryBox(d.getX()-30,d.getY()+100);
	}

	public void setHungryBox(boolean hungryBox) {
		// TODO Auto-generated method stub
		
	}

}
