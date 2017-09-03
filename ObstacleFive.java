
import jig.engine.util.*;


/**
 * Represents one of the obstacles that the frog must encounter. In this
 * specific implementation, this is a truck object on the road
 *
 * @author Uday Tripathi
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class ObstacleFive extends DynamicObject
{

    /**
     * Length of the truck
     */
    public static int LENGTH = 32 * 2;


    /**
     * Constructor for the obstacle (truck)
     * 
     * @param xy
     *            location at which the truck is to be started on the screen
     * @param velocity1
     *            velocity with which the truck should move
     */
    public ObstacleFive( Vector2D xy, Vector2D velocity1 )
    {
        super( World.GRAPHICS_PICS + "#truck" );
        position = xy;
        Vector2D posSphere1 = position;
        Vector2D posSphere2 = new Vector2D( position.getX() + 32, position.getY() );
        collisionDetection.add( new Colliders( posSphere1 ) );
        collisionDetection.add( new Colliders( posSphere2 ) );
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
