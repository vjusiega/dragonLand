package game.dragonTrading;
import java.io.*;

import java.net.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import game.DragonLand;
import game.mainScreenTeam.Dragon;
import guiPractice.components.Action;
import guiPractice.components.Button;

public class Client{
	

	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String serverIP;
		//IP of the person you are talking to 
	private Socket connection;
	
	
	public Client(String host){
		serverIP = host; 
	}

	
	//connect to server
	public void startRunning(NewTradingScreen thisScreen){
		try{
			connectToServer(thisScreen); 
			setUpStreams(thisScreen);
//			startTrading(s);
			whileTrading(thisScreen);
			thisScreen.trade();
		}catch(EOFException eofException){
			thisScreen.displayConnectionMessage("\n Client terminated connection");
		}catch(IOException ioException){
			ioException.printStackTrace();
		}finally{
			closeCrap(thisScreen);
		}
		
	}
	
	//connect to server
	private void connectToServer(NewTradingScreen thisScreen) throws IOException{
		thisScreen.displayConnectionMessage("Attempting connection... \n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789); 
			//first parameter passes in the IP address
			//6789 is the port number
		thisScreen.displayConnectionMessage("Connected to:" + connection.getInetAddress().getHostName());
			//we get it from the connection because we are getting it from the server
	}
	
	//set up streams to send and receive messages
	private void setUpStreams(NewTradingScreen thisScreen) throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		thisScreen.displayConnectionMessage("\n Streams are now connected \n");
	}
	
	//while chatting with server
	private void whileTrading(NewTradingScreen thisScreen) throws IOException{
		
		//gets info 
				String inputDrag = "";
				boolean done = false;
				do{
					try{
						inputDrag = (String) input.readObject();
						thisScreen.setTheirDragon(inputDrag);
						done = true;
					}catch(ClassNotFoundException classNotFoundException){
						thisScreen.displayConnectionMessage("\n I don't know that object type.");
					}
				}while(!done);
		
		//sends info
		String message = thisScreen.getMyDragon().getName(); 
		sendDragon(message);
	
	}
	
	//close the streams and sockets
	private void closeCrap(NewTradingScreen thisScreen){
		thisScreen.displayConnectionMessage("\n closing stuff down");
		//ableToType(false);
		System.out.println("I am closing");
		try{
			output.close(); //closes output stream
			input.close();
			connection.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//send messages to server
	private void sendMessage(String d, TradingScreen s){
		try{
			output.writeObject(d);
			output.flush();
			s.displayConnectionMessage("Dragon recieved"); 
				//even though you sent to through the stream does not mean that it is shown
				//makes it show on the GUI
		}catch(IOException e){
			//chatWindow.append("\n something messed up sending message");
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