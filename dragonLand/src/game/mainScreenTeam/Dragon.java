package game.mainScreenTeam;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.MovingComponent;
import guiPractice.components.Visible;
import introScreens.WelcomeScreen;



public class Dragon extends AnimatedComponent {

	
	/**
	 * @author Kat 
	 * w/ mods by Violetta
	 */
	private String name;
	private int price;
	private String imgSrc;
	private int direction;
	private int initialX;
	private int initialY;
	private boolean hungryBox;
	
	public void setInitialY(int y){
		initialY = y;
	}
	
	private boolean bouncing;
	private int bounceDistance; 
	
	
	//main screen functions
	private int UP = 0;
 	private int LEFT = 1;
 	private int RIGHT = 2;
 	private int DOWN = 4;
 	
 	
 	private double VY = Math.random(); //to randomize speed
	private HungryBox hungryBoxObj;
 	
 	//constructor for dragons on main screen
	public Dragon(int x, int y, int w, int h,  String name, int price, String imgSrc) {
		super(x, y, w, h);
		
		this.name=name;
		this.price=price;
		this.imgSrc=imgSrc;
		this.hungryBox = false;
		bounceDistance = 30; //based on the value Kat had before for her Dragons on main screen
		bouncing = true; 

	}
	
	//display dragon constructor that does not bounce
	public Dragon(int x, int y, int w, int h, String imgSrc){
		super(x, y, w, h);
		name = "display_only";
		price = 0; 
		this.imgSrc = imgSrc;
		this.hungryBox = false; 
		setDragonAnimation(this, imgSrc);
		bouncing = false; 
	}
	
	//display dragon constructor that bounces
	public Dragon(int x, int y, int w, int h, String imgSrc, int bounceDistance, double VY){
		super(x, y, w, h);
		name = "display_only";
		price = 0; 
		this.imgSrc = imgSrc;
		this.hungryBox = false; 
		setDragonAnimation(this, imgSrc);
		bouncing = true; 
		this.bounceDistance = bounceDistance; 
		this.VY = VY;
	}
	
	public void setCurrentFrame(int frame){
		currentFrame = frame;
	}
	
	/*
	 * set based on the y coordinate given
	 * tells it how it will move
	 * called in HomeKat when adding a dragon
	 */
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
	/*
	 * allows for directions to switch 
	 */
	public void checkBehaviors() {
		//System.out.println(direction);
		//if(hungryBox)
		if(direction == UP){
			if(bouncing){
				setVy(-VY);
				if((initialY-getY())>bounceDistance){
					direction=DOWN;
				}
			}
			if(currentFrame == 2){
				currentFrame = 0;
			}
		}
		if(direction == DOWN){
			if(bouncing){
				setVy(VY);
				if((getY()-initialY)>bounceDistance){
					direction=UP;
				}
			}
			if(currentFrame==2){
				currentFrame=0;
			}
		}
		if(direction == LEFT){
			if(bouncing){
				setVx(-VY);
				if((initialX-getX())>=bounceDistance){
					currentFrame=6;
					direction=RIGHT;
				}
			}
			if(currentFrame<3||currentFrame>=5){
				currentFrame=3;
			}

		}
		if(direction == RIGHT){
			if(bouncing){
				setVx(VY);
				if((getX()-initialX)>bounceDistance){
					currentFrame=3;
					direction=LEFT;
					
				}
			}
			if(currentFrame < 6||currentFrame==8){
				currentFrame = 6;
			}
		}
	}
/*
 * getters and setters
 */
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
	
	public void setDirection(int dir){
		this.direction = dir;
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
	
	public void setDragonAnimation(Dragon d, String imgSrc){
		AnimatedComponent a = (AnimatedComponent) d;
		
		try{
			ImageIcon icon = new ImageIcon(imgSrc);
			int numberRow = 3;
			int rows = 4;
			int w = 48;
			int h = 48;
			for(int i=0; i<numberRow*rows; i++){
				//declare cropped image
				BufferedImage cropped = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				int leftMargin=0;
				int topMargin =0 ;
				int x1 = leftMargin + w*(i%numberRow);
				int y1=topMargin +h*(i/numberRow);
				Graphics g = cropped.createGraphics();
				g.drawImage(icon.getImage(),0,0,w,h,x1,y1,x1+w,y1+h,null);
				a.addFrame(cropped, 300);
				if(i==numberRow*rows-1)
					i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void assignHungryBox(HungryBox hungryBoxObj) {
		this.hungryBoxObj = hungryBoxObj;
	}
	public HungryBox getHungryBoxObj(){
		return hungryBoxObj;
	}
}