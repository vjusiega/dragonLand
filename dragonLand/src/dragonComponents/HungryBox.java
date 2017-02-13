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
		super(x, y, W, H, TEXT+"\n"+hungryTime+" sec", color, null);
	}

	
	public void createHungryThread(Dragon d){
		//d is a dragon from HomeKat.onScreenDragons
		HungryBox hungryDragon = new HungryBox(d.getX(),d.getY()+100,DragonLand.DARKER_NUDE);
		Thread hungry = new Thread(hungryDragon);
		hungry.start();
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
	}
	
	@Override
	public void update(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(hungryTime<5){
			g.setColor(DragonLand.LIGHT_PINK);
		}
		else{
			g.setColor(DragonLand.DARKER_NUDE);
		}
		double thickness = 2;
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke((float) thickness));
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 25);
		g.setColor(DragonLand.NAVY);
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 35, 25);
		
		g.setFont(new Font("Dialog",Font.BOLD,getSize()));
		FontMetrics fm = g.getFontMetrics();
		
		if(getText()!= null){
			
			String t = getText();
			//just in case text is too wide, cut off
			int cutoff = t.length();
			while(cutoff > 0 && fm.stringWidth(t) > getWidth()){
				cutoff --;
				t = t.substring(0,cutoff); 
			}
			g.drawString(t, (getWidth()-fm.stringWidth(t))/2, (getHeight()+fm.getHeight()-fm.getDescent())/2);
		}
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
