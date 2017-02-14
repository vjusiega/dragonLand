package game;

import java.awt.event.MouseListener;
import java.util.ArrayList;

import dragonComponents.Background;
import dragonComponents.CurvedButton;
import guiPractice.ClickableScreen;
import guiPractice.components.Button;
import guiPractice.components.Visible;

public class HelpScreen extends ClickableScreen implements MouseListener {

	private Background background;
	private CurvedButton layerOne;
	private Button layerTwo;
	
	public HelpScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Background(0,0, getWidth(), getHeight());
		layerOne = new CurvedButton(50,70,getWidth()-100, getHeight()-95,null,DragonLand.LIGHT_NUDE, null);
		layerTwo = new Button(100, 150, getWidth()-200, getHeight()-225, null, DragonLand.BRIGHT_PINK, null);
	}

}
