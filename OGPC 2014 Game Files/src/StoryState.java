import java.awt.Font;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StoryState extends BasicGameState
{
	int windowWidth;
	int windowHeight;
	
	StoryParser[] story;
	Rhythms[] rhythmList;
	StateHandler stateHandler;
	Rhythms engineRhythm;
	TrueTypeFont wordFont;
	int storyPosition;
	int timer;
	boolean timerGo = false;
	
	StoryState(Rhythms rhythm, StateHandler sh)
	{
		engineRhythm = rhythm;
		stateHandler = sh;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame state) throws SlickException
	{
		Font font = new Font("Verdana", Font.BOLD, 32);
		wordFont = new TrueTypeFont(font, true);
		
		windowWidth = gc.getWidth();
		windowHeight = gc.getHeight();
		
		story = new StoryParser[]{new StoryParser("data/Story/story.txt", wordFont, windowWidth - (20 * 4))};
		
		rhythmList = new Rhythms[1];
		for (int i = 0; i < rhythmList.length; i++)
			rhythmList[i] = new Rhythms();
		rhythmList[0].setRhythm("Do I Wanna Know - Arctic Monkeys");
		
		storyPosition = 0;
		timerGo = false;
		timer = 0;
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

	public void drawTextBox(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, windowWidth, windowHeight);
		
		g.setColor(Color.white);
		g.fillRect(0, (windowHeight * 2/3) - (2 * 20), windowWidth, windowHeight /2);
		
		g.setColor(new Color(84, 84, 84));
		g.fillRoundRect(20, (windowHeight * 2/3) - 20, windowWidth - (20 * 2), windowHeight * 1/3, 20);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException
	{
		g.setAntiAlias(true);
		
		drawTextBox(g);
		
		story[0].drawText(wordFont, storyPosition, gc, g);
		
		/*g.setAntiAlias(false);
		g.setColor(Color.red);
		g.fillArc(0 - (125/2), windowHeight - (125/2), 125, 125, 270, 0);
		
		g.setColor(Color.green);
		g.fillArc(windowWidth/2 - 75, windowHeight - 75, 150, 150, 180, 0);
		
		g.setColor(Color.blue);
		g.fillArc(windowWidth - (125/2), windowHeight - (125/2), 125, 125, 180, 270);*/
		
		//g.setColor(Color.green);
		//g.setAntiAlias(false);
		//g.fillArc((windowWidth - (20 * 2))/2, (windowHeight * 2/3) + (wordFont.getHeight() * 5) + (5 * 3) - 20, 20, 20, 270, 270 + (timer * (360f/100f)));
		//g.fill(new Circle((windowWidth - (20 * 2))/2 + 20, (windowHeight * 2/3) + (wordFont.getHeight() * 5) + (5 * 5), 10));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException
	{
		Input input = gc.getInput();
		
		if (timer == 100)
		{
			story[0].setDisplay();
			if (storyPosition == story[0].story.length - 1)
			{
				storyPosition = 0;
				//engineRhythm.setRhythm(rhythmList[0].title + " - " + rhythmList[0].artist);
				//state.enterState(4);
			}
			else
				storyPosition++;
		}
		
		if (timerGo)
			timer += 2;
	}

	@Override
	public int getID()
	{
		return 3;
	}
	
}
