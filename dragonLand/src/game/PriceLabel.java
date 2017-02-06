package game;

import java.awt.Graphics2D;

import guiPractice.components.Component;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;

public class PriceLabel extends Component {
	
	
	private static final int TOP_MARGIN = 2;
	private static final int LEFT_MARGIN = 2;
	
	private int price;
	
	public PriceLabel(int x, int y, int w, int h, int p) {
		super(x, y, w, h);
		price = p;
	}

	@Override
	public void update(Graphics2D viewObjects) {
		int coinSide = getHeight() - 2 * TOP_MARGIN;
		Graphic coin = new Graphic(LEFT_MARGIN, TOP_MARGIN, coinSide, coinSide, "img/Coin.png");
		
		int priceX = coinSide + 2 * LEFT_MARGIN;
		int priceWidth = getWidth() - priceX - LEFT_MARGIN;
		int priceHeight = getHeight() - 2 * TOP_MARGIN;
		TextLabel priceLabel = new TextLabel(priceX, TOP_MARGIN, priceWidth, priceHeight, "" + price);
		
		view

	}

}
