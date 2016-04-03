package client_Commands;
import java.io.*;

import tetris_Client.*;

/**
 *  La classe <i>Client_Command</i> impl�mente les communication de base entre le client et le serveur
 *  
 *  @author RAMRAMI
 *  
 *  Les d�pendances de cette classe sont
 *
 *	<i>import java.io.*</i>
 *  <i>tetris_Client.*</i>
 **/
public interface Client_Command extends Serializable
{

	/**
	 *  La m�thode <i>execute(Tetris_Client s)</i> impl�mente les instructions c�t� serveur et trait�es
	 *  dans le client
	 *   
	 *  @param Tetris_Client
	 */
	public void execute(Tetris_Client s);



}
