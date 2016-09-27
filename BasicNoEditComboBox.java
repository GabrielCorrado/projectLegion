import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.util.Comparator;

import javax.swing.*;

public class BasicNoEditComboBox  extends JComboBox<String>{
	
		public static JComboBox<String> NEComboBox(String[] tempString){
		// Insert Code Here
		//Making a basic ComboBox that holds and array of strings
		//If want something special need to use a renderer
		
		
		JComboBox<String> colorList = new JComboBox<>(tempString);
		colorList.setSelectedIndex(0);
		//colorList.addActionListener(null);
		//colorList.addActionListener(colorList);
	
		
		
		
		
		colorList.addActionListener( new ActionListener(){@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent event){JComboBox<String> colorList = (JComboBox<String>) event.getSource();
		Object selected = colorList.getSelectedItem();
		System.out.println("Selected Item = " + selected);
		String command = event.getActionCommand();
		System.out.println("Action Command = " + command);
		
		if ("Blue".equals(command)) {
			System.out.println("User has selected blue from the combo box.");
		}
		else if ("White".equals(command)) {
			System.out.println("The user has selected white from the combo box.");
		}
		}
		});
	
		
		return colorList;	
		}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		
	}
	
}
