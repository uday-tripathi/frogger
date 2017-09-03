
import jig.engine.util.Vector2D;


/**
 * Creates a shorter pad for the frog to travel on and cross the river. It
 * extends DynamicObject and is a representation of its super class
 *
 * @author Vaishnav Balaji
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class SecondaryPad extends DynamicObject
{
    /**
     * The length of the secondary pad in the game
     */
    public static int LENGTH = 32 * 3;


    /**
     * Constructor
     * 
     * @param xy
     *            location of the secondary pad in the game
     * @param velocity1
     *            velocity of the moving secondary pad across the screen
     */
    public SecondaryPad( Vector2D xy, Vector2D velocity1 )
    {
        super( World.GRAPHICS_PICS + "#shortpad" );
        position = xy;
        Vector2D posSphere1 = position;
        Vector2D posSphere2 = new Vector2D( position.getX() + 32, position.getY() );
        Vector2D posSphere3 = new Vector2D( position.getX() + 64, position.getY() );
        collisionDetection.add( new Colliders( "colSmall", posSphere1 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere2 ) );
        collisionDetection.add( new Colliders( "colSmall", posSphere3 ) );
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
