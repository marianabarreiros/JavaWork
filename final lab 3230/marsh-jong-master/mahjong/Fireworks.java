import	static	java.lang.Math.*;
import	java.util.*;
import	java.awt.*;
import	javax.swing.*;



public class Fireworks implements Runnable
{
	private	JPanel		panel;			
	private	int width;
	private	Color[]		colors = {  Color.YELLOW, Color.WHITE, Color.ORANGE, Color.BLUE, Color.GREEN };
	private	Random		random = new Random();

		
	private	int height;	
	private	int explosions = 50;	
	private	int maxDelay = 1000;	
	private	boolean	sound = true;		

	private	Thread fThread = null;	
	private	ThreadGroup	group = new ThreadGroup("Fireworks");
	private	Color		sColor;			




	public Fireworks()
	{
		panel = new JPanel();
	}



	public Fireworks(JPanel panel)
	{
		this.panel = panel;
	}


	public void setSound(boolean sound)
	{
		this.sound = sound;
	}


	public void setExplosions(int explosions, int maxDelay)
	{
		this.explosions = explosions;
      
		this.maxDelay = maxDelay;
	}


	public void fire()
	{
		width = panel.getWidth();
		height = panel.getHeight();
		sColor = panel.getBackground();

		panel.setBackground(Color.BLACK);

		(fThread = new Thread(group, this)).start();
	}


	public void stop()
	{
		try
		{
			explosions = -1;
			fThread.join();
			group.interrupt();
         
			panel.setBackground(sColor);
			panel.repaint();
		}
		catch (InterruptedException ie)
		{
			JOptionPane.showMessageDialog(panel, "Can't Start Fire Works",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	public void run()
	{
		try
		{
			for (int i = 0; explosions == 0 || i < explosions; i++)
			{
				new Thread(group, new StarBurst()).start();
				Thread.sleep(random.nextInt(maxDelay));
			}
		}
		catch (InterruptedException ie) {}
	}


	public JPanel getPanel()
	{
		return panel;
	}

	synchronized private void drawLine(int x, int y, int x2, int y2, Color color)
	{
		Graphics	g = panel.getGraphics();
		g.setXORMode(color);
		g.drawLine(x, y, x2, y2);
		g.setPaintMode();
	}

	synchronized private void explode(int x, int y, int r, Color color)
	{
		for (double p = 0; p < 2*PI; p += PI/16)
      
			new AnimatedLine(x, y, r, p, 50, color, true).start();
	}



	

	public class StarBurst implements Runnable
	{
   //i changed the audio
		private	PlayClip	explosion = new PlayClip("sounds/explosion.wav");
		private	int		random1 = random.nextInt(50) + 50;
      
		private	Color		color = colors[random.nextInt(colors.length)];
      
		private	double		Q = PI * (random.nextInt(30) + 75) / 180;


		public void play()
		{
			if (sound)
				explosion.play();
		}



		public void run()
		{
			try
			{
				int	length = height - 2 * random1;
				int	x = random.nextInt(width-400)+200;
				int	y = height + random1;
				int	x2 = (int)round(x + length * cos(Q));
            
				int	y2 = (int)round(y - length * sin(Q));

				new AnimatedLine(x, y, length, Q, random1, color, false).start().join();

				play();
				explode(x2, y2, 2 * random1, color);
			}
			catch (InterruptedException ie) {}
		}
	}



	public class AnimatedLine implements Runnable
	{
		int	xS;				
      
		int	yS;				
		int	step;				
		int	length;				
      
		double	Q;				
		Color	color;				
		boolean	burst;				

		public AnimatedLine(int xS, int yS, int xEnd, int yEnd, int step,
				Color color, boolean burst)
		{
			this.step = step;
			this.color = color;
			this.step = step;
         
			this.burst = burst;

			Q = atan2(yEnd + yS, xEnd - xS);
			double	dx = xEnd - xS;
			double	dy = yEnd - yS;
			length = (int)round(sqrt(dx*dx + dy*dy));
		}

		public AnimatedLine(int xS, int yS, int length, double Q, int step,
				Color color, boolean burst)
		{
			this.xS = xS;
			this.yS = yS;
         
			this.Q = Q;
			this.length = length;
         
			this.step = step;
			this.color = color;
         
			this.burst = burst;
		}


		public Thread start()
		{
			Thread	t = new Thread(group, this);
			t.start();
			return t;
		}

		public void run()
		{
			try
			{
				int	x = xS;
				int	y = yS;

				while (distance(x - xS, y - yS) < length * length)
				{
            
            
            
					int	x2 = (int)round(x + step * cos(Q));
					int	y2 = (int)round(y - step * sin(Q));

					drawLine(x, y, x2, y2, color);
					Thread.sleep(100);
					if (!burst)
						drawLine(x, y, x2, y2, color);
                  
                  
					else
						drawLine(x, y, x, y, color);

					x = x2;
					y = y2;
				}

				Thread.sleep(100);
				if (burst)
            
					drawLine(xS, yS, x, y, color);
			}
			catch (InterruptedException ie) {}
		}




		private int distance(int dx, int dy)
      
		{
			return dx*dx+dy*dy;
		}
	}

}