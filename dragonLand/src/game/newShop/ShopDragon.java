package game.newShop;

import java.awt.Color;

import game.mainScreenTeam.Dragon;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;

public class ShopDragon {
	
	private ClickableGraphic background;
	private Dragon drag;
	private Button buyButton; 

	public ShopDragon(double xLine, double yLine, int screenW, int screenH, Dragon d){
		background = new ClickableGraphic(screenW, screenH, 1.1, "img/whiteBox.png", xLine, yLine);
		
		//set up dragon for display
		drag = d; 
		drag.setWidth((int)(background.getWidth() * 0.7));
		drag.setHeight((int)(background.getHeight() * 0.65));
		drag.placeDragonOnLines(screenW, screenH, xLine, yLine);
		drag.setDragonAnimation(d, d.getImgSrc());
		
		setUpButton();
	}
	
	public void setUpButton(){
		int buttonWidth = (int)(background.getWidth() * 0.8);
		int buttonHeight = (int)(background.getHeight() * 0.3);
		int buttonX = (background.getX() + (background.getWidth() / 2)) - (buttonWidth/2);
		int buttonY = (background.getY() + (int)(background.getWidth() * 0.7)) - (buttonHeight/2);
		
		buyButton = new Button(buttonX, buttonY, buttonWidth, buttonHeight, "BUY", Color.BLACK, null);
	}
	
	public Dragon getDragon(){
		return drag;
	}
	
	public Button getBuyButton(){
		return buyButton;
	}
	
	public ClickableGraphic getBackdrop(){
		return background;
	}
	
}
