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
public class HomeJenniber implements Runnable {
	
	private ArrayList<HungryBox> hungryBoxTimes;
//	private ArrayList<Visible> viewObjects;
	/**
	 * 
	 */
	public HomeJenniber() {
		System.out.println("Thread");
//		this.viewObjects = viewObjects;
		this.hungryBoxTimes = new ArrayList<HungryBox>();
		Thread hungerBegins = new Thread(this);
		hungerBegins.start();
//		randomHunger();
//		createHungryThread(getRandDragon());
	}
	
	public void randomHunger(){
		
	}
	
	public void createHungryThread(Dragon d){
		//d is a dragon from HomeKat.onScreenDragons
		System.out.println("Add hungryBox");
		HungryBox hungryBox = getHungryBox(d);
		hungryBox.setAction(new Action(){

			@Override
			public void act() {
				for(int i=0; i<HomeKat.dragonHome.getDragonsOnScreen().size();i++){
					Dragon d= HomeKat.dragonHome.getDragonsOnScreen().get(i);
					if((d.getY()<350 && hungryBox.getX()==d.getX()-25) || hungryBox.getY()==d.getY()+105){
							d.setHungryBox(false);
//							Thread waitUntilHungry = new Thread(new Runnable() {
//								
//								@Override
//								public void run() {
//									// TODO Auto-generated method stub
//									try {
//										int sleepingTime = (int)(Math.random()*9000)+1000;
//										Thread.sleep(20000+sleepingTime);
//									} catch (InterruptedException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//									createHungryThread(d);
//								}
//							});
//							waitUntilHungry.start();
					}
				}
				
				hungryBoxTimes.remove(hungryBox);
				DragonLand.homeScreen.remove(hungryBox);
				//viewObjects.remove(hungryBox);
			}
			
		});
		hungryBoxTimes.add(hungryBox);
//		viewObjects.add(hungryBox);
		DragonLand.homeScreen.addObject(hungryBox);
		Thread hungry = new Thread(hungryBox);
		hungry.start();
	}
	
	public void removeHungryAndDragon(Dragon d, HungryBox hungry) {
		hungryBoxTimes.remove(hungry);
		DragonLand.homeScreen.remove(d);
		DragonLand.homeScreen.remove(hungry);
//		HomeKat.removeDragon(d,viewObjects);
//		viewObjects.remove(hungry);
	}

	public void checkRemoveDragon() {
		for(int i=0; i< HomeKat.dragonHome.getDragonsOnScreen().size();i++){
			Dragon d = HomeKat.dragonHome.getDragonsOnScreen().get(i);
			if(d.getHungryBox()){
				for(int j=0; j<hungryBoxTimes.size(); j++){
					HungryBox hungry = hungryBoxTimes.get(j);
					int yCoord = d.getY()+105;
					if(d.getY()<350){
						if(hungry.getX()==d.getX()-25 && hungry.getHungryTime()<=0){
							removeHungryAndDragon(d,hungry);
						}
					}
					else{
						if(hungry.getY()==yCoord && hungry.getHungryTime()<=0){
							removeHungryAndDragon(d,hungry);
						}
					}
				}
			}
		}
	}
	
	public Dragon getRandDragon(){
		//Will return a randomly selected dragon from the ones currently being displayed on the HomeScreen
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

	@Override
	public void run() {
		while(HomeKat.dragonHome.getDragonsOnScreen().size()>0 && hungryBoxTimes.size()<HomeKat.dragonHome.getDragonsOnScreen().size()){
			double probability = .7;
			if(Math.random()>probability){
				createHungryThread(getRandDragon());
			}
			try {
				int sleepingTime = (int)(Math.random()*5000)+1000;
				Thread.sleep(sleepingTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

