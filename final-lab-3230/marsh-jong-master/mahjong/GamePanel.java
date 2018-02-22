import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.util.TimerTask;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Collections;
import java.util.Stack;
import java.util.List;
import java.awt.event.*;
import java.util.Timer;
import java.util.Random;
import java.util.Date;



public class GamePanel extends JPanel implements MouseListener {

	private static Random r;
	private Map<String, Tile> board = new HashMap<>();
	private Stack<Tile> removeTile = new Stack<>();
	private Stack<Tile> undo = new Stack<>();
   
	private String[] Backgronds = {"2.jpg", "3.jpg"};
	private String backgroundString;
  	private JPanel deletedPanel = null;
   
   private boolean tryHint = true;
	private Fireworks fireworks = null;
	private boolean initial = true;
   
   
	private boolean sound = true;
   private PlayClip playClip = new PlayClip("sounds/stone-scraping.wav");
	protected long Gnumber;
	private Tile chosenTile;



	public GamePanel(int width, int height) {
		this(width, height, true);
	}

	public GamePanel(int width, int height, boolean drawRound) {
		this(width, height, drawRound, null);
	}

	public GamePanel(int width, int height, Long rNumber) {
		this(width, height, true, rNumber);
	}

	public GamePanel(int width, int height, boolean drawRound,
			Long rNumber) {
		if (rNumber == null) {
			rNumber = new Date().getTime() % 1000000;
         
		}
      
      
      
      
      
		Gnumber = rNumber;
		r = new Random(rNumber);
		init(width, height, drawRound);
	}
// i changed the original sound because i don't like it
	protected boolean getSound() {
		return sound;
	}
	protected void setSound(boolean sound) {
		this.sound = sound;
	}

	protected int getRemovedTileCount() {
		return removeTile.size();
	}
   
   
   
   
   
   

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
         int trying = 8;
		if (backgroundString != null) {
      
      
			ImageIcon image = new ImageIcon(getClass().getResource(backgroundString));
			int xDiff = getWidth() - image.getIconWidth();
			int yDiff = getHeight() - image.getIconHeight();
			if (xDiff < 0 || yDiff < 0) {
				int diff = xDiff - yDiff;
            
				image = new ImageIcon(image.getImage().getScaledInstance(diff >= 0 ? getWidth() : -1, diff < 0 ? getHeight() : -1, Image.SCALE_DEFAULT));
			}
			g.drawImage(image.getImage(), getWidth() / 2 - image.getIconWidth() / 2, getHeight() / 2 - image.getIconHeight() / 2, this);
		}

