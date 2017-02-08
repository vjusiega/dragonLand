package game;

import java.util.ArrayList;

import dragonComponents.DragonLabel;
import dragonComponents.PriceLabel;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class BuyScreenW extends ClickableScreen {

    private ArrayList<Dragons> dragonsInShop;
    private ArrayList<DragonLabel> shoplabels; 
    private DragonLabel label;
    private Dragon[] dragons;
    
    private int price;
    private PriceLabel priceLabel;
    private Dragon sold;
    private int x;
	private	int y;

	public BuyScreenW(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(ArrayList<Visible> arg0) {
		// TODO Auto-generated method stub
		
		int titleWidth = 100;
		int titleHeight = 65;

		TextLabel shopTitle = new TextLabel(getWidth()/2 - titleWidth/2, getHeight()/2 - titleHeight/2, titleWidth, titleHeight, "Dragon Shop");
		
		addObject(shopTitle);
		
		price = DragonLand.coins;
		priceLabel = new PriceLabel(getWidth() - titleWidth, getHeight()/2 - titleHeight/2, 100, 50, price);
		
		addObject(priceLabel);
		
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
	
	private SellScreenInterface getSoldDragon(){
		
	}

}
