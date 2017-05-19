package game.dragonTrading;
import java.io.*;
import game.DragonLand;
import game.mainScreenTeam.Dragon;
import guiPractice.Screen;

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server{
	
	private ObjectOutputStream output;
		//this is what is sent out of the system
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	//constructor
	public Server() {
	}
	
	//set up and run the server
	public void startRunning(TradingScreen s){
		try{
			server = new ServerSocket(6789, 100); // first number for testing purposes
			while(true){
				try{
					waitForConnection(s);	
					setupStreams(s);
					whileTrading(s);
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
		Dragon message = new Dragon(0,0,0,0, "img/dragon1.png"); //simple prompt on screen
		sendDragon(message);
//		ableToType(true); 
		do{
			try{
				message = (Dragon) input.readObject(); 
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
		
	private void sendDragon(Dragon d){
		try{
			output.writeObject(d);
			output.flush(); 	
		}catch(IOException ioException){
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
		//allows us to update only the chatwindow and not the entire GUI
		//this creates a thread that updates a part of the GUI
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
	
//server class is done
