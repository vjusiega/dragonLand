package introScreens;

import java.util.ArrayList;

import game.mainScreenTeam.Dragon;
import game.miniGameTeam.GameVioletta;
import guiPractice.ClickableScreen;
import guiPractice.components.Visible;

public class WelcomeScreen extends ClickableScreen{
	
	private ArrayList<Dragon> dragons;

	public WelcomeScreen(int width, int height) {
		super(width, height);
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		dragons = new ArrayList<Dragon>();
		viewObjects.add(setUpDragons(1));
		viewObjects.add(setUpDragons(2));
	}
	
	public Dragon setUpDragons(int drag){
		String imgSrc = "img/dragon" + drag + ".png";
		int dragonHeight = getHeight()/8;
		int xPos;
		if(drag == 1){
			xPos = getWidth()/4;
		}else{
			xPos = 3*getWidth()/4;
		}
		int dragonWidth = (int) (dragonHeight * 0.75);
		int yPos = (-1)*dragonHeight;
		Dragon d = new Dragon(xPos, yPos, dragonHeight, dragonWidth, " ", 0, imgSrc);
		dragons.add(d);
		GameVioletta.setDragonAnimation(d, imgSrc);
		d.setX(xPos);
		d.setY(yPos);
		d.setDirection(6);
		d.play();
		
		return d;
	}
	
	public static int getDragonY(){
		return getHeight()/4;
	}

}
