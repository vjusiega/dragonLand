package dragonComponents;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
		fluctuation = new HungryBox(0, 0, game.DragonLand.LIGHT_NUDE, null);
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

		private TextLabel rewardDisplay;
		private Button beatAMonster;
		
		public DemoScreen(int width, int height) {
			super(width, height);
			// TODO Auto-generated constructor stub
		}

		public void initAllObjects(List<Visible> view) {
			rewardDisplay = new TextLabel(20, 40, 800, 25, "");
			beatAMonster = new Button(40, 100, 190, 40, "Beat a monster", Color.blue, new Action() {
				
				@Override
				public void act() {
					JenniberDemo.fluctuation.addHungry();
					rewardDisplay.setText("You earned a reward! Total points = ");
				}
			});
			view.add(rewardDisplay);
			view.add(beatAMonster);
		}

		@Override
		public void initAllObjects(ArrayList<Visible> viewObjects) {
			// TODO Auto-generated method stub
			
		}
		
	}

}