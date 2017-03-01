package dragonComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import game.DragonLand;
import guiPractice.components.Action;
import guiPractice.components.AnimatedComponent;
import guiPractice.components.Clickable;
import guiPractice.components.Component;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class DragonLabel2 extends Component implements Clickable{

	private static final int LABEL_WIDTH = 770;
	private static final int LABEL_HEIGHT = 100;
	
	public static final int LABEL_TOP_MARGIN = 160;
	public static final int LABEL_LEFT_MARGIN = 110;
	
	private static final int LEFT_MARGIN = 0;
	private static final int TOP_MARGIN = 5;
	
	private Dragon dragon;
	private String buttonType;
	private Action action;
	private ShopBackdrop labelBack;
	private TextLabel dragonName;
	private PriceLabel dragonPrice;
	private ShopActionButton button;
	private BufferedImage dragonImage;
	
	public DragonLabel2(int x, int y, Dragon d, String t) {
		super(x, y, LABEL_WIDTH, LABEL_HEIGHT);
		this.dragon = d;
		this.buttonType = t;
		update();
	}
	
	public DragonLabel2(int x, int y, Dragon d, String t, Action act) {
		super(x, y, LABEL_WIDTH, LABEL_HEIGHT);
		this.dragon = d;
		this.buttonType = t;
		this.action = act;
		update();
	}
	

	@Override
	public void update(Graphics2D g) {
		
		if(dragon != null)
		{
			int column1X = 30;
			int column2X = 145;
			int column3x = 590;
			
			labelBack = new ShopBackdrop(0,0,LABEL_WIDTH,LABEL_HEIGHT, DragonLand.DARKER_NUDE);			
			//dragonImage = new Graphic(column1X, TOP_MARGIN, 85, 85, dragon.getImgSrc());
			dragonName = new ShopLabel(column2X, TOP_MARGIN, 420, 40, dragon.getName(), Color.WHITE);
			
			if(buttonType.toUpperCase().equals("BUY"))
				dragonPrice = new PriceLabel(column2X, 50, dragon.getPrice());
			else dragonPrice = new PriceLabel(column2X, 50, dragon.getPrice()/5);
				
			button = new ShopActionButton(column3x, TOP_MARGIN * 2, 150, 80, buttonType, DragonLand.LIGHT_NUDE, null);
			
			g.drawImage(labelBack.getImage(), 0, 0, null);
			//g.drawImage(dragonImage.getImage(), dragonImage.getX(), dragonImage.getY(), null);
			g.drawImage(dragonName.getImage(), dragonName.getX(), dragonName.getY(), null);
			g.drawImage(dragonPrice.getCoin().getImage(), dragonPrice.getCoin().getX(), dragonPrice.getCoin().getY(), null);
			g.drawImage(dragonPrice.getPriceLabel().getImage(), dragonPrice.getPriceLabel().getX(), dragonPrice.getPriceLabel().getY(), null);
			g.drawImage(button.getImage(), button.getX(), button.getY(), null);
			
			g.drawImage(dragon.getFrame(1), column1X, TOP_MARGIN, 85, 85, null);
		}

	}
	
	public void setAction(Action a){
		action = a;
	}
	
	public static int getLabelWidth() {
		return LABEL_WIDTH;
	}

	public static int getLabelHeight() {
		return LABEL_HEIGHT;
	}
	
	public TextLabel getDragonName() {
		return dragonName;
	}

	public PriceLabel getDragonPrice() {
		return dragonPrice;
	}

	public ShopActionButton getButton() {
		return button;
	}
	
	public BufferedImage getDragonImg() {
		return dragonImage;
	}

	
	public ShopBackdrop getBackdrop()
	{
		return labelBack;
	}

	@Override
	public boolean isHovered(int x, int y) {
		return x > button.getX() + LABEL_LEFT_MARGIN && x < button.getX() + button.getWidth() + LABEL_LEFT_MARGIN && y > getY() + button.getY() && y < getY() + button.getY() + button.getHeight();
	}

	@Override
	public void act() {
		this.action.act();
	}

	@Override
	public Action getAction() {
		return action;
	}
}
