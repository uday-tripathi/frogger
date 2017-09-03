import java.util.*;
import jig.engine.util.*;


/**
 * Creates a weather condition for the frog to struggle with during the game. In
 * this implementation, the weather is a form of lightning that paralyzes the
 * frog momentarily
 *
 * @author Uday Tripathi
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class Weather
{
    /**
     * The period of time that the lightning is on the screen
     */
    final static int PERIOD = 5000;

    /**
     * The duration that the lightning has currently been on the screen
     */
    final static int DURATION = 3000;

    /**
     * Random number generator
     */
    Random r;

    /**
     * The amount of time, in milliseconds, that has passed
     */
    private long timeMiliSec;

    /**
     * The duration of the lightning so far
     */
    private long durationMiliSec;

    /**
     * Whether or not the lightning should strike (activation)
     */
    private boolean effect;

    private int counter;


    /**
     * Constructor
     */
    public Weather()
    {
        timeMiliSec = 0;
        effect = false;
        r = new Random( System.currentTimeMillis() );
    }


    /**
     * "Blows" the lightning across the screen as the particles move across the
     * game
     * 
     * @param f
     *            the frog that is being affected
     * @param levelNumber
     *            the level that the user is currently on
     * @param time
     *            system's time tracker
     */
    public void blow( Frogger f, int levelNumber, long time )
    {
        if ( !f.isAlive )
        {
            effect = false;
            return;
        }

        if ( effect && durationMiliSec < DURATION )
        {
            double vPos = (double)( time * r.nextDouble() * ( 0.01 * levelNumber ) );
            f.move( new Vector2D( vPos, 0 ) );
        }
        else
        {
            effect = false;
        }
    }


    /**
     * Begins the effect of the lightning
     * 
     * @param levelNumber
     *            the level that the user is currently on
     */
    public void begin( int levelNumber )
    {
        if ( !effect && timeMiliSec > PERIOD )
        {

            if ( r.nextInt( 100 ) < levelNumber * 10 )
            {
                durationMiliSec = 1;
                effect = true;
            }

            timeMiliSec = 0;
        }
        counter++;
    }


    /**
     * Getter method for counter for JUnit Testing.
     * 
     * @return counter
     */
    public int getCount()
    {
        return counter;
    }


    /**
     * Generates the lightning as a dynamic object that can then utilize the
     * same implementation that the DynamicObject has already implemented
     * 
     * @param levelNumber
     *            the level the user is currently on
     * @return the lightning as a DynamicObject
     */
    public DynamicObject generateWeatherBuilds( int levelNumber )
    {
        if ( !effect )
        {
            return null;
        }

        if ( r.nextInt( 100 ) > levelNumber * 10 )
        {
            return null;
        }

        int yPos = r.nextInt( 13 * 32 ) + 32;

        Vector2D pos = new Vector2D( 0, yPos );

        Vector2D v = new Vector2D( 0.2 + r.nextDouble(), ( r.nextDouble() - 0.5 ) * 0.1 );
        return new WeatherBuild( World.GRAPHICS_PICS + "#lightningStrike", pos, v );
    }


    /**
     * Updates the game before/after the lightning has struck
     * 
     * @param time
     *            system's time tracker
     */
    public void update( long time )
    {
        timeMiliSec += time;
        durationMiliSec += time;
    }


    /**
     * Getter method for timeMiliSec
     * 
     * @return time in milliseconds
     */
    public long getTimeMiliSec()
    {
        return timeMiliSec;
    }


    /**
     * Getter method for DurationMiliSec
     * 
     * @return duration in milliseconds
     */
    public long getDurationMiliSec()
    {
        return durationMiliSec;

    }


    /**
     * Getter method for effect
     * 
     * @return true if weather effect is present, false otherwise
     */
    public boolean getEffect()
    {
        return effect;
    }

}
