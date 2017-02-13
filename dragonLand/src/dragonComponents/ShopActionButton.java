package dragonComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import game.DragonLand;
import guiPractice.components.Action;
import guiPractice.components.Button;

public class ShopActionButton extends Button {
	
	private static final int ARC_WIDTH = 15;
	public ShopActionButton(int x, int y, int w, int h, String text, Action action) {
		super(x, y, w, h, text, DragonLand.DARKER_NUDE, action);
	}
	
	public ShopActionButton(int x, int y, int w, int h, String text, Color color, Action action) {
		super(x, y, w, h, text, color, action);
	}

	public void update(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		
		g.setColor(getColor());
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
			g.drawString(t, (getWidth() - fm.stringWidth(t))/2 - 10, (getHeight() + fm.getHeight() - fm.getDescent())/2);
		}
	}

}
