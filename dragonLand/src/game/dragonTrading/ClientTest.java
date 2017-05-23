package game.dragonTrading;
import java.awt.Color;

import javax.swing.JFrame;

import game.DragonLand;
import guiPractice.components.Action;
import guiPractice.components.Button;

public class ClientTest {

	public static void main(String[] args){
//		Client charlie; 
//		charlie = new Client("10.8.33.169");
		//127.0.0.1
	
//		charlie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		charlie.startRunning();
		
		Button client = new Button(300, 300, 100, 100, "client", Color.pink);
		client.setAction(new Action(){
			public void act(){
				Thread tradeClient = new Thread(new Runnable(){
					public void run(){
						Client c = new Client("127.0.0.1");	
						c.startRunning(DragonLand.tradingScreen);
					}
				});
				tradeClient.run();
			}
		});
	}
}
