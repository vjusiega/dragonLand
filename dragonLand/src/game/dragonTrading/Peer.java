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
			connect();
		}catch(IOException e){
			System.out.println("The connection ended");
		}finally{
			closeStuff();
		}
	}
	
	public void connect(){
		
	}

	public void closeStuff() {
		// TODO Auto-generated method stub
		
	}
}
