package guiPractice;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.Clickable;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;

public abstract class ClickableScreen extends Screen implements MouseListener {

	private ClickableGraphic pika;
	protected ArrayList<Clickable> clickables;
	
	public ClickableScreen(int width, int height) {
		super(width, height);
		
	}

	public abstract void initAllObjects(ArrayList<Visible> viewObjects);
	
	public void addObject(Visible v){
		 super.addObject(v);
		 if(v instanceof Clickable){
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
			if(c.isHovered(e.getX(), e.getY())){
				c.act();
				break;
			}
		}
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		initAllObjects(viewObjects);
		clickables = new ArrayList<Clickable>();
		for(Visible object: viewObjects){
			if(object instanceof Clickable){
				clickables.add((Clickable)object);
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
}