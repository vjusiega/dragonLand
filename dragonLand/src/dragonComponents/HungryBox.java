/**
 * 
 */
package dragonComponents;

import java.awt.Color;
import java.util.ArrayList;

import guiPractice.components.Action;
import guiPractice.components.Button;
/**
 * @author Jenniber Franco
 *
 */
public class HungryBox extends Button {

	private ArrayList<HungryTimesInterface> hungryTimes;
	private static final int W = 50;
	private static final int H = 25;
	private static final String TEXT = "Hungry!";
	private double hungryTime;
	
	
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param color
	 * @param action
	 */
	public HungryBox(int x, int y, Color color, Action action) {
		super(x, y, W, H, TEXT, color, null);
		hungryTime=10.0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private void addHungry() {
		for(int i= 0; i<hungryTimes.size(); i++){
			double probability = .2*(30.0-hungryTime)/30;
			if(Math.random()>probability){
				final HungryTimesInterface dragon = hungryTimes.get(i);
				dragon.setHungryTime((int)(500+Math.random()*2000));
				dragon.setAction(new Action(){
					public void act(){
						remove(dragon.hungryBox);
						dragon.remove(hungryBox);
					}
				});
				addObject(hungryBox);
				dragon.add(hungryBox);
			}
		}
	}
	
	private void removeHungry(){
		for(int i=0; i<hungryTimes.size(); i++){
			HungryTimesInterface d = hungryTimes.get(i);
			d.setAppearanceTime(d.getAppearanceTime()-10);
			if(d.getAppearanceTime()<=0){
				//remove from visible screen
				remove(d);
				i--;//compensate for i++
				
			}
		}
	}

	private void remove(HungryTimesInterface d) {
		// TODO Auto-generated method stub
		
	}

}
