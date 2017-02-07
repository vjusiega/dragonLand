package game;

import java.awt.Color;
import java.util.ArrayList;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class ShopScreen extends ClickableScreen {
	
	/*
	 * Color of title (224, 102, 102)
	 * Color of buttons (230,195,147)
	 * 
	 * Color of title,catalog in buy screen and sell screen (222,195,166)
	 * Color of buy/sell button (244,215,183)
	 */
	

	public ShopScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(ArrayList<Visible> visible) {
//		Button exit = new Button(getWidth() - 60,  10, 50, 50, "X", Color.RED, new Action(){
//			public void Act()
//			{
//				
//			}
//		});
		
		int titleWidth = 100;
		int titleHeight = 65;
		

		TextLabel shopTitle = new TextLabel(getWidth()/2 - titleWidth/2, getHeight()/2 - titleHeight/2, titleWidth, titleHeight, "Dragon Shop");

		int buttonWidth = 90;
		int buttonHeight = 55;
		
		Button buy = new Button(getWidth()/2 - buttonWidth/2, getHeight()/2 - buttonHeight/2, buttonWidth, buttonHeight, "BUY", new Color(230,195,147), new Action(){
			public void act()
			{
				DragonLand.game.setScreen(buyScreen);
			}
		});
		Button sell = new Button(getWidth()/2 - buttonWidth/2, getHeight()/2 - buttonHeight/2, buttonWidth, buttonHeight, "SELL", new Color(230,195,147), new Action(){
			public void act()
			{
				DragonLand.game.setScreen(sellScreen);
			}
		});
		
		addObject(shopTitle);
		addObject(buy);
		addObject(sell);
}
}