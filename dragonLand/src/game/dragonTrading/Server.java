package game.dragonTrading;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import guiPractice.components.Button;

import game.DragonLand;
import game.mainScreenTeam.Dragon;
import guiPractice.Screen;
import guiPractice.components.Action;

public class Server{
	
	private ObjectOutputStream output;
		//this is what is sent out of the system
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	//constructor
	public Server() {
	}
	
	public void startTrading(TradingScreen s){
		Button b = new Button(300, 250, 100, 100, "Trade", Color.green);
		b.setAction(new Action(){
			public void act() {
				try {
					whileTrading(s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		s.addObject(b);
	}
	
	//set up and run the server
	public void startRunning(TradingScreen s){
		try{
			server = new ServerSocket(6789, 100); // first number for testing purposes
			boolean waiting = true;
			while(waiting){
				try{
					System.out.println("I am starting and I am a server.");
					waitForConnection(s);	
					setupStreams(s);
					startTrading(s);
					waiting = false;
					//whileTrading(s);
				}catch(EOFException eofException){
					s.displayConnectionMessage("\n Server ended the connection!");
				}finally{
					closeCrap();
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}


	private void waitForConnection(TradingScreen s) throws IOException{
		System.out.println("I am in method");
		s.displayConnectionMessage("Waiting for someone to connect... \n");
		connection = server.accept();
		s.displayConnectionMessage("Now connected to " + connection.getInetAddress().getHostName());

	}
	
	private void setupStreams(TradingScreen s) throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		s.displayConnectionMessage("\n Streams are now setup! \n");
	}
	
	private void whileTrading(TradingScreen s) throws IOException{
		String message = "Sparky"; //simple prompt on screen
		sendDragon(message);
		System.out.println("I'm here with sparky");
//		ableToType(true); 
		do{
			try{
				message = (String) input.readObject(); 
				s.displayConnectionMessage("\n" + "I got a dragon");
			}catch(ClassNotFoundException classNotFoundException){
				s.displayConnectionMessage("\n hopefully this message never displays");
			}
		}while(!message.equals("CLIENT - END")); 
	}
	
	private void closeCrap(){
		showMessage("\n Closing connections \n"); 
//		ableToType(false);
		try{
			output.close();
			input.close(); 
			connection.close(); 
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
		
	private void sendDragon(String d){
		try{
			output.writeObject(d);
			output.flush(); 	
		}catch(Exception ioException){
			ioException.printStackTrace();
			System.out.println("Weird stuff sent through stream");
		}
	}
		
	//only displays, does not send
	private void showMessage(final String text){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
//						chatWindow.append(text); //adds a message to the end of the document and then updates chatWindow
					}
				}
		);
	}
	
//	private void ableToType(final boolean tof){
//		SwingUtilities.invokeLater(
//				new Runnable(){
//					public void run(){
//		
//					}
//				}
//		);
//	}
}
