package edu.bsu.slicktest;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Debug
{
	boolean debug_mode_on;
	long[] updateTimes = {0,0};
	long[] timesBetweenUpdates = {0,0,0,0,0,0,0,0,0,0};
	long[] renderTimes = {0,0};
	long[] timesBetweenRenders = {0,0,0,0,0,0,0,0,0,0};
	ArrayList<String> circlesWritten;
	
	Debug()
	{
		debug_mode_on = false;
		circlesWritten = new ArrayList();
	}
	
	public void setUpdateTimes(long currentUpdateTime)
	{
    	updateTimes[0] = updateTimes[1];
    	updateTimes[1] = currentUpdateTime;
    	for (int i = 0; i < timesBetweenUpdates.length - 1; i++)
    	{
    		timesBetweenUpdates[i] = timesBetweenUpdates[i+1];
    	}
    	timesBetweenUpdates[timesBetweenUpdates.length - 1] = updateTimes[1] - updateTimes[0];
    	
	}
	
	public void setRenderTimes(long currentRenderTime)
	{
		renderTimes[0] = renderTimes[1];
		renderTimes[1] = currentRenderTime;
		for (int i = 0; i < timesBetweenRenders.length - 1; i++)
    	{
    		timesBetweenRenders[i] = timesBetweenRenders[i+1];
    	}
    	timesBetweenRenders[timesBetweenRenders.length - 1] = renderTimes[1] - renderTimes[0];
	}
	
	public void debugToggle()
	{
		if (debug_mode_on)
			debug_mode_on = false;
		else
			debug_mode_on = true;
	}
	
	public void writeCircle(String circle)
	{
		circlesWritten.add(circle);
	}
	
	public void draw(GameContainer gc, Graphics g)
	{
		if (debug_mode_on)
		{
			g.setColor(Color.white);
			g.drawString("Time between updates: " + timesBetweenUpdates[timesBetweenUpdates.length - 1] + " ms", 10, 100);
			g.drawString("Average time between updates: " + ((timesBetweenUpdates[0] + timesBetweenUpdates[1] + timesBetweenUpdates[2] + timesBetweenUpdates[3] + timesBetweenUpdates[4] + timesBetweenUpdates[5] + timesBetweenUpdates[6] + timesBetweenUpdates[7] + timesBetweenUpdates[8] + timesBetweenUpdates[9]) / timesBetweenUpdates.length) + " ms", 10, 115);
			g.drawString("Time between renders: " + timesBetweenRenders[timesBetweenRenders.length - 1] + " ms", 10, 130);
			g.drawString("Average time between renders: " + ((timesBetweenRenders[0] + timesBetweenRenders[1] + timesBetweenRenders[2] + timesBetweenRenders[3] + timesBetweenRenders[4] + timesBetweenRenders[5] + timesBetweenRenders[6] + timesBetweenRenders[7] + timesBetweenRenders[8] + timesBetweenRenders[9]) / timesBetweenRenders.length) + " ms", 10, 145);
			for (int i = 0; i < circlesWritten.size(); i++)
			{
				int modifier = i + (circlesWritten.size() - gc.getScreenHeight()/20);
				if (modifier < i)
					modifier = i;
				else if (modifier == circlesWritten.size())
					break;
				g.drawString(circlesWritten.get(modifier), gc.getScreenWidth() - 100, 0 + (i * 20));
			}
		}
	}
}
