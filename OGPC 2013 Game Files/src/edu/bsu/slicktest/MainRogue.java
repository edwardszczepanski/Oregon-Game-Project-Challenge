package edu.bsu.slicktest;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.util.ResourceLoader;

public class MainRogue extends BasicGame {
  
	//Set variable to class instances
	MapGen map = new MapGen(height);
	Sidebar bar;
	Player p1;
	
	//Set variable to each image
	Image dungeonFloor = null;
	Image stairUp = null;
	Image stairDown = null;
	Image menuScreen = null;
	Image menu = null;
	Image characterSheet = null;
	Image plus = null;
	Image spellSelect = null;
	Image exit = null;
	Image spellsheetupgrade = null;
	Image sidebar = null;
	
	//Couple ints used in game
	int rooms = 10;
	int numMonster = 5;
	int numCurrentMonster = 5;
	
	//True false statments for game
	boolean gameOver = false;
	boolean start = true;
	boolean passStart = false;
	boolean charactersheetisopen = false;
	boolean spellsheetisopen = false;
	boolean controls = false;
	
	//Sets font variables
	TrueTypeFont font;
	TrueTypeFont font2;
	
	//Gets screen size to base map on
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int he = (int) screenSize.getHeight();
	static int height = he - 50;
	static int width = 1024;
	
	//Array of monsters
	ArrayList<Monster> monsters = new ArrayList<Monster>(1);
	
    public MainRogue()
    {
        super("Slick2D Path2Glory - Rouge");
    }
  
    @Override
    public void init(GameContainer gc) throws SlickException 
    {
    	//Declare fonts
    	Font awtFont = new Font("Times New Roman", Font.PLAIN, 55);
    	font = new TrueTypeFont(awtFont, false);
    	
    	//Declare images
    	characterSheet = new Image("data/CharacterScreen.png");
    	spellSelect = new Image("data/spells.png");
    	spellsheetupgrade = new Image("data/spellsheet.png");
    	plus = new Image("data/plus.png");
    	exit = new Image("data/exit.png");
        dungeonFloor = new Image("data/DungeonFloor1.png");
        stairUp = new Image("data/StairUp.png");
        stairDown = new Image("data/StairDown.png");
        menu = new Image("data/Menu.png");
        
        //Read map from text file into array
        map.readMap();
        
        //Declare instances of classes
        p1 = new Player(map.map, map, p1, height);
        bar = new Sidebar(p1);
        for (int i = 0; i < numCurrentMonster; i++)
        	monsters.add(i, new Monster(map.map, p1, height));
    }
  
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
    	//Checks monsters are alive, removes monsters, and adds EXP to player for kill
    	for (int i = 0; i < numCurrentMonster; i++)
    		if (monsters.get(i).alive == false)
    		{
    			monsters.remove(i);
    			numCurrentMonster--;
    			p1.gainXp();
    		}
    	
    	//Checks for gameover
    	if (!p1.alive)
    		gameOver = true;
    	
        Input input = gc.getInput();
        
        if(!charactersheetisopen)
        {
        	//Open Character sheet
        	if(input.isKeyPressed(Input.KEY_U) && passStart && !start)
	        	charactersheetisopen = true;
        	
        	//Detect mouse button on main menu
			if (input.isMousePressed(input.MOUSE_LEFT_BUTTON) && start) 
			{
				if(input.getMouseX() > 255 && input.getMouseX() < 805 && input.getMouseY() > 704 && input.getMouseY() < 760)
					gc.exit();
				else if(input.getMouseX() > 246 && input.getMouseX() < 996 && input.getMouseY() > 446 && input.getMouseY() < 508)
				{
					start = false;
					passStart = true;
				}
				else if(input.getMouseX() > 251 && input.getMouseX() < 967 && input.getMouseY() > 576 && input.getMouseY() < 634)
					controls = true;
			}
			
			//Movement
	        if(input.isKeyPressed(Input.KEY_A))
	        {
	        	p1.move('L', monsters, numCurrentMonster);
	        	for (int i = 0; i < numCurrentMonster; i++)
	        		monsters.get(i).move(monsters, i, numCurrentMonster);
	        }
	        if(input.isKeyPressed(Input.KEY_D))
	        {
	        	if (p1.move('R', monsters, numCurrentMonster))
	        	{
	        		for (int i = 0; i < numMonster; i++)
	        		{
	        			monsters.add(i, new Monster(map.map, p1, height));
	        			numCurrentMonster++;
	        		}
	        		p1.stair = false;
	        	}
	        	for (int i = 0; i < numCurrentMonster; i++)
	        		monsters.get(i).move(monsters, i, numCurrentMonster);
	        }
	        if(input.isKeyPressed(Input.KEY_W))
	        {
	        	p1.move('F', monsters, numCurrentMonster);
	        	for (int i = 0; i < numCurrentMonster; i++)
	        		monsters.get(i).move(monsters, i, numCurrentMonster);
	        }
	        if(input.isKeyPressed(Input.KEY_S))
	        {
	        	p1.move('B', monsters, numCurrentMonster);
	        	for (int i = 0; i < numCurrentMonster; i++)
	        		monsters.get(i).move(monsters, i, numCurrentMonster);
	        }
	        
	        //Attacking
	        if(input.isKeyPressed(Input.KEY_SPACE))
	        {
	        	for (int i = 0; i < numCurrentMonster; i++)
	        		if (monsters.get(i).checkPlayer())
	        			monsters.get(i).loseHP();
	        	for (int i = 0; i < numCurrentMonster; i++)
	        		monsters.get(i).move(monsters, i, numCurrentMonster);
	        }
	        
	        //Cheats
		    if(input.isKeyPressed(Input.KEY_Z))
		    	for (int i = 0; i < numCurrentMonster; i++)
		       		monsters.get(i).alive = false;
		    if(input.isKeyPressed(Input.KEY_L))
		       	p1.loseHP();
		    if(input.isKeyPressed(Input.KEY_X))
		       	p1.gainXp();
		    if(input.isKeyPressed(Input.KEY_M))
		    	if(p1.mana != 0)
		    		p1.mana--;
	        
	        //Access menu
	        if(input.isKeyPressed(Input.KEY_ESCAPE) && !charactersheetisopen && passStart)
	        {
	        	if (start)
	        		start = false;
	        	else if (!start)
	        		start = true;
	        }
	        
        }
        
