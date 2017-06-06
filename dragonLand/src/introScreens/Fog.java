package introScreens;

import java.awt.Graphics2D;

import game.DragonLand;
import guiPractice.Screen;
import guiPractice.components.GraphicMovingComponent;

public class Fog extends GraphicMovingComponent {
	
	private int initialY;
	private int heightVariance;

	public Fog(int x, int y, int w, int h, String imageLocation, int heightVariance) {
		super(x, y, w, h, imageLocation);
		this.heightVariance = heightVariance;
		setVx(generateRandomSpeed());
		setVy(0);
		initialY = y; 
		setY(generateYPos());
	}

	@Override
	public void checkBehaviors() {
		if(running){
			if(getX() > 1000){
				setX(-1*this.getWidth());
				setY(generateYPos());
				setVx(Math.random());
			}
		}
	}
	
	public int generateYPos(){
		int add = (int) (Math.random() * heightVariance);
		int multiplier = (int) (Math.random() * 2) + 1;
		
		return initialY + (add * multiplier);
	}
	
	boolean init;
	
	public void drawImage(Graphics2D g){
		if(!init){
			super.drawImage(g);
			init = true;
		}
	}
	
	public double generateRandomSpeed(){
		double random = Math.random();
		if(random < 0.5){
			random = random * 2;
		}
		if(random < 0.1){
			random = random * 10; 
		}
		if(random > 0.8){
			random = random / 2;
		}
		return random;
	}

}
