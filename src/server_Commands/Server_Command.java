package server_Commands;
import java.io.*;

import tetris_Client.Client_from_Server;



/**
 *	The class <i>CS_Command</i> is the basis of communication
 *	between server and client and the highest <i>command</i> class.
 *	@author RAMRAMI
 **/
public interface Server_Command extends Serializable
{
	

	/**
	 *	The <i>execute(Client_from_Server c)</i> method should implement
	 *	the client-sided execute instructions of the subclasses
	 *	@param c	SEE:Client_from_Server
	 */
	public void execute(Client_from_Server c);


	
	
}
