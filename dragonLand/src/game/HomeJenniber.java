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
import dragonComponents.HungryBox;
import dragonComponents.HungryTimesInterface;
import guiPractice.components.Visible;

/**
 * @author Jenniber
 *
 */
public class HomeJenniber implements Runnable, HungryTimesInterface{
	
	private static int hungryTime;
	//private int[] dragonNumbers;
	private ArrayList<Dragon> hungryDragons;
	private ArrayList<HungryTimesInterface> hungryBoxTimes;
	/**
	 * 
	 */
	public HomeJenniber(Dragon d) {
		
		int randNum = getRandDragon();
		createHungryThread(d, randNum);
	}
	
	public void createHungryThread(Dragon d, int dragonNum){
		//d is a dragon from HomeKat.onScreenDragons
		HungryBox hungryDragon = new HungryBox(d.getX(),d.getY()+100, dragonNum);
		hungryBoxTimes.add(hungryDragon);
		Thread hungry = new Thread(hungryDragon);
		hungry.start();
	}
	
	public void removeHungryAndDragon(int HungryNum, ArrayList<Visible> viewObjects) {
		
		Dragon d = getDragonsOnScreen().get(HungryNum);
		removeDragon(d,viewObjects);
		
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
			HungryBox d = getDragonsOnScreen().get(i).getHungryBox();
			if(d.getHungryTime()<=0){
				removeHungryAndDragon(i,HomeKat.getViewObjects());
			}
		}
		
	}
	
	public int getRandDragon(){
		int randNum = 1;
		do{
			randNum = (int) Math.random()*getDragonsOnScreen().size();
		}
		while(!getDragonsOnScreen().get(randNum).hasHungryBox());
		
		return randNum;
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
	public ArrayList<Dragon> setDragonsOnScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasHungryBox() {
		// TODO Auto-generated method stub
		return false;
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
		int num = getRandDragon();
		Dragon d = getDragonsOnScreen().get(num);
		return new HungryBox(d.getX(),d.getY(),num);
	}

	@Override
	public void setHungryBox() {
		// TODO Auto-generated method stub
		
	}

}
