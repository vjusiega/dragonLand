package guiPractice;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import game.mainScreenTeam.Draggable;
import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.Clickable;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;

public abstract class ClickableScreen extends Screen implements MouseListener, MouseMotionListener {

	private ClickableGraphic pika;
	protected ArrayList<Clickable> clickables;
	protected ArrayList<Draggable> draggables;
	
	public ClickableScreen(int width, int height) {
		super(width, height);
		
	}

	public abstract void initAllObjects(ArrayList<Visible> viewObjects);
	
	public void addObject(Visible v){
		 super.addObject(v);
		 if(clickables != null && v instanceof Clickable){
			 clickables.add((Clickable)v);
		 }
	}

	public void remove(Visible v){
		super.remove(v);
		clickables.remove(v);
	} 
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0; i<clickables.size();i++){
			Clickable c= clickables.get(i);
			if(c.getAction() != null && c.isHovered(e.getX(), e.getY())){
				c.act();
				break;
			}
		}
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		initAllObjects(viewObjects);
		clickables = new ArrayList<Clickable>();
		draggables = new ArrayList<Draggable>();
		for(Visible object: viewObjects){
			if(object instanceof Clickable){
				clickables.add((Clickable)object);
			}
			else if(object instanceof Draggable){
				draggables.add((Draggable)object);
			}
		}

	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public MouseListener getMouseListener(){
		return this;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		for(int i=0; i<draggables.size();i++){
			Draggable d= (Draggable) draggables.get(i);
			if(d.isHovered(d.getX(), d.getY())){
				d.setX(e.getX());
				d.setY(e.getY());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// setX() to e.getX();
		
	}
}