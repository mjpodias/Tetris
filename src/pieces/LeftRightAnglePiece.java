package pieces;

import java.awt.Color;
import java.awt.Graphics2D;
import tetris.MoveableShape;

/**
 * Class LeftRightAnglePiece represents the classic Tetris piece shaped like a backwards "L".
 */

// class RightAnglePiece
public class LeftRightAnglePiece implements MoveableShape {
    // instance variables (protected for the Piece subclasses to inherit)
	   
    private int xPos = 0; //represent the position of the center piece on the board
    private int yPos = 0;
    private int size;
	private int xc1, xc2, xc3, xc4, yc1, yc2, yc3, yc4;
	private Color color = java.awt.Color.CYAN;

    private int shapeX[] = new int[4];
    private int shapeY[] = new int[4];
	
	// default constructor
	public LeftRightAnglePiece(int xPos, int yPos, int size) {
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.size = size;
        
		shapeX[0] = 0;
		shapeY[0] = 0;
		
		shapeX[1] = 0;
		shapeY[1] = 1;

		shapeX[2] = 0;
		shapeY[2] = 2;
		
		shapeX[3] = -1;
		shapeY[3] = 0;

	}
	
	public void translate(int dx, int dy) {
		xPos += dx;
		yPos += dy;
		
	}
	
	public void draw(Graphics2D g2) {
		for (int i = 0; i < shapeX.length; i++) {
		g2.fillRect(xPos + shapeX[i] * size, yPos - (shapeY[i] * size), size, size);
		//System.out.println("Drawing rectangle at position " + shapeX[i] + ", " + shapeY[i]);
		}
		xc1 = xPos + shapeX[0] * size;
		xc2 = xPos + shapeX[1] * size;
		xc3 = xPos + shapeX[2] * size;
		xc4 = xPos + shapeX[3] * size;
		yc1 = yPos - (shapeY[0] * size);
		yc2 = yPos - (shapeY[1] * size);
		yc3 = yPos - (shapeY[2] * size);
		yc4 = yPos - (shapeY[3] * size);
		//System.out.println("Drawing piece");
		/*System.out.println("Coordinates for square 1 at positions: " + xc1 + ", " + yc1);
		System.out.println("Coordinates for square 2 at positions: " + xc2 + ", " + yc2);
		System.out.println("Coordinates for square 3 at positions: " + xc3 + ", " + yc3);
		System.out.println("Coordinates for square 4 at positions: " + xc4 + ", " + yc4);*/
		
	}
	
	/**
	 * This method rotates piece 90 degrees each time the method is called
	 */
	
	public void rotate() {
		int z = 0; // temporary placeholder
		for (int i = 0; i < shapeX.length; i++) {
			z = shapeX[i];
			shapeX[i] = shapeY[i];
			shapeY[i] = z * -1;
		}
	}
	
	public int getXC1() {
		return xc1;
	}
	
	public int getXC2() {
		return xc2;
	}
	
	public int getXC3() {
		return xc3;
	}
	
	public int getXC4() {
		return xc4;
	}
	
	public int getYC1() {
		return yc1;
	}
	
	public int getYC2() {
		return yc2;
	}
	
	public int getYC3() {
		return yc3;
	}
	
	public int getYC4() {
		return yc4;
	}

	public Color getColor() {
		return color;
	}
 	
}
