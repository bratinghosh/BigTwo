import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
/**
 * The BigTwoTable class is a subclass of CardGameTable and is used to build a GUI
 * for the Big Two card game and handle all user actions
 * 
 * @author Bratin Ghosh 3035437692
 * 
 */
public class BigTwoTable implements CardGameTable {
	private BigTwoClient game; 
	private boolean[] selected;
	private int activePlayer;
	private JFrame frame;
	private JPanel bigTwoPanel; 
	private JButton playButton; 
	private JButton passButton; 
	private JTextArea msgArea;
	private JTextArea msgArea1; 
	private Image[][] cardImages; 
	private Image cardBackImage;
	private Image[] avatars; 
	private boolean[] presence;
	private JTextField message_box;	
	/**
     * BigTwoTable constructor to intialize variables and create a BigTwoTable
     */
	public BigTwoTable(BigTwoClient game) 
	{
		this.game = game;
		this.setup();
		frame.setSize(1250, 925);
		frame.setVisible(true);
	}
	
		/**
	 * helps us to set the not existence using the player ID
	 */
	public void setNotExistence(int playerID)
	{
		presence[playerID] = false;
	}
	/**
	 * helps us to set the active player 
	 */
	public void setActivePlayer(int activePlayer)
	{
		this.activePlayer = activePlayer;
	}
	/**
     *  method for getting an array of indices of the cards selected
     *  
     *  @param ct    stores the total number of selected cards
     *  @param user_input      array that stores the index of all the selected cards
     *  @param counter             keep track of the number of selected cards
     *  
     *  @return user_input
     */
	public int[] getSelected()
	{
		int ct = 0;
		for (int i = 0; i < 13; i++)
		{
			if (selected[i])
			{
				ct++;
			}
		}
		int[] user_input = new int[ct];
		int counter = 0;
		for (int i = 0; i < 13; i++)
		{
			if (selected[i])
			{
				user_input[counter] = i;
				counter++;
			}
		}
		return user_input; 
	}
	/**
	 * helps us to reset the selected cards from the current hand
	 */
	public void resetSelected()
	{
		for (int i = 0; i < 13; i++)
			selected[i] = false;
	}
	/**
	 * used for repainting the entire frame 
	 */
	public void repaint()
	{
		frame.repaint();
	}
	/**
	 * helps us to print message on the message area
	 */
	public void printMsg(String msg)
	{
		msgArea.append(msg);
	}
	/**
	 * helps us to print message when a person wins the game of big two
	 */
	public void printEndGameMsg()
	{
		String endOfGame="";
		for(int i=0; i<game.getNumOfPlayers(); ++i)
		{
			endOfGame+=game.getPlayerList().get(i).getName()+": ";
			if(game.getPlayerList().get(i).getNumOfCards()!=0)
			{
				for(int j=0; j<game.getPlayerList().get(i).getNumOfCards(); ++j)
					endOfGame+=" ["+game.getPlayerList().get(i).getCardsInHand().getCard(j).toString()+"]";
				endOfGame+="\n";
			}
			else
			{
				endOfGame+=" wins\n";
			}
		}
		JOptionPane.showMessageDialog(null, "GameOver!!\n"+endOfGame);
	}
	/**
	 * used to print messages on the chat area
	 */
	public void printChatMsg(String msg) 
	{
		msgArea1.append(msg+"\n");
	}
	/**
	 * helps us to clear the message area
	 */
	public void clearMsgArea()
	{
		msgArea.setText("");
	}
	/**
	 * helps us to clear the chat area
	 */
	public void clearChatMsgArea() 
	{
		this.msgArea1.setText("");
	}
	/**
	 * used to set the presence of the player
	 */
	public void setExistence(int playerID)
	{
		presence[playerID] = true;
	}
	/**
     * used to reset the GUI and clear message 
     */
	public void reset()
	{
		frame.setVisible(false);
		BigTwoDeck deck = new BigTwoDeck();
		deck.shuffle();
		game.start(deck);
		printMsg("Game Restarted by User!");
	}
	/**
     * enable() helps us to enable the play and push buttons and the bigtwo panel
     */
	public void enable()
	{
		bigTwoPanel.setEnabled(true);
		playButton.setEnabled(true);
		passButton.setEnabled(true);
	}
	/**
     * disable() helps us to disable the play and push buttons and the bigtwo panel
     */
	public void disable()
	{
		bigTwoPanel.setEnabled(false);
		playButton.setEnabled(false);
		passButton.setEnabled(false);
	}
	/**
     * quit() helps us to quit the game and stop the program
     */
	public void quit() 
	{
		System.exit(0);
	}
	
