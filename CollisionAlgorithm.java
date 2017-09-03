import java.util.*;
import jig.engine.physics.*;
import jig.engine.util.*;


/**
 * This class calculates and implements the specific algorithm necessary when
 * the frog collides with any moving object in the game
 *
 * @author Vaishnav Balaji
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class CollisionAlgorithm
{
    /**
     * Frogger object that represents the user controlled frog in the game
     */
    public Frogger f;

    /**
     * Collider object that represents the location at which a collision occurs
     */
    public Colliders frogSphere;

    /**
     * River bounds for the bottom of the world
     */
    public int river_y0 = 1 * 32;

    /**
     * River bounds for the top of the world
     */
    public int river_y1 = river_y0 + 6 * 32;

    /**
     * Road bounds for the bottom of the world
     */
    public int road_y0 = 8 * 32;

    /**
     * Road bounds for the top of the world
     */
    public int road_y1 = road_y0 + 5 * 32;


    /**
     * Constructor that instantiates the CollisionAlgorithm object
     * 
     * @param frog
     *            Frogger object that collides with the objects on screen
     */
    public CollisionAlgorithm( Frogger frog )
    {
        f = frog;
        frogSphere = frog.getColliders().get( 0 );
    }


    /**
     * Tests if there has been a collision on the screen. The result is the frog
     * dying or proceeding. This includes going off of the screen or colliding
     * into one of the obstacles
     * 
     * @param layer
     *            the different dynamic objects that the frog can collide with
     */
    public void collisionTest( AbstractBodyLayer<DynamicObject> layer )
    {
        if ( !f.isAlive )
        {
            return;
        }

        Vector2D frogPosition = frogSphere.getCenterPosition();

        double dist2;

        if ( checkBounds() )
        {
            f.die();
            return;
        }

        for ( DynamicObject i : layer )
        {
            if ( i.isActive() )
            {
                List<Colliders> collisionObjects = i.getColliders();
                for ( Colliders objectSphere : collisionObjects )

                {
                    dist2 = ( frogSphere.getRadius() + objectSphere.getRadius() )
                        * ( frogSphere.getRadius() + objectSphere.getRadius() );
                    if ( frogPosition.distance2( objectSphere.getCenterPosition() ) < dist2 )
                    {
                        collision( i, objectSphere );
                        return;
                    }
                }
            }
        }
        if ( inWater() )
        {
            f.die();
            return;
        }
    }


    /**
     * Checks if the frog is in the world's bounds
     * 
     * @return true or false, depending on if the frog is in bounds
     */
    public boolean checkBounds()
    {
        Vector2D pos = frogSphere.getCenterPosition();
        if ( pos.getY() < 32 || pos.getY() > World.WORLD_HEIGHT )
            return true;
        if ( pos.getX() < 0 || pos.getX() > World.WORLD_WIDTH )
            return true;
        return false;
    }


    /**
     * Checks if the frog is in the water (river)
     * 
     * @return true or false, depending on the frog's location
     */
    public boolean inWater()
    {
        Vector2D pos = frogSphere.getCenterPosition();

        if ( pos.getY() > river_y0 && pos.getY() < river_y1 )
            return true;

        return false;
    }


    /**
     * Checks if the frog is on the road
     * 
     * @return true or false, depending on the location of the frog
     */
    public boolean onPath()
    {
        Vector2D pos = frogSphere.getCenterPosition();

        if ( pos.getY() > road_y0 && pos.getY() < road_y1 )
            return true;

        return false;
    }


    /**
     * Deals with the collision, depending on whether the object with which the
     * frog collides with is an obstacle or a pad/crocodile/turtle. If the
     * Dynamic Object is a car/copcar/truck, the frog will die. If the object is
     * a pad/crocodile/turtle, the frog will hop on and continue the game.
     * 
     * @param d
     *            the object which which the frog collides
     * @param c
     *            the collider object that the frog collides with
     */
    public void collision( DynamicObject d, Colliders c )
    {
        if ( d.getClass() == ObstacleFive.class || d.getClass() == ObstacleOne.class
            || d.getClass() == ObstacleTwo.class )
        {
            f.die();
        }

        if ( d.getClass() == ObstacleThree.class )
        {
            if ( c == ( (ObstacleThree)d ).head )
            {
                f.die();
            }
            else
            {
                f.follow( d );
            }
        }

        if ( d.getClass() == Pad.class || d.getClass() == SecondaryPad.class )
        {
            f.follow( d );
        }

        if ( d.getClass() == ObstacleFour.class )
        {
            if ( ( (ObstacleFour)d ).isUnderwater == true )
                f.die();
            f.follow( d );
        }

        if ( d.getClass() == Destination.class )
        {
            f.reachEnd( (Destination)( d ) );
        }
    }

}
