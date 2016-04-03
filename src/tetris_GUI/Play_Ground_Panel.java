package tetris_GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import tetriminos.*;

/**
 * The Play_Ground_Panel is basically the playground of this tetris. Most of the graphical actions happen here. It also implements
 * a KeyListener with which you can get commands over the keyboad. The paintComponent() is responsible for drawing he stones on the playground...
 * @author RAMRAMI
 */
public class Play_Ground_Panel extends JPanel implements KeyListener
{

	private All_Tetriminos_ColorBlock[][] tetMat = new All_Tetriminos_ColorBlock[15][26];
	public Seven_Tetriminos_Stone stone;

	private int width = 20;
	private int height = 20;
	public boolean gameOver = false;
	private JLabel gameOverLabel;
	public NextStoneLabel nextStoneShowLabel;

	private static final long serialVersionUID = 13L;

	public Play_Ground_Panel()
	{
		this.setPreferredSize(new Dimension(24*height, 12*width));
		
		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < 26; j++)
			{
				tetMat[i][j] = new All_Tetriminos_ColorBlock(Color.white, "white", 0);
			}
		}

		for(int i = 0; i < 23; i++)
		{
			tetMat[0][i].setColor(Color.lightGray, "lightGray", 8);
			tetMat[1][i].setColor(Color.lightGray, "lightGray", 8);
			tetMat[12][i].setColor(Color.lightGray, "lightGray", 8);
			tetMat[13][i].setColor(Color.lightGray, "lightGray", 8);
			tetMat[14][i].setColor(Color.lightGray, "lightGray", 8);
		}
		for(int i = 0; i < 15; i++)
		{
			tetMat[i][23].setColor(Color.lightGray, "lightGray", 8);
			tetMat[i][24].setColor(Color.lightGray, "lightGray", 8);
			tetMat[i][25].setColor(Color.lightGray, "lightGray", 8);
		}

		addKeyListener(this);
		//das gameOverLabel wird hier erzeugt jedoch nicht gleich zum Play_Ground_Panel hinzugefuegt, erst wenn gameOver = true!!
		gameOverLabel = new JLabel("<html><b><h1>GAME OVER!!!</h1></b></html>", SwingConstants.CENTER);
		gameOverLabel.setBackground(Color.white);
		//um das Spielfeld noch zu sehen wir der Hintergrund des Labels auf durchsichtig gestellt
		gameOverLabel.setOpaque(false);
		gameOverLabel.setPreferredSize(new Dimension(200, 100));
		gameOverLabel.setMinimumSize(new Dimension(200, 100));
		gameOverLabel.setMaximumSize(new Dimension(200, 100));

	}


	/**
	 * The paintComponent method is called up by repaint(). It fills the different fields playground matrix
	 * with the different colors of every stone placed in it and with white as the background and gray as the color of the window
	 * It also calls up the showStone() method and repaints the frame around the playgound...
	 *
	 * @param Graphics g is a default parameter which the paintComponent method needs to have!
	 */
	public void paintComponent(Graphics g)
	{
		requestFocus();

		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < 26; j++)
			{
				tetMat[i][j].paintBlock(g, i, j);	
			}
		}

		if (stone != null)
		{

			stone.showStone(g);
			g.setColor(Color.lightGray);

			g.fillRect(0, 3*height, 2*width, 23*height);
			
			g.fillRect(12*width, 3*height, 3*width, 23*height);
			
			g.fillRect(0, 23*height, 15*width, 3*height);
		}


		g.setColor(Color.lightGray);
		g.fillRect(40, 0, 10*width, 3*height);


		if (gameOver)
		{
			this.add(gameOverLabel);
			this.validate();
		}
	}
	
	public All_Tetriminos_ColorBlock[][] getTetMat()
	{
		return tetMat;
	}
	
	public All_Tetriminos_ColorBlock getTetMatAt(int x, int y)
	{
		return tetMat[x][y];
	}
	
	public void setTetMatAt(int x, int y, All_Tetriminos_ColorBlock blok)
	{
		tetMat[x][y] = blok;
	}

	/**
	 * This method is actually called up when a key is released however is this method empty...
	 * @param KeyEvent ke is the default parameter of an KeyEvent Method!
	 */
	public void keyReleased(KeyEvent ke)
	{

	}

	/**
	 * This method is called up when a key is pressed. If one of the arrows or space
	 * on keyboard is pressed one of these events are going to happen. Events like moving
	 * or rotating a stone and afterwards the the paintComponent() is called up... This all
	 * can only happen if stone != null!
	 * @param KeyEvent ke is the default parameter of an KeyEvent Method!
	 */
	public void keyPressed(KeyEvent ke)
	{

		int key = ke.getKeyCode();


		if(stone!=null)
		{
			switch(key)
			{

			case KeyEvent.VK_UP:
			{
				System.out.println("rotate stone");
				stone.rotate();
				repaint();
				break;
			}

			case KeyEvent.VK_LEFT:
			{
				System.out.println("move left");
				stone.moveLeft();
				repaint();
				break;
			}

			case KeyEvent.VK_RIGHT:
			{
				System.out.println("move right");
				stone.moveRight();
				repaint();
				break;
			}

			case KeyEvent.VK_DOWN:
			{
				System.out.println("move down");
				stone.moveDown();
				repaint();
				break;
			}

			case KeyEvent.VK_SPACE:
			{
				System.out.println("set down");
				stone.down();
				repaint();
				nextStoneShowLabel.repaint();
				break;
			}
			}
		}
	}

	/**
	 * This method is actually called up when a key istyped however is this method empty...
	 * @param KeyEvent ke is the default parameter of an KeyEvent Method!
	 */
	public void keyTyped(KeyEvent ke)
	{

	}
}





