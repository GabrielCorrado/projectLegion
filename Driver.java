package v4;

import java.util.Scanner;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//this is based on Tim's code
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number for the size of the board: ");
		int input = sc.nextInt();
		//as Tim noted, we should have a try/catch here, but the long term goal is not to need the scanner actually
		
		sc.close();
		
		Board board = new Board(400, 400, input);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(board);
		frame.pack();
		frame.setVisible(true);
	}
	
	

}
