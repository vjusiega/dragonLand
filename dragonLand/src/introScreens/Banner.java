package introScreens;

import guiPractice.components.GraphicMovingComponent;

public class Banner extends GraphicMovingComponent{
	
	private int bannerHeight;

	public Banner(int x, int y, int w, int h, String imageLocation) {
		super(x, y, w, h, imageLocation);
		setVx(0);
		setVy(0.7);
	}

	@Override
	public void checkBehaviors() {
		if(getY() > bannerHeight){
			setVy(0);
		}
	}

	public void setBannerHeight(int h){
		bannerHeight = h;
	}
}
