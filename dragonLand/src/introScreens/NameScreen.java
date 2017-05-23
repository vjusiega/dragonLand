package introScreens;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import game.DragonLand;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class NameScreen extends ClickableScreen implements KeyListener {

	private Graphic background;
	private ArrayList<Fog> fogs;

	public NameScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		fogs = new ArrayList<Fog>();
		
		ClickableGraphic post = new ClickableGraphic(getWidth()-250, getHeight()-200, 1.0,"img/continueSign.png");
		post.setAction(new Action(){
			public void act(){
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
		
		setUpFog(post);
		TextLabel input = new TextLabel(200, 200, 150, 150, "Username :");
		viewObjects.add(input);
		
	}
	public void setUpFog(ClickableGraphic post){
		Fog fog; 
		
		for(int i = -10; i < 10; i++){
			fog = new Fog((i*getWidth() / 10), 200, 500, 300, "img/introFog.png", 100);
			if(i == 8){
				viewObjects.add(post);
			}
			viewObjects.add(fog);
			fog.setY(fog.generateYPos());
			fogs.add(fog);
			fog.play();
		}
	}
}
