import jig.engine.physics.Body;
import jig.engine.util.Vector2D;
import java.util.*;


/**
 * An abstract class to be used for all objects that aren't static, such as our
 * frog, obstacles, and even the weather. Extends the Body class provided to us
 * by the Java Instructional Game Engine.
 *
 * @author Vaishnav Balaji
 * @version May 5, 2016
 * @author Period: 5th
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class DynamicObject extends Body
{
    /**
     * Used in JUnit testing.
     */
    private int i;

    /**
     * List of colliders that keeps track of all the objects that the frog can
     * collide with
     */
    public List<Colliders> collisionDetection;


    /**
     * Constructor that calls the superconstructor provided from the Body class
     * of the JIG
     * 
     * @param id
     *            the reference to the file with the dynamic objects
     */
    public DynamicObject( String id )
    {
        super( id );
        collisionDetection = new LinkedList<Colliders>();
    }


    /**
     * Getter method to return the colliders
     * 
     * @return the list of colliders objects
     */
    public List<Colliders> getColliders()
    {
        return collisionDetection;
    }


    /**
     * Resets the level once a frog has died. This method ensures that all the
     * moving objects return to their original place once the frog is at the
     * beginning
     * 
     * @param xy
     *            location (vector) at which the object is currently at
     */
    public void refresh( Vector2D xy )
    {
        i = 0;
        for ( Colliders c : collisionDetection )
        {
            Vector2D changeInPosition = new Vector2D( xy.getX() + ( 32 * i ), position.getY() );
            c.setPosition( changeInPosition );
            i++;
        }
    }


    /**
     * Updates the position of the moving objects on the screen and changes
     * their velocity based on the level at which the user is currently at
     * 
     * @param time
     *            the system's time tracker
     */
    public void update( final long time )
    {
        if ( position.getX() > World.WORLD_WIDTH + width || position.getX() < -( 32 * 4 ) )
        {
            setActivation( false );
        }

        position = new Vector2D( position.getX() + velocity.getX() * time, position.getY() + velocity.getY() * time );
        refresh( position );
    }


    /**
     * Getter method for i to be used in testing.
     * 
     * @return i (counter)
     */
    public int getI()
    {
        return i;
    }

}
