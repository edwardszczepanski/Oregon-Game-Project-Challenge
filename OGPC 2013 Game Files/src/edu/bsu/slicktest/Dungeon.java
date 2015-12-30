package edu.bsu.slicktest;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Dungeon
{
	//Declare a 2-d for the dungeon
	int [][] dungeon = new int[32][32];
	
	//Declare the dungeon to all 0's
	public void declareDungeon()
	{
		for (int row = 0; row < dungeon.length; row++)
		{
			for (int column = 0; column < dungeon[row].length; column++)
			{
				dungeon[row][column] = 0;
			}
		}
	}
}