package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class NewBoardWindow extends JFrame {

	private JPanel contentPane;
	private JTextField NewBoardSize;
	private JTextField txtNewswarmsize;
	private int size, numAgents;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public NewBoardWindow(JFrame frame) {
		System.out.println("test");
		setTitle("New Board Size");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewBoardSize = new JLabel("New Board Size");
		lblNewBoardSize.setBounds(95, 48, 100, 20);
		contentPane.add(lblNewBoardSize);
		
		JLabel lblNewSwarmSize = new JLabel("New Swarm Size");
		lblNewSwarmSize.setBounds(95, 102, 100, 20);
		contentPane.add(lblNewSwarmSize);
		
		NewBoardSize = new JTextField("10",5);
		NewBoardSize.setBounds(292, 48, 40, 20);
		contentPane.add(NewBoardSize);
		NewBoardSize.setColumns(10);
		
		
		txtNewswarmsize = new JTextField("10");
		txtNewswarmsize.setBounds(292, 102, 40, 20);
		contentPane.add(txtNewswarmsize);
		txtNewswarmsize.setColumns(10);
		
		
		//This button will close the JFrame and set the request Board class to create a new board that will be shown in the Main GUI
		JButton btnMakeNewBoard = new JButton("Make New Board");
		btnMakeNewBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size= Integer.parseInt(NewBoardSize.getText());
				numAgents = Integer.parseInt(txtNewswarmsize.getText());
				MakeNewBoard(frame);
				dispose();
			}
		});
		btnMakeNewBoard.setBounds(95, 184, 237, 49);
		contentPane.add(btnMakeNewBoard);
	}
	
	//This will be the code that will make a new board and set the variables in the Priamary GUI to the selected ones in this.
	protected void MakeNewBoard(JFrame frame){
		if(GUI.board != null)
		{
		frame.remove(GUI.board);
		}
		Board board = new Board(800,800,size,0,numAgents);
		board.setBackground(Color.WHITE);
		board.setBounds(10, 10, 800, 800);
		//displayPanel.add();
		frame.getContentPane().add(board);
		GUI.board = board;
	}
	
}
