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
	private String otherUsersDragons;
	private String serverIP;
		//IP of the person you are talking to 
	private Socket connection;
	
	
	public Client(String host){
		serverIP = host; 
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
	
	//connect to server
	public void startRunning(TradingScreen s){
		try{
			connectToServer(s); 
			setUpStreams(s);
			startTrading(s);
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
		//ableToType(true);
		do{
			try{
				System.out.println("waiting for dragon -client");
				otherUsersDragons = (String) input.readObject();
				System.out.println("recieved dragon -client");
				s.displayConnectionMessage("\n" + otherUsersDragons);
			}catch(ClassNotFoundException classNotFoundException){
				s.displayConnectionMessage("\n I don't know that object type.");
			}
		}while(!otherUsersDragons.equals("SERVER - END"));
	}
	
	//close the streams and sockets
	private void closeCrap(){
		showMessage("\n closing stuff down");
		//ableToType(false);
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
