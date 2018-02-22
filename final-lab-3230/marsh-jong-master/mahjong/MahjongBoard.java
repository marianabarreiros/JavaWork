import java.awt.*;

import java.awt.event.*;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JScrollPane;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.KeyStroke;
import java.util.*;


    
    
import java.awt.*;
public class MahjongBoard extends JFrame  {

    ///////














 
  

  

  


	//private static final long serialVersionUID = -8016337137685326379L;

	private static final int bWidth = 1200;
	private static final int bHeight = 850;
	private GamePanel gamePanel;

	private final int undoMove = 4;
	private final int redoMove = undoMove + 1;
	private final int showingHint = redoMove + 1;
	private final int saveGame = showingHint + 2;

	private final String title = "Mahjong game number : \t";
	private final String operationString = "  operation of the game. \n" +
			"FILE MENU:\n" +
			"\tNew Game: Starts a new game\n" +
			"\tNew Numbered Game: Starting the game by specific nubmer - you can see the game number in the title.\n" +
			"\tRestart: Restarts the  game.\n" +
			"\tUndo/Redo: undo or redo operation.\n" +
			"\tSave Game: to continue your game later.\n" +
			"\tExit Game: End the game and close it and exits\n\n" +
			"OPTIONS MENU:\n" +
			"\tRounded Corners: square or  rounded corners.\n" +
			"\tSound: sound on or off.\n" +
			"\tHigh Scores: show highest scores.\n" +
			"\tRemoved Tiles: showing removed tiles.\n\n";
	private final String rulesString = "   For more information about the game go to the following link.\n\n" +
			"   http://mahjong.wikidot.com/basic-rules \n\n" 
          +
			"   Good Luck!";

	private JFrame removedFrame = null;
	private JMenuItem removedTilesItem = null;
	private JFrame helpFrame = new JFrame();
///////////














///
 //   @Override
    public void run() {
        System.out.println("Timer task started at:"+new Date());
        completeTask();
        System.out.println("Timer task finished at:"+new Date());
    }

