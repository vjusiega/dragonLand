package game;

import java.util.ArrayList;

import DragonLabelComponent.DragonLabel;
import DragonLabelComponent.PriceLabel;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class BuyScreenW extends ClickableScreen {

   // private ArrayList<Dragons> dragonsInShop;
    private ArrayList<DragonLabel> shoplabels; 
    private DragonLabel label;
   // private Dragon[] dragons;
    private SellScreenInterface sold;
    private ShopBackBoxW back;
    private int price;
    private PriceLabel priceLabel;

	public BuyScreenW(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(ArrayList<Visible> arg0) {
		// TODO Auto-generated method stub
		
		back = new ShopBackBoxW(0,0,getWidth(),getHeight());
		
		int titleWidth = 100;
		int titleHeight = 65;

		TextLabel shopTitle = new TextLabel(getWidth()/2 - titleWidth/2, getHeight()/2 - titleHeight/2, titleWidth, titleHeight, "Dragon Shop");
		
		addObject(shopTitle);
		
		price = DragonLand.coins;
		priceLabel = new PriceLabel(getWidth() - titleWidth, getHeight()/2 - titleHeight/2, 100, 50, price);
		
		addObject(priceLabel);
		
//		Dragons[] dragons = DragonLand.DragonList;
//		for(int i = 0; i<dragons[3];i++)
//		{
//			label = new DragonLabel(0,0, dragons[i],"BUY", new Action(){
//				@Override
//				public void act() {
//					// TODO Auto-generated method stub
//					shoplabels.remove(label);
//					dragonsInShop.remove(dragons[i]);	
//					remove(label);
//				}
//			});
//			shoplabels.add(label);
//			dragonsInShop.add(dragons[i]);
//			addObject(label);
//		}
		
	}
	

}
