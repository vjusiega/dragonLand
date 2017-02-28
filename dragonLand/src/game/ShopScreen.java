package game;

import java.awt.Color;
import java.util.ArrayList;
import dragonComponents.CoinLabel;
import dragonComponents.ShopBackdrop;
import dragonComponents.ShopLabel;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public abstract class ShopScreen extends ClickableScreen {

	//private ArrayList<Dragon> dragonList;
//	private Action action;
//	private ShopBackdrop back;
//	private final static int DRAGONS_PER_PAGE = 3;
	private final static int SHOP_LEFT_MARGIN = 50;
	private final static int SHOP_TOP_MARGIN = 50;
	
	private final static int LEFT_MARGIN = 15;
	private final static int TOP_MARGIN = 15;
	
	

	private CoinLabel coins;
	private int currentPage = 1;
	private int totalPages = 2;
	private ShopLabel dragonAmount;
	private ShopLabel page;

	private ClickableGraphic arrowRight;
	private ClickableGraphic arrowLeft;

	public ShopScreen(int width, int height) {
		super(width, height);
		update();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {


		Graphic background=new Graphic(0,0,getWidth(),getHeight(),"img/Grassland.png");
		int backWidth = getWidth()-100;
		int backHeight = getHeight()-100;
		ShopBackdrop back = new ShopBackdrop(SHOP_LEFT_MARGIN, SHOP_TOP_MARGIN,backWidth,backHeight);
		
		int titleWidth = backWidth - 2 * LEFT_MARGIN;
		int titleHeight = 70;
		int titleX = SHOP_LEFT_MARGIN + LEFT_MARGIN;
		int titleY = SHOP_TOP_MARGIN + TOP_MARGIN;
		
		ShopLabel shopTitleBack = new ShopLabel(titleX, titleY, titleWidth, titleHeight, "", DragonLand.DARKER_NUDE);
		shopTitleBack.setArc(25);
		
		Button exit = new Button(getWidth() - 65,  40, 50, 40, "X", new Color(230,195,147), new Action(){
			
			public void act() {
				// TODO Auto-generated method stub
				DragonLand.game.setScreen(DragonLand.shopMain);
			}
		});
		
		int shopNameY = titleY + (int)(TOP_MARGIN * 0.5);
		TextLabel shopName = new TextLabel(titleX + LEFT_MARGIN * 2, shopNameY, titleWidth/3, titleHeight - TOP_MARGIN * 2, "Dragon Shop");
		shopName.setSize(26);
		
		int coinX = titleX + titleWidth - CoinLabel.getWdith() - LEFT_MARGIN * 2;
		coins = new CoinLabel(coinX, shopNameY, DragonLand.coins);
		dragonAmount = new ShopLabel(coinX, shopNameY + CoinLabel.getHeight2() + 2, CoinLabel.getWdith(), CoinLabel.getHeight2(), /*DragonLand.dragons.length() +*/ "0/6 Dragons", DragonLand.LIGHT_NUDE);

		dragonAmount.setArc(15);
		
		int back2Width = backWidth - 100;
		int back2Height = backHeight - titleHeight - 100;
		int back2X = SHOP_LEFT_MARGIN + 50;
		int back2Y = SHOP_TOP_MARGIN + titleHeight + 25;
		
		ShopBackdrop back2 = new ShopBackdrop(back2X, back2Y, back2Width, back2Height, DragonLand.BRIGHT_PINK);
		
		totalPages = 2;
		int bottomBarY = backHeight - TOP_MARGIN * 2 + 10;
		int pageWidth = 300;
		
		page = new ShopLabel(SHOP_LEFT_MARGIN + backWidth/2 - pageWidth/2, bottomBarY + TOP_MARGIN, pageWidth, 30, "Page 1 of " + totalPages , DragonLand.LIGHT_NUDE);

		
		arrowRight = new ClickableGraphic(backWidth - LEFT_MARGIN - 25, bottomBarY, 0.12, "img/arrowRight.png");
		arrowLeft = new ClickableGraphic(SHOP_LEFT_MARGIN + LEFT_MARGIN, bottomBarY, 0.12, "img/arrowLeft.png");	
		
		viewObjects.add(background);
		viewObjects.add(back);
		
		viewObjects.add(shopTitleBack);
		viewObjects.add(shopName);
		viewObjects.add(exit);
		viewObjects.add(coins);
		viewObjects.add(dragonAmount);
		
		viewObjects.add(back2);
		
		addDragonLabels(viewObjects);
		
		viewObjects.add(arrowRight);
		viewObjects.add(arrowLeft);
		viewObjects.add(page);
		addDragonLabels(viewObjects);
		
	}
	public abstract void addDragonLabels(ArrayList<Visible> viewObjects);
	//public abstract void addDragonLabels(ArrayList<Visible> viewObjects);
	
	public CoinLabel getCoins()
	{
		return coins;
	}
	
	public ShopLabel getDragonAmount()
	{
		return dragonAmount;
	}
	
	public ShopLabel getPage()
	{
		return page;
	}
	
	public ClickableGraphic getArrowRight()
	{
		return arrowRight;
	}
	
	public ClickableGraphic getArrowLeft()
	{
		return arrowLeft;
	}

}
