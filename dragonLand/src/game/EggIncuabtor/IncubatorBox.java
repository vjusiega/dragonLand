package game.EggIncuabtor;

import guiPractice.components.ClickableGraphic;

public class IncubatorBox {
	private ClickableGraphic background;
	private Incubator incubator;

	public IncubatorBox(double xLine, double yLine, int screenW, int screenH, Incubator incubator){
		background = new ClickableGraphic(screenW, screenH, 1.0, "img/whiteBox.png", xLine, yLine);
		//setUpButton();
		this.incubator = incubator;
		incubator.placeOnLines(xLine, yLine, screenW, screenH);
		
	}
	
	public ClickableGraphic getBackdrop(){
		return background;
	}
	
	public Incubator getIncubator(){
		return incubator;
	}

}
