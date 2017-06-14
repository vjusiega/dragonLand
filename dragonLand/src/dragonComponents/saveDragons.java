package dragonComponents;

import java.util.ArrayList;

import game.mainScreenTeam.Dragon;

public class saveDragons {

	private String TOP_LINE = "Name''Price''Img Src";
	private String NEW_LINE = "\n";
	
	public saveDragons(ArrayList<Dragon> myDragons, ArrayList<Egg> incubating, String fileName) {
		String dragonText = createDragon(myDragons, incubating);
		WriteFile dragon = new WriteFile(dragonText, fileName);
	}
//name, price, img src
	//make sure the arrays in shop are correct
	//my dragons Array -dragons owned
	//game.newShop.shopScreen.getMyDragons();
	//dragonsToBuy Array - dragons in shop
	//dragons in HomeKat - full array
	
	public String createDragon(ArrayList<Dragon> myDragons, ArrayList<Dragon> dragons){
		String dragonText = TOP_LINE + NEW_LINE;
		for(int i=0; i<myDragons.size();i++){
			Dragon d = myDragons.get(i);
			dragonText += d.getName()+"''"+d.getPrice()+"''"+d.getImgSrc()+NEW_LINE;
		}
		dragonText+= "--"+NEW_LINE;
		for(int i=0; i<dragons.size();i++){
			Dragon d = myDragons.get(i);
			dragonText += d.getName()+"''"+d.getPrice()+"''"+d.getImgSrc()+NEW_LINE;
		}
		
		return dragonText;
	}

}