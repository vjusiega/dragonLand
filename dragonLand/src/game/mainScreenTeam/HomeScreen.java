package game.mainScreenTeam;

import java.util.ArrayList;

import guiPractice.ClickableScreen;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;


/**
 * @author Kat 
 * @author Jenniber
 *
 */
public class HomeScreen extends ClickableScreen implements Runnable{

	private Graphic background;
	public static HomeJenniber jenCode;

	public HomeScreen(int width, int height) {
		super(width, height);
		Thread play = new Thread(this);
		play.start();
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {

		background=new Graphic(0,0,getWidth(),getHeight(),"img/Grassland.jpg");
		viewObjects.add(background);
		HomeKat katCode=new HomeKat(viewObjects, getWidth(), getHeight());
		
	}

	
	@Override
	public void run() {
	}

}