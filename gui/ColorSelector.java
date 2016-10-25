package gui;

import java.awt.Color;

public class ColorSelector {

	private Color newColor = null;
	
	//This code's purpose is to take in a string that we have allowed the user to select in the GUI and then return the matching object color.
	protected Color returnColor(String colorString)
	{
		//System.out.println(colorString);
		if (colorString == "GREEN"){newColor=Color.GREEN;}
		else if (colorString == "YELLOW"){newColor=Color.YELLOW;}
		else if (colorString == "ORANGE"){newColor=Color.ORANGE;}
		else if (colorString == "MAGENTA"){newColor=Color.MAGENTA;}
		else if (colorString == "BLUE"){newColor=Color.BLUE;}
		else if (colorString == "RED"){newColor=Color.RED;}
		else if (colorString == "WHITE"){newColor=Color.WHITE;}
		else if (colorString == "BLACK"){newColor=Color.BLACK;}		
		else if (colorString == "CYAN"){newColor=Color.CYAN;}		
		else {System.out.println("Error: Color Choice invalid. Consult GUI or ColorSelector."); return null;}
		return newColor;
	}
}
