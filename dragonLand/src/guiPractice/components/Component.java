package guiPractice.components;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Component implements Visible {

	private int x;
	private int y;
	private int w;
	private int h;
	private BufferedImage image;
	
	public static Font font = new Font("Times New Roman", Font.PLAIN, 12);
	
	public Font getFont(){
		return font;
	}
	
	public static void setBaseFont(Font fontf){
		System.out.println("Font created");
		font = fontf; 
	}
	
	public Component(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		image=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		
	}
	/**
	 * draw the component
	 * @param createGraphics
	 */
	public abstract void update(Graphics2D g);
	
	public Graphics2D clear(){
		image=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		return image.createGraphics();
	}
	public BufferedImage getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {	
		return w;
	}
	
	public void setWidth(int w){
		this.w = w;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight() {
		return h;
	}
	
	public void setHeight(int h){
		this.h = h;
	}
	public boolean isAnimated(){
		return false;

	}
	public void update(){
		update(image.createGraphics());
	}

}
