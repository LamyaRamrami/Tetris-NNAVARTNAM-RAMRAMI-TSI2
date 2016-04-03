package server_Commands;
import java.io.*;

import tetriminos.*;
import tetris_Client.Client_from_Server;



/**
 *	The class <i>Server_fallCommand</i> is the <i>command</i> class
 *	that the server uses to control the game pace; for each
 *	<i>Server_fallCommand</i> received the client iterates its game logic by one step.
 *	@author RAMRAMI
 **/
public class Server_fallCommand implements Server_Command, Serializable
{
	/**
	 * timerTime int value with Server time
	 * @uml.property  name="timerTime"
	 */
	private int timerTime;
	/**
	 * mat int[][] Matrix used to depict stones
	 * @uml.property  name="mat" multiplicity="(0 -1)" dimension="2"
	 */
	private All_Tetriminos_ColorBlock[][]mat;
	/**
	 *	Empty <i>Server_fallCommand()</i> constructor
	 */
	private static final long serialVersionUID = 13L;
	
	public Server_fallCommand()
	{

	}
	/**
	 *	<i>Server_fallCommand(int countOfTicks)</i> constructor with timerTime field
	 *	@param countOfTicks	int
	 */
	public Server_fallCommand(int countOfTicks)
	{
		timerTime = countOfTicks;
	}
	/**
	 *	The <i>toString()</i> method can be called by the server or client
	 *	and always returns the same value
	 *	@return "Server_fallCommand"
	 */
	public String toString()
	{
		return "Server_fallCommand";
	}
	/**
	 *
	 *
	 *	@param c	SEE:Client_from_Server
	 */
	public void execute(Client_from_Server c)
	{
		if(timerTime > 599)
		{
			if(timerTime%2==0)
			{
				if(timerTime == 600)
				{
					c.setTimeLabel("<html><b>time left</b></html>");
					c.paintStone = true;
				}
				else
				{
					c.setTimeLabel("<html><b><blink>start-count-down</blink></b></html>");
					String timeString = "" + ((600 - timerTime)/2);
					c.setTimeLeft(timeString);
				}

			}
			else
			{
				if(timerTime==601)
					c.setTimeLeft("GOOOOO!!!!");
				else
					c.setTimeLeft("GET READY!");
			}
		}
		else
		{
			String timeString = "" + timerTime;
			c.setTimeLeft(timeString);
		}
		

		if(!Client_from_Server.kill && c.actualStone != null && c.tetrisPanel.stone != null && timerTime < 600)
		{
			c.tetrisPanel.stone.y++;
			if(c.tetrisPanel.stone.checkCollision())
			{
				c.tetrisPanel.stone.y--;
				mat = c.tetrisPanel.stone.getMat();
				for(int i = 0; i<4; i++)
				{
					for(int j =0; j<4; j++)
					{

						if(mat[j][i].col != 0)
						{
							c.tetrisPanel.setTetMatAt(c.tetrisPanel.stone.x + i, c.tetrisPanel.stone.y + j, mat[j][i]);
							//c.tetrisPanel.setTetMatAt(c.tetrisPanel.stone.x + i, c.tetrisPanel.stone.y + j, mat[i][j]);
							//c.tetrisPanel.tetMat[c.tetrisPanel.stone.x + i][c.tetrisPanel.stone.y + j] = mat[j][i];
						}


					}
				}
				try
				{
					c.actualStone = null;
					c.tetrisPanel.stone = null;

				}
				catch(Exception e)
				{
					System.out.println("Error in fallCommand");
				}

			}
			c.tetrisPanel.repaint();
		}
	}

}
