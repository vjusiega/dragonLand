package dragonComponents;

import java.util.ArrayList;

import guiPractice.components.Action;
import guiPractice.components.Clickable;
import guiPractice.components.Visible;

public interface HungryTimesInterface extends Clickable {
	
	ArrayList<Dragon> getDragonsOnScreen();
	
	ArrayList<Dragon> setDragonsOnScreen();
	
	boolean hasHungryBox();
	
	void removeDragon(Dragon d, ArrayList<Visible>viewObjects);
	
	int getHungryTime();
	
	void setHungryTime(int num);
	
	HungryBox hungryBox;
	
	HungryBox getHungryBox();
	
	void setHungryBox();
}
