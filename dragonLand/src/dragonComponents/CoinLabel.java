package dragonComponents;

import java.awt.Graphics2D;

import game.DragonLand;
import guiPractice.components.Component;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class CoinLabel extends Component {

	private static final int TOP_MARGIN = 2;
	private static final int LEFT_MARGIN = 5;
	private static final int WIDTH = 160;
	private static final int HEIGHT = 28;
	
	private int coins;
	private ShopBackdrop coinBack;
	private Graphic coin;
	private TextLabel coinLabel;
	
	public CoinLabel(int x, int y, int p) {
		super(x, y, WIDTH, HEIGHT);
		coins = p;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		int coinSide = getHeight() - 2 * TOP_MARGIN;
		
		int priceX = coinSide + 2 * LEFT_MARGIN;
		int priceWidth = getWidth() - priceX - LEFT_MARGIN;
		int priceHeight = getHeight() - 2 * TOP_MARGIN;
		
		coinBack = new ShopBackdrop(getX(), getY(), WIDTH, HEIGHT, DragonLand.LIGHT_NUDE);
		coinBack.setArc(15);
		coinLabel = new TextLabel(getX() + LEFT_MARGIN * 2, getY() + TOP_MARGIN, priceWidth, priceHeight, "Coins: " + coins);
		coin = new Graphic(getX() + LEFT_MARGIN + priceWidth, getY() + TOP_MARGIN, coinSide, coinSide, "img/Coin.png");
		
		
	}
	
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public ShopBackdrop getCoinBack()
	{
		return coinBack;
	}
	
	public Graphic getCoin()
	{
		return coin;
	}
	
	public TextLabel getCoinLabel()
	{
		return coinLabel;
	}

	public static int getWdith() {
		return WIDTH;
	}

	
	public Visible[] getVisible()
	{
		return new Visible[] {coinBack, coin, coinLabel};
	}

	public static int getHeight2() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}

}
