package other;
/*		Author: Nick Corrado and Tim Dobeck
 * 		Description: This is the constructor class for the Agents to be created and drawn. Velocity determined the direction each agent will move and
 * 		Color is for determining if the color of the agent will be green or invisible. The rest of the agents are being made because it is extending the
 * 		Rectangle2D superclass.
 * 		Parameters: Agent is made up of its x coordinate, y coordinate, fixed size of agent, Point2D is the direction the agent will move, and color of agent
 * 					Draw is made up of Graphics g which is the type for drawing things in a JPanel
 * 					SetColor is made up of the specific color of the agent, this is called in the Board class
 * 					Both setVelocity's determine a new x and y coordinate for the Agents to have after they step
 * 					SetX and SetY determines where each agent will be placed originally
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * @author Nick
 * An agent is a freely moving and freely acting member of the swarm. An agent's goal is to
 * bring layer 1 of the simulation to a desired state as defined by the user. Agents move
 * around the board interacting with the board's cells. Agents can ask a given cell what its
 * state is, change that state if it wishes. The Agent class extends Ellipse2D.Double because
 * agents are, graphically, small circles with a position, width, height, etc.
 */
@SuppressWarnings("serial")
public class Agent extends Ellipse2D.Double {
	private int size;
	private Point2D velocity;	//adds direction to our agents
	private Color color; 	//only adding a color here so we can make it green or invisible in the board class
	public boolean agentPastBoard = false;    //helps determine if the color will be green or invisible
	
	
	/**
	 * @author Nick
	 * Constructor that makes an agent using given coordinates, Color, and velocity vector.
	 * The super class is Ellipse2D.Double; the constructor is passing it an x and y and
	 * the ellipse's dimensions.
	 * @param x
	 * @param y
	 * @param size
	 * @param v
	 * @param color
	 */
	public Agent(int x, int y, int size, Point2D v, Color color) {
		super(x, y, size, size);
		this.velocity = v;
		this.color = color;
	}
	
	/**
	 * @author Nick
	 * Constructor that makes an agent using randomly generated coordinates for its position.
	 * The constructor takes in the boardWidth so that no agents are created outside the width
	 * of the board. A random velocity is also generated for the agent. The super class is
	 * Ellipse2D.Double; the constructor is passing it an x and y and the ellipse's dimensions.
	 * @param boardWidth
	 * @param size
	 */
	public Agent(int boardWidth, int size) {
		super((int)(Math.random()*boardWidth), (int)(Math.random()*boardWidth), size, size);
		this.velocity = new Point2D.Double(Math.random()*10-5, Math.random()*10-5);
		this.color = Color.green;
	}
	
	/**
	 * @author Nick
	 * Draws the agent as an ellipse.
	 * @param g
	 */
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fill(this);
		g.fillOval((int)x, (int)y, size, size);
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getSize() {
		return size;
	}

	public Point2D getVelocity() {
		return velocity;
	}
	
	//determines how much an agent will move in a particular direction
	public void setVelocity(Point2D velocity) {
		this.velocity = new Point2D.Double(velocity.getX(), velocity.getY());
	}
	
	//determines how much an agent will move in a particular direction
	public void setVelocity(double x, double y) {
		this.velocity = new Point2D.Double(x, y);
	}
	
	public void setX(double x) {
		//this is being autocast to an int in board
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * @author Nick
	 * This method is called when the simulation is being "stepped" forward once. It
	 * updates the agent's position by adding its velocity, and 10% of the time also
	 * changes the agent's velocity. This is so that agents sometimes change direction,
	 * but not too often. The range of the velocity is between (-5, 5) in both the x
	 * and y directions so that values will be evenly distributed in each direction.
	 */
	public void step() {
		this.setX(x+velocity.getX());
		this.setY(y+velocity.getY());
		
		if (Math.random() < 0.1) {
			this.setVelocity(Math.random()*10-5, Math.random()*10-5);
		}
	}

}
