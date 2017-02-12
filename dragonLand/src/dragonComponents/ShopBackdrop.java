package dragonComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Component;

public class ShopBackdrop extends Component {
	
	private Color color = new Color(244,215,183);
	
	public ShopBackdrop(int x, int y, int w, int h) {
		super(x, y, w, h);
		update();
	}
	
	public ShopBackdrop(int x, int y, int w, int h, Color c) {
		super(x, y, w, h);
		color = c;
		update();
	}
	
	@Override
	public void update(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
		g.setColor(Color.BLACK);
		g.drawRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
	}

}
