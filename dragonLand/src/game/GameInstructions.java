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
	private String[] instructionText;
	
	private int sequenceNumber;
	
	private Button next;
	private Button skip;
	private Button playGame;
	private Button exit;
	
	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		sequenceNumber = 0;
		instructionText = new String[6];
		initiateText();
		
		
		
		background = new Graphic(0,0,getWidth(),getHeight(),"img/forest.jpg");
		viewObjects.add(background);
		
		title = new Button((int)(getWidth() * 0.1), 50, (int) (getWidth() * 0.8), 50, "Star Catch", DragonLand.DARKER_NUDE, null);
		viewObjects.add(title);
		
		instructions = new Button((int)(getWidth() * 0.1), 150, (int) (getWidth() * 0.8), 200, instructionText[sequenceNumber], DragonLand.DARKER_NUDE, null);
		viewObjects.add(instructions);
		
		exit = new Button(30, 50, 50, 50, "X", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.homeScreen);
			}
		});
		viewObjects.add(exit);
		
		skip = new Button((int) (getWidth() * 0.78), (int) (getHeight() * 0.9), (int) (getWidth() * 0.2), 50, "Skip Instructions", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				DragonLand.game.setScreen(DragonLand.miniGameScreen);
			}
		});
		viewObjects.add(skip);
		
		next = new Button((int) (getWidth() * 0.78), (int) (getHeight() * 0.7), (int) (getWidth() * 0.2), 50, "Continue", DragonLand.DARKER_NUDE, new Action() {
			@Override
			public void act() {
				updateText();
			}
		});
		viewObjects.add(next);
		
	}
	
	public void updateText(){
		if(sequenceNumber == 5){
			instructions.setText(instructionText[sequenceNumber]);
			remove(skip);
			next.setText("Play");
		}
		else{
			instructions.setText(instructionText[sequenceNumber]);
			sequenceNumber++;
		}
	}
	
	public void initiateText(){
		instructionText[0] = "Welcome to the feeding ground.";
		instructionText[1] = "Here your hungry dragon will catch and eat the falling stars";
		instructionText[2] = "Stars are the best source of nutrients for dragons";
		instructionText[3] = "The more stars you earn the more coins you get!";
		instructionText[4] = "Finally, don't let a star fall or you lose a life.";
		instructionText[5] = "Have fun!";
	}


}
