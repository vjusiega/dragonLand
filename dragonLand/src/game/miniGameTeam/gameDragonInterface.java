package game.miniGameTeam;

/**
 * @author Tamanna Hussain
 *
 */

import java.util.ArrayList;

import dragonComponents.Dragon;

public interface gameDragonInterface {
	
	boolean checkStarContact(Star1 star);
	Dragon addDragon(String imgSrc);
	Dragon removeDragon();
	void changeDragonPos(int x);
	ArrayList<Dragon> getDragonArray();
	boolean getPlaying();
	void setPlaying(boolean b);
}
