import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ArcadeState extends BasicGameState
{
	Engine engine;
	Rhythms engineRhythm;
	StateHandler stateHandler;
	
	Rhythms[] rhythmsList;
	Circle[] rhythmsListCircles;
	Circle[] displayCircles;
	ArrayList<Integer> indexOrder;
	ArrayList<Circle> pointCircles; // Kyle made me
	
	Polygon leftArrow;
	Polygon rightArrow;
	
	boolean fillRightArrow = false;
	boolean fillLeftArrow = false;
	boolean fillCircle = false;
	
	Music backgroundMusic;
	
	public ArcadeState(Rhythms rhythm, StateHandler sh)
	{
		engineRhythm = rhythm;
		stateHandler = sh;
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame state)
	{
		stateHandler.leavingState(state.getCurrentStateID());
		backgroundMusic.stop();
	}
	
	public void init(GameContainer gc, StateBasedGame state) throws SlickException
	{
		backgroundMusic = new Music("data/Sound Testing/Dubstep Filler or Starter.wav");
		
		float circleXLocation = gc.getScreenWidth()/5;
		float circleYLocation = gc.getScreenHeight()/2;
		float goldenRatio = (float) ((1 + Math.sqrt(5))/2);
		
		leftArrow = new Polygon();
		leftArrow.addPoint(gc.getScreenWidth()/2 - (gc.getScreenHeight()/(3 * goldenRatio)) - 100, gc.getScreenHeight()/2);
		leftArrow.addPoint(gc.getScreenWidth()/2 - (gc.getScreenHeight()/(3 * goldenRatio)) - 100 + 50, gc.getScreenHeight()/2 - 50);
		leftArrow.addPoint(gc.getScreenWidth()/2 - (gc.getScreenHeight()/(3 * goldenRatio)) - 100 + 50, gc.getScreenHeight()/2 + 50);
		rightArrow = new Polygon();
		rightArrow.addPoint(gc.getScreenWidth()/2 + (gc.getScreenHeight()/(3 * goldenRatio)) + 100 - 50, gc.getScreenHeight()/2 - 50);
		rightArrow.addPoint(gc.getScreenWidth()/2 + (gc.getScreenHeight()/(3 * goldenRatio)) + 100, gc.getScreenHeight()/2);
		rightArrow.addPoint(gc.getScreenWidth()/2 + (gc.getScreenHeight()/(3 * goldenRatio)) + 100 - 50, gc.getScreenHeight()/2 + 50);
		
		pointCircles = new ArrayList<Circle>();
		int radius = 3;
		pointCircles.add(new Circle(gc.getScreenWidth()/2 - (gc.getScreenHeight()/(3 * goldenRatio)) - 100, gc.getScreenHeight()/2, radius));
		pointCircles.add(new Circle(gc.getScreenWidth()/2 - (gc.getScreenHeight()/(3 * goldenRatio)) - 100 + 50, gc.getScreenHeight()/2 - 50, radius));
		pointCircles.add(new Circle(gc.getScreenWidth()/2 - (gc.getScreenHeight()/(3 * goldenRatio)) - 100 + 50, gc.getScreenHeight()/2 + 50, radius));
		pointCircles.add(new Circle(gc.getScreenWidth()/2 + (gc.getScreenHeight()/(3 * goldenRatio)) + 100 - 50, gc.getScreenHeight()/2 - 50, radius));
		pointCircles.add(new Circle(gc.getScreenWidth()/2 + (gc.getScreenHeight()/(3 * goldenRatio)) + 100, gc.getScreenHeight()/2, radius));
		pointCircles.add(new Circle(gc.getScreenWidth()/2 + (gc.getScreenHeight()/(3 * goldenRatio)) + 100 - 50, gc.getScreenHeight()/2 + 50, radius));
		
		rhythmsList = new Rhythms[5];
		for (int i = 0; i < rhythmsList.length; i++)
			rhythmsList[i] = new Rhythms();
		rhythmsList[0].setRhythm("Do I Wanna Know - Arctic Monkeys");
		rhythmsList[1].setRhythm("Hideaway - Kiesza");
		rhythmsList[2].setRhythm("Human - Christina Perri");
		rhythmsList[3].setRhythm("Pump Up The Jam Mixdown - Mowe");
		rhythmsList[4].setRhythm("The Days - Avicii");
		
		displayCircles = new Circle[5];
		displayCircles[0] = new Circle(0, gc.getScreenHeight()/2, 0);
		displayCircles[1] = new Circle(circleXLocation, circleYLocation, gc.getScreenHeight()/(4 * goldenRatio));
		displayCircles[2] = new Circle(gc.getScreenWidth()/2, circleYLocation, gc.getScreenHeight()/(3 * goldenRatio));
		displayCircles[3] = new Circle(gc.getScreenWidth() - circleXLocation, circleYLocation, gc.getScreenHeight()/(4 * goldenRatio));
		displayCircles[4] = new Circle(gc.getScreenWidth(), gc.getScreenHeight()/2, 0);
		
		rhythmsListCircles = new Circle[5];
		rhythmsListCircles[0] = displayCircles[0];
		rhythmsListCircles[1] = new Circle(displayCircles[1].getCenterX(), displayCircles[1].getCenterY(), displayCircles[1].getRadius() - 10);
		rhythmsListCircles[2] = new Circle(displayCircles[2].getCenterX(), displayCircles[2].getCenterY(), displayCircles[2].getRadius() - 10);
		rhythmsListCircles[3] = new Circle(displayCircles[3].getCenterX(), displayCircles[3].getCenterY(), displayCircles[3].getRadius() - 10);
		rhythmsListCircles[4] = displayCircles[4];
		
		indexOrder = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++)
			indexOrder.add(i);
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		stateHandler.exitKeyPress(key);
	}
	
	@Override
	public void keyReleased(int key, char c)
	{
		stateHandler.checkExit(key);
			
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame state)
	{
		backgroundMusic.loop();
	}
	
	public void changeDisplayed(int increment)
	{	
		for (int i = 0; i < indexOrder.size(); i++)
		{
			indexOrder.set(i, indexOrder.get(i) - increment);
			if (indexOrder.get(i) > 4)
				indexOrder.set(i, indexOrder.get(i) - 5);
			else if (indexOrder.get(i) < 0)
				indexOrder.set(i, indexOrder.get(i) + 5);
		}
	}
	
	public void moveCircles()
	{
		for (int i = 0; i < 5; i++)
		{
			int realI = indexOrder.get(i);
			if (realI == 0 || realI == 4)
				rhythmsListCircles[realI] = displayCircles[realI];
			else
				rhythmsListCircles[realI] = new Circle(displayCircles[realI].getCenterX(), displayCircles[realI].getCenterY(), displayCircles[realI].getRadius() - 10);
		}
	}
	
	public void playSample()
	{
		rhythmsList[indexOrder.get(2)].currentSong.setPosition(5);
		rhythmsList[indexOrder.get(2)].currentSong.play();
	}
	
	public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException
	{
		Input input = gc.getInput();
		
		//if (input.isKeyDown(Input.KEY_H) && input.isKeyDown(Input.KEY_J) && input.isKeyDown(Input.KEY_K))
		//{
			//state.enterState(0);
		//}
		//else
		//{
			if (input.isKeyDown(Input.KEY_K))
			{
				fillRightArrow = true;
			}
			else
			{
				fillRightArrow = false;
			}
			
			if (input.isKeyDown(Input.KEY_H))
			{
				fillLeftArrow = true;
			}
			else
			{
				fillLeftArrow = false;
			}
			
			if (input.isKeyDown(Input.KEY_J))
			{
				fillCircle = true;
			}
			else
			{
				fillCircle = false;
			}
			
			if (input.isKeyPressed(Input.KEY_K))
			{
				changeDisplayed(1);
				moveCircles();
				//playSample();
			}
			else if (input.isKeyPressed(Input.KEY_H))
			{
				changeDisplayed(-1);
				moveCircles();
				//playSample();
			}
			else if (input.isKeyPressed(Input.KEY_J))
			{
				int selected = indexOrder.get(2);
				engineRhythm.setRhythm(rhythmsList[selected].title + " - " + rhythmsList[selected].artist);
				state.enterState(4);
			}
		//}
		
		if (input.isKeyPressed(Input.KEY_ESCAPE))
		{
			state.enterState(0);
		}
	}
	
	public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException
	{
		g.setAntiAlias(true);
		g.setLineWidth(5);
		
		g.setColor(new Color(84, 84, 84));
		g.fill(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()));
		
		//Draws left arrow (H)
		g.setColor(new Color(173, 16, 16)); //red
		if (fillLeftArrow)
			g.fill(leftArrow);
		else
		{
			//Draws small circles at the tips of the arrows points to make it look nicer
			for (int i = 0; i < pointCircles.size()/2; i++)
				g.fill(pointCircles.get(i));
			
			g.draw(leftArrow);
		}
		
		//Draws outline circle (J)
		g.setColor(new Color(22, 161, 22)); //green
		if (fillCircle)
			g.fill(displayCircles[2]);
		else
			g.draw(displayCircles[2]);
		
		//Draws right arrow (K)
		g.setColor(new Color(10, 29, 145)); //blue
		if (fillRightArrow)
			g.fill(rightArrow);
		else
		{
			//Draws small circles at the tips of the arrows points to make it look nicer
			for (int i = pointCircles.size()/2; i < pointCircles.size(); i++)
				g.fill(pointCircles.get(i));
		
			g.draw(rightArrow);
		}
		
		//Draws the big circles
		g.setLineWidth(10);
		g.setColor(new Color(255, 255, 255));
		for (int i = 0; i < 5; i++)
			g.fill(rhythmsListCircles[i]);
		
		//Draws the text for the songs
		g.setColor(new Color(0, 0, 0));
		for (int i = 0; i < indexOrder.size(); i++)
		{
			int realI = indexOrder.get(i);
			int titleLength = g.getFont().getWidth(rhythmsList[realI].title);
			g.drawString(rhythmsList[realI].title, rhythmsListCircles[i].getCenterX() - titleLength/2, rhythmsListCircles[i].getCenterY());
		}
	}

	public int getID()
	{
		return 1;
	}
}
