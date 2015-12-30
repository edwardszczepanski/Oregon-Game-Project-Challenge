package edu.bsu.slicktest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.awt.Point;
import java.util.HashMap;

public class MainMenuState
{
	Image title;
	Image start;
	Image selector;
	Image controls;
	Image tutorial;
	Image keyboard;
	Image circ1;
	Image circ2;
	Image circ3;
	Image circ4;
	Image circ5;
	Animation backgroundAnimation;
	int delta;
	int MenuState;
	Point circ1Pos;
	Point circ2Pos;
	Point circ3Pos;
	Point circ4Pos;
	Point circ5Pos;
	int circleChanger = 0;
	Color selected = new Color(255, 255, 255);
	
	HashMap<Integer, Point> keyMap = new HashMap<Integer, Point>();
	
	MainMenuState()
	{
		//HashMap<String, Point> keyMap = new HashMap<String, Point>();
		//keyMap.put("enter", new Point(200, 200));
		//keyMap.get("enter");
		keyMap.put(Input.KEY_Q, new Point(167, 164));
		keyMap.put(Input.KEY_W, new Point(249, 164));
		keyMap.put(Input.KEY_E, new Point(331, 164));
		keyMap.put(Input.KEY_R, new Point(413, 164));
		keyMap.put(Input.KEY_T, new Point(495, 164));
		keyMap.put(Input.KEY_Y, new Point(578, 164));
		keyMap.put(Input.KEY_U, new Point(660, 164));
		keyMap.put(Input.KEY_I, new Point(742, 164));
		keyMap.put(Input.KEY_O, new Point(824, 164));
		keyMap.put(Input.KEY_P, new Point(907, 164));
		keyMap.put(Input.KEY_A, new Point(167, 246));
		keyMap.put(Input.KEY_S, new Point(249, 246));
		keyMap.put(Input.KEY_D, new Point(331, 246));
		keyMap.put(Input.KEY_F, new Point(413, 246));
		keyMap.put(Input.KEY_G, new Point(495, 246));
		keyMap.put(Input.KEY_H, new Point(578, 246));
		keyMap.put(Input.KEY_J, new Point(660, 246));
		keyMap.put(Input.KEY_K, new Point(742, 246));
		keyMap.put(Input.KEY_L, new Point(824, 246));
		keyMap.put(Input.KEY_SEMICOLON, new Point(906, 246));
		keyMap.put(Input.KEY_Z, new Point(167, 329));
		keyMap.put(Input.KEY_X, new Point(249, 329));
		keyMap.put(Input.KEY_C, new Point(331, 329));
		keyMap.put(Input.KEY_V, new Point(412, 329));
		keyMap.put(Input.KEY_B, new Point(495, 329));
		keyMap.put(Input.KEY_N, new Point(577, 329));
		keyMap.put(Input.KEY_M, new Point(659, 329));
		keyMap.put(Input.KEY_SPACE, new Point(503, 419));
		
		try
		{
			title = new Image("data/Title.png");
			start = new Image("data/Start.png");
			selector = new Image("data/Selector.png");
			controls = new Image("data/Controls.png");
			tutorial = new Image("data/Tutorial.png");
			backgroundAnimation = new Animation();
			for (int i = 1; i <= 6; i++)
				backgroundAnimation.addFrame(new Image("data/Background_" + i + ".png"), 250);
			for (int i = 6; i >= 1; i--)
				backgroundAnimation.addFrame(new Image("data/Background_" + i + ".png"), 250);
			keyboard = new Image("data/Untitled.png");
			circ1 = new Image("data/Number 1.png");
			circ2 = new Image("data/Number 2.png");
			circ3 = new Image("data/Number 3.png");
			circ4 = new Image("data/Number 4.png");
			circ5 = new Image("data/Number 5.png");
		} catch (SlickException e) {}
		
		delta = 100;
		
		circ1Pos = new Point(906, 246);
		circ2Pos = new Point(824, 246);
		circ3Pos = new Point(742, 246);
		circ4Pos = new Point(660, 246);
		circ5Pos = new Point(503, 419);
		
		MenuState = 0;
	}
	
