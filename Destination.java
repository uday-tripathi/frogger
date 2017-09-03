import jig.engine.util.*;


/**
 * This class represents the lily pods at the end of each level to which each
 * frog must reach to complete a level It extends the DynamicObject class as the
 * lily pads are also these types of objects
 *
 * @author Vaishnav Balaji
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class Destination extends DynamicObject
{
    /**
     * Boolean keepts track of whether the frog has reached the final lily pad
     */
    public boolean destinationReached = false;

   

    /**
     * Constructor
     * 
     * @param location
     *            the place where the final lily pad is and has to be reached
     */
    public Destination( int location )
    {
        super( World.GRAPHICS_PICS + "#destination" );
        position = new Vector2D( 32 * ( 1 + 2 * location ), 32 );
        collisionDetection.add( new Colliders( "colSmall", position ) );
        refresh( position );
        setFrame( 0 );
    }


    /**
     * Constructor
     * 
     * @param xy
     *            location (in vector form) where the final lily pad is and has
     *            to be reached
     */
    public Destination( Vector2D xy )
    {
        super( World.GRAPHICS_PICS + "#destination" );
        position = xy;
        collisionDetection.add( new Colliders( "colSmall", position ) );
        refresh( position );
        setFrame( 0 );
    }


    /**
     * When the frog reaches the destination lily pad, the system registers it.
     */
    public void reach()
    {
        destinationReached = true;
        setFrame( 1 );
    }


    
    /**
     * Unnecessary, only present for abstract method implementation purposes.
     */
    public void update( long time )
    {

    }
}
