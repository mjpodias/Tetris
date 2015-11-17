package tetris;

import java.awt.*;

/**
 * A shape that can be moved around.
*/

public interface MoveableShape
{
   /**
      Draws the shape.
      @param g2 the graphics context
   */
   void draw(Graphics2D g2);
   /**
      Moves the shape by a given amount.
      @param dx the amount to translate in x-direction
      @param dy the amount to translate in y-direction
   */
   void translate(int dx, int dy);
   
   /**
    * Rotates the piece 90 degrees
    */
   void rotate();
   
   Color getColor();
   
   int getXC1();
   
   int getXC2();
   
   int getXC3();
   
   int getXC4();
   
   int getYC1();
   
   int getYC2();
   
   int getYC3();
   
   int getYC4();
   
   
}
