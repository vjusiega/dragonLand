package guiPractice.sampleGames;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableGraphic;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;

public class MyScreen extends Screen implements MouseMotionListener, MouseListener{

	private Button back;
	private Graphic picture;
	private Graphic pictureTwo;
	
	public MyScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		picture=new Graphic(0,0,getWidth(),getHeight(),"resourses/sampleImages/blueBack.jpg");
		viewObjects.add(picture);
		back = new Button((getWidth()-200)/2,(getHeight()-50)/2,200,50,"Click here to go back",new Color(0,76,153), new Action(){
			public void act(){
				MouseFollower.game.setScreen(MouseFollower.coordScreen);
			}
			});
		//viewObjects.add(back);
		pictureTwo=new ClickableGraphic(0,0,0.2,"resourses/sampleImages/cat.png");
		viewObjects.add(pictureTwo);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(back.isHovered(e.getX(), e.getY())){
			back.act();
			}
	}
	
	public MouseMotionListener getMouseMotionListener(){
		return this;
	}
	public MouseListener getMouseListener(){
		return this;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
