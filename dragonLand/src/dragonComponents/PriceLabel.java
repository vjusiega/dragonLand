package dragonComponents;

import java.awt.Graphics2D;

import game.DragonLand;
import guiPractice.components.Component;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;

public class PriceLabel extends Component {
	
	
	private static final int TOP_MARGIN = 2;
	private static final int LEFT_MARGIN = 2;
	private static final int WIDTH = 120;
	private static final int HEIGHT = 30;
	
	private int price;
	private Graphic coin;
	private TextLabel priceLabel;
	
	public PriceLabel(int x, int y, int p) {
		super(x, y, WIDTH, HEIGHT);
		price = p;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		int coinSide = getHeight() - 2 * TOP_MARGIN;
		coin = new Graphic(getX() + LEFT_MARGIN, getY() + TOP_MARGIN, coinSide, coinSide, "img/Coin.png");
		
		int priceX = coinSide + 2 * LEFT_MARGIN;
		int priceWidth = getWidth() - priceX - LEFT_MARGIN;
		int priceHeight = getHeight() - 2 * TOP_MARGIN;
		priceLabel = new ShopLabel(getX() + priceX, getY() + TOP_MARGIN, priceWidth, priceHeight, "" + price, DragonLand.LIGHT_NUDE);
		
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
		
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public void setCoin(Graphic coin) {
		this.coin = coin;
	}

	public void setPriceLabel(TextLabel priceLabel) {
		this.priceLabel = priceLabel;
	}
	
	
	
	public int getPrice() {
		return price;
	}

	public Graphic getCoin()
	{
		return coin;
	}
	
	public TextLabel getPriceLabel()
	{
		return priceLabel;
	}

}
