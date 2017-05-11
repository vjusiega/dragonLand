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

public abstract class ClickableScreen extends Screen  implements MouseListener, MouseMotionListener  {

	private ClickableGraphic pika;
	protected ArrayList<Clickable> clickables;
	protected ArrayList<Draggable> draggables;
	private boolean alreadyDragging;
 
	public ClickableScreen(int width, int height) {
		super(width, height);
		//this.addMouseMotionListener(this);
		
	}

	public abstract void initAllObjects(ArrayList<Visible> viewObjects);
	
	public void addObject(Visible v){
		 super.addObject(v);
		 if(clickables != null && v instanceof Clickable){
			 clickables.add((Clickable)v);
		 }
		 if(draggables != null && v instanceof Draggable)
			 draggables.add((Draggable)v);
	}

	public void remove(Visible v){
		super.remove(v);
		clickables.remove(v);
		draggables.remove(v);
	} 
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseclicked");
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
	
	public MouseMotionListener getMouseMotionListener(){
		return this;
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
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Draggable d= (Draggable) draggables.get(0);
		d.setX(d.getOrigX());
		d.setY(d.getOrigY());
		alreadyDragging=false;
	}
	@Override
	public MouseListener getMouseListener(){
		return this;
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
	public void mouseMoved(MouseEvent e) {
		//System.out.println("moved");
	}
}