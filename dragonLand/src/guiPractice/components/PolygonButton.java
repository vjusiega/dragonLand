package guiPractice.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

public class PolygonButton extends Component implements Clickable {

	
	private Polygon shape;
	private Action action;
	public PolygonButton(Polygon shape, Action action) {
		super(0, 0, 0, 0);
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
		}
	}

	@Override
	public Action getAction() {
		return action;
	}


	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public boolean isAnimated() {
		return false;
	}


	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		double thickness = 2;
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke((float) thickness));
		g.draw(shape);
	}

	//@Override
//	public void update() {
//		update(image.create)
//	}
}