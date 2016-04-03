package client_Commands;
import java.io.*;

import tetris_Client.Tetris_Client;



/**
 *	The class <i>Client_newScoreCommand</i> is the <i>command</i> class
 *	that the client uses to transmit a new <i>score</i> to the server.
 *	@author RAMRAMI
 **/
public class Client_newScoreCommand implements Client_Command, Serializable
{

	/**
	 * score Client score value
	 * @uml.property  name="score"
	 */
	private int score;
	
	private static final long serialVersionUID = 13L;
	
	/**
	 *	<i>C_gameOverCommand()</i> constructor with score field
	 *	@param score	<i>int</i> value from client game
	 */
	public Client_newScoreCommand(int score)
	{

		this.score = score;
	}

	/**
	 *	The <i>toString()</i> method can be called by the server or client
	 *	and always returns the same value
	 *	@return "Client_newScoreCommand"
	 */
	public String toString()
	{
		return "Client_newScoreCommand";
	}

	/**
	 *	The <i>execute(S_fromClient s)</i> method is called by
	 *	the server and sets the new client score on the server.
	 *	@param s	SEE:S_fromClient
	 */
	public void execute(Tetris_Client s)
	{
		s.setScore(score);
	}

}

