import jig.engine.util.*;


/**
 * Weather generator class which allows for the graphic representation of the
 * Weather (lightning)
 *
 * @author Uday Tripathi
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class WeatherBuild extends DynamicObject
{

    /**
     * The number of chances a user has to die as they run out of time
     */
    private int timeExpire = 1;

    /**
     * The amount of time that a frog has been "alive" in the level
     */
    private int timeAlive = 1;


    /**
     * Constructor
     * 
     * @param graphic
     *            the sprite sheet reference that creates the weather
     * @param xy
     *            the location of the lightning particles
     * @param velocity1
     *            the velocity of the lightning particles
     */
    public WeatherBuild( String graphic, Vector2D xy, Vector2D velocity1 )
    {
        super( graphic );
        position = xy;
        velocity = velocity1;
        setActivation( true );
        timeExpire = 0;
    }


    /**
     * Constructor
     * 
     * @param graphic
     *            the sprite sheet reference that creates the weather
     * @param xy
     *            the location of the lightning particles
     * @param velocity1
     *            the velocity of the lightning particles
     * @param timer
     *            the system's time tracker
     */
    public WeatherBuild( String graphic, Vector2D xy, Vector2D velocity1, int timer )
    {
        super( graphic );
        position = xy;
        velocity = velocity1;
        setActivation( true );
        timeExpire = timer;
    }


    /**
     * Updates the game after the weather effect has occurred and ensures the
     * game resumes normal game play
     * 
     * @param time
     *            the system's time tracker
     */
    public void update( long time )
    {
        super.update( time );

        if ( timeExpire != 0 )
        {
            timeAlive += time;
            if ( timeAlive > timeExpire )
                setActivation( false );
        }
    }
}
