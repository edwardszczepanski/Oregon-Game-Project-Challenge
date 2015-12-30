import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;

public class MenuState extends BasicGameState
{
	int windowWidth;
	int windowHeight;
	
	Font font = new Font("Courier", Font.BOLD, 30);
	TrueTypeFont wordFont = new TrueTypeFont(font, false);
	
	StateHandler stateHandler;
	
	Circle[] menuCircles;
	String[] menuStrings;
	Point[] menuStringsCoords;
	Color[] colorArray;
	RoundedRectangle[] selectorRectangles;
	
	Shape selector;
	Circle timerCircle;
	
	Music backgroundMusic;
	Sound blip;
	Sound select;
	Sound transition;
	
	int selected;
	int timer;
	boolean timerGo = false;
	
	MenuState(StateHandler sh)
	{
		stateHandler = sh;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame state) throws SlickException
	{
		windowWidth = gc.getWidth();
		windowHeight = gc.getHeight();
		
		backgroundMusic = new Music("data/Sound Effects/background.wav");
		
		blip = new Sound("data/Sound Effects/Blip.wav");
		select = new Sound("data/Sound Testing/Select or Level.wav");
		transition = new Sound("data/Sound Testing/Transition Laser.wav");
		
		float goldenRatio = (float) ((1 + Math.sqrt(5))/2);
		
		menuCircles = new Circle[3];
		menuCircles[0] = new Circle(windowWidth/5, windowHeight/2, 100);//windowHeight/(3 * goldenRatio));
		menuCircles[1] = new Circle(windowWidth/2, windowHeight/2, 100);//windowHeight/(3 * goldenRatio));
		menuCircles[2] = new Circle(windowWidth - windowWidth/5, windowHeight/2, 100);//windowHeight/(3 * goldenRatio));
		
		colorArray = new Color[5];
		colorArray[0] = new Color(173, 16, 16); //red
		colorArray[1] = new Color(196, 196, 16); //yellow
		colorArray[2] = new Color(22, 161, 22); //green
		colorArray[3] = new Color(63, 186, 186); //cyan
		colorArray[4] = new Color(10, 29, 145); //blue
		
		menuStrings = new String[5];
		menuStrings[0] = "Arcade";
		menuStrings[1] = "OPTIONS";
		menuStrings[2] = "Story";
		menuStrings[3] = "EXIT";
		menuStrings[4] = "Tutorial";
		
		menuStringsCoords = new Point[]{new Point(windowWidth/2 - (windowWidth/2 - windowWidth/5)/2, windowHeight/2), new Point(windowWidth/2 + (windowWidth/2 - windowWidth/5)/2, windowHeight/2)};
		
		selector = new Circle(menuCircles[1].getCenterX(), menuCircles[1].getCenterY(), menuCircles[1].getRadius() + 10);
		
		selectorRectangles = new RoundedRectangle[2];
		//selectorRectangles[0] = new Rectangle(menuStringsCoords[0].getX() - 20, menuStringsCoords[0].getY() - 51, 40, 120);
		//selectorRectangles[1] = new Rectangle(menuStringsCoords[1].getX() - 20, menuStringsCoords[1].getY() - 37, 40, 74);
		
		timerCircle = new Circle(windowWidth/2, windowHeight/2 + windowHeight/3, 50);
		
		selected = 2;
		timer = 0;
	}

