package tetriminos;
import java.io.*;
import java.awt.*;

/**
 * this is the basis class of all 7 different stone-classes (tetriminos) which are named like: I,S,Z,O,T,L,J. it contains several methodes which are all the same for all different stones, and defines several abstract methodes, which are differently implementet in the concrete stone-objects..
 *
 *@author Winkler
 */
public class All_Tetriminos_ColorBlock implements Serializable
{
	/**
	 * @uml.property  name="name"
	 */
	public String name;
	/**
	 * @uml.property  name="width"
	 */
	private final int width = 20;
	/**
	 * @uml.property  name="height"
	 */
	private final int height = 20;
	/**
	 * @uml.property  name="colour"
	 */
	public Color colour; 
	/**
	 * @uml.property  name="col"
	 */
	public int col;
	
	private static final long serialVersionUID = 13L;

	/**
	 * default constructor only sets the name-variable to "Stone"
	 *
	 * @return nothing
	 */
	public All_Tetriminos_ColorBlock(Color colour, String name, int col)
	{
		this.name = name;
		this.colour = colour;
		this.col = col;
	}

	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName()
	{
		return name;
	}

	public String toString()
	{
		return "name=" + name;
	}
	
	public void setColor(Color co, String cstr,  int c)
	{
		//System.out.println("setColor() "+c);
		colour = co;
		name = cstr;
		col = c;
	}

	public void paintBlock(Graphics voodoo, int i, int j)
	{
		//System.out.println("All_Tetriminos_ColorBlock paitBlock() "+name+col);
		voodoo.setColor(colour);
		if(col == 0 || col == 8)
			voodoo.fillRect(i*width, j*height, width, height);
		else
			voodoo.fill3DRect(i*width, j*height, width, height, true);
	}
	
	public void paintBlockNextStone(Graphics voodoo, int i, int j)
	{
		//System.out.println("All_Tetriminos_ColorBlock paitBlock() "+name+col);
		
		if(col == 0 || col == 8)
		{
			voodoo.setColor(Color.lightGray);
			voodoo.fillRect(i*width, j*height, width, height);
		}
		else
		{
			voodoo.setColor(colour);
			voodoo.fill3DRect(i*width, j*height, width, height, true);
		}
	}
	
}
