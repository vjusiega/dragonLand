package game;

/**
 * @author Violetta Jusiega
 *
 */

import java.util.ArrayList;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;

public class GameInstructions extends ClickableScreen {

	public GameInstructions(int width, int height) {
		super(width, height);
	}

	private Graphic background;
	private Button title;
	private Button instructions;
	private String instructionText1;
	private String instructionText2;
	private String instructionText3;
	private Button next;
	private Button skip;
	private Button playGame;
	private Button exit;
	
	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		instructionText1 = "Welcome to the feeding ground.";
		instructionText2 = "Here your hungry dragon will catch and eat the falling stars --the best source of nutrients in Dragon Land--.";
		instructionText3 = "It's important to take good care of your dragon so as a reward you will recieve coins for the stars you catch, so catch as many as you can!";
		
		background = new Graphic(0,0,getWidth(),getHeight(),"img/forest.jpg");
		viewObjects.add(background);
		
		title = new Button((int)(getWidth() * 0.1), 50, (int) (getWidth() * 0.8), 50, "Star Catch", DragonLand.DARKER_NUDE, null);
		viewObjects.add(title);
		
		instructions = new Button((int)(getWidth() * 0.1), 150, (int) (getWidth() * 0.8), 200, instructionText1, DragonLand.DARKER_NUDE, null);
		viewObjects.add(instructions);
		
		exit = new Button(30, 50, 50, 50, "X", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
		viewObjects.add(exit);
		
		skip = new Button((int) (getWidth() * 0.8), (int) (getHeight() * 0.5``), (int) (getWidth() * 0.2), 50, "Skip Instructions", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.miniGameScreen);
			}
		});
		viewObjects.add(skip);
		
	}
	


}
