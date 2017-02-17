package dragonComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dragonComponents.Dragon;
import game.DragonLand;

import guiPractice.components.Action;
import guiPractice.components.Clickable;
import guiPractice.components.Component;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class DragonLabel extends Component implements Clickable{
	
	private static final int LABEL_WIDTH = 770;
	private static final int LABEL_HEIGHT = 100;
	
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
	private Graphic dragonImage;
	
	public DragonLabel(int x, int y, Dragon d, String t) {
		super(x, y, LABEL_WIDTH, LABEL_HEIGHT);
		this.dragon = d;
		this.buttonType = t;
		
		update();
		
	}
	
	public DragonLabel(int x, int y, Dragon d, String t, Action act) {
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
			
			
			int imageSide = LABEL_HEIGHT - 2 * TOP_MARGIN;
			labelBack = new ShopBackdrop(LABEL_LEFT_MARGIN + 15,getY(),LABEL_WIDTH,LABEL_HEIGHT, DragonLand.DARKER_NUDE);
			dragonImage = new Graphic(LABEL_LEFT_MARGIN - 80,  TOP_MARGIN, imageSide, imageSide, dragon.getImgSrc());
			
			int column2X = 2 * LEFT_MARGIN + imageSide;
			int nameWidth = LABEL_WIDTH - column2X - imageSide;
			int nameHeight = (int)(0.5 * imageSide);
			
			//dragonName = new ShopLabel(this.getX()+ column2X, this.getY() + TOP_MARGIN, nameWidth, nameHeight, dragon.getName(), Color.WHITE);
			dragonName = new ShopLabel(column2X+50, TOP_MARGIN, nameWidth-200, nameHeight, dragon.getName(), Color.WHITE);
			
			int row2Y = nameHeight + (int)(1.5 * TOP_MARGIN);
			int row2Width = nameWidth/3;
			int priceHeight = 9 * nameHeight/10;
			
			//dragonPrice = new PriceLabel(this.getX() + column2X, this.getY() + nameHeight + TOP_MARGIN, dragon.getPrice());
			dragonPrice = new PriceLabel(column2X+40, nameHeight + TOP_MARGIN, dragon.getPrice());
			
			int column3X = LABEL_WIDTH - LEFT_MARGIN;
			int buttonHeight = (int)(LABEL_HEIGHT * 0.70);
			
			button = new ShopActionButton(column3X-180, (int)(LABEL_HEIGHT * 0.15), row2Width-40, buttonHeight-5, buttonType, DragonLand.LIGHT_NUDE, action);
			
			g.drawImage(labelBack.getImage(), 0, 0, null);
			g.drawImage(dragonImage.getImage(), dragonImage.getX(), dragonImage.getY(), null);
			g.drawImage(dragonName.getImage(), dragonName.getX(), dragonName.getY(), null);
<<<<<<< HEAD
			g.drawImage(dragonPrice.getCoin().getImage(), dragonPrice.getX(), dragonPrice.getY(), null);
			g.drawImage(dragonPrice.getPriceLabel().getImage(), dragonPrice.getX(), dragonPrice.getY(), null);
=======
			g.drawImage(dragonPrice.getCoin().getImage(), dragonPrice.getX()+5, dragonPrice.getY(), null);
			g.drawImage(dragonPrice.getPriceLabel().getImage(), dragonPrice.getX()+30, dragonPrice.getY(), null);
>>>>>>> refs/remotes/origin/buyShopW
			g.drawImage(button.getImage(), button.getX(), button.getY(), null);
		}

	}
	
	public void setAction(Action a)
	{
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
	
	public Graphic getDragonImg() {
		return dragonImage;
	}

	
	public ShopBackdrop getBackdrop()
	{
		return labelBack;
	}
	
	public Visible[] getVisible()
	{
		return new Visible[] {labelBack, dragonName, dragonPrice.getCoin(), dragonPrice.getPriceLabel(), button, dragonImage};
	}

	@Override
	public boolean isHovered(int x, int y) {

		return x > button.getX()+100 && x < button.getX()+100 + button.getWidth() && y > getY() && y < getY() +getHeight();

	}

	@Override
	public void act() {
		this.action.act();
	}


}
