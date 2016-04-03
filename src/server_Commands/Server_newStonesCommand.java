package server_Commands;
import java.io.*;

import tetris_Client.Client_from_Server;


import java.util.*;

import tetriminos.*;


/**
 *	The class <i>Server_printScoreCommand</i> is the <i>command</i> class
 *	that the server uses to administrate and distribute the scores.
 *	@author RAMRAMI
 **/
public class Server_newStonesCommand implements Server_Command, Serializable
{
	
	/**
	 * @uml.property  name="stones"
	 */
	private ArrayList<Seven_Tetriminos_Stone> stones;

	/**
	 *	<i>Server_printScoreCommand()</i> constructor
	 *	
	 */
	
	private static final long serialVersionUID = 13L;
	
	public Server_newStonesCommand(ArrayList<Seven_Tetriminos_Stone> stones)
	{
		this.stones = stones;
		//System.out.println("Server_newStonesCommand stonesList.size() = "+stones.size());

	}

	/**
	 *	The <i>toString()</i> method can be called by the server or client
	 *	and always returns the same value
	 *	@return "Server_printScoreCommand"
	 */
	public String toString()
	{
		return "Server_newStonesCommand";
	}
	/**
	 *	The <i>execute(Client_from_Server c)</i> method is called by
	 *	the client and prints the udated scores onto the board
	 *	@param c	SEE:Client_from_Server
	 */
	public void execute(Client_from_Server c)
	{
		c.copyStones(stones);
	}



}

