package edu.bsu.slicktest;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player 
{
	//Create animation varibles for player animation
	Animation playerUp = new Animation();
	Animation playerRight = new Animation();
	Animation playerDown = new Animation();
	Animation playerLeft = new Animation();
	Animation playerAnimation = new Animation();
	
	//True false statements
	//Alive for player being alive
	//Stair for whether player can access stair and whether they have access the stair
	boolean alive = true;
	boolean stair = false;
	
	//Sprite sheet variable to pull player animations from
	static SpriteSheet playerSprites = null;
	
	//Couple instances of classes
	Dungeon map;
	MapGen mapGen;
	Player p1;
	
	//Couple of ints
	//Player mana, exp, HP, height of screen, x y coordinate, etc.
	int playerX = 0;
	int playerY = 0;
	int height;
	int HP = 100;
	int MAX_HP = 100;
	int MAX_mana = 10;
	int mana = 10;
	int exp = 0;
	int reqexp = 20;
	int level = 1;
	int skillpoints = 0;
	
	public Player(Dungeon Map, MapGen gen, Player play, int he)
	{
		//Declaring the sprite sheet to an image to draw the sprites from
		try {
			playerSprites = new SpriteSheet("data/GenericHumanoid.png", 32, 32);
		} catch (SlickException e2) {
			e2.printStackTrace();
		}
		
		//Duration of animation (i.e. fast or slow)
		int duration = 300;
		
		//Adds frames the player animations thus creating an animated sprite
		playerUp.addFrame(playerSprites.getSprite(0,0), duration);
		playerUp.addFrame(playerSprites.getSprite(1,0), duration);
		playerUp.addFrame(playerSprites.getSprite(2,0), duration);
		playerRight.addFrame(playerSprites.getSprite(0,1), duration);
		playerRight.addFrame(playerSprites.getSprite(1,1), duration);
		playerRight.addFrame(playerSprites.getSprite(2,1), duration);
		playerDown.addFrame(playerSprites.getSprite(0,2), duration);
		playerDown.addFrame(playerSprites.getSprite(1,2), duration);
		playerDown.addFrame(playerSprites.getSprite(2,2), duration);
		playerLeft.addFrame(playerSprites.getSprite(0,3), duration);
		playerLeft.addFrame(playerSprites.getSprite(1,3), duration);
		playerLeft.addFrame(playerSprites.getSprite(2,3), duration);
		
		//Declares the starting animation for the player
		playerAnimation = playerDown;
		
		//Class instances set to instances passed from MainRogue
		height = he;
		map = Map;
		mapGen = gen;
		p1 = play;
		
		//Random x y coordinates to randomly place the player in the dungeon
		int randomNumberX;
		int randomNumberY;
		
		//Randomly places the monster in the dungeon
		while(true)
		{
			randomNumberX = 0 + (int)(Math.random() * ((31 - 0) + 1));
			randomNumberY = 0 + (int)(Math.random() * ((31 - 0) + 1));
			if (map.dungeon[randomNumberY][randomNumberX] == 1)
			{
				playerX = randomNumberX * (32);
				playerY = randomNumberY * (height / 32);
				break;
			}
		}
	}
	
	//Checks to make sure the move of the player is valid
	private boolean moveCheck(char direction, ArrayList<Monster> monsters, int numMonster)
	{
		boolean move = true;
		
		if (direction == 'L')
		{
			if (map.dungeon[(playerY/(height / 32))][(playerX/(32)) - 1] == 0)
				move = false;
			for (int i = 0; i < numMonster; i++)
				if (playerY == monsters.get(i).monsterY && playerX - (32) == monsters.get(i).monsterX)
					move = false;
			if (playerX - (32) == mapGen.stairX && playerY == mapGen.stairY)
				move = false;
		}
		else if (direction == 'R')
		{
			if (map.dungeon[(playerY/(height / 32))][(playerX/(32)) + 1] == 0)
				move = false;
			for (int i = 0; i < numMonster; i++)
				if (playerY == monsters.get(i).monsterY && playerX + (32) == monsters.get(i).monsterX)
					move = false;
			if (playerX + (32) == mapGen.stairX && playerY == mapGen.stairY)
			{
				for (int i = 0; i < numMonster; i++)
					if (monsters.get(i).alive)
						move = false;
				if (move)
					stair = true;
			}
		}
		else if (direction == 'F')
		{
			if (map.dungeon[(playerY/(height / 32)) - 1][(playerX/(32))] == 0)
				move = false;
			for (int i = 0; i < numMonster; i++)
				if (playerY - (height / 32) == monsters.get(i).monsterY && playerX == monsters.get(i).monsterX)
					move = false;
			if (playerX == mapGen.stairX && playerY - (height / 32) == mapGen.stairY)
				move = false;
		}
		else if (direction == 'B')
		{
			if (map.dungeon[(playerY/(height / 32)) + 1][(playerX/(32))] == 0)
				move = false;
			for (int i = 0; i < numMonster; i++)
				if (playerY + (height / 32) == monsters.get(i).monsterY && playerX == monsters.get(i).monsterX)
					move = false;
			if (playerX == mapGen.stairX && playerY + (height / 32) == mapGen.stairY)
				move = false;
		}
		
		return move;
	}
	
	//Moves the player
	//Also deals with recreating monsters and dungeon when the player accesses the stair
	public boolean move(char direction, ArrayList<Monster> monsters, int numMonster)
	{
		if (direction == 'L')
		{
			playerAnimation = playerLeft;
			
			if (moveCheck(direction, monsters, numMonster))
				playerX = playerX - (32);
		}
		else if (direction == 'R')
		{
			playerAnimation = playerRight;
			
			if (moveCheck(direction, monsters, numMonster))
			{
				if (!stair)
					playerX = playerX + (32);
				else
				{
					int randomNumberX;
					int randomNumberY;
					
					playerY = playerY - ((height / 32) / 2);
					playerX = playerX + (32);
					map.declareDungeon();
					mapGen.readMap();
					while(true)
					{
						randomNumberX = 0 + (int)(Math.random() * ((31 - 0) + 1));
						randomNumberY = 0 + (int)(Math.random() * ((31 - 0) + 1));
						if (map.dungeon[randomNumberY][randomNumberX] == 1)
						{
							playerX = randomNumberX * (32);
							playerY = randomNumberY * (height / 32);
							break;
						}
					}
				}
			}
		}
		else if (direction == 'F')
		{
			playerAnimation = playerUp;
			
			if (moveCheck(direction, monsters, numMonster))
				playerY = playerY - (height / 32);
		}
		else if (direction == 'B')
		{
			playerAnimation = playerDown;
			
			if (moveCheck(direction, monsters, numMonster))
				playerY = playerY + (height / 32);
		}
		if (!stair)
			return false;
		else
			return true;
	}
	
	//Draws the player
	public void drawPlayer()
	{
		if (alive && !stair)
			playerAnimation.draw(playerX, playerY);
	}
	
	//Makes the player lose HP and checks if player HP is 0
	public void loseHP()
	{
		HP--;
		if (HP == 0)
			alive = false;
	}
	
	//Makes the player gain XP and levels if the required XP is met
	public void gainXp()
	{
		exp = exp + 10;
		if(exp == reqexp)
		{
			exp = 0;
			level++;
			reqexp = (reqexp * 2) - ((level - 1) * 10);
			skillpoints = skillpoints + 10;
			HP = MAX_HP;
			mana = MAX_mana;
		}
	}
}
