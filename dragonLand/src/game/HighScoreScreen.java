/**
 * 
 */
package game;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dragonComponents.XButton;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Visible;
import guiPractice.components.TextLabel;

/**
 * @author Veeraj
 *
 */
public class HighScoreScreen extends ClickableScreen implements MouseListener{
	
	private TextLabel title;
	private ColoredBox test;
	private Background background;
	private XButton back;
	/**
	 * 
	 */
	public HighScoreScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		title = new TextLabel((getWidth()/2)-90, 60, 150, 20, "High Scores");
		test = new ColoredBox((getWidth()/2)-100,45,150,50);
		background = new Background(0,0, getWidth(), getHeight());
		back = new XButton(40, 100, 50, 50, "Go Back", Color.black, new Action(){

			@Override
			public void act() {
				
			}
			
		});
		viewObjects.add(background);
		viewObjects.add(test);
		viewObjects.add(title);
		viewObjects.add(back);
	}
//	public void update(){
//		image = new BufferedImage(getWidth(), getHeight(), 
//				BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = image.createGraphics();
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		g.setColor(Color.black);
//		g.drawRect(0, 0, image.getWidth(), image.getHeight());
//		g.fillRect(0, 0, image.getWidth(), image.getHeight());
//		g.setColor(Color.black);
//		//draw all visible components
//		for(int i = 0; i < viewObjects.size(); i++){
//			Visible v = viewObjects.get(i);
//			g.drawImage(v.getImage(), v.getX(), v.getY(), null);
//		}
//		image = new BufferedImage(getWidth(), getHeight(), 
//				BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = image.createGraphics();
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		g.setColor(Color.white);
//		g.fillRect(0, 0, image.getWidth(), image.getHeight());
//		g.setColor(Color.red);
//		g.fillRect(40,40, 150, 20);
//		for(int i = 0; i < viewObjects.size(); i++){
//			Visible v = viewObjects.get(i);
//			g.drawImage(v.getImage(), v.getX(), v.getY(), null);
//		}
//	}
}
