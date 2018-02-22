


import java.awt.*;

public class WhiteDragonTile extends Tile {

	public WhiteDragonTile()
    {
		setToolTipText(toString());
	}
	
	@Override
	protected void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		
		g.setColor(BLUE);
		int x1;
		int y1 = 9;
		int x2;
		int y2 = HEIGHT - 14;
		for (x1 = 20; x1 <= WIDTH - 6; x1 += 12) {
			g.fillRect(x1, y1, 7, 5);
			x2 = x1 + 6;
			if (x2 != WIDTH) {
				g.fillRect(x2, y2, 6, 5);
			}
		}
		
		g.setColor(BLACK);
		for (x1 = 26; x1 <= WIDTH; x1 += 12)
       {
			if (x1 != WIDTH) {
				g.drawRect(x1, y1, 7, 4);
			}
			x2 = x1 - 6;
			g.drawRect(x2, y2, 7, 4);
		}
		
		x1 = 20;
		x2 = WIDTH - 1;
      
      
		for (y1 = 14; y1 < HEIGHT - 19; y1 += 10)
       {
			g.drawRect(x1, y1, 4, 6);
			y2 = y1 + 5;
			g.drawRect(x2, y2, 4, 6);
		}
		
		g.setColor(BLUE);
		for (y1 = 19; y1 < HEIGHT - 14; y1 += 10) 
      
      
      {
			g.fillRect(x1, y1, 5, 7);
			y2 = y1 - 5;
         
         
			g.fillRect(x2, y2, 5, 7);
		}
	}
	
	@Override
    public String toString() {
        return "White Dragon";
    }
}
