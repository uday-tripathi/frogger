import jig.engine.physics.vpe.VanillaSphere;
import jig.engine.util.Vector2D;


/**
 * Implements and sets the position of our collision objects. Extends the 
 * VanillaSphere superclass from the JIG. 
 *
 * @author Vaishnav Balaji
 * @version May 5, 2016
 * @author Period: 5th
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class Colliders extends VanillaSphere
{
    /**
     * No-argument Constructor
     * 
     * @param xy
     *            vector (location) of the collider
     */
    public Colliders( Vector2D xy )
    {
        super( "col" );
        setPosition( xy );
    }


    /**
     * Two parameter Constructor
     * 
     * @param id
     *            Specific string that user wants to reference to collider
     * @param xy
     *            vector (location) of the collider
     */
    public Colliders( String id, Vector2D xy )
    {
        super( id );
        setPosition( xy );
    }


    /**
     * Sets the position of the collider object at a given location
     * 
     * @param xy
     *            vector (location) of the collider where it should be played
     */
    public void setPosition( Vector2D xy )
    {
        double changeInX = 16 - getRadius();
        double changeInY = -getRadius() + 16;
        position = new Vector2D( xy.getX() + changeInX, xy.getY() + changeInY );
    }


    /**
     * No functionality. Only placeholder to satisfy the abstract method
     * specification
     * 
     * @param time
     *            of system
     */
    public void update( long time )
    {
        //Unneeded
    }
}
