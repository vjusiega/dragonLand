/**
 * 
 */
package game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import guiPractice.components.Graphic;
import guiPractice.components.MovingComponent;
import guiPractice.components.Visible;

/**
 * @author Tamanna Hussain
 *
 */
public class Star extends MovingComponent {

	private int starSpeed;
	private ArrayList<Star> starArray;
	private Graphic starImage;
	
	public Star(int x, int y, int w, int h) {
		super(x, y, w, h);
		setX(x);
		setY(y);
		starSpeed = 3;
	}
	
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		starArray = new ArrayList<Star>();
		
		starImage = new Graphic(0, 0, getWidth(), getHeight(), "img/star.png");
		viewObjects.add(starImage);
		
	}
	
	@Override
	public void checkBehaviors() {
		//If the stars, fall after a certain point (y = 100), they disappear
		if(getY() > 100){
			setY(100);
			starSpeed *= -5;
		}
	}

	@Override
	public void drawImage(Graphics2D g) {
		// TODO Auto-generated method stub

	}
	
	public void setStarSpeed(int x){
		starSpeed = x;
	}
	
	public int getStarSpeed(){
		return starSpeed;
	}
	
}
