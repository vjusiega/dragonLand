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
	
	private boolean touched;
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
		touched = false; 
		play();
	}
	
	@Override
	public void checkBehaviors() {
		//System.out.println("Updated star");
		if(getY() >= 560){
			if(!touched && GameVioletta.checkStarContact(getX(), getWidth())){
				System.out.println(getY());
				touched = true;
				int score = GameScreen.getScore() + 1;
				GameScreen.setScore(score);
				GameScreen.setScoreDisplay();
			}
			touched = true;
			game.removeStar(this);
		}
	}
}
