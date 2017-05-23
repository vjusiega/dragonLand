package game.dragonTrading;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import game.mainScreenTeam.Dragon;

public class Client{
	

	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Dragon otherUsersDragons;
	private String serverIP;
		//IP of the person you are talking to 
	private Socket connection;
	
	
	public Client(String host){
		serverIP = host; 
	}

	//connect to server
	public void startRunning(TradingScreen s){
		try{
			connectToServer(s); 
			setUpStreams(s);
			//whileTrading(s);
		}catch(EOFException eofException){
			showMessage("\n Client terminated connection");
		}catch(IOException ioException){
			ioException.printStackTrace();
		}finally{
			closeCrap();
		}
		
	}
	
	//connect to server
	private void connectToServer(TradingScreen s) throws IOException{
		s.displayConnectionMessage("Attempting connection... \n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789); 
			//first parameter passes in the IP address
			//6789 is the port number
		s.displayConnectionMessage("Connected to:" + connection.getInetAddress().getHostName());
			//we get it from the connection because we are getting it from the server
	}
	
	//set up streams to send and receive messages
	private void setUpStreams(TradingScreen s) throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		s.displayConnectionMessage("\n Streams are now connected \n");
	}
	
	//while chatting with server
	private void whileTrading(TradingScreen s) throws IOException{
		//now connected, make sure the user is able to now type
		ableToType(true);
		do{
			try{
				//now what do you want to happen when you are chatting
				otherUsersDragons = (Dragon) input.readObject();
					//take whatever they are sending through their stream
					//treat it as a string and store it in the variable message
				s.displayConnectionMessage("\n" + otherUsersDragons);
			}catch(ClassNotFoundException classNotFoundException){
				s.displayConnectionMessage("\n I don't know that object type.");
			}
		}while(!otherUsersDragons.equals("SERVER - END"));
		//as long as the server person doesn't type end, you can 
		//continue to have a conversation
			//The reason why it's "SERVER - END" and not just "END"
			//is because sendMessage is programmed so that every time
			//you send a message, you send it with the name/username
			//of whoever is sending it
			//so the computer receives "username + message"
	}
	
	//close the streams and sockets
	private void closeCrap(){
		showMessage("\n closing stuff down");
		ableToType(false);
		try{
			output.close(); //closes output stream
			input.close();
			connection.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//send messages to server
	private void sendMessage(Dragon d, TradingScreen s){
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
	
	//updates the GUI (chatwindow)
	private void showMessage(final String m){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					//chatWindow.append(m);
				}
			}
		);
	}
	
	//figure out why is it final
	//gives user permision to type into the text box
	private void ableToType(final boolean tof){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					//userText.setEditable(tof);
				}
			}
		);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
