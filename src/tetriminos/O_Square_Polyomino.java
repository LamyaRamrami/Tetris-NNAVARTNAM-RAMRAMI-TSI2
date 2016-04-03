package tetriminos;
import java.awt.Color;
import java.io.*;

/**
 * this class represents the stone looking like a "O"
 *
 * @author RAMRAMI
 */
public class O_Square_Polyomino extends Seven_Tetriminos_Stone implements Serializable
{

	private int colint;
	private Color colo;
	private String colstr;

	private static final long serialVersionUID = 13L;
	
	public O_Square_Polyomino()
	{
		name = "O";
		colint = 4;
		colo = Color.yellow;
		colstr= "yellow";
		mat = new All_Tetriminos_ColorBlock[][]
		       {{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(whito, wstr, wint)},
				{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(colo, colstr, colint),new All_Tetriminos_ColorBlock(colo, colstr, colint),new All_Tetriminos_ColorBlock(whito, wstr, wint)},
				{new All_Tetriminos_ColorBlock(whito, wstr, wint),new All_Tetriminos_ColorBlock(colo, colstr, colint),new All_Tetriminos_ColorBlock(colo, colstr, colint),new All_Tetriminos_ColorBlock(whito, wstr, wint)},
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

	}
}
