/**
 * 
 */
package game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import dragonComponents.ReadFile;
import game.EggIncuabtor.Egg;
import game.EggIncuabtor.IncubatorScreen;
import game.dragonTrading.NewTradingScreen;
import game.dragonTrading.TradingScreen;
import game.mainScreenTeam.Dragon;
import game.mainScreenTeam.HomeKat;
import game.mainScreenTeam.HomeScreen;
import game.miniGameTeam.GameInstructions;
import game.miniGameTeam.GameScreen;
import game.miniGameTeam.GameVioletta;
import game.miniGameTeam.HighScore;
import game.miniGameTeam.HighScoreScreen;
import game.newShop.ShopScreen;
import game.newShop.ShopSelectionScreen;


import guiPractice.GUIApplication;
import guiPractice.Screen;

import guiPractice.components.AnimatedComponent;
import guiPractice.components.Component;
import introScreens.NameScreen;
import introScreens.WelcomeScreen;


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
	public static int coins = 3000; 
	public static HomeScreen homeScreen;
	public static Screen shopMain; // shop 1
	//public static Screen sellScreen; // shop 2
	//public static Screen buyScreen; // shop 3
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
	
	private GameVioletta vGame;
	public WelcomeScreen welcomeScreen;
	public static Color TEXT_PINK;
	public static float fontSize;
	public static Screen newShopScreen;
	public static Screen nameScreen;
	public static Screen newTradingScreen;
	public static TradingScreen tradingScreen;
	public static Screen incubatorScreen;
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
			ArrayList<Dragon> myDragons = new ArrayList<Dragon>();//from
			ArrayList<Dragon> dragons = HomeKat.getDragons();
			ArrayList<Dragon> dragonsToBuy = new ArrayList<Dragon>();
			ArrayList<Egg> eggsIncubating = new ArrayList<Egg>();
			int x=0;

			
			for(int i=0; i<strings.length; i++){
				if(strings[i].indexOf("--")>=0){
					x=i;
					break;
				}
				String[] str = strings[i].split("''");
				myDragons.add(new Dragon(0,0,75,75,str[0],Integer.parseInt(str[1]),str[2]));
			}
			((ShopScreen)newShopScreen).setMyDragons(myDragons);
			
			for(int i=0;i<dragons.size();i++){
				 if(!dragonBought(dragons.get(i))){
					 dragonsToBuy.add(dragons.get(i));
				 }
			}
			((ShopScreen)newShopScreen).setDragonsToBuy(myDragons);
			int j=0;
			for(int i=x+1;i<strings.length;i++){
					String[] str1 = strings[i].split("''");
					eggsIncubating.set(j, new Egg(0,0,100,100,str1[0],str1[1],
							Integer.parseInt(str1[2]),Integer.parseInt(str1[3])));
					j++;
			}
			((IncubatorScreen)incubatorScreen).setEggsIncubating(eggsIncubating);
			
		}
	}
	
	private boolean dragonBought(Dragon dragon) {
		for(int i=0;i<((ShopScreen) newShopScreen).getMyDragons().size();i++){
			Dragon d = ((ShopScreen) newShopScreen).getMyDragons().get(i);
			if(d.getName().equals(dragon))return true;
		}
		return false;
	}

	public DragonLand() {
		//Sound.AMBIANCE.loop();


		
	}
	/* (non-Javadoc)
	 * @see guiPractice.GUIApplication#initScreen()
	 */
	@Override
	protected void initScreen() {
		
		initColors();
		try {
			File fontFile = new File("fonts/RockSalt.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			Font baseFont=font.deriveFont(16f);
			Component.setBaseFont(baseFont);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		miniGameScreen = new GameScreen(WIDTH, HEIGHT);
		shopMain = new ShopSelectionScreen(WIDTH, HEIGHT);
		newShopScreen = new ShopScreen(WIDTH, HEIGHT); 
		homeScreen = new HomeScreen(WIDTH, HEIGHT);
		highscoreScreen = new HighScore(WIDTH, HEIGHT);
		HomeScreen.jenCode = new game.mainScreenTeam.HomeJenniber();

		welcomeScreen = new WelcomeScreen(WIDTH, HEIGHT);
		gameInstructionsScreen = new GameInstructions(WIDTH, HEIGHT);
		tradingScreen = new TradingScreen(WIDTH, HEIGHT);
		vGame = new GameVioletta();

		incubatorScreen = new IncubatorScreen(WIDTH, HEIGHT);
		newTradingScreen = new NewTradingScreen(WIDTH, HEIGHT);
		setScreen(homeScreen);

	}
	private void initColors() {
		NAVY = new Color(62,74,99);
		BRIGHT_PINK = new Color(224,102,102);
		LIGHT_PINK = new Color(248,186,182);
		LIGHT_NUDE = new Color(244,215,183);
		DARKER_NUDE = new Color(230,195,147);
		TEXT_PINK = new Color(255,218,238);
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
			if(chooser.getSelectedFile()==null){
				game = new DragonLand(null);
				Thread go = new Thread(game);
				go.start();
			}else{

				dragonFile = new ReadFile(chooser.getSelectedFile().getPath());
				game = new DragonLand(dragonFile.OpenFile());
				Thread go = new Thread(game);
				go.start();
				
				System.out.print(chooser.getSelectedFile().getPath());
			}
		}catch
		(IOException e){
		}
	}
	
//	public static void jFileChoose(){
//		JButton open = new JButton();
//		chooser=new JFileChooser("Pick a saved file");
//		chooser.setDialogTitle("Hello World");
//		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
////		System.out.print(chooser.getSelectedFile().getPath());
//	}
	
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

		public void setFontSize(int fontSize){
			this.fontSize= fontSize;
		}
}
