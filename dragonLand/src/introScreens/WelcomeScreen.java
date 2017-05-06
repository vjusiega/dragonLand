package introScreens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.mainScreenTeam.Dragon;
import game.miniGameTeam.GameVioletta;
import guiPractice.ClickableScreen;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Graphic;
import guiPractice.components.GraphicMovingComponent;
import guiPractice.components.Visible;

public class WelcomeScreen extends ClickableScreen{
	
	private ArrayList<Dragon> dragons;
	private Graphic background;

	public WelcomeScreen(int width, int height) {
		super(width, height);
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background = new Graphic(0,0,getWidth(),getHeight(),"img/sunsetBackground.jpg");
		viewObjects.add(background);
		
		dragons = new ArrayList<Dragon>();
		viewObjects.add(setUpDragons(1));
		viewObjects.add(setUpDragons(3));
		
		GraphicMovingComponent fog = new Fog(0, 5*getHeight()/7, 100, 100, "img/introFog.png", 2, 0);
		viewObjects.add(fog);
		
	}
	
	public Dragon setUpDragons(int drag){
		String imgSrc = "img/dragon" + drag + ".png";
		int dragonHeight = getHeight()/8;
		int dragonWidth = (int) (dragonHeight * 0.8);
		int xPos;
		if(drag == 1){
			xPos = getWidth()/4;
		}else{
			xPos = (3*getWidth()/4) - dragonWidth;
		}
		
		int yPos = (-1)*dragonHeight;
		Dragon d = new Dragon(xPos, yPos, dragonHeight, dragonWidth, " ", 0, imgSrc);
		dragons.add(d);
		setDragonAnimation(d, imgSrc);
		d.setX(xPos);
		d.setY(yPos);
		d.setDirection(6);
		d.play();
		
		return d;
	}
	
	public static int getDragonY(){
		return getHeight()/4;
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
	

}
