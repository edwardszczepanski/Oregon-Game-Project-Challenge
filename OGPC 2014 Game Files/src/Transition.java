public class Transition
{
	float startingValue;
	float changingValue;
	float targetValue;
	float increment;
	boolean transition;
	boolean occilating;
	boolean sineOccilating;
	
	Transition(float change, float target)
	{
		transition = false;
		changingValue = 0;
		increment = change;
		targetValue = target;
		startingValue = 0;
	}
	
	Transition(float change, float target, float start, boolean sineOccilate)
	{
		occilating = true;
		transition = true;
		sineOccilating = sineOccilate;
		changingValue = 0;
		increment = change;
		targetValue = target;
		startingValue = start;
	}
	
	public boolean isActive()
	{
		return transition;
	}
	
	public void start()
	{
		transition = true;
	}
	
	private Object changeValue(Object toChange)
	{
		float[] changedArray = null;
		float changedNum = 0;
		
		if (toChange instanceof int[] || toChange instanceof float[])
		{
			changedArray = (float[]) toChange;
			for (int i = 0; i < changedArray.length; i++)
				changedArray[i] += increment;
			return changedArray;
		}
		else
		{
			changedNum = (float) toChange;
			changedNum += increment;
			return changedNum;
		}
	}
	
	public Object transition(Object toChange)
	{
		changingValue += increment;
		if (occilating)
		{
			if (sineOccilating)
			{
				if (Math.abs(changingValue) >= targetValue)
				{
					increment -= increment * 2;
				}
			}
			else
			{
				if (Math.abs(changingValue) >= targetValue || changingValue <= 0)
				{
					increment -= increment * 2;
				}
			}
		}
		else
		{
			if (Math.abs(changingValue) == targetValue)
			{
					transition = false;
					changingValue = 0;
			}
		}
		return changeValue(toChange);
	}
	
}
