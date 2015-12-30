import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class LogoState extends BasicGameState
{
	int radius;
	int x;
	int y;
	float counter;
	int change;
	Color[] gradientColors;
	
	Sound henry;
	
	public void init(GameContainer gc, StateBasedGame state) throws SlickException
	{
		henry = new Sound("data/Sound Effects/henry.wav");
		
		radius = 250;
		x = gc.getWidth()/2 - radius;
		y = gc.getHeight()/2 - radius;
		change = radius/2;
		
		gradientColors = new Color[5];
		
		gradientColors[0] = new Color(173f/255f, 16f/255f, 16f/255f); //red
		gradientColors[1] = new Color(196f/255f, 196f/255f, 16f/255f); //yellow
		gradientColors[2] = new Color(22f/255f, 161f/255f, 22f/255f); //green
		gradientColors[3] = new Color(63f/255f, 186f/255f, 186f/255f); //cyan
		gradientColors[4] = new Color(10f/255f, 29f/255f, 145f/255f); //blue
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException
	{
		Input input = gc.getInput();
		
		if (counter == 25 || counter == 26)
			henry.play();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			state.enterState(7, new FadeOutTransition(Color.black, 0), new FadeInTransition(Color.black, 750));
		if (counter == 100)
			state.enterState(7, new FadeOutTransition(Color.black, 750), new FadeInTransition(Color.black, 750));
		
		counter++;
	}
	
	public void drawShadedArc(Graphics g, Color color, float gradient, float diffX, float x, float y, float r, float startAngle, float endAngle)
	{
		int gradientCounter = 0;
		
		for (int i = 0; i < gradient; i++)
		{
			if (i % 5 == 0 && i != 0)
				gradientCounter++;
			g.setColor(new Color(gradientColors[gradientCounter].r, gradientColors[gradientCounter].g, gradientColors[gradientCounter].b, (1.0f/gradient) * i));
			g.drawArc(x + (diffX/gradient * i), y, r*2, r*2, startAngle, endAngle);
		}
	}
	
	public void drawShadedLine(Graphics g, Color color, float gradient, float diffX, float diffY, float x1, float y1, float x2, float y2)
	{
		int gradientCounter = 0;
		for (int i = 0; i < gradient; i++)
		{
			if (i % 5 == 0 && i != 0)
				gradientCounter++;
			g.setColor(new Color(new Color(gradientColors[gradientCounter].r, gradientColors[gradientCounter].g, gradientColors[gradientCounter].b, (1.0f/gradient/5) * i)));
			g.drawLine(x1 + (diffX/gradient * i), y1 + (diffY/gradient * i), x2 + (diffX/gradient * i), y2 + (diffY/gradient * i));
		}
	}
	
	public void swapColors()
	{
		Color tempColor = gradientColors[4];
		
		for (int i = 4; i > 0; i--)
		{
			gradientColors[i] = gradientColors[i-1];
		}
		
		gradientColors[0] = tempColor;
	}
	
	public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException
	{
		g.setAntiAlias(true);
		g.setLineWidth(5);
		
		g.setColor(new Color(84, 84, 84));
		g.fill(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()));
		
		Color color = new Color(102, 178, 255);
		
		//Top left arc
		drawShadedArc(g, color, 25, (-1) * (change - 50), x - (radius * 2) + radius/2, y, radius, 270, 360);
				
		//Bottom left arc
		drawShadedArc(g, color, 25, (-1) * (change - 50), x + radius/2, y, radius, 90, 180);
		
		drawShadedLine(g, color, 25, (-1) * (change - 50), 20, x + change, y + radius - 7.5f, x + radius + change + change/2, y + radius - 7.5f);
		
		//Top right arc
		drawShadedArc(g, color, 25, (-1) * (change - 50), x - radius/2 + (change - 50), y, radius, 270, 360);
		
		//Bottom right arc
		drawShadedArc(g, color, 25, (-1) * (change - 50), x + (radius * 2) - radius/2 + (change - 50), y, radius, 90, 180);
	}

	public int getID()
	{
		return 6;
	}
	
}
