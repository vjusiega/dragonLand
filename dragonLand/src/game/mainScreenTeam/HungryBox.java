/**
 * 
 */
package game.mainScreenTeam;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;

import game.DragonLand;
import game.miniGameTeam.GameScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Visible;
/**
 * @author Jenniber Franco
 *
 */
public class HungryBox extends Button implements Runnable{

	private static final int W = 150;
	private static final int H = 50;
	private static final String TEXT = "Hungry!";
	private static final int HUNGRY_LIMIT =20;
	private int hungryTime;
	
	
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param color
	 * @param action
	 */
	public HungryBox(int x, int y) {
		//super(x, y, W, H, TEXT+" "+HUNGRY_LIMIT+" sec", DragonLand.DARKER_NUDE,null);
		super(x, y, W, H, TEXT+" "+HUNGRY_LIMIT+" sec", DragonLand.DARKER_NUDE);
		hungryTime = HUNGRY_LIMIT;
		update();
	}	

	public int getHungryTime() {
		return hungryTime;
	}

	public void setHungryTime(int num) {
		hungryTime = num;
	}
	
	@Override
	public void update(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(hungryTime<=5){
			g.setColor(DragonLand.LIGHT_PINK);
		}
		else{
			g.setColor(DragonLand.DARKER_NUDE);
		}
		double thickness = 2;
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

	@Override
	public void run() {
		try{
			while(hungryTime>0){
				Thread.sleep(1000);
				setHungryTime(hungryTime-1);
				setText("Hungry!"+" "+hungryTime+" sec");
				HomeScreen.jenCode.checkRemoveDragon();
				if(GameScreen.isNotHome){
					HomeScreen.jenCode.removeHungry(this);
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
