/**
 * 
 */
package game;

import java.util.ArrayList;

import dragonComponents.Dragon;
import dragonComponents.HungryBox;
import guiPractice.components.Visible;

/**
 * @author Jenniber Franco
 *
 */
public class HomeJenniber implements Runnable{
	
	private static int hungryTime;
	//private int[] dragonNumbers;
	//private ArrayList<Dragon> hungryDragons;
	private ArrayList<HungryBox> hungryBoxTimes;
	/**
	 * 
	 */
	public HomeJenniber(ArrayList<Visible> viewObjects) {
		createHungryThread(getRandDragon(), viewObjects);
	}
	
	public void createHungryThread(Dragon d, ArrayList<Visible> viewObjects){
		//d is a dragon from HomeKat.onScreenDragons
		HungryBox hungryDragon = getHungryBox();
		Thread hungry = new Thread(hungryDragon);
		hungry.start();
		hungryBoxTimes.add(hungryDragon);
		viewObjects.add(hungryDragon);
	}
	
	@Override
	public void run() {
		HungryBox hungryBox = hungryBoxTimes.get(hungryBoxTimes.size()-1);
		hungryBox.setHungryTime(hungryBox.getHungryTime()-1);
		sleepHungryTime(hungryBox);
		hungryBox.update();
		checkRemoveDragon(null);
	}
	
	private void sleepHungryTime(HungryBox hungryBox){
		try{
			while(hungryBox.getHungryTime()>=0){
				Thread.sleep(1000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void removeHungryAndDragon(int HungryNum, ArrayList<Visible> viewObjects) {
		Dragon d = HomeKat.getDragonsOnScreen().get(HungryNum);
		for(int i=0; i<hungryBoxTimes.size();i++){
			HungryBox hungry = hungryBoxTimes.get(i);
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
			if(d.getHungryBox()){
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
		while(!HomeKat.getDragonsOnScreen().get(randNum).getHungryBox());
		return HomeKat.getDragonsOnScreen().get(randNum);
	}

	public HungryBox getHungryBox() {
		Dragon d = getRandDragon();
		return new HungryBox(d.getX()-30,d.getY()+100);
	}
}

