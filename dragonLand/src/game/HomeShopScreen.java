package game;

import java.awt.Color;
import java.util.ArrayList;

import dragonComponents.CoinLabel;
import dragonComponents.ShopActionButton;
import dragonComponents.ShopBackdrop;
import dragonComponents.ShopLabel;
import game.mainScreenTeam.HomeKat;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class HomeShopScreen extends ClickableScreen {
	
	public Button myButton;
	public CoinLabel coins;
	public ShopLabel dragonAmount;
	
	public HomeShopScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> visible) {
		int shopTopMargin = 50;
		int titleWidth = 300;
		int titleHeight = 50;	
		
		int buttonWidth = 150;
		int buttonHeight = 55;
		int topMargin = 40;
		
		int titleX = getWidth()/2 - titleWidth/2;
		int titleY = shopTopMargin + getHeight()/10;
		int shopBackHeight = (shopTopMargin + getHeight()/2 + topMargin);
		
		Graphic background=new Graphic(0,0,getWidth(),getHeight(),"img/Grassland.png");
		
		Button exit = new Button(getWidth() - 100,  60, 50, 40, "X", new Color(230,195,147), new Action(){
			
			public void act() {
				
				DragonLand.game.setScreen(DragonLand.homeScreen);
				HomeKat.dragonsOnScreen();
			}
		});
		
		ShopBackdrop shopBack = new ShopBackdrop(titleX - 30, titleY - 30, titleWidth + 60, shopBackHeight);
		ShopLabel shopTitle = new ShopLabel(titleX, titleY, titleWidth, titleHeight, "Dragon Shop");
		shopTitle.setSize(28);
		ShopActionButton buy = new ShopActionButton(getWidth()/2 - buttonWidth/2, shopTopMargin + getHeight()/4, buttonWidth, buttonHeight, "BUY", new Color(230,195,147), new Action(){
			public void act()
			{
				((ShopScreen) DragonLand.buyScreen).getCoins().setCoins();
				DragonLand.game.setScreen(DragonLand.buyScreen);
			}
		});
		ShopActionButton sell = new ShopActionButton(getWidth()/2 - buttonWidth/2, shopTopMargin + getHeight()/2 - topMargin, buttonWidth, buttonHeight, "SELL", new Color(230,195,147), new Action(){
			public void act()
			{
				((ShopScreen) DragonLand.sellScreen).getCoins().setCoins();
				DragonLand.game.setScreen(DragonLand.sellScreen);
			}
		});
		
		coins = new CoinLabel(titleX - 20, shopBackHeight + 30, DragonLand.coins, DragonLand.DARKER_NUDE);
		dragonAmount = new ShopLabel(titleX + CoinLabel.getWdith(), shopBackHeight + 30, CoinLabel.getWdith(), CoinLabel.getHeight2(),"0/6 Dragons", DragonLand.DARKER_NUDE);
		dragonAmount.setArc(15);
		
		visible.add(background);
		visible.add(shopBack);
		visible.add(exit);
		visible.add(shopTitle);
		visible.add(buy);
		visible.add(sell);
		visible.add(coins);
		visible.add(dragonAmount);
	}
	
	public void updateHomeShopLabels()
	{
		coins.setCoins();
		dragonAmount.setText(((SellShopZheng) DragonLand.sellScreen).getDragonsInSellShop().size() + "/6 Dragons");
	}
}