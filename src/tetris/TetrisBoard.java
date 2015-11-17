package tetris;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * This class is a Tetris Board that knows the locations of (colors or rectangles)
 */

public class TetrisBoard  {
	
	private Color grid[][]; // 2D Color Array to represent pieces
	private final int WIDTH = 200;
	private final int HEIGHT = 420;
	private Color color;
	
	/**
	 * This constructor initializes a new Tetris Board of a 2D color array
	 */
	public TetrisBoard() {
	    grid = new Color[WIDTH][HEIGHT];
	}
	
	/**
	 * The draw method draws the rectangles of a piece based on the coordinates of a given piece
	 * @param g2
	 */
	
	public void draw(Graphics2D g2) {
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
            	if (grid[i][j] != null) {
            		g2.setColor(grid[i][j]);
            		g2.fillRect(i, j, 20, 20);
            	}
            }
        }
	}
	
	/**
	 * This method returns the grid of 2D colors so it's locations can be accessed
	 * @return
	 */
	
	public Color[][] getGrid(){
		return grid;
	}
	
	/**
	 * This method sets the grid at a certain location to a color (or null)
	 * @param x
	 * @param y
	 * @param color
	 */
	
	public void setGrid(int x, int y, Color color){
		grid[x][y] = color;
	}
	
	/**
	 * This method returns the width of the array
	 * @return
	 */
	
	public int getWIDTH() {
		return WIDTH;
	}
	
	/**
	 * This method returns the height of the array
	 * @return
	 */
	
	public int getHEIGHT() {
		return HEIGHT;
	}
	
	/**
	 * This method attaches a current piece to the 2D array
	 * @param piece
	 * @param g2
	 */
	
	public void attach(MoveableShape piece, Graphics2D g2) { //should accept the piece location and color as a parameter
		System.out.println("Attaching piece");
		color = piece.getColor();
		grid[piece.getXC1()][piece.getYC1()] = color;
		grid[piece.getXC2()][piece.getYC2()] = color;
		grid[piece.getXC3()][piece.getYC3()] = color;
		grid[piece.getXC4()][piece.getYC4()] = color;
	}
	
	/**
	 * This method checks if a full row's x location's have been filled and deletes that row.
	 * It then drops all of the pieces above that row to fit the board.
	 */
	
	public void checkFullRow() {
		int i = 0; 
		for (int j = 0; j < HEIGHT; j = j + 20) {
			for (i = 0; i < WIDTH; i = i + 20) {
				
				if (grid[0][j] != null && grid[20][j] != null && grid[40][j] != null && grid[60][j] != null
				 && grid[80][j] != null && grid[100][j] != null && grid[120][j] != null
				 && grid[140][j] != null && grid[160][j] != null && grid[180][j] != null) {
						System.out.println("ROW " + j + " IS FULL");
						deleteRow(j);
						dropPieces(j);
				}
				
				else {
					System.out.println("ROW " + j + " IS NOT FULL");
				}
			}
		}
	}
	
	/**
	 * This method deletes a ceratin row, y.
	 * @param y
	 */
	
	private void deleteRow(int y) {
		for (int x = 0; x < WIDTH; x++){
			grid[x][y] = null;
		}
	}
	
	/**
	 * This method drops all of the pieces above a delted line down a spot
	 * @param deletedLine
	 */
	
	public void dropPieces(int deletedLine) {
		for (int j = deletedLine; j > 20; j--) {
			for (int i = 0; i < WIDTH; i++) {
				grid[i][j] = grid[i][j - 20];
			}
		}
	}
}
