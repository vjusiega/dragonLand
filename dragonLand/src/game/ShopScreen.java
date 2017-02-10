package game;

import java.util.ArrayList;

import dragonComponents.ShopBackdrop;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Visible;

public class ShopScreen extends ClickableScreen {
	
	private ArrayList<Dragon> dragonList;
	private Action action;
	private ShopBackdrop back;
	
	public ShopScreen(int width, int height, ArrayList<Dragon> dl, Action act) {
		super(width, height);
		dragonList = dl;
		action = act;
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {

	}

}
