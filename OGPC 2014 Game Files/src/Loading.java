import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Loading
{
	private float[] centerX;
	private float[] centerY;
	private float[] startAngle;
	private float[] endAngle;
	private float radius;
	private Color[] loadingColors;
	
	Loading(int windowWidth, int windowHeight)
	{
		float x = windowWidth/2;
		float y = windowHeight/2;
		float r = windowHeight/4;
		
		centerX = new float[5];
		centerY = new float[5];
		for (int i = 0; i < centerX.length; i++)
		{
			centerX[i] = (float) (x + (r-30) * Math.cos(i * 2 * Math.PI / 5));
			centerY[i] = (float) (y + (r-30) * Math.sin(i * 2 * Math.PI / 5));
		}
		
		startAngle = new float[5];
		endAngle = new float[5];
		for (int i = 0; i < startAngle.length; i++)
		{
			Point point = new Point((float) (x + r * Math.cos((i * 2 * Math.PI / 5) + (270 * Math.PI/180))), (float) (y + r * Math.sin((i * 2 * Math.PI / 5) + (270 * Math.PI/180))));
			endAngle[i] = (float) Math.atan((centerY[i] - point.getY())/(centerX[i] - point.getX()));
			if (i == 0 || i == 4)
				endAngle[i] = (float) (endAngle[i] * (180/Math.PI)) + 180;
			else
				endAngle[i] = (float) (endAngle[i] * (180/Math.PI));
			startAngle[i] = endAngle[i] - 84;
		}
		
		loadingColors = new Color[5];
		loadingColors[0] = new Color(0, 255, 255, .05f);
		loadingColors[1] = new Color(0, 0, 255, .05f);
		loadingColors[2] = new Color(255, 0, 0, .05f);
		loadingColors[3] = new Color(255, 255, 0, .05f);
		loadingColors[4] = new Color(0, 255, 0, .05f);
		
		radius = r;
	}
	
	public void update()
	{
		for (int i = 0; i < startAngle.length; i++)
		{
			startAngle[i] += 10;
			endAngle[i] += 10;
		}
	}
	
	public void draw(Graphics g)
	{
		g.setLineWidth(5);
		for (int i = 0; i < 5; i++)
		{
			for (int j = 1; j <= 5; j++)
			{
				g.setColor(new Color(loadingColors[i].r, loadingColors[i].g, loadingColors[i].b, loadingColors[i].a * j));
				float factor = 15;
				for (int k = 6 - j; k > 0; k--)
					factor -= k;
				g.drawArc(centerX[i] - radius, centerY[i] - radius, radius * 2, radius * 2, startAngle[i] + (factor * 80/15), endAngle[i]);
			}
			
			g.setColor(new Color(loadingColors[i].r, loadingColors[i].g, loadingColors[i].b));
			g.drawArc(centerX[i] - radius, centerY[i] - radius, radius * 2, radius * 2, endAngle[i] - 4, endAngle[i]);
		}
	}
}
