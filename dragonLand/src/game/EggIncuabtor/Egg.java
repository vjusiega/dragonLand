package game.EggIncuabtor;

import java.awt.Font;
import java.awt.Graphics2D;

import game.DragonLand;
import guiPractice.components.GraphicMovingComponent;
import guiPractice.components.TextLabel;

public class Egg extends GraphicMovingComponent {

	private int price;
	private String category;
	private int incubationTime;
	private boolean incubating;
	private String imgSrc;
	private long timeEnteredIncubation;
	private long currentTime;
	private TextLabel timer;
	
	public Egg(int x, int y, int w, int h, String imageLocation, String category, int price, int time) {
		super(x, y, w, h, imageLocation);
		this.category = category;
		this.incubating = false;
		this.price = price;
		this.incubationTime = time; 
		this.imgSrc = imageLocation;	
		timer = new TextLabel(x-39, y+75, 500, 30, "Time Left: ");
		//timer.setFont();
		//timer.setSize(1);
	}

	
	public long getTimeEnteredIncubation() {
		return timeEnteredIncubation;
	}


	public void setTimeEnteredIncubation(long timeEnteredIncubation) {
		this.timeEnteredIncubation = timeEnteredIncubation;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		if(incubating)
			DragonLand.incubatorScreen.addObject(timer);
		if(!incubating)
			DragonLand.incubatorScreen.remove(timer);
		currentTime = System.currentTimeMillis();
		
		int timeLeft = (int) (incubationTime - (currentTime - timeEnteredIncubation )/1000);
		timer.setText("Time Left: "+ timeLeft);

	}

	private boolean shiftRight = true;
	private boolean shake = true;
	private int countShake =(int) Math.random()*10;
	//finetransform
	@Override
	public void checkBehaviors() {
		currentTime = System.currentTimeMillis();
		
		if(incubating && ((int)((currentTime - timeEnteredIncubation)/1000) > incubationTime))
			System.out.println("hatch");
		
		else{
			int timeLeft = (int) (incubationTime - (currentTime - timeEnteredIncubation )/1000);
			timer.setText("Time Left: "+ timeLeft+"s");	
			
		}
		
		if(shake){
			if(shiftRight){
				setX(getX()+5);
				shiftRight = false;
			}
			else{
				setX(getX()-5);
				shiftRight = true;
			}
		}
		if(countShake%20 == 0)
			shake = !shake;
		
		if(incubating){
					
		}
		countShake++;
	}
	
	public void placeOnLines(double xLine, double yLine, int sWidth, int sHeight){
		setX((int)(sWidth * xLine) - (getWidth()/2));
		setY((int)(sHeight * yLine) - (getHeight()/2));
	}

}
