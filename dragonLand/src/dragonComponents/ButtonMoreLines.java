/**
 * 
 */
package dragonComponents;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import guiPractice.components.Action;
import guiPractice.components.Button;

/**
 * @author veera
 *
 */
public class ButtonMoreLines extends Button {

	public ButtonMoreLines(int x, int y, int w, int h, String text, Color color, Action action) {
		super(x, y, w, h, text, color, action);
		// TODO Auto-generated constructor stub
	}
	
	private void drawString(Graphics2D g, String text, int x, int y) {
		for (String line : text.split("\n")){
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
		}
	}
	
	public void update(Graphics2D g){
		FontMetrics fm = g.getFontMetrics();
		
		if(getText()!= null){
			String t = getText();
			drawString(g,t, 1,1);
		}
	}

}
