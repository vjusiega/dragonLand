package game.miniGameTeam;

import game.DragonLand;
import guiPractice.components.GraphicMovingComponent;

public class PowerUp extends GraphicMovingComponent {

	public static PowerUp power;
	private boolean touched;
	private GameScreen game;
	private int dragonXPos;
	
	public PowerUp(int x, int y, int w, int h, GameScreen game) {
		super(x, y, w, h, "img/berry.png");
		setVy(1.5);
		this.game = game;
		setVx(0);
		touched = false; 
		
	}
	
	@Override
	public void checkBehaviors() {
		int border = DragonLand.game.getHeight() - 200;
		if(GameVioletta.vGame.getPlaying()){
			if(getY() >= border && !touched && GameVioletta.vGame.checkPowerUpContact(this)){
				touched = true;
				game.removePowerUp(this);
				long time = System.currentTimeMillis();
				long end = time + 10000;
				while(System.currentTimeMillis() < end) {
					GameScreen.setSpeedLeft(-20);
					GameScreen.setSpeedRight(20);
				}
			}
			GameScreen.setSpeedLeft(-10);
			GameScreen.setSpeedRight(10);
		}			
		else{
			game.removePowerUp(this);
		}
	}
}
