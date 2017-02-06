/**
 * 
 */
package dragoncomponents;

import java.awt.Color;

import guiPractice.components.Action;
import guiPractice.components.Button;

/**
 * @author Violetta
 *
 */

/**
 * This class is for the mini game so that if the user needs help, they can hover over this button
 * and a screen with instructions comes up
 */

public class HoverForHelpStar extends Button{
	
	//fields
	private static int x;
	private static int y;
	private static int height;
	private static int width;
	private static String buttonText;
	
	//button constructor
	public HoverForHelpStar(int x, int y, int w, int h, String text, Color color, Action action) {
		super(x, y, w, h, text, color, action);
		// TODO Auto-generated constructor stub
	}


}
