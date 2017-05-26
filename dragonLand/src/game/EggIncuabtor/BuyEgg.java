package game.EggIncuabtor;

import javax.swing.Action;

//import guiPractice.components.Action;
import game.DragonLand;
import game.mainScreenTeam.Dragon;
import game.newShop.ShopScreen;
import guiPractice.components.ClickableGraphic;
import java.awt.Color;
import guiPractice.components.Button;

public class BuyEgg {
	
	private ClickableGraphic background;
	private Egg e;

	public BuyEgg(double xLine, double yLine, int screenW, int screenH, Egg e){
		background = new ClickableGraphic(screenW, screenH, 1.0, "img/whiteBox.png", xLine, yLine);
		//setUpButton();
		this.e = e;
		e.placeOnLines(xLine, yLine, screenW, screenH);
		
	}
	
	public ClickableGraphic getBackdrop(){
		return background;
	}
	
	public Egg getEgg(){
		return e;
	}

//	public void setUpButton(){
//		background.setAction(new Action(){	
//			public void act(){
//				((ShopScreen)DragonLand.newShopScreen).buyDragon(drag);
//				((ShopScreen)DragonLand.newShopScreen).drawDragons();
//			}
//		});
//	}
	
}
