import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
 
public class MainDDR extends StateBasedGame
{
	Controls controls = new Controls();
	Rhythms engineRhythm = new Rhythms();
	StateHandler stateHandler = new StateHandler();
	
	public MainDDR(String title)
	{
        super(title);
    }
	
    public static void main(String[] args) throws SlickException
    {
    	AppGameContainer app = new AppGameContainer(new MainDDR("Shady Rhythms"));
        
        app.setDisplayMode(app.getScreenWidth() - 150, app.getScreenHeight() - 100, false);
        System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
        app.setTitle("Full Circle");
        app.setTargetFrameRate(60);
        app.setShowFPS(false);
        app.setVSync(true);
        app.start();
    }
 
    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
    	this.addState(new LogoState());
    	this.addState(new FullCircleLogoState());
    	this.addState(new MenuState(stateHandler));
    	this.addState(new ArcadeState(engineRhythm, stateHandler));
    	this.addState(new GameState(engineRhythm, stateHandler));
    	this.addState(new TutorialState(controls));
    	this.addState(new OptionsState(controls));
    	this.addState(new StoryState(engineRhythm, stateHandler));
    }
 
}