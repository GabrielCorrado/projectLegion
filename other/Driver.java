package other;
/*		Author: Zak Gray, Nick Corrado and Tim Dobeck
 * 		Description: This is the class to kick start the program to run. It is creating the instance of the Board to be called depending on whatever parameters
 * 					 the user types in. The first input determines the size of the board (x by x) and the second input determines if the board will be a 
 * 					 checkerboard or random. This keeps looping through until there is a valid response. The try/catch block is for catching error in the responses
 * 					 We were torn between using an IllegalArgumentExceptiona and a TypeMismatchException because we need to catch both so that is why we left Exception()
 */

import java.util.Scanner;
import javax.swing.JFrame;

import gui.Board;

public class Driver {

	public static boolean validResponse = false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int input = 20;
		int typeBoard = 0;
		
		System.out.print("Enter the number for the size of the board: ");
		while (!validResponse) //keep looping through until response is valid, then draw it
		{
			try
			{
				if (sc.hasNextInt()) { //this tests whether the following input is actually an int
					input = sc.nextInt();
					if (input < 1 || input > 400) {
						throw new IllegalArgumentException("Enter a number between 1 and 400 inclusive:");
					} else {
						validResponse = true; //you've got a good response!
					}
				} else {
					System.out.println("That's not a number. Enter a number between 1 and 400 inclusive:");
					sc.nextLine(); //this goes to the next line of the scanner, so it doesn't keep reading a bad result like "asdf" forever
				}
			}
			catch (Exception ex) 
			{
				System.out.println(ex.getMessage());
			}
		}
		
		validResponse = false;
		
		System.out.println("Please indicate if you wish to start with a checkerboard by typing \"1\" or start randomly by typing \"0\": ");
		while (!validResponse) {
			try
			{
				if (sc.hasNextInt()) {
					typeBoard = sc.nextInt();
					if (typeBoard != 0 && typeBoard != 1) {
						throw new IllegalArgumentException("Enter either 0 or 1:");
					}
					else {
						validResponse = true;
					}
				} else {
					System.out.println("That's not a number. Enter either 0 or 1:");
					sc.nextLine();
				}
			}
			catch (Exception ex) 
			{
				System.out.println(ex.getMessage());
			}
		}
		sc.close();

		Board board = new Board(800, 800, input, 30, false);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(board);
		frame.pack();
		frame.setVisible(true);
	}
}
