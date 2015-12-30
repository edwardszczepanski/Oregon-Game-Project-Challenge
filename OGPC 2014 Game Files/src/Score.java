import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Score
{
	float points;
	float maxPoints;
	float percentage;
	
	Score()
	{
		points = 0;
		maxPoints = 0;
		percentage = 1;
	}
	
	public void updateMaxPoints()
	{
		maxPoints += 500;
		percentage = points/maxPoints;
	}
	
	
	public void modPoints(int value)
	{
		points += value;
	}
	
	public void draw(Graphics g, GameContainer gc)
	{
		g.drawString("" + points, 0, gc.getWidth()/2);
		g.setColor(Color.black);
		g.draw(new Circle(gc.getScreenWidth() - 150, 150, 75));
		g.setColor(Color.red);
		g.fill(new Circle(gc.getScreenWidth() - 150, 150, 75 * percentage));
		g.setColor(Color.black);
		g.drawString(Integer.toString((int)(percentage * 100)) + "%", gc.getScreenWidth() - 148, 150);
	}
}
