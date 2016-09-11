import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.MouseInputListener;


public class Board extends JPanel implements MouseInputListener {
	public static int GridButtonWidth = 100;
	public static int GridButtonHeight = 50;
	private static int MaxWidth = 1200;
	private static int MaxHeight = 1100;
	public static int ExtraWallSpace = 10;
	private int input = grabInput();
	private Cell board[][];
	public Rectangle2D.Double GridButton1 = new Rectangle2D.Double((MaxWidth-100)-ExtraWallSpace, ExtraWallSpace, GridButtonWidth, GridButtonHeight);
	public Rectangle2D.Double GridButton2 = new Rectangle2D.Double((MaxWidth-100)-ExtraWallSpace, GridButtonHeight+10+ExtraWallSpace, GridButtonWidth, GridButtonHeight);
	public Rectangle2D.Double GridButton3 = new Rectangle2D.Double((MaxWidth-100)-ExtraWallSpace, (GridButtonHeight*2)+20+ExtraWallSpace, GridButtonWidth, GridButtonHeight);

	
	public Board(int width, int height)
	{
		setPreferredSize(new Dimension(width, height));
		
		board = new Cell[input][input];
	}
	
	static int grabInput()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a number. This number is the size of the board.");
		int input = sc.nextInt();

		// Return the response
		return input;
	}

	public void paint(Graphics g)
	{

		int row;
		int col;
		int x;
		int y;
		int boardSize = 800;
		
		for (int row2 = 0; row2 < board.length; row2++) {
			for (int col2 = 0; col2 < board[row2].length; col2++) {
				board[row2][col2] = new Cell(row2, col2, boardSize/board.length, ((row2%2 == col2%2)? Color.WHITE : Color.BLACK));
			}
		}

		for ( row = 0;  row < board.length;  row++ )
		{
			for ( col = 0;  col < board[row].length;  col++)
			{
				int cellSize = boardSize/board.length;
				if ( (row % 2) == (col % 2) )
				{
					g.setColor(Color.WHITE);
				}
				else
				{
					g.setColor(Color.BLACK);
				}
				g.fillRect(cellSize*row+ExtraWallSpace, cellSize*col+ExtraWallSpace, cellSize, cellSize);
			}
		}

		//Grid #1
		g.setColor(Color.blue);
		g.fillRect((MaxWidth-100)-ExtraWallSpace, 0+ExtraWallSpace, GridButtonWidth, GridButtonHeight);
		//g.fill(GridButton1);
		g.setColor(Color.black);
		g.drawString("Grid #1", (MaxWidth-GridButtonWidth/2)-ExtraWallSpace, (GridButtonHeight/2)+ExtraWallSpace);

		//Grid #2
		g.setColor(Color.red);
		g.fillRect((MaxWidth-100)-ExtraWallSpace, GridButtonHeight+10+ExtraWallSpace, GridButtonWidth, GridButtonHeight);
		//g.fill(GridButton2);
		g.setColor(Color.black);
		g.drawString("Grid #2", (MaxWidth-GridButtonWidth/2)-ExtraWallSpace, (GridButtonHeight*2)+ExtraWallSpace);

		//Grid #3
		g.setColor(Color.green);
		g.fillRect((MaxWidth-100)-ExtraWallSpace, (GridButtonHeight*2)+20+ExtraWallSpace, GridButtonWidth, GridButtonHeight);
		//g.fill(GridButton3);
		g.setColor(Color.black);
		g.drawString("Grid #3", (MaxWidth-GridButtonWidth/2)-ExtraWallSpace, (GridButtonHeight*3)+ExtraWallSpace);

	}

	public static void main(String args[]) {

		Board check = new Board(MaxWidth, MaxHeight);
		
		JFrame frame = new JFrame();   // the program itself
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(check);  
		frame.pack(); //size it based on panel size
		frame.setVisible(true); 

	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col].containz(arg0.getPoint())) {
					board[row][col].flipColor();
					System.out.println("I flipped the color");
				}
			}
		}
		repaint();
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//this will select all the cells in the rectangle of space the user dragged in
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
