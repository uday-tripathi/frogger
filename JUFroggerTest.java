import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import jig.engine.util.Vector2D;


/**
 * Extensive JUnit Testing of all testable (non-GUI involved) classes in our
 * program. All manual testing of GUI-related classes can be found in the
 * attached spreadsheet "GUI Classes Test.xlsx"
 *
 * @author Vaishnav Balaji and Uday Tripathi
 * @version May 23, 2016
 * @author Period: 5th
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class JUFroggerTest
{

    // Weather - Tests written by Vaishnav Balaji
    /**
     * Tests the one parameter constructor of a Weather object.
     */
    @Test
    public final void testWeather()
    {
        Weather test = new Weather();
        assertEquals( 0, test.getTimeMiliSec() );
        assertEquals( false, test.getEffect() );
    }


    /**
     * Tests the "blow" method in the Weather class.
     */
    @Test
    public final void testBlow()
    {
        Weather test = new Weather();
        Frogger f = new Frogger( new World() );
        f.setIsAlive( false );
        assertEquals( false, test.getEffect() );

    }


    /**
     * Tests the "begin" method in the Weather class.
     */
    @Test
    public final void testBegin()
    {
        Weather test = new Weather();
        test.begin( 1 );
        assertEquals( 1, test.getCount() );
    }


    /**
     * Tests the generateWeatherBuilds method in the Weather class.
     */
    @Test
    public final void testGenerateWeatherBuilds()
    {
        Weather w = new Weather();
        assertNull( w.generateWeatherBuilds( 1 ) );
    }


    // WeatherBuild - tests written by Uday Tripathi

    /**
     * Tests the three parameter constructor for a WeatherBuild object.
     */
    @Test
    public final void testWeatherBuildStringVector2DVector2DConstructor()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        WeatherBuild w = new WeatherBuild( World.GRAPHICS_PICS, x, y );
        assertEquals( x, w.getPosition() );
        assertEquals( y, w.getVelocity() );
    }


    /**
     * Tests the four parameter constructor for a WeatherBuild object.
     */
    @Test
    public final void testWeatherBuildStringVector2DVector2DInt()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        WeatherBuild w = new WeatherBuild( World.GRAPHICS_PICS, x, y, 10 );
        assertEquals( x, w.getPosition() );
        assertEquals( y, w.getVelocity() );
    }


    // Pad - Test written by Uday Tripathi

    /**
     * Tests the constructor for a Pad object.
     */
    @Test
    public final void testPadConstructor()
    {
        Vector2D temp = new Vector2D( 1, 1 );
        Vector2D temp2 = new Vector2D( 2, 2 );
        Pad p = new Pad( temp, temp2 );
        assertEquals( temp, p.getPosition() );
        assertEquals( temp2, p.getVelocity() );
    }


    // Secondary Pad - Test written by Uday Tripathi

    /**
     * Tests the constructor for a SecondaryPad object.
     */
    @Test
    public final void testSecondaryPadConstructor()
    {
        Vector2D temp = new Vector2D( 1, 1 );
        Vector2D temp2 = new Vector2D( 2, 2 );
        SecondaryPad p = new SecondaryPad( temp, temp2 );
        assertEquals( temp, p.getPosition() );
        assertEquals( temp2, p.getVelocity() );
    }


    // Obstacle One - Obstacle One and Two tests written by Vaishnav Balaji
    /**
     * Tests the constructor for ObstacleOne.
     */
    @Test
    public final void testObstacleOneConstructor()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleOne temp = new ObstacleOne( x, y, 1 );
        assertEquals( x, temp.getPosition() );
        assertEquals( y, temp.getVelocity() );
    }


    // Obstacle Two
    /**
     * Tests the constructor for ObstacleTwo.
     */
    @Test
    public final void testObstacleTwo()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleTwo temp = new ObstacleTwo( x, y );
        assertEquals( x, temp.getPosition() );
        assertEquals( y, temp.getVelocity() );
    }

    // Obstacle Three - Obstacles three through five tests written by Uday Tripathi


    /**
     * Tests the constructor for ObstacleThree.
     */
    @Test
    public final void testObstacleThree()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleThree temp = new ObstacleThree( x, y );
        assertEquals( x, temp.getPosition() );
        assertEquals( y, temp.getVelocity() );

    }


    /**
     * Tests the move method for an ObstacleThree object.
     */
    @Test
    public final void testObstacleThreeMove()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleThree temp = new ObstacleThree( x, y );
        temp.move( 301 );
        assertEquals( 0, temp.getAnimationTime() );
    }


    // Obstacle Four

    /**
     * Tests the two parameter constructor for an ObstacleFour object.
     */
    @Test
    public final void testObstacleFourVector2DVector2D()
    {

        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleFour temp = new ObstacleFour( x, y );
        assertEquals( x, temp.getPosition() );
        assertEquals( y, temp.getVelocity() );
    }


    /**
     * Tests the three parameter constructor for an ObstacleFour object.
     */
    @Test
    public final void testObstacleFourVector2DVector2DInt()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleFour temp = new ObstacleFour( x, y, 1 );
        assertEquals( x, temp.getPosition() );
        assertEquals( y, temp.getVelocity() );
        assertEquals( true, temp.getIsUnderWater() );
        ObstacleFour bob = new ObstacleFour( x, y, 0 );
        assertEquals( false, bob.getIsUnderWater() );
    }


    /**
     * Tests the initialize method for an ObstacleFour object.
     */
    @Test
    public final void testInitialize()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleFour temp = new ObstacleFour( x, y );
        double getx = x.getX();
        double gety = x.getY();
        temp.initialize( x, y );
        assertEquals( getx + 32, temp.getPosSphereTwo().getX(), 0.0 );
        assertEquals( gety, temp.getPosSphereTwo().getY(), 0.0 );

    }


    /**
     * Tests the timeCheck method for an ObstacleFour object.
     */
    @Test
    public final void testObstacleFourTimeCheck()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleFour temp = new ObstacleFour( x, y );
        temp.timeCheck();
        assertEquals( 6, temp.getCheck() );
    }


    /**
     * Tests the animation method for an ObstacleFour object.
     */
    @Test
    public final void testAnimation()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleFour temp = new ObstacleFour( x, y );
        boolean hold = temp.getIsUnderWater();
        temp.animation();
        assertEquals( false, hold );

    }


    /**
     * Tests the beginAnimation method for an ObstacleFour object.
     */
    @Test
    public final void testBeginAnimation()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleFour temp = new ObstacleFour( x, y );
        temp.beginAnimation();
        assertEquals( false, temp.getIsUnderWater() );
        assertEquals( 0, temp.getStartTime() );
        assertEquals( 0, temp.getAFrame() );
        assertEquals( 0, temp.getTimer() );
    }


    // Obstacle Five

    /**
     * Tests the constructor for an ObstacleFive object.
     */
    @Test
    public final void testObstacleFive()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        ObstacleFive temp = new ObstacleFive( x, y );
        assertEquals( x, temp.getPosition() );
        assertEquals( y, temp.getVelocity() );
    }


    // DynamicObjectGenerator - tests written by Vaishnav Balaji
    /**
     * Tests the constructor for a DynamicObjectGenerator.
     */
    @Test
    public final void testDynamicObjectGenerator()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        DynamicObjectGenerator temp = new DynamicObjectGenerator( x, y );
        int zero = temp.getDynamicObjectArr()[0];
        int one = temp.getDynamicObjectArr()[1];
        int two = temp.getDynamicObjectArr()[2];
        int three = temp.getDynamicObjectArr()[3];
        assertEquals( 64, zero );
        assertEquals( 80, one );
        assertEquals( 64, two );
        assertEquals( 80, three );

    }


    /**
     * Tests the buildBasicObject method for a DynamicObjectGenerator.
     */
    @Test
    public final void testBuildBasicObject()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        DynamicObjectGenerator temp = new DynamicObjectGenerator( x, y );
        assertNull( temp.buildBasicObject( 1, 1 ) );
    }


    /**
     * Tests the buildSecondaryPadWithAnimal method for a
     * DynamicObjectGenerator.
     */
    @Test
    public final void testBuildSecondaryPadWithAnimal()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        DynamicObjectGenerator temp = new DynamicObjectGenerator( x, y );
        DynamicObject compare = temp.buildBasicObject( 2, 80 );
        DynamicObject compare2 = temp.buildSecondaryPadWithAnimal( -1 );
        assertEquals( compare, compare2 );
    }


    /**
     * Tests the buildPadWithAnimal method for a DynamicObjectGenerator.
     */
    @Test
    public final void testBuildPadWithAnimal()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        DynamicObjectGenerator temp = new DynamicObjectGenerator( x, y );
        DynamicObject compare = temp.buildBasicObject( 3, 80 );
        DynamicObject compare2 = temp.buildSecondaryPadWithAnimal( -1 );
        assertEquals( compare, compare2 );
    }


    /**
     * Tests the buildObstacle method for a DynamicObjectGenerator.
     */
    @Test
    public final void testBuildObstacle()
    {
        Vector2D x = new Vector2D( 1, 1 );
        Vector2D y = new Vector2D( 2, 2 );
        DynamicObjectGenerator temp = new DynamicObjectGenerator( x, y );
        DynamicObject compare = temp.buildBasicObject( 0, 80 );
        DynamicObject compare2 = temp.buildSecondaryPadWithAnimal( -1 );
        assertEquals( compare, compare2 );
        DynamicObject compare3 = temp.buildBasicObject( 1, 80 );
        assertEquals( compare3, compare2 );

    }


    // Dynamic Object - tests written by Vaishnav Balaji

    /**
     * Tests the constructor for a DynamicObject.
     */
    @Test
    public final void testDynamicObjectConstructor()
    {
        DynamicObject temp = new DynamicObject( World.GRAPHICS_PICS );
        assertNotNull( temp.getColliders() );

    }


    /**
     * Tests the refresh method for a DynamicObject.
     */
    @Test
    public final void testRefresh()
    {
        Vector2D x = new Vector2D( 1, 1 );
        DynamicObject temp = new DynamicObject( World.GRAPHICS_PICS );
        int size = temp.getColliders().size();
        temp.refresh( x );
        assertEquals( size, temp.getI(), 0.0 );

    }


    // Destination - Tests written by Vaishnav Balaji

    /**
     * Tests the int parameter constructor for a Destination object.
     */
    @Test
    public final void testDestinationInt()
    {
        Destination temp = new Destination( 10 );
        Vector2D expected = new Vector2D( 672, 32 );
        assertEquals( expected, temp.getPosition() );
    }


    /**
     * Tests the Vector2D parameter constructor for a Destination object.
     */
    @Test
    public final void testDestinationVector2D()
    {

        Vector2D expected = new Vector2D( 672, 32 );
        Destination temp = new Destination( expected );
        assertEquals( expected, temp.getPosition() );

    }


    /**
     * Tests the reach method for a Destination object.
     */
    @Test
    public final void testReachMethod()
    {
        Destination temp = new Destination( new Vector2D( 672, 32 ) );
        temp.reach();
        assertEquals( true, temp.destinationReached );
    }


    // DestinationInfo - Tests written by Vaishnav Balaji

    /**
     * Tests the constructor for a DestinationInfo object.
     */
    @Test
    public final void testDestinationInfoConstructor()
    {
        DestinationInfo temp = new DestinationInfo();
        assertNotNull( temp.getDestinationList() );
    }


    /**
     * Tests the start method for a DestinationInfo object.
     */
    @Test
    public final void testStartMethod()
    {
        DestinationInfo temp = new DestinationInfo();
        temp.start( 1 );
        assertEquals( 2, temp.getDestinationList().size() );
        temp.start( 2 );
        assertEquals( 4, temp.getDestinationList().size() );
    }


    /**
     * Tests the getRemaining method for a DestinationInfo object.
     */
    @Test
    public final void testGetRemaining()
    {
        DestinationInfo temp = new DestinationInfo();
        List<Destination> l = new LinkedList<Destination>();
        l = temp.getRemaining();
        assertNotNull( l );
    }


    // Colliders - Tests written by Vaishnav Balaji

    /**
     * Tests the one parameter constructor for a Colliders object.
     */
    @Test
    public final void testCollidersVector2D()
    {
        Vector2D temp = new Vector2D( 1, 1 );
        Colliders c = new Colliders( temp );
        Colliders z = new Colliders( temp );
        z.setPosition( temp );
        assertEquals( z.getPosition(), c.getPosition() );
    }


    /**
     * Tests the two parameter constructor for a Colliders object.
     */
    @Test
    public final void testCollidersStringVector2DConstructor()
    {
        Vector2D temp = new Vector2D( 1, 1 );
        Colliders c = new Colliders( World.GRAPHICS_PICS, temp );
        Colliders z = new Colliders( World.GRAPHICS_PICS, temp );
        z.setPosition( temp );
        assertEquals( z.getPosition(), c.getPosition() );
    }


    /**
     * Tests the setPosition method for a Colliders object.
     */
    @Test
    public final void testSetPositionVector2D()
    {
        Vector2D temp = new Vector2D( 1, 1 );
        Colliders c = new Colliders( World.GRAPHICS_PICS, temp );
        double changeX = 16 - c.getRadius();
        double changeY = -c.getRadius() + 16;
        Vector2D temp2 = new Vector2D( 2, 2 );
        c.setPosition( temp2 );
        assertEquals( new Vector2D( temp2.getX() + changeX, temp2.getY() + changeY ), c.getPosition() );
    }
}
