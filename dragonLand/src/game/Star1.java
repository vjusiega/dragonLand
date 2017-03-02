/**
 * 
 */
package game;

import java.util.Random;

import dragonComponents.StarInterface;
import guiPractice.components.GraphicMovingComponent;


/**
 * @author Tamanna
 *
 */
public class Star1 extends GraphicMovingComponent implements StarInterface{

	public static Star1 tStar;
	
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
		tStar = this;
	}
	
	@Override
	public void checkBehaviors() {
		int border = GameScreen.getHeight() - 200;
		if(getY() >= border && !touched && GameVioletta.vGame.checkStarContact(this)){
			touched = true;
			game.removeStar(this);
			int score = GameScreen.getScore() + 1;
			GameScreen.setScore(score);
			GameScreen.setScoreDisplay();
		}
		else if(getY() > GameScreen.getHeight() - 100){
			touched = true;
			game.removeStar(this);
		}
		
		if(GameScreen.getScore() >= 5 && GameScreen.getScore() < 10)
			setVy(1);
		if(GameScreen.getScore() >= 10 && GameScreen.getScore() < 15)
			setVy(1.5);
		if(GameScreen.getScore() >= 15 && GameScreen.getScore() < 20)
			setVy(2);
	}

	@Override
	public int starStartPos() {
		return this.getX();
	}

	@Override
	public int starEndPos() {
		return this.getX() + this.getWidth();
	}
}
