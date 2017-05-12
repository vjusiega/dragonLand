/**
 * 
 */
package game;

import java.awt.Color;
import java.awt.Dimension;

import game.mainScreenTeam.HomeScreen;
import game.miniGameTeam.GameInstructions;
import game.miniGameTeam.GameScreen;
import game.miniGameTeam.GameVioletta;
import game.miniGameTeam.HighScoreScreen;
import game.shopScreen.BuyScreenWendy;
import game.shopScreen.HomeShopScreen;
import game.shopScreen.SellShopZheng;

import guiPractice.GUIApplication;
import guiPractice.Screen;

/**
 * @author Kat
 *
 */
public class DragonLand extends GUIApplication {

	/**
	 * 
	 */
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 640;
	private static final long serialVersionUID = 1L;
	
	public static DragonLand game;
	public static int coins = 1500; 
	public static HomeScreen homeScreen;
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
	private GameVioletta vGame;
	
	/**
	 * 
	 */
//	public static void addDragon(AnimatedComponent a){
//		dragonList.add(a);
//	}
	public DragonLand() {
		setSize(new Dimension(WIDTH, HEIGHT));
	}

	/* (non-Javadoc)
	 * @see guiPractice.GUIApplication#initScreen()
	 */
	@Override
	protected void initScreen() {
		initColors();


		miniGameScreen = new GameScreen(DragonLand.WIDTH,DragonLand.HEIGHT);
		shopMain = new HomeShopScreen(DragonLand.WIDTH,DragonLand.HEIGHT);
		sellScreen = new SellShopZheng(DragonLand.WIDTH,DragonLand.HEIGHT);
		homeScreen = new HomeScreen(DragonLand.WIDTH,DragonLand.HEIGHT);
		buyScreen = new BuyScreenWendy(DragonLand.WIDTH,DragonLand.HEIGHT);
		highscoreScreen = new HighScoreScreen(DragonLand.WIDTH,DragonLand.HEIGHT);
		HomeScreen.jenCode = new game.mainScreenTeam.HomeJenniber();
		gameInstructionsScreen = new GameInstructions(DragonLand.WIDTH,DragonLand.HEIGHT);
		vGame = new GameVioletta();
		setScreen(homeScreen);

	}
	private void initColors() {
		NAVY = new Color(62,74,99);
		BRIGHT_PINK = new Color(224,102,102);
		LIGHT_PINK = new Color(248,186,182);
		LIGHT_NUDE = new Color(244,215,183);
		DARKER_NUDE = new Color(230,195,147);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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

		public GameVioletta getViolettaGame() {
			return vGame;
		}

}