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
	//private String otherUsersDragons;
	
	//constructor
	public Server() {
	}
	
	//set up and run the server
	public void startRunning(NewTradingScreen thisScreen){
		try{
			server = new ServerSocket(6789, 100); // first number for testing purposes
			try{
				System.out.println("I am starting and I am a server.");
				waitForConnection(thisScreen);	
				setupStreams(thisScreen);
				whileTrading(thisScreen);
				thisScreen.trade();
			}catch(EOFException eofException){
				thisScreen.displayConnectionMessage("\n Server ended the connection!");
			}finally{
				closeCrap(thisScreen);
			}
			
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	

	private void waitForConnection(NewTradingScreen thisScreen) throws IOException{
		System.out.println("I am in method");
		thisScreen.displayConnectionMessage("Waiting for someone to connect... \n");
		connection = server.accept();
		thisScreen.displayConnectionMessage("Now connected to " + connection.getInetAddress().getHostName());

	}
	
	private void setupStreams(NewTradingScreen thisScreen) throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		thisScreen.displayConnectionMessage("\n Streams are now setup! \n");
	}
	
	private void whileTrading(NewTradingScreen thisScreen) throws IOException{
		String message = thisScreen.getMyDragon().getName(); 
		System.out.println("about to send " + message);
		sendDragon(message);
		
		//gets info 
		String inputDrag = "";
		boolean done = false;
		do{
			try{
				System.out.println("here");
				inputDrag = (String) input.readObject();
				thisScreen.setTheirDragon(inputDrag);
				System.out.println(inputDrag);
				sendDragon("done");
				done = true;
			}catch(ClassNotFoundException classNotFoundException){
				thisScreen.displayConnectionMessage("\n I don't know that object type.");
			}
		}while(!done);		
	}
	
	private void closeCrap(NewTradingScreen thisScreen){
		thisScreen.displayConnectionMessage("\n Closing connections \n"); 
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

}

