package game;

import java.awt.Graphics2D;

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
	
	public DragonLabel(int x, int y, Dragon d, String t, Action act) {
		super(x, y, LABEL_WIDTH, LABEL_HEIGHT);
		dragon = d;
		buttonType = t;
		action = act;
	}

	@Override
	public void update(Graphics2D visible) {
		
		int imageSide = LABEL_HEIGHT - 2 * TOP_MARGIN;
		Graphic dragonImage = new Graphic(LEFT_MARGIN , TOP_MARGIN, imageSide, imageSide, dragon.getImage());
		
		int column2X = 2 * LEFT_MARGIN + imageSide;
		int nameWidth = LABEL_WIDTH - column2X - LEFT_MARGIN;
		int nameHeight = (int)(0.3 * imageSide);
		TextLabel dragonName = new TextLabel(column2X, TOP_MARGIN, nameWidth, nameHeight, dragon.getName());
		
		int row2Y = nameHeight + (int)(1.5 * TOP_MARGIN);
		int row2Width = nameWidth/3;
		int priceHeight = 9 * nameHeight/10;
		PriceLabel dragonPrice = new PriceLabel(column2X, row2Y, row2Width, priceHeight, dragon.getPrice());
		
		
		int column3X = column2X + 2 * row2Width;
		int buttonHeight = LABEL_HEIGHT - row2Y - TOP_MARGIN;
		ShopActionButton button;
		if(buttonType.toUpperCase().equals("BUY"))
			button = new ShopActionButton(column3X, row2Y, row2Width, buttonHeight, "BUY", action);
		else if(buttonType.toUpperCase().equals("SELL"))
			button = new ShopActionButton(column3X, row2Y, row2Width, buttonHeight, "BUY", action);

	}

}
