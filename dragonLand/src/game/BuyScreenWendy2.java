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
/*
 * @author Wendy
 * */

public class BuyScreenWendy2 extends ShopScreen{
	
	 private ArrayList<Dragon> dragonsInShop = new ArrayList<Dragon>();
	    private ArrayList<DragonLabel> shoplabels; 
	    private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
//	   private DragonLabel label;
	    
//	    private int price = label.getDragonPrice().getPrice();
	    //private Dragon sold;
	    private int x;
		private	int y;
		//private int numOfDragons = 0;

	public BuyScreenWendy2(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDragonLabels(ArrayList<Visible> visible) {
		// TODO Auto-generated method stub
//		getPage().setText("Page " + BuyScreenWendy.getPageNum() + "/2");
//		getDragonAmount().setText( BuyScreenWendy.getNumOfDragon() +"/6 dragons");
		
		getArrowLeft().setAction(new Action(){

			@Override
			public void act() {
				// TODO Auto-generated method stub
				update();
				DragonLand.game.setScreen(DragonLand.game.buyScreen);
			}
			
		});
		
		getArrowRight().setAction(new Action(){

			@Override
			public void act() {
				// TODO Auto-generated method stub

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
		
		
		for(Dragon d: dragons)
		//for(int i = 0; i< dragonsInShop.size(); i++)
		{
			if(dragonsInShop.contains(d))
			{
				DragonLabel label = new DragonLabel(DragonLabel.LABEL_LEFT_MARGIN,y, d,"BUY");
				label.setAction( new Action(){
					
					public void act() {
						// TODO Auto-generated method stub

						if(DragonLand.coins > d.getPrice())
						{
							update();
							dragonsInShop.remove(d);	
							visible.remove(label);			
							
<<<<<<< HEAD
							BuyScreenWendy.setNumOfDragon();
							//getDragonAmount().setText( BuyScreenWendy.getNumOfDragon() +"/6 dragons");
							//System.out.println(BuyScreenWendy.getNumOfDragon() + "/6 dragons");
=======
//							BuyScreenWendy.setNumOfDragon();
//							update();
//							getDragonAmount().setText( BuyScreenWendy.getNumOfDragon() +"/6 dragons");
//							System.out.println(BuyScreenWendy.getNumOfDragon() + "/6 dragons");
>>>>>>> branch 'buyShopW' of https://github.com/katsemenova/dragonLand.git
							DragonLand.coins -= d.getPrice();
							getCoins().setCoins(DragonLand.coins);
							System.out.println(DragonLand.coins);
							update();
						}
						else
						{
							System.out.println("You donot have enough coins. Go play our minigame to win more coins");
						}
					}
				});

				
				visible.add(label);
				
				y = y + DragonLabel.getLabelHeight()+20;
				//System.out.println(dragons.size());
				//System.out.println(dragonsInShop.size());
				
			}
			
		}
		
	}
	
	
	public void inLists(){
		
		dragonsInShop = new ArrayList<Dragon>();
		dragons = new ArrayList<Dragon>();
		
		dragons = HomeKat.getDragons();
		
//		for(int i = BuyScreenWendy.getNum(); i<BuyScreenWendy.getNum()+3; i++)
//		{
//			dragonsInShop.add(dragons.get(i));
//		}

		
	}
}
