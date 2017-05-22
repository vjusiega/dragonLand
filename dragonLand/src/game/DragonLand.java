/**
 * 
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import dragonComponents.ReadFile;
import game.EggIncuabtor.IncubatorScreen;
import game.mainScreenTeam.Dragon;
import game.mainScreenTeam.HomeScreen;
import game.miniGameTeam.GameInstructions;
import game.miniGameTeam.GameScreen;
import game.miniGameTeam.HighScoreScreen;
import game.shopScreen.BuyScreenWendy;
import game.shopScreen.HomeShopScreen;
import game.shopScreen.SellShopZheng;

import guiPractice.GUIApplication;
import guiPractice.Screen;
import guiPractice.components.AnimatedComponent;
import introScreens.WelcomeScreen;


/**
 * @author Kat
 *
 */
public class DragonLand extends GUIApplication {

	/**
	 * 
	 */
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
	public static Screen incubator;
	public static Screen HelpScreen;
	public static Color NAVY;
	public static Color BRIGHT_PINK;
	public static Color LIGHT_PINK;
	public static Color LIGHT_NUDE;
	public static Color DARKER_NUDE;
	public static WelcomeScreen welcomeScreen;
	static JFileChooser chooser;
	static ReadFile dragonFile;
	
	/**
	 * 
	 */
//	public static void addDragon(AnimatedComponent a){
//		dragonList.add(a);
//	}
	public DragonLand(String[] strings) {
		if(strings != null){
			for(int i=0; i<strings.length;i++){
				System.out.println(strings[i]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see guiPractice.GUIApplication#initScreen()
	 */
	@Override
	protected void initScreen() {
		initColors();


		miniGameScreen = new GameScreen(getWidth(),getHeight());
		shopMain = new HomeShopScreen(getWidth(),getHeight());
		sellScreen = new SellShopZheng(getWidth(),getHeight());
		homeScreen = new HomeScreen(getWidth(),getHeight());
		buyScreen = new BuyScreenWendy(getWidth(),getHeight());
		highscoreScreen = new HighScoreScreen(getWidth(),getHeight());
		HomeScreen.jenCode = new game.mainScreenTeam.HomeJenniber();
		gameInstructionsScreen = new GameInstructions(getWidth(), getHeight());
		welcomeScreen = new WelcomeScreen(getWidth(), getHeight());
//		incubator = new IncubatorScreen(viewObjects);

		setScreen(shopMain);
		

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
		try{
//			ReadFile file = new ReadFile("C:/Users/Student 8/Desktop/test.txt");
			
			JButton open = new JButton();
			chooser=new JFileChooser();
//			chooser.setCurrentDirectory(new java.io.File("C:/Users/student 8/Desktop"));
			chooser.setDialogTitle("Pick a saved file");
			if(chooser.showOpenDialog(open)== JFileChooser.APPROVE_OPTION){
				//Open JFileChooser
			}
			dragonFile = new ReadFile(chooser.getSelectedFile().getPath());
			
			game = new DragonLand(dragonFile.OpenFile());
			Thread go = new Thread(game);
			go.start();
			
			System.out.print(chooser.getSelectedFile().getPath());
		}catch
		(IOException e){
			game = new DragonLand(null);
			Thread go = new Thread(game);
			go.start();
		}
	}
	
	public static void jFileChoose(){
		JButton open = new JButton();
		chooser=new JFileChooser("Pick a saved file");
		chooser.setDialogTitle("Hello World");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		System.out.print(chooser.getSelectedFile().getPath());
	}
	
	//public coin getter + setter
		public void setCoins(int x){
			coins = x;
		}
		public int getCoins(){
			return coins;
		}

}