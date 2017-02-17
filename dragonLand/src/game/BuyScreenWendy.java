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

public class BuyScreenWendy extends ShopScreen{
	
	 private ArrayList<Dragon> dragonsInShop = new ArrayList<Dragon>();
	    private ArrayList<DragonLabel> shoplabels; 
	    private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
//	   private DragonLabel label;
	    
//	    private int price = label.getDragonPrice().getPrice();
	    //private Dragon sold;
	    private int x; 
		private	int y;
		private static int startIndex = 0;
		private static int numOfDragons;
		private static int pageNum;
		private boolean clicked;

	public BuyScreenWendy(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDragonLabels(ArrayList<Visible> visible) {
		// TODO Auto-generated method stub
		//pageNum = 1;
		dragonsInShop = new ArrayList<Dragon>();
		dragons = new ArrayList<Dragon>();
		dragons = HomeKat.getDragons();
		dragonsInShop = HomeKat.getDragons();

		buttonArrows();
		
		
//		sold = SellShopZheng.getSold();
//		if(sold != null)
//		{
//			dragonsInShop.add(sold);
//		}

		
		x = 0;
		y = 170;
		
		int endIndex = startIndex +3;
		
		if(endIndex > dragons.size())
		{
			endIndex = dragons.size();
		}
		for(int i = startIndex; i< endIndex; i++)
		{
			if(dragonsInShop.contains(dragons.get(i)))
			{
				Dragon d = dragons.get(i);
				DragonLabel label = new DragonLabel(DragonLabel.LABEL_LEFT_MARGIN,y, d,"BUY");
				label.setAction( new Action(){
					
					public void act() {
						// TODO Auto-generated method stub

						if(DragonLand.coins > d.getPrice())
						{
							update();
							dragonsInShop.remove(d);	
							visible.remove(label);							
							numOfDragons ++;
							getDragonAmount().setText(numOfDragons+"/6 dragons");
							System.out.println(numOfDragons + "/6 dragons");

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

				
				//visible.add(label);
				addObject(label);
				
				y += DragonLabel.getLabelHeight()+20;
				//System.out.println(dragons.size());
				//System.out.println(dragonsInShop.size());
				
			}
			
		}
		
	}
	
	
private void buttonArrows() {
		// TODO Auto-generated method stub
	getArrowLeft().setAction(new Action(){

		@Override
		public void act() {
			// TODO Auto-generated method stub
			clicked = false;
			addDragonLabels(viewObjects);
		}
	});
	
	getArrowRight().setAction(new Action(){
		@Override
		public void act() {
			// TODO Auto-generated method stub
			clicked = true;
			addDragonLabels(viewObjects);
			pageNum = 2;
			System.out.println(pageNum);
			//DragonLand.game.setScreen(DragonLand.game.buyScreen2);
			update();
		}
	});
	}

	
	public static void setNumOfDragon()
	{
		numOfDragons ++;
	}
	
	public static int getPageNum(){
		return pageNum;
	}
}
