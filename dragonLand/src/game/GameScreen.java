/**
 * 
 */
package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import dragonComponents.XButton;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Visible;

/**
 * @author Tamanna Hussain and Violetta Jusiega
 *
 */
public class GameScreen extends ClickableScreen implements MouseListener{

	private XButton exit;

	public GameScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */

	@Override
	public void initAllObjects(ArrayList<Visible> view) {
		exit = new XButton(20, 20, 40, 40, "", null, new Action() {
			
			@Override
			public void act() {
				// TODO Auto-generated method stub
				DragonLand.game.setScreen(DragonLand.mainScreen);
			}
		});
		
		view.add(exit);
	}

}
