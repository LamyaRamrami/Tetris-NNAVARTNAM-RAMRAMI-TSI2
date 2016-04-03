package tetris_GUI;
import javax.swing.*;

import tetris_Server.*;

import java.awt.*;
import java.awt.event.*;

/**
 * ServerSide_GUI is the graphical user interface for the server of this tetris. It builds up the window with its different elements.
 * It also starts a new Thread for the Server_connectClient class.
 *
 * @author RAMRAMI
 */
public class ServerSide_GUI extends JFrame implements ActionListener
{

	private JFrame mainFrame;
	private static JPanel mainPanel;
	private static JPanel tfPanel;
	private static JPanel labelPanel;
	private static JPanel buttonPanel;
	private static JPanel prefPanel;
	private static JPanel radioPanel;
	private static JPanel rbPanel;
	private static JPanel rlabelPanel;

	private JTextField numberOfClientsTF;

	private JLabel numberOfClientsLabel;

	private JLabel rbLabel;
	private JButton startGameB;
	private JRadioButton yrb;
	private JRadioButton nrb;

	private static final long serialVersionUID = 13L;

	public ServerSide_GUI()
	{
		//Create and set up the window.
		mainFrame = new JFrame("TetrisServer");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// dimension du panneau principal de Tetris
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS ));
		mainPanel.setMinimumSize(new Dimension(330, 100));
		mainPanel.setMaximumSize(new Dimension(330, 100));
		mainPanel.setPreferredSize(new Dimension(330, 100));
		mainPanel.setBackground(Color.lightGray);

		// Les labels et le text boxes du panneau principal
		prefPanel = new JPanel();
		prefPanel.setLayout(new BoxLayout(prefPanel, BoxLayout.X_AXIS ));
		prefPanel.setMinimumSize(new Dimension(330, 50));
		prefPanel.setMaximumSize(new Dimension(330, 50));
		prefPanel.setPreferredSize(new Dimension(330, 50));
		prefPanel.setBackground(Color.lightGray);

		//pour créer le panneau essayer un bouton rado

		radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS ));
		radioPanel.setMinimumSize(new Dimension(330, 25));
		radioPanel.setMaximumSize(new Dimension(330, 25));
		radioPanel.setPreferredSize(new Dimension(330, 25));
		radioPanel.setBackground(Color.lightGray);

		rlabelPanel = new JPanel();
		rlabelPanel.setLayout(new BoxLayout(rlabelPanel, BoxLayout.Y_AXIS ));
		rlabelPanel.setMinimumSize(new Dimension(120, 25));
		rlabelPanel.setMaximumSize(new Dimension(120, 25));
		rlabelPanel.setPreferredSize(new Dimension(120, 25));
		rlabelPanel.setBackground(Color.lightGray);


		rbPanel = new JPanel();
		rbPanel.setLayout(new BoxLayout(rbPanel, BoxLayout.X_AXIS ));
		rbPanel.setMinimumSize(new Dimension(160, 25));
		rbPanel.setMaximumSize(new Dimension(160, 25));
		rbPanel.setPreferredSize(new Dimension(160, 25));
		rbPanel.setBackground(Color.lightGray);

		labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS ));
		labelPanel.setMinimumSize(new Dimension(220, 25));
		labelPanel.setMaximumSize(new Dimension(220, 25));
		labelPanel.setPreferredSize(new Dimension(220, 25));
		labelPanel.setBackground(Color.lightGray);



		tfPanel = new JPanel();
		tfPanel.setLayout(new BoxLayout(tfPanel, BoxLayout.Y_AXIS ));
		tfPanel.setMinimumSize(new Dimension(60, 25));
		tfPanel.setMaximumSize(new Dimension(60, 25));
		tfPanel.setPreferredSize(new Dimension(60, 25));
		tfPanel.setBackground(Color.lightGray);

		//Panel nur fï¿½r den Button
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS ));
		buttonPanel.setMinimumSize(new Dimension(280, 25));
		buttonPanel.setMaximumSize(new Dimension(280, 25));
		buttonPanel.setPreferredSize(new Dimension(280, 25));
		buttonPanel.setBackground(Color.lightGray);


		numberOfClientsLabel = new JLabel("Number of connectable Clients: ", SwingConstants.RIGHT);
		numberOfClientsLabel.setMinimumSize(new Dimension(220, 25));
		numberOfClientsLabel.setMaximumSize(new Dimension(220, 25));
		numberOfClientsLabel.setPreferredSize(new Dimension(220, 25));

		numberOfClientsTF = new JTextField("1");

		//Bouton radio

		rbLabel = new JLabel("Start Server: ", SwingConstants.RIGHT);
		rbLabel.setMinimumSize(new Dimension(120, 25));
		rbLabel.setMaximumSize(new Dimension(120, 25));
		rbLabel.setPreferredSize(new Dimension(120, 25));

		yrb = new JRadioButton("yes");
		nrb = new JRadioButton("no");
		yrb.setSelected(true);

		ButtonGroup grb = new ButtonGroup();
		grb.add( yrb ); grb.add( nrb );


		//Démarrage du bouton
		startGameB = new JButton("Start");
		startGameB.setMinimumSize(new Dimension(100, 20));
		startGameB.setMaximumSize(new Dimension(100, 20));
		startGameB.setPreferredSize(new Dimension(100, 20));
		startGameB.addActionListener(this);



		labelPanel.add(numberOfClientsLabel);
		tfPanel.add(numberOfClientsTF);


		//Bouton radio
		rlabelPanel.add(rbLabel);
		rbPanel.add(yrb);
		rbPanel.add(nrb);


		radioPanel.add(rlabelPanel);
		radioPanel.add(rbPanel);


		prefPanel.add(labelPanel);
		prefPanel.add(tfPanel);
		buttonPanel.add(startGameB);



		mainPanel.add(radioPanel);

		mainPanel.add(prefPanel);
		mainPanel.add(buttonPanel);


		//Afficher la fenetre du jeu.
		mainFrame.getContentPane().add(mainPanel);

		mainFrame.pack();
		mainFrame.setVisible(true);
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 
	 * Cette méthode est appelée quand un composant dans ce cas, le bouton de démarrage a un listner d'action et a été pressée. 
	 * Maintenant, si vous appuyez sur le bouton de démarrage des actions définies dans cette méthode sont en cours.
	 * 
	 * @param event qui est le paramètre par defaut
	 */
	public void actionPerformed(ActionEvent event)
	{
		try
		{
			//Un bouton radio a été intérogé et le serveur a démarré
			if(yrb.isSelected())
			{
				int numberOfClients = Integer.parseInt(
						numberOfClientsTF.getText());
				// 	crée une classe qui gère tous les champs et les méthodes communes
				Server_Administration commonThings = new Server_Administration(numberOfClients);
				//Le Thread de connexion du client au serveur démarre ici...
				Tetris_Server progStart = new
				Tetris_Server(commonThings);
				progStart.start();
				startGameB.setEnabled(false);
			}
		}
		catch(Exception e)
		{
			System.out.println("Connection failed");
		}

		try
		{
			mainFrame.setVisible(false);

			//Démarrer lorsque le client sélection un serveur dans l'interface Tetris
			new ClientSide_GUI();
		}
		catch(Exception e)
		{
		}
	}
	/**
	 * Il s'agit de la classe main. 
	 * This is the main method of this class. Appelée en entrant "java testris_GUI.S_GUI" dans le shell.
	 * Ensuite créee l'interface GUI et affiche la fenêtre du jeu....
	 * 
	 * @param args non utilisé
	 */
	public static void main(String[] args)
	{
		try
		{
			//L'apparance de l'interface GUI est définie ici et s'adapte aux différents OS: Linux, Windows et MacOS
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}
		// crée le GUI dans son constructeur et l'objet est généré automatiquement...
		new ServerSide_GUI();
	}





	private void jbInit() throws Exception {
	}
}