        //Character sheet code
        else if(charactersheetisopen)
        {
        	if(p1.skillpoints == 0)
        	{
	            if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
	            	if(input.getMouseX() > 400 && input.getMouseX() < 550 && input.getMouseY() > 25 && input.getMouseY() < 100)
	                {
	            		if(spellsheetisopen == false)
	            		{
	            			spellsheetisopen = true;
	            			charactersheetisopen = false;
	            		}
	            		else
	            		{
	            			spellsheetisopen = false;
	            			charactersheetisopen = true;
	            		}
	                }
	                if(input.getMouseX() > 924 && input.getMouseX() < 1024 && input.getMouseY() > 0 && input.getMouseY() < 100)
	                {
	                	System.out.println("hi");
	                	charactersheetisopen = false;
	                }
	            }
	            if(input.isKeyPressed(Input.KEY_U))
	            {
	            	charactersheetisopen = false;
	            }
        	}
        	
        	else if(p1.skillpoints != 0)
            {
        		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
        			if(input.getMouseX() > 400 && input.getMouseX() < 550 && input.getMouseY() > 25 && input.getMouseY() < 100)
                    {
                		if(spellsheetisopen == false)
                		{
                			spellsheetisopen = true;
                			charactersheetisopen = false;
                		}
                		else
                		{
                			spellsheetisopen = false;
                			charactersheetisopen = true;
                		}
                    }
	                if(input.getMouseX() > 924 && input.getMouseX() < 1024 && input.getMouseY() > 0 && input.getMouseY() < 100)
	                {
	                	charactersheetisopen = false;
	                }
	                if(input.getMouseX() > 900 && input.getMouseX() < 964 && input.getMouseY() > 200 && input.getMouseY() < 264)
	                {
	                	if (p1.MAX_HP != 200)
	                	{
	                		p1.MAX_HP++;
	                		p1.skillpoints--;
	                	}
	                }
	                if(input.getMouseX() > 900 && input.getMouseX() < 964 && input.getMouseY() > 300 && input.getMouseY() < 364)
	                {
	                	if (p1.MAX_mana != 100)
	                	{
	                		p1.MAX_mana++;
	                		p1.skillpoints--;
	                	}
	                }
	            }
            }
        }
    }
  
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
    	//Turn off FPS counter in top left
    	gc.setShowFPS(false);
    	
    	//Display character sheet
    	if (charactersheetisopen)
    	{
    		characterSheet.draw(0,0);
    		spellSelect.draw(400,25);
    		exit.draw(924, 0);
    		font.drawString(100, 25, "LEVEL " + p1.level);
    		font.drawString(400, 200, "HEALTH " + p1.MAX_HP);
    		font.drawString(400, 300, "MANA " + p1.MAX_mana);
    		if(p1.skillpoints != 0)
    		{
    			font.drawString(575, 25, "SkillPoints " + p1.skillpoints);
    			if (p1.MAX_HP != 200)
    				plus.draw(900, 200);
    			if (p1.MAX_mana != 200)
    				plus.draw(900, 300);
    		}
    	}
    	
    	//Display spell sheet
    	else if (spellsheetisopen)
    	{
    		spellsheetupgrade.draw(0,0);
    		
    	}
    	
    	//Display menu
    	else if (start)
    	{
    		menu.draw(0, 0);
    		if (controls)
    		{
    			
    		}
    	}
    	
    	//Gameover screen
    	else if (gameOver)
    		g.drawString("GAME OVER!!! :( ", 461, 461);
    	
    	//Standard game display
    	else
    	{
	    	int counterDisplayX = 0;
	    	int counterDisplayY = 0;
	    	
	    	for (int row = 0; row < map.map.dungeon.length; row++)
			{
				for (int column = 0; column < map.map.dungeon[row].length; column++)
				{
					if (map.map.dungeon[row][column] == 1)
						dungeonFloor.draw(counterDisplayX, counterDisplayY, 2);
					else if (map.map.dungeon[row][column] == 2)
					{
						dungeonFloor.draw(counterDisplayX, counterDisplayY, 2);
						stairUp.draw(counterDisplayX, counterDisplayY, 1);
					}
					counterDisplayX = counterDisplayX + (width / 32);
				}
				counterDisplayY = counterDisplayY + (height / 32);
				counterDisplayX = 0;
			}
	    	p1.drawPlayer();
	    	bar.displaySidebar(g);
	    	
	    	for (int i = 0; i < numCurrentMonster; i++)
	    		monsters.get(i).drawMonster();
    	}
    }
    
    public static MainRogue getReference()
    {
    	MainRogue Game = new MainRogue();
    	return Game;
    }
  
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer( new MainRogue() );
         
         app.setDisplayMode(1224, height, false);
         app.start();
    }
}