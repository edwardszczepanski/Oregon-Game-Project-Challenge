package edu.bsu.slicktest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


import java.util.*;

public class MapGen {

	//Variables to instances of classes
	//Random to generate random numbers (java built in class)
	Random generator = new Random();
	Dungeon map = new Dungeon();
	
	//Couple ints
	//Stair coordinates
	//Height of screen as well
	int stairX;
	int stairY;
	int height;
	
	//String to make sure two maps not picked in a row
	String lastMap;
	
	MapGen(int he)
	{
		//Takes in height from MainRogue and sets it to height variable in this class
		height = he;
	}
	
	//Select which text file to read the map from
	private String selectMap()
	{
		String map;
		String[] maps = new String[11];
		
		for (int i = 0; i < 11; i++)
		{
			maps[i] = "data/Textmap" + i + ".txt";
		}
		
		map = maps[generator.nextInt(11)];
		
		while (true)
		{
			if (map == lastMap)
				map = maps[generator.nextInt(11)];
			else if (map != lastMap)
				break;
		}
		
		lastMap = map;
		return map;
	}
	
	//Read the map and write it to the dungeon array
	public void readMap()
	{
		Reader reader = null;
		try {
			reader = new FileReader(selectMap());
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}

	    int data = 0;
		try {
			data = reader.read();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		int y = 0;
		int x = 0;
		int lineBreak1 = 0;
		int lineBreak2 = 1;
		int counter = 0;
	    while(data != -1)
	    {
	    	char dataChar = (char) data;
	        try {
				data = reader.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        if (dataChar != ' ')
	        {
	        	if (y > 0 && x == lineBreak1 && counter < 2)
	        		counter++;
	        	if (y > 0 && x == lineBreak2 && counter < 2)
	        		counter++;
	        	if (counter == 2)
	        	{
	        		counter++;
	        		x = x - 2;
	        	}
	        	else if (counter > 1)
	        	{
	        		if (dataChar == '2')
	        		{
	        			map.dungeon[y][x] = 2;
	        			stairX = (32) * x;
	        			stairY = (height / 32) * y;
	        		}
	        		if (dataChar == '1')
	        			map.dungeon[y][x] = 1;
	        		if (dataChar == '0')
	        			map.dungeon[y][x] = 0;
	        	}
	        	
	        	x++;
	        	if (x > 31)
	        	{
	        		y++;
	        		x = 0;
	        		counter = 0;
	        	}
	        }
	    }
	}
	
	//For debug purposes
	//Prints dungeon to console
	public void printMap()
	{
		for (int y = 0; y < 32; y++)
		{
			System.out.println(" ");
			for (int x = 0; x < 32; x++)
			{
				System.out.print(map.dungeon[x][y]);
			}
		}
	}
	//Original map generation
		/*private int xNodes[];
		private int yNodes[];
		
		public void boreRoom(int num)
		{
			xNodes = new int [num]; 
			yNodes = new int [num];
			
			for (int i = 0; i < num; i ++)
			{
				int roomX = generator.nextInt(8) + 1;
				int roomY = generator.nextInt(8) + 1;
				int cornerX = generator.nextInt(31) + 1;
				int cornerY = generator.nextInt(31) + 1;
				
				for (int y = 0; y < roomY && y + cornerY < 31; y++)
				{
					for (int x = 0; x < roomX && x + cornerX < 31; x++)
					{
						if (map.dungeon[cornerY + y][cornerX + x] == 1)
						{
							x = 31;
							y = 31;
							num--;
						}
						else
							map.dungeon[cornerY + y][cornerX + x] = 1;
					}
				}
			}
		}
		
		public void boreHall()
		{
			
		}
		
		public void initalRoom()
		{
			map.declareDungeon();
			
			for (int row = 13; row < 19; row++)
			{
				for (int column = 13; column < 19; column++)
				{
					map.dungeon[row][column] = 1;
				}
			}
		}*/
}
