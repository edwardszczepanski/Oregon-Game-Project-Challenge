import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Circle;

public class RadarCircles
{
	static int center_x;
	static int center_y;
	int radius;
	Color[] outlineColors;
	Color[] fillColors;
	int[] lineWidth;
	
	RadarCircles(int screenWidth, int screenHeight)
	{
		center_x = screenWidth/2;
		center_y = screenHeight/2;
		
		radius = (screenHeight/10) - 5;
		
		fillColors = new Color[5];
		for (int i = 0; i < fillColors.length; i++)
			fillColors[i] = Color.black;
		
		outlineColors = new Color[5];
		outlineColors[0] = new Color(173, 16, 16); //red
		outlineColors[1] = new Color(196, 196, 16); //yellow
		outlineColors[2] = new Color(22, 161, 22); //green
		outlineColors[3] = new Color(63, 186, 186); //cyan
		outlineColors[4] = new Color(10, 29, 145); //blue
		
		lineWidth = new int[5];
		lineWidth[0] = 4;
		lineWidth[1] = 2;
		lineWidth[2] = 4;
		lineWidth[3] = 2;
		lineWidth[4] = 4;
	}
	
	public void select(int circle)
	{
		for (int i = 0; i < lineWidth.length; i++)
			lineWidth[i] = 3;
		lineWidth[circle - 1] = 8;
	}
	
	public void keyPressed(int layer)
	{
		fillColors[layer - 1] = outlineColors[layer - 1];
	}
	
	public void draw(Graphics g)
	{
		for (int i = 0; i < 5; i++)
		{
			g.setLineWidth(lineWidth[i]);
			g.setColor(outlineColors[i]);
			g.draw(new Circle(center_x + (radius * (i + 1)), center_y, 28));
			g.draw(new Circle(center_x, center_y, radius * (i + 1)));
			g.setColor(fillColors[i]);
			g.fill(new Circle(center_x + (radius * (i + 1)), center_y, 27));
			fillColors[i] = Color.black;
		}
			
	}
}
