package edu.bsu.slicktest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import org.newdawn.slick.geom.Circle;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.ResourceLoader;

public class MainRhythms extends BasicGame
{
	//Creates the selector class instance (NOTE::Most likely going to be moved somewhere else)
	Selector selector;
	//Creates a variable for the computer screen width
	static int screen_width;
	//Creates a variable for the computer screen height
	static int screen_height;
	//Creates an instance of the Debug class so that debug mode can be activated
	Debug debug;
	//Creates a variable for special keyboard input that is not support in the slick library
	String specialInput;
	//Creates a music variable to hold the song that will be played (NOTE::This is temporary for testing rhythms)  
	Music song;
	RadarCircles radar;
	BufferedWriter rhythmFile;
	Image pause;
	boolean gamePaused;
	int first = 0;
	
    public MainRhythms()
    {
        super("Rhythms");
    }
    
    //Creates a function that tests for special input that is not detected by slick2d
    @Override
    public void keyPressed(int key, char c)
    {
        if (c == '`')
            specialInput += "`";
    }
  
    //Initializes variables and class instances
    @Override
    public void init(GameContainer gc) throws SlickException 
    {
    	//Initializes a the selector class, passing it the width and height of the screen
    	selector = new Selector(screen_width, screen_height);
    	//Initializes the debug class which will be used for testing render and update methods (NOTE::Could be used for other debug functions. Put anything that the player should not see, but could be useful in there)
    	debug = new Debug();
    	//Initializes the special input variable to a string with nothing inside so that it can be compared without throwing an error if a special key was not pressed
    	specialInput = "";
    	//Initializes the music (NOTE::The path to the file where the music is located is sent as a string variable when initialized. Music class only supports two formats it seems. We will use wavs as those seem more common)
    	song = new Music("data/WaltzOfFatCats.wav");
    	radar = new RadarCircles(screen_width, screen_height);
    	try {rhythmFile = new BufferedWriter(new FileWriter("data/song.txt"));} catch (IOException e) {}
    	pause = new Image("data/PauseDown.png");
    	gamePaused = true;
    	
    	//-----TESTING-----
    	//Testing to see if the music was loaded correctly and plays
    	//Sets the music volume to max
    	song.setVolume(1);
    	//Starts to play the song
    	if (!gamePaused)
    		song.play();
    }
    
    //Updates the game state (NOTE::Normally called roughly ever 16 ms with slight fluctuation. To check time between updates on current machine activate debug mode with `)
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
    	//Sends the current time in ms to the debug functions to determine the time between updates
    	debug.setUpdateTimes(gc.getTime());
    	
    	//Sets a variable which will grab input using slick libraries
    	final Input input = gc.getInput();
    	
    	//Checks to see if a special input key was pressed
    	if (specialInput.equals("`"))
    	{
    		//Toggles debug mode on and off
    		debug.debugToggle();
    		//Sets the specialInput variable back to an empty string so that it can be used again
    		specialInput = "";
    	}
    	
    	float selectorAngle;
    	if (input.isKeyPressed(Input.KEY_SPACE))
    	{
    		radar.keyPressed(' ');
    		selectorAngle = selector.getAngle() + 180;
    		if (selectorAngle >= 360)
    			selectorAngle -= 360;
    		String circle = "(" + Math.round(selectorAngle) + "," + selector.getRotations() + ",5)";
    		try {rhythmFile.write(circle); rhythmFile.newLine();} catch (IOException e) {}
    		debug.writeCircle(circle);
    	}
    	
    	if (input.isKeyPressed(Input.KEY_H))
    	{
    		radar.keyPressed('h');
    		selectorAngle = selector.getAngle() + 180;
    		if (selectorAngle >= 360)
    			selectorAngle -= 360;
    		String circle = "(" + Math.round(selectorAngle) + "," + selector.getRotations() + ",4)";
    		try {rhythmFile.write(circle); rhythmFile.newLine();} catch (IOException e) {}
    		debug.writeCircle(circle);
    	}
    	
    	if (input.isKeyPressed(Input.KEY_J))
    	{
    		radar.keyPressed('j');
    		selectorAngle = selector.getAngle() + 180;
    		if (selectorAngle >= 360)
    			selectorAngle -= 360;
    		String circle = "(" + Math.round(selectorAngle) + "," + selector.getRotations() + ",3)";
    		try {rhythmFile.write(circle); rhythmFile.newLine();} catch (IOException e) {}
    		debug.writeCircle(circle);
    	}
    	
    	if (input.isKeyPressed(Input.KEY_K))
    	{
    		radar.keyPressed('k');
    		selectorAngle = selector.getAngle() + 180;
    		if (selectorAngle >= 360)
    			selectorAngle -= 360;
    		String circle = "(" + Math.round(selectorAngle) + "," + selector.getRotations() + ",2)";
    		try {rhythmFile.write(circle); rhythmFile.newLine();} catch (IOException e) {}
    		debug.writeCircle(circle);
    	}
    	
    	if (input.isKeyPressed(Input.KEY_L))
    	{
    		radar.keyPressed('l');
    		selectorAngle = selector.getAngle() + 180;
    		if (selectorAngle >= 360)
    			selectorAngle -= 360;
    		String circle = "(" + Math.round(selectorAngle) + "," + selector.getRotations() + ",1)";
    		try {rhythmFile.write(circle); rhythmFile.newLine();} catch (IOException e) {}
    		debug.writeCircle(circle);
    	}
    	
    	if (input.isKeyPressed(Input.KEY_ESCAPE))
    	{
    		try {rhythmFile.close();} catch (IOException e) {}
    	}
    	
    	if (input.getMouseX() >= 0 && input.getMouseX() <= pause.getWidth() && input.getMouseY() >= gc.getScreenHeight() - pause.getHeight() && input.getMouseY() <= gc.getScreenHeight())
    	{
    		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
    		{
    			if (!gamePaused)
    			{
    				song.pause();
    				pause = new Image("data/PauseDown.png");
    			}
    			else
    			{
    				if (first == 0)
    				{
    					first++;
    					song.play();
    				}
    				else
    					song.resume();
    				pause = new Image("data/Pause.png");
    			}
    			gamePaused = !gamePaused;
    		}
    	}
    	
    	//Example of how to use the built in slick input to detect input
    	/*if (input.isKeyPressed(Input.KEY_BACKSLASH))
    	{
    		debug.debugToggle();
    	}*/
    	
    	if (!gamePaused)
    	{
    		//Updates the selctors position
    		selector.updateSelector();
    	}
    	radar.update();
    }
    
    //Renders the game
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
    	g.setAntiAlias(true);
    	
    	//Sends the current time in ms to the debug functions to determine the time between renders
    	debug.setRenderTimes(gc.getTime());
    	
    	g.drawImage(pause, 0, gc.getScreenHeight() - pause.getHeight());
    	
    	radar.draw(g);
    	
    	//Draws debug mode
    	debug.draw(gc, g);
    	
    	//Draw the selector
    	selector.drawSelector(g);
    	
    	//Turn off FPS counter in top left
    	gc.setShowFPS(false);
    }
    
    public static MainRhythms getReference()
    {
    	MainRhythms Game = new MainRhythms();
    	return Game;
    }
  
    public static void main(String[] args) throws SlickException, InterruptedException, BrokenBarrierException
    {
        AppGameContainer app = new AppGameContainer( new MainRhythms() );
         
        screen_width = app.getScreenWidth();
        screen_height = app.getScreenHeight();
         
        app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), false);
        app.setFullscreen(true);
        app.setTargetFrameRate(60);
        app.setVSync(true);
        app.start();
    }
}