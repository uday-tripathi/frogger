
import jig.engine.util.Vector2D;


/**
 * Represents an obstacle that the frog encounters in the game. In this case,
 * that obstacle is a car that moves across the road
 *
 * @author Uday Tripathi
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class ObstacleOne extends DynamicObject
{

    /**
     * The number of kinds of cars
     */
    public final static int KINDS = 3;

    /**
     * The size of the cars
     */
    public final static int DIMENSIONS = 32;


    /**
     * Constructor
     * 
     * @param xy
     *            location vector of the car where it should begin
     * @param velocity1
     *            velocity of the moving car
     * @param random
     *            the type of car that needs to be made
     */
    public ObstacleOne( Vector2D xy, Vector2D velocity1, int random )
    {
        super( World.GRAPHICS_PICS + "#car" + random );
        position = xy;
        collisionDetection.add( new Colliders( position ) );
        velocity = velocity1;
        if ( velocity1.getX() < 0 )
        {
            setFrame( 1 );
        }
        else
        {
            setFrame( 0 );
        }
    }
}