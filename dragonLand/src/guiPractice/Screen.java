package guiPractice;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import guiPractice.components.Visible;

public abstract class Screen {
	
	private int width;
	private int height;
	protected ArrayList<Visible> viewObjects;
	
	protected BufferedImage image;
	
	public Screen(int width, int height){
		viewObjects=new ArrayList<Visible>();
		this.width = width;
		this.height= height;
		initObjects(viewObjects);
		initImage();
		}
	
	public abstract void initObjects(ArrayList<Visible> viewObjects2);

	private void initImage() {
		image=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		update();	
	}

	protected void update() {
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.setColor(Color.black);
		//draw all visible componenets
		for(int i=0;i<viewObjects.size();i++){
			Visible v=viewObjects.get(i);
			g.drawImage(v.getImage(), v.getX(), v.getY(), null);
		}
//		g.setFont(new Font("Helvetica", Font.PLAIN,20));
//		g.drawString("Hello", 40,80);
//		g.drawOval(0, 40, 120, 80);
//		g.drawRect(20,120,80,110);
//		g.drawLine(100, 120, 110, 200);
//		g.drawLine(20, 120, 10, 200);
//		g.drawLine(230, 40, 225, 140);
//		g.setColor(Color.green);
//		for(int i=0;i<image.getWidth();i+=4){
//			g.drawLine(i, 230, i, 238);
//		}
//		for(int i=2;i<image.getWidth();i+=4){
//			g.drawLine(i, 234, i, 238);
//		}
	}
	/**
	 * remove a visible from the screen
	 * @param v
	 */
	public void remove(Visible v){
		viewObjects.remove(v); //removes object that has the same identity, not an object that is equal to v
		/**
		 * array lists notes
		 * while this method is very simple, do not underestimate the trickiness of removing 
		 * items in an ArrayList. it causes indices to chanage:
		 * example suppose you have an ArrayList<Integer> and you want
		 * to remove all values greater that 5
		 * THIS IS BAD:
		 * 
		 * for(int i=0;i<list.size();i++){
		 * 	if(list.get(i)>5)
		 * 		list.remove(i);
		 * }
		 * 
		 * suppose you have (4,5,6,7)
		 * 1) integer is removed at index 2, 
		 * 2) 7 is now index 2
		 * 3) list is (4,5,7)
		 * 4) i gets incremented so 3 is out of bounds and 7 never gets removed. 
		 * instead when an object is removed, you also have to do i--, to compnesate
		 * for change in size.
		 * 
		 * correct code:
		 * 
		 * for(int i=0;i<list.size();i++){
		 * 	if(list.get(i)>5){
		 * 		list.remove(i);
		 * 		i--;
		 * 	}
		 * }
		 * ALSO CORRECT
		 * for(int i=0;i<list.size();i++){
		 * while (i<list.size()&&list.get(i)>5){
		 * list.remove
		 * }}
		 * 
		 * for this reason the following doesnt work because remove messes up the size 
		 * for(Integer i:list){
		 * if(i>5)list.remove(i);
		 * 
		 * }
		 * 
		 * Finally, of you remove using an index, it returns the removed object, so you
		 * can do this: System.out.println(list.remove(0).toString()+"was removed");
		 */
	}
	public void moveToFront(Visible v){
		if(viewObjects.contains(v)){
			viewObjects.remove(v);
			viewObjects.add(v);
		}
	}
	public void moveToBack(Visible v){
		if(viewObjects.contains(v)){
			viewObjects.remove(v);
			viewObjects.add(0,v);
		}
	}
	public BufferedImage getImage(){
		return image;
	}
	//Abstraction 
	//Interface
	//	description of methods
	//abstract class
	//	some methods
	//	some description

	public MouseListener getMouseListener() {
		return null;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public MouseMotionListener getMouseMotionListener() {
		return null;
	}
	
	public KeyListener getKeyListener(){
		return null;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void addObject(Visible v) {
		viewObjects.add(v);
		
	}

	
}
