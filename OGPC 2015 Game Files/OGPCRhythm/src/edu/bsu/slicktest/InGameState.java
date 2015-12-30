package edu.bsu.slicktest;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class InGameState
{
	//Creates the selector class instance (NOTE::Most likely going to be moved somewhere else)
	Selector selector;
	//Creates an arraylist of rhythmcircles (NOTE::Not used at the moment, most likely used later)
	ArrayList<RhythmCircle> circles;
	//Creates a music variable to hold the song that will be played (NOTE::This is temporary for testing rhythms)
	Music song;
	RadarCircles radar;
	int points = 0;
	boolean paused;
	int pauseCount = 0;
	
	InGameState(GameContainer gc)
	{
		//Initializes an arraylist of RhythmCircles to be used for songs
    	circles = new ArrayList();
    	//Initializes a the selector class, passing it the width and height of the screen
    	selector = new Selector(gc.getScreenWidth(), gc.getScreenHeight());
    	//Initializes the music (NOTE::The path to the file where the music is located is sent as a string variable when initialized. Music class only supports two formats it seems. We will use wavs as those seem more common)
    	try {song = new Music("data/WaltzOfFatCats.wav");} catch (SlickException e1) {}
    	radar = new RadarCircles(gc.getScreenWidth(), gc.getScreenHeight());
    	paused = false;
    	
    	//-----TESTING-----
    	//Testing to see if the music was loaded correctly and plays
    	//Sets the music volume to max
    	song.setVolume(1);
    	//Starts to play the song
    	//song.play();
    	
    	//-----TESTING-----
    	int[][] data = null;
    	ReadSong read = new ReadSong("data/song.txt");
    	try {
			data = read.OpenFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for (int i = 0; i < data.length; i++)
    	{
    		circles.add(new RhythmCircle(data[i][0], data[i][1], data[i][2], gc));
    	}
	}
	
	public String update(Input input, String specialInput, Debug debug)
	{	
		if (!paused)
		{
			//Checks to see if a special input key was pressed
	    	if (specialInput.equals("`"))
	    	{
	    		//Toggles debug mode on and off
	    		debug.debugToggle();
	    		//Sets the specialInput variable back to an empty string so that it can be used again
	    		specialInput = "";
	    	}
	    	
	    	if (input.isKeyPressed(Input.KEY_SPACE))
	    	{
	    		radar.keyPressed(' ');
	    		for (int i = 0; i < circles.size(); i++)
	    		{
	    			if (circles.get(i).getLayer() == 5)
	    			{
	    				points = circles.get(i).keyPressed(' ', selector, points);
	    				break;
	    			}
	    		}
	    	}
	    	
	    	if (input.isKeyPressed(Input.KEY_J))
	    	{
	    		radar.keyPressed('j');
	    		for (int i = 0; i < circles.size(); i++)
	    		{
	    			if (circles.get(i).getLayer() == 4)
	    			{
	    				points = circles.get(i).keyPressed('h', selector, points);
	    				break;
	    			}
	    		}
	    	}
	    	
	    	if (input.isKeyPressed(Input.KEY_K))
	    	{
	    		radar.keyPressed('k');
	    		for (int i = 0; i < circles.size(); i++)
	    		{
	    			if (circles.get(i).getLayer() == 3)
	    			{
	    				points = circles.get(i).keyPressed('j', selector, points);
	    				break;
	    			}
	    		}
	    	}
	    	
	    	if (input.isKeyPressed(Input.KEY_L))
	    	{
	    		radar.keyPressed('l'); 
	    		for (int i = 0; i < circles.size(); i++)
	    		{
	    			if (circles.get(i).getLayer() == 2)
	    			{
	    				points = circles.get(i).keyPressed('k', selector, points);
	    				break;
	    			}
	    		}
	    	}
	    	
	    	if (input.isKeyPressed(Input.KEY_SEMICOLON))
	    	{
	    		radar.keyPressed(';');
	    		for (int i = 0; i < circles.size(); i++)
	    		{
	    			if (circles.get(i).getLayer() == 1)
	    			{
	    				points = circles.get(i).keyPressed('l', selector, points);
	    				break;
	    			}
	    		}
	    	}
		}
    	
    	if (input.isKeyPressed(Input.KEY_P))
    	{
    		paused = !paused;
    		if (paused)
    			song.pause();
    		else
    			song.resume();
    		pauseCount++;
    	}
    	
    	if (selector.getAngle() == 180 && selector.getRotations() == 0)
    		song.play();
    	
    	//Updates the selctors position
    	if (!paused)
    	{
	    	radar.update();
	    	selector.updateSelector();
    	}
    	
    	return specialInput;
	}
	
	public void render(GameContainer gc, Graphics g, Debug debug)
	{
	    radar.draw(g);
	    
	    if (!paused)
		{
		    //-----Testing-----
		    //Draws a test circle (NOTE::Selector is passed to the draw function so that the circle can determine from the position of the selector when to start drawing)
		    for (int i = 0; i < circles.size(); i++)
		    {
		    	if (circles.get(i).returnTermination() == 1)
		    		circles.remove(i);
		    	else
		    		points = circles.get(i).drawCircle(g, selector, points);
		    }
		    
		    //Draws debug mode
		    debug.draw(g, gc);
		}
	    
	    g.setColor(Color.red);
	    g.drawString(Integer.toString(points), gc.getScreenWidth() - 100, 10);
	    
	    //Draw the selector
	    selector.drawSelector(g);
	}
}
