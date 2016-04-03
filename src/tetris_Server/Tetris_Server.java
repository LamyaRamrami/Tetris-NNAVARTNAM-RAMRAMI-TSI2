package tetris_Server;

import tetris_Client.*;

import java.io.*;
import java.net.*;

/**
 * This class builds a server, that is waiting to receive connections by clients. If a user-defiened number of Clients has connected it starts a new Thread of the Class Server_to_Client and ends.
 * @author RAMRAMI
 */
public class Tetris_Server extends Thread
{
	private Server_Administration commonThings;
	
	public Tetris_Server(Server_Administration commonThings)
	{
	
		this.commonThings = commonThings;
	}

	/**
	 * The run()-method is started automatically when a Thread is called on, this run() method continues to call on the go()-method.
	 */
	public void run()
	{
		try
		{
			go();
		}
		catch(Exception e){
			System.out.println("S_connectClient run() exception:");
			System.out.println(e);
		}
	}

	/**
	 * The go()-method builds a server with the portnumber 8181 on the
	 * localhost which accepts a certain number of clients (input Server.java)
	 * @exception IOException on wrong input
	 */
	
	public void go() throws IOException
	{
	
		ServerSocket serverso = new ServerSocket(16322);
	
		int id = 1;
	
		System.out.println("server is running...(max. numberOfClients = "+commonThings.numberOfClients+")");
	
		
		while( id <= commonThings.numberOfClients )
		{
	
			Socket clientso = serverso.accept();
	
			Server_to_Client sFred = new Server_to_Client(clientso, id, commonThings);
	
			sFred.start();
	
			commonThings.clientSockets[id-1] = clientso;
	
			System.out.println("\n"+"Client"+id+" connected!");
	
			id++;
		}
	
		commonThings.timer.schedule( commonThings.ticker, 0, 500 );
	
	
	}
}
