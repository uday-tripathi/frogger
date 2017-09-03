import jig.engine.util.*;
import java.util.*;


/**
 * This class generates the Dynamic Object that is then used to visually
 * represent the object in the game.
 *
 * @author Vaishnav Balaji
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class DynamicObjectGenerator
{
    /**
     * The index at which the car is stored
     */
    public static int CAR = 0;

    /**
     * The index at which the truck is stored
     */
    public static int TRUCK = 1;

    /**
     * The index at which the secondary pad is stored
     */
    public static int SPAD = 2;

    /**
     * The index at which the pad is stored
     */
    public static int LPAD = 3;

    /**
     * The vector representation of the position of the moving object
     */
    public Vector2D position;

    /**
     * The vector representation of the velocity of the moving object
     */
    public Vector2D velocity;

    /**
     * Random number generator
     */
    public Random r;

    /**
     * The new time at which the game should be at
     */
    private long newTime = 0;

    /**
     * The time that must pass before police cars can begin to appear in the
     * road
     */
    private long policeCarDelay = 0;

    /**
     * The rate at which the cop car is moving
     */
    private long rateMs = 1000;

    /**
     * Represents the distance between 2 objects in a road/river line
     */
    private int padding = 64;

    /**
     * Array that keeps track of the creation of the various Dynamic Objects
     */
    private int[] dynamicObjectArr = new int[4];


    /**
     * Constructor that instantiates the various Dynamic Objects in the array
     * 
     * @param pos
     *            vector at which the object is to be generated
     * @param v
     *            vector of the velocity at which the object is moving
     */
    public DynamicObjectGenerator( Vector2D pos, Vector2D v )
    {
        position = pos;
        velocity = v;
        r = new Random( System.currentTimeMillis() );

        dynamicObjectArr[CAR] = (int)Math
            .round( ( ( ObstacleOne.DIMENSIONS ) + padding + 32 ) / Math.abs( velocity.getX() ) );
        dynamicObjectArr[TRUCK] = (int)Math
            .round( ( ( ObstacleFive.LENGTH ) + padding + 32 ) / Math.abs( velocity.getX() ) );
        dynamicObjectArr[SPAD] = (int)Math
            .round( ( ( SecondaryPad.LENGTH ) + padding - 32 ) / Math.abs( velocity.getX() ) );
        dynamicObjectArr[LPAD] = (int)Math.round( ( ( Pad.LENGTH ) + padding - 32 ) / Math.abs( velocity.getX() ) );
    }


    public int[] getDynamicObjectArr()
    {
        return dynamicObjectArr;
    }


    /**
     * Builds basic moving object depending on the condition of the user in the
     * game
     * 
     * @param type
     *            CAR, TRUCK, SPAD, LPAD
     * @param chance
     *            probability of the object appearing
     * @return DynamicObject on the environment and position that the user is
     *         currently in during the game/level. Varies with different
     *         situations
     */
    public DynamicObject buildBasicObject( int type, int chance )
    {
        if ( newTime > rateMs )
        {
            newTime = 0;

            if ( r.nextInt( 100 ) < chance )
            {
                if ( type == 0 ) // car
                {
                    rateMs = dynamicObjectArr[CAR];
                    return new ObstacleOne( position, velocity, r.nextInt( ObstacleOne.KINDS ) );
                }
                else if ( type == 1 ) // truck
                {
                    rateMs = dynamicObjectArr[TRUCK];
                    return new ObstacleFive( position, velocity );
                }
                else if ( type == 2 ) // small pad
                {
                    rateMs = dynamicObjectArr[SPAD];
                    return new SecondaryPad( position, velocity );
                }
                else if ( type == 3 ) // long pad
                {
                    rateMs = dynamicObjectArr[LPAD];
                    return new Pad( position, velocity );
                }
                else
                {
                    return null;
                }
            }

        }

        return null;
    }


    /**
     * This builds the secondary pad at the same time a crocodile or turtle is
     * made
     * 
     * @param chance
     *            probability of the creation occurring
     * @return the obstacle that is to be created
     */
    public DynamicObject buildSecondaryPadWithAnimal( int chance )
    {
        DynamicObject m = buildBasicObject( SPAD, 80 );
        if ( m != null && r.nextInt( 100 ) < chance )
        {
            return new ObstacleFour( position, velocity, r.nextInt( 2 ) );
        }
        return m;
    }


    /**
     * This builds the pad at the same time a crocodile is made
     * 
     * @param chance
     *            probability of the creation occurring
     * @return the obstacle that is to be created
     */
    public DynamicObject buildPadWithAnimal( int chance )
    {
        DynamicObject m = buildBasicObject( LPAD, 80 );
        if ( m != null && r.nextInt( 100 ) < chance )
        {
            return new ObstacleThree( position, velocity );
        }
        return m;
    }


    /**
     * Builds the obstacles. Cars are more common than trucks. If there are no
     * other vehicles in a given line, a cop car appears
     * 
     * @return the moving object that is chosen
     */
    public DynamicObject buildObstacle()
    {
        DynamicObject m;
        if ( r.nextInt( 100 ) < 80 )
        {
            m = buildBasicObject( CAR, 50 );
        }
        else
        {
            m = buildBasicObject( TRUCK, 50 );
        }

        if ( m != null )
        {
            if ( Math.abs( velocity.getX() * policeCarDelay ) > World.WORLD_WIDTH )
            {
                policeCarDelay = 0;
                return new ObstacleTwo( position, velocity.scale( 5 ) );
            }
            policeCarDelay = 0;
        }
        return m;
    }


    /**
     * Updates the GUI to keep the game moving
     * 
     * @param time
     *            the time tracker in the system
     */
    public void update( final long time )
    {
        newTime += time;
        policeCarDelay += time;
    }
}