	public boolean update(Input input)
	{
		if (MenuState == 0)
		{
			if (input.isKeyPressed(Input.KEY_DOWN))
			{
				if (delta == 100)
					delta = 25;
				else if (delta == 25)
					delta = -50;
			}
			if (input.isKeyPressed(Input.KEY_UP))
			{
				if (delta == -50)
					delta = 25;
				else if (delta == 25)
					delta = 100;
			}
			if (input.isKeyPressed(Input.KEY_ENTER))
			{
				if (delta == 100)
					return true;
				else if (delta == 25)
					MenuState = 1;
				else if (delta == -50)
					MenuState = 2;
			}
		}
		
		else if (MenuState == 1)
		{
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				if (input.getMouseX() >= 0 && input.getMouseY() >= 0 && input.getMouseX() <= 150 && input.getMouseY() <= 25)
				{
					if (circleChanger == 0)
					{
						selected = new Color(255,233,10);
						circleChanger = 1;
					}
					else
					{
						selected = new Color(255, 255, 255);
						circleChanger = 0;
					}
				}
				else if (input.getMouseX() >= 0 && input.getMouseY() >= 25 && input.getMouseX() <= 150 && input.getMouseY() <= 50)
				{
					if (circleChanger != 2)
					{
						selected = new Color(255, 255, 255);
						circleChanger = 2;
					}
					else
					{
						selected = new Color(255,233,10);
						circleChanger = 0;
					}
				}
			}
			if (input.isKeyPressed(Input.KEY_Q))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_Q);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_Q);
			}
			else if (input.isKeyPressed(Input.KEY_W))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_W);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_W);
			}
			else if (input.isKeyPressed(Input.KEY_E))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_E);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_E);
			}
			else if (input.isKeyPressed(Input.KEY_R))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_R);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_R);
			}
			else if (input.isKeyPressed(Input.KEY_T))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_T);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_T);
			}
			else if (input.isKeyPressed(Input.KEY_Y))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_Y);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_Y);
			}
			else if (input.isKeyPressed(Input.KEY_U))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_U);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_U);
			}
			else if (input.isKeyPressed(Input.KEY_I))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_I);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_I);
			}
			else if (input.isKeyPressed(Input.KEY_O))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_O);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_O);
			}
			else if (input.isKeyPressed(Input.KEY_P))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_P);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_P);
			}
			else if (input.isKeyPressed(Input.KEY_A))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_A);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_A);
			}
			else if (input.isKeyPressed(Input.KEY_S))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_S);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_S);
			}
			else if (input.isKeyPressed(Input.KEY_D))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_D);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_D);
			}
			else if (input.isKeyPressed(Input.KEY_F))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_F);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_F);
			}
			else if (input.isKeyPressed(Input.KEY_G))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_G);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_G);
			}
			else if (input.isKeyPressed(Input.KEY_H))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_H);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_H);
			}
			else if (input.isKeyPressed(Input.KEY_J))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_J);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_J);
			}
			else if (input.isKeyPressed(Input.KEY_K))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_K);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_K);
			}
			else if (input.isKeyPressed(Input.KEY_L))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_L);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_L);
			}
			else if (input.isKeyPressed(Input.KEY_SEMICOLON))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_SEMICOLON);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_SEMICOLON);
			}
			else if (input.isKeyPressed(Input.KEY_Z))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_Z);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_Z);
			}
			else if (input.isKeyPressed(Input.KEY_X))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_X);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_X);
			}
			else if (input.isKeyPressed(Input.KEY_C))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_C);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_C);
			}
			else if (input.isKeyPressed(Input.KEY_V))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_V);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_V);
			}
			else if (input.isKeyPressed(Input.KEY_B))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_B);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_B);
			}
			else if (input.isKeyPressed(Input.KEY_N))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_N);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_N);
			}
			else if (input.isKeyPressed(Input.KEY_M))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_M);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_M);
			}
			else if (input.isKeyPressed(Input.KEY_SPACE))
			{
				if (circleChanger == 1)
					circ1Pos = keyMap.get(Input.KEY_SPACE);
				if (circleChanger == 2)
					circ2Pos = keyMap.get(Input.KEY_SPACE);
			}
			else if (input.isKeyPressed(Input.KEY_ESCAPE))
				MenuState = 0;
			
		}

		else if (MenuState == 1 && input.isKeyPressed(Input.KEY_ESCAPE) || MenuState == 2 && input.isKeyPressed(Input.KEY_ESCAPE))
		{
			MenuState = 0;
		}
		
		return false;
	}
	
	public void render(Graphics g, GameContainer gc)
	{
		if (MenuState == 0)
		{
			g.drawAnimation(backgroundAnimation, 0, 0);
			g.drawImage(title, gc.getScreenWidth()/2 - title.getWidth()/2, 0);
			
			g.drawImage(start, gc.getScreenWidth()/2 - start.getWidth()/2, gc.getScreenHeight()/2 - 100);
			g.drawImage(controls, gc.getScreenWidth()/2 - controls.getWidth()/2, gc.getScreenHeight()/2 - 25);
			g.drawImage(tutorial, gc.getScreenWidth()/2 - tutorial.getWidth()/2, gc.getScreenHeight()/2 + 50);
			g.drawImage(selector, gc.getScreenWidth()/2 - (start.getWidth()/2 + 120), gc.getScreenHeight()/2 - delta);
		}
		else if (MenuState == 1)
		{
			int keyboardx = gc.getScreenWidth()/2 - keyboard.getWidth()/2;
			int keyboardy = gc.getScreenHeight() - keyboard.getHeight();
			
			g.drawImage(keyboard, keyboardx, keyboardy);
			g.drawImage(circ1, keyboardx + circ1Pos.x, keyboardy + circ1Pos.y);
			g.drawImage(circ2, keyboardx + circ2Pos.x, keyboardy + circ2Pos.y);
			g.drawImage(circ3, keyboardx + circ3Pos.x, keyboardy + circ3Pos.y);
			g.drawImage(circ4, keyboardx + circ4Pos.x, keyboardy + circ4Pos.y);
			g.drawImage(circ5, keyboardx + circ5Pos.x, keyboardy + circ5Pos.y);
			g.setColor(selected);
			g.fill(new Rectangle(0, 0, 150, 25));
			g.fill(new Rectangle(0, 25, 150, 25));
			g.setColor(Color.white);
			g.drawString("Outer circle = 5", 0, 0);
			g.drawString("2nd inner circle = 4", 0, 30);
			//RadarCircles radar = new RadarCircles(gc.getScreenWidth(), gc.getScreenHeight());
			//radar.draw(g);
		}
		else if (MenuState == 2)
		{
			
		}
	}
}
