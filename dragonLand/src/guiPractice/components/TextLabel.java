package guiPractice.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import game.DragonLand;

public class TextLabel extends Component {

	private String text;
	private String font;
	private int size;
	private Color color;
	
	public TextLabel(int x, int y, int w, int h,String text) {
		super(x, y, w, h);
		this.text=text;
		font="AppleMyungjo";
		size=20;
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

	public String getFont() {
		return font;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size){
		this.size=size;
		update();
	}

	public void setFont(String font){
		this.font=font;
		update();
	}
	public void update(Graphics2D g) {
		g=clear();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
		if(text!=null){
			g.setColor(color);
			g.setFont(new Font("Dialog",Font.BOLD,getSize()));
			g.drawString(text, 4, getHeight()-5);
		}
	}

}
