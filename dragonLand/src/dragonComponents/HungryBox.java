/**
 * 
 */
package dragonComponents;

import java.awt.Color;
import java.util.ArrayList;

import game.DragonLand;
import game.HomeKat;
import guiPractice.components.Action;
import guiPractice.components.Button;
/**
 * @author Jenniber Franco
 *
 */
public class HungryBox extends Button implements Runnable {

	private ArrayList<HungryTimesInterface> hungryTimes;
	private static final int W = 50;
	private static final int H = 25;
	private static final String TEXT = "Hungry!";
	private static int hungryTime=15;
	
	
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param color
	 * @param action
	 */
	public HungryBox(int x, int y, Color color) {
		super(x, y, W, H, TEXT+"/n"+hungryTime+" sec", color, null);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void createHungryThread(){
		HungryBox hungry1 = new HungryBox(HomeKat.dragonsOnScreen.getX(),HomeKat.dragonsOnScreen.getY()+100,DragonLand.DARKER_NUDE);
		Thread hungry = new Thread(this);
		hungry.start();
	}
	
	private void changeHungryTime(){
		try{
			hungryTime--;
			Thread.sleep(1000);
			if(hungryTime<=0){
				Thread.;
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
//	public void addHungry() {
//		for(int i= 0; i<hungryTimes.size(); i++){
//			double probability = (double).2*(15-hungryTime)/15;
//			if(Math.random()>probability){
//				final HungryTimesInterface dragon = hungryTimes.get(i);
//				dragon.setHungryTime((int)(500+Math.random()*2000));
//				dragon.setAction(new Action(){
//					public void act(){
//						remove(dragon.hungryBox);
//						dragon.remove(hungryBox);
//					}
//				});
//				addObject(hungryBox);
//				dragon.add(hungryBox);   
//			}
//		}
//	}
//	
//	public void removeHungry(){
//		for(int i=0; i<hungryTimes.size(); i++){
//			HungryTimesInterface d = hungryTimes.get(i);
//			d.setAppearanceTime(d.getAppearanceTime()-10);
//			if(d.getAppearanceTime()<=0){
//				//remove from visible screen
//				remove(d);
//				i--;//compensate for i++
//				
//			}
//		}
//	}

//	private void remove(HungryTimesInterface d) {
//		// TODO Auto-generated method stub
//		
//	}

}
