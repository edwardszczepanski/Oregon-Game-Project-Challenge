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

//Incomplete class
//Eventual implementation, but not enough time
public class Spells 
{
	//Declares animations for the fire ball spell
	Animation FireballLeft = new Animation();
	Animation FireballRight = new Animation();
	Animation FireballUp = new Animation();
	Animation FireballDown = new Animation();
	Animation Fireball = new Animation();
	
	//Variable for sprite sheet for fire ball
	static SpriteSheet FireballSprite = null;
	
	//
	public Spells(Dungeon Map)
	{
		//Declare sprite sheet that the animations will come from
		try {
			FireballSprite = new SpriteSheet("data/fireballspell.png", 32, 32);
		} catch (SlickException e2) {
			e2.printStackTrace();
		}
		
		//Duration of animation (i.e. fast or slow)
		int duration = 300;
		
		//Fire ball animations declared to point on sprite sheet
		FireballLeft.addFrame(FireballSprite.getSprite(0, 0), duration);
		FireballLeft.addFrame(FireballSprite.getSprite(1, 0), duration);
		FireballLeft.addFrame(FireballSprite.getSprite(2, 0), duration);
		FireballLeft.addFrame(FireballSprite.getSprite(3, 0), duration);
		FireballLeft.addFrame(FireballSprite.getSprite(4, 0), duration);
		FireballLeft.addFrame(FireballSprite.getSprite(5, 0), duration);
		FireballLeft.addFrame(FireballSprite.getSprite(6, 0), duration);
		FireballLeft.addFrame(FireballSprite.getSprite(7, 0), duration);
		
		FireballRight.addFrame(FireballSprite.getSprite(0, 4), duration);
		FireballRight.addFrame(FireballSprite.getSprite(1, 4), duration);
		FireballRight.addFrame(FireballSprite.getSprite(2, 4), duration);
		FireballRight.addFrame(FireballSprite.getSprite(3, 4), duration);
		FireballRight.addFrame(FireballSprite.getSprite(4, 4), duration);
		FireballRight.addFrame(FireballSprite.getSprite(5, 4), duration);
		FireballRight.addFrame(FireballSprite.getSprite(6, 4), duration);
		FireballRight.addFrame(FireballSprite.getSprite(7, 4), duration);
		
		FireballUp.addFrame(FireballSprite.getSprite(0, 2), duration);
		FireballUp.addFrame(FireballSprite.getSprite(1, 2), duration);
		FireballUp.addFrame(FireballSprite.getSprite(2, 2), duration);
		FireballUp.addFrame(FireballSprite.getSprite(3, 2), duration);
		FireballUp.addFrame(FireballSprite.getSprite(4, 2), duration);
		FireballUp.addFrame(FireballSprite.getSprite(5, 2), duration);
		FireballUp.addFrame(FireballSprite.getSprite(6, 2), duration);
		FireballUp.addFrame(FireballSprite.getSprite(7, 2), duration);
		
		FireballDown.addFrame(FireballSprite.getSprite(0, 6), duration);
		FireballDown.addFrame(FireballSprite.getSprite(1, 6), duration);
		FireballDown.addFrame(FireballSprite.getSprite(2, 6), duration);
		FireballDown.addFrame(FireballSprite.getSprite(3, 6), duration);
		FireballDown.addFrame(FireballSprite.getSprite(4, 6), duration);
		FireballDown.addFrame(FireballSprite.getSprite(5, 6), duration);
		FireballDown.addFrame(FireballSprite.getSprite(6, 6), duration);
		FireballDown.addFrame(FireballSprite.getSprite(7, 6), duration);
		
	}
}
