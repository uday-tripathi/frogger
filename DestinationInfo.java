import java.util.*;

import jig.engine.util.Vector2D;


/**
 * Represents the class that stores information for the final pads that the frog
 * must reach to complete the level.
 *
 * @author Vaishnav Balaji
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class DestinationInfo
{
    /**
     * The number of lily pads that are stored
     */
    final static int NUMBER_OF_DESTINATIONS = 6;

    /**
     * A list of destination objects that stores the final lily pads
     */
    private List<Destination> destinationList;

    /**
     * Checking first lily pad
     */
    private int check1 = 0;

    /**
     * Checking second lily pad
     */
    private int check2 = 0;


    /**
     * No-parameter Constructor that instantiates the list as a LinkedList and
     * begins the game in terms of displaying the destination
     */
    public DestinationInfo()
    {
        destinationList = new LinkedList<Destination>();
        start( 1 );
    }


    /**
     * Called when a level starts. If the user is on level 1, there are only two
     * lily pads to advance. If the user is on a later level, there are four
     * lily pads that need to be reached in order to advance.
     * 
     * @param level
     *            The level at which to start
     */
    public void start( final int level )
    {
        destinationList.clear();

        if ( level == 1 )
        {
            destinationList.add( new Destination( new Vector2D( 5 * 32, 32 ) ) );
            destinationList.add( new Destination( new Vector2D( 7 * 32, 32 ) ) );

        }
        else
        {

            destinationList.add( new Destination( new Vector2D( 5 * 32, 32 ) ) );
            destinationList.add( new Destination( new Vector2D( 7 * 32, 32 ) ) );
            destinationList.add( new Destination( new Vector2D( 3 * 32, 32 ) ) );
            destinationList.add( new Destination( new Vector2D( 9 * 32, 32 ) ) );

        }

    }


    /**
     * Returns the list of the lily pads that need to be reached to complete the
     * level
     * 
     * @return destanationList, which stores the destination objects
     */
    public List<Destination> getDestinationList()
    {
        return destinationList;
    }


    /**
     * Finds the lily pads that have yet to be reached by the frog in that level
     * 
     * @return the Destination lily pad that has not been reached
     */
    public List<Destination> getRemaining()
    {
        List<Destination> l = new LinkedList<Destination>();
        for ( Destination d : destinationList )
        {
            if ( !d.destinationReached )
            {
                l.add( d );
            }
        }

        return l;
    }


    /**
     * Updates the check booleans to ensure that the user is within the time
     * limit
     * 
     * @param time
     *            system's time tracker
     */
    public void update( long time )
    {
        check1 += time;
        check2 += time;

    }
}
