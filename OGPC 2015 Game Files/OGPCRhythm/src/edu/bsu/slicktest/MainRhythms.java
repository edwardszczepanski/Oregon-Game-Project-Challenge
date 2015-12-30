package edu.bsu.slicktest;

//https://code.google.com/p/gate541/wiki/SlickWindowsDeployment

import java.util.concurrent.BrokenBarrierException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MainRhythms extends BasicGame
{
	//Creates instances of classes that will be needed in this class
	Debug debug;
	InGameState game;
	MainMenuState menu;
	//Creates a variable for the game state
	int GameState;
	//Creates a variable for special keyboard input that is not support in the slick library
	String specialInput;
	
	//Constructor for this class which titles the LWJGL display
    public MainRhythms()
    {
        super("Rhythms");
    }
  
    //Initializes variables and class instances
    @Override
    public void init(GameContainer gc) throws SlickException 
    {
    	//Initializes the special input variable to a string with nothing inside so that it can be compared without throwing an error if a special key was not pressed
    	specialInput = "";
    	//Initializes several instances of classes which are used within this class
    	debug = new Debug();
    	game = new InGameState(gc);
    	menu = new MainMenuState();
    	//Initializes the GameState variable to the mainmenu game state (NOTE:: 0=main menu 1=game)
    	GameState = 0;
    }
    
    //Creates a function that tests for special input that is not detected by slick2d
    @Override
    public void keyPressed(int key, char c)
    {
        if (c == '`')
            specialInput += "`";
    }
    
    //Updates the game state (NOTE::Normally called roughly ever 16 ms with slight fluctuation. To check time between updates on current machine activate debug mode with `)
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
    	//Sends the current time in ms to the debug functions to determine the time between updates
    	debug.setUpdateTimes(gc.getTime());
    	
    	//Sets a variable which will grab input using slick libraries
    	final Input input = gc.getInput();
    	
    	//Checks the gamestate to determine what to do
    	if (GameState == 0)
    	{
    		//Calls the update function for the menu which will return true if the user presses enter on the play button
    		if (menu.update(input))
    		{
    			//Clears the input record so that input given to the menu will not activate statements once inside the game
    			input.clearKeyPressedRecord();
    			//Sets the gamestate to the game
    			GameState = 1;
    		}
    	}
    	else if (GameState == 1)
    	{
    		//Calls the update function in game and returns the special input to detect keys that are not supported in the slick library
    		specialInput = game.update(input, specialInput, debug);
    	}
    }
    
    //Renders the game
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
    	//Sends the current time in ms to the debug functions to determine the time between renders
    	debug.setRenderTimes(gc.getTime());
    	
    	g.setAntiAlias(true);
    	//Turn off FPS counter in top left
    	gc.setShowFPS(false);
    	
    	if (GameState == 0)
    	{
    		menu.render(g, gc);
    	}
    	
    	else if (GameState == 1)
    	{
    		game.render(gc, g, debug);
    	}
    }
    
    public static MainRhythms getReference()
    {
    	MainRhythms Game = new MainRhythms();
    	return Game;
    }
  
    public static void main(String[] args) throws SlickException, InterruptedException, BrokenBarrierException
    {
        AppGameContainer app = new AppGameContainer( new MainRhythms() );
         
        app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), false);
        app.setFullscreen(true);
        app.setTargetFrameRate(60);
        app.setVSync(true);
        app.start();
    }
}