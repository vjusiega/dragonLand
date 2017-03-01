/**
 * 
 */
package game;

import java.util.Random;

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
		run();
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
	
	@Override
	public void run() {
		Random rand = new Random();
		int val = rand.nextInt(4) + 1;
		int chance = (int) ((Math.random() * 8) + 1);
		if (val == 1) { 
			//1/4 of the time
			for(int i = 0; i < chance; i++){
				
			}
		} else { 
			//3/4 of the time
			for(int i = 0; i < chance; i++){
				
			}
		}
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}