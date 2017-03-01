/**
 * 
 */
package game;

import java.util.ArrayList;

import dragonComponents.Dragon;
import dragonComponents.HungryBox;
import guiPractice.components.Action;
import guiPractice.components.Visible;

/**
 * @author Jenniber Franco
 *
 */
public class HomeJenniber {
	
	private ArrayList<HungryBox> hungryBoxTimes;
	private ArrayList<Visible> viewObjects;
	/**
	 * 
	 */
	public HomeJenniber(ArrayList<Visible> viewObjects) {
		System.out.println("Thread");
		this.viewObjects = viewObjects;
		createHungryThread(getRandDragon());
	}
	
	public void createHungryThread(Dragon d){
		//d is a dragon from HomeKat.onScreenDragons
		System.out.println("Add hungryBox");
		this.hungryBoxTimes = new ArrayList<HungryBox>();
		HungryBox hungryDragon = getHungryBox(d);
		hungryBoxTimes.add(hungryDragon);
		viewObjects.add(hungryDragon);
		Thread hungry = new Thread(hungryDragon);
		hungry.start();
	}
	
	public void removeHungryAndDragon(Dragon d, HungryBox hungry) {
		hungryBoxTimes.remove(hungry);
		HomeKat.removeDragon(d,viewObjects);
		viewObjects.remove(hungry);
	}
	
	public void removeHungry(HungryBox hungry){
		for(int i=0; i<HomeKat.dragonHome.getDragonsOnScreen().size();i++){
			Dragon d= HomeKat.dragonHome.getDragonsOnScreen().get(i);
			if(d.getY()<350 && hungry.getX()==d.getX()-25){
				System.out.println("remove!!!");
				d.setHungryBox(false);
			}
			else{
				if(hungry.getY()==d.getY()+105){
					d.setHungryBox(false);
				}
			}
		}
		hungryBoxTimes.remove(hungry);
		viewObjects.remove(hungry);
	}

	public void checkRemoveDragon() {
		for(int i=0; i< HomeKat.dragonHome.getDragonsOnScreen().size();i++){
			Dragon d = HomeKat.dragonHome.getDragonsOnScreen().get(i);
			if(d.getHungryBox()){
				for(int j=0; j<hungryBoxTimes.size(); j++){
					HungryBox hungry = hungryBoxTimes.get(j);
					int yCoord = d.getY()+105;
					if(d.getY()<350){
						if(hungry.getX()==d.getX()-25 && hungry.getHungryTime()==0){
							removeHungryAndDragon(d,hungry);
						}
					}
					else{
						if(hungry.getY()==yCoord && hungry.getHungryTime()==0){
							removeHungryAndDragon(d,hungry);
						}
					}
				}
			}
		}
	}
	
	public Dragon getRandDragon(){
		//Will return a randomly selected dragon from the ones currently being diplayed on the HomeScreen
		int randNum = (int) (Math.random()*HomeKat.dragonHome.getDragonsOnScreen().size());
		while(HomeKat.dragonHome.getDragonsOnScreen().get(randNum).getHungryBox()){
			randNum = (int) (Math.random()*HomeKat.dragonHome.getDragonsOnScreen().size());
		}
		HomeKat.dragonHome.getDragonsOnScreen().get(randNum).setHungryBox(true);
		
		return HomeKat.dragonHome.getDragonsOnScreen().get(randNum);
	}

	public HungryBox getHungryBox(Dragon d) {
		int yCoord = d.getY()+105;
		if(d.getY()<350){
			yCoord+=30;
		}
		return new HungryBox(d.getX()-25,yCoord);
	}
}

