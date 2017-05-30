package game.EggIncuabtor;

import guiPractice.components.GraphicMovingComponent;

public class Egg extends GraphicMovingComponent {

	private int price;
	private String name;
	private int incubationTime;
	private boolean incubating;
	private String imgSrc;
	
	public Egg(int x, int y, int w, int h, String imageLocation, String name, int price, int time) {
		super(x, y, w, h, imageLocation);
		this.name = name;
		this.incubating = false;
		this.price = price;
		this.incubationTime = time; 
		this.imgSrc = imageLocation;
	}
	
	public int getTime(){
		return incubationTime;
	}
	
	public String getImgSrc(){
		return imgSrc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIncubationTime() {
		return incubationTime;
	}

	public void setIncubationTime(int incubationTime) {
		this.incubationTime = incubationTime;
	}

	public boolean isIncubating() {
		return incubating;
	}

	public void setIncubating(boolean incubating) {
		this.incubating = incubating;
	}

	@Override
	public void checkBehaviors() {
		// TODO Auto-generated method stub

	}
	
	public void placeOnLines(double xLine, double yLine, int sWidth, int sHeight){
		setX((int)(sWidth * xLine) - (getWidth()/2));
		setY((int)(sHeight * yLine) - (getHeight()/2));
	}

}
