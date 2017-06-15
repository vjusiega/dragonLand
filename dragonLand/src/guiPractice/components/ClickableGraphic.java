package guiPractice.components;

public class ClickableGraphic extends Graphic implements Clickable {

	private Action action;

	public ClickableGraphic(int x, int y, String imageLocation) {
		super(x, y, imageLocation);
	}

	public ClickableGraphic(int x, int y, int w, int h, String imageLocation) {
		super(x, y, w, h, imageLocation);
	}

	public void setAction(Action a){
		this.action=a;
	}
	
	public ClickableGraphic(int x, int y, double scale, String imageLocation) {
		super(x, y, scale, imageLocation);
	}
	
	public ClickableGraphic(int screenWidth, int screenHeight, double scale, String imageLocation, double xLine, double yLine){
		super(0, 0, scale, imageLocation);
		
		
		setX((int)(screenWidth*xLine) - (getWidth()/2));
		setY((int)(screenHeight*yLine) - (getHeight()/2));
	}
	public ClickableGraphic(int screenWidth, int screenHeight, double scale, String imageLocation, double xLine, double yLine, Action action){
		super(0, 0, scale, imageLocation);
		setX((int)(screenWidth*xLine) - (getWidth()/2));
		setY((int)(screenHeight*yLine) - (getHeight()/2));
		this.action = action;
	}
	@Override
	public boolean isHovered(int x, int y) {
		return (x>getX()&&y>getY()+25&&x<getX()+getWidth()&&y<getY()+25+getHeight());
	}

	@Override
	public void act() {
		if(action !=null)
			action.act();
	}

	@Override
	public Action getAction() {
		return action;
	}

}
