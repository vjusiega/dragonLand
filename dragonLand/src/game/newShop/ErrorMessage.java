package game.newShop;

import java.util.ArrayList;

import guiPractice.components.Graphic;
import guiPractice.components.Visible;

public class ErrorMessage implements Runnable{
	
	private Graphic errorImage;
	private ArrayList<Visible> viewObjects;

	public ErrorMessage(Graphic errorImage, ArrayList<Visible> viewObjects){
		this.errorImage = errorImage;
		this.viewObjects = viewObjects;
	}
	
	
	@Override
	public void run() {
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		viewObjects.remove(errorImage);
		
	}

}
