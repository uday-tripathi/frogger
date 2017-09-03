import java.awt.event.KeyEvent;
import jig.engine.*;
import jig.engine.PaintableCanvas.*;
import jig.engine.hli.*;
import jig.engine.physics.*;
import jig.engine.util.Vector2D;


/**
 * This class is the main class of the Frogger game that has to be run in order
 * for the game to be launched. The World class implements all of the obstacles
 * and pads, as well as the weather objects. It also sets up the references to
 * the sprite sheet in order for the UI of the game to work.
 *
 * @author Uday Tripathi and Vaishnav Balaji
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class World extends StaticScreenGame
{
    /**
     * The moving objects on the first line in the road
     */
    private DynamicObjectGenerator road1;

    /**
     * The moving objects on the second line in the road
     */
    private DynamicObjectGenerator road2;

    /**
     * The moving objects on the third line in the road
     */
    private DynamicObjectGenerator road3;

    /**
     * The moving objects on the fourth line in the road
     */
    private DynamicObjectGenerator road4;

    /**
     * The moving objects on the fifth line in the road
     */
    private DynamicObjectGenerator road5;

    /**
     * Layer keeps track of the moving objects in the game
     */
    private AbstractBodyLayer<DynamicObject> movingObj;

    /**
     * Layer keeps track of the weather (lightning) coming into the game
     */
    private AbstractBodyLayer<DynamicObject> weatherLayer;

    /**
     * The moving objects on the first line in the river
     */
    private DynamicObjectGenerator river1;

    /**
     * The moving objects on the second line in the river
     */
    private DynamicObjectGenerator river2;

    /**
     * The moving objects on the third line in the river
     */
    private DynamicObjectGenerator river3;

    /**
     * The moving objects on the fourth line in the river
     */
    private DynamicObjectGenerator river4;

    /**
     * The moving objects on the fifth line in the river
     */
    private DynamicObjectGenerator river5;

    /**
     * The CollisionAlgorithm object that allows for the collision of a frog and
     * an obstacle during a game
     */
    private CollisionAlgorithm frogCol;

    /**
     * The frog object that the user controls in the game
     */
    private Frogger frog;

    /**
     * The GUI that the user sees on the screen
     */
    private GUI ui;

    /**
     * The background on top of which the game is played
     */
    private ImageBackgroundLayer bckgLayer;

    /**
     * References the folder in which the graphic files of the game are stored
     */
    static final String RSC_PATH = "resources/";

    /**
     * References the sprite sheet from which the various images are stored
     */
    static final String GRAPHICS_PICS = RSC_PATH + "vaishnavuday_sprites.png";

    /**
     * Represents the lightning object that represents the weather
     */
    private Weather lightning;

    /**
     * Keeps track of the completion of a frog's journey to a lily pad
     */
    private DestinationInfo destinationManager;

    /**
     * Represents the number of lives a frog to cross the world
     */
    static final int FROGGER_LIVES = 5;

    /**
     * Represents the level at which the frog starts the game
     */
    static final int STARTING_LEVEL = 1;

    /**
     * Represents the amount of time a frog has to cross the world
     */
    static final int DEFAULT_LEVEL_TIME = 60;

    /**
     * The default starting state of the game
     */
    protected int GameState = GAME_INTRO;

    /**
     * The defalt starting level of the game
     */
    protected int GameLevel = STARTING_LEVEL;

    /**
     * The default number of lives for the frog to cross the river
     */
    public int GameLives = FROGGER_LIVES;

    /**
     * The starting score for the user
     */
    public int GameScore = 0;

    /**
     * The timer that keeps track of how long a frog has been on a level
     */
    public int levelTimer = DEFAULT_LEVEL_TIME;

    /**
     * The state at which the user has just launched the game
     */
    static final int GAME_INTRO = 0;

    /**
     * The state at which the user is playing the game
     */
    static final int GAME_PLAY = 1;

    /**
     * The state at which the user has completed a level
     */
    static final int GAME_FINISH_LEVEL = 2;

    /**
     * The state at which the instructions should be prompted to the screen
     */
    static final int GAME_INSTRUCTIONS = 3;

    /**
     * The state at which the user loses and the game is over
     */
    static final int GAME_OVER = 4;

    /**
     * Keeping track of whether the user is ready to play the game
     */
    private boolean space_has_been_released = false;

    /**
     * Keeping track of whether a key is pressed on the keyboard by the user
     */
    private boolean keyPressed = false;

    /**
     * Keeping track of whether the user's input on the up, down, left, right
     * keys is to be registered within the gameplay
     */
    private boolean listenInput = true;

    /**
     * Represents the width of the GUI
     */
    static final int WORLD_WIDTH = ( 13 * 32 );

    /**
     * Represents the height of the GUI
     */
    static final int WORLD_HEIGHT = ( 14 * 32 );

    /**
     * Represents the location at which the frog starts a level
     */
    static final Vector2D FROGGER_START = new Vector2D( 6 * 32, WORLD_HEIGHT - 32 );


    /**
     * Constructs a world object that enables game play Calls the superclass's
     * constructor (StaticGameScreen) to instantiate the game Displays the
     * images from the sprite sheet and creates the various layers to be
     * displayed on the screen Instantiates the instance variables frog,
     * frogCol, ui, lightning, and goal manager to prepare for game play
     */
    public World()
    {
        super( WORLD_WIDTH, WORLD_HEIGHT, false );

        gameframe.setTitle( "Frogger Game" );

        ResourceFactory.getFactory().loadResources( RSC_PATH, "resources.xml" );

        ImageResource bkg = ResourceFactory.getFactory().getFrames( GRAPHICS_PICS + "#worldScreen" ).get( 0 );
        bckgLayer = new ImageBackgroundLayer( bkg, WORLD_WIDTH, WORLD_HEIGHT, ImageBackgroundLayer.TILE_IMAGE );

        PaintableCanvas.loadDefaultFrames( "col", 30, 30, 2, JIGSHAPE.RECTANGLE, null );
        PaintableCanvas.loadDefaultFrames( "colSmall", 4, 4, 2, JIGSHAPE.RECTANGLE, null );

        frog = new Frogger( this );
        frogCol = new CollisionAlgorithm( frog );
        ui = new GUI( this );
        lightning = new Weather();
        destinationManager = new DestinationInfo();

        movingObj = new AbstractBodyLayer.IterativeUpdate<DynamicObject>();
        weatherLayer = new AbstractBodyLayer.IterativeUpdate<DynamicObject>();

        beginLevel( 1 );
    }


    /**
     * Allows for the various images in the game to be displayed on the screen
     * for the user
     * 
     * @param r
     *            the Rendering object (JIG) that allows for the actual visual
     *            representation of the game
     * @throws ArrayIndexOutOfBoundsException
     *             error handling
     */
    public void render( RenderingContext r ) throws ArrayIndexOutOfBoundsException
    {

        if ( GameState == GAME_FINISH_LEVEL || GameState == GAME_PLAY )
        {

            bckgLayer.render( r );

            if ( frog.isAlive )
            {
                movingObj.render( r );
                frog.render( r );
            }
            else
            {
                frog.render( r );
                movingObj.render( r );
            }

            weatherLayer.render( r );
            ui.render( r );
        }
        else if ( GameState == GAME_OVER || GameState == GAME_INSTRUCTIONS || GameState == GAME_INTRO )
        {

            bckgLayer.render( r );
            movingObj.render( r );
            ui.render( r );
        }

    }


    /**
     * Cycles the objects through the screen
     * 
     * @param time
     *            the amount of time that the object should be on the screen
     */
    public void obstacleCycle( long time )
    {
        DynamicObject m;
        /* Updates the moving objects on the road */
        road1.update( time );
        if ( ( m = road1.buildObstacle() ) != null )
        {
            movingObj.add( m );
        }

        road2.update( time );
        if ( ( m = road2.buildObstacle() ) != null )
        {
            movingObj.add( m );
        }

        road3.update( time );
        if ( ( m = road3.buildObstacle() ) != null )
        {
            movingObj.add( m );
        }

        road4.update( time );
        if ( ( m = road4.buildObstacle() ) != null )
        {
            movingObj.add( m );
        }

        road5.update( time );
        if ( ( m = road5.buildObstacle() ) != null )
        {
            movingObj.add( m );
        }

        /* Updates the moving objects on the river */
        river1.update( time );
        if ( ( m = river1.buildPadWithAnimal( 40 ) ) != null )
        {
            movingObj.add( m );
        }

        river2.update( time );
        if ( ( m = river2.buildSecondaryPadWithAnimal( 30 ) ) != null )
        {
            movingObj.add( m );
        }

        river3.update( time );
        if ( ( m = river3.buildPadWithAnimal( 50 ) ) != null )
        {
            movingObj.add( m );
        }

        river4.update( time );
        if ( ( m = river4.buildSecondaryPadWithAnimal( 20 ) ) != null )
        {
            movingObj.add( m );
        }

        river5.update( time );
        if ( ( m = river5.buildPadWithAnimal( 10 ) ) != null )
        {
            movingObj.add( m );
        }

        if ( ( m = lightning.generateWeatherBuilds( GameLevel ) ) != null )
        {
            weatherLayer.add( m );
        }

        movingObj.update( time );
        weatherLayer.update( time );
    }


    /**
     * Called when the game begins at the specified level. Creates the dynamic
     * objects on the river and on the road based on the level. On higher
     * levels, the objects are created to move faster and make the level tricker
     * for the user.
     * 
     * @param levelNumber
     *            the level number at which the game is to begin.
     */
    public void beginLevel( int levelNumber )
    {
        /*
         * Velocity of the moving objects based of the level number
         */
        double velocity_multiplier = levelNumber * 0.05 + 1;

        movingObj.clear();

        /* Road Objects */
        road1 = new DynamicObjectGenerator( new Vector2D( World.WORLD_WIDTH, 8 * 32 ),
            new Vector2D( -0.1 * velocity_multiplier, 0 ) );

        road2 = new DynamicObjectGenerator( new Vector2D( -( 32 * 4 ), 9 * 32 ),
            new Vector2D( 0.08 * velocity_multiplier, 0 ) );

        road3 = new DynamicObjectGenerator( new Vector2D( World.WORLD_WIDTH, 10 * 32 ),
            new Vector2D( -0.12 * velocity_multiplier, 0 ) );

        road4 = new DynamicObjectGenerator( new Vector2D( -( 32 * 4 ), 11 * 32 ),
            new Vector2D( 0.075 * velocity_multiplier, 0 ) );

        road5 = new DynamicObjectGenerator( new Vector2D( World.WORLD_WIDTH, 12 * 32 ),
            new Vector2D( -0.05 * velocity_multiplier, 0 ) );

        /* River Objects */
        river1 = new DynamicObjectGenerator( new Vector2D( -( 32 * 3 ), 2 * 32 ),
            new Vector2D( 0.06 * velocity_multiplier, 0 ) );

        river2 = new DynamicObjectGenerator( new Vector2D( World.WORLD_WIDTH, 3 * 32 ),
            new Vector2D( -0.04 * velocity_multiplier, 0 ) );

        river3 = new DynamicObjectGenerator( new Vector2D( -( 32 * 3 ), 4 * 32 ),
            new Vector2D( 0.09 * velocity_multiplier, 0 ) );

        river4 = new DynamicObjectGenerator( new Vector2D( -( 32 * 4 ), 5 * 32 ),
            new Vector2D( 0.045 * velocity_multiplier, 0 ) );

        river5 = new DynamicObjectGenerator( new Vector2D( World.WORLD_WIDTH, 6 * 32 ),
            new Vector2D( -0.045 * velocity_multiplier, 0 ) );

        destinationManager.start( levelNumber );
        for ( Destination g : destinationManager.getDestinationList() )
        {
            movingObj.add( g );
        }

        /*
         * Creates some moving objects to display for menu screen
         */
        for ( int i = 0; i < 500; i++ )
        {
            obstacleCycle( 10 );
        }
    }


    /**
     * Implements the necessary commands for user's pressing of the keys on the
     * keyboard
     */
    public void inputKey()
    {
        keyboard.poll();

        boolean keyReleased = false;
        boolean down = keyboard.isPressed( KeyEvent.VK_DOWN );
        boolean up = keyboard.isPressed( KeyEvent.VK_UP );
        boolean left = keyboard.isPressed( KeyEvent.VK_LEFT );
        boolean right = keyboard.isPressed( KeyEvent.VK_RIGHT );

        if ( down || up || left || right )
        {
            keyPressed = true;
        }
        else if ( keyPressed )
        {
            keyReleased = true;
        }

        if ( listenInput )
        {
            if ( down )
            {
                frog.down();
            }
            if ( up )
            {
                frog.up();
            }
            if ( left )
            {
                frog.strafeLeft();
            }
            if ( right )
            {
                frog.strafeRight();
            }

            if ( keyPressed )
            {
                listenInput = false;
            }
        }

        if ( keyReleased )
        {
            listenInput = true;
            keyPressed = false;
        }

        if ( keyboard.isPressed( KeyEvent.VK_ESCAPE ) )
            GameState = GAME_INTRO;
    }


    /**
     * Processes the commands by the user on the keyboard
     */
    public void checkCommand()
    {
        keyboard.poll();

        if ( !keyboard.isPressed( KeyEvent.VK_SPACE ) )
        {
            space_has_been_released = true;
        }

        if ( !space_has_been_released )
            return;

        if ( keyboard.isPressed( KeyEvent.VK_SPACE ) )
        {
            if ( GameState == GAME_OVER )
            {
                GameState = GAME_INTRO;
                space_has_been_released = false;
            }
            else
            {
                GameLives = FROGGER_LIVES;
                GameScore = 0;
                GameLevel = STARTING_LEVEL;
                levelTimer = DEFAULT_LEVEL_TIME;
                frog.setPosition( FROGGER_START );
                GameState = GAME_PLAY;
                beginLevel( GameLevel );
            }

        }

    }


    /**
     * Once a level is completed, this method allows for the user to advance to
     * the next level by pressing the "Space" key
     */
    public void inputAdvanceLevel()
    {
        keyboard.poll();
        if ( keyboard.isPressed( KeyEvent.VK_SPACE ) )
        {
            GameState = GAME_PLAY;
            beginLevel( ++GameLevel );
        }
    }


    /**
     * Abstract method implementation. Updates all the obstacles and objects on
     * the screen at their various locations
     * 
     * @param time
     *            at which the game is currently at
     */
    public void update( long time )
    {
        if ( GameState == GAME_PLAY )
        {
            inputKey();
            lightning.update( time );
            frog.update( time );
            ui.update( time );

            obstacleCycle( time );
            frogCol.collisionTest( movingObj );

            if ( frogCol.inWater() )
            {
                lightning.begin( GameLevel );
            }
            lightning.blow( frog, GameLevel, time );

            if ( !frog.isAlive )
            {
                weatherLayer.clear();
            }

            destinationManager.update( time );

            if ( destinationManager.getRemaining().size() == 0 )
            {
                GameState = GAME_FINISH_LEVEL;
                weatherLayer.clear();
            }

            if ( GameLives < 1 )
            {
                GameState = GAME_OVER;
            }

        }
        else if ( GameState == GAME_OVER || GameState == GAME_INSTRUCTIONS || GameState == GAME_INTRO )
        {
            destinationManager.update( time );
            checkCommand();
            obstacleCycle( time );
        }
        else if ( GameState == GAME_FINISH_LEVEL )
        {
            inputAdvanceLevel();
        }
    }


    /**
     * Main. Calls the JIG run method to get the game running on the screen
     * 
     * @param args
     *            arguments
     */
    public static void main( String[] args )
    {
        World w = new World();
        w.run();
    }

}
