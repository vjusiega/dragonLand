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
	}

	@Override
	public void addDragonLabels(ArrayList<Visible> viewObjects) {
		setArrowAction();
		inLists();
		pageNumber = 1;
		setPageDisplay();
		
		drawDragons(viewObjects);
	}

	private void setArrowAction() {
		getArrowLeft().setAction(new Action(){

			@Override
			public void act() {
				if(pageNumber == 2)
				{
					pageNumber --;
					update();
					getPage().setText("Page " + pageNumber + " of 2");
				}
			}
			
		});
		
		getArrowRight().setAction(new Action(){

			@Override
			public void act() {
				if(pageNumber == 1 && dragonsInSellShop.size() > 3)
				{
					pageNumber ++;
					System.out.println("DASASDDS");
					update();
					getPage().setText("Page " + pageNumber + " of 2");
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
		System.out.println("DASASDDSD");
		dragonsInSellShop = new ArrayList<Dragon>();
		dragonsSold = new ArrayList<Dragon>();
		for(int i = 0; i < 3; i++)
		{
			dragonsInSellShop.add(HomeKat.getDragons().get(i));
		}
		
		getDragonAmount().setText(dragonsInSellShop.size() + "/6 Dragons");
	}
	
	public void setPageDisplay()
	{
		if(dragonsInSellShop.size() > 3)
			getPage().setText("Page " + pageNumber + " of 2");
		else getPage().setText("Page " + pageNumber + " of 1");
	}
	
	public void drawDragons(ArrayList<Visible> viewObjects)
	{
		int endi;
		if((pageNumber) * 3 > dragonsInSellShop.size())
			endi = dragonsInSellShop.size();
		else endi = (pageNumber)* 3;
		
		for(int i = (pageNumber - 1)* 3; i < endi; i++)
		{
			Dragon dragon = dragonsInSellShop.get(i);
			DragonLabel2 label = new DragonLabel2(DragonLabel2.LABEL_LEFT_MARGIN, DragonLabel2.LABEL_TOP_MARGIN  + DragonLabel2.getLabelHeight() * i + (i * 10), dragon, "SELL");
			label.setAction(new Action(){
				public void act()
				{
					dragonsSold.add(dragon);
					
					dragonsInSellShop.remove(dragon);
					//remove(label);
					removeDragons(viewObjects);
					drawDragons(viewObjects);
					
					System.out.println(dragon.getName());
					System.out.println(dragonsInSellShop.toString());
					
					DragonLand.coins += label.getDragonPrice().getPrice();
					getCoins().setCoins();
					
					getDragonAmount().setText(dragonsInSellShop.size() + "/6 Dragons");
					
					setPageDisplay();
				}
			});
			viewObjects.add(label);
		}
	}
	
	public void removeDragons(ArrayList<Visible> viewObjects)
	{
		for(int i = 0; i  < viewObjects.size(); i++)
		{
			Visible v = viewObjects.get(i);
			if(v instanceof DragonLabel2)
			{
				remove(v);
				i--;
			}
		}
			
	}
}
