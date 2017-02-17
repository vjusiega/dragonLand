/**
 * 
 */
package game;

import java.util.ArrayList;

import guiPractice.components.Graphic;

/**
 * @author Tamanna Hussain
 *
 */
public class Star extends Graphic {

	private double vy;
	private Graphic starImage;
	private boolean running;
	private long moveTime;
	
	public static final int REFRESH_RATE = 20;
	
	public Star(int x, int y, int w, int h) {
		super(x, y, w, h, "img/star.png");
	//	System.out.println("star");
		setX(getRandomX());
		setY(y);
		vy = 0;
		running = false;
	}
	
	private int getRandomX() {
		int randomX = (int) (Math.random()*GameScreen.getWidth()); 
		//System.out.println(randomX);
		return randomX;		
	}
	
	public void checkBehaviors() {
		//If the stars, fall after a certain point (y = 100), they disappear
		if(getY() > 100){
			setY(100);
			vy *= -5;
		}
	}

//	public void drawImage(Graphics2D g) {
//		// TODO Auto-generated method stub
//
//	}
	
// 	public void setStarSpeed(int x){
// 		starSpeed = x;
// 	}
	
// 	public int getStarSpeed(){
// 		return starSpeed;
// 	}
	
 	public void run() {
// 		posx = getX();
// 		posy = getY();
 		running = true;
 		moveTime = System.currentTimeMillis();
 		while(running){
 			try {
 				Thread.sleep(REFRESH_RATE);
 				checkBehaviors();
 				update();
 			} catch (InterruptedException e) {
 				e.printStackTrace();
 			}
 		}
 	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

 	public boolean isRunning() {
 		return running;
 	}

 	public void setRunning(boolean running) {
 		this.running = running;
 	}

 	public void play() {
 		if(!running){
 			Thread go = new Thread();
 			go.start();
 		}
 	}
}
