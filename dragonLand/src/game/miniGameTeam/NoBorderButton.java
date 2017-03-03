
package game.miniGameTeam;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import game.DragonLand;
import guiPractice.components.Action;
import guiPractice.components.Button;

public class NoBorderButton extends Button{

	public NoBorderButton(int x, int y, int w, int h, String text, Color color, Action action) {
		super(x, y, w, h, text, color, action);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getColor());
		double thickness = 2;
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke((float) thickness));
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 25);
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 35, 25);
		g.setColor(DragonLand.NAVY);
		
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