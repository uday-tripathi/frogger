
import jig.engine.util.Vector2D;


/**
 * Creates an obstacle that the frog must encounter on its way across the world.
 * This specific implementation creates a crocodile object
 *
 * @author Uday Tripathi
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class ObstacleThree extends DynamicObject
{

    /**
     * The length of the crocodile in the game
     */
    public static int LENGTH = 32 * 3;

    /**
     * How long the delay is for the animation of the crocodile
     */
    private long animationDelay = 300;

    /**
     * How long the crocodile is animated on the screen during the game
     */
    private long animationTime = 0;

    /**
     * The frame at which the crocodile first enters the screen
     */
    private int startFrame = 0;

    /**
     * The next frame that should be present in the game
     */
    private int nextFrame = 0;

    /**
     * The head of the crocodile that the frog can collide with
     */
    protected Colliders head;


    /**
     * Constructor
     * 
     * @param xy
     *            location of the crocodile in vector form
     * @param velocity1
     *            velocity at which the crocodile moves across the screen
     */
    public ObstacleThree( Vector2D xy, Vector2D velocity1 )
    {
        super( World.GRAPHICS_PICS + "#monster" );
        position = xy;
        Vector2D posSphere1 = position;
        Vector2D posSphere2 = new Vector2D( position.getX() + 32 * 1, position.getY() );
        Vector2D posSphere3 = new Vector2D( position.getX() + 32 * 2, position.getY() );
        Vector2D posSphere4 = new Vector2D( position.getX() + 32 * 3, position.getY() );
        collisionDetection.add( new Colliders( "colSmall", posSphere1 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere2 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere3 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere4 ) );
        velocity = velocity1;

        if ( velocity1.getX() < 0 )
        {
            startFrame = 2;
            head = collisionDetection.get( 0 );
        }
        else
        {
            startFrame = 0;
            head = collisionDetection.get( 3 );
        }

        setFrame( startFrame );
    }


    /**
     * Moves the crocodile across the screen
     * 
     * @param time
     *            system's time tracker
     */
    public void move( long time )
    {
        animationTime += time;
        if ( animationTime > animationDelay )
        {
            animationTime = 0;
            nextFrame = ( nextFrame + 1 ) % 2;
            setFrame( nextFrame + startFrame );
        }
    }


    /**
     * Updates the game based on the crocodile's movement and collisions with
     * the frog
     * 
     * @param time
     *            system's time tracker
     */
    public void update( final long time )
    {
        super.update( time );
        move( time );
    }


    public long getAnimationTime()
    {
        return animationTime;
    }
}
