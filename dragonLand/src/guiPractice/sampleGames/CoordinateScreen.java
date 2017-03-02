package guiPractice.sampleGames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import guiPractice.Screen;
import guiPractice.components.Button;
import guiPractice.components.Graphic;
import guiPractice.components.MovingComponent;
import guiPractice.components.TextArea;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import guiPractice.components.Action;
import guiPractice.components.AnimatedComponent;

public class CoordinateScreen extends Screen implements MouseMotionListener, MouseListener{

	private TextLabel label;
	private TextArea paragraph;
	private Button button;
	private Graphic picture;
	
	public CoordinateScreen(int width, int height) {
		super(width, height);
		
	}
	
	public void initObjects(ArrayList<Visible> viewObjects2) {
		label=new TextLabel(40,45,760, 40, "Welcome to the game!!!");
		
		//paragraph = new TextArea(40,85,550,500,"SAmple paragraph  SAmple SAmple paragraph SAmple paragraph SAmple paragraph SAmple paragraph SAmple paragraph SAmple paragraph SAmple paragraph SAmple paragraph SAmple paragraph SAmple paragraph SAmple paragraph ");
		//viewObjects.add(paragraph);
		button = new Button((getWidth()-200)/2,(getHeight()-50)/2,200,50,"Click here to start",new Color(0,76,153), new Action(){
			public void act(){
				MouseFollower.game.setScreen(MouseFollower.graphicsMove);
			}
			});
		picture=new Graphic(0,0,getWidth(),getHeight(),"resourses/sampleImages/blueBack.jpg");
		
		viewObjects.add(picture);
		//viewObjects.add(label);
		viewObjects.add(button);
//		MovingComponent mc= new MovingComponent(30,60,80,80);
//		mc.setVy(3);
//		mc.play();
//		viewObjects.add(mc);
		
		addAnimation(viewObjects);
		
	}

	
	private void addAnimation(ArrayList<Visible> viewObjects) {
		AnimatedComponent a = new AnimatedComponent(200,200,170,180);
		try{
			ImageIcon icon = new ImageIcon("resourses/sampleImages/bird.png");
			int numberRow =5 ;
			int rows =3;
			int w =160;
			int h = 190;
			for(int i=0;i<numberRow*rows;i++){
				
				//declare cropped image
				BufferedImage cropped = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				int leftMargin=50;
				int topMargin =0 ;
				int x1 = leftMargin + w*(1%numberRow);
				int y1=topMargin +h*(i/numberRow);
				Graphics g = cropped.createGraphics();
				g.drawImage(icon.getImage(),50,50,w,h,x1,y1,x1+w,y1+h,null);
				a.addFrame(cropped, 30);
				if(i==numberRow*rows-1)
					i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		viewObjects.add(a);
		a.play();
	}

	public void mouseDragged(MouseEvent m) {
	
	}

	public MouseMotionListener getMouseMotionListener(){
		return this;
	}
	public MouseListener getMouseListener(){
		return this;
	}
	public void mouseMoved(MouseEvent m) {
//		label.setText("Mouse at: "+m.getX()+", "+m.getY());
		
	}
	           
	@Override
	public void mouseClicked(MouseEvent e) {
		if(button.isHovered(e.getX(), e.getY())){
			button.act();
			}
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
}
