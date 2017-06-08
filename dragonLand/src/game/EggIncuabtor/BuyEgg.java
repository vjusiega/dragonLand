package game.EggIncuabtor;

import guiPractice.components.Action;
//import guiPractice.components.Action;
import game.DragonLand;
import game.Sound;
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
		setUpButton();
		this.e = e;
		e.placeOnLines(xLine, yLine, screenW, screenH);
		e.setInitialX(e.getX());
	}
	
	public ClickableGraphic getBackdrop(){
		return background;
	}
	
	public Egg getEgg(){
		return e;
	}
	
	public void setUpButton(){
		background.setAction(new Action(){	
			public void act(){
				int size = ((ShopScreen) DragonLand.newShopScreen).getMyDragons().size();
				
				if (size+1<=9 && DragonLand.coins - e.getPrice()>=0){
					((IncubatorScreen) DragonLand.incubatorScreen).addEggToIncubator(e);
					DragonLand.coins -= e.getPrice();
					((IncubatorScreen) DragonLand.incubatorScreen).updateCoins();
					Sound.BOUGHT.play();
				}	
				else if(size+1>=9)
					((IncubatorScreen) DragonLand.incubatorScreen).addDragonError();
				else if(DragonLand.coins - e.getPrice() < 0)
					((IncubatorScreen) DragonLand.incubatorScreen).addCoinError();
			}});
	}
}
