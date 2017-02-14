/**
 * 
 */
package dragonComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;

import game.DragonLand;
import game.HomeKat;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Visible;
/**
 * @author Jenniber Franco
 *
 */
public class HungryBox extends Button implements HungryTimesInterface, Runnable {

	private int[] dragonNumbers;
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
	public HungryBox(int x, int y, int dragonNum) {
		super(x, y, W, H, TEXT+"\n"+hungryTime+" sec", DragonLand.DARKER_NUDE, null);
		//createHungryThread(HomeKat.getDragonsOnScreen().get(dragonNum), dragonNum);
	}

	
	
	
//HomeKat.removeDragon(Dragon d, viewObjects)
	@Override
	public ArrayList<Dragon> getDragonsOnScreen() {
		return HomeKat.getDragonsOnScreen();
	}


	@Override
	public Dragon setDragonsOnScreen() {
		return null;
	}


	@Override
	public void removeDragon(Dragon d, ArrayList<Visible> viewObjects) {
		HomeKat.removeDragon(d, viewObjects);
		
	}


	@Override
	public int getHungryTime() {
		// TODO Auto-generated method stub
		return hungryTime;
	}


	@Override
	public void setHungryTime(int num) {
		hungryTime = num;
		
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

}
