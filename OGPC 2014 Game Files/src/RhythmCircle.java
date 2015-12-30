import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class RhythmCircle
{
	private static int windowWidth;
	private static int windowHeight;
	
	private float x;
	private float y;
	private float rotations;
	private float angle;
	
	private float startDrawingAngle;
	private float circleRadiusAsAngle;
	
	private float radius;
	private static float circle_radius;
	
	private boolean visible;
	
	private float[] pointAngles;
	private int layer;
	private int termination;
	
	private Color color;
	
	
	RhythmCircle(float a, int rot, int lay, GameContainer gc)
	{
		windowWidth = gc.getWidth();
		windowHeight = gc.getHeight();
		
		startDrawingAngle = a;
		rotations = rot;
		layer = lay;
		
		circle_radius = 25;
		
		double csquared = (circle_radius*2) * (circle_radius*2);
		double ab = 2 * ((((windowHeight/10) - 5) * layer) * (((windowHeight/10) - 5) * layer));
		
		angle = (float) (Math.acos((csquared - ab) / (-1 * ab)) * (180/Math.PI)) + (5/layer); 
		circleRadiusAsAngle = angle;
		
		/*pointAngles = new float[3];
		csquared = circle_radius * circle_radius;
		pointAngles[0] = (float) (Math.acos((csquared - ab) / (-1 * ab)) * (180/Math.PI)) + (5/layer);
		csquared = (circle_radius*.5) * (circle_radius*.5);
		pointAngles[1] = (float) (Math.acos((csquared - ab) / (-1 * ab)) * (180/Math.PI)) + (5/layer);
		csquared = (circle_radius*.25) * (circle_radius*.25);
		pointAngles[2] = (float) (Math.acos((csquared - ab) / (-1 * ab)) * (180/Math.PI)) + (5/layer);*/
		
		x = windowWidth/2 + (float) (((windowHeight/10) * layer) * Math.cos(angle * (Math.PI/180)));
		y = windowHeight/2 + (float) (((windowHeight/10) * layer) * Math.sin(angle * (Math.PI/180)));
		
		if (layer == 1)
			color = new Color(173, 16, 16);
		else if (layer == 2)
			color = new Color(196, 196, 16);
		else if (layer == 3)
			color = new Color(22, 161, 22);
		else if (layer == 4)
			color = new Color(63, 186, 186);
		else if (layer == 5)
			color = new Color(10, 29, 145);
		
		radius = 0;
		visible = false;
		
		termination = -1;
	}
	
	public float getStartingAngle()
	{
		return circleRadiusAsAngle;
	}
	
	public void printCircle()
	{
		System.out.println("(" + (int)startDrawingAngle + "," + (int)rotations + "," + (int)layer + ")");
	}
	
	public boolean checkTermination()
	{
		if (termination == 1)
			return true;
		
		return false;
	}
	
	public void toggleVisible()
	{
		visible = !visible;
		termination++;
	}
	
	public boolean checkVisible(Selector selector)
	{
		if (selector.getAngle() == startDrawingAngle && selector.getRotations() == rotations)
			toggleVisible();
		
		return visible;
	}
	
	public void keyPressed(int layerPressed)
	{
		if (layer == layerPressed && termination != 1)
		{
			if (angle > (360 - circleRadiusAsAngle))
			{
				visible = false;
				termination = 1;
			}
		}
	}
	
	private void smoothAppear()
	{
		if (radius <= circle_radius)
			radius += .1;
	}
	
	public void move()
	{
		angle += .75;
		
		x = windowWidth/2 + (float) ((((windowHeight/10) - 5) * layer) * Math.cos(angle * (Math.PI/180)));
		y = windowHeight/2 + (float) ((((windowHeight/10) - 5) * layer) * Math.sin(angle * (Math.PI/180)));
	}
	
	private void checkFullCircle()
	{
		if (angle + .1 >= circleRadiusAsAngle + 360 && angle - .1 <= circleRadiusAsAngle + 360)
		{
			toggleVisible();
		}
	}
	
	public void updateCircle()
	{
		smoothAppear();
		move();
		checkFullCircle();
	}
	
	public void draw(Graphics g, Selector selector)
	{
		if (visible)  
		{
			if (selector.checkRunning())
				updateCircle();
			g.setColor(color);
			g.fill(new Circle(x, y, radius));
			g.setColor(Color.black);
			g.draw(new Circle(x, y, radius));
		}
	}
}