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
	
	public void StartRunning(){
		try{
			server = new ServerSocket(6789, 100);
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
			connect();
		}catch(IOException e){
			ioException.printStackTrace();
		}
	}
	
	public void waitForConnection(TradingScreen s) throws IOException{
		s.displayConnectionMessage("Attempting connection... \n");
		connect();
		s.displayConnectionMessage("Connected to somebody");
	}
	
	public void connect(){
		while(connecition == null){
			connection = server.accept();
			if(connection == null){
				connection = new Socket(InetAddress.getByName(serverIP), 6789);
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
