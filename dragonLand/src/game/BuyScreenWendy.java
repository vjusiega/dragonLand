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
	
	 private ArrayList<Dragon> dragonsInShop;
	    private ArrayList<DragonLabel> shoplabels; 
	    private Dragon[] dragons;
	    private DragonLabel label;
	    
	    private int price;
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
		
		for(int i= 0; i< 3;i++)
		{
			if(dragonsInShop.contains(dragons[i]))
			{
				 label = new DragonLabel(x,y, dragons[i],"BUY", new Action(){

					public void act() {
						// TODO Auto-generated method stub
//					shoplabels.remove(label);
					dragonsInShop.remove(label);	
						for(Visible v: label.getVisible())
						{
							visible.remove(v);							
						}
						price = label.getDragonPrice().getPrice();
						DragonLand.coins -= price;
						System.out.println(DragonLand.coins);//don't know why it doesn't change in display
						update();
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
		dragons = new Dragon[20];
		
		String[] names = new String[] {"Rowdy","Thorn","Mushu","Falcor","Elliot","Puff","Spyro","Sandy",
				"Scaly","Nessie","Nymph","Sparky","Flambi","Drago","Viper","Moon","Saphira","Scorch","Toothless","Stormfly"};
		price=50;
		
		for(int i=1;i<20;i++){
			
			//dragons[i] = new Dragon(50, 50, 50, 50, "Nice Dragon" + i, 100, "img/dragon9.png");
			dragons[i] = new Dragon(0,0,50,50, names[i], price+i*50, "img/dragon"+i+".png");
			//dragons[i] = HomeKat.getDragonList().[i];
			dragonsInShop.add(dragons[i]);
		}

		
	}
}
