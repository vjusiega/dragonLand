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

	private GameScreen game;
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param imageLocation
	 */
	public Star1(int x, int y, int w, int h, GameScreen game) {
		super(x, y, w, h, "img/star.png");
		setVy(2);
		this.game = game;
		setVx(0);
		play();
	}
	
	@Override
	public void checkBehaviors() {
		//System.out.println("Updated star");
		if(getY() >= 540){
			game.removeStar(this);
		}

	}
}
