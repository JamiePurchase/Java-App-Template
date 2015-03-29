/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;
import audio.AudioManager;
/**
 *
 * @author Jamie
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;
import state.State;
import state.StateTitle;

public class Game extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static Display display;
	public String title;
	public int width, height;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	public static AudioManager audio;

	public Game()
	{
		this.title = "TemplateApp";
		this.width = 1366;
		this.height = 768;
	}
	
	private void init()
	{
		// Create Display
		display = new Display(title, width, height);

		// Audio
		audio = new AudioManager();
		
		// Title Menu
		State.setState(new StateTitle());
	}
	
	private void initAudio()
	{
		audio = new AudioManager();
	}
	
	private void render()
	{
		// Buffer strategy
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		// Graphics start
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		// Graphics draw
		if(State.getState() != null)
		{
			State.getState().render(g);
		}

		// Graphics done
		bs.show();
		g.dispose();
	}
	
	public void run()
	{
		// Render speed
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		// Create window
		init();
		
		// Main game loop
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1)
			{			
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000)
			{
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		// End game
		stop();
	}
	
	private void tick()
	{
		// Audio
		audio.tick();
		
		// Tick state
		if(State.getState() != null)
		{
			State.getState().tick();
		}
	}
	
	public synchronized void start()
	{
		if(running==false)
		{
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop()
	{
		if(running==true)
		{
			try
			{
				thread.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}