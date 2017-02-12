package dragonComponents;

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


public class Dragon extends AnimatedComponent implements DragonToShop{

	
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
	
	int UP=0;
	int LEFT=1;
	int RIGHT =2;
	int DOWN=3;

	public Dragon(int x, int y, int w, int h,  String name, int price, String imgSrc) {
		super(x, y, w, h);
		
		this.name=name;
		this.price=price;
		this.imgSrc=imgSrc;
		initialY=y;
		initialX=x;

		if(y<350){
			direction=UP;
			setVy(-1);
		}else{
			direction=RIGHT;
			setVx(1);
		}
			
	}


	@Override
	public void checkBehaviors() {
		
		if(direction ==UP){
			if(currentFrame==2)
				currentFrame=0;
			if(Math.abs(getY()-initialY)>30){
				direction=DOWN;
				setVy(1);
			}
		}
		if(direction ==DOWN){
			if(currentFrame==2)
				currentFrame=0;
			if(Math.abs(getY()-initialY)>=20){
				direction=UP;
				setVy(-1);
			}
		}
		if(direction ==LEFT){
			if(currentFrame<3||currentFrame==5)
				currentFrame=3;
			if(Math.abs(getX()-initialX)>=30){
				direction=RIGHT;
				setVx(1);
			}
		}
		if(direction ==RIGHT){
			if(currentFrame<6||currentFrame==8)
				currentFrame=6;
			if(Math.abs(getX()-initialX)>=30){
				direction=LEFT;
				setVx(-1);
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




}
