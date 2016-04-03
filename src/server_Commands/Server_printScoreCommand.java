package server_Commands;
import java.io.*;

import tetris_Client.Client_from_Server;




/**
 *	The class <i>Server_printScoreCommand</i> is the <i>command</i> class
 *	that the server uses to administrate and distribute the scores.
 *	@author RAMRAMI

 **/
public class Server_printScoreCommand implements Server_Command, Serializable
{

	/**
	 * @uml.property  name="tempPlayerNames" multiplicity="(0 -1)" dimension="1"
	 */
	private String[] tempPlayerNames;
	/**
	 * @uml.property  name="tempPlayerScores" multiplicity="(0 -1)" dimension="1"
	 */
	private int[] tempPlayerScores;
	/**
	 * @uml.property  name="tempGameStates" multiplicity="(0 -1)" dimension="1"
	 */
	private String[] tempGameStates;
	
	private static final long serialVersionUID = 13L;

	/**
	 *	<i>Server_printScoreCommand()</i> constructor
	 *	@param tempPlayerNames	array for sorting
	 *	@param tempPlayerScores	scores
	 *	@param tempGameStates
	 */
	public Server_printScoreCommand(String[] tempPlayerNames, int[] tempPlayerScores, String[] tempGameStates)
	{
		this.tempPlayerNames = new String[tempPlayerNames.length];
		this.tempPlayerScores = new int [tempPlayerScores.length];
		this.tempGameStates = new String[tempGameStates.length];
		for(int i = 0; i < tempPlayerScores.length; i++)
		{
			this.tempPlayerNames[i] = tempPlayerNames[i];
			this.tempPlayerScores[i] = tempPlayerScores[i];
			this.tempGameStates[i] = tempGameStates[i];

		}
	}

	/**
	 *	The <i>toString()</i> method can be called by the server or client
	 *	and always returns the same value
	 *	@return "Server_printScoreCommand"
	 */
	public String toString()
	{
		return "Server_printScoreCommand";
	}
	/**
	 *	The <i>execute(Client_from_Server c)</i> method is called by
	 *	the client and prints the udated scores onto the board
	 *	@param c	SEE:Client_from_Server
	 */
	public void execute(Client_from_Server c)
	{
		String output = "<html>";
		for(int i = 0; i<tempPlayerScores.length; i++)
		{
			output = output + tempPlayerNames[i] + ": " + tempPlayerScores[i]+ " " +tempGameStates[i] +"<br>";
		}
		output = output + "</html>";
		c.setPartPlayer(output);
	}

}

