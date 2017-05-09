package serverPractice;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame{
	//instant messaging program

	private JTextField userText;
		//user input
	private JTextArea chatWindow;
		//where the texts will be shown
	private ObjectOutputStream output;
		//this is what is sent out of the system
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	//constructor
	public Server() {
		super("IM"); // title 
		userText = new JTextField();
			//sets up the text field 
		userText.setEditable(false);
			//if you're not connected the user cannot enter anything to the box
			//bad stuff happens when you send stuff through a stream and there is no one on the other side
		userText.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						sendMessage(event.getActionCommand());
							//passes in the text that was written in the text field
						//all this method does is.. send the message
						userText.setText("");
							//once you send you want the message area to be clear
							//reset
					}
				}
			);
			add(userText, BorderLayout.NORTH);
			
			chatWindow = new JTextArea();
			add(new JScrollPane(chatWindow));
			setSize(300, 150);
				//usually want a bigger size
			setVisible(true);
				//see everything on the screen
	}
	
	//set up and run the server
	public void startRunning(){
		try{
			server = new ServerSocket(6789, 100); // first number for testing purposes
				//connecting to a specific application kind of like accesing an app on your phone
				//you want a port number, the first parameter, which the destination of the output stream boat
			
				//second parameter: backlog
					//how many people can wait to access the app
					//limiting the number of people allows you to keep your server from crashing
				//the port number you get to decide it and you must remember it
			
			while(true){
				try{
					waitForConnection();
						//wait for someone to connect with me
					setupStreams();
						//set up output and input stream
					whileChatting();
						//code that allows us to send messages back and forth
						
					//connect and have conversation
				}catch(EOFException eofException){
					//eofException means end of a stream or end of a connection
					
					showMessage("\n Server ended the connection!");
					//this isn't REALLy an, error because you want this to show once someone leaves the conversation
				}finally{
					closeCrap();
				}
			}
			
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	//wait for connection, then display connection info
	private void waitForConnection() throws IOException{
		showMessage("Waiting for someone to connect... \n");
		//to let the program that you set up successfully
		
		//set up socket
		connection = server.accept();
		//server accept says that they want to have a connection with you
		//connection is socket
		//when they accept, connection/socket is made
		
		//blocks a connection until it is made
		//only going to create socket ONE TIME
		
		showMessage("Now connected to " + connection.getInetAddress().getHostName());
		
		//waits for connection, after they do, socket is created, now it just tells you IP Address
	}
	
	//get stream to send and receive data
	private void setupStreams() throws IOException {
		//after connected, it sets up stream
		
		output = new ObjectOutputStream(connection.getOutputStream());
		//allows someone to output messages to someone else
		
		output.flush();
		//sometimes data gets left over when you try to send someone something, when data is left over, you want to "flush" the rest it of out and send it out
		
		input = new ObjectInputStream(connection.getInputStream());
		//you can't input.flush cause the other person can only flush your stuff
		
		showMessage("\n Streams are now setup! \n");
		//now you can start a conversation
	}
	
	//code that runs while the people are chatting
	private void whileChatting() throws IOException{
	
		String message = " You are now connected! "; //simple prompt on screen
		sendMessage(message);
		ableToType(true); //method to be created. The user is unable to type unless the person is connected to another person. This method will allow user to write in text box
		
		
		do{ //do something while something is true…. Not the same as a while loop
			try{
				message = (String) input.readObject(); 
					//viewing the object that comes into us through the stream and then put it into our message variable
				showMessage("\n" + message);
					//once you have your message, display it!
			}catch(ClassNotFoundException classNotFoundException){
				showMessage("\n hopefully this message never displays");
				//this would be sent if the user sent something really weird like a weird symbol
			}
		}while(!message.equals("CLIENT - END")); //if a user type END then the conversation ends
	}
	//difference between while and do while loop
		//while you test your condition and then do something
		//do while, you do something and then you test your condition at the end
		
	//close streams and sockets after you are done chatting
	private void closeCrap(){
		showMessage("\n Closing connections \n"); // these type of messages are also like loading messages 
		ableToType(false); //user cannot type while connections are being closed
		try{
			output.close(); //stream closed
			input.close(); //stream closed
			connection.close(); //closes the socket i.e. the main connection
				//if you don't close things you will waste memory on your server
			//this is all you have to do! 
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
		
	//send a message to client (computer that is connected to the server)
	private void sendMessage(String message){
		
		//whenever you want to send something you need a try catch
		//then you output.writeObject(obj); to send the object which in this case is a string
		
		try{
			output.writeObject("SERVER - " + message);
				//sends a message through the output stream 
				//we write server - here but this is just the username
			output.flush(); 	
				//good practice whenever you send something
			showMessage("\nSERVER - " + message);
				//we show the message because the user wants to see what he sent too
		}catch(IOException ioException){
			chatWindow.append("\n I can't send that message, hopefully you never see this error"); //append means stick this in the chat message
		}
	}
		
	//only displays, does not send
	//updates chatWindow
	//allows us to update only the chatwindow and not the entire GUI
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						chatWindow.append(text); //adds a message to the end of the document and then updates chatWindow
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
						userText.setEditable(tof);
					}
				}
		);
	}
}
	
//server class is done
