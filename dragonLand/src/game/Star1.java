package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import guiPractice.components.Graphic;
import guiPractice.components.MovingComponent;

public class Star1 extends MovingComponent {

	private Graphic image;
	private int vy;
	
	public Star1(int x, int y, int w, int h) {
		super(x, y, w, h);
//		setX(getRandomX());
//		setY(y);
		vy = 0;
		update();
	}

	private int getRandomX() {
		int randomX = (int) (Math.random()*GameScreen.getWidth()); 
		System.out.println(randomX);
		return randomX;
	}

	@Override
	public void checkBehaviors() {
		if(getY() > 100){
			//setY((getY() + getVy()));
		}
	}

	@Override
	public void drawImage(Graphics2D g) {
		image = new Graphic(getRandomX(), 100, 1.5, "img/star.png");
		g.drawImage(image.getImage(), 100, 100, null);
		
//		ImageIcon icon = new ImageIcon("img/star.png");
//		
//		int newWidth = (int)(icon.getIconWidth()*1.5);
//		int newHeight = (int)(icon.getIconHeight()*1.5);
//		image=new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB);
//		image.createGraphics();
//		g.drawImage(icon.getImage(),0,0,newWidth,newHeight,0,0,icon.getIconWidth(),icon.getIconHeight(),null);
	}

	public double getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

}
