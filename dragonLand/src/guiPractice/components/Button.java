package guiPractice.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;

import game.DragonLand;



public class Button extends TextLabel implements Clickable{
	
	private Color color;
	private Action action;
	private String text;
	
	public Button(int x, int y, int w, int h, String text, Color color, Action action) {
		super(x, y, w, h, text);
		this.color=color;
		this.action=action;
		update();
	}
	public Button(int x, int y, int w, int h, String text, Color color) {
		super(x, y, w, h, text);
		this.color=color;
		update();
	}
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
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
		
		

	@Override
	public boolean isHovered(int x, int y) {
		if(x>getX()&&x<(getX()+getWidth())&&y>getY()&&y<(getY()+getHeight()))
			return true;
		return false;
	}

	@Override
	public void act() {
		if(action!=null){
			action.act();			
		}
	}

	@Override
	public Action getAction() {
		// TODO Auto-generated method stub
		return action;
	}
	
	public void setAction(Action action){
		this.action = action;
	}
}
