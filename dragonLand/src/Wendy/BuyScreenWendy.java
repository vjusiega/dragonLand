package Wendy;

import java.util.ArrayList;

import dragonComponents.Dragon;
import dragonComponents.DragonLabel;
import dragonComponents.PriceLabel;
import dragonComponents.ShopBackdrop;
import game.ShopScreen;
import guiPractice.components.Action;
import guiPractice.components.Visible;

public class BuyScreenWendy extends ShopScreen{
	
	 private ArrayList<Dragon> dragonsInShop;
	    private ArrayList<DragonLabel> shoplabels; 
	    private Dragon[] dragons;
	    private DragonLabel label;
	    
	    private int price;
	    private PriceLabel priceLabel;
	   // private Dragon sold;
	    private int x;
		private	int y;

	public BuyScreenWendy(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void addDragonLabels(ArrayList<Visible> visible) {
		// TODO Auto-generated method stub
		inLists();
		
		x = 0;
		y = 170;
		
		shoplabels = new ArrayList<DragonLabel>();
		
		for(int i= 0; i< dragons.length;i++)
		{
			if(dragonsInShop.contains(dragons[i]))
			{
				 label = new DragonLabel(x,y, dragons[i],"BUY", new Action(){

					public void act() {
						// TODO Auto-generated method stub
//					shoplabels.remove(label);
//					dragonsInShop.remove(label);	
						for(Visible v: label.getVisible())
							visible.remove(v);
						//DragonLand.coins -= this.priceLabel.getPrice();
						//DragonLand.game.setScreen(DragonLand.homeScreen);
					}
				});
				shoplabels.add(label);
				
				dragonsInShop.add(dragons[i]);
				
				for(Visible v: label.getVisible())
					visible.add(v);
				
				y = y + DragonLabel.getLabelHeight()+20;
			}
				
		}
		
	}
	
	public void inLists(){
		
		dragonsInShop = new ArrayList<Dragon>();
		dragons = new Dragon[3];
		for(int i= 0; i< dragons.length;i++)
		{
			dragons[i] = new Dragon(50, 50, 50, 50, "Nice Dragon" + i, 100, "img/dragon9.png");
			dragonsInShop.add(dragons[i]);
		}
	}
}
