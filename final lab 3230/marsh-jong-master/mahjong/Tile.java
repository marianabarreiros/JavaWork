


import java.awt.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

abstract public class Tile extends JPanel implements Comparable<Tile> {

   protected static final int S = 10;	
	protected static final int WIDTH = 55;
	protected static final int HEIGHT = 70;
	
	protected static final int FW = WIDTH - S;
	protected static final int FH = HEIGHT - S;

	protected boolean drawRound = true;
	protected Integer zPos = null;

	protected Integer xPos = null;
	protected Integer yPos = null;
   	protected final Color MIX_COLOR = new Color(129, 109, 111);

	protected final Color IVORY = new Color(244, 244, 217);

	protected final Color BLACK = Color.BLACK;
   	protected final Color LILAC = new Color(255, 175, 244);
	protected final Color HIGHLIGHT = LILAC;
	protected final Color GREEN = new Color(0, 149, 0);
   	protected final Color BLUE = Color.BLUE;
	protected final Color YELLOW = Color.YELLOW;

	protected final Color ORANGE = Color.ORANGE;
   	protected final Color WHITE = Color.WHITE;


	protected final Color tryHint = GREEN;

	private boolean ifChosen = false;
	private boolean isHint = false;
	protected boolean isZ = false;

	public boolean matches(Tile Tile2) {
   
		return Tile2 != null && Tile2.getClass() == this.getClass();
	}

	@Override
	protected void paintComponent(Graphics g) {
   
		super.paintComponent(g);

		setSize(WIDTH + 11, HEIGHT + 6);
		setOpaque(false);
      
		setPreferredSize(new Dimension(61, 71));
      

		if (drawRound) {
			paintRound(g);
         
         
		} else {
      
      
			paintSquare(g);
		}
	}

	protected void highlight(boolean ifChosen) {
   
		this.ifChosen = ifChosen;
		revalidate();
	}

	protected void tryinghint(boolean enableHint) {
   
		this.isHint = enableHint;
		revalidate();
	}

	protected void place(int parentWidth, int parentHeight) {
   
   
   
		if (xPos == null || yPos == null || zPos == null) {
      
      
			setLocation(parentWidth / 2 - WIDTH / 2, parentHeight / 2 - HEIGHT / 2);
			return;
		}

		int xB = parentWidth / 2 - WIDTH / 2;
		int yB = parentHeight / 2 - HEIGHT / 2;
		int offsetX = (4 - zPos) * S;
      
      
      
		int offsetY = offsetX * -1;
		int xTile = WIDTH * xPos - (WIDTH / 2 + 1) * (xPos != 0 ? xPos / Math.abs(xPos) : xPos);
		int yTile = (HEIGHT - 5) * yPos * -1 + (HEIGHT / 2 - 3) * (yPos != 0 ? yPos / Math.abs(yPos) : yPos);
		int xL = xB + offsetX + xTile;
      
      
      
		int yL = yB + offsetY + yTile;

		setLocation(xL, yL);
	}

	@Override
	public int compareTo(Tile Tile2)
    {
		if (this == Tile2) {
			return 0;
		}


		if (xPos == null || Tile2.xPos == null) 
      {
			return 0;
		} else if (yPos == null || Tile2.yPos == null) 
      {
			return 0;
		} else if (zPos == null || Tile2.zPos == null)
       {
			return 0;
		}


		int CC = zPos.compareTo(Tile2.zPos);
		if (CC != 0) {
			return CC;
		}


		if (yPos.equals(Tile2.yPos)
				|| (Math.abs(yPos) == 1 && Tile2.yPos == 0)
            
            
            
				|| (Math.abs(Tile2.yPos) == 1 && yPos == 0)) 
            {
			CC = xPos.compareTo(Tile2.xPos);
		} else {
			CC = yPos.compareTo(Tile2.yPos);
		}

		return CC;
	}

	@Override
	public boolean equals(Object otherObject)
    {
		if (this == otherObject)
       {
			return true;
		}
		if (otherObject == null || !getClass().equals(otherObject.getClass())) 
      {
			return false;
		}
		Tile Tile2 = (Tile) otherObject;
		if (xPos == null || Tile2.xPos == null) 
      {
			return false;
		} else if (yPos == null || Tile2.yPos == null) 
      {
			return false;
         
         
		} else if (zPos == null || Tile2.zPos == null) {
			return false;
		}

		return xPos.equals(Tile2.xPos) && yPos.equals(Tile2.yPos)
				&& zPos.equals(Tile2.zPos);
	}

	@Override
	public int hashCode() {
		int hash = 1;
		if (xPos == null || yPos == null || zPos == null) 
      {
			return hash;
		}
      
		hash = hash * 11 + xPos.hashCode();
		hash = hash * 13 + yPos.hashCode();
      
		hash = hash * 17 + zPos.hashCode();

		return hash;
	}

