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
	//public static Dragon[] DragonList; //list of all dragons in the game
	public static int coins; 
	public static Screen homeScreen;
	public static Screen shopMain; // shop 1
	public static Screen sellScreen; // shop 2
	public static Screen buyScreen; // shop 3
	public static Screen highscoreScreen; // high score
	public static Screen miniGameScreen; // minigame
	
	
	
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
		homeScreen = new HomeScreen(getWidth(),getHeight());
//		shopMain = new (getWidth(),getHeight());
//		sellScreen = new (getWidth(),getHeight());
//		buyScreen = new (getWidth(),getHeight());
		highscoreScreen = new HighScoreScreen(getWidth(),getHeight());
//		miniGameScreen = new (getWidth(),getHeight());
		//uncomment your line once u have a class, input class name before get width()/height()
		setScreen(highscoreScreen);
		//////////!!!!!!!!! if u want to test only your screen change the above lines^
		// but before you push to develop/ merge from develop always change it back plz
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		game = new DragonLand();
		Thread go = new Thread(game);
		go.start();
	}

}
