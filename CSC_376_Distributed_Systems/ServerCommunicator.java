/**
 * ServerCommunicator.java
 * Performs the communication for the EchoServer
 * 
 * @author Karen Heart
 * 
 * Last modified: 4-1-15
 */

/*
 * Modified by Luke Fischer
 * 
 * Last modified: 9-23-15
*/

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServerCommunicator {
	int port_number;
	ServerSocket server_socket;
	Socket client_socket;

	public ServerCommunicator( int port ) throws IOException {
		port_number= port;
		server_socket= new ServerSocket( port_number );
		System.out.println( "Listening on port " + Integer.toString( port_number ) );
	}
	
	public String listen() throws IOException
	{
		// listen for a connection
		client_socket= server_socket.accept();
		
		// grab the input stream
		BufferedReader reader= new BufferedReader( 
				 new InputStreamReader(client_socket.getInputStream()) );
		
		
		// read the input
		String input_line= reader.readLine();
		System.out.println( "Received from client: " );
		System.out.println( input_line );
		
		return input_line;
		
		
	}
	
	void communicate(String message) throws IOException
	{
		// listen for a connection
		client_socket= server_socket.accept();
		//get output stream
		PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
		// send the message back to the client
		output.println( message );
	}
	
	void close()
	{
		try
		{
			client_socket.close();
			System.out.println( "Listening concluded; shutting down..." );
			server_socket.close();
		}
		catch( Exception e )
		{
			// ignore
		}
	}
}
