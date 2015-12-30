import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Engine
{
	RadarCircles rc;
	Selector selector;
	Score points;
	RhythmCircle circle;
	Rhythms rhythm;
	
	boolean playingSong = false;
	
	ArrayList<Particles> particles;
	
	
	Engine(GameContainer gc, Rhythms r)
	{
		rc = new RadarCircles(gc.getWidth(), gc.getHeight());
		rhythm = r;
	}
	
	public void setup()
	{
		selector = new Selector();
		points = new Score();
		particles = new ArrayList<Particles>();
	}
	
	public void leave(GameContainer gc, StateBasedGame state)
	{
		points = new Score();
		particles = new ArrayList<Particles>();
	}
	
	public void start()
	{
		selector.start();
	}
	
	public void pause()
	{
		selector.pause();
		if (rhythm.currentSong.playing())
			rhythm.currentSong.pause();
	}
	
	public void play()
	{
		selector.start();
		rhythm.currentSong.resume();
	}
	
	public void update(GameContainer gc, StateBasedGame state)
	{
		Input input = gc.getInput();
		
		try
		{
			if (selector.getRotations() == 0 && selector.getAngle() == 180)// && selector.getAngle() == (270 - (int) rhythm.circleList.get(0).getStartingAngle()))
				rhythm.currentSong.play();
		}catch(IndexOutOfBoundsException e){}
		
		selector.updateSelector();
		
		rhythm.updateRhythmCircleList(selector);
		
		if (input.isKeyDown(Input.KEY_H) && input.isKeyDown(Input.KEY_J))
		{
			rc.keyPressed(2);
			try
			{
				for (int i = 0; i < 5; i++)
					rhythm.circleList.get(i).keyPressed(2);
			}catch(IndexOutOfBoundsException e){}
		}
		
		else if (input.isKeyDown(Input.KEY_J) && input.isKeyDown(Input.KEY_K))	
		{
			rc.keyPressed(4);
			try
			{
				for (int i = 0; i < 5; i++)
					rhythm.circleList.get(i).keyPressed(4);
			}catch(IndexOutOfBoundsException e){}
		}
		
		else if (input.isKeyDown(Input.KEY_H))
		{
			rc.keyPressed(1);
			try
			{
				for (int i = 0; i < 5; i++)
					rhythm.circleList.get(i).keyPressed(1);
			}catch(IndexOutOfBoundsException e){}
		}
		
		else if (input.isKeyDown(Input.KEY_J))
		{
			rc.keyPressed(3);
			try
			{
				for (int i = 0; i < 5; i++)
					rhythm.circleList.get(i).keyPressed(3);
			}catch(IndexOutOfBoundsException e){}
		}
		
		else if (input.isKeyDown(Input.KEY_K))
		{
			rc.keyPressed(5);
			try
			{
				for (int i = 0; i < 5; i++)
					rhythm.circleList.get(i).keyPressed(5);
			}catch(IndexOutOfBoundsException e){}
		}
	}
	
	public void render(GameContainer gc, Graphics g)
	{
		g.setColor(new Color(84, 84, 84));
		g.fill(new Rectangle(0, 0, gc.getScreenWidth(), gc.getScreenHeight()));
		
		rc.draw(g);
		g.setColor(Color.cyan);
		
		g.drawString(Float.toString(selector.getAngle()), 0, 0);
		g.drawString(Integer.toString(selector.getRotations()), 0, 20);
		
		for (int i = 0; i < rhythm.circleList.size(); i++)
		{
			rhythm.circleList.get(i).draw(g, selector);
			//if (!rhythm.circleList.get(i).draw(g))
			//	if (i == rhythm.circleList.size()-1 || !rhythm.circleList.get(i + 1).getVisible() )
					//break;
		}
	}
}
