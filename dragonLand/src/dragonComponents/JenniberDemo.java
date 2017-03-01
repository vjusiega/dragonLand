package dragonComponents;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.DragonLand;
import game.HomeKat;
import guiPractice.GUIApplication;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.ClickableScreen;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

/**
 * @author Jenniber Franco
 *
 */
public class JenniberDemo extends GUIApplication {

	public static HungryBox fluctuation;
	/**
	 * 
	 */
	public JenniberDemo() {
		fluctuation = new HungryBox(100, 100);
	}

	/* (non-Javadoc)
	 * @see guiPractice8.GUIApplication#initScreen()
	 */
	@Override
	protected void initScreen() {
		DemoScreen demo = new DemoScreen(getWidth(), getHeight());
		setScreen(demo);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JenniberDemo demo = new JenniberDemo();
		Thread app = new Thread(demo);
		app.start();
	}
	
	//nested inner class;
	private class DemoScreen extends ClickableScreen{

		private HungryBox hungryBox1;
		
		public DemoScreen(int width, int height) {
			super(width, height);
			// TODO Auto-generated constructor stub
		}

		public void initAllObjects(ArrayList<Visible> viewObjects) {
			hungryBox1 = new HungryBox(40, 100);
			//JenniberDemo.fluctuation.createHungryThread(HomeKat.getDragonsOnScreen().get(1));
			hungryBox1.update();
			viewObjects.add(hungryBox1);
		}
		
	}

}