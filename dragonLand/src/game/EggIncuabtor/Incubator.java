package game.EggIncuabtor;

import guiPractice.components.ClickableGraphic;

public class Incubator  extends ClickableGraphic{

	private Egg egg;
	private boolean busy;
	
	public Incubator(int x, int y, int w, int h, String imageLocation, Egg egg) {
		super(x, y, w, h, imageLocation);
		this.egg = egg;
		busy = false; 
	}
	public Egg getEgg(){
		return egg;
	}
	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	

	public void addEgg(Egg e){
		System.out.println("adding egg into incubator");
		egg = e;
		busy = true;
		//incubationTime = egg.getTime();
		//timePutIn = System.currentTimeMillis();
		//timeLeft = egg.getTime() ;
	}
	public void removeEgg(Egg e){
		egg = null;
		busy = false;
	}
	public  void placeOnLines(double xLine, double yLine, int sWidth, int sHeight) {
		setX((int)(sWidth * xLine) - (getWidth()/2));
		setY((int)(sHeight * yLine) - (getHeight()/2));
	}
	
}
