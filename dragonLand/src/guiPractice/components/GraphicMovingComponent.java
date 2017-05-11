package guiPractice.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public abstract class GraphicMovingComponent extends MovingComponent {

	private int x;
	private int y;
	private BufferedImage image;
	private String img;
	private boolean loadedImages;
	
	public GraphicMovingComponent(int x, int y, int w, int h, String imageLocation) {
		super(x, y, w, h);
		img = imageLocation;
		loadedImages=false;
		loadImages(img, w, h);
	}

	@Override
	public void drawImage(Graphics2D g) {
		Graphic image = new Graphic(0, 0, 100, 100, img);
		g.drawImage(image.getImage(), image.getX(), image.getY(), image.getWidth(), image.getHeight(), null);
	}
	
	public BufferedImage getImage() {
		return image;
	}

	private void loadImages(String imageLocation, int w, int h) {
		try{
			//get image full size
			ImageIcon icon = new ImageIcon(imageLocation);
			
			if(w==0&&h==0){
				image=new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
				Graphics2D g=image.createGraphics();
				g.drawImage(icon.getImage(), 0, 0, null);
			}else{
				image=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g=image.createGraphics();
				g.drawImage(icon.getImage(),0,0,w,h,0,0,icon.getIconWidth(),icon.getIconHeight(),null);
			}
			
			loadedImages = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
//	public void setX(int x) {
//		
//		this.x=x;
//	}
//
//	public void setY(int y) {
//		this.y=y;
//	}
	
	public boolean isAnimated(){
		return false;
	}

}
