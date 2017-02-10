package dragonComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import guiPractice.components.AnimatedComponent;
import guiPractice.components.MovingComponent;
import guiPractice.components.Visible;


public class Dragon extends MovingComponent {

	/**
	 * @author Kat 
	 *
	 */
	private String name;
	private int price;
	private String imgSrc;
	int UP=1;
	int SIDE=2;
	public AnimatedComponent anim;
	private int x;
	private int y;
	private int h;
	private int w;
	
	
	
	public Dragon(int x, int y, int w, int h, String color, String adjective, int price,String imgSrc) {
		super(x, y, w, h);
		this.x=x;
		this.w=w;
		this.h=h;
		this.y=y;
		this.name=adjective+color;
		this.price=price;
		this.imgSrc=imgSrc;
		addAnimation();
	}
	private void addAnimation() {
		AnimatedComponent a = new AnimatedComponent(x,y,w,h);
		try{
			ImageIcon icon = new ImageIcon(imgSrc);
			int numberRow =3 ;
			int rows =4;
			int w =48;
			int h = 48;
			for(int i=0;i<numberRow*rows;i++){
				
				//declare cropped image
				BufferedImage cropped = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				int leftMargin=0;
				int topMargin =0 ;
				int x1 = leftMargin + w*(i%numberRow);
				int y1=topMargin +h*(i/numberRow);
				Graphics g = cropped.createGraphics();
				g.drawImage(icon.getImage(),0,0,w,h,x1,y1,x1+w,y1+h,null);
				a.addFrame(cropped, 30);
				if(i==numberRow*rows-1)
					i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		anim=a;
		
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


}
