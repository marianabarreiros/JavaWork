

import java.awt.*;

public class CircleTile extends RankTile {

	public CircleTile(int rank) {
        super(rank);
		
		setToolTipText(toString());
    }
    
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int size;
		int xS;
		int yS;
		int distance;
		Color[] colors;
		
		switch (rank) {
		case 1:
			size = 50;
			g.setColor(GREEN);
			g.fillOval(13, 8, size, size);
			
			size = 13;
         
         
         
			g.setColor(ORANGE);
			g.fillOval(29, 26, size, size);
			drawSymbol(29, 26, size, g);
			

			size = 5;
			int nCircles = 12;
			int x1 = 35;
			int y1 = 32;
			double rotation = 2 * Math.PI / nCircles;
			
			for (int circle = 0; circle < nCircles; circle ++) {
				double position = circle * rotation;
				
				g.setColor(WHITE);
				g.fillOval((int) (x1 + Math.cos(position) * 19), 
						(int) (y1 + Math.sin(position) * 19), 
						size, size);
			}
			break;
         
         
		case 2:
			size = 22;
			xS = 25;
			yS = 9;
			distance = 3;
			
			drawVerticalCircles(size, 2, xS, yS, distance, new Color[] {GREEN, ORANGE}, g);
			break;
         
         
		case 3:
			size = 18;
			xS = 14;
			yS = 8;
			colors = new Color[] {BLUE, ORANGE, GREEN};
			
			drawDiagonalCircles(size, 3, xS, yS, -1, 0, colors, g);
			break;
         
         
		case 4:
			size = 22;
			xS = 16;
			yS = 9;
			distance = 3;
			
			drawVerticalCircles(23, 2, xS, yS, distance, new Color[]{BLUE, GREEN}, g);
			drawVerticalCircles(23, 2, xS + size, yS, distance, new Color[]{GREEN, BLUE}, g);			
			break;
         
         
		case 5:
			size = 18;
			xS = 13;
			yS = 4;
			distance = 3 + size;
			colors = new Color[] {BLUE, GREEN};
			
			drawVerticalCircles(size, 2, xS, yS, distance, colors, g);
			
			colors = new Color[] {ORANGE};
			drawVerticalCircles(size, 1, xS + size, yS + size + 2, distance, colors, g);
			
			colors = new Color[] {GREEN, BLUE};
			drawVerticalCircles(size, 2, xS + size * 2,yS,  distance, colors, g);
			break;
         
         
		case 6:
			size = 18;
			xS = 19;
			yS = 5;
			distance = 4;
			colors = new Color[] {GREEN, ORANGE, ORANGE};
			
			drawVerticalCircles(size, 3, xS, yS, distance, colors, g);
			drawVerticalCircles(size, 3, xS + size + 5, yS, distance, colors, g);
			break;
         
         
		case 7:
			size = 15;
			xS = 15;
			yS = 5;
			distance = -(size / 2) - 1;
			colors = new Color[] {GREEN};
			
			drawDiagonalCircles(size, 3, xS, yS, 5, distance, colors, g);
			
			xS = 19;
			yS = (HEIGHT - 9) / 2;
			distance = 4;
			colors = new Color[] {ORANGE};
			
			drawVerticalCircles(size, 2, xS, yS, distance, colors, g);
			drawVerticalCircles(size, 2, xS + size * 2, yS, distance, colors, g);
			break;
         
         
		case 8:
			size = 14;
			xS = 20;
			yS = 4;
			distance = 3;
			colors = new Color[] {BLUE};
			
			drawVerticalCircles(size, 4, xS, yS, distance, colors, g);
			drawVerticalCircles(size, 4, xS + size * 2, yS, distance, colors, g);
			break;
         
         
		case 9:
			size = 18;
			xS = 14;
			yS = 6;
			distance = 3;
			colors = new Color[] {GREEN, ORANGE, BLUE};
			
			drawVerticalCircles(size, 3, xS, yS, distance, colors, g);
			drawVerticalCircles(size, 3, xS + size, yS, distance, colors, g);
			drawVerticalCircles(size, 3, xS + size * 2, yS, distance, colors, g);
		default:
			break;
         
         
		}
	}
	
	private void drawVerticalCircles(int size, int numberOfRepeats, 
			int xS, int yS, int distance, 
			Color[] colors, Graphics g) {
		if (colors.length != numberOfRepeats) {
			if (colors.length == 1) {
				Color color = colors[0];
				colors = new Color[numberOfRepeats];
				for (int i = 0; i < numberOfRepeats; i++) {
					colors[i] = color;
				}
			}
			else {
				System.err.println("Cant Draw circles " );
				return;
			}
		}
		for (int i = 0; i < numberOfRepeats; i++) {
			int addY = distance * i + size * i;
			g.setColor(colors[i]);
			g.fillOval(xS, yS + addY, size, size);
			drawSymbol(xS, yS + addY, size, g);
		}
	}
	
	private void drawDiagonalCircles(int size, int nCircles,	int xS, int yS, int xSpacing, int ySpacing,
			Color[] colors, Graphics g) {
		
		if (colors == null || colors.length != nCircles) {
			if (colors.length == 1) {
				Color color = colors[0];
				colors = new Color[nCircles];
				for (int i = 0; i < nCircles; i++) {
					colors[i] = color;
               
               
				}
			}
			else {
				System.err.println(" unable to draw circles");
				return;
			}
		}
		
		for (int i = 0; i < nCircles; i++) {
			int x = xS + i * (size + xSpacing);
			int y = yS + i * (size + ySpacing);
         
         
			g.setColor(colors[i]);
			g.fillOval(x, y, size, size);
			drawSymbol(x, y, size, g);
         
		}
	}
	
	private void drawSymbol(int xS, int yS, int size, Graphics g) {
   
		int[] xPoints = {xS + 4, xS + (size / 3 * 2) + 3, xS + size / 2,
      
      
       
			xS + (size / 3) - 2, xS + size - 3};
		int[] yPoints = {yS + size / 3 + 1, yS + size - 4, yS + 2,
      
      
			yS + size - 4, yS + size / 3 + 1};
		
		g.setColor(WHITE);
		g.fillPolygon(xPoints, yPoints, 5);
	}
	
	@Override
    public String toString() {
        if (rank < 1 || rank > 9) {
            return "Invalid Circle Tile";
        }
        return "Circle " + rank;
    }
}
