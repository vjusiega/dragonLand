package game;
import java.io.*;
import game.DragonLand;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends DragonLand{
	//instant messaging program

//	private JTextField userText;
//		//user input
//	private JTextArea chatWindow;
//		//where the texts will be shown
	
	private ObjectOutputStream output;
		//this is what is sent out of the system
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	//constructor
	public Server() {
//		super("IM"); // title 
//		userText = new JTextField();
//			//sets up the text field 
//		userText.setEditable(false);
//			//if you're not connected the user cannot enter anything to the box
//			//bad stuff happens when you send stuff through a stream and there is no one on the other side
//		userText.addActionListener(
//				new ActionListener(){
//					public void actionPerformed(ActionEvent event){
//						sendMessage(event.getActionCommand());
//							//passes in the text that was written in the text field
//						//all this method does is.. send the message
//						userText.setText("");
//							//once you send you want the message area to be clear
//							//reset
//					}
//				}
//			);
//			add(userText, BorderLayout.NORTH);
//			
//			chatWindow = new JTextArea();
//			add(new JScrollPane(chatWindow));
//			setSize(300, 150);
//				//usually want a bigger size
//			setVisible(true);
//				//see everything on the screen
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
				//sends a message through the output stream 
				//we write server - here but this is just the username
			output.flush(); 	
				//good practice whenever you send something
			showMessage("\nSERVER - " + message);
				//we show the message because the user wants to see what he sent too
		}catch(IOException ioException){
//			chatWindow.append("\n I can't send that message, hopefully you never see this error"); //append means stick this in the chat message
		}
	}
		
	//only displays, does not send
	//updates chatWindow
	//allows us to update only the chatwindow and not the entire GUI
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
	
	//by default, setEditable is set to false because user 
		//should not be allowed to type when they are not 
		//connected to a stream
	//let the user type stuff into their box
	private void ableToType(final boolean tof){
		//tof = true or false
		//updating a portion of the GUI
		//able to type stuff in or not be able to stuff in
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						//means whenever parameter is true you can type stuff but when it is false, the area is basically greyed out so that you can't type stuff
//						userText.setEditable(tof);
					}
				}
		);
	}
}
	
//server class is done
