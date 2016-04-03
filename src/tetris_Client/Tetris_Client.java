package tetris_Client;
import tetris_Server.*;

import java.io.*;

import client_Commands.Client_Command;


/**
 * This thread on server-side is supervising the input stream of the server socket and processing the incoming tasks.
 * @author RAMRAMI
 * 
 */

public class Tetris_Client extends Thread
{
	private ObjectInputStream objectInput;
	private Server_to_Client fredi;
	public int idi;
	private String playerName;
	public Server_Administration commonThings;
	//public HighScore hs;

	//Ce thread est démarré par Server_to_Client avec ces références...
	public Tetris_Client(ObjectInputStream input, Server_to_Client fred,int id, Server_Administration commonThings)
	{
		idi = id;
		objectInput = input;
		fredi =fred;
		this.commonThings = commonThings;
	}

	/**
	 *  This method has the purpose to read and save the playernames,
	 *  read and execute commands, that the client sends,
	 *  and check if the game ended for all players when a player disconnects.
	 *  Then it kills itself and Thread that is responsible to send to the clients.
	 */

	public void run()
	{
		Object clientInput;

		try
		{

			playerName = (String)objectInput.readObject();

			Server_Administration.playerNames[idi - 1] = playerName;
			Server_Administration.tempPlayerNames[idi - 1] = playerName;


			if (idi == commonThings.numberOfClients){

				System.out.println("\nActual List of Players:");
				for (int n = 0; n<commonThings.numberOfClients; n++)
				{
					System.out.println(Server_Administration.playerNames[n]);
				}

				commonThings.newScore = 1;
			}


			boolean kill = false;
			while(!kill)
			{

				clientInput = objectInput.readObject();

				Client_Command com = (Client_Command)clientInput;
				com.execute(this);

			}
		}
		catch(Exception e)
		{
	
			Server_Administration.gameStates[idi-1] = "Disconnected";
	
			boolean gameEnd = true;
	
			for(int j=0; j<commonThings.numberOfClients; j++)
			{
	
				if(Server_Administration.gameStates[j].compareTo("") == 0)
				{
	
					gameEnd = false;
					break;
				}
			}
	
			if(gameEnd)
			{
	
				commonThings.ticker.setI(0);
				System.out.print(">>> Game End <<<\n");
			}


		}
	
		fredi.kill = true;
	}

	/**
	 *  This method is called on by a command object
	 *  and sets a flag in the sending Server_to_Client class in such a way, that only the stone-requesting player gets a new stone.
	 */
	public void setSendStone()
	{
	
		commonThings.setNewStonesFlag();
	}

	/**
	 *  This method is called on by a command object, refreshes the score of a player and sorts the player arrays by the new scores.
	 *  @param score is the new score that a player sends in a command object, every time he gets a line.
	 */
	public void setScore(int score)
	{
	
		Server_Administration.playerScores[idi-1] = score;

	
		//commonThings.sortScores();

		//sort and send the new scores
		commonThings.setNewScoreFlag();
	
		//System.out.println("New Score:");
		for(int i = 0; i<commonThings.numberOfClients; i++)
		{
			System.out.println(Server_Administration.tempPlayerNames[i] + ": " + Server_Administration.tempPlayerScores[i]);
		}
	}
	/**
	 *  This method is called on by a command object and sets a player game over.
	 */
	public void setGameOver()
	{
	
		Server_Administration.gameStates[idi-1] = "Game Over";

		//setScoreList();
	}

	public void setScoreList()
	{
	
		//String cScore = "" + Server_Administration.playerScores[idi-1];
		//String cName = Server_Administration.playerNames[idi-1];


		/*
	
		HighScore hs = new HighScore();
		hs.setHS(cName, cScore);
		 */
	}
}
