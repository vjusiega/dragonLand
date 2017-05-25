package dragonComponents;

import java.util.ArrayList;

import game.mainScreenTeam.Dragon;

public class saveDragons {

	private String TOP_LINE = "Name''Price''Img Src";
	private String NEW_LINE = "\n";
	
	public saveDragons() {
		// TODO Auto-generated constructor stub
	}
//name, price, img src
	//make sure the arrays in shop are correct
	//my dragons Array -dragons owned
	//game.newShop.shopScreen.getMyDragons();
	//dragonsToBuy Array - dragons in shop
	//dragons in HomeKat - full array
	public static void main(String[] args) {
		
	}
	
	public String createDragon(ArrayList<Dragon> arr){
		String dragons = TOP_LINE + NEW_LINE;
		for(int i=0; i<arr.size();i++){
			Dragon d = arr.get(i);
			dragons = d.getName()+"''"+d.getPrice()+"''"+d.getImgSrc()+NEW_LINE;
		}
		return dragons;
	}

}