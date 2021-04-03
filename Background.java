import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Background extends GameObject
{
	BufferedImage bg;

	public Background()
	{
		bg = MarioWindow.getImage("Battle_template(2).png");
	}
	public void paint(Graphics2D g)
	{
		g.drawImage(bg,0,0,null);
	}
	public void music()//Lance Lim for code for music implementation
	{
		try
		{
      URL url = this.getClass().getClassLoader().getResource("PBM.wav");
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
      Clip clip = AudioSystem.getClip();
      clip.open(audioIn);
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
  	catch (UnsupportedAudioFileException e) 
  	{
    	e.printStackTrace();
  	}
  	catch (IOException e) 
  	{
    	e.printStackTrace();
  	} 
  	catch (LineUnavailableException e) 
  	{
    	e.printStackTrace();
  	}
  }
}