	public void drawStringDown(float x, float y, String string, Graphics g)
	{
		int height = g.getFont().getHeight(string);
		String[] stringChars = new String[string.length()];
		for (int i = 1; i < string.length() + 1; i++)
			stringChars[i - 1] = string.substring(i - 1, i);
		
		for (int i = 0; i < stringChars.length; i++)
		{
			g.drawString(stringChars[i], x - g.getFont().getWidth(stringChars[i])/2, (y - (height * (stringChars.length/2))) + (i * height));
		}
		if (string.equals("OPTIONS"))
			selectorRectangles[0] = new RoundedRectangle(x - g.getFont().getWidth(stringChars[0])/2 - 10, y - (height * (stringChars.length/2)) - 5, g.getFont().getWidth(stringChars[0]) + 20, stringChars.length * height + 10, 6);
		else
			selectorRectangles[1] = new RoundedRectangle(x - g.getFont().getWidth(stringChars[0])/2 - 10, y - (height * (stringChars.length/2)) - 5, g.getFont().getWidth(stringChars[0]) + 20, stringChars.length * height + 10, 6);
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame state)
	{
		timerGo = false;
		timer = 0;
		if (!backgroundMusic.playing())
		{
			backgroundMusic.fade(750, 1, false);
			backgroundMusic.loop();
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException
	{
		g.setAntiAlias(true);
		g.setFont(wordFont);
		
		g.setColor(new Color(84, 84, 84));
		g.fill(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()));
		
		for (int i = 0; i < menuStrings.length; i += 2)
		{
			g.setColor(colorArray[i]);
			g.fill(menuCircles[i/2]);
			g.setColor(Color.black);
			g.drawString(menuStrings[i], menuCircles[i/2].getCenterX() - g.getFont().getWidth(menuStrings[i])/2, menuCircles[i/2].getCenterY() - g.getFont().getHeight(menuStrings[i])/2);
		}
		
		for (int i = 0; i < menuStringsCoords.length; i++)
		{
			g.setColor(colorArray[(2*i) + 1]);
			drawStringDown(menuStringsCoords[i].getX(), menuStringsCoords[i].getY(), menuStrings[(2*i) + 1], g);
		}
		
		if (timerGo)
		{
			g.setLineWidth(3);
			g.setColor(colorArray[selected]);
			g.draw(timerCircle);
			
			g.setAntiAlias(false);
			g.fillArc(timerCircle.getX(), timerCircle.getY(), timerCircle.getRadius() * 2, timerCircle.getRadius() * 2, 270, 270 + (timer * (360f/100f)));
			g.setAntiAlias(true);
		}
		
		g.setLineWidth(10);
		g.setColor(new Color(255, 255, 255));
		g.draw(selector);
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		if (key == Input.KEY_H)
		{
			timerGo = true;
		}
		else if (key == Input.KEY_J)
		{
			timerGo = true;
		}
		else if (key == Input.KEY_K)
		{
			timerGo = true;
		}
	}
	
	@Override
	public void keyReleased(int key, char c)
	{
		if (key == Input.KEY_H)
		{
			timerGo = false;
			timer = 0;
		}
		else if (key == Input.KEY_J)
		{
			timerGo = false;
			timer = 0;
		}
		else if (key == Input.KEY_K)
		{
			timerGo = false;
			timer = 0;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException
	{
		Input input = gc.getInput();
		
		if (timer == 100)
		{
			backgroundMusic.fade(750, 0, true);
			transition.play();
			select.play();
			if (selected == 3)
				gc.exit();
			else
				state.enterState(selected + 1, new FadeOutTransition(Color.black, 750), new FadeInTransition(Color.black, 750));
		}
		
		int thisIteration = selected;
		
		if (input.isKeyDown(Input.KEY_H) && input.isKeyDown(Input.KEY_J))
		{
			selector = selectorRectangles[0];
			selected = 1;
		}
		else if (input.isKeyDown(Input.KEY_J) && input.isKeyDown(Input.KEY_K))
		{
			selector = selectorRectangles[1];
			selected = 3;
		}
		else if (input.isKeyPressed(Input.KEY_H) && selected != 0)
		{
			selector = new Circle(menuCircles[0].getCenterX(), menuCircles[0].getCenterY(), menuCircles[0].getRadius() + 10);
			selected = 0;
		}
		else if (input.isKeyPressed(Input.KEY_J) && selected != 2)
		{
			selector = new Circle(menuCircles[1].getCenterX(), menuCircles[1].getCenterY(), menuCircles[1].getRadius() + 10);
			selected = 2;
		}
		else if (input.isKeyPressed(Input.KEY_K) && selected != 4)
		{
			selector = new Circle(menuCircles[2].getCenterX(), menuCircles[2].getCenterY(), menuCircles[2].getRadius() + 10);
			selected = 4;
		}
		
		if (selected != thisIteration)
		{
			blip.play();
		}
		
		if (timerGo)
			timer += 2;
			
	}
	
	public int getID()
	{
		return 0;
	}
}