package tetris_GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import tetris_Client.Client_from_Server;

/**
 * ClientSide_GUI is the graphical user interface for the clients of this tetris. It builds up the window with its different elements.
 * It also starts a new Thread for the Client_from_Server class.
 *
 * @author RAMRAMI
 */

public class ClientSide_GUI extends JFrame implements ActionListener, KeyListener
{
	
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel commandoPanel;
	private JPanel infoPanel;
	private JPanel connectPanel;
	private JPanel playerPanel;
	private JLabel ipLabel;
	private JLabel nameLabel;
	private JLabel yourNameLabel;
	private JLabel playerName;
	private JLabel partPlayerLabel;
	private JLabel nextStoneLabel;
	private JButton connectButton;
	private JTextField serverIP;
	private JTextField gameName;
	
	private JLabel  timeLeftLabel;
	private JLabel timeLeftShowLabel;
	private JLabel partPlayer;
	public Play_Ground_Panel tetrisPanel;
	public NextStoneLabel nextStoneShowLabel;

	private static final long serialVersionUID = 13L;
	
	
	public ClientSide_GUI()
	{
		try
		{
	
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}

		
		mainFrame = new JFrame("Tetris");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS ));
		mainPanel.setMinimumSize(new Dimension(600, 520));
		mainPanel.setMaximumSize(new Dimension(600, 520));
		mainPanel.setPreferredSize(new Dimension(600, 520));
		mainPanel.setBackground(Color.lightGray);



		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS ));
		infoPanel.setMinimumSize(new Dimension(280, 520));
		infoPanel.setMaximumSize(new Dimension(280, 520));
		infoPanel.setPreferredSize(new Dimension(280, 520));
		infoPanel.setBackground(Color.lightGray);



		tetrisPanel = new Play_Ground_Panel();
		tetrisPanel.setLayout(new GridLayout(1,1) );
		tetrisPanel.setMinimumSize(new Dimension(300, 520));
		tetrisPanel.setMaximumSize(new Dimension(300, 520));
		tetrisPanel.setPreferredSize(new Dimension(300, 520));


		commandoPanel = new JPanel();
		commandoPanel.setLayout(new BoxLayout(commandoPanel, BoxLayout.Y_AXIS ));
		commandoPanel.setMinimumSize(new Dimension(280, 225));
		commandoPanel.setMaximumSize(new Dimension(280, 225));
		commandoPanel.setPreferredSize(new Dimension(280, 225));
		commandoPanel.setBackground(Color.lightGray);


		connectPanel = new JPanel();
		connectPanel.setLayout(new BoxLayout(connectPanel, BoxLayout.Y_AXIS ));
		connectPanel.setMinimumSize(new Dimension(280, 160));
		connectPanel.setMaximumSize(new Dimension(280, 160));
		connectPanel.setPreferredSize(new Dimension(280, 160));
		connectPanel.setBackground(Color.lightGray);


		playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS ));
		playerPanel.setMinimumSize(new Dimension(280, 250));
		playerPanel.setMaximumSize(new Dimension(280, 250));
		playerPanel.setPreferredSize(new Dimension(280, 250));
		playerPanel.setBackground(Color.lightGray);

		//commandoPanel
		nextStoneLabel = new JLabel("<html>next stone: </html>", SwingConstants.LEFT);

		nextStoneShowLabel = new NextStoneLabel();
		nextStoneShowLabel.setPreferredSize(new Dimension(100, 80));
		nextStoneShowLabel.setMinimumSize(new Dimension(100, 80));
		nextStoneShowLabel.setMaximumSize(new Dimension(100, 80));
		nextStoneShowLabel.setOpaque(true);
		tetrisPanel.nextStoneShowLabel=nextStoneShowLabel;


		timeLeftLabel = new JLabel("<html>time left: </html>", SwingConstants.LEFT);

		timeLeftShowLabel = new JLabel("", SwingConstants.CENTER);
		timeLeftShowLabel.setPreferredSize(new Dimension(100, 20));
		timeLeftShowLabel.setMinimumSize(new Dimension(100, 20));
		timeLeftShowLabel.setMaximumSize(new Dimension(100, 20));
		timeLeftShowLabel.setOpaque(true);
		timeLeftShowLabel.setBackground(Color.lightGray);

		commandoPanel.add(nextStoneLabel);
		commandoPanel.add(nextStoneShowLabel);
		commandoPanel.add(timeLeftLabel);
		commandoPanel.add(timeLeftShowLabel);


		//connectPanel
		ipLabel = new JLabel("<html>server IP: </html>", SwingConstants.LEFT);
		serverIP = new JTextField("localhost");

		connectButton = new JButton("connect");
		connectButton.setPreferredSize(new Dimension(100, 20));
		connectButton.setMinimumSize(new Dimension(100, 20));
		connectButton.setMaximumSize(new Dimension(100, 20));
		connectButton.setActionCommand("connect");
		connectButton.addActionListener(this);
		connectButton.addKeyListener(this);

		nameLabel = new JLabel("<html>gameName: </html>", SwingConstants.LEFT);
		gameName = new JTextField();
		
		
		connectPanel.add(ipLabel);
		connectPanel.add(serverIP);
		connectPanel.add(nameLabel);
		connectPanel.add(gameName);
		connectPanel.add(connectButton);


		//playerPanel
		yourNameLabel = new JLabel("<html><b>gameName: </b></html>");
		yourNameLabel.setPreferredSize(new Dimension(100, 20));
		yourNameLabel.setMinimumSize(new Dimension(100, 20));
		yourNameLabel.setMaximumSize(new Dimension(100, 20));


		playerName = new JLabel();
		playerName.setPreferredSize(new Dimension(280, 20));
		playerName.setMinimumSize(new Dimension(280, 20));
		playerName.setMaximumSize(new Dimension(280, 20));

		partPlayerLabel = new JLabel("<html>scoreList: </html>");
		partPlayerLabel.setPreferredSize(new Dimension(100, 20));
		partPlayerLabel.setMinimumSize(new Dimension(100, 20));
		partPlayerLabel.setMaximumSize(new Dimension(100, 20));

		partPlayer = new JLabel();
		partPlayer.setPreferredSize(new Dimension(280, 150));
		partPlayer.setMinimumSize(new Dimension(280, 150));
		partPlayer.setMaximumSize(new Dimension(280, 150));


		playerPanel.add(playerName);
		playerPanel.add(partPlayerLabel);
		playerPanel.add(partPlayer);

		//emptyBorder bedeuet, dass au�en herum ein leerer Kasten gezeichnet wird
		infoPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		tetrisPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		//TitleBorder beinhaltet den Titel eines Panels
		commandoPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("info"),
				BorderFactory.createEmptyBorder(5,5,5,5)));

		connectPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("connect"),
				BorderFactory.createEmptyBorder(5,5,5,5)));

		playerPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("players"),
				BorderFactory.createEmptyBorder(5,5,5,5)));


		infoPanel.add(commandoPanel);
		infoPanel.add(connectPanel);

		mainPanel.add(tetrisPanel);
		mainPanel.add(infoPanel);

		//um ueberhaupt auf dem mainFrame zeichnen zu koennen
		mainFrame.getContentPane().add(mainPanel);

		//klatscht alle Components in der gewuenschten Groe�e hin
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}



	/**
	 * This method is called up when a Component in this case the start button has an action listener
	 * and therefore is pressed. Now If you press the start button the actions defined in this method are taking place.
	 *
	 * @param event is also the default parament this method needs to have
	 */
	public void actionPerformed(ActionEvent event)
	{
	
		if ("connect".equals(connectButton.getActionCommand()))
		{
	
			connect();
		}
	}
	
	public void keyPressed(KeyEvent ke)
	{
	
		int key = ke.getKeyCode();
		System.out.println("KEYEVENT Pressed");
		if(key == KeyEvent.VK_ENTER)
		{
			System.out.println("Enter => Connect");
			connect();
		}	
	}
	public void keyTyped(KeyEvent ke)
	{
		System.out.println("KEYEVENT Typed");
	}
	public void keyReleased(KeyEvent ke)
	{
		System.out.println("KEYEVENT REleased");
	}
	
	public void setTimeL(String time)
	{
		timeLeftShowLabel.setText(time);
	}
	
	public void setPlayerL(String player)
	{
		partPlayer.setText(player);
	}
	
	public void setTimeLabel(String txt)
	{
		timeLeftLabel.setText(txt);
	}
	
	public void connect()
	{
		String connect = serverIP.getText();
		String playerN = gameName.getText();
	
		infoPanel.remove(connectPanel);
		infoPanel.add(playerPanel);
	
		infoPanel.validate();
		playerName.setText(playerN);

		try
		{

	
			Client_from_Server progStart = new Client_from_Server(connect, playerN, tetrisPanel,nextStoneShowLabel,this);
			progStart.start();

		}
		catch(Exception e)
		{
			System.out.println("Connection failed");
		}
	}

	private void jbInit() throws Exception {
	}
}
