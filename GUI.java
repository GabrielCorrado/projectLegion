import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;

public class ProjectLegion {

	static int HEIGHT = 864;
	static int WIDTH = 1536;
	static int BOARDSIZE = 800;
	private JFrame frame;
	private JTextField textField_NumAgents;
	private JTextField textField_NumAgentChanges;
	private JTextField textField_AgentCloseness;
	private JTextField textField_PheromoneStrength;
	private JTextField textField_BoardSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectLegion window = new ProjectLegion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProjectLegion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Makes the top menu bar that has file and edit
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnEdit.add(mntmNew);
		frame.getContentPane().setLayout(null);
				
		//************************************************************ This makes the 800 by 800 JPanel that will be where the board goes every time it is painted. 
		/*JPanel board = new JPanel();
		board.setBackground(Color.WHITE);
		board.setBounds(10, (HEIGHT-BOARDSIZE)/8, BOARDSIZE, BOARDSIZE);
		//System.out.print((HEIGHT-BOARDSIZE)/8);
		frame.getContentPane().add(board);*/

		//This is where the tabs for the layer options go.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(818, 10, 700, 600);
		frame.getContentPane().add(tabbedPane);
		
		//************************************************************ TAB 1 ************************************************************ 
		JPanel tabLayer1 = new JPanel();
		tabLayer1.setBackground(new Color(211, 211, 211));
		tabbedPane.addTab("Layer 1", null, tabLayer1, null);
		tabLayer1.setLayout(null);
		
		//************************************************************ Change Size of the Board Button
		JButton btnChangeBoardSize = new JButton("Update Size");
		btnChangeBoardSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnChangeBoardSize.setBounds(286, 157, 125, 35);
		tabLayer1.add(btnChangeBoardSize);
		
		//************************************************************ Labels displaying information
		JLabel lblNumStartingWhite = new JLabel("Initial White Cells:");
		lblNumStartingWhite.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumStartingWhite.setBounds(10, 11, 125, 35);
		tabLayer1.add(lblNumStartingWhite);
		
		JLabel lblIntWhiteCells = new JLabel("IntWhiteCells");
		lblIntWhiteCells.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntWhiteCells.setBounds(145, 11, 125, 35);
		tabLayer1.add(lblIntWhiteCells);
		
		JLabel lblNumStartingBlack = new JLabel("Initial Black Cells:");
		lblNumStartingBlack.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumStartingBlack.setBounds(10, 79, 125, 35);
		tabLayer1.add(lblNumStartingBlack);
		
		JLabel lblIntblackCells = new JLabel("IntBlackCells");
		lblIntblackCells.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntblackCells.setBounds(145, 79, 125, 35);
		tabLayer1.add(lblIntblackCells);
		
		JLabel lblBoardSizeLayer = new JLabel("Board Size:");
		lblBoardSizeLayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoardSizeLayer.setBounds(10, 157, 125, 35);
		tabLayer1.add(lblBoardSizeLayer);
		
		//************************************************************ Text field that will trigger when button pushed, this is the value that will be the new board size
		textField_BoardSize = new JTextField();
		textField_BoardSize.setText("20");
		textField_BoardSize.setBounds(177, 164, 50, 20);
		tabLayer1.add(textField_BoardSize);
		textField_BoardSize.setColumns(10);
		
		//************************************************************ TAB 2 ************************************************************ 
		JPanel tabLayer2 = new JPanel();
		tabLayer2.setBackground(new Color(211, 211, 211));
		tabbedPane.addTab("Layer 2", null, tabLayer2, null);
		tabLayer2.setLayout(null);
		
		//************************************************************ Primary color for polarity choice comboBox where you can change it
		JLabel lblPrimaryPolarityColor = new JLabel("Primary Polarity Color");
		lblPrimaryPolarityColor.setBounds(25, 25, 125, 14);
		tabLayer2.add(lblPrimaryPolarityColor);
		
		JComboBox comboPrimary = new JComboBox();
		comboPrimary.setModel(new DefaultComboBoxModel(new String[] {"BLUE", "RED", "GREEN", "CYAN", "BLACK", "WHITE"}));
		comboPrimary.setBounds(25, 50, 125, 22);
		String[] tempString = {"BLUE","RED","GREEN","CYAN","WHITE","BLACK"};
		tabLayer2.add(comboPrimary);
		
		//************************************************************ Secondary color for polarity choice comboBox where you can change it
		JLabel lblSecondaryPolarityColor = new JLabel("Secondary Polarity Color");
		lblSecondaryPolarityColor.setBounds(238, 25, 150, 14);
		tabLayer2.add(lblSecondaryPolarityColor);
		
