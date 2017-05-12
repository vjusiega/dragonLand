package game.EggIncuabtor;

import java.util.ArrayList;

import game.mainScreenTeam.HomeKat;
import guiPractice.Screen;
import guiPractice.components.Visible;

public class IncubatorScreen extends Screen {
	
	private final int EGGS_ON_SCREEN = 9;
	

	public IncubatorScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	public boolean dragonHatch(){
		if(HomeKat.dragonHome.getDragonsOnScreen().size()+1>9){
			return false;
		}
		return true;
	}
	
	@Override
	public void initObjects(ArrayList<Visible> viewObjects2) {
		// TODO Auto-generated method stub
		
	}

}
