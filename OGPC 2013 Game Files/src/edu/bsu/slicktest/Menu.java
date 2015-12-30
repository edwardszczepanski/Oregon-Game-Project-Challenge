package edu.bsu.slicktest;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Menu 
{
	public void start(GameContainer gc, Image menuScreen) 
	{
		Input input = gc.getInput();
		
		
		
		while (true)
		{
			if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) 
			{
				if(input.getMouseX() > 211 && input.getMouseX() < 805 && input.getMouseY() > 745 && input.getMouseY() < 690)
	            	gc.exit();
				else if(input.getMouseX() > 211 && input.getMouseX() < 805 && input.getMouseY() > 506 && input.getMouseY() < 452)
					break;
			}
		}
	}
}