		JComboBox comboSecondary = new JComboBox();
		comboSecondary.setModel(new DefaultComboBoxModel(new String[] {"RED", "BLUE", "GREEN", "CYAN", "BLACK", "WHITE"}));
		comboSecondary.setBounds(238, 50, 125, 22);
		tabLayer2.add(comboSecondary);
		
		//************************************************************ Tertiary color comboBox if we want it.
		JLabel lblTertiaryPolarityColor = new JLabel("Tertiary Polarity Color");
		lblTertiaryPolarityColor.setBounds(450, 25, 125, 14);
		tabLayer2.add(lblTertiaryPolarityColor);
		
		JComboBox comboTertiary = new JComboBox();
		comboTertiary.setModel(new DefaultComboBoxModel(new String[] {"null", "BLUE", "RED", "GREEN", "CYAN", "BLACK", "WHITE"}));
		comboTertiary.setBounds(450, 50, 125, 22);
		tabLayer2.add(comboTertiary);
		
		//************************************************************ Sets what the polarity ratios should be for the two colors.
		JLabel lblReginalStability = new JLabel("Reginal Stability");
		lblReginalStability.setBounds(25, 200, 125, 14);
		tabLayer2.add(lblReginalStability);
		
		JComboBox reginalStablility = new JComboBox();
		reginalStablility.setModel(new DefaultComboBoxModel(new String[] {"50/50", "60/40", "70/30", "80/20", "90/10", "100/0"}));
		reginalStablility.setBounds(25, 225, 125, 22);
		tabLayer2.add(reginalStablility);
		
		//************************************************************ Choose the pattern which you want to be applied to the grid.  Might make the grid to be able to be entered as a value of MOD 2 or MOD 3 if we actually want to implement it.
		JRadioButton patternButton_0_0 = new JRadioButton("0_0");
		patternButton_0_0.setSelected(true);
		patternButton_0_0.setBounds(238, 300, 50, 50);
		tabLayer2.add(patternButton_0_0);
		
		JRadioButton patternButton_0_1 = new JRadioButton("0_1");
		patternButton_0_1.setBounds(288, 301, 50, 50);
		tabLayer2.add(patternButton_0_1);
		
		JRadioButton patternButton_0_2 = new JRadioButton("0_2");
		patternButton_0_2.setSelected(true);
		patternButton_0_2.setBounds(338, 301, 50, 50);
		tabLayer2.add(patternButton_0_2);
		
		JRadioButton patternButton_1_0 = new JRadioButton("1_0");
		patternButton_1_0.setBounds(238, 350, 50, 50);
		tabLayer2.add(patternButton_1_0);
		
		JRadioButton patternButton_1_1 = new JRadioButton("1_1");
		patternButton_1_1.setSelected(true);
		patternButton_1_1.setBounds(288, 350, 50, 50);
		tabLayer2.add(patternButton_1_1);
		
		JRadioButton patternButton_1_2 = new JRadioButton("1_2");
		patternButton_1_2.setBounds(338, 350, 50, 50);
		tabLayer2.add(patternButton_1_2);
		
		JRadioButton patternButton_2_0 = new JRadioButton("2_0");
		patternButton_2_0.setSelected(true);
		patternButton_2_0.setBounds(238, 400, 50, 50);
		tabLayer2.add(patternButton_2_0);
		
		JRadioButton patternButton_2_1 = new JRadioButton("2_1");
		patternButton_2_1.setBounds(288, 400, 50, 50);
		tabLayer2.add(patternButton_2_1);
		
		JRadioButton patternButton_2_2 = new JRadioButton("2_2");
		patternButton_2_2.setSelected(true);
		patternButton_2_2.setBounds(338, 400, 50, 50);
		tabLayer2.add(patternButton_2_2);
		
		//************************************************************ Updates the polarity to what is entered on the radio buttons
		JLabel lblPolarity = new JLabel("Polarity");
		lblPolarity.setHorizontalAlignment(SwingConstants.CENTER);
		lblPolarity.setBounds(238, 200, 150, 14);
		tabLayer2.add(lblPolarity);
		
		JButton btnUpdatePolarity = new JButton("Update Polarity");
		btnUpdatePolarity.setBounds(238, 225, 150, 22);
		tabLayer2.add(btnUpdatePolarity);
		
		//************************************************************ TAB 3 ************************************************************ 
		JPanel tabLayer3 = new JPanel();
		tabLayer3.setBackground(new Color(211, 211, 211));
		tabbedPane.addTab("Layer 3", null, tabLayer3, null);
		tabLayer3.setLayout(null);
		
		//************************************************************ User can set the number of agents
		JLabel lblNumberOfAgents = new JLabel("Number of Agents:");
		lblNumberOfAgents.setBounds(10, 25, 150, 14);
		tabLayer3.add(lblNumberOfAgents);
		
