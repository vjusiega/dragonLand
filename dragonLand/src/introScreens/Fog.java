package introScreens;

import guiPractice.Screen;
import guiPractice.components.GraphicMovingComponent;

public class Fog extends GraphicMovingComponent {
	
	//this class can be used for any image that is moving.

	public Fog(int x, int y, int w, int h, String imageLocation, int vx, int vy) {
		super(x, y, w, h, imageLocation);
		setVx(vx);
		setVy(vy);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkBehaviors() {
		if(getX() > Screen.getWidth()){
			setX(0);
		}
	}

}
