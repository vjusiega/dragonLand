package game.miniGameTeam;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import guiPractice.components.Action;
import guiPractice.components.Button;

/**
 * @author Tamanna Hussain and Violetta Jusiega
 *
 */
public class XButton extends Button {
	
//	private static final Color _BUTTON_COLOR = new Color();
//	private static final int _BUTTON_WIDTH = 80;
//	private static final int _BUTTON_HEIGHT = 40;

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param color
	 * @param action
	 */
	public XButton(int x, int y, int w, int h, String text, Color color, Action action) {
		super(x, y, w, h, text, color, action);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Graphics2D g){
		g.setColor(Color.white);
		g.setStroke(new BasicStroke(5));
		g.drawLine(0, 0, getWidth(), getHeight()); //we might have to change this stuff to make it look better 
		g.drawLine(getWidth(), 0, 0, getHeight());
	}

}