/**
 * 
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import dragonComponents.Dragon;
import guiPractice.GUIApplication;
import guiPractice.Screen;
import guiPractice.components.AnimatedComponent;

/**
 * @author Kat
 *
 */
public class DragonLand extends GUIApplication {
	/**
	 * Static Fields
	 */

	public static DragonLand game;

	//public static ArrayList<AnimatedComponent> dragonList; //list of all dragons in the game
	public static int coins; 

	public static Screen homeScreen;
	public static Screen shopMain; // shop 1
	public static Screen sellScreen; // shop 2
	public static Screen buyScreen; // shop 3
	public static Screen highscoreScreen; // high score
	public static GameScreen miniGameScreen; // minigame
	public static Screen gameInstructionsScreen;

	public static Screen HelpScreen;

	public static Color NAVY;
	public static Color BRIGHT_PINK;
	public static Color LIGHT_PINK;
	public static Color LIGHT_NUDE;
	public static Color DARKER_NUDE;
	
	//
	/**
	 * 
	 */
//	public static void addDragon(AnimatedComponent a){
//		dragonList.add(a);
//	}
	public DragonLand() {
		
//		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
//		int monitorWidth=(int)screenSize.getWidth();
//		int monitorHeight=(int)screenSize.getHeight();
//		setSize(WIDTH, HEIGHT);
//		setLocation((monitorWidth-WIDTH)/2,(monitorHeight-HEIGHT)/2);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
	}

	/* (non-Javadoc)
	 * @see guiPractice.GUIApplication#initScreen()
	 */
	@Override
	protected void initScreen() {
		initColors();
		homeScreen = new HomeScreen(getWidth(),getHeight());
		miniGameScreen = new GameScreen(getWidth(),getHeight());
		shopMain = new HomeShopScreen(getWidth(),getHeight());
		//sellScreen = new SellShopZheng(getWidth(),getHeight());
		buyScreen = new BuyScreenWendy(getWidth(),getHeight());
		highscoreScreen = new HighScoreScreen(getWidth(),getHeight());
		setScreen(highscoreScreen);
	}

	private void initColors() {
		NAVY = new Color(62,74,99);
		BRIGHT_PINK = new Color(224,102,102);
		LIGHT_PINK = new Color(248,186,182);
		LIGHT_NUDE = new Color(244,215,183);
		DARKER_NUDE = new Color(230,195,147);
	}
//
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		game = new DragonLand();
		Thread go = new Thread(game);
		go.start();
	}
	
	//public coin getter + setter
		public void setCoins(int x){
			coins = x;
		}
		public int getCoins(){
			return coins;
		}

}