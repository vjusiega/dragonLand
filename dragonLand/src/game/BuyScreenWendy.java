package game;

import java.util.ArrayList;

import dragonComponents.Dragon;

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

public class BuyScreenWendy extends ShopScreen implements BuyScreenInterface{

	private ArrayList<Dragon> dragonsInShop;
	private ArrayList<Dragon> dragons; 
	//private DragonLabel label;
	
	//private int price = label.getDragonPrice().getPrice();
	//private Dragon sold;
	private int x; 
	private	int y;
	private static int startIndex = 0;
	private static int numOfDragons;
	private int pageNum;

	public BuyScreenWendy(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDragonLabels(ArrayList<Visible> visible) {
		pageNum = 1;
		dragons = HomeKat.getDragons();
		dragonsInShop = getDragonsInBuyShop();
		getPage().setText("Page " + pageNum);
		addLabels(visible);
		buttonArrows();
	}
	
	private void addLabels(ArrayList<Visible> visible) {
		x = 0;
		y = 170;
		numOfDragons = ((SellShopZheng)DragonLand.sellScreen).getDragonsInSellShop().size();
		getDragonAmount().setText(numOfDragons + " / 6 dragons");
		
		startIndex = (pageNum - 1) * 3;
		int endIndex = startIndex +3;
		
		if(endIndex > dragonsInShop.size())
		{
			endIndex = dragonsInShop.size();
		}
		
		for(int i = startIndex; i< endIndex; i++)
		{
			Dragon d = dragonsInShop.get(i);
			DragonLabel2 label = new DragonLabel2(DragonLabel2.LABEL_LEFT_MARGIN,y, d,"BUY");
			label.setAction( new Action(){
				public void act() {
					if(DragonLand.coins > d.getPrice() && ((SellShopZheng)DragonLand.sellScreen).getDragonsInSellShop().size() < 6)
					{
						boughtDragon(d,label);	
						removeDragons();
						addLabels(visible);
					}
					else
					{
						System.out.println("You donot have enough coins. Go play our minigame to win more coins You have " + DragonLand.coins + " coins");
					}
				}
			});
		
			addObject(label);	
			y += DragonLabel2.getLabelHeight()+20;
			
		}
	}
	
	
	public void inLists(int num){
		
		dragonsInShop = new ArrayList<Dragon>();
		dragons = new ArrayList<Dragon>();
		
		dragons = HomeKat.getDragons();
		for(int i = 0; i<num; i++)
		{
			dragonsInShop.add(dragons.get(i));
		}
	}
	private ArrayList<Dragon> getDragonsInBuyShop() {
		dragonsInShop = dragons;
		for(Dragon d: HomeKat.getDragonsOnScreen())
		{
			dragonsInShop.remove(d);
		}
		return dragonsInShop;
	}
	
	private void boughtDragon(Dragon d, DragonLabel2 label) {
		// TODO Auto-generated method stub
		dragonsInShop.remove(d);	
		remove(label);
		System.out.println("adding to sell");
		((SellShopZheng)DragonLand.sellScreen).addToDragonsInSellShop(d);
		numOfDragons ++;
		getDragonAmount().setText(numOfDragons+" / 6 dragons");
		System.out.println(numOfDragons + " / 6 dragons");
	
		System.out.println("This dragon is " + d.getPrice());
		DragonLand.coins -= d.getPrice();
		getCoins().setCoins();
		System.out.println("You have " + DragonLand.coins + " coins");
		update();
	}
	
	private void buttonArrows() {
			// TODO Auto-generated method stub
		getArrowLeft().setAction(new Action(){
	
			@Override
			public void act() {
				if(pageNum > 1)
				{
					pageNum--;				
				}
				getPage().setText("Page " + pageNum);
				removeDragons();
				addLabels(viewObjects);
			}
		});
		
		getArrowRight().setAction(new Action(){
			@Override
			public void act() {
				if(pageNum * 3 < dragonsInShop.size())
				{		
					pageNum++;
				}
				removeDragons();
				getPage().setText("Page " + pageNum);
				addLabels(viewObjects);
			}
		});
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

	public void addToDragonsInBuyShop(Dragon dragon) {
		dragonsInShop.add(dragon);
		addLabels(viewObjects);
	}
}

