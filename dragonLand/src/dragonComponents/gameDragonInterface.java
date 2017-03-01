package dragonComponents;

import java.util.ArrayList;

import game.Star1;

public interface gameDragonInterface {
	
	boolean checkStarContact(Star1 star);
	Dragon addDragon(String imgSrc);
	Dragon removeDragon();
	void changeDragonPos(int x);
	ArrayList<Dragon> getDragonArray();
	boolean stillPlaying();
}
