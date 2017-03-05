package game.shopScreen;

import java.awt.Color;
import java.awt.Graphics2D;

import game.DragonLand;
import guiPractice.components.Component;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
/*
 * @author Wendy, Zheng
 * */
public class CoinLabel extends Component {

	private static final int TOP_MARGIN = 2;
	private static final int LEFT_MARGIN = 5;
	private static final int WIDTH = 160;
	private static final int HEIGHT = 28;
	
	private int coins;
	private ShopBackdrop coinBack;
	private Graphic coin;
	private TextLabel coinLabel;
	private Color color;
	
	public CoinLabel(int x, int y, int p) {
		super(x, y, WIDTH, HEIGHT);
		color = DragonLand.LIGHT_NUDE;
		coins = p;
		update();
	}
	
	public CoinLabel(int x, int y, int p, Color c) {
		super(x, y, WIDTH, HEIGHT);
		color = c;
		coins = p;
		update();
	}
	
	@Override
	public void update(Graphics2D g) {
		int coinSide = getHeight() - 2 * TOP_MARGIN;
		
		int priceX = coinSide + 2 * LEFT_MARGIN;
		int priceWidth = getWidth() - priceX - LEFT_MARGIN;
		int priceHeight = getHeight() - 2 * TOP_MARGIN;
		
		coinBack = new ShopBackdrop(0, 0, WIDTH, HEIGHT, color);
		coinBack.setArc(15);
		coinLabel = new TextLabel(LEFT_MARGIN * 2,TOP_MARGIN, priceWidth, priceHeight, "Coins: " + coins);
		coin = new Graphic(LEFT_MARGIN + priceWidth,TOP_MARGIN, coinSide, coinSide, "img/Coin.png");
		
		g.drawImage(coinBack.getImage(), coinBack.getX(), coinBack.getY(), coinBack.getWidth(), coinBack.getHeight(), null);
		g.drawImage(coinLabel.getImage(), coinLabel.getX(), coinLabel.getY(), coinLabel.getWidth(), coinLabel.getHeight(), null);
		g.drawImage(coin.getImage(), coin.getX(), coin.getY(), coin.getWidth(), coin.getHeight(), null);
	}
	
	public void setCoins(int coins) {
		this.coins = coins;
		coinLabel.setText("Coins: " + coins);
	}
	
	public void setCoins() {
		coins = DragonLand.coins;
		coinLabel.setText("Coins: " + coins);
		update();
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