    private void completeTask() {
        try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
























//////
	public MahjongBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(bWidth, bHeight);
		setLayout(new BorderLayout());

		gamePanel = new GamePanel(getContentPane().getWidth(),
				getContentPane().getHeight(), true);

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");
      
      
      
      
      
		JMenuItem item = new JMenuItem("New Game");
		item.setMnemonic(KeyEvent.VK_N);
		item.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_N, KeyEvent.CTRL_MASK));
            
            
            
		item.setToolTipText("Starts a new game.");
		item.addActionListener(new ActionListener()
       {
			@Override
			public void actionPerformed(ActionEvent e) 
         {
				int chosen = JOptionPane.showConfirmDialog(
						(JMenuItem) e.getSource(),
						"Close this game and start a new one?",
						"New Game",
						JOptionPane.YES_NO_OPTION
						);
				if (chosen == 0) 
            {
						newGame();
				}
			}
		});
		menu.add(item);

		item = new JMenuItem("New specific  Game");
		item.setMnemonic(KeyEvent.VK_G);
		item.setToolTipText("Start specific game.");
		item.addActionListener(new ActionListener()
       {
			@Override
			public void actionPerformed(ActionEvent e)
          {
				String number = JOptionPane.showInputDialog(
						"Enter the game number");
                  // to make sure it is 6 digits
				if (Helper.isInteger(number) && number.length() == 6)
             {
					newGame(Long.parseLong(number));
				}
				else if (number != null) 
            {
					JOptionPane.showMessageDialog((JMenuItem) e.getSource(),
               
							"Invalid , game number must be 6 digits",
							"your number doesn't match any game",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menu.add(item);

		item = new JMenuItem("Restart");
		item.setToolTipText("Restarts the game.");
		item.addActionListener(new ActionListener() 
      {
			@Override
			public void actionPerformed(ActionEvent e)
          {
				int chosen = JOptionPane.showConfirmDialog(
						(JMenuItem) e.getSource(),
                  
						"Do you want to Restart your game?",
						"New Game",
						JOptionPane.YES_NO_OPTION
						);
				if (chosen == 0) {
					newGame(gamePanel.Gnumber);
				}
			}
		});
		menu.add(item);

		menu.addSeparator();

		item = new JMenuItem("Undo");
		item.setMnemonic(KeyEvent.VK_U);
		item.setAccelerator(KeyStroke.getKeyStroke(
      
      
      
      
				KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
		item.addActionListener(new ActionListener()
       {
			@Override
			public void actionPerformed(ActionEvent e)
          {
				gamePanel.undo();
			}
		});
		item.setEnabled(gamePanel.canUndo());
		menu.add(item, undoMove);

		item = new JMenuItem("Redo");
		item.setMnemonic(KeyEvent.VK_R);
		item.setAccelerator(KeyStroke.getKeyStroke
      (
				KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
		item.addActionListener(new ActionListener() 
      {
			@Override
			public void actionPerformed(ActionEvent e)
          {
				gamePanel.redo();
			}
		});
		item.setEnabled(gamePanel.RedoAbility());
		menu.add(item, redoMove);

		item = new JMenuItem("Tournament Mode");
		item.setMnemonic(KeyEvent.VK_H);

		menu.add(item, showingHint);

		menu.addSeparator();

		item = new JMenuItem("Save Game");
		item.setMnemonic(KeyEvent.VK_S);
		item.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		item.setToolTipText("able you to play saved games");
		item.setEnabled(false);
		item.addActionListener(new ActionListener()
       {
			@Override
			public void actionPerformed(ActionEvent e)
          {

			}
		});
		item.setEnabled(false);
		menu.add(item, saveGame);





		item = new JMenuItem("Exit Game");
		item.setToolTipText("Exits the game.");
		item.addActionListener(new ActionListener() 
      {
			@Override
			public void actionPerformed(ActionEvent e)
          {
				close();
			}
		});
		menu.add(item);

		menu.setMnemonic(KeyEvent.VK_F);

		menubar.add(menu);

		menu = new JMenu("Options");
		menu.setMnemonic(KeyEvent.VK_O);



		item = new JCheckBoxMenuItem("Rounded Corners");
		item.setMnemonic(KeyEvent.VK_R);
		((JCheckBoxMenuItem) item).setSelected(true);
		item.addActionListener(new ActionListener() 
      {
			@Override
			public void actionPerformed(ActionEvent e) 
         
         {
				gamePanel.toggleRoundedCorners();
			}
		});
		menu.add(item);
// to play the sound
		item = new JCheckBoxMenuItem("Sound");
		((JCheckBoxMenuItem) item).setSelected(true);
		item.addActionListener(new ActionListener()
       {
			@Override
			public void actionPerformed(ActionEvent e)
          {
				if (!(e.getSource() instanceof JCheckBoxMenuItem))
             {
					return;
				}

				JCheckBoxMenuItem checkbox = (JCheckBoxMenuItem) e.getSource();
				gamePanel.setSound(checkbox.isSelected());
			}
		});
		menu.add(item);

		menu.addSeparator();
// to show high scores
		item = new JMenuItem("High Scores");
		item.setEnabled(false);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		menu.add(item);

		removedTilesItem = new JMenuItem("Removed Tiles");
		removedTilesItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (removedFrame == null) {
					initRemovedFrame();
				}
				removedFrame.setVisible(true);
				removedTilesItem.setEnabled(false);
			}
		});
		menu.add(removedTilesItem);

		menubar.add(menu);
		setJMenuBar(menubar);

		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);

		item = new JMenuItem("Operation");
      
      
      
      
      
      
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				JTextArea text = new JTextArea();
				JScrollPane scrollPane = new JScrollPane(text);
				text.setText(operationString);
				text.setLineWrap(true);
            
            
            
				text.setWrapStyleWord(true);
				text.setTabSize(2);
				scrollPane.setPreferredSize(new Dimension(550, 350));
				panel.add(scrollPane);
				helpFrame.setTitle("Operation Instructions");
				helpFrame.add(panel);
            
            
				helpFrame.setVisible(true);
				helpFrame.setSize(600, 400);
			}
		});
		menu.add(item);
		item = new JMenuItem("Game Rules");
		item.addActionListener(new ActionListener()
       {
			@Override
			public void actionPerformed(ActionEvent e)
          {
				JPanel panel = new JPanel();
				JTextArea text = new JTextArea();
				JScrollPane scrollPane = new JScrollPane(text);
				text.setText(rulesString);
				text.setLineWrap(true);
				text.setWrapStyleWord(true);
				text.setTabSize(2);
				scrollPane.setPreferredSize(new Dimension(600, 400));
				panel.add(scrollPane);
				helpFrame.setTitle("Game Rules");
				helpFrame.add(panel);
				helpFrame.setVisible(true);
				helpFrame.setSize(580, 380);
			}
		});
		menu.add(item);

		menubar.add(menu);

		helpFrame.addWindowListener(new WindowAdapter()
       {
			@Override
			public void windowClosing(WindowEvent e) 
         {
				helpFrame.dispose();
				helpFrame = new JFrame();
				helpFrame.addWindowListener(this);
			}
		});

		add(gamePanel);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() 
      {
			@Override
			public void windowClosing(WindowEvent e)
          {
				int chosen =
						JOptionPane.showConfirmDialog(e.getComponent(),
						"Do you want to quit the game?",
						"Exit Game",
						JOptionPane.YES_NO_OPTION);
				if (chosen == 0) {
					System.exit(0);
				}
			}
		});

	setTitle(title + gamePanel.Gnumber);
		setResizable(false);
		setVisible(true);
	}

	private void initRemovedFrame()
    {
		removedFrame = new JFrame("Removed Tiles");
		JScrollPane scrollPane = new JScrollPane(gamePanel.getRemovedPanel());
		removedFrame.add(scrollPane);
      
		removedFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				removedTilesItem.setEnabled(true);
			}
		});
		gamePanel.resizeDeletedFrame();
	}

	public void checkEndGame() {
	

	}

	public void checkEnabledMenus() {
		JMenu menu = getJMenuBar().getMenu(0);
		menu.getMenuComponent(undoMove).setEnabled(gamePanel.canUndo());
		menu.getMenuComponent(redoMove)
				.setEnabled(gamePanel.RedoAbility());
	}

	private void close()
    {
		WindowEvent event = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
	}
	private void newGame()
    {
		newGame(null);
	}
	private void newGame(Long randomNumber)
    {
		boolean roundedCorners = gamePanel.hasRoundedCorners();
		remove(gamePanel);

		int width = getContentPane().getWidth();
		int height = getContentPane().getHeight();

		if (randomNumber == null)
       {
      
      
      
      
			gamePanel = new GamePanel(width, height, roundedCorners);
		}
		else {
			gamePanel = new GamePanel(width, height, roundedCorners, randomNumber);
		}

		add(gamePanel);

		checkEnabledMenus();

		setTitle(title + gamePanel.Gnumber);
		repaint();
	}

  private static void walk()
  
   {
    for (int i = 0; i < Integer.MAX_VALUE; i++)
      ;
  }
  

	public static void main(String[] args) {
   
   
   

   


		new MahjongBoard();
      
	}

}
