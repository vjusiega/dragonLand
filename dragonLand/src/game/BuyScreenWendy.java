package game;

import java.util.ArrayList;

import dragonComponents.Dragon;
import dragonComponents.DragonLabel;
import dragonComponents.DragonLabel2;
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
		private int pageNum = 1;
		private boolean clicked = false;

	public BuyScreenWendy(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDragonLabels(ArrayList<Visible> visible) {
		// TODO Auto-generated method stub
		
		getPage().setText("Page " + pageNum);

		addLabels(visible);
		buttonArrows();
	}
		


private void addLabels(ArrayList<Visible> visible) {
		// TODO Auto-generated method stub
	x = 0;
	y = 170;
	
	dragonsInShop = new ArrayList<Dragon>();
	dragons = new ArrayList<Dragon>();
	dragons = HomeKat.getDragons();
	dragonsInShop = HomeKat.getDragons();
	
	getDragonAmount().setText(numOfDragons+"/"+dragons.size() + " dragons");
	
	getFromSell();
	
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
			DragonLabel2 label = new DragonLabel2(DragonLabel2.LABEL_LEFT_MARGIN,y, d,"BUY");
			label.setAction( new Action(){
				
				public void act() {
					// TODO Auto-generated method stub

					if(DragonLand.coins > d.getPrice())
					{
						boughtDragon(d,label);
						
					}
					else
					{
						System.out.println("You donot have enough coins. Go play our minigame to win more coins You have " + DragonLand.coins + " coins");
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

protected void boughtDragon(Dragon d, DragonLabel2 label) {
	// TODO Auto-generated method stub
	dragonsInShop.remove(d);	
	remove(label);							
	update();
	//DragonLand.sellScreen.dragonsInSellShop.add(d); problem
	numOfDragons ++;
	getDragonAmount().setText(numOfDragons+"/"+dragons.size() + " dragons");
	System.out.println(numOfDragons + "/" + dragons.size() + " dragons");
	//why is dragons.size() = 0?
	System.out.println("This dragon is " + d.getPrice());
	DragonLand.coins -= d.getPrice();
	getCoins().setCoins(DragonLand.coins);
	System.out.println("You have " + DragonLand.coins + " coins");
	update();
}

private void buttonArrows() {
		// TODO Auto-generated method stub
	getArrowLeft().setAction(new Action(){

		@Override
		public void act() {
			// TODO Auto-generated method stub
			if(startIndex -3 < 0)
			{
				startIndex = 0;
			}
			else
			{
				startIndex-=3;				
			}
			
			addDragonLabels(viewObjects);
			if(pageNum>1)
			{
				pageNum--;				
			}
			getPage().setText("Page " + pageNum);
			update();
		}
	});
	
	getArrowRight().setAction(new Action(){
		@Override
		public void act() {
			// TODO Auto-generated method stub
			clicked = true;
			if(clicked && startIndex < dragons.size())
			{	
				startIndex+=3;
			}

			
			System.out.println(startIndex);
			addDragonLabels(viewObjects);
			if(startIndex < dragons.size())
			{
				
				pageNum++;
			}
			getPage().setText("Page " + pageNum);
			//DragonLand.game.setScreen(DragonLand.game.buyScreen2);
			update();
		}
	});
}

private void getFromSell() {
	// TODO Auto-generated method stub
	Dragon sold = ((SellShopZheng)DragonLand.sellScreen).getSold();
	if(sold != null)
	{
		dragonsInShop.add(sold);
	}
}
	

}
