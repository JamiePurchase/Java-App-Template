/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import graphics.Fonts;
import input.InputKeyboard;
import java.awt.Color;
import java.awt.Graphics;

public class StateTitle extends State
{
	
	public StateTitle()
	{
		// Audio
		//Game.audio.playMusic("music1"); 
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
                g.fillRect(0, 0, 1366, 768);
                g.setFont(Fonts.getFont("standard"));
                g.setColor(Color.WHITE);
                g.drawString("TITLE STATE", 500, 100);
	}
	
	public void tick()
	{
		tickKey();
	}
	
	public void tickKey()
	{
		//if(Keyboard.getKeyPressed()=="")
                //Game.setStateChange(new StateGame());

		if(InputKeyboard.getKeyPressed()=="Escape")
		{
			InputKeyboard.keyPressedDone();
			System.exit(0);
		}
	}
}