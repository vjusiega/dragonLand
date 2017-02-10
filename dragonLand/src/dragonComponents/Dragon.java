package dragonComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import guiPractice.components.AnimatedComponent;
import guiPractice.components.Visible;


public class Dragon extends AnimatedComponent {

	/**
	 * @author Kat 
	 *
	 */
	private String name;
	private int price;
	private String imgSrc;
	int UP=1;
	int SIDE=2;
	
	
	
	public Dragon(int x, int y, int w, int h, String name, int price,String imgSrc) {
		super(x, y, w, h);
		this.name=name;
		this.price=price;
		this.imgSrc=imgSrc;
		addAnimation();
	}
	private void addAnimation() {
		try{
			ImageIcon icon = new ImageIcon(imgSrc);
			int numberRow = 3 ;
			int rows = 4;
			int w = 48;
			int h = 48;
			for(int i=0;i<numberRow*rows;i++){
				//declare cropped image
				BufferedImage cropped = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				int leftMargin=0;
				int topMargin =0 ;
				int x1 = leftMargin + w*(1%numberRow);
				int y1=topMargin +h*(i/numberRow);
				Graphics g = cropped.createGraphics();
				g.drawImage(icon.getImage(),50,50,w,h,x1,y1,x1+w,y1+h,null);
				this.addFrame(cropped, 30);
				if(i==numberRow*rows-1)
					i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void checkBehaviors() {
		
	}

	@Override
	public void drawImage(Graphics2D g) {
		
	}
	public void animationUp(){
		
	}
	public void animationDown(){
		
	}
	public void animationLeft(){
		
	}
	public void animationRight(){
		
	}
//	public void move(int x, int y){
//		this.x=x;
//		this.y=y
//	}

}