		newdraw();
	}


		private void init(int width, int height, boolean drawRound) {
		setLayout(null);
      
		setSize(width, height);
// get the images
		int n = r.nextInt(Backgronds.length);
		backgroundString = "images/" + Backgronds[n];

		List<Tile> squares = initSquares(drawRound);

		int xPos = 0;
		int yPos = 0;
		int zPos = 0;

		for (Tile tile : squares) {
			tile.setSize(Tile.WIDTH, Tile.HEIGHT);
         
			tile.xPos = xPos;
         
			tile.yPos = yPos;
         
			tile.zPos = zPos;
         

			tile.addMouseListener(this);
			board.put(getKey(xPos, yPos, zPos), tile);

			int[] newPositions = getNewPositions(xPos, yPos, zPos);
         
			xPos = newPositions[0];
         
			yPos = newPositions[1];
         
			zPos = newPositions[2];
		}

		addMouseListener(this);
	}


	private int[] getNewPositions(int currentXPosition, int currentYPosition, int currentZPosition) {
		int maximumX;
		int maximumY;

		switch (currentZPosition) {
      
			case 0:
				maximumX = 0;
				maximumY = 0;
				break;
            
			case 1:
				maximumX = 1;
				maximumY = 1;
				break;
            
			case 2:
				maximumX = 2;
				maximumY = 2;
				break;
            
			case 3:
				maximumX = 3;
				maximumY = 3;
				break;
            
			case 4:
				maximumY = 4;
				switch (Math.abs(currentYPosition)) {
            
					case 1:
               
					case 4:
						maximumX = 6;
						break;
					case 2:
						maximumX = 5;
						break;
					case 3:
						maximumX = 4;
						break;
					default:
						maximumX = 0;
				}
				break;
			default:
				maximumX = 0;
				maximumY = 0;
				break;
		}

		int absXPos = Math.abs(currentXPosition);
		int absYPos = Math.abs(currentYPosition);

		if (currentZPosition == 0) {
			currentXPosition = 1;
			currentYPosition = 1;
			currentZPosition = 1;
		} 
		else {
			if (currentYPosition != 0 && absXPos < maximumX) {

				currentXPosition += currentXPosition / absXPos;
			} 
         
			else if (currentYPosition != 0 && absYPos < maximumY) {
				currentYPosition += currentYPosition / absYPos;
				currentXPosition = currentXPosition / absXPos;
            
			} else if (currentXPosition > 0 && currentYPosition > 0) {
				currentXPosition = 1;
				currentYPosition = -1;
            
			} else if (currentXPosition > 0 && currentYPosition < 0) {
				currentXPosition = -1;
				currentYPosition = -1;
            
			} else if (currentXPosition < 0 && currentYPosition < 0) {
				currentXPosition = -1;
				currentYPosition = 1;
            
			} else if (currentXPosition < 0 && currentYPosition > 0 && currentZPosition <= 4) {
				currentXPosition = 1;
				currentYPosition = 1;
            
				currentZPosition++;
			} 
         // need fix
			else if (currentZPosition == 4 && currentYPosition == 0) {
				switch (currentXPosition) {
					case -7:
						currentXPosition = 7;
						break;
					case 7:
						currentXPosition = 8;
						break;
					default:
						break;
				}
			}

			if (currentZPosition == 5) {
				currentXPosition = -7;
				currentYPosition = 0;
				currentZPosition = 4;
			}
		}

		int[] positions = new int[]{currentXPosition, currentYPosition, currentZPosition};
		return positions;
	}

		private List<Tile> initSquares(Boolean drawRound) {
		List<Tile> squares = new ArrayList<>();

		for (int i = 0; i < 4; i++) {

			// Tiles
			squares.add(new CharacterTile('1'));
			squares.add(new CharacterTile('2'));
			squares.add(new CharacterTile('3'));
			squares.add(new CharacterTile('4'));
			squares.add(new CharacterTile('5'));
			squares.add(new CharacterTile('6'));
			squares.add(new CharacterTile('7'));
			squares.add(new CharacterTile('8'));
			squares.add(new CharacterTile('9'));



			squares.add(new CircleTile(1));
			squares.add(new CircleTile(2));
			squares.add(new CircleTile(3));
			squares.add(new CircleTile(4));
			squares.add(new CircleTile(5));
			squares.add(new CircleTile(6));
			squares.add(new CircleTile(7));
			squares.add(new CircleTile(8));
			squares.add(new CircleTile(9));


			squares.add(new CharacterTile('N'));
			squares.add(new CharacterTile('S'));
			squares.add(new CharacterTile('E'));
			squares.add(new CharacterTile('W'));

			squares.add(new CharacterTile('C'));
			squares.add(new CharacterTile('F'));
			squares.add(new WhiteDragonTile());

			squares.add(new Bamboo1Tile());
			squares.add(new BambooTile(2));
			squares.add(new BambooTile(3));
			squares.add(new BambooTile(4));
			squares.add(new BambooTile(5));
			squares.add(new BambooTile(6));
			squares.add(new BambooTile(7));
			squares.add(new BambooTile(8));
			squares.add(new BambooTile(9));


		}

		squares.add(new FlowerTile("Chrysanthemum"));
		squares.add(new FlowerTile("Orchid"));
		squares.add(new FlowerTile("Plum"));
		squares.add(new FlowerTile("Bamboo"));
		squares.add(new SeasonTile("Spring"));
		squares.add(new SeasonTile("Summer"));
		squares.add(new SeasonTile("Fall"));
		squares.add(new SeasonTile("Winter"));

		Collections.shuffle(squares, r);

		if (drawRound != null) {
			for (Tile tile : squares) {
				tile.drawRound = drawRound;
			}
		}

		return squares;
	}

	protected void toggleRoundedCorners() {
		for (Tile tile : board.values()) {
      
			tile.drawRound = !tile.drawRound;
		}
		Stack<Tile> tempStack = removeTile;
		for (Tile tile : tempStack) 
      {
			tile.drawRound = !tile.drawRound;
		}

		repaint();
	}

	protected boolean hasRoundedCorners() 
   {
		if (board.values() != null && board.values().size() > 0) 
      {
			for (Tile tile : board.values()) 
         {
				return tile.drawRound;
			}
		}

		return false;
	}


	private void newdraw() {
		List<Tile> squares = new ArrayList<>();
		squares.addAll(board.values());
		Collections.sort(squares);

		for (Tile tile : squares) {
			if (!initial && !tile.isZ)
          {
				continue;
			}
			tile.place(getWidth(), getHeight());
			add(tile);
			tile.isZ = false;
		}

		if (!initial)
       {
			int newZOrder = 0;
			for (Tile tile: squares) {
				setComponentZOrder(tile, newZOrder++);
			}
		}
		initial = false;
	}


	private String getKey(int xPos, int yPos, int zPos) {
		return xPos + "_" + yPos + "_" + zPos;
	}

		public Tile deleteTile(int xPos, int yPos, int zPos) 
      {
      
		Tile tile = board.remove(getKey(xPos, yPos, zPos));
		int sSize = removeTile.size();
		if (tile == null) {
			System.err.println("There is no Tile!!!");
		} else {
			if (sound) {
				playClip.play();
			}
			tile.isZ = true;
			removeTile.push(tile);
         
			remove(tile);
         
			newdraw();
			repaint();
		}
		if (sSize % 2 != 0 && removeTile.size() > sSize && deletedPanel != null) {
			deletedPanel.removeAll();
			for (Tile t: removeTile) {
				deletedPanel.add(t, 0);
			}
			resizeDeletedFrame();
			deletedPanel.revalidate();
		}
		return tile;
	}

	protected void resizeDeletedFrame() {
			int countTile = getRemovedTileCount() <= 0 ? 1 : getRemovedTileCount();
         
         
         
			int height = countTile * (Tile.HEIGHT + 119 / countTile) / 2 + 48;
         
			height = height < Tile.HEIGHT + 48 ? Tile.HEIGHT + 48 :
				height > 450 ? 501 : height;
			deletedPanel.getTopLevelAncestor().setSize(249, height);

			if (height > 499) {
				deletedPanel.getTopLevelAncestor().revalidate();
			}
	}
		public Tile deleteTile(Tile tile) {
		if (tile == null) {
			System.err.println("No tile to remove.");
			return null;
		}
		if (tile.xPos == null || tile.yPos == null || tile.zPos == null) {
			System.err.println("no tile placed yet.");
			return null;
		}
		return deleteTile(tile.xPos, tile.yPos, tile.zPos);
	}

		public boolean undo() {
		try {
			Tile tile1 = removeTile.pop();
         
			Tile tile2 = removeTile.pop();
         
			board.put(getKey(tile1.xPos, tile1.yPos, tile1.zPos), tile1);
         
			board.put(getKey(tile2.xPos, tile2.yPos, tile2.zPos), tile2);
         
			undo.push(tile2);
			undo.push(tile1);
			newdraw();
         
			repaint();
		} catch (EmptyStackException e) {
			return false;
		}

		((MahjongBoard) getTopLevelAncestor()).checkEnabledMenus();

		if (getRemovedTileCount() > 0 && deletedPanel != null) {
			deletedPanel.removeAll();
         
			for (Tile t: removeTile)
          {
				deletedPanel.add(t, 0);
			}
			resizeDeletedFrame();
			deletedPanel.revalidate();
		}

		return true;
	}

