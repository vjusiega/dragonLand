package game.shopScreen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Component;
/*
 * @author Wendy, Zheng
 * */
public class ShopBackdrop extends Component {
	
	private Color color = new Color(244,215,183);
	private int arc = 50;
	public ShopBackdrop(int x, int y, int w, int h) {
		super(x, y, w, h);
		update();
	}
	
	public ShopBackdrop(int x, int y, int w, int h, Color c) {
		super(x, y, w, h);
		color = c;
		update();
	}
	
	public void setArc(int a)
	{
		arc = a;
		update();
	}
	
	@Override
	public void update(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		g.drawRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
		g.setColor(new Color(244,215,183));
		g.fillRoundRect(1, 2, getWidth()-1, getHeight()-1, 50, 50);

		if(arc > 0)
		{
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(color);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
			g.setColor(new Color(62, 74, 99));
			g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arc, arc);
		}
	}

}