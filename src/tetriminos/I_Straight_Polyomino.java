package tetriminos;
import java.io.*;
import java.awt.*;

/**
 * this class represents the stone looking like a "I"
 *
 * @author RAMRAMI
 */
public class  I_Straight_Polyomino extends Seven_Tetriminos_Stone implements Serializable
{

	private int colint;
	private Color colour;
	private String colstr;
	private static final long serialVersionUID = 13L;

	public I_Straight_Polyomino()
	{
		name = "I";
		colint = 1;
		colour = Color.blue;
		colstr = "blue";
		mat = new All_Tetriminos_ColorBlock[][]
		       {{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(colour, colstr, colint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint)},
				{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(colour, colstr, colint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint)},
				{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(colour, colstr, colint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint)},
				{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(colour, colstr, colint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint)}};
	}

	/**
	 * returns a String with the name of the stone (S,Z,L,J,O,T,I)
	 *
	 * @return name=nameOfStone
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * returns a String like "name=nameOfStone"
	 *
	 * @return name=nameOfStone
	 */
	public String toString()
	{
		return "name=" + name;
	}
	/**
	 * returns the matrix representing the stone in the actual state
	 *
	 * @return mat matrix with entrances where colored rectangles should be painted
	 */
	public All_Tetriminos_ColorBlock[][] getMat()
	{
		return mat;
	}

	public void rotate()
	{
		if (state == 0)
		{
			mat[0][1].setColor(whito, wstr, wint);
			mat[1][0].setColor(colour, colstr, colint);
			mat[1][2].setColor(colour, colstr, colint);
			mat[1][3].setColor(colour, colstr, colint);
			mat[2][1].setColor(whito, wstr, wint);
			mat[3][1].setColor(whito, wstr, wint);

			state = 1;

			if(checkCollision())
			{
				mat[0][1].setColor(colour, colstr, colint);
				mat[1][0].setColor(whito, wstr, wint);
				mat[1][2].setColor(whito, wstr, wint);
				mat[1][3].setColor(whito, wstr, wint);
				mat[2][1].setColor(colour, colstr, colint);
				mat[3][1].setColor(colour, colstr, colint);

				state = 0;
			}
		}
		else
		{
			mat[0][1].setColor(colour, colstr, colint);
			mat[1][0].setColor(whito, wstr, wint);
			mat[1][2].setColor(whito, wstr, wint);
			mat[1][3].setColor(whito, wstr, wint);
			mat[2][1].setColor(colour, colstr, colint);
			mat[3][1].setColor(colour, colstr, colint);

			state = 0;

			if(checkCollision())
			{
				mat[0][1].setColor(whito, wstr, wint);
				mat[1][0].setColor(colour, colstr, colint);
				mat[1][2].setColor(colour, colstr, colint);
				mat[1][3].setColor(colour, colstr, colint);
				mat[2][1].setColor(whito, wstr, wint);
				mat[3][1].setColor(whito, wstr, wint);

				state = 1;
			}
		}
	}
}
