package tetris_Client;
import tetris_Server.*;

import java.io.*;
import java.net.*;

import server_Commands.*;

/**
 * This thread on server-side is supervising the output stream of the server socket and responsible for sending to the client.
 * @author RAMRAMI
 */

public class Server_to_Client extends Thread
{
	private Socket s;
	private int countOfTicks=620;
	private Tetris_Client input;
	public boolean kill = false;
	private int idi;

	private Server_Administration commonThings;

	public Server_to_Client(Socket cs, int id, Server_Administration commonThings)
	{
		idi = id;
		s=cs;
		this.commonThings = commonThings;
	}

	/**
	 *  This method is responsable for sending the Stone-Objects (and creating them if necessary),
	 *  the new Scores (to the connected Clients) and fall-Commands on every tick.
	 *  This also implies the game start (by sending the first two stones).
	 */

	public void run()
	{
		ObjectInputStream fromClient;
		ObjectOutputStream objectToClient;

		try
		{

			fromClient = new ObjectInputStream(s.getInputStream());


			input = new Tetris_Client(fromClient, this, idi, commonThings);

			input.start();

			objectToClient = new ObjectOutputStream( s.getOutputStream() );


			while(!kill)
			{	

				if (countOfTicks>commonThings.ticker.getI())
				{

					objectToClient.writeObject(new Server_fallCommand(countOfTicks));
					objectToClient.flush();

					countOfTicks=commonThings.ticker.getI();
				}


				if(commonThings.newStones == idi)
				{
					sendNewStonesCommand(objectToClient);
				}

				if(commonThings.newScore == idi)
				{
					sendPrintScoreCommand(objectToClient);
				}


				Thread.sleep(10);
			}

			s.close();
			System.out.println(Server_Administration.playerNames[idi-1] + " disconnected!");
		}
		catch( Exception e )
		{
			System.out.println("Server_to_Client execption!!!");
		}
	}



	public void sendPrintScoreCommand(ObjectOutputStream objectToClient)
	{
		try
		{

			objectToClient.reset();
			objectToClient.writeObject(new Server_printScoreCommand(Server_Administration.tempPlayerNames, Server_Administration.tempPlayerScores, Server_Administration.tempGameStates));
			objectToClient.flush();

			if (idi < commonThings.numberOfClients)
			{

				int a = idi;
				while(a < commonThings.numberOfClients)
				{
					if(Server_Administration.gameStates[a].compareTo("Disconnected") == 0)
					{
						a++;
					}
					else
					{

						commonThings.newScore = a + 1;

						a = commonThings.numberOfClients;
					}
				}
			}
			else
			{

				commonThings.newScore = 0;
			}

		}
		catch(Exception e)
		{

		}
	}

	public void sendNewStonesCommand(ObjectOutputStream objectToClient)
	{
		try
		{

			objectToClient.reset();
			objectToClient.writeObject(new Server_newStonesCommand(commonThings.stones));
			objectToClient.flush();

			if (idi < commonThings.numberOfClients)
			{

				int a = idi;
				while(a < commonThings.numberOfClients)
				{
					if(Server_Administration.gameStates[a].compareTo("Disconnected") == 0)
					{
						a++;
					}
					else
					{

						commonThings.newStones = a + 1;

						a = commonThings.numberOfClients;
					}
				}
			}
			else
			{
				commonThings.stones = null;
				commonThings.newStones = 0;
			}
		}
		catch(Exception e)
		{

		}
	}
}
