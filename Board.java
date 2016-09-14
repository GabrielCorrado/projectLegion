package v4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel implements MouseInputListener {
	private Cell[][] cells;
	private int size; //these are the numbers of cells in the board, NOT the graphical dimensions of the board
	private static final int EXTRA_BOARD_SPACE = 50;
	
	public Board(int width, int height, int size) {
		//set preferred graphical dimensions of the board
		setPreferredSize(new Dimension(width, height));
		//HOW DID I FORGET THIS EARLIER
		this.size = size;
		
		//set the graphical dimensions of the cells themselves
		//the cells are always square, but the space they take up is constrained by the width and height of the board
		//and by the number of cells.
		int cellSize = (width-2*EXTRA_BOARD_SPACE)/size;
		
		cells = new Cell[size][size];
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				cells[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, (row%2 == col%2 ? Color.BLACK : Color.WHITE));
			}
		}
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		
		Graphics2D g = (Graphics2D)arg0;
		
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				cells[row][col].draw(g);
				
			}
		}
	}
	
	public void step() {
		//initialize temp array of colors
		Color[][] result = new Color[size][size];
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				int tally = 0;
				//check how many neighbors are alive
				for (Cell n : getNeighbors(cells, row, col)) {
					//the array may have nulls in it ;)
					if (n != null) {
						if (n.getColor() == Color.BLACK) {
							tally++;
						}
					}
				}
				//if three of your neighbors are alive, you come alive or stay alive,
				//or if two of your neighbors are alive and you're alive, you stay alive,
				//otherwise you die
				if (tally == 3 || (tally == 2 && cells[row][col].getColor() == Color.BLACK)) {
					//result[row][col] = new Cell(cells[row][col].getX(), cells[row][col].getY(), cells[row][col].getWidth(), Color.BLACK);
					result[row][col] = Color.BLACK;
				}
				else {
					//result[row][col] = new Cell(cells[row][col].getX(), cells[row][col].getY(), cells[row][col].getWidth(), Color.WHITE);
					result[row][col] = Color.WHITE;
				}
			}
		}
		
		//populate the old array with the results of the temp array
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				cells[row][col].setColor(result[row][col]);
			}
		}
	}
	
	public Cell[] getNeighbors(Cell[][] cells, int rowNum, int colNum) {
		//each cell only has 8 neighbors! for now at least.... :(
		Cell[] neighbors = new Cell[8];
		//this is pretty cool
		int rowMax = cells.length-1;
		int colMax = cells[rowMax-1].length-1;
		
		//THIS IS AWFUL AND NEEDS SO MUCH REVISION IT'S NOT EVEN FUNNY
		
		//top left
		if (rowNum == 0 && colNum == 0) {
			neighbors[4] = cells[rowNum][colNum+1];
			neighbors[6] = cells[rowNum+1][colNum];
			neighbors[7] = cells[rowNum+1][colNum+1];
		}
		
		//bottom left
		if (rowNum == rowMax && colNum == 0) {
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[2] = cells[rowNum-1][colNum+1];
			neighbors[4] = cells[rowNum][colNum+1];
		}
		
		//top right
		if (rowNum == 0 && colNum == cells[0].length-1) {
			neighbors[3] = cells[rowNum][colNum-1];
			neighbors[5] = cells[rowNum+1][colNum-1];
			neighbors[6] = cells[rowNum+1][colNum];
		}
		
		//bottom right
		if (rowNum == rowMax && colNum == colMax) {
			neighbors[0] = cells[rowNum-1][colNum-1];
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[3] = cells[rowNum][colNum-1];
		}
		
		//top
		if (rowNum == 0 && colNum > 0 && colNum < colMax) {
			neighbors[3] = cells[rowNum][colNum-1];
			neighbors[4] = cells[rowNum][colNum+1];
			neighbors[5] = cells[rowNum+1][colNum-1];
			neighbors[6] = cells[rowNum+1][colNum];
			neighbors[7] = cells[rowNum+1][colNum+1];
		}
		
		//bottom
		if (rowNum == rowMax && colNum > 0 && colNum < colMax) {
			neighbors[0] = cells[rowNum-1][colNum-1];
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[2] = cells[rowNum-1][colNum+1];
			neighbors[3] = cells[rowNum][colNum-1];
			neighbors[4] = cells[rowNum][colNum+1];
		}
		
		//left
		if (rowNum > 0 && rowNum < rowMax && colNum == 0) {
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[2] = cells[rowNum-1][colNum+1];
			neighbors[4] = cells[rowNum][colNum+1];
			neighbors[6] = cells[rowNum+1][colNum];
			neighbors[7] = cells[rowNum+1][colNum+1];
		}
		
		//right
		if (rowNum > 0 && rowNum < rowMax && colNum == colMax) {
			neighbors[0] = cells[rowNum-1][colNum-1];
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[3] = cells[rowNum][colNum-1];
			neighbors[5] = cells[rowNum+1][colNum-1];
			neighbors[6] = cells[rowNum+1][colNum];
		}
		
		//middle cells obviously get everything
		if (rowNum > 0 && rowNum < rowMax && colNum > 0 && colNum < colMax) {
			neighbors[0] = cells[rowNum-1][colNum-1];
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[2] = cells[rowNum-1][colNum+1];
			neighbors[3] = cells[rowNum][colNum-1];
			neighbors[4] = cells[rowNum][colNum+1];
			neighbors[5] = cells[rowNum+1][colNum-1];
			neighbors[6] = cells[rowNum+1][colNum];
			neighbors[7] = cells[rowNum+1][colNum+1];
		}
		
		return neighbors;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		//i commented this out for now so you can test the step procedure by clicking
		//the behavior is boring atm because checkerboard is a bad starting pattern (almost everything
		//has four neighbors, so everything dies immediately, except the border cells, which all
		//spring alive)
//		for (int row = 0; row < cells.length; row++) {
//			for (int col = 0; col < cells[row].length; col++) {
//				if (cells[row][col].contains(arg0.getPoint())) {
//					cells[row][col].flipColor();
//				}
//			}
//		}
		step();
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
