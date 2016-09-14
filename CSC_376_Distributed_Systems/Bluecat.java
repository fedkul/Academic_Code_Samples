/*
 * Student: Luke Fischer
 * Assignment #2
 * Bluecat.java
 * 
 */

import java.net.InetAddress;
import java.net.Socket;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;



public class Bluecat 
{
	private static boolean rCom = false;
	private static boolean fCom = false;
	private static boolean opCom = false;
	private static String outName = "";
	private static String inName = "";

	public static void main(String[] args) 
	{
		//usage check
		if ( args.length < 2 )
		{
			System.out.println( "Basic usage for client:" );
			System.out.println( "   java Bluecat [-fro] [option data] [hostname] [port number]" );
			System.out.println( "Basic usage for server:" );
			System.out.println( "   java Bluecat -l [port number] [-frp] [option data]" );
			return;
		}
		//collect and or check arguments
		argsCheck(args);

		//check for multiple read file commands 
		//(I am unsure whether we need multiple functionality at the moment)
		if (rCom == true && fCom == true)
		{
			System.out.println( "Only one file read command is allowed per command argument" );
			return;
		}

		//run client or server based off arguments
		if (args[0].equals("-l"))
		{
			server(opCom, Integer.valueOf(args[1]), outName, inName);
		}
		else
		{
			client(opCom, Integer.valueOf(args[args.length - 1]), outName, inName);
		}

	}

