package guiPractice.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import game.Sound;

public class PolygonButton extends Component implements Clickable {

	
	private Polygon shape;
	private Action action;
	
	public PolygonButton(int x, int y , int w, int h, Polygon shape, Action action) {
		super(x, y+25, w, h);
		this.shape = shape;
		this.action = action;
		update();
	}

	@Override
	public boolean isHovered(int x, int y) {
		return shape.contains(x-getX(), y-getY());
	}

	@Override
	public void act() {
		if(action != null){
			action.act();
			//Sound.CLICK.play();
		}
	}

	@Override
	public Action getAction() {
		return action;
	}


	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		double thickness = 2;
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke((float) thickness));
		//g.draw(shape);
	}


}