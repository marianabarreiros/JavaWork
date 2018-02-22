

import	java.net.*;
import	java.io.*;
import	javax.swing.*;
import	javax.sound.sampled.*;



public class PlayClip implements LineListener
{
	private	URL	link;
	private	boolean	problemFinder;



	public PlayClip(String name)
	{
		this(name, false);
	}




	public PlayClip(String name, boolean problemFinder)
	{
		this.problemFinder = problemFinder;
		link = PlayClip.class.getResource(name);
	}
	
		
	public void play()
	{
		if (AudioSystem.isLineSupported(Port.Info.SPEAKER))
		{
			try
			{
				Clip	audio = null;		
				AudioInputStream	stream = AudioSystem.getAudioInputStream(link);
				
				audio = AudioSystem.getClip();
            
				audio.addLineListener(this);
            
				audio.open(stream);
				audio.start();
			}
			catch (LineUnavailableException lue)
			{
				if (problemFinder)
					JOptionPane.showMessageDialog(null,
							"Error ",
							"Sound Clip Error",
                     
							JOptionPane.ERROR_MESSAGE);
			}
			catch (UnsupportedAudioFileException uafe)
			{
				if (problemFinder)
					JOptionPane.showMessageDialog(null,
							"audio type is not supported",
                     
							"audio Clip Error",
							JOptionPane.ERROR_MESSAGE);
			}
			catch (IOException ioe)
			{
				if (problemFinder)
					JOptionPane.showMessageDialog(null,
               
							"File I/O error: " + ioe,
							"audio Clip Error",
							JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void update(LineEvent event)
	{
		if (event.getType() == LineEvent.Type.STOP)
      
			event.getLine().close();
	}


}