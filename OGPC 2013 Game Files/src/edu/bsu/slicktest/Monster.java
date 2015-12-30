package edu.bsu.slicktest;

import java.awt.image.BufferedImage;
import org.newdawn.slick.Animation;
import java.io.File;
import org.newdawn.slick.SpriteSheet;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Monster 
{
	//Variable ints for monster
	int HP = 100;
	int monsterX = 0;
	int monsterY = 0;
	int height;
	
	//True false statements to check things in monster
	boolean attacked = false;
	boolean facingPlayer = false;
	boolean alive;
	
	//Creating a sprite sheet to do animations
	static SpriteSheet mySheet = null;
	
	//Creating new animations for the monster
	Animation monster = new Animation();
	Animation monsterAttackDown = new Animation();
	Animation monsterAttackRight = new Animation();
	Animation monsterAttackLeft = new Animation();
	Animation monsterAttackUp = new Animation();
	Animation current = monster;
	
	//Variables for instances of classes
	Dungeon map;
	Player p1;
	
	public Monster(Dungeon Map, Player player, int he)
	{	
		//Make the monster alive when it is called and created
		alive = true;
		
		//Declaring the sprite sheet to an image to draw the sprites from
		try {
			mySheet = new SpriteSheet("data/Enemy.png", 32, 32);
		} catch (SlickException e2) {
			e2.printStackTrace();
		}
		
		//Duration of animation (i.e. fast or slow)
		int duration = 300;
		
		//Adds frames the monster animations thus creating an animated sprite
		monster.addFrame(mySheet.getSprite(0,0), duration);
		monster.addFrame(mySheet.getSprite(1,0), duration);
		monster.addFrame(mySheet.getSprite(2,0), duration);
		monster.addFrame(mySheet.getSprite(3,0), duration);
		monsterAttackRight.addFrame(mySheet.getSprite(0,1), duration);
		monsterAttackRight.addFrame(mySheet.getSprite(1,1), duration);
		monsterAttackRight.addFrame(mySheet.getSprite(2,1), duration);
		monsterAttackRight.addFrame(mySheet.getSprite(3,1), duration);
		monsterAttackLeft.addFrame(mySheet.getSprite(0,2), duration);
		monsterAttackLeft.addFrame(mySheet.getSprite(1,2), duration);
		monsterAttackLeft.addFrame(mySheet.getSprite(2,2), duration);
		monsterAttackLeft.addFrame(mySheet.getSprite(3,2), duration);
		monsterAttackDown.addFrame(mySheet.getSprite(0,3), duration);
		monsterAttackDown.addFrame(mySheet.getSprite(1,3), duration);
		monsterAttackDown.addFrame(mySheet.getSprite(2,3), duration);
		monsterAttackDown.addFrame(mySheet.getSprite(3,3), duration);
		monsterAttackUp.addFrame(mySheet.getSprite(0,4), duration);
		monsterAttackUp.addFrame(mySheet.getSprite(1,4), duration);
		monsterAttackUp.addFrame(mySheet.getSprite(2,4), duration);
		monsterAttackUp.addFrame(mySheet.getSprite(3,4), duration);
		
		//Declare class instances passed form MainRogue to instances for Monster to use
		height = he;
		p1 = player;
		map = Map;
		
		//Random x y coordinates to randomly place monsters in the dungeon
		int randomNumberX;
		int randomNumberY;
		
		//Randomly places the monster in the dungeon
		while(true)
		{
			randomNumberX = 0 + (int)(Math.random() * ((31 - 0) + 1));
			randomNumberY = 0 + (int)(Math.random() * ((31 - 0) + 1));
			if (map.dungeon[randomNumberY][randomNumberX] == 1)
			{
				monsterX = randomNumberX * (32);
				monsterY = randomNumberY * (height / 32);
				if (monsterX != p1.playerX || monsterY != p1.playerY)
					break;
			}
		}
	}
	
	//Decreases the HP of the monster
	private void decreaseHP()
	{
		HP = HP - 100;
		if (HP == 0)
			alive = false;
	}
	
	//Checks to see if the player is adjacent and facing the monster
	public boolean checkPlayer()
	{
		boolean check = false;
		
		if (monsterY == p1.playerY && monsterX - (32) == p1.playerX)
			if (p1.playerAnimation == p1.playerRight)
				check = true;
		if (monsterY == p1.playerY && monsterX + (32) == p1.playerX)
			if (p1.playerAnimation == p1.playerLeft)
				check = true;
		if (monsterY - (height / 32) == p1.playerY && monsterX == p1.playerX)
			if (p1.playerAnimation == p1.playerDown)
				check = true;
		if (monsterY + (height / 32) == p1.playerY && monsterX == p1.playerX)
			if (p1.playerAnimation == p1.playerUp)
				check = true;
		
		return check;
	}
	
	//Make attacked true and call a function that decreases the monsters HP
	public void loseHP()
	{
		decreaseHP();
		attacked = true;
	}
	
	//Draw the monster if alive
	public void drawMonster()
	{
		if (alive)
			current.draw(monsterX, monsterY);
	}
	
	//Chooses a random direction for the monster to move
	private char chooseDirection()
	{
		int randomDirection;
		char D = 0;
		
		randomDirection = 0 + (int)(Math.random() * ((3 - 0) + 1));
		if (randomDirection == 0)
			D = 'L';
		else if (randomDirection == 1)
			D = 'R';
		else if (randomDirection == 2)
			D = 'U';
		else
			D = 'D';
		
		return D;
	}
	
	//Once given the direction checks to make sure that move is valid and reutrn true or false based on validity
	private boolean moveCheck(char direction, ArrayList<Monster> monsters, int currentMonster, int numMonster)
	{
		boolean move = true;
		
		if (direction == 'L')
		{
			if (map.dungeon[(monsterY/(height / 32))][(monsterX/(32) - 1)] == 0)
				move = false;
			if (monsterY == p1.playerY && monsterX - (32) == p1.playerX)
				move = false;
			for (int i = 0; i < numMonster; i++)
				if (i != currentMonster)
					if (monsterY == monsters.get(i).monsterY && monsterX - (32) == monsters.get(i).monsterX)
						move = false;
		}
		else if (direction == 'R')
		{
			if (map.dungeon[(monsterY/(height / 32))][(monsterX/(32)) + 1] == 0)
				move = false;
			if (monsterY == p1.playerY && monsterX + (32) == p1.playerX)
				move = false;
			for (int i = 0; i < numMonster; i++)
				if (i != currentMonster)
					if (monsterY == monsters.get(i).monsterY && monsterX + (32) == monsters.get(i).monsterX)
						move = false;
		}
		else if (direction == 'U')
		{
			if (map.dungeon[(monsterY/(height / 32)) - 1][(monsterX/(32))] == 0)
				move = false;
			if (monsterY - (height / 32) == p1.playerY && monsterX == p1.playerX)
				move = false;
			for (int i = 0; i < numMonster; i++)
				if (i != currentMonster)
					if (monsterY - (height / 32) == monsters.get(i).monsterY && monsterX == monsters.get(i).monsterX)
						move = false;
		}
		else if (direction == 'D')
		{
			if (map.dungeon[(monsterY/(height / 32)) + 1][(monsterX/(32))] == 0)
				move = false;
			if (monsterY + (height / 32) == p1.playerY && monsterX == p1.playerX)
				move = false;
			for (int i = 0; i < numMonster; i++)
				if (i != currentMonster)
					if (monsterY + (height / 32) == monsters.get(i).monsterY && monsterX == monsters.get(i).monsterX)
						move = false;
		}
		
		return move;
	}
	
	//Moves the monster, towards the player if in range, randomly if player is not in range
	public void move(ArrayList<Monster> monsters, int currentMonster, int numMonster)
	{
		char D = chooseDirection();
		boolean moved = false;
		
		if (!moved)
		{
			if (monsterY == p1.playerY && monsterX - (32) == p1.playerX)
			{
				current = monsterAttackLeft;p1.loseHP();
			}
			if (monsterY == p1.playerY && monsterX + (32) == p1.playerX)
			{
				current = monsterAttackRight;
				p1.loseHP();
			}
			if (monsterY - (height / 32) == p1.playerY && monsterX == p1.playerX)
			{
				current = monsterAttackUp;
				p1.loseHP();
			}
			if (monsterY + (height / 32) == p1.playerY && monsterX == p1.playerX)
			{
				current = monsterAttackDown;
				p1.loseHP();
			}
					
			/*if (monsterY == p1.playerY && monsterX - (32) == p1.playerX)
				current = monsterLeft;
			if (monsterY == p1.playerY && monsterX + (32) == p1.playerX)
				current = monsterRight;
			if (monsterY - (height / 32) == p1.playerY && monsterX == p1.playerX)
				current = monsterUp;
			if (monsterY + (height / 32) == p1.playerY && monsterX == p1.playerX)
				current = monsterIdle;*/
				
			if (Math.sqrt(Math.abs(monsterX/(32) - p1.playerX/(32)) + Math.abs(monsterY/(height / 32) - p1.playerY/(height / 32))) <= 2)
			{
				if (monsterX == p1.playerX)
				{
					if (monsterY < p1.playerY && moveCheck('D', monsters, currentMonster, numMonster))
					{
						current = monster;
						monsterY = monsterY + (height / 32);
					}
					else if (monsterY > p1.playerY && moveCheck('U', monsters, currentMonster, numMonster))
					{
						current = monster;
						monsterY = monsterY - (height / 32);
					}
				}
				else if (monsterY == p1.playerY)
				{
					if (monsterX < p1.playerX && moveCheck('R', monsters, currentMonster, numMonster))
					{
						current = monster;
						monsterX = monsterX + (32);
					}
					else if (monsterX > p1.playerX && moveCheck('L', monsters, currentMonster, numMonster))
					{
						current = monster;
						monsterX = monsterX - (32);
					}
				}
				else
				{
					int XorY = 0 + (int)(Math.random() * ((1 - 0) + 1));
					
					if (XorY == 0)
					{
						if (monsterX < p1.playerX && moveCheck('R', monsters, currentMonster, numMonster))
						{
							current = monster;
							monsterX = monsterX + (32);
						}
						else if (monsterX > p1.playerX && moveCheck('L', monsters, currentMonster, numMonster))
						{
							current = monster;
							monsterX = monsterX - (32);
						}
					}
					else if (XorY == 1)
					{
						if (monsterY < p1.playerY && moveCheck('D', monsters, currentMonster, numMonster))
						{
							current = monster;
							monsterY = monsterY + (height / 32);
						}
						else if (monsterY > p1.playerY && moveCheck('U', monsters, currentMonster, numMonster))
						{
							current = monster;
							monsterY = monsterY - (height / 32);
						}
					}
				}
	
			}
			else
			{
				if (D == 'L')
				{
					if (moveCheck(D, monsters, currentMonster, numMonster))
					{
						current = monster;
						monsterX = monsterX - (32);
					}
					else
						move(monsters, currentMonster, numMonster);
				}
				else if (D == 'R')
				{
					if (moveCheck(D, monsters, currentMonster, numMonster))
					{
						current = monster;
						monsterX = monsterX + (32);
					}
					else
						move(monsters, currentMonster, numMonster);
				}
				else if (D == 'U')
				{
					if (moveCheck(D, monsters, currentMonster, numMonster))
					{
						current = monster;
						monsterY = monsterY - (height / 32);
					}
					else
						move(monsters, currentMonster, numMonster);
				}
				else if (D == 'D')
				{
					if (moveCheck(D, monsters, currentMonster, numMonster))
					{
						current = monster;
						monsterY = monsterY + (height / 32);
					}
					else
						move(monsters, currentMonster, numMonster);
				}
			}
			moved = true;
		}
		
		if (!moved)
			move(monsters, currentMonster, numMonster);
	}
}
