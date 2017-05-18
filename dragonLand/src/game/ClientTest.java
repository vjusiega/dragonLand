package game;
import javax.swing.JFrame;

public class ClientTest {

	public static void main(String[] args){
		Client charlie; 
		charlie = new Client("10.8.33.169");
		//127.0.0.1
			//this number means "local host" which means
				//the computer that I'm at 
			//this means that the server is my computer
		charlie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		charlie.startRunning();
			//once the GUI is made and you're connected
			//start running
	}
}
