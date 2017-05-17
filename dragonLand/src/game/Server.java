package game;
import java.io.*;
import game.DragonLand;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends DragonLand{
	
	private ObjectOutputStream output;
		//this is what is sent out of the system
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	//constructor
	public Server() {
	}
	
	//set up and run the server
	public void startRunning(){
		try{
			server = new ServerSocket(6789, 100); // first number for testing purposes
			while(true){
				try{
					waitForConnection();	
					setupStreams();
					whileTrading();
				}catch(EOFException eofException){
					showMessage("\n Server ended the connection!");
				}finally{
					closeCrap();
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}


	private void waitForConnection() throws IOException{
		showMessage("Waiting for someone to connect... \n");
		connection = server.accept();
		showMessage("Now connected to " + connection.getInetAddress().getHostName());
	}
	
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now setup! \n");
	}
	
	private void whileTrading() throws IOException{
		String message = " You are now connected! "; //simple prompt on screen
		sendMessage(message);
		ableToType(true); 
		do{
			try{
				message = (String) input.readObject(); 
				showMessage("\n" + message);
			}catch(ClassNotFoundException classNotFoundException){
				showMessage("\n hopefully this message never displays");
			}
		}while(!message.equals("CLIENT - END")); 
	}
	
	private void closeCrap(){
		showMessage("\n Closing connections \n"); 
		ableToType(false);
		try{
			output.close();
			input.close(); 
			connection.close(); 
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
		
	private void sendMessage(String message){
		try{
			output.writeObject("SERVER - " + message);
			output.flush(); 	
			showMessage("\nSERVER - " + message);
		}catch(IOException ioException){
			System.out.println("Weird stuff sent through stream");
		}
	}
		
	//only displays, does not send
	private void showMessage(final String text) {
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
	
	private void ableToType(final boolean tof){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
		
					}
				}
		);
	}
}
	
//server class is done
