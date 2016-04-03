package client_Commands;
import java.io.*;

import tetris_Server.*;
import tetris_Client.*;

/**
 *	La classe <i>Client_gameOverCommand</i> est une classe de <i>client_Commands</i>
 *
 *	@author RAMRAMI
 *
 *	Les dépendances de cette classe sont
 *
 *	<i>import java.io.*</i>
 *	<i>import serverClient.*</i>
 *	<i>import java.net.*</i>
 *	<i>import java.util.*</i>
 *	<i>import tetriminos.*</i>
 *  <i>tetris_Server.*</i>
 *  <i>tetris_Client.*</i>
 **/
public class Client_gameOverCommand implements Client_Command, Serializable
{
	/**
	 * <i>gameEnd()</i> variable is set to true and sent to the server, to request to be dismissed
	 * 
	 * La variable <i>gameEnd()</i> est initialisée à true et envoyé au server, pour annuller la requête
	 */
	private boolean gameEnd = true;
	
	private static final long serialVersionUID = 13L;

	/**
	 *	The empty <i>Client_gameOverCommand()</i> constructor
	 */
	public Client_gameOverCommand()
	{

	}

	/**
	 *	The <i>toString()</i> method can be called by the server or client
	 *	and always returns the same value
	 *	@return "C_gameOverCommand"
	 */
	public String toString()
	{
		return "C_gameOverCommand";
	}

	/**
	 *
	 *	@param s	SEE:S_fromClient
	 */
	public void execute(Tetris_Client s)
	{
		s.setGameOver();

		for(int j=0; j<s.commonThings.numberOfClients; j++)
		{
			if(Server_Administration.gameStates[j].compareTo("") == 0)
			{
				gameEnd = false;
				break;
			}
		}

		if(gameEnd)
		{
			s.commonThings.ticker.setI(0);
		}
	}
}

