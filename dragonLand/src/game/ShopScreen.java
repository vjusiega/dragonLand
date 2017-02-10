package game;

import java.util.ArrayList;

import dragonComponents.PriceLabel;
import dragonComponents.ShopBackdrop;
import dragonComponents.ShopLabel;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;

public class ShopScreen extends ClickableScreen {
	
	//private ArrayList<Dragon> dragonList;
	private Action action;
	private ShopBackdrop back;

	
	public ShopScreen(int width, int height) {
		super(width, height);
}
	
	public ShopScreen(int width, int height, ArrayList<Dragon> dl, Action act) {
		super(width, height);
		dragonList = dl;
		action = act;
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {

		Graphic background=new Graphic(0,0,getWidth(),getHeight(),"img/Grassland.png");
		ShopBackdrop back = new ShopBackdrop(50,50,getWidth()-100,getHeight()-100);



		PriceLabel ppp = new PriceLabel(50, 50, 100000);
		
		
		viewObjects.add(background);
		viewObjects.add(back);
		viewObjects.add(ppp.getCoin());
		viewObjects.add(ppp.getPriceLabel());
	}

}
