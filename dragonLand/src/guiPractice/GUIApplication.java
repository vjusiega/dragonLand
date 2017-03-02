package guiPractice;

import java.awt.Graphics;

import javax.swing.JFrame;

public abstract class GUIApplication extends JFrame implements Runnable{

	private Screen currentScreen;
	//no main, cant instentiate an abstract class
	public GUIApplication(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated(true);
		int x=40;
		int y=40;
		int width=1000;
		int height=640;
		setBounds(x,y,width,height);
		initScreen();
		setVisible(true);
	}
	//this is a method for creating the starting screen
	protected abstract void initScreen();

	public void setScreen(Screen screen){
		//stop controls from previous screen
		removeListeners();

		setCurrentScreen(screen);
		//add new controls
		addListeners();
	}

	private void removeListeners(){
		if(getCurrentScreen() != null){
			if(getCurrentScreen().getMouseListener() != null) removeMouseListener(getCurrentScreen().getMouseListener());
			if(getCurrentScreen().getMouseMotionListener() != null) removeMouseMotionListener(getCurrentScreen().getMouseMotionListener());
			if(getCurrentScreen().getKeyListener() != null) removeKeyListener(getCurrentScreen().getKeyListener());
			//		if(currentScreen.getMouseWheelListener() != null) removeMouseWheelListener(currentScreen.getMouseWheelListener());
		}
	}

	private void addListeners(){
		if(getCurrentScreen() != null){
			if(getCurrentScreen().getMouseListener() != null)addMouseListener(getCurrentScreen().getMouseListener());
			if(getCurrentScreen().getMouseMotionListener() != null) addMouseMotionListener(getCurrentScreen().getMouseMotionListener());
			if(getCurrentScreen().getKeyListener() != null){
				addKeyListener(getCurrentScreen().getKeyListener());
			}
			//		if(currentScreen.getMouseWheelListener() != null) addMouseWheelListener(currentScreen.getMouseWheelListener());
		}
	}

	public void paint(Graphics g){
		g.drawImage(getCurrentScreen().getImage(), 0, 0, null);
	}

	public void run(){
		while(true){
			getCurrentScreen().update();
			repaint();
			try {
				Thread.sleep(40);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Screen getCurrentScreen() {
		return currentScreen;
	}
	public void setCurrentScreen(Screen currentScreen) {
		this.currentScreen = currentScreen;
	}

}
