package tetris_GUI;

import java.awt.*;
import javax.swing.*;

import tetriminos.*;

/**
 * The NextStoneLabel is the "window" where you can see the next stone. The paintComponent method is called
 * up when a new stone "arrives"...
 * @author RAMRAMI
 */
public class NextStoneLabel extends JLabel
{
	/**
	 * @uml.property  name="stone"
	 * @uml.associationEnd  
	 */
	public Seven_Tetriminos_Stone stone;

	private static final long serialVersionUID = 13L;

	public NextStoneLabel()
	{
		this.setPreferredSize(new Dimension(100, 80));
		this.setOpaque(true);
		this.setBackground(Color.lightGray);
	}
	/**
	 * The paintComponent method is called up by repaint(). It fills the different fields of the stone matrix
	 * with the different colors of every stone. At the end the whole is repainted.
	 *
	 * @param Graphics voodoo is a default parameter which the paintComponent method needs to have!
	 */
	public void paintComponent(Graphics voodoo)
	{
		
		if (stone != null)
		{
		
			All_Tetriminos_ColorBlock[][] mat = stone.getMat();
			this.setBackground(Color.lightGray);
		
			for(int i = 0; i < 4; i++)
			{
				for(int j = 0; j < 4; j++)
				{
					mat[i][j].paintBlockNextStone(voodoo, j, i);
			
				}
			}
		}
	}
}





