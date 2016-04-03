package client_Commands;
import java.io.*;

import tetris_Client.Tetris_Client;



/**
 *	The class <i>Client_getStoneCommand</i> is the <i>command</i> class
 *	that the client use to demand a new <i>stone</i> from the server.
 *	@author RAMRAMI
 **/
public class Client_getStoneCommand implements Client_Command, Serializable
{
	/**
	 *	The empty <i>Client_getStoneCommand()</i> constructor
	 */
	public Client_getStoneCommand()
	{

	}
	
	private static final long serialVersionUID = 13L;
	
	/**
	 *	The <i>toString()</i> method can be called by the server or client
	 *	and always returns the same value
	 *	@return "Client_getStoneCommand"
	 */
	public String toString()
	{
		return "Client_getStoneCommand";
	}

	/**
	 *	The <i>execute(S_fromClient s)</i> method is called by
	 *	the client and executes the instructions of the <i>command</i>
	 *	in the class
	 *	@param s	SEE: S_fromClient class description
	 */
	public void execute(Tetris_Client s)
	{
		s.setSendStone();
	}
}

