package DragonLabelComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Action;
import guiPractice.components.Button;

public class ShopActionButton extends Button {
	
	private static final int ARC_WIDTH = 5;
	
	public ShopActionButton(int x, int y, int w, int h, String text, Action action) {
		super(x, y, w, h, text, null, action);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		
		g.setColor(new Color(244, 215, 183));
		g.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 2, ARC_WIDTH, ARC_WIDTH);
		
		g.setStroke(new BasicStroke(2));
		g.setColor(new Color(62, 74, 99));
		g.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, ARC_WIDTH, ARC_WIDTH);
		
		if(getText() != null)
		{
			int cutoff = getText().length();
			String t = getText();
			while(cutoff > 0 && fm.stringWidth(t) > getWidth())
			{
				cutoff --;
				t = t.substring(0, cutoff);
			}
			
			
			g.setFont(new Font(getFont(), Font.PLAIN, getSize()));
			g.drawString(t, (getWidth() - fm.stringWidth(t))/3, (getHeight() + fm.getHeight() - fm.getDescent())/2);
		}
	}

}
