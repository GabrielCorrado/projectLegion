package gui;
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

@SuppressWarnings("serial")
public class Board extends JPanel implements MouseInputListener {
	public Cell[][] cells;
	public Cell[][] cells2;
	public GenCell[][] display;
	public int size; //these are the numbers of cells in the board, NOT the graphical dimensions of the board
	private static final int EXTRA_BOARD_SPACE = 50;
	private Color boardCellColor;
	public Color polarity;
	private int cellSize;
	private int agentSize;
	private Agent[] agents;
	private Color agentColor = Color.green;
	private int tempL2D;
	private int agentRate = 50;
	public int period = 10;
	public Timer t;
	public Color oldPolarity1 = Color.RED;
	public Color oldPolarity2 = Color.BLUE;

	
	public Board(int width, int height, int size, int typeBoard, int numAgents) {
		//set preferred graphical dimensions of the board
		setPreferredSize(new Dimension(width, height));
		//HOW DID I FORGET THIS EARLIER
		this.size = size;
		display = new GenCell[size][size];
		//set the graphical dimensions of the cells themselves
		//the cells are always square, but the space they take up is constrained by the width and height of the board
		//and by the number of cells.
		cellSize = ((width)-2*EXTRA_BOARD_SPACE)/size; //board space in the middle?
		agentSize = (int)(cellSize*0.7);
		//gap between the two changes depending on if the size is 100 vs 5
		
		//layer 1
		cells = new Cell[size][size];
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				if (typeBoard==0)
				{
					int rand = (int) (Math.random()*2);
					if (rand == 0)
					{
						boardCellColor = Color.black;
					}
					else
					{
						boardCellColor = Color.white;
					}
					cells[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, boardCellColor);
				}
				else if (typeBoard==1)
				{
					if (row%2 == col%2)
					{
						boardCellColor = Color.black;
					}
					else
					{
						boardCellColor = Color.white;
					}
					cells[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, boardCellColor);

				}

			}

		}
		
		//generate the swarm; kirsch suggested, say, 30 agents, so we're trying 10 right now
		//we've tried moving the swarm agents every frame with mousedragged... it can handle at least 500 with no
		//visible slowdown.
		agents = new Agent[numAgents];
		for (int i = 0; i < agents.length; i++) {
			//these generate in a random spot on the board itself, with a random vector that makes no effing sense yet
			agents[i] = new Agent((int)(Math.random()*(width-EXTRA_BOARD_SPACE)), (int)(Math.random()*(width-EXTRA_BOARD_SPACE)), agentSize, new Point2D.Double(Math.random()*10-5, Math.random()*10-5), agentColor);
		
			//agent.x < EXTRA_BOARD_SPACE   AKA left border
			//agent.y < EXTRA_BOARD_SPACE   AKA top border
			//agent.x > EXTRA_BOARD_SPACE+(size*cellSize)   AKA right border
			//agent.y > EXTRA_BOARD_SPACE+(size*cellSize)   AKA bottom border
			//you must add agentSize to the right border and the bottom border. This is because ellipses are essentially circles with boxes in them and the top left corner starts
			//at (0,0). Therefore you want to add agentSize to the right and the bottom so it knows if the tip of the circle is at the point where the board cannot go anymore.
			if (agents[i].x < EXTRA_BOARD_SPACE)  
			{
				agents[i].agentPastBoard = true;
				agents[i].x = EXTRA_BOARD_SPACE + agents[i].x;
				agents[i].agentPastBoard = false;
			}
			if ( agents[i].y < EXTRA_BOARD_SPACE)
			{
				agents[i].agentPastBoard = true;
				agents[i].y = EXTRA_BOARD_SPACE + agents[i].y;
				agents[i].agentPastBoard = false;
			}
			if (agents[i].x+agentSize > EXTRA_BOARD_SPACE+(size*cellSize))
			{
				agents[i].agentPastBoard = true;
				agents[i].x =  EXTRA_BOARD_SPACE+(size*cellSize) - agents[i].x;
				agents[i].agentPastBoard = false;
			}
			if (agents[i].y+agentSize > EXTRA_BOARD_SPACE+(size*cellSize))
			{
				agents[i].agentPastBoard = true;
				agents[i].y = EXTRA_BOARD_SPACE+(size*cellSize) - agents[i].y;
				agents[i].agentPastBoard = false;
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
	}
	
	protected void layer2(Color polarity)
	{

		cells2 = new Cell[size][size];
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
							cells2[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, GUI.polarity1);
							//then you are the same polarity as cell[0][0]
						}
						else
							//if its in a spot that SHOULDN'T be black
						{
							cells2[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, GUI.polarity2);
							//then you are in the opposite polarity than cells[0][0]
						}
					}
					else
						//if the layer 1 cell is white
					{
						if(col%2 == row%2)
							//if its in a spot that SHOULDN'T be 
						{
							cells2[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, GUI.polarity2);
							// then its in the opposite polarity than cells[0]
						}
						else
							//if its in a spot that should be white
						{
							cells2[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, GUI.polarity1);
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
							cells2[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, GUI.polarity1);
						}
						else
						{
							cells2[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, GUI.polarity2);
						}
					}
					else
					{
						if(col%2 == row%2)
						{
							cells2[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, GUI.polarity2);
						}
						else
						{
							cells2[row][col] = new Cell(EXTRA_BOARD_SPACE+row*cellSize, EXTRA_BOARD_SPACE+col*cellSize, cellSize, GUI.polarity1);
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
			for (int row = 0; row < size; row++) {
				for (int col = 0; col <size; col++) {
					display[row][col] = cells[row][col];
					display[row][col].draw(g);
				}
			}
			tempL2D = 1;
		}
		else if(GUI.layer2Draw == 2)
		{
			for (int row = 0; row < size; row++) {
				for (int col = 0; col <size; col++) {
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
				agents[i].setColor(Color.green);
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
					//agent.x < EXTRA_BOARD_SPACE   AKA left border
					//agent.y < EXTRA_BOARD_SPACE   AKA top border
					//agent.x > EXTRA_BOARD_SPACE+(size*cellSize)   AKA right border
					//agent.y > EXTRA_BOARD_SPACE+(size*cellSize)   AKA bottom border
					//you must add agentSize to the right border and the bottom border. This is because ellipses are essentially circles with boxes in them and the top left corner starts
					//at (0,0). Therefore you want to add agentSize to the right and the bottom so it knows if the tip of the circle is at the point where the board cannot go anymore.
					if (agent.x < EXTRA_BOARD_SPACE)  
					{
						agent.agentPastBoard = true;
						agent.x = EXTRA_BOARD_SPACE + agent.x;
						agent.agentPastBoard = false;
					}
					if ( agent.y < EXTRA_BOARD_SPACE)
					{
						agent.agentPastBoard = true;
						agent.y = EXTRA_BOARD_SPACE + agent.y;
						agent.agentPastBoard = false;
					}
					if (agent.x+agentSize > EXTRA_BOARD_SPACE+(size*cellSize))
					{
						agent.agentPastBoard = true;
						agent.x = EXTRA_BOARD_SPACE+(size*cellSize) - agent.x;
						agent.agentPastBoard = false;
					}
					if (agent.y+agentSize > EXTRA_BOARD_SPACE+(size*cellSize))
					{
						agent.agentPastBoard = true;
						agent.y = EXTRA_BOARD_SPACE+(size*cellSize) - agent.y;
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

	//This method exists to be called by the GUI jComboBox when its index is changed from green to another color. It will run this code with the string from its JComboBox.
	protected void changeAgentColor(Color color){
		this.agentColor = color;
		System.out.print(agentColor);
		for (int i = 0; i < agents.length; i++) {
			agents[i].setColor(agentColor);
		}
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
	public void setAgentRate(int rate)
	{
		agentRate = rate;
	}

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
}
