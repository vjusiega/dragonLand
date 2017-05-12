package game.mainScreenTeam;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import guiPractice.ClickableScreen;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;


/**
 * @author Kat 
 * @author Jenniber
 *
 */
public class HomeScreen extends ClickableScreen implements Runnable{

	private Graphic background;
	public static HomeJenniber jenCode;

	public HomeScreen(int width, int height) {
		super(width, height);
		Thread play = new Thread(this);
		play.start();
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {

		background=new Graphic(0,0,getWidth(),getHeight(),"img/Grassland.jpg");
		viewObjects.add(background);
		HomeKat katCode=new HomeKat(viewObjects, getWidth(), getHeight());
		
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

	@Override
	public void mouseReleased(MouseEvent e) {
		Draggable d= (Draggable) draggables.get(0);
		jenCode.checkFeed(e.getX(),e.getY());
		d.setX(d.getOrigX());
		d.setY(d.getOrigY());
		alreadyDragging=false;
		
	}
	@Override
	public void run() {
	}

}