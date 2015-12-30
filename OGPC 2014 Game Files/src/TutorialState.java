import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.svg.Gradient;
import org.newdawn.slick.svg.RadialGradientFill;

public class TutorialState extends BasicGameState
{
	Controls controls;
	Transition moveLeft;
	Transition moveRight;
	Transition move2Left;
	Transition move2Right;
	Transition pulse;
	private int selected;
	private int numSongs;
	private int fullRadius;
	private int songRadius;
	private Point fullCenter;
	private String[] songNames;
	private float[] songAngles;
	private Point[] songPoints;
	private Font font;
	private TrueTypeFont wordFont;
	private float numGlowCircles;
	private Point pulseCirclePoint;
	private float[] colorOpacity;
	private int updateCounter = 0;
	/*int increment;
	int[][] toReturn;
	double scaler;
	
	private int getSixteenBitSample(int high, int low)
	{
		return (high << 8) + (low & 0x00ff);
	}*/
	
	TutorialState(Controls c)
	{
		controls = c;
	}
	
	public void init(GameContainer gc, StateBasedGame state) throws SlickException
	{
		numSongs = 10;
		
		fullRadius = gc.getScreenWidth();
		songRadius = 50;
		fullCenter = new Point(gc.getScreenWidth()/2, gc.getScreenHeight()*2.3f);
		
		songNames = new String[numSongs];
		songNames[0] = "Stolen Dance";
		songNames[1] = "Humming Bird";
		songNames[2] = "Stay with Me";
		songNames[3] = "Hope";
		songNames[4] = "Am I Wrong";
		songNames[5] = "Ravers in the UK";
		songNames[6] = "I'm Ready";
		songNames[7] = "Break Free";
		songNames[8] = "Back 2 Life";
		songNames[9] = "The Walker";
		
		songAngles = new float[numSongs];
		for (int i = 0; i < songAngles.length; i++)
			songAngles[i] = (360/numSongs) * i;
		
		songPoints = new Point[numSongs];
		for (int i = 0; i < songPoints.length; i++)
			songPoints[i] = new Point(fullCenter.getX() + ((float) (fullRadius * Math.cos((Math.PI/180) * (songAngles[i] + 90)))), fullCenter.getY() - ((float) (fullRadius * Math.sin((Math.PI/180) * (songAngles[i] + 90)))));
		
		pulseCirclePoint = new Point(fullCenter.getX() + ((float) (fullRadius * Math.cos((Math.PI/180) * 90))), fullCenter.getY() - ((float) (fullRadius * Math.sin((Math.PI/180) * 90))));
		
		moveLeft = new Transition(-1, 360/numSongs);
		moveRight = new Transition(1, 360/numSongs);
		move2Left = new Transition(-2, 2 * 360/numSongs);
		move2Right = new Transition(2, 2 * 360/numSongs);
		
		font = new Font("Verdana", Font.BOLD, 32);
		wordFont = new TrueTypeFont(font, true);
		
		selected = 0;
		
		colorOpacity = new float[2];
		for (int i = 0; i < colorOpacity.length; i++)
			colorOpacity[i] = 1;
		
		numGlowCircles = 10;
		pulse = new Transition(.1f, 5, numGlowCircles, false);
		
		/*File file = new File("data/Music/Rather Be.wav");
		AudioInputStream audioInputStream = null;
		try
		{
			audioInputStream = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException | IOException e) {e.printStackTrace();}
		  
		int frameLength = (int) audioInputStream.getFrameLength();
		int frameSize = (int) audioInputStream.getFormat().getFrameSize();
		  
		byte[] eightBitByteArray = new byte[frameLength * frameSize];
		  
		int result = 0;
		try
		{
			result = audioInputStream.read(eightBitByteArray);
		} catch (IOException e) {e.printStackTrace();}
		
		int numChannels = audioInputStream.getFormat().getChannels();
		toReturn = new int[numChannels][frameLength];
		
		int sampleIndex = 0;
	
		for (int t = 0; t < eightBitByteArray.length;) 
		{
			for (int channel = 0; channel < numChannels; channel++) 
			{
				int low = (int) eightBitByteArray[t];
				t++;
				int high = (int) eightBitByteArray[t];
				t++;
				int sample = getSixteenBitSample(high, low);
				toReturn[channel][sampleIndex] = sample;
			}
			sampleIndex++;
		}
		
		increment = toReturn[0].length / gc.getScreenWidth();
		
		scaler = Math.abs(toReturn[0][0]);
		
		for (int i = 1; i < toReturn[0].length; i++)
		{
			if (Math.abs(toReturn[0][i]) > scaler)
			{
				scaler = Math.abs(toReturn[0][i]);
			}
		}
		
		scaler = (gc.getScreenHeight()/4) / scaler;*/
	}
	
