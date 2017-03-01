/**
 * 
 */
package game;

import java.util.Random;

import dragonComponents.starInterface;
import guiPractice.components.GraphicMovingComponent;


/**
 * @author Tamanna
 *
 */
public class Star1 extends GraphicMovingComponent{

	private GameScreen game;
	private int dragonXPos;
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
		setVy(0.5);
		this.game = game;
		setVx(0);
		touched = false; 
	}
	
	@Override
	public void checkBehaviors() {
		int border = GameScreen.getHeight() - 100;
		if(getY() >= border){
			if(!touched && GameVioletta.vGame.checkStarContact(getX(), getWidth())){
				//System.out.println(getY());
				touched = true;
				int score = GameScreen.getScore() + 1;
				GameScreen.setScore(score);
				GameScreen.setScoreDisplay();
			}
			touched = true;
			game.removeStar(this);
		}
	}
// 		if(getScore() >= 25 && getScore() < 50)
// 			setVy(1);
}
