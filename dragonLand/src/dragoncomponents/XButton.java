/**
 * 
 */
package dragoncomponents;

import java.awt.Color;
import java.awt.Graphics2D;

import guiPractice.components.Action;
import guiPractice.components.Button;

/**
 * @author Student 8
 *
 */
public class XButton extends Button {

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
		g.setColor(Color.black);
		g.drawLine(0, 0, getWidth(), getHeight());
		g.drawLine(getWidth(), 0, 0, getHeight());
	}

}
