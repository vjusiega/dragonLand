/**
 * 
 */
package dragonComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import guiPractice.components.Action;
import guiPractice.components.Button;

/**
 * @author Veeraj
 *
 */
public class CurvedButton extends Button {

	private Color color;
	private String font;
	
	public CurvedButton(int x, int y, int w, int h, String text, Color color, Action action) {
		super(x, y, w, h, text, color, action);
		this.color=color;
		update();
		// TODO Auto-generated constructor stub
	}
	
	public CurvedButton(int x, int y, int w, int h, String text, Color color, Action action, String font){
		super(x,y,w,h,text,color,action,font);
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		update();
	}

	
	public void update(Graphics2D g){
		g.setColor(color);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 100, 100);
		g.setColor(Color.black);
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 100, 100);
	}

}
