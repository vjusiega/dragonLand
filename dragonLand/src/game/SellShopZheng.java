package game;

import java.util.ArrayList;

import dragonComponents.Dragon;
import dragonComponents.DragonLabel;
import dragonComponents.DragonLabel2;
import guiPractice.components.Action;
import guiPractice.components.Visible;

public class SellShopZheng extends ShopScreen implements SellScreenInterface, StoreSellInterfaceK{
	
	private ArrayList<Dragon> dragonsInSellShop;
	private ArrayList<Dragon> dragonsSold;
	private int pageNumber;
	
	public SellShopZheng(int width, int height) {
		super(width, height);
		pageNumber = 0;
	}

	@Override
	public void addDragonLabels(ArrayList<Visible> viewObjects) {
		setArrowAction();
		inLists();
		
		int endi;
		if((pageNumber + 1)* 3 > dragonsInSellShop.size())
			endi = dragonsInSellShop.size();
		else endi = (pageNumber + 1)* 3;
			
		for(int i = pageNumber * 3; i < endi; i++)
		{
			Dragon dragon = dragonsInSellShop.get(i);
			DragonLabel2 label = new DragonLabel2(DragonLabel2.LABEL_LEFT_MARGIN, DragonLabel2.LABEL_TOP_MARGIN  + DragonLabel2.getLabelHeight() * i + (i * 10), dragon, "SELL");
			label.setAction(new Action(){
				public void act()
				{
					dragonsInSellShop.remove(dragon);
					//System.out.println(dragonsInSellShop.size());
					viewObjects.remove(label);
					DragonLand.coins += label.getDragonPrice().getPrice();
					getCoins().setCoins();
				}
			});
			viewObjects.add(label);
		}
	}

	private void setArrowAction() {
		getArrowLeft().setAction(new Action(){

			@Override
			public void act() {
				if(pageNumber > 1)
				{
					pageNumber --;
					update();
				}
			}
			
		});
		
		getArrowRight().setAction(new Action(){

			@Override
			public void act() {
				if(pageNumber < 2)
				{
					pageNumber ++;
					System.out.println("DASASDDS");
					update();
				}
			}
			
		});
		
	}

	@Override
	public Dragon getSold() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getNamesOfPurchased() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void inLists()
	{
		
		dragonsInSellShop = new ArrayList<Dragon>();
		dragonsSold = new ArrayList<Dragon>();
		for(int i = 0; i < 6; i++)
		{
			dragonsInSellShop.add(HomeKat.getDragons().get(i));
		}
		
		
	}
}
