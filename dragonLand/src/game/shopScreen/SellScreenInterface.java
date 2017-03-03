package game.shopScreen;

import java.util.ArrayList;

import game.mainScreenTeam.Dragon;

/*
 * @author Wendy
 * */

public interface SellScreenInterface {
	void addToDragonsInSellShop(Dragon d);
	ArrayList<Dragon> getDragonsInSellShop();
}