		textField_NumAgents = new JTextField();
		textField_NumAgents.setText("4");
		textField_NumAgents.setBounds(150, 23, 40, 20);
		tabLayer3.add(textField_NumAgents);
		textField_NumAgents.setColumns(10);
		
		JButton btnUpdateAgents = new JButton("Update Agents");
		btnUpdateAgents.setBounds(215, 23, 150, 20);
		tabLayer3.add(btnUpdateAgents);
		
		//************************************************************ User can change the color of the agents
		JLabel lblAgentsColor = new JLabel("Agents Color:");
		lblAgentsColor.setBounds(440, 25, 125, 14);
		tabLayer3.add(lblAgentsColor);
		
		JComboBox comboBox_AgentColor = new JComboBox();
		comboBox_AgentColor.setBounds(568, 23, 100, 20);
		tabLayer3.add(comboBox_AgentColor);
		comboBox_AgentColor.setModel(new DefaultComboBoxModel(new String[] {"GREEN", "YELLOW", "ORANGE", "MAGENTA", "BLUE", "RED", "WHITE", "BLACK"}));
		
		//************************************************************ User can select how many changes the agent can make
		JLabel lblNumberOfChanges = new JLabel("Number of Changes:");
		lblNumberOfChanges.setBounds(10, 58, 150, 14);
		tabLayer3.add(lblNumberOfChanges);
		
		textField_NumAgentChanges = new JTextField();
		textField_NumAgentChanges.setText("-1");
		textField_NumAgentChanges.setBounds(150, 56, 40, 20);
		tabLayer3.add(textField_NumAgentChanges);
		textField_NumAgentChanges.setColumns(10);
		
		JButton btnUpdateChanges = new JButton("Update Changes");
		btnUpdateChanges.setBounds(215, 56, 150, 20);
		tabLayer3.add(btnUpdateChanges);
		
		//************************************************************ User can choose how close an agent can get to another. 0 implies that many spaces between, thus they could overlap.
		JLabel lblAgentCloseness = new JLabel("Agent Closeness:");
		lblAgentCloseness.setBounds(10, 92, 150, 14);
		tabLayer3.add(lblAgentCloseness);
		
		textField_AgentCloseness = new JTextField();
		textField_AgentCloseness.setText("0");
		textField_AgentCloseness.setBounds(150, 90, 40, 20);
		tabLayer3.add(textField_AgentCloseness);
		textField_AgentCloseness.setColumns(10);
		
		JButton btnUpdateCloseness = new JButton("Update Closeness");
		btnUpdateCloseness.setBounds(215, 90, 150, 20);
		tabLayer3.add(btnUpdateCloseness);
		
		//************************************************************ User changes the color of the pheromone trails on the board.
		JLabel lblPharamoneTrailColor = new JLabel("Pheromone Color:");
		lblPharamoneTrailColor.setBounds(440, 58, 125, 14);
		tabLayer3.add(lblPharamoneTrailColor);
		
		JComboBox comboBox_PheromoneColor = new JComboBox();
		comboBox_PheromoneColor.setModel(new DefaultComboBoxModel(new String[] {"WHITE", "GREEN", "YELLOW", "ORANGE", "MAGENTA", "BLUE", "RED", "BLACK"}));
		comboBox_PheromoneColor.setBounds(568, 56, 100, 20);
		tabLayer3.add(comboBox_PheromoneColor);
		
		//************************************************************ User can set how strongly the agents should follow the swarm.
		JLabel lblPheromoneStrength = new JLabel("Pheromone Strength:");
		lblPheromoneStrength.setBounds(10, 125, 150, 14);
		tabLayer3.add(lblPheromoneStrength);
		
		textField_PheromoneStrength = new JTextField();
		textField_PheromoneStrength.setText("1");
		textField_PheromoneStrength.setColumns(10);
		textField_PheromoneStrength.setBounds(150, 123, 40, 20);
		tabLayer3.add(textField_PheromoneStrength);
		
		JButton btnUpdatePStrength = new JButton("Update P Strength");
		btnUpdatePStrength.setBounds(215, 123, 150, 18);
		tabLayer3.add(btnUpdatePStrength);
		
		JToggleButton tglbtnViewAgents = new JToggleButton("View Agents");
		tglbtnViewAgents.setBounds(10, 495, 187, 69);
		tabLayer3.add(tglbtnViewAgents);
		
		//************************************************************ Global Zone Buttons + Slider ************************************************************ 
		//************************************************************ Just shows information
		JLabel lblBoardSizeGlobal = new JLabel("Board Size:");
		lblBoardSizeGlobal.setBounds(820, 621, 74, 14);
		frame.getContentPane().add(lblBoardSizeGlobal);
		
