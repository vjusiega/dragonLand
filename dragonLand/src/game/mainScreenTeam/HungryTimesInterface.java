package game.mainScreenTeam;

import java.util.ArrayList;

import guiPractice.components.Action;
import guiPractice.components.Clickable;
import guiPractice.components.Visible;

/**
 * @author Jenniber Franco
 *
 */
public interface HungryTimesInterface extends Clickable {
	
	void removeDragon(Dragon d, ArrayList<Visible>viewObjects);
	
}
