package edu.bsu.slicktest;

import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Selector
{
	//Creates a static variable to hold the selector length
	private static int selector_length;
	//Creates static variables to hold the center x and y coordinates for the selector
	private static float center_x;
	private static float center_y;
	//Creates variables to hold the change in x and y coordinates for the outside point of the selector
	private double deltaX;
	private double deltaY;
	//Creates a variable to hold the current angle of the selector (NOTE::Positive x-axis is 0 degrees. Selector turns clockwise)
	private float angle;
	//Creates a variable to hold the speed that the angle changes
	private double speed;
	private int rotations;
	
	Selector(int w, int h)
	{
		//Initializes the center x and y of the selector to be half the screen width and height to place the center of the selector in the center of the screen
		center_x = w/2;
		center_y = h/2;
		//Initializes the selector length to half the height of the screen
		selector_length = h/2;
		//Initializes the changing x and y values to zero
		deltaX = 0;
		deltaY = 0;
		//Initializes the angle to zero degrees
		angle = 0;
		//Initializes the speed to roughly 31.25 degrees/second (NOTE::The speed is added to the angle each time updateSelector is called. Speed is in degrees/16 ms. 16 ms is determined by the time between updates loops [Check debug mode to find the time between updates for your current machine])
		speed = 0.5; //degrees per 16 milliseconds
		rotations = 0;
	}
	
	public float getAngle()
	{	
		return angle;
	}
	
	public int getRotations()
	{
		return rotations;
	}

	//Updates the selectors position by incrementing the angle by the speed and then calculating the new x and y coordinates using trig
	public void updateSelector()
	{
		if (angle >= 360)
		{
			angle = 0;
			rotations++;
		}
		else
			angle+=speed;
		deltaX = selector_length * Math.cos(angle * (Math.PI / 180));
		deltaY = selector_length * Math.sin(angle * (Math.PI / 180));
	}
	
	//Draws the selector
	public void drawSelector(Graphics g)
	{
		g.setColor(Color.white);
		g.drawLine(center_x, center_y, center_x + (float)deltaX, center_y + (float)deltaY);
	}
}
