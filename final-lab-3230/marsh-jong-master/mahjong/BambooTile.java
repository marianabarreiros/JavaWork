


import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class BambooTile extends RankTile 

{

	@SuppressWarnings("not used")
	private final int  xSize = 8;
	private final int  ySize = 20;
	
    public BambooTile(int rank) {
        super(rank);
		
		setToolTipText(toString());
    }
    
	@Override
	protected void paintComponent(Graphics g)
    {
		super.paintComponent(g);
				int yS;

		int xS;
		Color[] colors;
		
		switch (rank) 
      {
      
		case 2:
			xS = (WIDTH - 9) / 2 + 9;
			yS = 16;
			colors = new Color[] {GREEN, BLUE};
			
			drawVerticalBamboo(2, xS, yS, colors, g);
			break;
         
		case 3:
			xS = (WIDTH - 9) / 2 + 3;
			yS = 34;
			drawBamboo(xS, yS, GREEN, g);
			drawBamboo(xS + 20, yS, GREEN, g);
			
			drawBamboo(xS + 10, yS - 20, BLUE, g);
			break;
         
		case 4:
			xS = (WIDTH - 9) / 2;
			yS = 15;
			colors = new Color[] {BLUE, GREEN};
			
			drawVerticalBamboo(2, xS, yS, colors, g);
			
			xS = (WIDTH - 9) / 2 + 19;
			colors = new Color[] {GREEN, BLUE};
			
			drawVerticalBamboo(2, xS, yS, colors, g);
			break;
         
		case 5:
			xS = 19;
			yS = 14;
			colors = new Color[] {GREEN, BLUE};
			
			drawVerticalBamboo(2, xS, yS, colors, g);
			
			xS = 48;
			colors = new Color[] {BLUE, GREEN};
			
			drawVerticalBamboo(2, xS, yS, colors, g);
			
			xS = 36;
			yS = 25;
			
			drawBamboo(xS, yS, ORANGE, g);
			break;
         
		case 6:
			xS = 19;
			yS = 14;
			colors = new Color[] {GREEN, BLUE};
			
			for (int i = 0; i < 3; i++) {
				drawVerticalBamboo(2, xS + 15 * i, yS, colors, g);
			}
			break;
         
		case 7:
			xS = 21;
			yS = 24;
			colors = new Color[] {GREEN};
			
			drawVerticalBamboo(2, xS, yS, colors, g);
			drawVerticalBamboo(2, xS + 30, yS, colors, g);
			
			xS = 36;
			yS = 2;
			colors = new Color[] {ORANGE, BLUE, BLUE};
			
			drawVerticalBamboo(3, xS, yS, colors, g);
			break;
         
		case 8:
			xS = 14;
			yS = 14;
			colors = new Color[] {GREEN, BLUE};
			
			drawVerticalBamboo(2, xS, yS, colors, g);
			drawVerticalBamboo(2, xS + 40, yS, colors, g);
			drawTiltedBamboo(xS + 9, yS, 45, GREEN, g);
			drawTiltedBamboo(xS + 3, yS + 5, -45, GREEN, g);
			drawTiltedBamboo(xS + 6, yS + 20, 45, BLUE, g);
			drawTiltedBamboo(xS - 33, yS + 25, -45, BLUE, g);
			break;
         
         
		case 9:
			xS = 19;
			yS = 2;
			colors = new Color[] {ORANGE};
			
			drawVerticalBamboo(3, xS, yS, colors, g);
			
			xS = 34;
			colors = new Color[] {BLUE};
			
			drawVerticalBamboo(3, xS, yS, colors, g);
			
			xS = 49;
			colors = new Color[] {GREEN};
			
			drawVerticalBamboo(3, xS, yS, colors, g);
			break;
         
         
         
		default:
			break;
         
         
		}
	}
	
	private void drawVerticalBamboo(int numberOfBamboo, int xS, int yS,
			Color[] colors, Graphics g) 
         {
		if (colors.length != numberOfBamboo)
       {
			if (colors.length == 1) {
				Color color = colors[0];
				colors = new Color[numberOfBamboo];
				for (int i = 0; i < numberOfBamboo; i++) 
            {
					colors[i] = color;
				}
			}
			else {
				System.err.println("Invalid number of colors, cannot create bamboo");
				return;
			}
		}
		
		int size =  ySize;
		for (int i = 0; i < numberOfBamboo; i++) {
			drawBamboo(xS, yS + size * i, colors[i], g);
		}
	}
	
	private void drawBamboo(int xS, int yS, Color color, Graphics g) {
		g.setColor(color);
		g.fillRect(xS + 2, yS + 1, 4, 15);
		g.fillOval(xS, yS, 8, 4);
		g.fillOval(xS, yS + 7, 8, 4);
		g.fillOval(xS, yS + 14, 8, 4);
		g.setColor(color.equals(WHITE) ? BLACK : WHITE);
		g.drawLine(xS + 4, yS + 3, xS + 4, yS + 6);
		g.drawLine(xS + 4, yS + 11, xS + 4, yS + 14);
	}
	private void drawTiltedBamboo(int xS, int yS, int slope, Color color, Graphics g)
    {
		g.setColor(color);
		Graphics2D graphics = (Graphics2D) g;
		
		Rectangle body = new Rectangle(slope < 0 ? 2 : 3, 1, 4, 9 + 6);
      
		Ellipse2D top = new Ellipse2D.Float(0, 0, 8, 4);
		Ellipse2D middle = new Ellipse2D.Float(0, 7, 8, 4);
		Ellipse2D bottom = new Ellipse2D.Float(0, 14, 8, 4);
      
		Line2D topLine = new Line2D.Float(slope  < 0 ? 4 : 5, 3, 4, 6);
		Line2D bottomLine = new Line2D.Float(slope < 0 ? 4 : 5, 11, 4, 14);
		
      
		graphics.translate(xS + 10, yS);
		graphics.rotate(Math.toRadians(slope));
		graphics.fill(body);
		graphics.fill(top);
      
		graphics.fill(middle);
		graphics.fill(bottom);
		graphics.setColor(color.equals(WHITE) ? BLACK : WHITE);
      
		graphics.draw(topLine);
		graphics.draw(bottomLine);
		
		graphics.rotate(Math.toRadians(-slope));
		graphics.translate(-xS, -yS);
	}
	
	@Override
    public String toString()
     {
        if (rank < 2 || rank > 9)
         {
            return "Invalid Bamboo Tile";
        }
        return "Bamboo " + rank;
    }
}
