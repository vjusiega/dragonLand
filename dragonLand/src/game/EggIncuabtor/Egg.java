package game.EggIncuabtor;

import java.awt.Font;
import java.awt.Graphics2D;

import game.DragonLand;
import game.newShop.ShopScreen;
import guiPractice.components.GraphicMovingComponent;
import guiPractice.components.TextLabel;

public class Egg extends GraphicMovingComponent {

	private int price;
	private String category;
	//limit time
	private int incubationTime;
	private boolean incubating;
	private String imgSrc;
	//when bought
	private long timeEnteredIncubation;
	//time i use to later compare the elapsed time
	private long currentTime;
	private TextLabel timer;
	private boolean shiftRight = true;
	private boolean shake = true;
	private int countShake =(int) Math.random()*10;
	private int initialX;
	private int moveToX;
	
	public Egg(int x, int y, int w, int h, String imageLocation, String category, int price, int time) {
		super(x, y, w, h, imageLocation);
		this.category = category;
		this.incubating = false;
		this.price = price;
		this.incubationTime = time; 
		this.imgSrc = imageLocation;	
		timer = new TextLabel(x-39, y+75, 500, 30, "Time Left: ");
	}

	public void setIncubating(boolean incubating) {
		this.incubating = incubating;
		if(incubating)
			DragonLand.incubatorScreen.addObject(timer);
		if(!incubating){
			DragonLand.incubatorScreen.remove(timer);
			DragonLand.incubatorScreen.remove(this);
			((IncubatorScreen) DragonLand.incubatorScreen).removeEggFromIncubator(this);
			String name = ((ShopScreen) DragonLand.newShopScreen).getHatchedName();
			((IncubatorScreen) DragonLand.incubatorScreen).placeAlert(name);
		}
		currentTime = System.currentTimeMillis();
		
		int timeLeft = (int) (incubationTime - (currentTime - timeEnteredIncubation )/1000);
		timer.setText("Time Left: "+ timeLeft);

	}

	
	//finetransform
	@Override
	public void checkBehaviors() {
		currentTime = System.currentTimeMillis();
		//hatch check
		if(incubating && ((int)((currentTime - timeEnteredIncubation)/1000) > incubationTime)){
			((ShopScreen) DragonLand.newShopScreen).hatchEgg(category);
			
			setIncubating(false);
			this.setRunning(false);
		}
		
		else{
			int timeLeft = (int) (incubationTime - (currentTime - timeEnteredIncubation )/1000);
			timer.setText("Time Left: "+ timeLeft+"s");	
			
		}
		//movement
		if(shake){
			
			if(shiftRight){
				setX(moveToX);
				shiftRight = false;
			}
			else{
				setX(initialX);
				shiftRight = true;
			}
		}
		//makes it stop shaking
		if(countShake%20 == 0)
			shake = !shake;
		
		countShake++;
		
	}
	public void setInitialX(int x){
		initialX = x - 3;
		moveToX = x + 3;
	}
	public void placeOnLines(double xLine, double yLine, int sWidth, int sHeight){
		setX((int)(sWidth * xLine) - (getWidth()/2));
		setY((int)(sHeight * yLine) - (getHeight()/2));
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
}
