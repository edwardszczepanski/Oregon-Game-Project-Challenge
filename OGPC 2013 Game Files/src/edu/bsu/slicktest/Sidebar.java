package edu.bsu.slicktest;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

public class Sidebar 
{
	//Class instance of player
	Player p1;
	
	//Images for the health, mana, and XP bar
	Image HP_Full = null;
	Image HP_Gone = null;
	
	Sidebar(Player player)
	{
		//Declare HP_Full Image
		try {
			HP_Full = new Image("data/HP Full.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		//Set class instance to the instance from MainRogue
		p1 = player;
	}
	
	//Displays the sidebar with mana, XP, levels, HP, etc.
	//Basic status of the player displayed here, held in player
	void displaySidebar(Graphics g)
	{
		//HP Bar
		g.drawString("HP", 1000, 30);
		
		Rectangle HPGone = new Rectangle(1000, 50, 200, 32);
        ShapeFill HPGoneColor = new GradientFill(0, 32 / 2, Color.red, 200, 32 - 1, Color.red, true);

        float HPLeft = ((float) p1.HP / (float) p1.MAX_HP) * (float) 200;
        Rectangle HP = new Rectangle(1000, 50, HPLeft, 32);
        ShapeFill HPColor = new GradientFill(0, 32 / 2, Color.green, HPLeft, 32 - 1, Color.green, true);

        g.fill(HPGone, HPGoneColor);
        g.fill(HP, HPColor);
		
		g.drawString("" + p1.HP + "/" + p1.MAX_HP, 1000, 50);
		/*HP_Full.draw(950, 136);
		int numDraw = p1.MAX_HP - p1.HP;
		
		if (numDraw != 0)
		{
			try {
				HP_Gone = new Image("data/HP Gone" + numDraw + ".png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
			HP_Gone.draw(1150 - (2 * numDraw), 136);
		}*/
		
		//Mana Bar
		g.drawString("Mana", 1000, 130);
		
		Rectangle ManaGone = new Rectangle(1000, 150, 200, 32);
		ShapeFill ManaGoneColor = new GradientFill(0, 32 / 2, Color.black, 200, 32 - 1, Color.black, true);
		ShapeFill ManaOutlineColor = new GradientFill(0, 32 / 2, Color.blue, 200, 32 - 1, Color.blue, true);

        float ManaLeft = ((float) p1.mana / (float) p1.MAX_mana) * (float) 200;
        Rectangle Mana = new Rectangle(1000, 150, ManaLeft, 32);
        ShapeFill ManaColor = new GradientFill(0, 32 / 2, Color.blue, ManaLeft, 32 - 1, Color.blue, true);

        g.fill(ManaGone, ManaGoneColor);
        g.fill(Mana, ManaColor);
        g.draw(ManaGone, ManaOutlineColor);
        
		g.drawString("" + p1.mana + "/" + p1.MAX_mana, 1000, 150);
		
		//XP Bar
		g.drawString("EXP", 1000, 230);
		
		Rectangle XPNotHave = new Rectangle(1000, 250, 200, 32);
		ShapeFill XPNotHaveColor = new GradientFill(0, 32 / 2, Color.black, 200, 32 - 1, Color.black, true);
		ShapeFill XPOutlineColor = new GradientFill(0, 32 / 2, Color.red, 200, 32 - 1, Color.red, true);

        float XPLeft = ((float) p1.exp / (float) p1.reqexp) * (float) 200;
        Rectangle XP = new Rectangle(1000, 250, XPLeft, 32);
        ShapeFill XPColor = new GradientFill(0, 32 / 2, Color.red, XPLeft, 32 - 1, Color.red, true);

        g.fill(XPNotHave, XPNotHaveColor);
        g.fill(XP, XPColor);
        g.draw(XPNotHave, XPOutlineColor);
        
		g.drawString("" + p1.exp + "/" + p1.reqexp, 1000, 250);
		
		g.drawString("Level = " + p1.level, 1000, 290);
		//g.drawString("News Reporter:  This just in!  There are wide spread tornadoes ravaging the towns of rural Texas.  Tens of thousands are at danger of being seriously harmed.  The visibility is low and time is scarce.  As it stands nobody has been dispatched to help these people…  I am currently being told that the DEHK Red Cross is on the scene.  May god have mercy on their souls.", 500, 500);
	}
}
