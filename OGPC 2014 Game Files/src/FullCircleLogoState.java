import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class FullCircleLogoState extends BasicGameState 
{
	LogoShape[] shapes;
	
	int timer;
	
	@Override
	public void init(GameContainer gc, StateBasedGame state) throws SlickException
	{
		Color[] colors = new Color[5];
		colors[0] = new Color(63f/255f, 186f/255f, 186f/255f, .05f); //cyan
		colors[1] = new Color(10f/255f, 29f/255f, 145f/255f, .05f); //blue
		colors[2] = new Color(173f/255f, 16f/255f, 16f/255f, .05f); //red
		colors[3] = new Color(196f/255f, 196f/255f, 16f/255f, .05f); //yellow
		colors[4] = new Color(22f/255f, 161f/255f, 22f/255f, .05f); //green
		
		float x = gc.getWidth()/2;
		float y = gc.getHeight()/2;
		float r = gc.getHeight()/4;
		
		Point[] regularPentagonVerticies = new Point[5];
		Point[] tiltedPentagonVerticies = new Point[5];
		for (int i = 0; i < tiltedPentagonVerticies.length; i++)
		{
			tiltedPentagonVerticies[i] = new Point((float) (x + (r-30) * Math.cos(i * 2 * Math.PI / 5)), (float) (y + (r-30) * Math.sin(i * 2 * Math.PI / 5)));
		}
		for (int i = 0; i < regularPentagonVerticies.length; i++)
		{
			regularPentagonVerticies[i] = new Point((float) (x + r * Math.cos((i * 2 * Math.PI / 5) + (270 * Math.PI/180))), (float) (y + r * Math.sin((i * 2 * Math.PI / 5) + (270 * Math.PI/180))));
		}
		
		shapes = new LogoShape[5];
		for (int i = 0; i < shapes.length; i++)
		{
			shapes[i] = new LogoShape(regularPentagonVerticies[i], tiltedPentagonVerticies[i], colors[i], new Circle(gc.getWidth()/2, gc.getHeight()/2, gc.getHeight()/4), i);
		}
		
		timer = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException
	{
		g.setAntiAlias(true);
		
		g.setColor(new Color(84, 84, 84));
		g.fill(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()));
		
		if (shapes[0].rotated && shapes[0].opacity < 0)
		{
			g.setColor(new Color(50, 50, 50));
			g.fill(new Circle(shapes[0].centerX, shapes[0].centerY, shapes[0].radius));
		}
		
		for (int i = 0; i < shapes.length; i++)
			shapes[i].draw(g);
		
		g.setColor(Color.white);
		g.drawString("Full Circle", gc.getWidth()/2 - g.getFont().getWidth("Full Circle")/2, gc.getHeight()/2 - g.getFont().getHeight("Full Circle")/2);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException
	{
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			state.enterState(0, new FadeOutTransition(Color.black, 0), new FadeInTransition(Color.black, 750));
		
		if (timer == 150)
		{
			for (int i = 0; i < shapes.length; i++)
				shapes[i].rotate(i);
		}
		
		if (timer == 550)
			state.enterState(0, new FadeOutTransition(Color.black, 750), new FadeInTransition(Color.black, 750));
		
		for (int i = 0; i < shapes.length; i++)
			shapes[i].rotation();
		
		timer++;
	}
	
	public int getID()
	{
		return 7;
	}
}
