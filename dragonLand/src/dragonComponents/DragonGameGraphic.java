package dragonComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;


public class DragonGameGraphic {


		private int x;
		private int y;
		private BufferedImage image;
		private boolean loadedImages;
		private String img;
		
		public DragonGameGraphic(int x, int y, String imageLocation) {
			img = imageLocation;
			this.x=x;
			this.y=y;
			loadedImages=false;
			loadImages(imageLocation,0,0);
		}
		public DragonGameGraphic(int x, int y, int w, int h, String imageLocation) {
			this.x=x;
			this.y=y;
			loadedImages=false;
			loadImages(imageLocation,w,h);
		}
		public DragonGameGraphic(int x, int y, double scale, String imageLocation) {
			this.x=x;
			this.y=y;
			loadedImages=false;
			loadImages(imageLocation,scale);
		}

		private void loadImages(String imageLocation, int w, int h) {
			try{
				//get image full size
				ImageIcon icon = new ImageIcon(imageLocation);
				
				
				if(w==0&&h==0){
					//use original size
					image=new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
					image = image.getSubimage(50, 140, 50, 50);
					    
					//draw icon onto image
					Graphics2D g=image.createGraphics();
					g.drawImage(icon.getImage(),0,0,null);
				}else{
					image = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
					image = image.getSubimage((w/3), (h/4)*3, (w/3), (h/4));
					Graphics2D g=image.createGraphics();
					//select coordinate of top
					//left rectangle within image
					//select width and height to display graphic
					//then of the icon you want to display select
					//x and y coordinates and width height
					//this can split an image into parts
					g.drawImage(icon.getImage(),0,0,w,h,0,0,icon.getIconWidth(),icon.getIconHeight(),null);
				}
				
				loadedImages = true;
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		private void loadImages(String imageLocation, double scale) {
			try{
				//get image full size
				ImageIcon icon = new ImageIcon(imageLocation);
				
				int newWidth = (int)(icon.getIconWidth()*scale);
				int newHeight = (int)(icon.getIconHeight()*scale);
				image=new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g=image.createGraphics();
				g.drawImage(icon.getImage(),0,0,newWidth,newHeight,0,0,icon.getIconWidth(),icon.getIconHeight(),null);
				
				loadedImages = true;
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		public BufferedImage getImage() {
			return image;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
		public void setX(int x) {
			this.x=x;
		}

		public void setY(int y) {
			this.y=y;
		}

		public int getWidth() {
			return image.getHeight();
		}

	
		public int getHeight() {
			return image.getWidth();
		}

		public boolean isAnimated() {
			// TODO Auto-generated method stub
			return false;
		}

		public void update() {
			// does nothing image doesnt change

		}
		
		public String getImageLocation(){
			return img;
		}

	}

