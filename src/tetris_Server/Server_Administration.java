package tetris_Server;

import java.net.*;
import java.util.*;

import tetriminos.*;

/**
 *  This class contains and administrates all of the fields and methods that all threads on server side have in common and need.
 *  @author Winkler
 */

public class Server_Administration
{
	/**
	 * @uml.property  name="numberOfClients"
	 */
	public int numberOfClients = 4;
	public static String[] playerNames;
	public static String[] tempPlayerNames;
	public static int[] playerScores;
	public static int[] tempPlayerScores;
	public static String[] gameStates;
	public static String[] tempGameStates;

	/**
	 * @uml.property  name="timer"
	 */
	public Timer timer;
	/**
	 * @uml.property  name="ticker"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	public Server_TimerTask_Sync ticker;
	//flag for new score and new stones sending
	/**
	 * @uml.property  name="newScore"
	 */
	public int newScore;
	/**
	 * @uml.property  name="newStones"
	 */
	public int newStones;
	
	/**
	 * @uml.property   name="stones"
	 * @uml.associationEnd   multiplicity="(0 -1)" elementType="tetriminos.L_Polyominos"
	 */
	public ArrayList<Seven_Tetriminos_Stone> stones;
	
	/**
	 * @uml.property  name="clientSockets" multiplicity="(0 -1)" dimension="1"
	 */
	public Socket[] clientSockets;
	/**
	 * @uml.property  name="rand"
	 */
	private Random rand;

	public Server_Administration(int numberOfClients)
	{
		//System.out.println("Server_Administration created"+this.toString());
		
		rand = new Random();
		this.numberOfClients = numberOfClients;
		playerNames = new String[numberOfClients];
		playerScores = new int[numberOfClients];
		tempPlayerNames = new String[numberOfClients];
		tempPlayerScores = new int[numberOfClients];
		gameStates = new String[numberOfClients];
		tempGameStates = new String[numberOfClients];
		//ein ticker object wird hier erzeugt
		ticker = new Server_TimerTask_Sync();
		timer = new Timer();
		//es wird durch den timer task jede sekunde(1000ms) aufgerufen

		newScore = 0;
		newStones = 0;
		
		clientSockets = new Socket[numberOfClients];
		for (int s=0; s<numberOfClients; s++)
		{
			gameStates[s]="";
			tempGameStates[s]="";
		}

	}

	/**
	 *  This method creates different stone-objects (type is by chance) and appends them to the stoneList.
	 */
	public 	void createStones()
	{
		stones = new ArrayList<Seven_Tetriminos_Stone>();
		int num;
		for(int i = 0; i < 5; i++)
		{	
			
			// die Variable num wird mit Zufallsintegern von 0 - 6 gefuellt, die Stellvertreter fuer die 7 Spielsteine sind
			num = rand.nextInt(7);
			// je nach Belegung (case) von num werden verschieden Spielsteinobjekte an die Liste angehaengt
			switch (num)
			{
				case 0: stones.add(new I_Straight_Polyomino());break; 
				case 1: stones.add(new J_Polyominos());break;
				case 2: stones.add(new L_Polyominos());break; 
				case 3: stones.add(new O_Square_Polyomino());break; 
				case 4: stones.add(new S_Skew_Polyominos());break;
				case 5: stones.add(new T_Polyomino());break;
				case 6: stones.add(new Z_Skew_Polyominos());break;
			}

			//System.out.println("Server_Administration createStones() i = "+i+" num = "+num+" size(): "+stones.size());
		}
		//System.out.println("Server_Administration sontes.size() = "+stones.size());
	}

	/**
	 *  This method sorts the names and game-states of the players using the scores and sends the new scores to all (still) connected players.
	 */
	public void sortScores()
	{
		//fuer alle Spieler werden temporaere arrays gefuellt...
		for(int i = 0; i < numberOfClients; i++)
		{
			tempPlayerNames[i] = playerNames[i];
			tempPlayerScores[i] = playerScores[i];
			tempGameStates[i] = gameStates[i];

		}

		//...die umsortiert werden (bubble sort)...
		boolean changeOccured = true;
		int el;

		while(changeOccured)
		{
			changeOccured = false;
			el = 0;
			while(el < numberOfClients-1)
			{
				if(tempPlayerScores[el] < tempPlayerScores[el+1])
				{
					int tempInt = tempPlayerScores[el];
					tempPlayerScores[el] = tempPlayerScores[el+1];
					tempPlayerScores[el+1] = tempInt;
					String tempStr = tempPlayerNames[el];
					tempPlayerNames[el] = tempPlayerNames[el+1];
					tempPlayerNames[el+1] = tempStr;
					String tempState = tempGameStates[el];
					tempGameStates[el] = tempGameStates[el+1];
					tempGameStates[el+1] = tempState;

					changeOccured = true;
				}
				el++;
			}
		}
		
	}
	
	public void setNewScoreFlag(){
		
		sortScores();
		
		//sollte etwa nicht gerade ein Score-Sende-Vorgang am Laufen sein (dann waere newScore != 0)...
		if(newScore == 0)
		{
			int a = 0;
			//...schicke dem ersten Player im playerNames-array, der noch connected ist den Punktestand
			while(a < numberOfClients)
			{
				if(gameStates[a].compareTo("Disconnected") == 0)
				{
					a++;
				}
				else
				{
					newScore = a+1;
					a = numberOfClients;
				} 
			}
		}
	}
	
	public void setNewStonesFlag()
	{
		createStones();
		//sollte etwa nicht gerade ein Stpne-Sende-Vorgang am Laufen sein (dann waere newStone != 0)...
		if(newStones == 0)
		{
			//System.out.println("setNewStonesFlag()");
			int a = 0;
			//...schicke dem ersten Player im playerNames-array, der noch connected ist den Punktestand
			while(a < numberOfClients)
			{
				if(gameStates[a].compareTo("Disconnected") == 0)
				{
					a++;
				}
				else
				{
					newStones = a+1;
					a = numberOfClients;
				} 
			}
		}
	}
}
