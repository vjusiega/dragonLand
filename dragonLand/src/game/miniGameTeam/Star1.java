/**
 * 
 */
package game.miniGameTeam;

import java.util.Random;

import game.DragonLand;
import game.dragonTrading.TradingScreen;
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
		//powerUp = 0; 
	}
	
	@Override
	public void checkBehaviors() {
		int border = DragonLand.game.getHeight() - 200;
		if(GameVioletta.vGame.getPlaying()){
			if(getY() >= border && !touched && GameVioletta.vGame.checkStarContact(this)){
				touched = true;
				game.removeStar(this);
				setRunning(false);
				int score = GameScreen.getScore() + (GameVioletta.vGame.getDragonArray()).size();
				
				((GameScreen)DragonLand.miniGameScreen).setScore(score);
				((GameScreen)DragonLand.miniGameScreen).setScoreDisplay();
				
				GameScreen.tGame.setPowerUp((GameScreen.tGame.getPowerUp() + 1));
				if(GameScreen.tGame.getPowerUp() % 10 == 0){
					GameScreen.tGame.addDragonToScreen(GameVioletta.vGame.addDragon("img/dragon1.png"));
				}
			}

			else if(getY() > DragonLand.game.getHeight() - 100){
				GameScreen.tGame.setPowerUp(0);

				setRunning(false);
				touched = true;
				game.removeStar(this);
				GameScreen.tGame.removeDragonToScreen(GameVioletta.vGame.removeDragon());
			}
			
			//used these numbers for demonstration purposes
			if(GameScreen.getScore() >= 10 && GameScreen.getScore() < 20)
				setVy(1.2);
			if(GameScreen.getScore() >= 20 && GameScreen.getScore() < 30)
				setVy(1.4);
			if(GameScreen.getScore() >= 30 && GameScreen.getScore() < 40)
				setVy(1.6);
			if(GameScreen.getScore() >= 40){
				setVy(1.8);
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