// extra credit
	public void redo() {
		try {
			Tile tile1 = undo.pop();
			Tile tile2 = undo.pop();
			deleteTile(tile1);
			deleteTile(tile2);

			((MahjongBoard) getTopLevelAncestor()).checkEnabledMenus();

			resizeDeletedFrame();
		} catch (EmptyStackException e) {
			System.err.println("No more redos");
		}
	}


	public boolean hint(boolean highlight) {
		return hint(highlight, true);
	}

	
	public boolean hint(boolean highlight, boolean bestMove) {
		for (Tile tile : board.values()) {
			if (! ifOpen(tile)) {
				continue;
			}
			for (Tile tile2 : board.values()) {
				if (tile == tile2) {
					break;
				}
			
			}
		}
		return false;
	}

	
	 
	public Tile getTile(int xPos, int yPos, int zPos) {
		return board.get(getKey(xPos, yPos, zPos));
	}

	public boolean  ifOpen(int xPos, int yPos, int zPos) {
		if (getTile(xPos, yPos, zPos - 1) != null) {
			return false;
		}

		boolean ifLeftOpen = getTile(xPos - 1, yPos, zPos) == null
				&& (xPos != 1 || getTile(xPos - 2, yPos, zPos) == null);
		boolean ifRightOpen = getTile(xPos + 1, yPos, zPos) == null
				&& (xPos != -1 || getTile(xPos + 2, yPos, zPos) == null);
		boolean ifMiddleY = Math.abs(yPos) == 1;
		boolean ifYZero = yPos == 0;
      
      
		if (ifLeftOpen && ifMiddleY) {
			ifLeftOpen = getTile(xPos - 1, 0, zPos) == null;
		} else if (ifLeftOpen && ifYZero) {
			ifLeftOpen = getTile(xPos - 1, 1, zPos) == null
					&& getTile(xPos - 1, -1, zPos) == null;
		}
		if (ifRightOpen && ifMiddleY) {
			ifRightOpen = getTile(xPos + 1, 0, zPos) == null;
		}

		if (ifMiddleY && Math.abs(xPos) == 1 && getTile(0, 0, 0) != null) {
			return false;
		}
		if (!ifLeftOpen && !ifRightOpen) {
			return false;
		}

		if (Math.abs(xPos) == 1) {
		}

		return true;
	}

	public boolean  ifOpen(Tile tile) {
		if (tile == null
				|| tile.xPos == null || tile.yPos == null || tile.zPos == null) {
			System.err.println("Invalid.");
			return false;
		}

		return  ifOpen(tile.xPos, tile.yPos, tile.zPos);
	}


	public boolean RedoAbility() {
		return !undo.isEmpty();
	}


	public boolean canUndo() {
		return !removeTile.isEmpty();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger() && !(e.getSource() instanceof Tile)) {
			JPopupMenu showUP = new JPopupMenu();

			JMenuItem theMenu = new JMenuItem("Undo");
			if (canUndo()) {
				theMenu.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						undo();
					}
				});
			}
			theMenu.setEnabled(canUndo());
			showUP.add(theMenu);
