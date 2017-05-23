package game.dragonTrading;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Peer {

	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket connection;
	private String otherUserIP;
	private ServerSocket server;
	
	public Peer(String IP){
		otherUserIP = IP;
	}
	
	public void startRunning(TradingScreen s){
		try{
			server = new ServerSocket(6789, 100);
			System.out.println("here");
			while(true){
				try{
					waitForConnection(s);	
					setupStreams(s);
					whileTrading(s);
				}catch(EOFException eofException){
					s.displayConnectionMessage("\n Server ended the connection!");
				}finally{
					closeStuff();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void whileTrading(TradingScreen s) {
		// TODO Auto-generated method stub
		
	}

	public void waitForConnection(TradingScreen s) throws IOException{
		System.out.println("connect");
		s.displayConnectionMessage("Attempting connection... \n");
		connect();
		s.displayConnectionMessage("Connected to somebody");
	}
	
	public void connect() throws IOException{
		while(connection == null){
			connection = server.accept();
			if(connection == null){
				connection = new Socket(InetAddress.getByName(otherUserIP), 6789);
			}
		}
	}
	
	private void setupStreams(TradingScreen s) throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		s.displayConnectionMessage("\n Streams are now setup! \n");
	}
	
	public void closeStuff() {
		// TODO Auto-generated method stub
		
	}
}