	private static void client(boolean toFile, int port, String outName, String inName)
	{
		try
		{
			//open client for communication
			ClientCommunicator client= new ClientCommunicator( port );

			//choose communication method and send/receive information
			if (rCom == true)
			{
				//string variable to hold response for writing if necessary
				String message = null;

				//write responses to file if requested
				if (opCom == true)
				{
					PrintWriter outFile= new PrintWriter(new BufferedWriter(new FileWriter(outName)));
					BufferedReader inFile= new BufferedReader(new FileReader(inName));
					String line= null;
					while( (line= inFile.readLine()) != null )
					{
						client.communicate(line);
						gatherInputC(message, client);
						outFile.println(message);
					}
				}
				else
				{
					BufferedReader inFile= new BufferedReader(new FileReader(inName));
					String line= null;
					while( (line= inFile.readLine()) != null )
					{
						client.communicate(line);
						gatherInputC(message, client);
					}
				}
			}
			else if (fCom == true)
			{
				//string variable to hold response for writing if necessary
				String message = null;

				//write responses to file if requested
				if (opCom == true)
				{
					PrintWriter outFile= new PrintWriter(new BufferedWriter(new FileWriter(outName)));
					BufferedReader inFile= new BufferedReader(new FileReader(inName));
					String line= null;
					while( (line= inFile.readLine()) != null )
					{
						client.communicate(line);
					}
					gatherInputC(message, client);
					outFile.println(message);
				}
				else
				{
					BufferedReader inFile= new BufferedReader(new FileReader(inName));
					String line= null;
					while( (line= inFile.readLine()) != null )
					{
						client.communicate(line);
					}
					gatherInputC(message, client);
				}
			}
			else
			{
				//string variable to hold response for writing if necessary
				String message = null;

				//write responses to file if requested
				if (opCom == true)
				{
					PrintWriter outFile= new PrintWriter(new BufferedWriter(new FileWriter(outName)));
					// input the message from standard input
					BufferedReader reader= new BufferedReader( new InputStreamReader(System.in) );
					System.out.print( "Enter message: " );
					String stdInput= reader.readLine();
					while((stdInput)!=null)
					{
						client.communicate(stdInput);
						gatherInputC(message, client);
						outFile.println(message);
						stdInput= reader.readLine();
					}
				}
				else
				{
					// input the message from standard input
					BufferedReader reader= new BufferedReader( new InputStreamReader(System.in) );
					System.out.print( "Enter message: " );
					String stdInput= reader.readLine();
					while((stdInput)!=null)
					{
						client.communicate(stdInput);
						gatherInputC(message, client);
						stdInput= reader.readLine();
					}
				}

			}
			//client.client_socket.shutdownOutput();
			client.close();
		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage() );
		}
	}
	private static void server(boolean toLog, int port, String outName, String inName) 
	{
		ServerCommunicator server= null;
		try
		{
			//start server and begin listening
			server= new ServerCommunicator(port);
			String initialM = server.listen();
			//add initial message to log file if requested
			if (opCom)
			{
				PrintWriter outFile= new PrintWriter(new BufferedWriter(new FileWriter(outName)));
				outFile.println("> " + initialM);
			}
			//choose communication method and send/receive information
			if (rCom == true)
			{
				//string variable to hold response for writing if necessary
				String message = null;

				//write responses to file if requested
				if (opCom == true)
				{
					PrintWriter outFile= new PrintWriter(new BufferedWriter(new FileWriter(outName)));
					BufferedReader inFile= new BufferedReader(new FileReader(inName));
					String line= null;
					while( (line= inFile.readLine()) != null )
					{
						server.communicate(line);
						outFile.println("> " + line);
						gatherInputS(message, server);
						outFile.println(message);
					}
				}
				else
				{
					BufferedReader inFile= new BufferedReader(new FileReader(inName));
					String line= null;
					while( (line= inFile.readLine()) != null )
					{
						server.communicate(inFile.readLine());
						gatherInputS(message, server);
					}
				}
			}
			else if (fCom == true)
			{
				//string variable to hold response for writing if necessary
				String message = null;

				//write responses to file if requested
				if (opCom == true)
				{
					PrintWriter outFile= new PrintWriter(new BufferedWriter(new FileWriter(outName)));
					BufferedReader inFile= new BufferedReader(new FileReader(inName));
					String line= null;
					while( (line= inFile.readLine()) != null )
					{
						server.communicate(inFile.readLine());
					}
					gatherInputS(message, server);
					outFile.println(message);
				}
				else
				{
					BufferedReader inFile= new BufferedReader(new FileReader(inName));
					String line= null;
					while( (line= inFile.readLine()) != null )
					{
						server.communicate(inFile.readLine());
					}
					gatherInputS(message, server);
				}
			}
			else
			{
				//string variable to hold response for writing if necessary
				String message = null;

				//write responses to file if requested
				if (opCom == true)
				{
					PrintWriter outFile= new PrintWriter(new BufferedWriter(new FileWriter(outName)));
					// input the message from standard input
					BufferedReader reader= new BufferedReader( new InputStreamReader(System.in) );
					System.out.print( "Enter message: " );
					String stdInput= reader.readLine();
					while((stdInput)!=null)
					{
						server.communicate(message);
						gatherInputS(message, server);
						outFile.println(message);
						stdInput= reader.readLine();
					}
				}
				else
				{
					// input the message from standard input
					BufferedReader reader= new BufferedReader( new InputStreamReader(System.in) );
					System.out.print( "Enter message: " );
					String stdInput= reader.readLine();
					while((stdInput)!=null)
					{
						server.communicate(message);
						gatherInputS(message, server);
						stdInput= reader.readLine();
					}
				}

			}


		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage() );
		}
		server.close();
	}

	private static void argsCheck(String[] args)
	{
		for (int i = 0; i<args.length; i++)
		{
			if (args[i].equals("-f")) 
			{
				fCom = true;
				inName = args[i+1];
			}
			if (args[i].equals("-r"))
			{
				rCom = true;
				inName = args[i+1];
			}
			if (args[i].equals("-o") || args[i].equals("-p")) 
			{
				opCom = true;
				outName = args[i+1];
			}
		}
	}
	private static void gatherInputC(String message, ClientCommunicator client) throws IOException
	{

		String response = client.listen();
		while (response != null)
		{
			message+=response;
		}
	}
	private static void gatherInputS(String message, ServerCommunicator server) throws IOException
	{
		//grab first line then iterate through the input in case it is -f
		message = server.listen();
		while (server.listen() != null)
		{
			//do nothing
		}
	}
}
