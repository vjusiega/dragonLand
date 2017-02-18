package dragonComponents;

import java.util.ArrayList;

import guiPractice.components.Visible;
/**
 * @author Jenniber Franco
 *
 */
public interface DragonArrayInterface {

	void removeHungryDragon(Dragon d, ArrayList<Visible>viewObjects);
	
	ArrayList<Dragon> getDragonsOnScreen();
}