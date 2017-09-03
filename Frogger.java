import jig.engine.util.Vector2D;


/**
 * This class represents the actual frog object that the user controls using the
 * keyboard
 *
 * @author Uday Tripathi 
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class Frogger extends DynamicObject
{
    /**
     * The distance that the frog moves per key click on the screen
     */
    final static int MOVE_STEP = 32;

    /**
     * The animation step count for keeping the frame speed at 8 animation
     * frames for 10 miliseconds each
     */
    final static private int ANIMATION_STEP = 4;

    /**
     * Current animation frame that is on the screen
     */
    private int curAnimationFrame = 0;

    /**
     * Final animation frame that should appear
     */
    private int finalAnimationFrame = 0;

    /**
     * The delay between the animation occurring and disappearing from the
     * screen
     */
    private long animationDelay = 10;

    /**
     * The time at which the animation should begin
     */
    private long animationBeginTime = 0;

    /**
     * Boolean to check if the frog is being animated
     */
    private boolean isAnimating = false;

    /**
     * The direction that the animated frog should be moving towards
     */
    private Vector2D dirAnimation = new Vector2D( 0, 0 );

    /**
     * The object that the frog is on and must follow to not drown in the river
     */
    private DynamicObject followObject = null;

    /**
     * Boolean to see if the frog is alive
     */
    public boolean isAlive = false;

    /**
     * Keeps track of the time at which the frog dies, which factors into the
     * score of the user
     */
    private long timeOfDeath = 0;

    /**
     * Current sprite frame
     */
    private int currentFrame = 0;

    /**
     * A temporary sprite frame
     */
    private int tmpFrame = 0;

    /**
     * The change in time
     */
    public int deltaTime = 0;

    /**
     * Keeps track of whether an object has moved around the frog
     */
    public boolean hw_hasMoved = false;

    /**
     * The current game that the frog is in
     */
    private World game;


    /**
     * Constructor, calls super constructor from the DynamicObject class to
     * create the visual representation of the frog
     * 
     * @param w
     *            the world/game that the frog is in
     */
    public Frogger( World w )
    {
        super( World.GRAPHICS_PICS + "#mainsprite" );
        game = w;
        reset();
        collisionDetection.add( new Colliders( position ) );
    }


    /**
     * Resets frog to initial position as a new level/spawn begins
     */
    public void reset()
    {
        isAlive = true;
        isAnimating = false;
        currentFrame = 0;
        followObject = null;
        position = World.FROGGER_START;
        game.levelTimer = World.DEFAULT_LEVEL_TIME;
    }


    /**
     * Moves the frog to the left when the user presses the left key
     */
    public void strafeLeft()
    {
        if ( getCenterPosition().getX() - 16 > 0 && isAlive && !isAnimating )
        {
            currentFrame = 3;
            move( new Vector2D( -1, 0 ) );
        }
    }


    /**
     * Moves the frog to the right when the user presses the right key
     */
    public void strafeRight()
    {
        if ( getCenterPosition().getX() + 32 < World.WORLD_WIDTH && isAlive && !isAnimating )
        {
            currentFrame = 2;
            move( new Vector2D( 1, 0 ) );
        }
    }


    /**
     * Moves the frog up when the user presses the up key
     */
    public void up()
    {
        if ( position.getY() > 32 && isAlive && !isAnimating )
        {
            currentFrame = 0;
            move( new Vector2D( 0, -1 ) );
        }
    }


    /**
     * Moves the frog down when the user presses the down key
     */
    public void down()
    {
        if ( position.getY() < World.WORLD_HEIGHT - MOVE_STEP && isAlive && !isAnimating )
        {
            currentFrame = 1;
            move( new Vector2D( 0, 1 ) );
        }
    }


    /**
     * Gets the time currently in the system
     * 
     * @return the time in the game according to CPU in milliseconds
     */
    public long getTime()
    {
        return System.currentTimeMillis();
    }


    /**
     * Moves the frog to a given location according to a vector
     * 
     * @param xy
     *            location (vector) that the frog is to move to
     */
    public void move( Vector2D xy )
    {
        followObject = null;
        curAnimationFrame = 0;
        finalAnimationFrame = MOVE_STEP / ANIMATION_STEP;
        isAnimating = true;
        hw_hasMoved = true;
        animationBeginTime = getTime();
        dirAnimation = xy;

        tmpFrame = currentFrame;

        refresh( new Vector2D( position.getX() + dirAnimation.getX() * MOVE_STEP,
            position.getY() + dirAnimation.getY() * MOVE_STEP ) );
    }


    /**
     * Updates the GUI based on what the current state of the frog is
     */
    public void update()
    {

        if ( !isAnimating || !isAlive )
        {
            refresh( position );
            return;
        }

        if ( curAnimationFrame >= finalAnimationFrame )
        {
            isAnimating = false;
            currentFrame = tmpFrame;
            return;
        }

        if ( animationBeginTime + animationDelay < getTime() )
        {
            animationBeginTime = getTime();
            position = new Vector2D( position.getX() + dirAnimation.getX() * ANIMATION_STEP,
                position.getY() + dirAnimation.getY() * ANIMATION_STEP );
            curAnimationFrame++;
            return;
        }
    }


    /**
     * Align frog to the appropriate x coordinate in the world
     */
    public void alignToX()
    {
        if ( isAnimating || followObject != null )
            return;
        double x = position.getX();
        x = Math.round( x / 32 ) * 32;
        position = new Vector2D( x, position.getY() );
    }


    /**
     * Updates the frog's location if it has jumped on to a pad in the river
     * 
     * @param time
     *            system's time tracker
     */
    public void followUpdate( long time )
    {
        if ( followObject == null || !isAlive )
            return;
        Vector2D dS = followObject.getVelocity().scale( time );
        position = new Vector2D( position.getX() + dS.getX(), position.getY() + dS.getY() );
    }


    /**
     * Keeps the frog on the pad/animal on which it is on top of to cross the
     * river
     * 
     * @param obj
     *            Pad, Secondary Pad, Turtle, or Crocodile that the frog is on
     *            top of
     */
    public void follow( DynamicObject obj )
    {
        followObject = obj;
    }


    /**
     * Called when the frog dies and resets the game. The frog is in its initial
     * position and a life is deducted from the user
     */
    public void die()
    {
        if ( isAnimating )
            return;

        followObject = null;
        isAlive = false;
        currentFrame = 4;
        game.GameLives--;
        hw_hasMoved = true;

        timeOfDeath = getTime();
        game.levelTimer = World.DEFAULT_LEVEL_TIME;
    }


    /**
     * When the frog reaches its final destination, one of the lily pads at the
     * end of the world, this method is called
     * 
     * @param d
     *            the lily pad that the frog has reached
     */
    public void reachEnd( final Destination d )
    {
        if ( d.destinationReached == false )
        {
            game.GameScore += 100;
            game.GameScore += game.levelTimer;

            d.reach();
            reset();
        }
        else
        {
            setPosition( d.getPosition() );
        }
    }


    /**
     * Updates the world depending on the situation in the game
     * 
     * @param time
     *            system's time tracker
     */
    public void update( final long time )
    {
        if ( game.GameLives <= 0 )
        {
            return;
        }

        if ( !isAlive && timeOfDeath + 2000 < System.currentTimeMillis() )
        {
            reset();
        }

        update();
        followUpdate( time );
        setFrame( currentFrame );

        // Level Timer
        deltaTime += time;
        if ( deltaTime > 1000 )
        {
            deltaTime = 0;
            game.levelTimer--;
        }

        if ( game.levelTimer <= 0 )
        {
            die();
        }
    }


    /**
     * Returns if a frog is alive for the purposes of testing.
     * 
     * @return true if frog isAlive, false otherwise
     */
    public boolean getIsAlive()
    {
        return isAlive;
    }


    /**
     * Allows us to set the state of isAlive for the purposes of testing.
     * 
     * @param b
     *            Input boolean to set isAlive to
     */
    public void setIsAlive( boolean b )
    {
        if ( b == true )
        {
            isAlive = true;
        }
        else
        {
            isAlive = false;
        }
    }
}
