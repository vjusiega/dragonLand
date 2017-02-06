package game;

import java.util.ArrayList;

import guiPractice.ClickableScreen;
import guiPractice.components.Visible;

/**
 * @author Kat 
 * @author Jenniber
 *
 */
public class HomeScreen extends ClickableScreen implements Runnable{
	
	

	public HomeScreen(int width, int height) {
		super(width, height);
		
		Thread play = new Thread(this);
		play.start();
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
