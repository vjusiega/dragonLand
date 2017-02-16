/**
 * 
 */
package dragonComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import guiPractice.components.Component;

/**
 * @author Period82.0
 *
 */
public class Background extends Component {

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Background(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see guiPractice.components.Component#update(java.awt.Graphics2D)
	 */
	@Override
	public void update(Graphics2D g) {
		g.setColor(Color.black);
		g.drawRoundRect(0,0,getWidth(),getHeight(),50,50);
		g.setColor(new Color(135,206,250));
		g.fillRoundRect(0,0,getWidth() - 1, getHeight() - 1, 50, 50);
	}

}
