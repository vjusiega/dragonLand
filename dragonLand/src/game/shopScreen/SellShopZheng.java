package game.shopScreen;

import java.util.ArrayList;

import dragonComponents.DragonLabel2;
import game.DragonLand;
import game.mainScreenTeam.Dragon;
import game.mainScreenTeam.StoreSellInterfaceK;
import guiPractice.components.Action;
import guiPractice.components.Visible;
/*
 * @author Zheng
 * */
public class SellShopZheng extends ShopScreen implements SellScreenInterface, StoreSellInterfaceK{
	
	private ArrayList<Dragon> dragonsInSellShop;
	private int pageNumber;
	
	public SellShopZheng(int width, int height) {
		super(width, height);
	}

	@Override
	public void addDragonLabels(ArrayList<Visible> viewObjects) {
		dragonsInSellShop = new ArrayList<Dragon>();
		setArrowAction();
		//inLists();
		pageNumber = 1;
		drawDragons();
	}

	private void setArrowAction() {
		getArrowLeft().setAction(new Action(){

			@Override
			public void act() {
				if(pageNumber == 2)
				{
					pageNumber --;
					//update();
					
					removeDragons();
					drawDragons();
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
					//update();
					
					removeDragons();
					drawDragons();
					getPage().setText("Page " + pageNumber + " of 2");
				}
			}
			
		});
		
	}

	@Override
	public String[] getNamesOfPurchased() {
		String[] names = new String[dragonsInSellShop.size()];
		for(int i = 0; i < dragonsInSellShop.size(); i++){
			names[i] = dragonsInSellShop.get(i).getName();
		}
			
		return names;
	}
	
	
	public void setPageDisplay()
	{
		if(dragonsInSellShop.size() > 3)
			getPage().setText("Page " + pageNumber + " of 2");
		else getPage().setText("Page " + pageNumber + " of 1");
	}
	
	public void drawDragons()
	{
		setPageDisplay();
		updateDragonAmount();
		int labelY = 0;
		int endi = (pageNumber)* 3;
		if((pageNumber) * 3 > dragonsInSellShop.size())
			endi = dragonsInSellShop.size();
		
		for(int i = (pageNumber - 1)* 3; i < endi; i++)
		{
			Dragon dragon = dragonsInSellShop.get(i);
			if(pageNumber == 1)
				labelY = DragonLabel2.LABEL_TOP_MARGIN  + DragonLabel2.getLabelHeight() * i + (i * 10);
			else
				labelY = DragonLabel2.LABEL_TOP_MARGIN  + DragonLabel2.getLabelHeight() * (i - 3) + ((i - 3) * 10);
			
			DragonLabel2 label = new  DragonLabel2(DragonLabel2.LABEL_LEFT_MARGIN, labelY, dragon, "SELL");
			label.setAction(new Action(){
				public void act()
				{
					dragonsInSellShop.remove(dragon);
					((BuyScreenInterface)DragonLand.buyScreen).addToDragonsInBuyShop(dragon);
					removeDragons();
					drawDragons();
					
					DragonLand.coins += label.getDragonPrice().getPrice();
					getCoins().setCoins();
				}
			});
			
			addObject(label);
		}
	}
	
	public void removeDragons()
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

	public ArrayList<Dragon> getDragonsInSellShop() {
		return dragonsInSellShop;
	}
	
	public void addToDragonsInSellShop(Dragon d) {
		dragonsInSellShop.add(d);
		drawDragons();
	}
	
	public void removeDragonsInSellShop(Dragon d) {
		dragonsInSellShop.remove(d);
	}

	@Override
	public void updateDragonAmount() {
		getDragonAmount().setText(dragonsInSellShop.size() + "/6 Dragons");
	}

}