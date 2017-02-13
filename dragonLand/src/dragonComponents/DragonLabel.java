package dragonComponents;

import java.awt.Color;
import java.awt.Graphics2D;


import dragonComponents.Dragon;
import game.DragonLand;

import guiPractice.components.Action;
import guiPractice.components.Component;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class DragonLabel extends Component {
	
	private static final int LABEL_WIDTH = 770;
	private static final int LABEL_HEIGHT = 100;
	
	private static final int LABEL_LEFT_MARGIN = 100;
	
	private static final int LEFT_MARGIN = 40;
	private static final int TOP_MARGIN = 5;
	
	private Dragon dragon;
	private String buttonType;
	private Action action;
	
	private ShopBackdrop labelBack;
	private TextLabel dragonName;
	private PriceLabel dragonPrice;
	private ShopActionButton button;
	private Graphic dragonImage;

	public DragonLabel(int x, int y, Dragon d, String t, Action act) {
		super(x, y, LABEL_WIDTH, LABEL_HEIGHT);
		this.dragon = d;
		this.buttonType = t;
		action = act;
		
		update();
		
	}

	@Override
	public void update(Graphics2D visible) {
		
		if(dragon != null)
		{
			int imageSide = LABEL_HEIGHT - 2 * TOP_MARGIN;
			
			dragonImage = new Graphic(LABEL_LEFT_MARGIN + LEFT_MARGIN , this.getY() + TOP_MARGIN, imageSide, imageSide, dragon.getImgSrc());
			
			int column2X = 2 * LEFT_MARGIN + imageSide + LABEL_LEFT_MARGIN;
			int nameWidth = LABEL_WIDTH - column2X - imageSide - LEFT_MARGIN;
			int nameHeight = (int)(0.5 * imageSide);
			
			//dragonName = new ShopLabel(this.getX()+ column2X, this.getY() + TOP_MARGIN, nameWidth, nameHeight, dragon.getName(), Color.WHITE);
			dragonName = new ShopLabel(column2X, this.getY() + TOP_MARGIN, nameWidth, nameHeight, dragon.getName(), Color.WHITE);
			
			int row2Y = nameHeight + (int)(1.5 * TOP_MARGIN);
			int row2Width = nameWidth/3;
			int priceHeight = 9 * nameHeight/10;
			
			//dragonPrice = new PriceLabel(this.getX() + column2X, this.getY() + nameHeight + TOP_MARGIN, dragon.getPrice());
			dragonPrice = new PriceLabel(column2X, this.getY() + nameHeight + TOP_MARGIN, dragon.getPrice());
			
			int column3X = LABEL_WIDTH - LEFT_MARGIN;
			int buttonHeight = (int)(LABEL_HEIGHT * 0.70);
			
			//if(buttonType.toUpperCase().equals("BUY"))
			//button = new ShopActionButton(this.getX() + column3X, this.getY() + (int)(LABEL_HEIGHT * 0.15), row2Width, buttonHeight, "BUY", action);
			button = new ShopActionButton(column3X, this.getY() + (int)(LABEL_HEIGHT * 0.15), row2Width, buttonHeight, "BUY", DragonLand.LIGHT_NUDE, action);
			//else if(buttonType.toUpperCase().equals("SELL"))
			//	button = new ShopActionButton(column3X, row2Y, row2Width, buttonHeight, "BUY", action);
		}

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

}
