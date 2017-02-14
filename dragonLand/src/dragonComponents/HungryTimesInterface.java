package dragonComponents;

import guiPractice.components.Action;
import guiPractice.components.Clickable;

public interface HungryTimesInterface extends Clickable {

	void setHungryTime(int i);

	void setAction(Action action);

	int getAppearanceTime();

	void setAppearanceTime(int i);
	
	Dragon getDragonsOnScreen();
	

}
