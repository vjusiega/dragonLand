package game;

import java.util.ArrayList;

import dragonComponents.Dragon;
import dragonComponents.DragonLabel;
import dragonComponents.PriceLabel;
import dragonComponents.ShopBackdrop;
import game.DragonLand;
import game.ShopScreen;
import guiPractice.components.Action;
import guiPractice.components.Visible;

public class BuyScreenWendy extends ShopScreen{
	
	 private ArrayList<Dragon> dragonsInShop = new ArrayList<Dragon>();
	    private ArrayList<DragonLabel> shoplabels; 
	    private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	    private DragonLabel label;
	    
	    private int price = label.getDragonPrice().getPrice();;
	    //private Dragon sold;
	    private int x;
		private	int y;

	public BuyScreenWendy(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDragonLabels(ArrayList<Visible> visible) {
		// TODO Auto-generated method stub
		getArrowLeft().setAction(new Action(){

			@Override
			public void act() {
				// TODO Auto-generated method stub
				DragonLand.game.setScreen(DragonLand.game.homeScreen);
			}
			
		});
		
		getArrowRight().setAction(new Action(){

			@Override
			public void act() {
				// TODO Auto-generated method stub
				DragonLand.game.setScreen(DragonLand.game.buyScreen2);
			}
			
		});
		
		inLists();
		
//		sold = SellShopZheng.getSold();
//		if(sold != null)
//		{
//			dragonsInShop.add(sold);
//		}
		
		x = 0;
		y = 170;
		
		shoplabels = new ArrayList<DragonLabel>();
		
		for(Dragon d: dragons)
		{
			if(dragonsInShop.contains(d))
			{
				label = new DragonLabel(x,y, d,"BUY", new Action(){
					
					public void act() {
						// TODO Auto-generated method stub
//					shoplabels.remove(label);
						dragonsInShop.remove(label);	
						for(Visible v: label.getVisible())//ask Mr.Nockles for help
						{
							visible.remove(v);							
						}
						DragonLand.coins -= price;
						//System.out.println(DragonLand.coins);//don't know why it doesn't change in display
						update();
					}
				});
				shoplabels.add(label);
				
				for(Visible v: label.getVisible())
					visible.add(v);
				
				y = y + DragonLabel.getLabelHeight()+20;
				//System.out.println(dragons.size());
				System.out.println(dragonsInShop.size());
			}
			
		}
		
	}
	
	
	public void inLists(){
		
		dragonsInShop = new ArrayList<Dragon>();
		dragons = new ArrayList<Dragon>();
		
		dragons = HomeKat.getDragons();//ask Kat if there are only two dragons and why is the name "h"...
		
		for(int i = 0; i<1; i++)
		{
			dragonsInShop.add(dragons.get(i));
		}

		
	}
}
