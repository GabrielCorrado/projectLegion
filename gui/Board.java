package gui;

/*
 *		Authors: Zakary Gray, Tim Dobeck, Nick Corrado, Gabriel Petkac
 *		Description:  This is currently the main class for all intents and purposes.  The board holds the cells of layers 1 and 2
 *               as well as the agents in the layer 3 swarm. The jframe of Board are displayed in the GUI after a new board is created
 *               in NewBoardWindow.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import cells.Cell;
import cells.GenericCell;
import cells.NullCell;
import other.SwarmAgent;
import strategies.AbstractStrategy;
import strategies.AllBlack;
import strategies.CheckerBoard;
import strategies.Lines;
/*
 * Authors: Nick, Tim, Zak, Gabriel
 * Description: This is the guts of the program. Two 2x2 Cell arrays of size[size X size] are created to be layers 1 and 2,
 * Layer 2 gives information about layer 1, for example... Layer one currently tells which polarity (black in the corners or white)
 * of a checker board the cells in layer 1 are in.  An array of Agents is also created with random movement over the layers 1 & 2
 * while randomly changing the cells underneath them. In the future, the agents will have a low level of intelligence.
 * Parameters: width of board in pixels, height of board in pixels, number of cells on a side, and number of Agents
 */
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseInputListener {
	private Cell[][] layer1;
	private Cell[][] layer2;
	private GenericCell[][] display; //layer to paint
	private int numCellsOnSide; //these are the numbers of cells in the board, NOT the graphical dimensions of the board
	private boolean wrap = false; //whether the walls of the Board wrap or not; by default, they don't
	private Color polarity;
	private int cellSize; //pixel dimensions of each cell
	private int agentSize; //pixel dimensions of each agent
	private SwarmAgent[] agents;
	private Color agentColor = GUI.agentColor;
	private double attractorStrength = 1;
	private int attractorMaxDistance; //distance in cells, not pixels
	private int attractOrRepel = 1; //1 if attract, -1 if repel
	private int tempL2D;//temporary layer 2 used in calculations
	private int agentRate = 50;//delay between timer firing
	public int period = 100000;
	public Timer t;//main timer
	public Color oldPolarity1 = Color.RED;//old polarity color is saved so that it is possible to change them to new polarity
	public Color oldPolarity2 = Color.BLUE;
	public Color oldPolarity3 = Color.YELLOW;
	public Color oldPolarity4 = Color.WHITE;
	private int blackCellCounter = 0;//info for GUI
	private int whiteCellCounter = 0;
	public static int currBlackCellCounter = 0;
	public static int currWhiteCellCounter = 0;
	private GenericCell[] neighbors = new GenericCell[8];//the 8 cells that neighbor a given cell in layer 1 are stored here
	private AbstractStrategy strategy;//stategy that the agents and layer 2 use for their calculations given the current goal

	public Board(int width, int height, int numCellsOnSide, int numAgents, boolean wrap, Cell[][] layer1GetNeighbor) {
		strategy = new CheckerBoard();
		//set preferred graphical dimensions of the board
		setPreferredSize(new Dimension(width, height));
		this.numCellsOnSide = numCellsOnSide;
		this.wrap = wrap;
		display = new GenericCell[numCellsOnSide][numCellsOnSide];
		//set the graphical dimensions of the cells themselves. the cells are always square, but the
		//space they take up is constrained by the width and height of the board and by the number of cells.
		cellSize = width/numCellsOnSide;
		agentSize = (int)(cellSize*0.7);

		attractorMaxDistance = cellSize*5; //the attractor affects everything in a five cell block radius

		//layer 1
		layer1 = new Cell[numCellsOnSide][numCellsOnSide];
		int rand;
		for (int row = 0; row < layer1.length; row++) {
			for (int col = 0; col < layer1[row].length; col++) {
				rand = (int) (Math.random()*2);
				if (rand==0)
				{
					blackCellCounter++;
					currBlackCellCounter++;
				}
				else
				{
					whiteCellCounter++;
					currWhiteCellCounter++;
				}
				layer1[row][col] = new Cell(row*cellSize, col*cellSize, cellSize, (rand == 0? Color.BLACK : Color.WHITE));
			}
		}

		layer2 = new Cell[numCellsOnSide][numCellsOnSide];
		for (int row = 0; row < layer2.length; row++) {
			for (int col = 0; col < layer2[row].length; col++) {
				layer2[row][col] = strategy.Layer2(layer1, cellSize, row, col, getNeighbors(layer1, row, col));	
			}
		}
		//generates the swarm and adjusts their positions
		agents = new SwarmAgent[numAgents];
		for (int i = 0; i < agents.length; i++) {
			//these agents generate in a random spot on the board, with a random starting vector.
			agents[i] = new SwarmAgent(width, cellSize, agentSize);
		}

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		StartTimer();

		if (GUI.layer2Draw == 3)
		{
			GUI.layer2Draw = 1;
		}

		GUI.setLblIntBlackCells(blackCellCounter);
		GUI.setLblIntWhiteCells(whiteCellCounter);
	}

	public void StartTimer()
	{
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				step();
				repaint();
				//System.out.println("tick");
				t.cancel(); // cancel time
				StartTimer();
			}
		}, 500-agentRate,period);
	}

	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);

		Graphics2D g = (Graphics2D)arg0;

		//draw boards
		if (GUI.layer2Draw == 1)
		{
			for (int row = 0; row < numCellsOnSide; row++) {
				for (int col = 0; col <numCellsOnSide; col++) {
					display[row][col] = layer1[row][col];
					display[row][col].draw(g);
				}
			}
			tempL2D = 1;
		}
		else if(GUI.layer2Draw == 2)
		{
			for (int row = 0; row < numCellsOnSide; row++) {
				for (int col = 0; col <numCellsOnSide; col++) {
					display[row][col] = layer2[row][col];
					display[row][col].draw(g);
				}
			}
			tempL2D = 2;
		}
		else
		{
			GUI.layer2Draw = tempL2D;
			repaint();
		}

		//draw agents
		for (SwarmAgent agent : agents) {
			//if the agents have been set to non-visible, sets them to a transparent color
			if (GUI.whetherAgentsVisible) {
				agent.setColor(agentColor);
			} else {
				agent.setColor(new Color(0,1,0,0));
			}
			agent.draw(g);
			//if you're sticking off the right or bottom of map, draw another ellipse there too
			//this is a hack; really, i think this should be a job for the agent's draw method.
			if (wrap && agent.getX()+agentSize > this.getWidth()) {
				g.fill(new Ellipse2D.Double(agent.getX()-this.getWidth(), agent.getY(), agentSize, agentSize));
			}
			if (wrap && agent.getY()+agentSize > this.getHeight()) {
				g.fill(new Ellipse2D.Double(agent.getX(), agent.getY()-this.getHeight(), agentSize, agentSize));
			}
		}
	}

	/**
	 * @author Nick, zgray17, Tim
	 * This method handles the "stepping forward" of the simulation, which for now just means updating the positions of all
	 * of the agents and changing the colors of the board's cells based on these agents' actions.
	 */
	public void step() {
		//for each agent, have the agent decide randomly whether to flip its cell's color
		for (SwarmAgent agent : agents) {
			//10% of the time, the agent will determine algebraically which cell it's in, then flip the color of that cell.
			//a better approach than this would be to have the agent store which cell it's currently in, then just flip that
			//color 10% of the time. this would also make it easy to keep the agent from flipping the same cell many times
			//before leaving it--something we haven't gotten to yet.

			//TESTING NEIGHBORS
			int cornerCount = 0;
			int edgeCount = 0;
			neighbors = getNeighbors(layer1, (int)agent.getCenterX()/cellSize, (int)agent.getCenterY()/cellSize);
			int rowMax = layer1.length-1;
			int colMax = layer1[rowMax-1].length-1;
			if((int)agent.getCenterX()/cellSize<=rowMax && (int)agent.getCenterY()/cellSize<=colMax)
			{
				strategy.logic(agent, layer1, layer2, neighbors, layer1[((int)agent.getCenterX()/cellSize)][((int)agent.getCenterY()/cellSize)], cellSize);
				for (int row = 0; row < layer2.length; row++) {
					for (int col = 0; col < layer2[row].length; col++) {
						GenericCell[] neighbors =  getNeighbors(layer1, row, col);
						layer2[row][col] = strategy.Layer2(layer1, cellSize, row, col, neighbors);
					}
				}
			}
			
			agent.step(cellSize);
			if (wrap) {
				//since there's no walls, this lets the agents "wrap" to the other side of the screen. this is awesome.
				agent.setX((agent.getX()+this.getWidth())%this.getWidth());
				agent.setY((agent.getY()+this.getHeight())%this.getHeight());

				//this is not perfect: what we actually want this to do is draw both, so long as it's sticking a bit
				//off of the screen. that makes the above operations much uglier. :(
			} else {
				//since there's walls, this checks whether the agent has crossed any of the four bounds of the board:
				//left, then top, then right, then bottom, and whether the agent's velocity has it headed further off
				//the board. If it does, then it has the agent "bounce" off the board's wall. This is kind of a hack--
				//the desirable behavior, actually, is actually just always bouncing it, but that will require refactoring
				//this, and the agent's own step method, at a later date.
				if (agent.x < agent.getSize() && agent.getVelocity().getX() < 0)  
				{
					agent.xBounce();
				}
				if (agent.y < agent.getSize() && agent.getVelocity().getY() < 0)
				{
					agent.yBounce();
				}
				if (agent.x+agentSize > numCellsOnSide*cellSize+agent.getSize() && agent.getVelocity().getX() > 0)
				{
					agent.xBounce();
				}
				if (agent.y+agentSize > numCellsOnSide*cellSize+agent.getSize() && agent.getVelocity().getY() > 0)
				{
					agent.yBounce();
				}
			}
		}
		GUI.setLblCurrBlackCells(currBlackCellCounter);
		GUI.setLblCurrWhiteCells(currWhiteCellCounter);
	}

	/**
	 * @author Nick
	 * This method takes in the board's cells and a particular row and column number and returns an array
	 * of all of the neighbors of the cell whose row and column number has been provided. For our purposes,
	 * "neighbor" means the eight cells directly and diagonally adjacent to the given cell. In the future,
	 * that can be restricted by the given user-defined rules to just the cells directly adjacent. This
	 * must also be revised to take into account whether the Board has wrap or not.
	 * @param cells
	 * @param rowNum
	 * @param colNum
	 * @return an array of all of the neighbors of the cell whose row and column number has been provided.
	 */
	public static GenericCell[] getNeighbors(Cell[][] cells, int rowNum, int colNum) {
		//each cell only has 8 neighbors! for now at least.... :(
		GenericCell[] neighbors = new Cell[8];
		int rowMax = cells.length-1;
		int colMax = cells[rowMax-1].length-1;
		//This is an attempt to use nullCell singletons on the edge of the board to handle edge cases
		//makes a new board with the null cells surrounding it on all sides
		//this does top and bottom rows, then the left and right sides
		//		GenericCell[][] cellsWithNull = new GenericCell[numCellsOnSide+2][numCellsOnSide+2];
		//		for (int row = 0; row < cellsWithNull.length; row++) {
		//			cellsWithNull[row][0] = NullCell.getNullCell();
		//			cellsWithNull[row][colMax] = NullCell.getNullCell();
		//		}
		//		for (int col = 0; col < cellsWithNull[0].length; col++) {
		//			cellsWithNull[0][col] = NullCell.getNullCell();
		//			cellsWithNull[rowMax][col] = NullCell.getNullCell();
		//		}

		//this 10% chance thing is just because java gets mad if you have stuff
		//after a return statement, it is super hardcore not a permanent feature
		//of this class trust me guys.
		//		if (Math.random() < 0.1) {
		//			neighbors[0] = cellsWithNull[rowNum-1][colNum-1];
		//			neighbors[1] = cellsWithNull[rowNum-1][colNum];
		//			neighbors[2] = cellsWithNull[rowNum-1][colNum+1];
		//			neighbors[3] = cellsWithNull[rowNum][colNum-1];
		//			neighbors[4] = cellsWithNull[rowNum][colNum+1];
		//			neighbors[5] = cellsWithNull[rowNum+1][colNum-1];
		//			neighbors[6] = cellsWithNull[rowNum+1][colNum];
		//			neighbors[7] = cellsWithNull[rowNum+1][colNum+1];
		//			return neighbors;
		//		}
		//		
		//		//obsolete approach
		//top left
		if (rowNum == 0 && colNum == 0) {
			neighbors[3] = cells[rowNum][colNum+1];
			neighbors[4] = cells[rowNum+1][colNum+1];
			neighbors[5] = cells[rowNum+1][colNum];
		}

		//bottom left
		if (rowNum == rowMax && colNum == 0) {
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[2] = cells[rowNum-1][colNum+1];
			neighbors[3] = cells[rowNum][colNum+1];
		}

		//top right
		if (rowNum == 0 && colNum == cells[0].length-1) {
			neighbors[5] = cells[rowNum+1][colNum];
			neighbors[6] = cells[rowNum+1][colNum-1];
			neighbors[7] = cells[rowNum][colNum-1];
		}

		//bottom right
		if (rowNum == rowMax && colNum == colMax) {
			neighbors[0] = cells[rowNum-1][colNum-1];
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[7] = cells[rowNum][colNum-1];
		}

		//top
		if (rowNum == 0 && colNum > 0 && colNum < colMax) {
			neighbors[3] = cells[rowNum][colNum+1];
			neighbors[4] = cells[rowNum+1][colNum+1];
			neighbors[5] = cells[rowNum+1][colNum];
			neighbors[6] = cells[rowNum+1][colNum-1];
			neighbors[7] = cells[rowNum][colNum-1];
		}

		//bottom
		if (rowNum == rowMax && colNum > 0 && colNum < colMax) {
			neighbors[0] = cells[rowNum-1][colNum-1];
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[2] = cells[rowNum-1][colNum+1];
			neighbors[3] = cells[rowNum][colNum+1];
			neighbors[7] = cells[rowNum][colNum-1];
		}

		//left
		if (rowNum > 0 && rowNum < rowMax && colNum == 0) {
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[2] = cells[rowNum-1][colNum+1];
			neighbors[3] = cells[rowNum][colNum+1];
			neighbors[4] = cells[rowNum+1][colNum+1];
			neighbors[5] = cells[rowNum+1][colNum];
		}

		//right
		if (rowNum > 0 && rowNum < rowMax && colNum == colMax) {
			neighbors[0] = cells[rowNum-1][colNum-1];
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[5] = cells[rowNum+1][colNum];
			neighbors[6] = cells[rowNum+1][colNum-1];
			neighbors[7] = cells[rowNum][colNum-1];
		}

		//middle cells obviously get everything
		if (rowNum > 0 && rowNum < rowMax && colNum > 0 && colNum < colMax) {
			neighbors[0] = cells[rowNum-1][colNum-1];
			neighbors[1] = cells[rowNum-1][colNum];
			neighbors[2] = cells[rowNum-1][colNum+1];
			neighbors[3] = cells[rowNum][colNum+1];
			neighbors[4] = cells[rowNum+1][colNum+1];
			neighbors[5] = cells[rowNum+1][colNum];
			neighbors[6] = cells[rowNum+1][colNum-1];
			neighbors[7] = cells[rowNum][colNum-1];
		}

		return neighbors;
	}


	/**
	 * This method returns the Cell occupied by a given agent in a given layer. Static because it's really just
	 * converting between x and y coordinates and 2D array position. Really, <em>that</em> should be the static method
	 * and may be coming up next.
	 * @param agent
	 * @param layer
	 * @return the cell occupied by a given agent in a given layer
	 */
	public static Cell getOccupiedCell(SwarmAgent agent, Cell[][] layer) {
		return layer[(int)(agent.getCenterX()/layer[0][0].width)][(int)(agent.getCenterY()/layer[0][0].height)];
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println((int)(arg0.getX()/cellSize));
		System.out.println((int)(arg0.getY()/cellSize));
		System.out.println();


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
		double magnitude;
		Point2D.Double towardMouse;

		//if you click in a spot on the board, the agents will be attracted to that spot with decreasing effect
		//the further away they are
		if (GUI.attractOrRepel)
		{
			attractOrRepel=1;
		}
		else
		{
			attractOrRepel=-1;
		}
		for (SwarmAgent agent : agents) {
			magnitude = Math.sqrt((agent.getCenterX()-arg0.getX())*(agent.getCenterX()-arg0.getX()) + (agent.getCenterY()-arg0.getY())*(agent.getCenterY()-arg0.getY()));
			towardMouse = new Point2D.Double(attractOrRepel*cellSize*(arg0.getX()-agent.getCenterX())/magnitude, attractOrRepel*cellSize*(arg0.getY()-agent.getCenterY())/magnitude);

			if (magnitude < attractorMaxDistance) {
				//then the agent is in the specified range
				//double angle = Math.asin(distance/(agent.getCenterY()-arg0.getY()));
				if (Math.random() < attractorStrength) {
					agent.setVelocity(towardMouse);
				}
			}

		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @author zgray17
	 * This method updates the rate of the agent clock. Blah blah blah.
	 * @param rate
	 */
	public void setAgentRate(int rate)
	{
		agentRate = rate;
	}

	/**
	 * @author zgray17
	 * This method updates the polary color 1. Blah blah blah.
	 * @param polarity1
	 */
	public void updateNewPolarityColor1(Color polarity1)
	{
		for (int row = 0; row < layer2.length; row++) {
			for (int col = 0; col < layer2[row].length; col++) {
				if(layer2[row][col].getColor() == oldPolarity1)
				{
					layer2[row][col].setColor(polarity1);
				}
			}
		}
		oldPolarity1 = polarity1;
	}

	/**
	 * @author zgray17
	 * This method updates the polarity of color 2. Blah blah blah.
	 * @param polarity2
	 */
	public void updateNewPolarityColor2(Color polarity2)
	{
		for (int row = 0; row < layer2.length; row++) {
			for (int col = 0; col < layer2[row].length; col++) {
				if(layer2[row][col].getColor() == oldPolarity2)
				{
					layer2[row][col].setColor(polarity2);
				}
			}
		}
		oldPolarity2 = polarity2;
	}

	public void updateNewPolarityColor3(Color polarity3)
	{
		for (int row = 0; row < layer2.length; row++) {
			for (int col = 0; col < layer2[row].length; col++) {
				if(layer2[row][col].getColor() == oldPolarity3)
				{
					layer2[row][col].setColor(polarity3);
				}
			}
		}
		oldPolarity3 = polarity3;
	}

	public void updateNewPolarityColor4(Color polarity4)
	{
		for (int row = 0; row < layer2.length; row++) {
			for (int col = 0; col < layer2[row].length; col++) {
				if(layer2[row][col].getColor() == oldPolarity4)
				{
					layer2[row][col].setColor(polarity4);
				}
			}
		}
		oldPolarity4 = polarity4;
	}

	/**
	 * @author zgray17
	 * This method updates the color of the agents. Blah blah blah.
	 * @param newColor
	 */
	public void updateAgentColor(Color newColor)
	{
		this.agentColor = newColor;
		for (int i = 0; i < agents.length; i++) {
			agents[i].setColor(agentColor);
		}
	}

	public void updateGoalStrategy(AbstractStrategy newStrategy)
	{
		strategy = newStrategy;
		for (int row = 0; row < layer2.length; row++) {
			for (int col = 0; col < layer2[row].length; col++) {
				GenericCell[] neighbors =  getNeighbors(layer1, row, col);
				layer2[row][col] = strategy.Layer2(layer1, cellSize, row, col, neighbors);
			}
		}
	}

	public void setWrap(boolean guiWrap)
	{
		this.wrap = guiWrap;
	}
}
