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
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import cells.Cell;
import cells.GenCell;
import cells.NullCell;
import other.Agent;
import other.LabelHandler;
/*
 * Authors: Nick, Tim, Zak, Gabriel
 * Description: This is the guts of the program. Two 2x2 Cell arrays of size[size X size] are created to be layers 1 and 2,
 * Layer 2 gives information about layer 1, for example... Layer one currently tells which polarity (black in the corners or white)
 * of a checker board the cells in layer 1 are in.  An array of Agents is also created with random movement over the layers 1 & 2
 * while randomly changing the cells underneath them. In the future, the agents will have a low level of intelligence.
 * Parameters: width of board in pixels, height of board in pixels, size of board[nXn], typeBoard is a diagnostic tool, and number of Agents
 */
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseInputListener {
	private Cell[][] cells;//layer1
	private Cell[][] cells2;//layer2
	private GenCell[][] display;//layer to paint
	private int numCellsOnSide; //these are the numbers of cells in the board, NOT the graphical dimensions of the board
	private int borderForCentering;
	private Color c;
	private Color polarity;
	private int cellSize;//pixel dimensions of each cell
	private int agentSize;//pixel dimensions of each agent
	private Agent[] agents;
	private Color agentColor = GUI.agentColor;
	private int tempL2D;
	private int agentRate = 50;
	public int period = 10;
	public Timer t;
	public Color oldPolarity1 = Color.RED;
	public Color oldPolarity2 = Color.BLUE;
	public LabelHandler labelHandler;
	
	public Board(int width, int height, int numCellsOnSide, int typeBoard, int numAgents) {
		//set preferred graphical dimensions of the board
		setPreferredSize(new Dimension(width, height));
		//HOW DID I FORGET THIS EARLIER
		this.numCellsOnSide = numCellsOnSide;
		display = new GenCell[numCellsOnSide][numCellsOnSide];
		//set the graphical dimensions of the cells themselves
		//the cells are always square, but the space they take up is constrained by the width and height of the board
		//and by the number of cells.
		
		//determine how much border space it needs to be centered in the given panel, then use that value
		//width and height are currently 800 and 800 respectively; these are and will be hard-coded values, but might possibly change
		int spareSpace = width%numCellsOnSide;
		borderForCentering = spareSpace/2;
		
		cellSize = ((width)-2*borderForCentering)/numCellsOnSide; //board space in the middle?
		agentSize = (int)(cellSize*0.7);
		//gap between the two changes depending on if the size is 100 vs 5
		
		//layer 1
		cells = new Cell[numCellsOnSide][numCellsOnSide];
		int rand;
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				if (typeBoard==0)//random starting black and white board
				{
					rand = (int) (Math.random()*2);
					if (rand == 0)
					{
						c = Color.black;
					}
					else
					{
						c = Color.white;
					}
					cells[row][col] = new Cell(borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, c);
				}
				else if (typeBoard==1)//"solved" checkboard to start
				{
					if (row%2 == col%2)
					{
						c = Color.black;
					}
					else
					{
						c = Color.white;
					}
					cells[row][col] = new Cell(borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, c);

				}

			}

		}
		
		//generate the swarm; kirsch suggested, say, 30 agents, so we're trying 10 right now
		//we've tried moving the swarm agents every frame with mousedragged... it can handle at least 500 with no
		//visible slowdown.
		agents = new Agent[numAgents];
		for (int i = 0; i < agents.length; i++) {
			//these generate in a random spot on the board itself, with a random vector that makes no effing sense yet
			//agents[i] = new Agent((int)(borderForCentering+Math.random()*width-2*borderForCentering), (int)(borderForCentering+Math.random()*width-2*borderForCentering), agentSize, new Point2D.Double(Math.random()*10-5, Math.random()*10-5), agentColor);
			agents[i] = new Agent(width, agentSize);
		
		
			//agent.x < borderForCentering   AKA left border
			//agent.y < borderForCentering   AKA top border
			//agent.x > borderForCentering+(size*cellSize)   AKA right border
			//agent.y > borderForCentering+(size*cellSize)   AKA bottom border
			//you must add agentSize to the right border and the bottom border. This is because ellipses are essentially circles with boxes in them and the top left corner starts
			//at (0,0). Therefore you want to add agentSize to the right and the bottom so it knows if the tip of the circle is at the point where the board cannot go anymore.
			if (agents[i].x < borderForCentering || agents[i].y < borderForCentering || agents[i].x+agentSize > borderForCentering+(numCellsOnSide*cellSize) || agents[i].y+agentSize > borderForCentering+(numCellsOnSide*cellSize))
			{
				agents[i].x = borderForCentering + agents[i].x;
			}
			if ( agents[i].y < borderForCentering)
			{
				agents[i].y = borderForCentering + agents[i].y;
			}
			if (agents[i].x+agentSize > borderForCentering+(numCellsOnSide*cellSize))
			{
				agents[i].x =  borderForCentering+(numCellsOnSide*cellSize) - agents[i].x;
			}
			if (agents[i].y+agentSize > borderForCentering+(numCellsOnSide*cellSize))
			{
				agents[i].y = borderForCentering+(numCellsOnSide*cellSize) - agents[i].y;
			}
			else
			{
				agents[i].agentPastBoard = false;
			}
		}
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//In regards to polarity, a checker board with black in the top left is red, the other is blue.
		if(cells[0][0].getColor()==Color.BLACK)
		{
			polarity = Color.RED;
		}
		else
		{
			polarity = Color.BLUE;
		}
		
		//layer 2 initial construction
		layer2(polarity);
		StartTimer();
		
		if (GUI.layer2Draw == 3)
		{
			GUI.layer2Draw = 1;
		}
		
		labelHandler = new LabelHandler(numCellsOnSide, agents.length,0,0);
	}
	
	protected void layer2(Color polarity)
	{
		cells2 = new Cell[numCellsOnSide][numCellsOnSide];

		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {

				if(polarity == Color.RED)
					//if the top left is black
				{
					if(cells[row][col].getColor() == Color.BLACK)
						//if the layer 1 cell is black
					{
						if(col%2 == row%2)
							//if its in a spot that should be black
						{
							cells2[row][col] = new Cell(800+borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, GUI.polarity1);
							//then you are the same polarity as cell[0][0]
						}
						else
							//if its in a spot that SHOULDN'T be black
						{
							cells2[row][col] = new Cell(800+borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, GUI.polarity2);
							//then you are in the opposite polarity than cells[0][0]
						}
					}
					else
						//if the layer 1 cell is white
					{
						if(col%2 == row%2)
							//if its in a spot that SHOULDN'T be 
						{
							cells2[row][col] = new Cell(800+borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, GUI.polarity2);
							// then its in the opposite polarity than cells[0]
						}
						else
							//if its in a spot that should be white
						{
							cells2[row][col] = new Cell(800+borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, GUI.polarity1);
							//then its in the same polarity as cells[0][0]
						}
					}
				}
				else
				{
					if(cells[row][col].getColor() == Color.WHITE)
					{
						if(col%2 == row%2)
						{
							cells2[row][col] = new Cell(800+borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, GUI.polarity1);
						}
						else
						{
							cells2[row][col] = new Cell(800+borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, GUI.polarity2);
						}
					}
					else
					{
						if(col%2 == row%2)
						{
							cells2[row][col] = new Cell(800+borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, GUI.polarity2);
						}
						else
						{
							cells2[row][col] = new Cell(800+borderForCentering+row*cellSize, borderForCentering+col*cellSize, cellSize, GUI.polarity1);
						}
					}
				}
			}
		}
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
		}, 100-agentRate,period);
	}
	
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		
		Graphics2D g = (Graphics2D)arg0;
		//draw boards

		if (GUI.layer2Draw == 1)
		{
			for (int row = 0; row < numCellsOnSide; row++) {
				for (int col = 0; col <numCellsOnSide; col++) {
					display[row][col] = cells[row][col];
					display[row][col].draw(g);
				}
			}
			tempL2D = 1;
		}
		else if(GUI.layer2Draw == 2)
		{
			for (int row = 0; row < numCellsOnSide; row++) {
				for (int col = 0; col <numCellsOnSide; col++) {
					display[row][col] = cells2[row][col];
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
		for (int i = 0; i < agents.length; i++) {
			if (agents[i].agentPastBoard == true)
			{
				agents[i].setColor(new Color(0,1,0,0));
			}
			else
			{
				agents[i].setColor(agentColor);
			}
			agents[i].draw(g);
		}
		
		for (int i = 0; i < agents.length; i++) {
			agents[i].draw(g);
		}
	}

	public void step() {
		//for each agent, have the agent decide randomly whether to flip its cell's color
		for (Agent agent : agents) {
			if (Math.random() < 0.1) {
				//to decide which cell the agent is in... this is bad :( need to possibly flip it around, or decide
				//which cell each agent is in each time they move, in fact this could be determined using each agent's
				//x and y rather than searching all the cells... but this should be runnable for now
				for (int row = 0; row < cells.length; row++) {
					for (int col = 0; col < cells[row].length; col++) {
						if (cells[row][col].contains(agent.getCenterX(), agent.getCenterY())) {
							cells[row][col].flipColor();
							cells2[row][col].flipColor();
						}
					}
				}
			}
			//agent.x < borderForCentering   AKA left border
			//agent.y < borderForCentering   AKA top border
			//agent.x > borderForCentering+(size*cellSize)   AKA right border
			//agent.y > borderForCentering+(size*cellSize)   AKA bottom border
			//you must add agentSize to the right border and the bottom border. This is because ellipses are essentially circles with boxes in them and the top left corner starts
			//at (0,0). Therefore you want to add agentSize to the right and the bottom so it knows if the tip of the circle is at the point where the board cannot go anymore.
			if (agent.x < borderForCentering)  
			{
				agent.agentPastBoard = true;
				agent.x = borderForCentering + agent.x;
				agent.agentPastBoard = false;
			}
			if ( agent.y < borderForCentering)
			{
				agent.agentPastBoard = true;
				agent.y = borderForCentering + agent.y;
				agent.agentPastBoard = false;
			}
			if (agent.x+agentSize > borderForCentering+(numCellsOnSide*cellSize))
			{
				agent.agentPastBoard = true;
				agent.x = borderForCentering+(numCellsOnSide*cellSize) - agent.x;
				agent.agentPastBoard = false;
			}
			if (agent.y+agentSize > borderForCentering+(numCellsOnSide*cellSize))
			{
				agent.agentPastBoard = true;
				agent.y = borderForCentering+(numCellsOnSide*cellSize) - agent.y;
				agent.agentPastBoard = false;
			}
			else
			{
				agent.agentPastBoard = false;
			}
			agent.step();

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

		//for each agent, have the agent decide randomly whether to flip its cell's color
		for (Agent agent : agents) {
			if (Math.random() < 0.1) {
				//to decide which cell the agent is in... this is bad :( need to possibly flip it around, or decide
				//which cell each agent is in each time they move, in fact this could be determined using each agent's
				//x and y rather than searching all the cells... but this should be runnable for now
				//						for (int row = 0; row < cells.length; row++) {
				//							for (int col = 0; col < cells[row].length; col++) {
				//								if (cells[row][col].contains(agent.getCenterX(), agent.getCenterY())) {
				//									cells[row][col].flipColor();
				//								}
				//							}
				//						}
			}
			//what we actually want to do is calculate which cell the agent is in based on its x and y coordinates.
			//this is a bit complicated, but can be worked out algebraically. i think this might be it? should occur
			//if and only if the agent is on the board.
			if (agent.getCenterX() >= borderForCentering && agent.getCenterX() < 800-borderForCentering && agent.getCenterY() >= borderForCentering && agent.getCenterY() < 800-borderForCentering) {
				cells[((int)(agent.getCenterX()-borderForCentering))/cellSize][((int)(agent.getCenterY()-borderForCentering))/cellSize].flipColor();
			}
			//agent.x < borderForCentering   AKA left border
			//agent.y < borderForCentering   AKA top border
			//agent.x > borderForCentering+(size*cellSize)   AKA right border
			//agent.y > borderForCentering+(size*cellSize)   AKA bottom border
			//you must add agentSize to the right border and the bottom border. This is because ellipses are essentially circles with boxes in them and the top left corner starts
			//at (0,0). Therefore you want to add agentSize to the right and the bottom so it knows if the tip of the circle is at the point where the board cannot go anymore.
			if (agent.x < borderForCentering || agent.y < borderForCentering || agent.x+agentSize > borderForCentering+(numCellsOnSide*cellSize) || agent.y+agentSize > borderForCentering+(numCellsOnSide*cellSize))
			{
				agent.agentPastBoard = true;
				agent.setColor(new Color(0,1,0,0));
			}
			else
			{
				agent.agentPastBoard = false;
				agent.setColor(Color.green);
			}
		}
		
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
		
		//for each agent, have the agent decide randomly whether to flip its cell's color
		for (Agent agent : agents) {
			if (Math.random() < 0.1) {
				//to decide which cell the agent is in... this is bad :( need to possibly flip it around, or decide
				//which cell each agent is in each time they move, in fact this could be determined using each agent's
				//x and y rather than searching all the cells... but this should be runnable for now
//				for (int row = 0; row < cells.length; row++) {
//					for (int col = 0; col < cells[row].length; col++) {
//						if (cells[row][col].contains(agent.getCenterX(), agent.getCenterY())) {
//							cells[row][col].flipColor();
//						}
//					}
//				}
			}
			//what we actually want to do is calculate which cell the agent is in based on its x and y coordinates.
			//this is a bit complicated, but can be worked out algebraically. i think this might be it? should occur
			//if and only if the agent is on the board.
			if (agent.getCenterX() >= borderForCentering && agent.getCenterX() < 800-borderForCentering && agent.getCenterY() >= borderForCentering && agent.getCenterY() < 800-borderForCentering) {
				cells[((int)(agent.getCenterX()-borderForCentering))/cellSize][((int)(agent.getCenterY()-borderForCentering))/cellSize].flipColor();
			}
			//agent.x < EXTRA_BOARD_SPACE   AKA left border
			//agent.y < EXTRA_BOARD_SPACE   AKA top border
			//agent.x > EXTRA_BOARD_SPACE+(size*cellSize)   AKA right border
			//agent.y > EXTRA_BOARD_SPACE+(size*cellSize)   AKA bottom border
			//you must add agentSize to the right border and the bottom border. This is because ellipses are essentially circles with boxes in them and the top left corner starts
			//at (0,0). Therefore you want to add agentSize to the right and the bottom so it knows if the tip of the circle is at the point where the board cannot go anymore.
			if (agent.x < borderForCentering || agent.y < borderForCentering || agent.x+agentSize > borderForCentering+(numCellsOnSide*cellSize) || agent.y+agentSize > borderForCentering+(numCellsOnSide*cellSize))
			{
				agent.agentPastBoard = true;
				agent.setColor(new Color(0,1,0,0));
			}
			else
			{
				agent.agentPastBoard = false;
				agent.setColor(Color.green);
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
		for (int row = 0; row < cells2.length; row++) {
			for (int col = 0; col < cells2[row].length; col++) {
				if(cells2[row][col].getColor() == oldPolarity1)
				{
					cells2[row][col].setColor(polarity1);
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
		for (int row = 0; row < cells2.length; row++) {
			for (int col = 0; col < cells2[row].length; col++) {
				if(cells2[row][col].getColor() == oldPolarity2)
				{
					cells2[row][col].setColor(polarity2);
				}
			}
		}
		oldPolarity2 = polarity2;
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
}
