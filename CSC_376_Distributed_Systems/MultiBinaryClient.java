/*
 * Student: Luke Fischer
 * Assignment #4
 * MultiBinaryClient.java
 * 
 */
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class MultiBinaryClient implements Runnable{
	
	static ArrayList<String> files = new ArrayList<String>();
	static ArrayList<Thread> threads = new ArrayList<Thread>();
	static String server = "";
	static int port = 0;
	String fileName = "";

	public static void main(String[] args) throws IOException {
		//check usage
		if(args.length < 2){
			System.out.println( "Usage:" );
			System.out.println( "   MultiBinaryClient <server> <port> ( < <filename> )" );
			return;
		}
		server = args[0];
		port = Integer.parseInt(args[1]);
		
		/*
		 Check to see if file names are kept in a .txt
		 	if so parse through .txt to get filenames
		 	else get filenames by standard input
		 */
		BufferedReader reader= new BufferedReader( new InputStreamReader(System.in));
		String stdInput = "";
		System.out.print( "Please enter the names of the files you would like to import\n(return ctrl z, ^Z, to end the loop)\n" );
		stdInput = reader.readLine();
		while(stdInput != null){
			files.add(stdInput);
			stdInput = reader.readLine();
		}
		reader.close();
		//check to see if any files were added
		if(files.isEmpty()){
			System.out.println("No files were requested for transfer");
			return;
		}
		//setup and run threads
		for(int i = files.size(); i > 0; i--){
			threads.add(new Thread(new MultiBinaryClient(server, port, files.get(i - 1))));
		}
		for(Thread thread : threads){
			thread.start();
		}
	}
	public MultiBinaryClient(String server, int port, String name){
		this.fileName = name;
	}
	@Override
	public void run() {
		//attempt to connect to the server and receive the file
		try {
			System.out.println("Thread "+fileName+" is Running!");
			Socket connection = new Socket(InetAddress.getByName(server), port);
			DataOutputStream output= new DataOutputStream( connection.getOutputStream() );
			DataInputStream input= new DataInputStream( connection.getInputStream() );	

			String fileNameB = fileName;
			output.writeUTF(fileNameB);
			long fileSize = input.readLong();
			
			//checking if file was located
			if(fileSize == 0){
				System.out.println("File could not be found!");
			}
			else{
				System.out.println("File contains "+ fileSize +" Bytes");
				FileOutputStream outFile = new FileOutputStream(fileName);
				byte byteLine = input.readByte();
				fileSize -=1;
				while (fileSize > 0){
					outFile.write(byteLine);
					byteLine = input.readByte();
					fileSize--;
				}
				System.out.println("File "+fileName+" has been transferred");
				outFile.close();
			}
			
			input.close();
			output.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

}
