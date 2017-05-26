package game.dragonTrading;
import java.awt.Color;

import javax.swing.JFrame;

import game.DragonLand;
import guiPractice.components.Action;
import guiPractice.components.Button;


public class ServerTest {

	public static void main(String[] args) {
//		Server sally = new Server();
//		sally.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		sally.startRunning();
		
		Button b = new Button(100, 100, 100, 100, "server", Color.BLUE);
		b.setAction(new Action(){
			public void act() {
				Thread Server = new Thread(new Runnable(){
					public void run(){
						Server s = new Server();
						s.startRunning(DragonLand.tradingScreen);
					}
				});
				Server.run();	
			}
		});
	}

}
