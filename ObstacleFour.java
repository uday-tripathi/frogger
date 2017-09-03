
import jig.engine.util.*;


/**
 * Creates an obstacle that the frog must encounter as it completes each level.
 * This specific implementation creates a turtle
 *
 * @author Uday Tripathi
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class ObstacleFour extends DynamicObject
{
    /**
     * The amount of time that the turtle has been underwater
     */
    private long underwaterTime = 0;

    /**
     * The period of time that the turtle can be underwater
     */
    private long underwaterPeriod = 1200;

    /**
     * Checks if the turtle is underwater
     */
    protected boolean isUnderwater = false;

    /**
     * Checks if the game is animating the turtle
     */
    public boolean isAnimating = false;

    /**
     * The time that has passed since the turtle appeared
     */
    private long timeChange;

    /**
     * The time at which the turtle first starts animating
     */
    private long startTime;

    /**
     * The system's time tracker
     */
    private long timer;

    /**
     * The amount of time a turtle should be animating
     */
    private long animatingPeriod = 150;

    /**
     * Sprite sheet frame
     */
    private int aFrame = 0;

    /**
     * Max numbers of frames that the turtle should be present
     */
    private int max_aFrame = 2;

    Vector2D posSphere1;

    Vector2D posSphere2;

    Vector2D posSphere3;

    private int check = 5;


    /**
     * Constructor, calls super class Dynamic Object's constructor
     * 
     * @param xy
     *            location of the turtle in the world
     * @param velocity1
     *            velocity of the moving turtle
     */
    public ObstacleFour( Vector2D xy, Vector2D velocity1 )
    {
        super( World.GRAPHICS_PICS + "#turtles" );
        initialize( xy, velocity1 );
    }


    /**
     * Constructor, calls super class Dynamic Object's constructor
     * 
     * @param xy
     *            location of the turtle in the world
     * @param velocity1
     *            velocity of the moving turtle
     * @param submerged
     *            keeps track of whether or not the turtle is underwater
     */
    public ObstacleFour( Vector2D xy, Vector2D velocity1, int submerged )
    {
        super( World.GRAPHICS_PICS + "#turtles" );
        initialize( xy, velocity1 );

        if ( submerged == 0 )
        {
            isUnderwater = false;
        }
        else
        {
            isUnderwater = true;
            setFrame( getFrame() + 2 );
        }
    }


    /**
     * Initializes the turtles and their appropriate collision detection
     * algorithms based on their location in the world
     * 
     * @param xy
     *            location of the turtle
     * @param velocity1
     *            velocity of the moving turtle
     */
    public void initialize( Vector2D xy, Vector2D velocity1 )
    {
        position = xy;

        posSphere1 = position;
        posSphere2 = new Vector2D( position.getX() + 32, position.getY() );
        posSphere3 = new Vector2D( position.getX() + 64, position.getY() );
        collisionDetection.add( new Colliders( "colSmall", posSphere1 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere2 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere3 ) );
        velocity = velocity1;

        if ( velocity1.getX() < 0 )
            setFrame( 0 );
        else
            setFrame( 3 );
    }


    /**
     * Checks if the turtle is submerged or under water
     */
    public void timeCheck()
    {
        check++;
        underwaterTime += timeChange;
        if ( underwaterTime > underwaterPeriod )
        {
            underwaterTime = 0;
            animation();
        }

    }


    public int getCheck()
    {
        return check;
    }


    /**
     * Animates the turtle, whether it is over water or underwater and displays
     * the appopriate representation on the screen.
     */
    public void animation()
    {
        if ( !isAnimating )
            return;

        if ( startTime < timer )
        {
            startTime = timer + animatingPeriod;

            if ( isUnderwater )
            {
                setFrame( getFrame() - 1 );
            }
            else
            {
                setFrame( getFrame() + 1 );
            }

            aFrame++;
        }

        if ( aFrame >= max_aFrame )
        {
            isAnimating = false;
            isUnderwater = !isUnderwater;
        }
    }


    /**
     * Starts the animation of the turtle when it is time for it to show up on
     * the screen
     */
    public void beginAnimation()
    {
        isAnimating = true;
        startTime = 0;
        timer = 0;
        aFrame = 0;
    }


    public long getStartTime()
    {
        return startTime;
    }


    public long getTimer()
    {
        return timer;
    }


    public long getAFrame()
    {
        return aFrame;
    }


    /**
     * Updates the world when a turtle appears/disappears
     * 
     * @param time
     *            system's time tracker
     */
    public void update( long time )
    {
        super.update( time );
        timeChange = time;
        timer += timeChange;
        timeCheck();
        animation();
    }


    public boolean getIsUnderWater()
    {
        return isUnderwater;
    }


    public Vector2D getPosSphereOne()
    {
        return posSphere1;
    }


    public Vector2D getPosSphereTwo()
    {
        return posSphere2;
    }


    public Vector2D getPosSphereThree()
    {
        return posSphere3;
    }


    public long getUnderWaterTime()
    {
        return underwaterTime;
    }
}
