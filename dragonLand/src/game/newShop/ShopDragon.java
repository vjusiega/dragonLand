package game.newShop;

import java.awt.Color;

import game.DragonLand;
import game.mainScreenTeam.Dragon;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;

public class ShopDragon {
	
	private ClickableGraphic background;
	private Dragon drag;
	
	public ShopDragon(double xLine, double yLine, int screenW, int screenH, Dragon d){
		background = new ClickableGraphic(screenW, screenH, 1.0, "img/whiteBox.png", xLine, yLine);
//		
		//set up dragon for display
		drag = d; 
		drag.setWidth((int)(background.getWidth() * 0.7));
		drag.setHeight((int)(background.getHeight() * 0.65));
		drag.placeDragonOnLines(screenW, screenH, xLine, yLine);
		drag.setDragonAnimation(d, d.getImgSrc());
		setUpButton();
		
	}
	
	public void setUpButton(){

		background.setAction(new Action(){
			
			public void act(){
				((ShopScreen)DragonLand.newShopScreen).buyDragon(drag);
				((ShopScreen)DragonLand.newShopScreen).drawDragons();
			}
		});
		
	}
	
	public Dragon getDragon(){
		
		return drag;
	}
	
	public ClickableGraphic getBackdrop(){
		return background;
	}
	
}
