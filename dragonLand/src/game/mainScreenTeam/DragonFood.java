package game.mainScreenTeam;



import guiPractice.components.Graphic;

public class DragonFood extends Graphic implements Draggable {
	
	private int originalX;
	private int originalY;
	public DragonFood(int x, int y, int w, int h, String imageLocation) {
		super(x, y, w, h, imageLocation);
		// TODO Auto-generated constructor stub
		originalX=x;
		originalY=y;
	}

	@Override
	public boolean isHovered(int x, int y) {
		if(x>getX()&&x<(getX()+getWidth())&&y>getY()&&y<(getY()+getHeight())){
			return true;
		}
		return false;
	}

	public int getOrigY(){
		return originalY;
	}

	public int getOrigX(){
		return originalX;
	}
}
