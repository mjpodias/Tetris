package tetris;

import pieces.BoxPiece;
import pieces.LeftRightAnglePiece;
import pieces.LeftStepPiece;
import pieces.RightAnglePiece;
import pieces.RightStepPiece;
import pieces.StraightPiece;
import pieces.UpsideDownTPiece;

/**
 * A class for generating randomly generated pieces.
*/
public class PieceFactory {

public static MoveableShape getRandomPiece(){
    MoveableShape p = null;
    int num = (int) (Math.random()*7 + 1); //get a random number between 1 and 7
    switch (num) {
       case 1:
          p = new BoxPiece(120, 40, 20);
          System.out.println("Box is being printed");
          break;
       case 2: 
         p = new LeftRightAnglePiece(120, 40, 20);
         System.out.println("Left right angle is being printed");
         break;
      case 3:
         p = new LeftStepPiece(120, 40, 20);
         System.out.println("Left step piece is being printed");
        break;
      case 4:
          p = new RightAnglePiece(120, 40, 20);
          System.out.println("Right angle piece is being printed");
         break;
      case 5:
          p = new RightStepPiece(120, 40, 20);
          System.out.println("Right step piece is being printed");
         break;
      case 6:
          p = new StraightPiece(120, 40, 20);
          System.out.println("Straight piece is being printed");
         break;
      case 7:
          p = new UpsideDownTPiece(120, 30, 20);
          System.out.println("Upsidedown T piece is being printed");
         break;
       }

     return p;
}

}
