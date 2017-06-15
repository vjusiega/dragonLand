package dragonComponents;

import java.util.ArrayList;

import game.EggIncuabtor.Egg;
import game.mainScreenTeam.Dragon;

public class saveDragons {

	private String TOP_LINE = "Name''Price''Img Src";
	private String NEW_LINE = System.getProperty("line.separator");
	
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
	
	public String createDragon(ArrayList<Dragon> myDragons, ArrayList<Egg> incubating){
		String dragonText = "";
		for(int i=0; myDragons!=null && i<myDragons.size();i++){
			Dragon d = myDragons.get(i);
			dragonText += d.getName()+"''"+d.getPrice()+"''"+d.getImgSrc()+NEW_LINE;
		}
		dragonText+= "--"+NEW_LINE;
		for(int j=0; incubating!=null && j<incubating.size();j++){
			Egg e = incubating.get(j);
			dragonText += e.getImgSrc()+"''"+e.getCategory()+"''"+e.getPrice()+"''"+e.getIncubationTime()+"''"+e.getTime()+NEW_LINE;
		}
		System.out.println(dragonText);
		return dragonText;
	}
}