	private void paintSquare(Graphics g) 
   {
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint paint = new GradientPaint(0, HEIGHT, BLUE, 9, 0, ORANGE);

		g2.setPaint(paint);
      
      
		g2.fillPolygon(new int[]{0, 6, 6, 0}, new int[]{16, 6, HEIGHT, HEIGHT + 6}, 4);
		g2.setColor(LILAC);
      
      
		g2.fillPolygon(new int[]{0, 5, WIDTH + 5, WIDTH},
				new int[]{HEIGHT + 6, HEIGHT, HEIGHT, HEIGHT + 6}, 4);

		g2.setColor(MIX_COLOR);
      
      
		g2.drawPolygon(new int[]{0, 6, 6, 0}, new int[]{11, 6, HEIGHT, HEIGHT + 6}, 4);
		g2.drawPolygon(new int[]{0, 5, WIDTH + 5, WIDTH},
				new int[]{HEIGHT + 6, HEIGHT, HEIGHT, HEIGHT + 6}, 4);

		paint = new GradientPaint(0, HEIGHT, WHITE, 10, 0, ORANGE);

		g2.setPaint(paint);
      
      
      
		g2.fillPolygon(new int[]{6, 11, 11, 6}, new int[]{6, 0, HEIGHT - 4, HEIGHT}, 4);
		g2.setColor(IVORY);
		g2.fillPolygon(new int[]{6, 11, WIDTH + 11, WIDTH + 6},
				new int[]{HEIGHT, HEIGHT - 5, HEIGHT - 6, HEIGHT}, 4);

		g2.setColor(MIX_COLOR);
      
      
      
		g2.drawPolygon(new int[]{5, 11, 11, 6}, new int[]{6, 0, HEIGHT - 5, HEIGHT}, 4);
		g2.drawPolygon(new int[]{5, 11, WIDTH + 9, WIDTH + 6},
				new int[]{HEIGHT, HEIGHT - 5, HEIGHT - 5, HEIGHT}, 4);

		paint = new GradientPaint(WIDTH, 0, WHITE, 11, HEIGHT, ifChosen ? HIGHLIGHT : isHint ? tryHint : IVORY);
		g2.setPaint(paint);

		g2.fillRect(11, 0, WIDTH, HEIGHT - 4);
		g2.setColor(MIX_COLOR);
		g2.drawRect(11, 0, WIDTH, HEIGHT - 4);
	}

	private void paintRound(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint paint = new GradientPaint(0, HEIGHT, WHITE, 6, 6, BLUE);

		g2.setPaint(paint);
      
      
      
		g2.fillPolygon(new int[]{0, 6, 6, 0}, new int[]{14, 11, HEIGHT - 4, HEIGHT}, 4);
		paint = new GradientPaint(5, HEIGHT - 5, WHITE, 5, HEIGHT + 5, LILAC);
		g2.setPaint(paint);
      
      
      
		g2.fillPolygon(new int[]{1, 5, 9, 5}, new int[]{HEIGHT, HEIGHT - 4, HEIGHT, HEIGHT + 4}, 4);
		g2.setColor(LILAC);
      
      
		g2.fillPolygon(new int[]{5, 10, WIDTH, WIDTH - 5}, new int[]{HEIGHT + 5, HEIGHT, HEIGHT, HEIGHT + 5}, 4);

		g2.setColor(Color.WHITE);
		g2.drawRoundRect(0, 11, WIDTH, HEIGHT - 5, 14, 14);

		paint = new GradientPaint(4, HEIGHT - 4, WHITE, 10, 0, ORANGE);
		g2.setPaint(paint);
		g2.fillPolygon(new int[]{5, 10, 11, 5}, new int[]{11, 6, HEIGHT - 10, HEIGHT - 5}, 4);
		paint = new GradientPaint(10, HEIGHT - 11, WHITE, 11, HEIGHT, ORANGE);
		g2.setPaint(paint);
		g2.fillPolygon(new int[]{6, 9, 14, 9}, new int[]{HEIGHT - 5, HEIGHT - 9, HEIGHT - 5, HEIGHT - 1}, 4);
		g2.setColor(IVORY);
      
      
		g2.fillPolygon(new int[]{11, 14, WIDTH + 4, WIDTH}, new int[]{HEIGHT, HEIGHT - 4, HEIGHT - 5, HEIGHT}, 4);

		g2.setColor(Color.ORANGE);
		g2.drawRoundRect(5, 5, WIDTH, HEIGHT - 4, 14, 14);

		paint = new GradientPaint(WIDTH, 0, WHITE, 10, HEIGHT, ifChosen ? HIGHLIGHT : isHint ? tryHint : IVORY);
		g2.setPaint(paint);

		g2.fillRoundRect(11, 2, WIDTH - 2, HEIGHT - 5, 17, 17);

		g2.setColor(Color.ORANGE);
      
      
		g2.drawRoundRect(11, 0, WIDTH, HEIGHT - 5, 16, 16);
	}
}
