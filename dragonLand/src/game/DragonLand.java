/**
 * 
 */
package game;

import guiPractice.GUIApplication;
import guiPractice.Screen;

/**
 * @author Kat
 *
 */
public class DragonLand extends GUIApplication {
	/**
	 * Static Fields
	 */
	public static DragonLand game;
	public static Dragon[] DragonList; //list of all dragons in the game
	public static int coins; 
	public static Screen mainScreen;
	
	
	/**
	 * 
	 */
	public DragonLand() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see guiPractice.GUIApplication#initScreen()
	 */
	@Override
	protected void initScreen() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		game = new DragonLand();
		Thread go = new Thread(game);
		go.start();

	}

}
