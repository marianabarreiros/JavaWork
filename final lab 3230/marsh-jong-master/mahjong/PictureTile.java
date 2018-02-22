


import java.awt.*;

import javax.swing.*;

abstract public class PictureTile extends Tile{
	
	private String n;
    
    public PictureTile(String n) {
        this.n = n;
		
		setToolTipText(toString());
    }
 
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//background
      
      
		ImageIcon image = new ImageIcon(getClass().getResource("images/" + n + ".png"));
		image = new ImageIcon(
				image.getImage().getScaledInstance(
						(int) (image.getIconWidth() * .9), 
						(int) (image.getIconHeight() * .9), 
						Image.SCALE_DEFAULT));
		
		int x = (WIDTH - image.getIconWidth()) / 2 + 9;
		int y = (HEIGHT - image.getIconHeight()) / 2 + 1;
		g.drawImage(image.getImage(), x, y, this);
      
	}
	
	@Override
    public String toString() {
        String[] nPass = { "Spring", "Plum",  "Summer", "Fall","Chrysanthemum", "Orchid",  "Bamboo", "Winter"};
    
        int index = -1;
        for (int i = 0; i < nPass.length; i++) 
        {
        
            if (nPass[i].equals(n)) 
            {
                index = i;
                break;
            }
        }
        
        if (index < 0) {
            return "invalid ";
        }
        return n;
    }
}
