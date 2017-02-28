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
public class Star1 extends GraphicMovingComponent implements starInterface{

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
		setVy(2);
		this.game = game;
		setVx(0);
		touched = false; 
//		run();
	}
	
	public int getDragonXPos(int dragonX){
		dragonXPos = dragonX;
		return dragonXPos;
	}
	
	@Override
	public void checkBehaviors() {
		if(getY() >= 560){
			if(!touched && GameVioletta.vGame.checkStarContact(getX(), getWidth())){
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
