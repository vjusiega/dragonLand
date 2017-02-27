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
	
	//private int[] dragonNumbers;
	//private ArrayList<Dragon> hungryDragons;
	private ArrayList<HungryBox> hungryBoxTimes;
	/**
	 * 
	 */
	public HomeJenniber(ArrayList<Visible> viewObjects) {
		System.out.println("Thread");
		createHungryThread(getRandDragon(), viewObjects);
		
	}
	
	public void createHungryThread(Dragon d, ArrayList<Visible> viewObjects){
		//d is a dragon from HomeKat.onScreenDragons
		System.out.println("Add hungryBox");
		HungryBox hungryDragon = getHungryBox(d);
		hungryBoxTimes.add(hungryDragon);
		viewObjects.add(hungryDragon);
		Thread hungry = new Thread(hungryDragon);
		hungry.start();
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
		Dragon d = HomeKat.dragonHome.getDragonsOnScreen().get(HungryNum);
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
		for(int i=0; i< HomeKat.dragonHome.getDragonsOnScreen().size();i++){
			Dragon d = HomeKat.dragonHome.getDragonsOnScreen().get(i);
			if(d.getHungryBox()){
				for(int j=0; j<hungryBoxTimes.size(); j++){
					if(hungryBoxTimes.get(j).getX()+30==d.getX() && hungryBoxTimes.get(j).getY()-100==d.getY()){
						removeHungryAndDragon(i,viewObjects);
					}
				}
			}
		}
		
	}
	
	public Dragon getRandDragon(){
		System.out.println("rand get");
		int randNum = (int) (Math.random()*HomeKat.dragonHome.getDragonsOnScreen().size());
		while(HomeKat.dragonHome.getDragonsOnScreen().get(randNum).getHungryBox()){
			randNum = (int) (Math.random()*HomeKat.dragonHome.getDragonsOnScreen().size());
		}
		
		HomeKat.dragonHome.getDragonsOnScreen().get(randNum).setHungryBox(true);
		
		System.out.println("return"+ HomeKat.dragonHome.getDragonsOnScreen().get(randNum).getName());
		return HomeKat.dragonHome.getDragonsOnScreen().get(randNum);
	}

	public HungryBox getHungryBox(Dragon d) {
		return new HungryBox(d.getX(),d.getY()+100);
	}
}

