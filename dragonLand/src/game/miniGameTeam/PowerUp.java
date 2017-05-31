package game.miniGameTeam;

import guiPractice.components.GraphicMovingComponent;

public class PowerUp extends GraphicMovingComponent {

	public static PowerUp power;
	private boolean touched;
	private GameScreen game;
	private int dragonXPos;
	
	public PowerUp(int x, int y, int w, int h, GameScreen game) {
		super(x, y, w, h, "");
		setVy(1);
		this.game = game;
		setVx(0);
		touched = false; 
		
	}

	@Override
	public void checkBehaviors() {
		// TODO Auto-generated method stub

	}

}
