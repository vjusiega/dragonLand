package Wendy;

import java.awt.Color;
import java.util.ArrayList;

import dragonComponents.DragonLabel;
import dragonComponents.PriceLabel;
import dragonComponents.ShopLabel;
import game.DragonLand;
import game.ShopScreen;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class BuyScreenW extends ShopScreen {

   // private ArrayList<Dragons> dragonsInShop;
    private ArrayList<DragonLabel> shoplabels; 
    private DragonLabel label;
   // private Dragon[] dragons;
    
    private int price;
    private PriceLabel priceLabel;
   // private Dragon sold;
    private int x;
	private	int y;

	public BuyScreenW(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(ArrayList<Visible> visible) {
		// TODO Auto-generated method stub
		
		Button exit = new Button(getWidth() - 100,  60, 50, 40, "X", new Color(230,195,147), new Action(){
			
			public void act() {
				// TODO Auto-generated method stub
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
		
		visible.add(exit);
		
		int titleWidth = 100;
		int titleHeight = 65;

		ShopLabel shopTitle = new ShopLabel(50, 50, titleWidth*4, titleHeight, "Dragon Shop");
		
		visible.add(shopTitle);
		
		
		x = 0;
		y = 0;
		
		Dragons[] dragons = DragonLand.DragonList;
		for(int i = 0; i<dragons[3];i++)
		{
			label = new DragonLabel(x,y, dragons[i],"BUY", new Action(){
				@Override
				public void act() {
					// TODO Auto-generated method stub
					shoplabels.remove(label);
					dragonsInShop.remove(dragons[i]);	
					remove(label);
					DragonLand.coins -= dragons[i].price;
				}
			});
			shoplabels.add(label);
			dragonsInShop.add(dragons[i]);
			addObject(label);
			
			y = y + DragonLabel.getLabelHeight();
		}	
		
		sold = SellScreenZ.getSold();
		if(sold != null)
		{
			label = new DragonLabel(x,y,sold,"BUY", new Action(){
				@Override
				public void act() {
					// TODO Auto-generated method stub
					shoplabels.remove(label);
					dragonsInShop.remove(dragons[i]);	
					remove(label);
					DragonLand.coins -= dragons[i].price;
				}
			});
			shoplabels.add(label);
			dragonsInShop.add(sold);
			addObject(label);
		}
	}
	

}
