package game;

import java.awt.Color;
import java.util.ArrayList;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class ShopScreen extends ClickableScreen {

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
		
		Button buy = new Button(getWidth()/2 - buttonWidth/2, getHeight()/2 - buttonHeight/2, buttonWidth, buttonHeight, "BUY", Color.cyan, new Action(){
			public void act()
			{
				
			}
		});
		Button sell = new Button(getWidth()/2 - buttonWidth/2, getHeight()/2 - buttonHeight/2, buttonWidth, buttonHeight, "SELL", Color.cyan, new Action(){
			public void act()
			{
				
			}
		});
		
		addObject(shopTitle);
		addObject(buy);
		addObject(sell);
	}

}