	/*@Override
    public void keyPressed(int key, char c)
    {
		if (key == Input.KEY_F)
		{
			greenSpeed = .5f;
		}
		if (key == Input.KEY_J)
			blueSpeed = .5f;
    }*/
	@Override
    public void keyReleased(int key, char c)
    {
		if (key == Input.KEY_F)
		{
			if (updateCounter <= 10)
			{
				moveLeft.start();
				selected--;
			}
			updateCounter = 0;
			colorOpacity[0] = 1;
		}
		if (key == Input.KEY_J)
		{
			if (updateCounter <= 10)
			{
				moveRight.start();
				selected++;
			}
			updateCounter = 0;
			colorOpacity[1] = 1;
		}
    }
	
	public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException
	{
		Input input = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_F))
		{
			colorOpacity[0] = .5f;
			updateCounter++;
		}
		if (input.isKeyPressed(Input.KEY_D))
		{
			move2Left.start();
			selected -= 2;
		}
		if (input.isKeyDown(Input.KEY_J))
		{
			colorOpacity[1] = .5f;
			updateCounter++;
		}
		if (input.isKeyPressed(Input.KEY_K))
		{
			move2Right.start();
			selected += 2;
		}
		
		if (selected < 0)
			selected += numSongs;
		if (selected > 9)
			selected = numSongs - selected;
		
		if (moveLeft.isActive())
		{
			songAngles = (float[]) moveLeft.transition(songAngles);
			for (int i = 0; i < songPoints.length; i++)
				songPoints[i] = new Point(fullCenter.getX() + ((float) (fullRadius * Math.cos((Math.PI/180) * (songAngles[i] + 90)))), fullCenter.getY() - ((float) (fullRadius * Math.sin((Math.PI/180) * (songAngles[i] + 90)))));
		}
		if (move2Left.isActive())
		{
			songAngles = (float[]) move2Left.transition(songAngles);
			for (int i = 0; i < songPoints.length; i++)
				songPoints[i] = new Point(fullCenter.getX() + ((float) (fullRadius * Math.cos((Math.PI/180) * (songAngles[i] + 90)))), fullCenter.getY() - ((float) (fullRadius * Math.sin((Math.PI/180) * (songAngles[i] + 90)))));
		}
		
		if (moveRight.isActive())
		{
			songAngles = (float[]) moveRight.transition(songAngles);
			for (int i = 0; i < songPoints.length; i++)
				songPoints[i] = new Point(fullCenter.getX() + ((float) (fullRadius * Math.cos((Math.PI/180) * (songAngles[i] + 90)))), fullCenter.getY() - ((float) (fullRadius * Math.sin((Math.PI/180) * (songAngles[i] + 90)))));
		}
		if (move2Right.isActive())
		{
			songAngles = (float[]) move2Right.transition(songAngles);
			for (int i = 0; i < songPoints.length; i++)
				songPoints[i] = new Point(fullCenter.getX() + ((float) (fullRadius * Math.cos((Math.PI/180) * (songAngles[i] + 90)))), fullCenter.getY() - ((float) (fullRadius * Math.sin((Math.PI/180) * (songAngles[i] + 90)))));
		}
		
		numGlowCircles = (float) pulse.transition(numGlowCircles);
		
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			state.enterState(0, new FadeOutTransition(Color.black, 750), new FadeInTransition(Color.black, 750));
	}
	
	public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException
	{
		g.setAntiAlias(true);
		g.setLineWidth(3);
		g.setColor(Color.white);
		g.fill(new Rectangle(0, 0, gc.getScreenWidth(), gc.getScreenHeight()));
		g.setColor(Color.black);
		
		g.draw(new Circle(fullCenter.getX(), fullCenter.getY(), fullRadius));
		
		for (int i = 0; i < numSongs; i++)
		{
			g.fill(new Circle(songPoints[i].getX(), songPoints[i].getY(), songRadius));
			wordFont.drawString(songPoints[i].getX() - wordFont.getWidth(songNames[i])/2, songPoints[i].getY() + ((gc.getScreenHeight() - songPoints[i].getY()))/2, songNames[i], Color.black);
		}
		
		g.setColor(Color.white);
		g.fillArc(fullCenter.getX() - fullRadius, fullCenter.getY() - fullRadius + 75, 2 * fullRadius, 2 * fullRadius, 230, 260);
		g.fillArc(fullCenter.getX() - fullRadius, fullCenter.getY() - fullRadius + 75, 2 * fullRadius, 2 * fullRadius, 280, 310);
		for (int i = 0; i < 5; i++)
		{
			g.setColor(new Color(255, 255, 255, 1.0f - (1.0f/5) * i));
			g.fillArc(fullCenter.getX() - fullRadius, fullCenter.getY() - fullRadius + 75, 2 * fullRadius, 2 * fullRadius, 260 + i * .75f, 261 + i * .75f);
			g.fillArc(fullCenter.getX() - fullRadius, fullCenter.getY() - fullRadius + 75, 2 * fullRadius, 2 * fullRadius, 279 - i * .75f, 280 - i * .75f);
		}
			
		g.setLineWidth(1);
		for (int i = 0; i < numGlowCircles; i++)
		{
			g.setColor(new Color(255, 0, 0, (1.0f/numGlowCircles) * i/colorOpacity[0]));
			g.drawArc(pulseCirclePoint.getX() - (songRadius + numGlowCircles - 1 - (1 * i)), pulseCirclePoint.getY() - (songRadius + numGlowCircles - (1 * i)), (songRadius*2) + (numGlowCircles - (1 * i)) * 2, (songRadius*2) + (numGlowCircles - (1 * i)) * 2, 90, 270);
			g.setColor(new Color(0, 0, 255, (1.0f/numGlowCircles) * i/colorOpacity[1]));
			g.drawArc(pulseCirclePoint.getX() - (songRadius + numGlowCircles + 1 - (1 * i)), pulseCirclePoint.getY() - (songRadius + numGlowCircles - (1 * i)), (songRadius*2) + (numGlowCircles - (1 * i)) * 2, (songRadius*2) + (numGlowCircles - (1 * i)) * 2, 270, 90);
			//Draws full circle pulsating
			//g.draw(new Circle(pulseCirclePoint.getX(), pulseCirclePoint.getY(), songRadius + numGlowCircles - (1 * i)));
			//The one below gives us a pulsing star thing (cool)
			//g.draw(new Circle(100, 100, numGlowCircles - (1 * i)));
			//The one below this draws 3-D stuff that looks cool
			//g.drawArc(songPoints[selected].getX() - songRadius - numGlowCircles + (1 + i), songPoints[selected].getY() - songRadius - numGlowCircles + (1 * i), 2 * songRadius + (1 * i), 2 * songRadius + (1 * i), 180, 360);
			g.setColor(new Color(255, 255, 0, (1.0f/numGlowCircles) * i));
			g.drawArc(fullCenter.getX() - (fullRadius + numGlowCircles - (1 * i)), fullCenter.getY() - 3 - (fullRadius + numGlowCircles - (1 * i)), (2*fullRadius) + (numGlowCircles - (1 * i)) * 2, (2*fullRadius) + (numGlowCircles - (1 * i)) * 2, 230, 260);
			g.setColor(new Color(0, 255, 0, (1.0f/numGlowCircles) * i));
			g.drawArc(fullCenter.getX() - (fullRadius + numGlowCircles - (1 * i)), fullCenter.getY() - 3 - (fullRadius + numGlowCircles - (1 * i)), (2*fullRadius) + (numGlowCircles - (1 * i)) * 2, (2*fullRadius) + (numGlowCircles - (1 * i)) * 2, 280, 310);
		}
		
		
		/*g.setColor(Color.orange);
		
		int oldX = 0;
		int oldY = gc.getScreenHeight()/2;
		int xIndex = 0;
		
		int t = 0;

		for (t = 0; t < increment; t += increment)
		{
			g.drawLine(oldX, oldY, xIndex, oldY);
			xIndex++;
			oldX = xIndex;
		}
		
		for (; t < toReturn[0].length; t += increment)
		{
			double scaledSample = toReturn[0][t] * scaler;
			int y = (int) ((gc.getScreenHeight() / 2) - (scaledSample));
			g.drawLine(oldX, oldY, xIndex, y);

			xIndex++;
			oldX = xIndex;
			oldY = y;
		}*/
		
		//g.drawString("Tutorial State", 0, 0);
	}

	public int getID()
	{
		return 5;
	}
}