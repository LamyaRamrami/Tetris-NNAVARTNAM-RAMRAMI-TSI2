package tetris_Client;


import java.io.*;
import java.net.*;
import java.util.*;

import client_Commands.Client_gameOverCommand;
import client_Commands.Client_getStoneCommand;
import client_Commands.Client_newScoreCommand;
import tetris_GUI.*;
import server_Commands.*;
import tetriminos.*;
/**
 *  This class handles the communication with the server and is watching over several conditions.
 * @author RAMRAMI
 */
public class Client_from_Server extends Thread 
{
	
	private String playerName;
	public Play_Ground_Panel tetrisPanel;
	private NextStoneLabel nextStoneShowLabel;
	public ClientSide_GUI gui;
	private Socket so;
	public ObjectOutputStream objectToServer;
	private ObjectInputStream objectFromServer;


	private int numOfLines;
	private int lineTest;
	public static boolean kill = false;
	private int score = 0;
	public boolean paintStone = false;

	//Stone list
	
	private ArrayList<Seven_Tetriminos_Stone> stoneList = new ArrayList<Seven_Tetriminos_Stone>();

	public Seven_Tetriminos_Stone nextStone;
	public Seven_Tetriminos_Stone actualStone;

	public Client_from_Server(String serverHost, String playerName, Play_Ground_Panel tetrisPanel, NextStoneLabel nextStoneShowLabel, ClientSide_GUI gui)
	{

		this.playerName = playerName;
		this.tetrisPanel = tetrisPanel;
		this.nextStoneShowLabel = nextStoneShowLabel;
		this.gui = gui;


		try
		{

			so = new Socket(serverHost, 16322);
			objectToServer = new ObjectOutputStream(so.getOutputStream());
			objectFromServer = new ObjectInputStream(so.getInputStream());

		}
		catch (Exception e)
		{
			System.out.println("Client_from_Server connection exception");
		}
	}

	/**
	 * The run()-method is started when this thread is called on, it has the sole purpose to start the go()-method.
	 */
	public void run()
	{
		try
		{
			go();
		}
		catch(Exception e){};
	}

	/**
	 *  This method contains the most important tasks of the class.
	 *  Such as sending the player-name to the server,
	 *  managing the correct handling of the incoming stone-objects,
	 *  removing full lines and requesting the server to set new scores,
	 *  checking if this player is game over,
	 *  @exception IOException is thrown on errors in network communication
	 */

	public void go() throws IOException
	{
		//String serverOutput;
		Object serverObjectOutput;



		try
		{
	
			objectToServer.writeObject(playerName);
			objectToServer.flush();

			while(!kill)
			{
	
				serverObjectOutput = objectFromServer.readObject();

	
				Server_Command commandObject = (Server_Command)serverObjectOutput;
				commandObject.execute(this);


	
				if(stoneList.size() > 2 && paintStone)
				{	
	
					if (nextStone==null && actualStone==null)
					{
						actualStone = fillStone();
						actualStone.tetrisPanel = tetrisPanel;
						tetrisPanel.stone = actualStone;

						nextStone = fillStone();
						nextStoneShowLabel.stone = nextStone;
					}
	
					else if(actualStone==null)
					{
						actualStone = nextStone;
						actualStone.tetrisPanel = tetrisPanel;
						tetrisPanel.stone = actualStone;

						nextStone = fillStone();
						nextStoneShowLabel.stone = nextStone;

					}
	
					else if(nextStone==null)
					{
						nextStone = fillStone();
						nextStoneShowLabel.stone = nextStone;
					}

	
					nextStoneShowLabel.repaint();
					tetrisPanel.repaint();	
				}

				if(stoneList.size() < 3)
				{
					sendGetStoneCommand();
				}

				lineChecker();

				gameOverCheck();
			}
		}
		catch(Exception e)
		{
	
			System.out.print("Client_from_Server Exception >>> Client disconnected!! <<<\n");
		}

		try
		{
			objectToServer.writeObject(new Client_gameOverCommand());
	
			objectToServer.writeObject(new Client_newScoreCommand(score));

	
			while(!false)
			{
				serverObjectOutput = objectFromServer.readObject();
				if (serverObjectOutput instanceof Server_Command)
				{
					Server_Command commandObject = (Server_Command)serverObjectOutput;
					commandObject.execute(this);
				}
			}
		}
		catch(Exception e)
		{
			System.out.print(">>> Client disconnected!! <<<\n");
		}
	}

	public void lineChecker()
	{
		numOfLines = 0;
	
		lineTest = 1;

	
		for(int z = 3; z < 23; z++)
		{
	
			for(int s = 2; s < 12; s++)
			{

				lineTest = lineTest * tetrisPanel.getTetMatAt(s,z).col;
			}
	
			if(lineTest != 0)
			{
	
				numOfLines++;

	
				for(int zi = z; zi > 2; zi--)
				{
	
					for(int si = 2; si < 12; si++)
					{
						tetrisPanel.setTetMatAt(si, zi, tetrisPanel.getTetMatAt(si,zi-1));
	
					}
				}
	
				lineTest = 1;
	
				score = score + (numOfLines * numOfLines * 10);
	

				try {
					objectToServer.writeObject(new Client_newScoreCommand(score));
					objectToServer.flush();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
			else
			{

				lineTest = 1;
			}
		}
	}

	public void gameOverCheck()
	{
		for(int i = 2; i <  12; i++)
		{
			if(tetrisPanel.getTetMatAt(i,2).col!=0)
			{
				kill=true;
				tetrisPanel.gameOver=true;
			}
		}
	}

	public void copyStones(ArrayList<Seven_Tetriminos_Stone> stones)
	{
		stoneList.addAll(stones);

	}

	public Seven_Tetriminos_Stone fillStone()
	{
		Seven_Tetriminos_Stone stone = null;

		if(stoneList.size() > 0){
			stone = stoneList.get(0);
			stoneList.remove(0);

			return stone;
		}
		else
		{

			return null;
		}
	}

	public void sendGetStoneCommand()
	{
		try 
		{
			objectToServer.writeObject(new Client_getStoneCommand());
			objectToServer.flush();
		} catch (IOException e) 
		{
		}
	}
	
	public void setTimeLeft(String time)
	{
		gui.setTimeL(time);
	}
	
	public void setTimeLabel(String txt)
	{
		gui.setTimeLabel(txt);
	}
	
	public void setPartPlayer(String player)
	{
		gui.setPlayerL(player);
	}
}
