/**
 * 
 */
package game;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Visible;
import guiPractice.components.TextLabel;

/**
 * @author Veeraj
 *
 */
public class HighScoreScreen extends ClickableScreen implements MouseListener{
	
	private TextLabel test;
	private BufferedImage image;
	/**
	 * 
	 */
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		System.out.println(getWidth());
		test = new TextLabel((getWidth()/2)-100, 50, 150, 20, "High Scores");
		viewObjects.add(test);
		//update();
	}
	
//	public void update(){
//		image = new BufferedImage(getWidth(), getHeight(), 
//				BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = image.createGraphics();
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		g.setColor(Color.white);
//		g.fillRect(0, 0, image.getWidth(), image.getHeight());
//		g.setColor(Color.red);
//		g.fillRect(40,40, 150, 20);
//	}
}
