package edu.bsu.slicktest;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;

public class RadarCircles
{
	private static int center_x;
	private static int center_y;
	private static int normal_radius;
	private ArrayList<Circle> radar;
	private ArrayList<Integer> pulseRadius;
	
	RadarCircles(int w, int h)
	{
		center_x = w/2;
		center_y = h/2;
		normal_radius = h/10;
		pulseRadius = new ArrayList();
		for (int i = 0; i < 5; i++)
		{
			pulseRadius.add(0);
		}
		radar = new ArrayList();
		for (int i = 1; i <= 5; i++)
    	{
    		radar.add(new Circle(center_x, center_y, normal_radius * i));
    	}
	}
	
	public void keyPressed(char key)
	{
		if (key == ';')
			pulseRadius.set(4, 10);
		else if (key == 'l')
			pulseRadius.set(3, 10);
		else if (key == 'k')
			pulseRadius.set(2, 10);
		else if (key == 'j')
			pulseRadius.set(1, 10);
		else if (key == ' ')
			pulseRadius.set(0, 10);
	}
	
	public void update()
	{
		for (int i = 0; i < 5; i++)
		{
			if (pulseRadius.get(i) != 0)
				pulseRadius.set(i, pulseRadius.get(i) - 1);
		}
	}
			
	public void draw(Graphics g)
	{
		g.setColor(new Color(0, 255, 0, 64));
    	for (int i = 0; i < 18; i++)
    	{
    		g.draw(new Line(center_x + (float) ((normal_radius * 5) * Math.cos(((i * 10) + 180) * (Math.PI / 180))), center_y + (float) ((normal_radius * 5) * Math.sin(((i * 10) + 180) * (Math.PI / 180))), center_x + (float) ((normal_radius * 5) * Math.cos((i * 10) * (Math.PI / 180))), center_y + (float) ((normal_radius * 5) * Math.sin((i * 10) * (Math.PI / 180)))));
    	}
    	
    	g.setColor(Color.red);
    	g.draw(new Circle(center_x, center_y, (normal_radius) + pulseRadius.get(0)));
    	g.setColor(Color.orange);
    	g.draw(new Circle(center_x, center_y, (normal_radius * 2) + pulseRadius.get(1)));
    	g.setColor(Color.yellow);
    	g.draw(new Circle(center_x, center_y, (normal_radius * 3) + pulseRadius.get(2)));
    	g.setColor(Color.green);
    	g.draw(new Circle(center_x, center_y, (normal_radius * 4) + pulseRadius.get(3)));
    	g.setColor(Color.blue);
    	g.draw(new Circle(center_x, center_y, (normal_radius * 5) + pulseRadius.get(4)));
		
		//Sets the drawing color to blue
		g.setColor(Color.green);
    	//Draws the five "radar" circles based on the screen width and height
    	//for (int i = 1; i <= 5; i++)
    	//{
    	//	g.draw(new Circle(center_x, center_y, (normal_radius * i) + pulseRadius.get(i - 1)));
    	//}
	}
}
