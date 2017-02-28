package dragonComponents;

import java.util.ArrayList;

public interface gameDragonInterface {
	
	boolean checkStarContact(int x, int starWidth);
	Dragon addDragon(String imgSrc);
	Dragon removeDragon();
	void changeDragonPos(int x);
}