	/**
     * setup() method helps us to setup the GUI like cards, avatars, etc and prepare the board 
     * for a game of Big two
     * 
     * @param frame                     new frame which contains all the GUI
     * @param suit,rank                 contains the suit and rank of all 52 cards
     * @param fileLocation              contains the path to the pictures of all the 52 cards
     * @param left_pane,right_pane      left and right panes on the frame
     * @param Menu_Bar                  contains the menu items 
     * @param cardImages                contains the card Images of all 52 cards
     * @param avatars                   contains all the 8 avatars
     * @param Game,Message              Menus present in the menu bar
     * @param Quit, Clear_Information, Clear_ChatBoard      Menu items present in the menus
     * @param msgArea, msgArea1         text area where all the moves/messages are posted
     * @param scroll_1,scroll_2         Scroll bars for the 2 message areas
     * @param message_input             Input box for typing messages/chat box
     */
	public void setup()
	{
		setActivePlayer(game.getPlayerID()); 
		selected = new boolean[13];
		resetSelected();
		bigTwoPanel = new BigTwoPanel();
		cardImages = new Image [4][13];
		avatars = new Image[4];
		avatars[0] = new ImageIcon("src/avatars/flash_128.png").getImage();
		avatars[1] = new ImageIcon("src/avatars/superman_128.png").getImage();
		avatars[2] = new ImageIcon("src/avatars/wonder_woman_128.png").getImage();
		avatars[3] = new ImageIcon("src/avatars/batman_128.png").getImage();
		cardBackImage = new ImageIcon("src/cards/b.gif").getImage();
		char[] suit = {'d','c','h','s'};
		char[] rank = {'a', '2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k'};
		String fileLocation = new String();
		for (int i = 0; i < 4; i++)
		    {
				for(int j = 0 ; j < 13;j++)
				{
					fileLocation = "src/cards/" + rank[j] + suit[i] + ".gif";
			        cardImages[i][j] = new ImageIcon(fileLocation).getImage();
			     }
			 }
		presence = new boolean[4];
		for (int i = 0; i < 4; i++)
			presence[i] = false;		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setTitle("Big Two");
		frame.getContentPane().setBackground(new Color(25, 115, 25));
		final JSplitPane left_pane = new JSplitPane();
		left_pane.setDividerLocation(800);
		left_pane.setDividerSize(5);
		frame.add(left_pane, BorderLayout.CENTER);
		JMenuBar Menu_Bar = new JMenuBar();
		JMenu Game = new JMenu("Game");
		JMenu Message = new JMenu("Message");
		JMenuItem Connect = new JMenuItem("Connect");
		Connect.addActionListener(new ConnectMenuItemListener());
		JMenuItem Quit = new JMenuItem("Quit");
		Quit.addActionListener(new QuitMenuItemListener());
		JMenuItem Clear_Information = new JMenuItem("Clear Infomation Board");
		Clear_Information.addActionListener(new ClearMenuItemListener());
		JMenuItem Clear_ChatBoard = new JMenuItem("Clear Chat Board");
		Clear_ChatBoard.addActionListener(new ClearChatMenuItemListener());
		Game.add(Connect);
		Game.add(Quit);
		Message.add(Clear_Information);
		Message.add(Clear_ChatBoard);
		Menu_Bar.add(Game);
		Menu_Bar.add(Message);
		frame.setJMenuBar(Menu_Bar);
		msgArea = new JTextArea(100, 30);
		DefaultCaret caret = (DefaultCaret)msgArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		msgArea.append("Message Board\n");
		msgArea.setEditable(false);
		JScrollPane scroll_1 = new JScrollPane(msgArea);   
		msgArea.setLineWrap(true); 
		scroll_1.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);    
		JPanel message = new JPanel();
		message.setLayout(new BoxLayout(message, BoxLayout.Y_AXIS));
		msgArea1 = new JTextArea(100, 30);
		DefaultCaret caret1 = (DefaultCaret)msgArea1.getCaret();
		caret1.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		msgArea1.append("This is the chat area!\n\n");
		msgArea1.setEditable(false);
		JScrollPane scroll_2 = new JScrollPane(msgArea1);   
		msgArea1.setLineWrap(true); 
		scroll_2.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JLabel label = new JLabel("Message: ");
		message_box = new MyTextField(30);
		message_box.setMinimumSize(new Dimension(30, 10));
		JPanel message_input = new JPanel();
		message_input.setLayout(new FlowLayout(FlowLayout.LEFT));
		message_input.add(label);
		message_input.add(message_box);
		message.add(scroll_1);
		message.add(scroll_2);
		message.add(message_input);
		left_pane.setRightComponent(message);
		left_pane.getRightComponent().setMinimumSize(new Dimension(100, 60));
		JPanel button_area = new JPanel();
		button_area.setLayout(new FlowLayout());
		playButton = new JButton(" Play ");
		playButton.addActionListener(new PlayButtonListener());
		passButton = new JButton(" Pass ");
		passButton.addActionListener(new PassButtonListener());
		button_area.add(playButton);
		button_area.add(passButton);
		button_area.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		button_area.setSize(900, 35);
		if (game.getCurrentIdx() != activePlayer)
		{
			button_area.setEnabled(false);
			playButton.setEnabled(false);
			passButton.setEnabled(false);
		}
		else
		{
			button_area.setEnabled(true);
			playButton.setEnabled(true);
			passButton.setEnabled(true);
		} 
		final JSplitPane right_pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		right_pane.setDividerSize(5);
		right_pane.setDividerLocation(800);;
		right_pane.setTopComponent(bigTwoPanel);
		right_pane.setBackground(Color.green.darker().darker());
		right_pane.setBottomComponent(button_area);
		right_pane.getBottomComponent().setMinimumSize(new Dimension(800, 35));
		left_pane.setLeftComponent(right_pane);
	}
	/**
     * Inner class that extends the JPanel class and implements the
     * MouseListener interface
     */
	class BigTwoPanel extends JPanel implements MouseListener {
		private static final long serialVersionUID = 1L;
		/**
         * This is a BigTwoPanel constructor and it helps add Mouse Listener to the panels.
         */
		public BigTwoPanel() 
		{
			this.addMouseListener(this);
		}
		/**
         * method for setting up the GUI after each move like changing the size of avatars
         * to denote which player's turn it is, selection and cards, etc.
         * 
         * @param g2    graphics object for painting the GUI
         */
		public void paintComponent(Graphics g) 
		{
			this.setOpaque(true);
			Graphics2D g2 = (Graphics2D) g;
	        if (presence[0]) 
	        {
		        if (game.getCurrentIdx() == 0 && game.getPlayerList().get(0).getNumOfCards() != 0)
		        		g.setColor(Color.BLUE);
		        if (activePlayer == 0)
		        {
		        		g.drawString("You", 10, 20);
		        }
		        		
		        else
		        {
		        		g.drawString(game.getPlayerList().get(0).getName(), 10, 20);
		        }
		        g.setColor(Color.BLACK);
				g.drawImage(avatars[0], 10, 20, this);
			}
	        g2.drawLine(0, 160, 1600, 160);
	        if (activePlayer == 0) 
	        {
		        	for (int i = 0; i < game.getPlayerList().get(0).getNumOfCards(); i++) 
		        	{
			    		if (!selected[i])
			    			g.drawImage(cardImages[game.getPlayerList().get(0).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(0).getCardsInHand().getCard(i).getRank()], 155+40*i, 43, this);
			    		else
			    			g.drawImage(cardImages[game.getPlayerList().get(0).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(0).getCardsInHand().getCard(i).getRank()], 155+40*i, 43-20, this);
			    	}
	        }
	        else
	        {
	        		for (int i = 0; i < game.getPlayerList().get(0).getCardsInHand().size(); i++)
	        			g.drawImage(cardBackImage, 155 + 40*i, 43, this);
		    }
		    if (presence[1])
		    {
			    if (game.getCurrentIdx() == 1 && game.getPlayerList().get(1).getNumOfCards() != 0)
			    		g.setColor(Color.BLUE);	
		        	if (activePlayer == 1)
		        	{
		        		g.drawString("You", 10, 180);
		        	}
		        		
		        else
		        {
		        		g.drawString(game.getPlayerList().get(1).getName(), 10, 180);
		        }
		        g.setColor(Color.BLACK);
        		g.drawImage(avatars[1], 10, 180, this);	
			}
		    g2.drawLine(0, 320, 1600, 320);
		    if (activePlayer == 1) 
		    {
			    	for (int i = 0; i < game.getPlayerList().get(1).getNumOfCards(); i++)
			    	{
			    		if (!selected[i])
			    			g.drawImage(cardImages[game.getPlayerList().get(1).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(1).getCardsInHand().getCard(i).getRank()], 155+40*i, 203, this);   		
			    		else
			    			g.drawImage(cardImages[game.getPlayerList().get(1).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(1).getCardsInHand().getCard(i).getRank()], 155+40*i, 203-20, this);
			    	}
		    } 
		    else
		    {
		    		for (int i = 0; i < game.getPlayerList().get(1).getCardsInHand().size(); i++) 
		    			g.drawImage(cardBackImage, 155 + 40*i, 203, this);
		    }
		    if (presence[2])
		    {
			    if (game.getCurrentIdx() == 2 && game.getPlayerList().get(2).getNumOfCards() != 0)
			       	g.setColor(Color.BLUE);
		        if (activePlayer == 2)
		        {
		        		g.drawString("You", 10, 340);
		        }
		        else
		        {
		        		g.drawString(game.getPlayerList().get(2).getName(), 10, 340);
		        }
		        g.setColor(Color.BLACK);
			    g.drawImage(avatars[2], 10, 340, this);
			}	    
		    g2.drawLine(0, 480, 1600, 480);
		    if (activePlayer == 2) 
		    {
			    	for (int i = 0; i < game.getPlayerList().get(2).getNumOfCards(); i++) 
			    	{
			    		if (!selected[i])
			    			g.drawImage(cardImages[game.getPlayerList().get(2).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(2).getCardsInHand().getCard(i).getRank()], 155+40*i, 363, this);
			    		else
			    			g.drawImage(cardImages[game.getPlayerList().get(2).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(2).getCardsInHand().getCard(i).getRank()], 155+40*i, 363-20, this);
			    	}
		    }
		    else
		    {
			    	for (int i = 0; i < game.getPlayerList().get(2).getCardsInHand().size(); i++)
			    		g.drawImage(cardBackImage, 155 + 40*i, 363, this);
		    }
		    if (presence[3])
		    {
			    if (game.getCurrentIdx() == 3 && game.getPlayerList().get(3).getNumOfCards() != 0)
			    		g.setColor(Color.BLUE);
		        if (activePlayer == 3)
		        {
		          	g.drawString("You", 10, 500);
		        }
		        else
		        {
		        		g.drawString(game.getPlayerList().get(3).getName(), 10, 500);
		        }
		        g.setColor(Color.BLACK);
			    g.drawImage(avatars[3], 10, 500, this);
			}
		    g2.drawLine(0, 640, 1600, 640);
		    if (activePlayer == 3)
		    {
			    	for (int i = 0; i < game.getPlayerList().get(3).getNumOfCards(); i++)
			    	{
			    		if (!selected[i])
			    			g.drawImage(cardImages[game.getPlayerList().get(3).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(3).getCardsInHand().getCard(i).getRank()], 155+40*i, 523, this);
			    		else
			    			g.drawImage(cardImages[game.getPlayerList().get(3).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(3).getCardsInHand().getCard(i).getRank()], 155+40*i, 523-20, this);
			    	}
		    } 
		    else
		    {
			    	for (int i = 0; i < game.getPlayerList().get(3).getCardsInHand().size(); i++)
			    		g.drawImage(cardBackImage, 155 + 40*i, 523, this);
		    }
		    g.drawString("Current Hand on Table", 10, 660);		    
		    if (game.getHandsOnTable().size() == 0)
		     	g.drawString("No Hand on Table.", 10, 690);
		    else
		    {
		    		Hand handOnTable = game.getHandsOnTable().get(game.getHandsOnTable().size()-1);
		    		g.drawString("Hand Type:\n ", 10, 720);
		    		if (game.getPlayerList().get(game.getCurrentIdx()) != game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getPlayer()) 
			    	{
			    		g.drawString(game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getPlayer().getName(), 10, 745);
			    		for (int i = 0; i < handOnTable.size(); i++)
			    			g.drawImage(cardImages[handOnTable.getCard(i).getSuit()][handOnTable.getCard(i).getRank()], 160 + 40*i, 690, this);
			    	}
		    }
		    g2.drawLine(0, 800, 1600, 800);
		}
		/**
         * handles all the mouse click events like selection and deselection of cards
         * @param check             used to keep a check
         * @param starting_point    stores the staring point
         */
		public void mouseClicked(MouseEvent e)
		{
			boolean check = false; 
			int starting_point = game.getPlayerList().get(activePlayer).getNumOfCards()-1;
			if (e.getX() >= (155+starting_point*40) && e.getX() <= (155+starting_point*40+73)) 
			{
				if(selected[starting_point] == false && e.getY() >= 43 + 160 * activePlayer && e.getY() <= 43 + 160 * activePlayer+97) 
				{
					selected[starting_point] = true;
					check = true;
				} 
				else if (selected[starting_point] == true && e.getY() >= 43 + 160 * activePlayer-20 && e.getY() <= 43 + 160 * activePlayer+97-20)
				{
					selected[starting_point] = false;
					check = true;
				}
			}
			for (starting_point = game.getPlayerList().get(activePlayer).getNumOfCards()-2; starting_point >= 0 && !check; starting_point--) 
			{
				if (e.getX() >= (155+starting_point*40) && e.getX() <= (155+starting_point*40+40)) 
				{
					if(selected[starting_point] == false && e.getY() >= 43 + 160 * activePlayer && e.getY() <= 43 + 160 * activePlayer+97) 
					{
						selected[starting_point] = true;
						check = true;
					} 
					else if (selected[starting_point] == true && e.getY() >= 43 + 160 * activePlayer-20 && e.getY() <= 43 + 160 * activePlayer+97-20)
					{
						selected[starting_point] = false;
						check = true;
					}
				}
				else if (e.getX() >= (155+starting_point*40+40) && e.getX() <= (155+starting_point*40+73) && e.getY() >= 43 + 160 * activePlayer && e.getY() <= 43 + 160 * activePlayer+97) 
				{
					if (selected[starting_point+1] == true && selected[starting_point] == false) 
					{
						selected[starting_point] = true;
						check = true;
					}
				}
				else if (e.getX() >= (155+starting_point*40+40) && e.getX() <= (155+starting_point*40+73) && e.getY() >= 43 + 160 * activePlayer-20 && e.getY() <= 43 + 160 * activePlayer+97-20)
				{
					if (selected[starting_point+1] == false && selected[starting_point] == true)
					{
						selected[starting_point] = false;
						check = true;
					}
				}
			}
			frame.repaint();
		}
		/**
         * Overridden method 
         */
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		/**
         * Overridden method 
         */
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		/**
         * Overridden method 
         */
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		/**
         * Overridden method 
         */
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	/**
	 * Inner class that extends the JTextField class and implements the
     * ActionListener interface
	 */
	class MyTextField extends JTextField implements ActionListener
	{
		private static final long serialVersionUID = 1L;
		/**
		 * MyTextField constructor for initializing the variables and adding an action listener
		 */
		public MyTextField(int i)
		{
			super(i);
			addActionListener(this);
		}
		/**
		 * Method to perform the required action when the action is encountered, in this case when someone sends a message
		 */
		public void actionPerformed(ActionEvent event)
		{
			String input = getText();
			if (input != null && input.trim().isEmpty() == false) 
			{
				CardGameMessage message = new CardGameMessage(7, activePlayer, input);
				game.sendMessage(message);
			}
			this.setText("");
		}
	}
	
	/**
     * Inner class that implements the ActionListener
     * interface, implements the actionPerformed() method from the ActionListener interface
     * to handle button-click events for the “Play” button
     */
	class PlayButtonListener implements ActionListener
	{
	    /**
	     * The method is invoked when the play button is clicked thus playing the required set of cards
	     */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if (game.getCurrentIdx() == activePlayer)
			{ 
				if (getSelected().length == 0) 
				{	
					int [] cardIdx = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
					game.makeMove(activePlayer, cardIdx);
				}
				else
				{
					game.makeMove(activePlayer, getSelected());
				}
				resetSelected();
				repaint();
			}
			else 
			{ 
				printMsg("It is not your turn\n");
				resetSelected();
				repaint();
			}
		}
	}
	
	/**
     * Inner class that implements the ActionListener
     * interface, implements the actionPerformed() method from the ActionListener interface
     * to handle button-click events for the “Pass” button
     */
	class PassButtonListener implements ActionListener
	 	{
		/**
	     * The method is invoked when the pass button is clicked thus passing this current turn
	     */
		public void actionPerformed(ActionEvent e) {
			if (game.getCurrentIdx() == activePlayer)
			{ 
				int[] cardIdx = null;
				game.makeMove(activePlayer, cardIdx);
				resetSelected();
				repaint();
			} 
			else 
			{
				printMsg("Not your turn!\n");
				resetSelected();
				repaint();
			}
		}
	}
	
	/**
     * Inner class that implements the ActionListener
     * interface, implements the actionPerformed() method from the ActionListener interface
     * to handle connect-click in the menu item
     */
	class ConnectMenuItemListener implements ActionListener
	{
	    /**
	     * The method is invoked when the connect menuitem is clicked
	     */
		public void actionPerformed(ActionEvent e) 
		{
			if (game.getPlayerID() == -1) 
				game.makeConnection();
			else if (game.getPlayerID() >= 0 && game.getPlayerID() <= 3)
				printMsg("Connection already established!\n");
		}
	}
	
	/**
     * Inner class that implements the ActionListener
     * interface, implements the actionPerformed() method from the ActionListener interface
     * to handle menu-item-click events for the “Quit” menu item
     */
	class QuitMenuItemListener implements ActionListener
	{
		/**
	     * The method is invoked when the quit menuitem is clicked thus quitting the game
	     */
		public void actionPerformed(ActionEvent e)
		{
			printMsg("Game Ended by the User!\n");
			System.exit(0);
		}
	}
	/**
     * Inner class that implements the ActionListener
     * interface, implements the actionPerformed() method from the ActionListener interface
     * to handle Clear menu-click in the menu item
     */
	class ClearMenuItemListener implements ActionListener
	{
	    /**
	     * The method is invoked when the clear menu menuitem is clicked thus clearing the menu 
	     */
		public void actionPerformed(ActionEvent e)
		{
			clearMsgArea();
		}
	}
	
	/**
     * Inner class that implements the ActionListener
     * interface, implements the actionPerformed() method from the ActionListener interface
     * to handle Clear chat menu-click in the menu item
     */
	class ClearChatMenuItemListener implements ActionListener
	{
	    /**
	     * The method is invoked when the clear chat menu menuitem is clicked thus clearing the chat menu 
	     */
		public void actionPerformed(ActionEvent e)
		{
			clearChatMsgArea();
		}
	}
}