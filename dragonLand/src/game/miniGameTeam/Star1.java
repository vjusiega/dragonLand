/**
 * 
 */
package game.miniGameTeam;

import java.util.Random;

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
	private boolean test;
	
	private int powerUp;
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param imageLocation
	 */
	public Star1(int x, int y, int w, int h, GameScreen game) {
		super(x, y, w, h, "img/star.png");
		setVy(1);
		this.game = game;
		setVx(0);
		touched = false; 
		tStar = this;
		test = false;
		powerUp = 0; 
	}
	
	@Override
	public void checkBehaviors() {
		int border = GameScreen.getHeight() - 200;
		if(GameVioletta.vGame.getPlaying()){
			if(getY() >= border && !touched && GameVioletta.vGame.checkStarContact(this)){
				touched = true;
				game.removeStar(this);
				setRunning(false);
				int score = GameScreen.getScore() + (GameVioletta.vGame.getDragonArray()).size();
				GameScreen.setScore(score);
				GameScreen.setScoreDisplay();
				powerUp++;
				if(powerUp == 10){
					GameScreen.tGame.addDragonToScreen(GameVioletta.vGame.addDragon("img/dragon1.png"));
				}
			}
			else if(getY() > GameScreen.getHeight() - 100){
				powerUp = 0; 
				setRunning(false);
				touched = true;
				game.removeStar(this);
				GameScreen.tGame.removeDragonToScreen(GameVioletta.vGame.removeDragon());
			}
			
			//used these numbers for demonstration purposes
			if(GameScreen.getScore() >= 5 && GameScreen.getScore() < 10)
				setVy(1.5);
			if(GameScreen.getScore() >= 10 && GameScreen.getScore() < 15)
				setVy(2);
			if(GameScreen.getScore() >= 15 && GameScreen.getScore() < 20)
				setVy(2.5);
			if(GameScreen.getScore() >= 20){
				setVy(2.5);
			}		
		}else{
			//HighScoreScreen.setRoundScore(GameScreen.getScore());
			//HighScoreScreen.updateOnEnter();
			game.removeStar(this);
			setRunning(false);
		}
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
