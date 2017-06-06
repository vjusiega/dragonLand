package guiPractice.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import game.DragonLand;

public class TextLabel extends Component {

	private String text;
	protected Font font;
	private float size;
	private Color color;
	
	public TextLabel(int x, int y, int w, int h,String text) {
		super(x, y, w, h);
		this.text=text;
		font=getFont();
		size=20;
		color = Color.black;
		update();
	}
	
	public void setColor(Color s){
		color = s; 
	}

	public void setText(String s){
		this.text=s;
		update();
	}
	
	public String getText() {
		return text;
	}



	public void setFont(Font font) {
		this.font = font;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size){
		this.size=size;
		font=font.deriveFont(size);
		update();
	}


	public void update(Graphics2D g) {
		g=clear();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
		if(text!=null){
			g.setColor(color);
			g.setFont(font);
			g.drawString(text, 4, getHeight()-5);
		}
	}

}
