package dragonComponents;

import java.awt.Graphics2D;

import dragonComponents.Dragon;
import guiPractice.components.Action;
import guiPractice.components.Component;
import guiPractice.components.Graphic;
import guiPractice.components.TextLabel;

public class DragonLabel extends Component {
	
	private static final int LABEL_WIDTH = 600;
	private static final int LABEL_HEIGHT = 100;
	
	private static final int LEFT_MARGIN = 5;
	private static final int TOP_MARGIN = 5;
	
	private Dragon dragon;
	private String buttonType;
	private Action action;
	
	private TextLabel dragonName;
	private PriceLabel dragonPrice;
	private ShopActionButton button;
	private Graphic dragonImage;

	public DragonLabel(int x, int y, Dragon d, String t, Action act) {
		super(x, y, LABEL_WIDTH, LABEL_HEIGHT);
		this.dragon = d;
		this.buttonType = t;
		action = act;
	}

	@Override
	public void update(Graphics2D visible) {
		
		int imageSide = LABEL_HEIGHT - 2 * TOP_MARGIN;
		//dragonImage = new Graphic(LEFT_MARGIN , TOP_MARGIN, imageSide, imageSide, dragon.getImgSrc());
		dragonImage = new Graphic(100 , this.getY(), imageSide, imageSide, "img/dragonNine.jpg");
		
		int column2X = 2 * LEFT_MARGIN + imageSide;
		int nameWidth = LABEL_WIDTH - column2X - LEFT_MARGIN;
		int nameHeight = (int)(0.3 * imageSide);
		//dragonName = new TextLabel(column2X, TOP_MARGIN, nameWidth, nameHeight, dragon.getName());
		dragonName = new TextLabel(this.getX()+300, this.getY(), nameWidth, nameHeight, "Dragon");
		
		int row2Y = nameHeight + (int)(1.5 * TOP_MARGIN);
		int row2Width = nameWidth/3;
		int priceHeight = 9 * nameHeight/10;
		//dragonPrice = new PriceLabel(column2X, row2Y, dragon.getPrice());
		dragonPrice = new PriceLabel(this.getX() + 300, this.getY() + 50, 50);
		
		int column3X = column2X + 2 * row2Width;
		int buttonHeight = LABEL_HEIGHT - row2Y - TOP_MARGIN;
		//if(buttonType.toUpperCase().equals("BUY"))
			//button = new ShopActionButton(column3X, row2Y, row2Width, buttonHeight, "BUY", action);
			button = new ShopActionButton(this.getX() + 600, this.getY(), row2Width, buttonHeight, "BUY", action);
		//else if(buttonType.toUpperCase().equals("SELL"))
		//	button = new ShopActionButton(column3X, row2Y, row2Width, buttonHeight, "BUY", action);

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
}
