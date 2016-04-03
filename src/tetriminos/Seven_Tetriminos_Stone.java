package tetriminos;
import java.io.*;

import tetris_GUI.*;

import java.awt.*;

/**
 * this is the basis class of all 7 different stone-classes which are named like: I,S,Z,O,T,L,J. it contains several methodes which are all the same for all different stones, and defines several abstract methodes, which are differently implementet in the concrete stone-objects..
 *
 * @author RAMRAMI
 */
public abstract class Seven_Tetriminos_Stone implements Serializable //implements S_Block, Serializable 
{
	protected String name;
	public int state = 0;
	public All_Tetriminos_ColorBlock[][] mat = new All_Tetriminos_ColorBlock[4][4];
	public int wint = 0;
	public Color whito = Color.white;
	public String wstr = "white";
	public int x = 5;
	public int y = 0;
	public Play_Ground_Panel tetrisPanel;
	private static final long serialVersionUID = 13L;

	/**
	 * default constructor only sets the name-variable to "Stone"
	 *
	 * @return nothing
	 */
	
	public Seven_Tetriminos_Stone()
	{
		name = "Stone";
	}

	/**
	 * @return
	 */
	public abstract String getName();

	public abstract String toString();

	public abstract All_Tetriminos_ColorBlock[][] getMat();

	/**
	 * abstract methode to rotate the stone on the board (implemented in the specific stone-classes)
	 *
	 * @return nothing
	 */
	public abstract void rotate();

	/**
	 * methode to paint this stone while falling down the board
	 *
	 * @param voodoo the Graphics-object on which the stone is painted
	 * @return nothing
	 */
	public void showStone(Graphics voodoo)
	{
		mat = getMat();
		//System.out.println("stone matrix: ");
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				//System.out.print(mat[i][j].col+" ");
				if(mat[i][j].col != 0)
					mat[i][j].paintBlock(voodoo, (x+j), (y+i));
					//mat[i][j].paintBlock(voodoo, (x+i), (y+j));
			}
			//System.out.println();
		}
	}

	/**
	 * moves the stone to the left. uses the methode checkCollision() to validate whether the move is possible or not.
	 *
	 * @return nothing
	 */
	public void moveLeft()
	{
		x--;
		if (checkCollision())
		{
			x++;
		}
	}

	/**
	 * moves the stone to the right. uses the methode checkCollision() to validate whether the move is possible or not.
	 *
	 * @return nothing
	 */
	public void moveRight()
	{
		x++;
		if (checkCollision())
		{
			x--;
		}
	}

	/**
	 * moves the stone one step down (like it will be after every tick) uses the methode checkCollision() to validate whether the move is possible or not.
	 *
	 * @return nothing
	 */
	public void moveDown()
	{
		y++;
		if(checkCollision())
		{
			y--;
		}
	}


	/**
	 * moves the stone all down to the bottom of the bord. uses the methode checkCollision() to get the last possible position for the stone.
	 *
	 * @return nothing
	 */
	public void down()
	{
		boolean down = true;

		while(down)
		{
			y++;
			if(checkCollision())
			{
				y--;
				down = false;
			}
		}
	}


	/**
	 * checks if the move which executes the methode is possible, or if it would last in a collision between the stone and another stone or the border of the bord and so returns true or false
	 *
	 * @return true if the move is possible
	 * @return false if the move is not allowed
	 */
	public boolean checkCollision()
	{
		mat = getMat();
		for(int i = 0; i<4; i++)
		{
			for(int j =0; j<4; j++)
			{
				if((mat[j][i].col * tetrisPanel.getTetMatAt(x+i,y+j).col) != 0)//if((mat[i][j].col * tetrisPanel.getTetMatAt(x+i,y+j).col) != 0)
				{
					//System.out.println("Collision >> x:"+x+" y:"+y);
					return true;
				}
			}
		}
		return false;
	}
	
}
