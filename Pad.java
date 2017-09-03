
import jig.engine.util.*;


/**
 * This class creates the pad on which the frog goes to cross the river The
 * class only has a constructor that allows for the initialization of the pad.
 * Extends DynamicObject
 *
 * @author Uday Tripathi
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class Pad extends DynamicObject
{
    /**
     * Represents the length of the pad on the screen
     */
    public static int LENGTH = 32 * 4;


    /**
     * Constructor of the pad. Calls the DynamicObject class to create the
     * representation of the pad and allow for its movement on the screen
     * 
     * @param xy
     *            The location at which the pad is
     * @param velocity1
     *            The speed at which the pad travels
     */
    public Pad( Vector2D xy, Vector2D velocity1 )
    {
        super( World.GRAPHICS_PICS + "#longpad" );
        position = xy;
        Vector2D posSphere1 = position;
        Vector2D posSphere2 = new Vector2D( position.getX() + 32 * 1, 
            position.getY() );
        Vector2D posSphere3 = new Vector2D( position.getX() + 32 * 2, 
            position.getY() );
        Vector2D posSphere4 = new Vector2D( position.getX() + 32 * 3, 
            position.getY() );
        collisionDetection.add( new Colliders( "colSmall", posSphere1 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere2 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere3 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere4 ) );
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