// extra credit
			theMenu = new JMenuItem("Redo");
			if (RedoAbility()) {
				theMenu.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						redo();
					}
				});
			}
			theMenu.setEnabled(RedoAbility());
			showUP.add(theMenu);

			showUP.addSeparator();

			theMenu = new JMenuItem("Hint");

			showUP.add(theMenu);

			showUP.show(this, e.getX(), e.getY());
			return;
		} else if (e.isPopupTrigger()) {
			return;
		}
      
      
      
		Object source = e.getSource();
		if (source instanceof Tile &&  ifOpen((Tile) source))
       {
			Tile tile = (Tile) source;
			undo.clear();
			if (chosenTile != null && chosenTile != tile
					&& chosenTile.matches(tile))
                {
				deleteTile(tile);
				chosenTile.highlight(false);
				deleteTile(chosenTile);
				((MahjongBoard) getTopLevelAncestor()).checkEnabledMenus();
				if (board.isEmpty()) 
            {
					fireworks = new Fireworks(this);
					fireworks.setSound(sound);
					fireworks.fire();
				}
				if (!hint(false)) {
					((MahjongBoard) getTopLevelAncestor()).checkEndGame();
				}
				chosenTile = null;
			} else if (chosenTile == tile) {
				chosenTile.highlight(false);
				chosenTile = null;
				repaint();
			} else if (chosenTile != null) {
				chosenTile.highlight(false);
				chosenTile = tile;
				tile.highlight(true);
				repaint();
			} else {
				chosenTile = tile;
				chosenTile.highlight(true);
				repaint();
			}
		} else if (!(source instanceof Tile))
       {
			if (chosenTile != null) 
         {
				chosenTile.highlight(false);
				chosenTile = null;
            
				repaint();
			}
		}
	}

	protected void stopFireworks() 
   {
		if (fireworks != null) 
      {
			fireworks.stop();
			fireworks = null;
		}
	}

	protected JPanel getRemovedPanel() 
   {
		if (deletedPanel == null)
       {
			GridLayout layout = new GridLayout(0, 2, 5, 5);
			deletedPanel = new JPanel(layout);
			for (Tile tile: removeTile) {
				deletedPanel.add(tile, 0);
			}
		}
		return deletedPanel;
	}

	protected boolean Hint(Container parent)
    {
		if (!tryHint)
       {
      
			int chosen = JOptionPane.showConfirmDialog(parent,
					"do you want to use hint?",
					"Use Hints?", JOptionPane.YES_NO_OPTION);
			if (chosen == 0)
          {
				tryHint = true;
			}
			return tryHint;
		}
		else {
			return tryHint;
		}
	}
}
