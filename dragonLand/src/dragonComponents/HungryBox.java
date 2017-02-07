/**
 * 
 */
package dragonComponents;

import java.awt.Color;

import guiPractice.components.Action;
import guiPractice.components.Button;

/**
 * @author Jenniber Franco
 *
 */
public class HungryBox extends Button {

	private static final int W = 50;
	private static final int H = 25;
	private static final String TEXT = "Hungry!";
	
	
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param color
	 * @param action
	 */
	public HungryBox(int x, int y, Color color, Action action) {
		super(x, y, W, H, TEXT, color, new Action(){

			@Override
			public void act() {
				// Make a thread
				
			}

		});

	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
