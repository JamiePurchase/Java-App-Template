/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.awt.Graphics;

public abstract class State
{	
	public abstract void tick();
	public abstract void render(Graphics g);
	private static State currentState = null;
	private static String changeState = null;
	
	public static State getState()
	{
		return currentState;
	}
	
	public static String getStateChange()
	{
		return changeState;
	}
	
	public static void setState(State state)
	{
		currentState = state;
	}
	
	public static void setStateChange(String change)
	{
		changeState = change;
	}
}