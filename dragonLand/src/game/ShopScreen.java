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
	public void initAllObjects(ArrayList<Visible> arg0) {
//		Button exit = new Button(getWidth() - 60,  10, 50, 50, "X", Color.RED, new Action(){
//			public void Act()
//			{
//				
//			}
//		});
		
		int titleWidth = 100;
		int titleHeight = 65;
		
		TextLabel shopTitle = new TextLabel(getWidth()/2 - titleWidth, getHeight()/2 - titleHeight, titleWidth, titleHeight, text);
		
		Button buy = new Button(x, y, w, h, text, color, action);
		Button sell = new Button(x, y, w, h, text, color, action);
	}

}
