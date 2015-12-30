import org.newdawn.slick.Input;

public class StateHandler
{
	private int lastStateID;
	private int[] keysPressed;

	public boolean exitCurrentState;
	
	StateHandler()
	{
		lastStateID = 0;
		keysPressed = new int[]{-1, -1, -1};
		
		exitCurrentState = false;
	}
	
	public void exitKeyPress(int key)
	{
		if (key == Input.KEY_H)
		{
			keysPressed[0]++;
		}
		if (key == Input.KEY_J)
		{
			keysPressed[0]++;
		}
		if (key == Input.KEY_K)
		{
			keysPressed[0]++;
		}
	}
	
	public void checkExit(int key)
	{
		
	}
	
	public void leavingState(int ID)
	{
		lastStateID = ID;
	}
	
	public int getLastStateID()
	{
		return lastStateID;
	}
}
