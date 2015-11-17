package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/***
 * This class establishes the code to create a Tetris Panel Constructor to 
 * provide the movement and rotation methods for a single tetris piece
 */

public class TetrisPanel extends javax.swing.JPanel {
	
	private static final long serialVersionUID = 1L;
	private MoveableShape piece;
	private final TetrisBoard board;
	private int delay = 1000;
	private Timer t;
	private Graphics g;
	private boolean gameIsRunning;
	private boolean gameIsOver;

	/**
	 * Construct the Tetris Panel
	 */
	
	public TetrisPanel() {
		super();
		this.setBackground(java.awt.Color.black);
		this.setPreferredSize(new java.awt.Dimension(200, 400));
//		this.setSize(new java.awt.Dimension(1000, 2000));
		this.setFocusable(true);
		
        /*
         * These key listeners allow the user to move the piece as well as pause the game pending 
         * certain requirements defined in the if statements to prevent the pieces from going
         * out of bounds.
         */
		
		this.addKeyListener(new KeyListener()	{
		           public void keyPressed(KeyEvent e) {

		            int id = e.getKeyCode();
		            
		            //Rules for pieces moving down
		            if (id == KeyEvent.VK_DOWN
		            	&& (piece.getYC1()+20 <= 380) && (piece.getYC2()+20 <= 380)
		            	&& (piece.getYC3()+20 <= 380) && (piece.getYC4()+20 <= 380) 
		            	&& gameIsRunning) {
		            	gameOver();
	            		stopPiece(g);
		            	piece.translate(0, 20);
		            }
		            
		            //Rules for pieces moving to the left
		            if (id == KeyEvent.VK_LEFT 
		            	&& (piece.getXC1() > 0) && (piece.getXC2() > 0) 
		            	&& (piece.getXC3() > 0) && (piece.getXC4() > 0)
		            	&& (board.getGrid()[piece.getXC1()-20][piece.getYC1()] == null) 
		            	&& (board.getGrid()[piece.getXC2()-20][piece.getYC2()] == null)
		            	&& (board.getGrid()[piece.getXC3()-20][piece.getYC3()] == null)
		            	&& (board.getGrid()[piece.getXC4()-20][piece.getYC4()] == null)
		            	&& gameIsRunning) {
		            	piece.translate(-20, 0);
		            }
		            
		            //Rules for pieces moving to the right
		            if (id == KeyEvent.VK_RIGHT 
		            	&& (piece.getXC1() < 200) && (piece.getXC2() < 200) 
			            && (piece.getXC3() < 200) && (piece.getXC4() < 200)
			            && (board.getGrid()[piece.getXC1()+20][piece.getYC1()] == null) 
			           	&& (board.getGrid()[piece.getXC2()+20][piece.getYC2()] == null)
			           	&& (board.getGrid()[piece.getXC3()+20][piece.getYC3()] == null)
			           	&& (board.getGrid()[piece.getXC4()+20][piece.getYC4()] == null)
			           	&& gameIsRunning) {
		            	piece.translate(20, 0);           
		            }
		            
		            //Rules for pieces to rotate
		            if (id == KeyEvent.VK_SPACE		
		            	&& (piece.getXC1() >= 0) && (piece.getXC2() >= 0) 
			            && (piece.getXC3() >= 0) && (piece.getXC4() >= 0)
			            && (board.getGrid()[piece.getXC1()-20][piece.getYC1()] == null) 
			            && (board.getGrid()[piece.getXC2()-20][piece.getYC2()] == null)
			            && (board.getGrid()[piece.getXC3()-20][piece.getYC3()] == null)
			            && (board.getGrid()[piece.getXC4()-20][piece.getYC4()] == null)
			            && (piece.getXC1() <= 200) && (piece.getXC2() <= 200) 
			            && (piece.getXC3() <= 200) && (piece.getXC4() <= 200)
			            && (board.getGrid()[piece.getXC1()+20][piece.getYC1()] == null) 
			           	&& (board.getGrid()[piece.getXC2()+20][piece.getYC2()] == null)
			           	&& (board.getGrid()[piece.getXC3()+20][piece.getYC3()] == null)
			           	&& (board.getGrid()[piece.getXC4()+20][piece.getYC4()] == null)
		            	&& (piece.getYC1() < 400) && (piece.getYC2() < 400)
		            	&& (piece.getYC3() < 400) && (piece.getYC4() < 400)
		            	&& (board.getGrid()[piece.getXC1()][piece.getYC1()+20] == null) 
			            && (board.getGrid()[piece.getXC2()][piece.getYC2()+20] == null)
			            && (board.getGrid()[piece.getXC3()][piece.getYC3()+20] == null)
			            && (board.getGrid()[piece.getXC4()][piece.getYC4()+20] == null)
		            	&& gameIsRunning) {
		            	piece.rotate();
		            }
		            
		            if (id == KeyEvent.VK_P)
		            	if (gameIsRunning){
		            		gameIsRunning = false;
		            		t.stop();
		            	}
		            	else{
		            		gameIsRunning = true;
		            		t.restart();
		            	}
		            else if (id == KeyEvent.VK_SPACE && gameIsOver) {
		            	gameIsRunning = true;
		            	gameIsOver = false;
		            	t.restart();
		            	gameRestart();
		            }
		            repaint();
		       }

				public void keyReleased(KeyEvent arg0) {
					
				}

				public void keyTyped(KeyEvent arg0) {
					
				}
		         });
		
		/*
		 * The timer keeps the pieces translating, it also checks if the game is over, whether or not a piece
		 * should stop or if there is a full row.
		 */
		
		t = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				gameOver();
				piece.translate(0, 20);
				stopPiece(g);
				board.checkFullRow();
				repaint();
			}
		});
		
		piece = PieceFactory.getRandomPiece(); //Returns a random piece
		board = new TetrisBoard();
		gameIsRunning = true;
		gameIsOver = false;
		
		t.start(); //Starts the timer initially
		
		
	}
	
	/***
	 * The stopPiece method checks to see if the piece should stop and makes sure the the stop will be legal
	 * according to the rules of Tetris.
	 */
	
	public void stopPiece(Graphics g) {
		if (   (piece.getYC1()+20 == 400) || (piece.getYC2()+20 == 400) 
			|| (piece.getYC3()+20 == 400) || (piece.getYC4()+20 == 400)) {
			System.out.println("Stopping at location " + piece.getXC1() + ", " + piece.getYC1());
			delay = 0;
			Graphics2D brush = (Graphics2D) g;
			board.attach(piece, brush);
			repaint();
			System.out.println("Piece attached");
			piece = PieceFactory.getRandomPiece();
			delay = 1000;
			System.out.println("Piece stopped");
		}
		
		else {
			if  ( (board.getGrid()[piece.getXC1()][piece.getYC1()+20] != null) || (board.getGrid()[piece.getXC2()][piece.getYC2()+20] != null)
				||(board.getGrid()[piece.getXC3()][piece.getYC3()+20] != null) || (board.getGrid()[piece.getXC4()][piece.getYC4()+20] != null)) {
				System.out.println("Stopping at location " + piece.getXC1() + ", " + piece.getYC1());
				delay = 0;
				Graphics2D brush = (Graphics2D) g;
				board.attach(piece, brush);
				repaint();
				System.out.println("Piece attached");
				piece = PieceFactory.getRandomPiece();
				delay = 1000;
				System.out.println("Piece stopped");
				}
		}
	}
	
	/*
	 * The restart method erases the board for a new game
	 */
	
	public void gameRestart() {
		for (int i = 0; i < board.getWIDTH(); i++) {
			for (int j = 0; j < board.getHEIGHT(); j++) {
				board.setGrid(i, j, null);
			}
		}
	}
	
	/*
	 * The gameOver method stops the timer and changes two booleans to there alternates to stop the game.
	 */
	
	public void gameOver() {
		for (int i = 0; i < board.getWIDTH(); i++){
			if (board.getGrid()[i][0] != null){
				t.stop();
				gameIsRunning = false;
				gameIsOver = true;
				System.out.println("Game Over");
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		brush.setColor(piece.getColor());
		piece.draw(brush);
		board.draw(brush);
		}
}
