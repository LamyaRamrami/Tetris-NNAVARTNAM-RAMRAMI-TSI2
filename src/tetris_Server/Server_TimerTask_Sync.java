package tetris_Server;
import java.util.*;


/**
 *  This class is an TimerTask that could be described as a "synchronizing game clock". It is just counting down an integer value, from which several actions in other classes depend.
 *  @author RAMRAMI
 */

public class Server_TimerTask_Sync extends TimerTask
{
	
	private int i;

	Server_TimerTask_Sync()
	{
		i = 610;
	}

        /**
         *  This method counts down an integer value, until it is (set) 0.
         */
	public void run()
	{
		if(i >= 0)
		{
			i--;
			System.out.print(" " + i);
		}
	}
	
	
	public int getI()
	{
		return i;
	}
	
		public void setI(int ii)
	{
		i = ii;
	}
}
