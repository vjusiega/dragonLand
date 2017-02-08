package guiPractice.sampleGames;

import guiPractice.GUIApplication;

public class MouseFollower extends GUIApplication {

	public static CoordinateScreen coordScreen;
	public static MouseFollower game;
	public static MyScreen moveScreen;
	public static GraphicClickScreen graphicsMove;
	
	public static void main(String[] args){
		game = new MouseFollower();
		Thread app = new Thread(game);
		app.start();
	}
	protected void initScreen() {
		moveScreen=new MyScreen(getWidth(),getHeight());
		coordScreen=new CoordinateScreen(getWidth(),getHeight());
		graphicsMove=new GraphicClickScreen(getWidth(),getHeight());
		setScreen(coordScreen);
	}

}
