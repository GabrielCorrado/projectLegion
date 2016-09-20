//Tim Dobeck's slightly modified code to include random state for board

package v5;

import java.util.Scanner;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
			//this is based on Tim's code
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter the number for the size of the board: ");
			int input = sc.nextInt();
			int input2 = input;
			System.out.println("Please indicate if you wish to start with a checkerboard by typing \"1\" or start randomly by typing \"0\": ");
			int typeBoard = sc.nextInt();
	
			//as Tim noted, we should have a try/catch here, but the long term goal is not to need the scanner actually

			sc.close();
			
			Board board = new Board(800, 800, input, typeBoard);
			JFrame frame = new JFrame();
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(board);
			frame.pack();
			frame.setVisible(true);
			
			Board board2 = new Board(800, 800, input2, 2);
			JFrame frame2 = new JFrame();
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.add(board2);
			frame2.pack();
			frame2.setVisible(true);
			
		

	}


	

}
