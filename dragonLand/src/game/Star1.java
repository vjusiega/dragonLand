/**
 * 
 */
package game;

import guiPractice.components.GraphicMovingComponent;

/**
 * @author Tamanna
 *
 */
public class Star1 extends GraphicMovingComponent {

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param imageLocation
	 */
	public Star1(int x, int y, int w, int h) {
		super(x, y, w, h, "img/star.png");
		setVy(5);
		setVx(0);
	}
	
	@Override
	public void checkBehaviors() {
		if(getY() <= 540){
			//
		}

	}
}
