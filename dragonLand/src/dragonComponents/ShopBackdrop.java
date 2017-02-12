package dragonComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Component;

public class ShopBackdrop extends Component {

	public ShopBackdrop(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		g.drawRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
		g.setColor(new Color(244,215,183));
		g.fillRoundRect(1, 2, getWidth()-1, getHeight()-1, 50, 50);
	}

}
