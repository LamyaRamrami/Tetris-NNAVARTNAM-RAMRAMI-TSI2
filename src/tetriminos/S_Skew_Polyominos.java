package tetriminos;
import java.awt.Color;
import java.io.*;

/**
 * this class represents the stone looking like a "S"
 *
 * @author RAMRAMI
 */
public class  S_Skew_Polyominos extends Seven_Tetriminos_Stone implements Serializable
{

	private int colint;
	private Color colo;
	private String colstr;
	
	private static final long serialVersionUID = 13L;

	public S_Skew_Polyominos()
	{
		name = "S";
		colint = 5;
		colo = Color.magenta;
		colstr= "magenta";
		mat = new All_Tetriminos_ColorBlock[][]
		       {{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint)},
				{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(colo, colstr, colint),new All_Tetriminos_ColorBlock(colo, colstr, colint),new All_Tetriminos_ColorBlock(whito, wstr, wint)},
				{new All_Tetriminos_ColorBlock(colo, colstr, colint),new All_Tetriminos_ColorBlock(colo, colstr, colint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint)},
				{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint)}};
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
			mat[1][2].setColor(whito, wstr, wint);
			mat[2][0].setColor(whito, wstr, wint);
			mat[2][2].setColor(colo, colstr, colint);
			mat[3][2].setColor(colo, colstr, colint);

			state = 1;
			if(checkCollision())
			{
				mat[1][2].setColor(colo, colstr, colint);
				mat[2][0].setColor(colo, colstr, colint);
				mat[2][2].setColor(whito, wstr, wint);
				mat[3][2].setColor(whito, wstr, wint);

				state = 0;
			}
		}
		else
		{
			mat[1][2].setColor(colo, colstr, colint);
			mat[2][0].setColor(colo, colstr, colint);
			mat[2][2].setColor(whito, wstr, wint);
			mat[3][2].setColor(whito, wstr, wint);

			state = 0;
			if(checkCollision())
			{
				mat[1][2].setColor(whito, wstr, wint);
				mat[2][0].setColor(whito, wstr, wint);
				mat[2][2].setColor(colo, colstr, colint);
				mat[3][2].setColor(colo, colstr, colint);

				state = 1;
			}
		}
	}
}
