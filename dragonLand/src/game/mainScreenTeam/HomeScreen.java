package game.mainScreenTeam;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import game.Sound;
import guiPractice.ClickableScreen;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;
import introScreens.Fog;


/**
 * @author Kat 
 * @author Jenniber
 *
 */
public class HomeScreen extends ClickableScreen implements Runnable{

	private Graphic background;
	public static HomeJenniber jenCode;
	public HomeKat katCode;
	public HomeScreen(int width, int height) {
		super(width, height);
		Thread play = new Thread(this);
		play.start();
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {

		background=new Graphic(0,0,getWidth(),getHeight(),"img/Grassland.jpg");
		viewObjects.add(background);
		setUpFog();
		 katCode=new HomeKat(viewObjects, getWidth(), getHeight());
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Draggable d= (Draggable) draggables.get(0);
		if(d.isHovered(e.getX(), e.getY()) || alreadyDragging){
			alreadyDragging = true;
			d.setX(e.getX() - 37);
			d.setY(e.getY() - 37);
		}
		
	}
	public void setUpFog(){
		Fog fog; 
		
		for(int i = -10; i < 10; i++){
			fog = new Fog((i*getWidth() / 10), 0, 400, 150, "img/introFog.png", 50);
			viewObjects.add(fog);
			fog.setY(fog.generateYPos());
			fog.play();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		Draggable d= (Draggable) draggables.get(0);
		jenCode.checkFeed(katCode.getFood().getX()+(int)(katCode.getFood().getWidth()/2),katCode.getFood().getY()+(int)(katCode.getFood().getHeight()/2));
		d.setX(d.getOrigX());
		d.setY(d.getOrigY());
		alreadyDragging=false;
		
		
	}
	@Override
	public void run() {
	}

}