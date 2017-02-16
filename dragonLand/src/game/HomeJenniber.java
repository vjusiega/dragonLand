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
public class HomeJenniber implements Runnable, HungryTimesInterface, DragonInterface{
	
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
		hungryBoxTimes.add(hungryDragon);
		Thread hungry = new Thread(hungryDragon);
		hungry.start();
	}
	
	@Override
	public void run() {
		sleepHungryTime();
		hungryTime--;
		update();
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
		
		Dragon d = getDragonsOnScreen().get(HungryNum);
		for(int i=0; i<hungryBoxTimes.size();i++){
			HungryTimesInterface hungry = hungryBoxTimes.get(i);
			if(hungry.getX()+30==d.getX() && hungry.getY()-100==d.getY()){
				hungryBoxTimes.remove(hungry);
			}
		}
		removeDragon(d,viewObjects);
		
	}

	//fix this
	private void checkRemoveDragon(ArrayList<Visible> viewObjects) {
		for(int i=0; i< getDragonsOnScreen().size();i++){
			Dragon d = getDragonsOnScreen().get(i);
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
			randNum = (int) Math.random()*getDragonsOnScreen().size();
		}
		while(!getDragonsOnScreen().get(randNum).hasHungryBox());
		
		return getDragonsOnScreen().get(randNum);
	}

	@Override
	public boolean isHovered(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAnimated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Dragon> getDragonsOnScreen() {
		// TODO Auto-generated method stub
		return HomeKat.getDragonsOnScreen();
	}

	@Override
	public boolean hasHungryBox() {
		return HomeKat.hasHungryBox();
	}

	@Override
	public void removeDragon(Dragon d, ArrayList<Visible> viewObjects) {
		HomeKat.removeDragon(d,viewObjects);	
	}

	@Override
	public int getHungryTime() {
		return hungryTime;
	}

	@Override
	public void setHungryTime(int num) {
		hungryTime = num;
	}

	@Override
	public HungryBox getHungryBox() {
		Dragon d = getRandDragon();
		return new HungryBox(d.getX()-30,d.getY()+100);
	}

}
