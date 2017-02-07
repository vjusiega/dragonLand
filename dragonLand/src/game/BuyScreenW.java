package game;

import java.util.ArrayList;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Visible;

public class BuyScreenW extends ClickableScreen {

    private ArrayList<Dragons> dragonsInShop;
    private ArrayList<DragonLabel> shoplabels; 
    private DragonLabel label;
    private Dragon[] dragons;
    private SellScreenInterface sold;

	public BuyScreenW(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(ArrayList<Visible> arg0) {
		// TODO Auto-generated method stub
		Dragons[] dragons = DragonLand.DragonList;
		for(int i = 0; i<dragons.length;i++)
		{
			label = new DragonLabel(0,0, dragons[i],"BUY", new Action(){
				@Override
				public void act() {
					// TODO Auto-generated method stub
					
				}
			});
			shoplabels.add(label);
		}
		
	}
	

}
