package dragonComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.TextLabel;

public class ShopLabel extends TextLabel {
<<<<<<< HEAD

	public ShopLabel(int x, int y, int w, int h, String text) {
		super(x, y, w, h, text);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Graphics2D g) {
		g=clear();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		
		g.setColor(new Color(224, 102, 102));
		g.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 5, 5);
		
		g.setStroke(new BasicStroke(2));
		g.setColor(new Color(62, 74, 99));
		g.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 5, 5);
		
		
		g.setColor(Color.BLACK);
		if(getText()!=null){
			int cutoff = getText().length();
			String t = getText();
			while(cutoff > 0 && fm.stringWidth(t) > getWidth())
			{
				cutoff --;
				t = t.substring(0, cutoff);
			}
			
			
			g.setFont(new Font(getFont(), Font.PLAIN, getSize()));
			g.drawString(t, ((getWidth() - fm.stringWidth(t)))/3, (getHeight() + fm.getHeight() - fm.getDescent())/2);
=======
	
	private Color color = new Color(224, 102, 102);
	private int arc= 5;
	
	public ShopLabel(int x, int y, int w, int h, String text) {
		super(x, y, w, h, text);
		update();
	}
	
	public ShopLabel(int x, int y, int w, int h, String text, Color c) {
		super(x, y, w, h, text);
		color = c;
		update();
	}
	
	public void setArc(int a)
	{
		arc = a;
		update();
	}
	
	public void update(Graphics2D g) {
		g=clear();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		
		g.setColor(color);
		g.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 2, arc, arc);
		
		g.setColor(new Color(62, 74, 99));
		g.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, arc, arc);
		
		
		g.setColor(Color.BLACK);
		if(getText()!=null){
			int cutoff = getText().length();
			String t = getText();
			while(cutoff > 0 && fm.stringWidth(t) > getWidth())
			{
				cutoff --;
				t = t.substring(0, cutoff);
			}
			
			
			g.setFont(new Font(getFont(), Font.PLAIN, getSize()));
			g.drawString(t, ((getWidth() - fm.stringWidth(t)))/2 - 20, (getHeight() + fm.getHeight() - fm.getDescent())/2);
>>>>>>> refs/remotes/origin/shop
		}
	}
}
