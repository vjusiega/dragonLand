package dragonComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import guiPractice.components.AnimatedComponent;
import guiPractice.components.MovingComponent;

public class GameDragon extends AnimatedComponent{
	
	private String imgSrc;
	
	//must add picture and animate
	
	public GameDragon(int x, int y, int w, int h, String imgSrc) {
		super(x, y, w, h);
		setX(x);
		setY(y);
		this.imgSrc=imgSrc;
	}
	
	public void addFrames(){
		try{
			ImageIcon icon = new ImageIcon(imgSrc);
			int numberRow = 3 ;
			int rows = 4;
			int w = 48;
			int h = 48;
			for(int i=0; i < numberRow*rows; i++){
				
				//declare cropped image
				BufferedImage cropped = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				int leftMargin=0;
				int topMargin =0 ;
				int x1 = leftMargin + w*(i%numberRow);
				int y1=topMargin +h*(i/numberRow);
				Graphics g = cropped.createGraphics();
				g.drawImage(icon.getImage(),0,0,w,h,x1,y1,x1+w,y1+h,null);
				//a.addFrame(cropped, 300);
				addFrame(cropped, 10);
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
		if(true){
			if(currentFrame==2)
				currentFrame=0;
		}
	}

	@Override
	public void drawImage(Graphics2D g){
		super.drawImage(g);
	}
}
