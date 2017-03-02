package game.mainScreenTeam;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import game.DragonToShop;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.MovingComponent;
import guiPractice.components.Visible;



public class Dragon extends AnimatedComponent {

	
	/**
	 * @author Kat 
	 *
	 */
	private String name;
	private int price;
	private String imgSrc;
	int direction;
	int initialX;
	int initialY;
	boolean hungryBox;
	
	
	private int UP=0;
 	private int LEFT=1;
 	private int RIGHT =2;
 	private int DOWN=4;
 	private double VY= Math.random();
 	
	public Dragon(int x, int y, int w, int h,  String name, int price, String imgSrc) {
		super(x, y, w, h);
		
		this.name=name;
		this.price=price;
		this.imgSrc=imgSrc;
		this.hungryBox = false;

	}

	public void setY(int y){
		initialY=y;
		super.setY(y);
		if(y<350){
			direction=DOWN;
		}else{
			direction=(int)(Math.random()*1+1);
			if(direction == 1 )
				currentFrame=3;
			else
				currentFrame=6;
		}
	}
	public void setX(int x){
		initialX=x;
		super.setX(x);
	}
	
	public void checkBehaviors() {
		//System.out.println(direction);
		if(direction ==UP){
			setVy(-VY);
			if(currentFrame==2)
				currentFrame=0;
			if((initialY-getY())>30){
				direction=DOWN;
			}
		}
		if(direction ==DOWN){
			setVy(VY);
			if(currentFrame==2)
				currentFrame=0;
			if((getY()-initialY)>30){
				direction=UP;
			}
		}
		if(direction ==LEFT){
			setVx(-VY);
			if(currentFrame<3||currentFrame>=5)
				currentFrame=3;
			if((initialX-getX())>=30){
				currentFrame=6;
				direction=RIGHT;
				
			}
		}
		if(direction ==RIGHT){
			setVx(VY);
			if(currentFrame<6||currentFrame==8)
				currentFrame=6;
			if((getX()-initialX)>30){
				currentFrame=3;
				direction=LEFT;
				
			}
		}
	}

	@Override
	public void drawImage(Graphics2D g) {
		super.drawImage(g);
	}
	public void animationUp(){
		direction=UP;
	}
	public void animationDown(){
		direction=DOWN;
	}
	public void animationLeft(){
		direction=LEFT;
	}
	public void animationRight(){
		direction = RIGHT;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getImgSrc() {
		return imgSrc;
	}


	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public boolean getHungryBox() {
		return hungryBox;
	}
	
	public void setHungryBox(boolean hungryBox){
		this.hungryBox = hungryBox; 
	}

}