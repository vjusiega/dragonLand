package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dragonComponents.Dragon;
import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Button;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import guiPractice.sampleGames.MouseFollower;

/**
 * @author Kat 
 * @author Jenniber
 *
 */
public class HomeScreen extends ClickableScreen implements Runnable{
	

	private Graphic background;

	public HomeScreen(int width, int height) {
		super(width, height);
		
		Thread play = new Thread(this);
		play.start();
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		background=new Graphic(0,0,getWidth(),getHeight(),"img/Grassland.png");
		viewObjects.add(background);
		//addbuttons
		Button shop = new Button(getWidth()-110-(getWidth()*2/100),(getHeight()*5/100),  110,  50,  "Shop",DragonLand.DARKER_NUDE,  null);
		viewObjects.add(shop);
//		Button minigame = new Button(getWidth()-110-(getWidth()*2/100),(getHeight()*5/100)+53,  110,  50,  "Minigame",DragonLand.DARKER_NUDE,  new Action(){
//
//			@Override
//			public void act() {
//				DragonLand.game.setScreen(miniGameScreen);
//			}
//		
//		});
//		viewObjects.add(minigame);
		Button help = new Button(getWidth()-50-(getWidth()*2/100),getHeight()-50-(getHeight()*2/100),  50,  50,  "?",DragonLand.DARKER_NUDE,  null);
		viewObjects.add(help);
		Button title = new Button((getWidth()*2/100),(getHeight()*5/100),  350,  50,  "Welcome to Dragon Land!",DragonLand.DARKER_NUDE,  null);
		title.setSize(26);
		viewObjects.add(title);

		addAnimation(viewObjects,100,100, "Jack", 130, "img/dragonOne.png");
	
	}
	
	private void addAnimation(ArrayList<Visible> viewObjects,int x,int y, String name, int price,String imgSrc) {
		
		AnimatedComponent a = new Dragon(x,y,100,100,name, price, imgSrc);
		
		try{
			ImageIcon icon = new ImageIcon("img/dragonSeven.png");
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
				a.addFrame(cropped, 300);
				if(i==numberRow*rows-1)
					i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	//	a.setVx(1);
		viewObjects.add(a);

		
		a.update();
		a.setX(x);
		a.setY(y);
		a.play();
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
