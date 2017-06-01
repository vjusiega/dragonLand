package game.miniGameTeam;

import guiPractice.components.GraphicMovingComponent;

public class PowerUp extends GraphicMovingComponent {

	public static PowerUp power;
	private boolean touched;
	private GameScreen game;
	private int dragonXPos;
	
	public PowerUp(int x, int y, int w, int h, GameScreen game) {
		super(x, y, w, h, "img/strawberry.png");
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
				long end = time + 15000;
				while(System.currentTimeMillis() < end) {
					//change the speed of dragon from (-10) to (-20)
				}
				//change the speed back to norma;
			}
		}			
		else{
			game.removePowerUp(this);
		}
	}
}
