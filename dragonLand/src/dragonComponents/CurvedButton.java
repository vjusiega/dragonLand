/**
 * 
 */
package dragonComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import guiPractice.components.Action;
import guiPractice.components.Button;

/**
 * @author Veeraj
 *
 */
public class CurvedButton extends Button {

	private Color color;
	
	public CurvedButton(int x, int y, int w, int h, String text, Color color, Action action) {
		super(x, y, w, h, text, color, action);
		this.color=color;
		update();
		// TODO Auto-generated constructor stub
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		update();
	}

	
	public void update(Graphics2D g){
		double thickness = 2;
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke((float) thickness));
		
		g.setColor(color);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 100, 100);
		g.setColor(Color.black);
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 100, 100);
	}

}
