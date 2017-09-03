
import jig.engine.util.*;


/**
 * Creates an obstacle that the frog encounters during the game. This specific
 * implementation creates a fast moving cop car for the frog to deal with
 *
 * @author Uday Tripathi 
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class ObstacleTwo extends DynamicObject
{

    /**
     * Constructor
     * 
     * @param xy
     *            location of the cop car
     * @param velocity1
     *            the velocity with which the cop car moves across the road
     */
    public ObstacleTwo( Vector2D xy, Vector2D velocity1 )
    {
        super( World.GRAPHICS_PICS + "#policecar" );
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