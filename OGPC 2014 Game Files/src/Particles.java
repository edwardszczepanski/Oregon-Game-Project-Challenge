import java.awt.Font;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;

public class Particles
{
	private Font font;
	private TrueTypeFont wordFont;
	private float radius;
	private int numCircles;
	private boolean draw;
	private int counter;
	private float centerX;
	private float centerY;
	private float[] circlesX;
	private float[] circlesY;
	private int[] directions;
	private Color color;
	private float textX;
	private float textY;
	private String points;
	Random random;
	
	Particles(int num, float x, float y, int point, Color circleColor)
	{
		draw = true;
		radius = 5;
		numCircles = num;
		centerX = x;
		centerY = y;
		counter = 0;
		
		random = new Random();
		
		circlesX = new float[numCircles];
		circlesY = new float[numCircles];
		for (int i = 0; i < circlesX.length; i++)
			circlesX[i] = centerX;
		for (int i = 0; i < circlesY.length; i++)
			circlesY[i] = centerY;
		
		directions = new int[numCircles];
		for (int i = 0; i < directions.length; i++)
			directions[i] = random.nextInt(360);
		
		textX = x;
		textY = y;
		
		points = Integer.toString(point);
		
		font = new Font("Comic Sans MS", Font.BOLD, 32);
		wordFont = new TrueTypeFont(font, true);
		
		color = circleColor;
	}
	
	private void toggleDraw()
	{
		draw = !draw;
	}
	
	public boolean checkDraw()
	{
		return draw;
	}
	
	public void update()
	{
		if (draw)
		{
			for (int i = 0; i < circlesX.length; i++)
				circlesX[i] = (float) (circlesX[i] + (1 * Math.cos(directions[i] * Math.PI/180)));
			for (int i = 0; i < circlesX.length; i++)
				circlesY[i] = (float) (circlesY[i] + (1 * Math.sin(directions[i] * Math.PI/180)));
			
			counter++;
			radius -= radius/30;
			if (counter == 30)
				toggleDraw();
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		
		if (draw)
		{
			for (int i = 0; i < numCircles; i++)
				g.fill(new Circle(circlesX[i], circlesY[i], radius));
			wordFont.drawString(textX, textY, points, color);
		}
	}
}
