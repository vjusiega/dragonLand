package game.tradeDragons;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import game.mainScreenTeam.Dragon;

public class TempServerClass {
	
	//private JTextField userText;
	
	private ArrayList<Dragon> dragons;
		//this is the dragons that person has
	//private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	public TempServerClass() {
//		super("IM"); 
//		userText = new JTextField();
//		userText.setEditable(false);
//		userText.addActionListener(
//				new ActionListener(){
//					public void actionPerformed(ActionEvent event){
//						sendMessage(event.getActionCommand());
//						userText.setText("");
//					}
//				}
//			);
//			add(userText, BorderLayout.NORTH);
//			
//			chatWindow = new JTextArea();
//			add(new JScrollPane(chatWindow));
			//setSize(300, 150);
//			setVisible(true);
	}
	
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
		showMessage("Waiting for someone to trade with... \n");
		connection = server.accept();
		showMessage("Now trading with " + connection.getInetAddress().getHostName());
		
	}
	
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now setup! \n");
	}
	
	private void whileTrading() throws IOException{
		String message = " You can now trade! "; 
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
			chatWindow.append("\n I can't send that message, hopefully you never see this error"); //append means stick this in the chat message
		}
	}
		
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						chatWindow.append(text); 
					}
				}
		);
	}
	
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