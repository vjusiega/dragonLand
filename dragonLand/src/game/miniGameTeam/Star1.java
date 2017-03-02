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
		test = false;
	}
	
	@Override
	public void checkBehaviors() {
		int border = GameScreen.getHeight() - 200;
//		System.out.println(GameVioletta.vGame.getPlaying());
		if(GameVioletta.vGame.getPlaying()){
			if(getY() >= border && !touched && GameVioletta.vGame.checkStarContact(this)){
				touched = true;
				game.removeStar(this);
				setRunning(false);
				int score = GameScreen.getScore() + 1;
				GameScreen.setScore(score);
				GameScreen.setScoreDisplay();
				if(score == 5 || score == 10 || score == 15){
					GameScreen.tGame.addDragon(GameVioletta.vGame.addDragon("img/dragon1.png"));
				}
			}
			else if(getY() > GameScreen.getHeight() - 100){
				System.out.println("I am here");
				setRunning(false);
				touched = true;
//				if(!test){
//					test = true;
					game.removeStar(this);
					GameScreen.tGame.removeDragon(GameVioletta.vGame.removeDragon());
//				}
				
				
			}
			if(GameScreen.getScore() >= 5 && GameScreen.getScore() < 10)
				setVy(1);
			if(GameScreen.getScore() >= 10 && GameScreen.getScore() < 15)
				setVy(1.5);
			if(GameScreen.getScore() >= 15 && GameScreen.getScore() < 20)
				setVy(2);
		
		}else{
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
