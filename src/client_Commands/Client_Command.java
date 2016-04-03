package client_Commands;
import java.io.*;

import tetris_Client.*;

/**
 *  La classe <i>Client_Command</i> implémente les communication de base entre le client et le serveur
 *  
 *  @author RAMRAMI
 *  
 *  Les dépendances de cette classe sont
 *
 *	<i>import java.io.*</i>
 *  <i>tetris_Client.*</i>
 **/
public interface Client_Command extends Serializable
{

	/**
	 *  La méthode <i>execute(Tetris_Client s)</i> implémente les instructions côté serveur et traitées
	 *  dans le client
	 *   
	 *  @param Tetris_Client
	 */
	public void execute(Tetris_Client s);



}
