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
<<<<<<< HEAD
public class HungryBox extends Button {
=======
public class HungryBox extends Button implements Runnable{
>>>>>>> refs/remotes/origin/mainScreenJenniber

	private static final int W = 100;
	private static final int H = 50;
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
				// TODO Auto-generated method stub
				
			}
			
		});

	}

<<<<<<< HEAD
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

=======
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
>>>>>>> refs/remotes/origin/mainScreenJenniber
	}
<<<<<<< HEAD

=======
	
>>>>>>> refs/remotes/origin/mainScreenJenniber
}
