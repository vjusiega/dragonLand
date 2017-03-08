package game.shopScreen;

import java.awt.Color;
import java.util.ArrayList;

import dragonComponents.DragonLabel2;
import game.DragonLand;
import game.mainScreenTeam.Dragon;
import game.mainScreenTeam.HomeKat;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Visible;
/*
 * @author Wendy
 * */

public class BuyScreenWendy extends ShopScreen implements BuyScreenInterface{

	private ArrayList<Dragon> dragonsInShop;
	private int x; 
	private	int y;
	private static int startIndex = 0;
	private static int numOfDragons;
	private int pageNum;
	private Button error;
	public BuyScreenWendy(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDragonLabels(ArrayList<Visible> visible) {
		//initiates shop display
		pageNum = 1;
		dragonsInShop = new ArrayList<Dragon>();
		getDragonsInBuyShop();
		getPage().setText("Page " + pageNum);
		addLabels(visible);
		buttonArrows();
	}
	
	private void addLabels(ArrayList<Visible> visible) {
		//position of labels
		x = 0;
		y = 170;
		
		//controlling display of number of dragons in inventory
		numOfDragons = ((SellScreenInterface)DragonLand.sellScreen).getDragonsInSellShop().size();
		getDragonAmount().setText(numOfDragons + " / 6 dragons");
		
		//dragons on display
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
					if(DragonLand.coins >= d.getPrice())
					{
						if(((SellScreenInterface)DragonLand.sellScreen).getDragonsInSellShop().size() < 6)
						{
							boughtDragon(d,label);	
							removeDragons();
							addLabels(visible);
						}
						else
						{
							addError("You have already maxed out your inventory. Go sell a dragon before buying another");
						}
					}
					else
					{
						addError("You do not have enough coins. Go play our minigame to win more coins");
						
					}
				}

				private void addError(String string) {
					error = new Button(100,100,850,100,string,new Color(244,215,183),new Action()
							{
								@Override
								public void act() {
									remove(error);
									
								}
						
							});
					addObject(error);
					
					
				}
			});

			addObject(label);
			y += DragonLabel2.getLabelHeight()+20;	
		}
	}
	
	private void getDragonsInBuyShop() {
		//adds array with all dragons from Kat
		for(Dragon d: HomeKat.getDragons())
			dragonsInShop.add(d);
		
		//remove the dragons from array that is already in inventory from Zheng
		for(Dragon d: ((SellScreenInterface)DragonLand.sellScreen).getDragonsInSellShop())
		{
			dragonsInShop.remove(d);
		}
	}
	
	private void boughtDragon(Dragon d, DragonLabel2 label) {
		// when buy button is clicked removing dragon from array and removing from display
		dragonsInShop.remove(d);
		remove(label);
		
		//adding sold dragon to Zheng's array for inventory/selling
		((SellScreenInterface)DragonLand.sellScreen).addToDragonsInSellShop(d);
		
		//updating inventory amount
		numOfDragons ++;
		getDragonAmount().setText(numOfDragons+" / 6 dragons");
	
		//updating the amount of coins 
		DragonLand.coins -= d.getPrice();
		getCoins().setCoins();
		update();
	}
	
	private void buttonArrows() {
			// change "page"
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
		//remove all the labels on screen before re-adding and updating display
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
		//method for sell shop to add dragon in buy after selling the dragon
		dragonsInShop.add(dragon);
		addLabels(viewObjects);
	}

	@Override
	public void updateDragonAmount() {
		numOfDragons = ((SellScreenInterface)DragonLand.sellScreen).getDragonsInSellShop().size();
		getDragonAmount().setText(numOfDragons + " / 6 dragons");
	}
}
