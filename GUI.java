import java.awt.*;
import java.awt.geom.*;
import java.util.List;

import jig.engine.*;


/**
 * This class creates the GUI that the user sees and ultimately plays the game
 * in.
 *
 * @author Vaishnav Balaji
 * @version May 21, 2016
 * @author Period: 5
 * @author Assignment: Frogger Final Project
 *
 * @author Sources: None
 */
public class GUI implements ViewableLayer
{
    /**
     * List of lives images
     */
    List<ImageResource> lifeCount = ResourceFactory.getFactory().getFrames( World.GRAPHICS_PICS + "#lives" );

    /**
     * List of "Game Over" images
     */
    List<ImageResource> gameOver = ResourceFactory.getFactory().getFrames( World.GRAPHICS_PICS + "#gameover" );

    /**
     * List of "Level Completed" images
     */
    List<ImageResource> endLevel = ResourceFactory.getFactory().getFrames( World.GRAPHICS_PICS + "#endLevel" );

    /**
     * List of menu screen that opens when the user first begins the game
     */
    List<ImageResource> startUp = ResourceFactory.getFactory().getFrames( World.GRAPHICS_PICS + "#startUp" );

    /**
     * Font generator (Monospaced gives the game a "classic" look)
     */
    FontResource font = ResourceFactory.getFactory().getFontResource( new Font( "Monospaced", Font.BOLD, 14 ),
        Color.BLACK,
        null );

    /**
     * Represents the color of the font (Black is default)
     */
    FontResource fontBlack = ResourceFactory.getFactory().getFontResource( new Font( "Sans Serif", Font.BOLD, 14 ),
        Color.black,
        null );

    /**
     * Game that the GUI is currently representing
     */
    World game;


    /**
     * Constructor
     * 
     * @param w
     *            the world that the game should display
     */
    public GUI( final World w )
    {
        game = w;

    }


    /**
     * Method overrides render method from the Sprite super class and creates
     * all the displays in the GUI
     * 
     * @param r
     *            JIG object that takes care of the rendering of the world
     */
    public void render( RenderingContext r )
    {
        font.render( "Score: " + game.GameScore, r, AffineTransform.getTranslateInstance( 205, 7 ) );
        font.render( "Time Left: " + game.levelTimer, r, AffineTransform.getTranslateInstance( 310, 7 ) );

        if ( game.GameLives > 0 )
        {
            int dx = 0;

            int lives = game.GameLives;
            if ( lives > 10 )
            {
                lives = 10;
            }
            else
            {
                lives = game.GameLives;
            }

            for ( int i = 0; i < lives; i++ )
            {
                lifeCount.get( 0 ).render( r, AffineTransform.getTranslateInstance( dx + 8, 8 ) );
                dx = 16 * ( i + 1 );
            }
        }

        font.render( "Level: " + game.GameLevel, r, AffineTransform.getTranslateInstance( 110, 7 ) );

        if ( game.GameState == World.GAME_INTRO )
        {
            startUp.get( 0 ).render( r,
                AffineTransform.getTranslateInstance( ( World.WORLD_WIDTH - startUp.get( 0 ).getWidth() ) / 2,
                    150 ) );
            return;
        }

        if ( game.GameState == World.GAME_OVER )
        {
            gameOver.get( 0 ).render( r,
                AffineTransform.getTranslateInstance( ( World.WORLD_WIDTH - gameOver.get( 0 ).getWidth() ) / 2, 150 ) );
            return;
        }

        if ( game.GameState == World.GAME_FINISH_LEVEL )
        {
           endLevel.get( 0 ).render( r,
                AffineTransform.getTranslateInstance( ( World.WORLD_WIDTH - endLevel.get( 0 ).getWidth() ) / 2,
                    150 ) );
        }
    }


    /**
     * Method only present to satisfy the abstract method requirement
     * 
     * @param time
     *            System's time tracker
     */
    public void update( long time )
    {

    }


    /**
     * Boolean method that keeps the game running
     * 
     * @return true, the game is always running when it is started
     */
    public boolean isActive()
    {
        return true;
    }


    /**
     * Method only present to satisfy the abstract method requirement
     * 
     * @param b
     *            determines whether game is to be activated
     */
    public void setActivation( boolean b )
    {

    }
}
