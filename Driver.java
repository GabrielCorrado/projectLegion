import java.util.Scanner;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//this is based on Tim's code
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number for the size of the board: ");
		int input = sc.nextInt();
		//as Tim said, we should have a try/catch here, but the long term goal is really to not need the scanner
		
		sc.close();
		
		Board board = new Board(900, 900, input);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(board);
		frame.pack();
		frame.setVisible(true);
	}
	
	

}
