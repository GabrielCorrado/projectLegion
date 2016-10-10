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

public class Agent extends Ellipse2D.Double {
	private int size;
	private Point2D velocity;	//adds direction to our agents
	private Color color; 	//only adding a color here so we can make it green or invisible in the board class
	public boolean agentPastBoard = false;    //helps determine if the color will be green or invisible
	
	//constructor
	public Agent(int x, int y, int size, Point2D v, Color color) {
		super(x, y, size, size);
		this.velocity = v;
		this.color = color;
	}
	
	//draws Agents using the superclass for Ellipse2D
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
	
	//move the agent along according to its velocity vector, updating its x and y in the process
	public void step() {
		this.setX(x+velocity.getX());
		this.setY(y+velocity.getY());
		this.setVelocity(Math.random()*10-5, Math.random()*10-5);
	}
	
}
