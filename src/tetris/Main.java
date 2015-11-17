package tetris;

import javax.swing.*;

/**
 * This class tests the Tetris Panel in a JFrame
 */


@SuppressWarnings("serial")
public class Main extends JFrame {
	  /**
	    Construct an TetrisTester object.
	    @param title is the title of the JFrame
	  */
	   public Main(String title){
		   super(title);
		   
		   /**Create a ShapePanel object and add it to the frame.
		    */
		   this.add(new TetrisPanel());

		    /** Standard code for all graphical apps. 
		     */
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.pack();
		    this.setVisible(true);
		    this.setResizable(false);
	              

	   }
	   
	   public static void main(String[] args)
	    {
	        new Main("Tetris");
	    }
	 
	 
}