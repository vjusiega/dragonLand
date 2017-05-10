package serverPractice;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame{

	//this class is for the GUI for the client
	
	//we need different classes for client and server because these 
		//are two completely different programs
		//on different computers

	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
		//output is now from the client.
		//output isn't always from the server
		//its just whatever is flowing out from the computer using this program
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
		//IP of the person you are talking to 
	private Socket connection;
	
	public Client(String host){
		//server is public to everyone
		//this program sits on personal/private computer
			//not everyone should be able to access the clientside
		super("Client"); //this is the title of the box
		serverIP = host;
			//this protects the client
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					sendMessage(event.getActionCommand());
					userText.setText("");
				}
			}
		);
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		setSize(300, 150);
		setVisible(true);
	}

	//connect to server
	public void startRunning(){
		try{
			connectToServer(); //client is responsible for connecting to the server
			setUpStreams();
			whileChatting();
		}catch(EOFException eofException){
			showMessage("\n Client terminated connection");
			//this is okay to see because client ended connection
		}catch(IOException ioException){
			ioException.printStackTrace();
				//we don't want to see this. bad
		}finally{
			closeCrap();
		}
		
	}
	
	//connect to server
	private void connectToServer() throws IOException{
		showMessage("Attempting connection... \n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789); 
			//first parameter passes in the IP address
			//6789 is the port number
		showMessage("Connected to:" + connection.getInetAddress().getHostName());
			//we get it from the connection because we are getting it from the server
	}
	
	//set up streams to send and receive messages
	private void setUpStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now connected \n");
	}
	
	//while chatting with server
	private void whileChatting() throws IOException{
		//now connected, make sure the user is able to now type
		ableToType(true);
		do{
			try{
				//now what do you want to happen when you are chatting
				message = (String) input.readObject();
					//take whatever they are sending through their stream
					//treat it as a string and store it in the variable message
				showMessage("\n" + message);
			}catch(ClassNotFoundException classNotFoundException){
				showMessage("\n I don't know that object type.");
			}
		}while(!message.equals("SERVER - END"));
		//as long as the server person doesn't type end, you can 
		//continue to have a conversation
			//The reason why it's "SERVER - END" and not just "END"
			//is because sendMessage is programmed so that every time
			//you send a message, you send it with the name/username
			//of whoever is sending it
			//so the computer receives "username + message"
	}
}
