import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;



public class Arch extends JFrame
{
//private Image image;				//awt
	private ImageIcon 	image; 		//swing



 public Arch()

{
	 setTitle("Arch");
	 //setSize(600, 400);
	 
	 setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	 addWindowListener(new WindowAdapter()
			 {public void windowClosing(WindowEvent e)
		// {System.exit(0);}
		 {close();}
		 
			 });
			 
	 /*
	 try
	 {
		 
		 URL		url = new URL("https://www.nps.gov/arch/learn/photosmultimedia/upload/arches11.jpg");
		 image = Toolkit.getDefaultToolkit().getImage(url);
	 }
	 
	 catch(MalformedURLException murle)
	 {
		 JOptionPane.showMessageDialog(this,  "BAD URL: " + murle,  "Image Error", JOptionPane.ERROR_MESSAGE);
		 
		 
	 }
	 
	 */
	 //image = Toolkit.getDefaultToolkit().getImage("images/arches11.jpg");
	 image = new ImageIcon("images/arches3.jpg");
	 /*
	 MediaTracker	 tracker = new MediaTracker(this);
	 tracker.addImage(image, 0);
	 
	 try
	 {
		tracker.waitForAll(); 
		
	 }
	 catch(InterruptedException ie)
	 {
		 JOptionPane.showMessageDialog(this, "Unable to load Image", "Image Error" ,JOptionPane.ERROR_MESSAGE);
		 
		 
	 }
	 */
	// image = image.getScaledInstance((int)(image.getHeight(this) * 1.5), -1, Image.SCALE_SMOOTH);
//image = new ImageIcon( image.getImage().getScaledInstance((int)(image.getIconWidth() * 1.5), -1,
		//Image.SCALE_SMOOTH));
// tracker.addImage(image, 0);
//	 
//	 try
//	 {
//		tracker.waitForAll(); 
//		
//	 }
//	 catch(InterruptedException ie)
//	 {
//		 JOptionPane.showMessageDialog(this, "Unable to load Image", "Image Error" ,JOptionPane.ERROR_MESSAGE);
//		 
//		 
//	 }
	 
	 
	 add(new Display());
	 
	 pack();
	 
	 Dimension	screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 setLocation((screenSize.width - getWidth())/2 , (screenSize.height - getHeight()) /2 );
	 
	 
	 setVisible(true);
	 
}
 
 
 private void close()
 {
	 if(JOptionPane.showConfirmDialog(this, "Exit Preogram?",
	"Confirm Exit", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION)
	 System.exit(0);
		 
	
 }
 
 
 class Display extends JPanel 
 {
		public Display()
		{
			//setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
			setPreferredSize(new Dimension(image.getIconWidth()+ 50, image.getIconHeight()+ 50));
			
					//image.getIconHeight()));
					
		}
	public void paintComponent(Graphics g)
	 {
	
		 super.paintComponent(g);
		 //g.drawImage(image, 0, 0 , this);
		// g.drawImage(image.getImage(), 0, 0, this);
		 //g.drawRect(0, 0, 450, 310);
		 g.setColor(Color.BLUE);
		 g.fillRect(0, 0, 450, 310);
		 image.paintIcon(this,g, 25,15);
		 
		 String caption = "Delicate Arch";
		 
		// Font 		f = g.getFont().deriveFont(20F);
		 Font 		f = g.getFont();
		 f = f.deriveFont(f.getSize2D() * 1.2F);
		 
		 g.setFont(f);
		 
		 FontMetrics		fm = g.getFontMetrics();
		 int 			wid = fm.stringWidth(caption);
		 g.setColor(Color.RED);
		 
		 g.drawString( caption,  (getWidth() - wid) / 2, getHeight() - 10);
	 }
 }

 public static void main (String[] args)
 {
	 
	 new Arch();
 }
	
	
}
