package game.mainScreenTeam;

import java.util.ArrayList;

import game.mainScreenTeam.Dragon;
import guiPractice.components.Visible;
/**
 * @author Jenniber Franco
 *
 */
public interface DragonArrayInterface {

	void removeHungryDragon(Dragon d);

	ArrayList<Dragon> getDragonsOnScreen();
}