		JLabel lblBoardSizeInt = new JLabel("Int");
		lblBoardSizeInt.setBounds(910, 621, 46, 14);
		frame.getContentPane().add(lblBoardSizeInt);
		
		JLabel lblSwarmCount = new JLabel("Swarm Count:");
		lblSwarmCount.setBounds(1014, 621, 100, 14);
		frame.getContentPane().add(lblSwarmCount);
		
		JLabel lblSwarmCountInt = new JLabel("Int");
		lblSwarmCountInt.setBounds(1109, 621, 46, 14);
		frame.getContentPane().add(lblSwarmCountInt);
		
		JLabel lblSwarmRate = new JLabel("Swarm Rate:");
		lblSwarmRate.setBounds(820, 646, 90, 14);
		frame.getContentPane().add(lblSwarmRate);
		
		//************************************************************ Slider for the user to change how fast the board will step
		JSlider sliderSwarmSpeed = new JSlider();
		sliderSwarmSpeed.setBounds(953, 646, 450, 24);
		frame.getContentPane().add(sliderSwarmSpeed);
		
		JLabel lblSlow = new JLabel("Slow");
		lblSlow.setBounds(910, 646, 46, 14);
		frame.getContentPane().add(lblSlow);
		
		JLabel lblFast = new JLabel("Fast");
		lblFast.setBounds(1417, 646, 46, 14);
		frame.getContentPane().add(lblFast);
		
		//************************************************************ Buttons that start stop and do other things that they are clearly labeled for.
		JButton btnStopSwarm = new JButton("Stop Swarm");
		btnStopSwarm.setBackground(new Color(255, 51, 51));
		btnStopSwarm.setBounds(1030, 726, 125, 23);
		frame.getContentPane().add(btnStopSwarm);
		
		JButton btnStartSwarm = new JButton("Start Swarm");
		btnStartSwarm.setBackground(new Color(0, 255, 0));
		btnStartSwarm.setBounds(895, 726, 125, 23);
		frame.getContentPane().add(btnStartSwarm);
		
		JButton btnRecord = new JButton("Record");
		btnRecord.setBackground(new Color(102, 255, 153));
		btnRecord.setBounds(895, 767, 125, 23);
		frame.getContentPane().add(btnRecord);
		
		JButton btnStopRecord = new JButton("Stop Record");
		btnStopRecord.setBackground(new Color(255, 102, 102));
		btnStopRecord.setBounds(1030, 767, 125, 23);
		frame.getContentPane().add(btnStopRecord);
		
		JButton btnSaveRec = new JButton("Save Rec.");
		btnSaveRec.setBackground(new Color(204, 51, 255));
		btnSaveRec.setBounds(1203, 767, 125, 23);
		frame.getContentPane().add(btnSaveRec);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.setBackground(new Color(51, 204, 255));
		btnRestart.setBounds(1203, 726, 125, 23);
		frame.getContentPane().add(btnRestart);
		
		JButton btnNewRandomSwarm = new JButton("New Swarm");
		btnNewRandomSwarm.setBackground(new Color(51, 102, 255));
		btnNewRandomSwarm.setBounds(1338, 726, 125, 23);
		frame.getContentPane().add(btnNewRandomSwarm);
		
		JButton btnNewScreenSave = new JButton("Screen Shot");
		btnNewScreenSave.setBackground(new Color(204, 51, 255));
		btnNewScreenSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewScreenSave.setBounds(1338, 767, 125, 23);
		frame.getContentPane().add(btnNewScreenSave);
		
		JLabel lblSlowCycless = new JLabel("1/2 Cycle/s");
		lblSlowCycless.setBounds(910, 671, 100, 14);
		frame.getContentPane().add(lblSlowCycless);
		
		JLabel lblFastCycless = new JLabel("5 Cycles/s");
		lblFastCycless.setBounds(1417, 671, 100, 14);
		frame.getContentPane().add(lblFastCycless);
		
		JButton btnInitializeBoard = new JButton("Initialize Board");
		btnInitializeBoard.addMouseListener(new MouseAdapter() {
			//************************************************************This code works with board, cell, gencell classes in order to have a basic board print on the JFrame UI
			/*@Override
			public void mouseClicked(MouseEvent arg0) {
				Board board = new Board(800,800,10,0);
				board.setBackground(Color.WHITE);
				board.setBounds(10, (HEIGHT-BOARDSIZE)/8, BOARDSIZE, BOARDSIZE);
				frame.getContentPane().add(board);
				board.Step(); 
			}*/
		});
		btnInitializeBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnInitializeBoard.setBounds(1203, 681, 125, 24);
		frame.getContentPane().add(btnInitializeBoard);
		
		
	}
}
