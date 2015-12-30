public class Selector
{
	private float angle;
	private float speed;
	private int rotations;
	private boolean running;
	
	public Selector()
	{
		angle = 0;
		speed = 0.5f;//Calculated to allow 11 seconds until the first note hits the bar (144/275)
		rotations = 0;
		running = false;
	}
	
	public boolean checkRunning()
	{
		return running;
	}
	
	public void start()
	{
		running = true;
	}
	
	public int getRotations()
	{
		return rotations;
	}
	
	public float getAngle()
	{
		return angle;                                                                                                                                                                                                                                                                                                                                                                                                 
	}
	
	public void pause()
	{
		running = false;
	}
	
	public void updateSelector()
	{
		if (running)
		{
			angle += speed;
			
			if (angle >= 360)
			{
				angle -= 360;
				rotations++;
			}
		}
	}
}
