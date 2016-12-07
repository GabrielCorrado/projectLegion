package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cells.Cell;
import strategies.AbstractStrategy;
import strategies.AllBlack;
import strategies.CheckerBoard;
import strategies.Lines;

/*
 * Authors: Gabriel, Zak
 * Description: Gui created through Auto-generated code. Most of the code is simple creation, with functionality within certain labels and buttons.
 */
public class GUI {

	public static final int HEIGHT = 864;
	public static final int WIDTH = 1536;
	public static final int MAXBOARDSIZE = 800;//pixel size of board
	
	public JFrame frmProjectLegion;//main frame
	private JTextField textField_NumAgents;
	private JTextField textField_NumAgentChanges;
	private JTextField textField_AgentCloseness;
	private JTextField textField_PheromoneStrength;
	private JTextField textField_BoardSize;
	
	public static JLabel lblBoardSizeInt = new JLabel(); //updates BoardSize Label
	public static JLabel lblSwarmCountInt = new JLabel(); //updates SwarmCount Label
	public static JLabel lblIntWhiteCells = new JLabel(); //updates InitWhiteCells
	public static JLabel lblIntBlackCells = new JLabel(); //updates InitBlackCells
	public static JLabel lblCurrWhiteCells = new JLabel(); //updates CurrentWhiteCells
	public static JLabel lblCurrBlackCells = new JLabel(); //updates CurrentBlackCells
	
	public static int layer2Draw = 1;//which cell array in board to display
	public static Board board;//board to be drawn
	private boolean timerStarted = true;//timer or agent step
	public static Color polarity1 = Color.RED;//color1 of board.cells2
	public static Color polarity2 = Color.BLUE;//color2 of board.cells2
	public static int initBoardSize, initAgentCount;
	public static boolean attractOrRepel = true;
	public static Color agentColor = Color.GREEN;
	public static boolean whetherAgentsVisible = true;
	public static AbstractStrategy goalStrategy = new CheckerBoard();
	public static int agentSliderRate;
	public static boolean wrap = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmProjectLegion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Color getPolarity1() {
		return polarity1;
	}

	public static void setPolarity1(Color polarity1) {
		GUI.polarity1 = polarity1;
	}

	public static Color getPolarity2() {
		return polarity2;
	}

	public static void setPolarity2(Color polarity2) {
		GUI.polarity2 = polarity2;
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProjectLegion = new JFrame();
		frmProjectLegion.setTitle("Project Legion");
		frmProjectLegion.setBounds(100, 100, WIDTH, HEIGHT);
		frmProjectLegion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Makes the top menu bar that has file and edit
		JMenuBar menuBar = new JMenuBar();
		frmProjectLegion.setJMenuBar(menuBar);
		
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
		frmProjectLegion.getContentPane().setLayout(null);
				
		//************************************************************ This makes the 800 by 800 JPanel that will be where the board goes every time it is painted. 
		JPanel boardInGUI = new JPanel();
		JFrame frame = new JFrame();
		boardInGUI.setBackground(Color.WHITE);
		boardInGUI.setBounds(10, (HEIGHT-MAXBOARDSIZE)/8, MAXBOARDSIZE, MAXBOARDSIZE);
		//System.out.print((HEIGHT-BOARDSIZE)/8);
		frame.getContentPane().add(boardInGUI);

		//This is where the tabs for the layer options go.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(818, 10, 700, 600);
		//used to change whether board.cells or board.cells2 is shown in board when the tab selected is changed 
		ChangeListener changeListener = new ChangeListener(){
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				layer2Draw = index + 1;
			}
		};
		tabbedPane.addChangeListener(changeListener);
		frmProjectLegion.getContentPane().add(tabbedPane);
		
		//************************************************************ TAB 1 ************************************************************ 
		JPanel tabLayer1 = new JPanel();
		tabLayer1.setBackground(new Color(211, 211, 211));
		tabbedPane.addTab("Layer 1", null, tabLayer1, null);
		tabLayer1.setLayout(null);
		
		//************************************************************ Change Size of the Board Button
		JButton btnChangeBoardSize = new JButton("Update Size");
		btnChangeBoardSize.setForeground(Color.LIGHT_GRAY);
		btnChangeBoardSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Button gains actions here
				//board.size = Integer.parseInt(textField_BoardSize.getText());
			}
		});
		btnChangeBoardSize.setBounds(286, 157, 125, 35);
		tabLayer1.add(btnChangeBoardSize);
		
		//************************************************************ Labels displaying information
		JLabel lblNumStartingWhite = new JLabel("Initial White Cells:");
		lblNumStartingWhite.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumStartingWhite.setBounds(10, 11, 125, 35);
		tabLayer1.add(lblNumStartingWhite);
		
		
		lblIntWhiteCells.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntWhiteCells.setBounds(145, 11, 125, 35);
		tabLayer1.add(lblIntWhiteCells);
		
		JLabel lblNumStartingBlack = new JLabel("Initial Black Cells:");
		lblNumStartingBlack.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumStartingBlack.setBounds(10, 79, 125, 35);
		tabLayer1.add(lblNumStartingBlack);
		
		
		lblIntBlackCells.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntBlackCells.setBounds(145, 79, 125, 35);
		tabLayer1.add(lblIntBlackCells);
		
		JLabel lblBoardSizeLayer = new JLabel("Board Size:");
		lblBoardSizeLayer.setForeground(Color.lightGray);
		lblBoardSizeLayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoardSizeLayer.setBounds(10, 157, 125, 35);
		tabLayer1.add(lblBoardSizeLayer);
		
		JLabel lblNumCurrentWhite = new JLabel("Current White Cells:");
		lblNumCurrentWhite.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumCurrentWhite.setBounds(10, 300, 125, 35);
		tabLayer1.add(lblNumCurrentWhite);
		
		lblCurrWhiteCells.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrWhiteCells.setBounds(145, 300, 125, 35);
		tabLayer1.add(lblCurrWhiteCells);
		
		JLabel lblNumCurrentBlack = new JLabel("Current Black Cells:");
		lblNumCurrentBlack.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumCurrentBlack.setBounds(10, 368, 125, 35);
		tabLayer1.add(lblNumCurrentBlack);
		
		lblCurrBlackCells.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrBlackCells.setBounds(145, 368, 125, 35);
		tabLayer1.add(lblCurrBlackCells);
		
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
		//update the first color of cells in board.cells2
		JComboBox comboPrimary = new JComboBox();
		comboPrimary.setModel(new DefaultComboBoxModel(new String[] {"RED", "BLUE", "GREEN", "CYAN", "YELLOW"}));
		Color[] primaryColorList = new Color[] {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW};
		comboPrimary.setBounds(25, 50, 125, 22);
		comboPrimary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){

				JComboBox src = (JComboBox) e.getSource();
				if(polarity2 == primaryColorList[src.getSelectedIndex()])
				{
					int temp = Arrays.asList(primaryColorList).indexOf(polarity1);
					comboPrimary.setSelectedIndex(temp);
				}
				else
				{
					polarity1 = primaryColorList[src.getSelectedIndex()];
					board.updateNewPolarityColor1(polarity1);
				}
				if(!timerStarted)
				{
					board.repaint();
				}

			}
		});
		tabLayer2.add(comboPrimary);

		//************************************************************ Secondary color for polarity choice comboBox where you can change it
		JLabel lblSecondaryPolarityColor = new JLabel("Secondary Polarity Color");
		lblSecondaryPolarityColor.setBounds(238, 25, 150, 14);
		tabLayer2.add(lblSecondaryPolarityColor);
		//update the second color of cells in board.cells2
		JComboBox comboSecondary = new JComboBox();
		comboSecondary.setModel(new DefaultComboBoxModel(new String[] {"BLUE", "RED", "GREEN", "CYAN"}));
		Color[] secondaryColorList = new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN};
		comboSecondary.setBounds(238, 50, 125, 22);
		comboSecondary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				JComboBox src = (JComboBox) e.getSource();
				if(polarity1 == secondaryColorList[src.getSelectedIndex()])
				{
					int temp = Arrays.asList(secondaryColorList).indexOf(polarity2);
					comboSecondary.setSelectedIndex(temp);
				}
				else
				{
					polarity2 =secondaryColorList[src.getSelectedIndex()];
					board.updateNewPolarityColor2(polarity2);
				}
				if (!timerStarted)
				{
					board.repaint();
				}

			}
		});
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
		lblReginalStability.setForeground(Color.LIGHT_GRAY);
		lblReginalStability.setBounds(25, 200, 125, 14);
		tabLayer2.add(lblReginalStability);
		
		JComboBox reginalStablility = new JComboBox();
		reginalStablility.setModel(new DefaultComboBoxModel(new String[] {"50/50", "60/40", "70/30", "80/20", "90/10", "100/0"}));
		reginalStablility.setBounds(25, 225, 125, 22);
		tabLayer2.add(reginalStablility);
		
		//************************************************************ Updates the polarity to what is entered on the radio buttons
		JLabel lblPolarity = new JLabel("Goal");
		lblPolarity.setHorizontalAlignment(SwingConstants.CENTER);
		lblPolarity.setBounds(238, 200, 150, 14);
		tabLayer2.add(lblPolarity);
		
		JComboBox comboGoalStrategy = new JComboBox();
		comboGoalStrategy.setModel(new DefaultComboBoxModel(new String[] {"CheckerBoard", "Lines", "All Black"}));
		AllBlack allBlack = new AllBlack();
		CheckerBoard checkerBoard = new CheckerBoard();
		Lines lines = new Lines();
		AbstractStrategy[] goalStrategyList = new AbstractStrategy[]{checkerBoard, lines, allBlack};
		comboGoalStrategy.setBounds(238, 225, 150, 22);
		comboGoalStrategy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				JComboBox src = (JComboBox) e.getSource();
				goalStrategy = goalStrategyList[src.getSelectedIndex()];
				board.updateGoalStrategy(goalStrategy);
				whetherAgentsVisible = true;
			}
		});
		tabLayer2.add(comboGoalStrategy);
		//************************************************************ TAB 3 ************************************************************ 
		JPanel tabLayer3 = new JPanel();
		tabLayer3.setBackground(new Color(211, 211, 211));
		tabbedPane.addTab("Layer 3", null, tabLayer3, null);
		tabLayer3.setLayout(null);
		
		//************************************************************ User can set the number of agents
		JLabel lblNumberOfAgents = new JLabel("Number of Agents:");
		lblNumberOfAgents.setForeground(Color.LIGHT_GRAY);
		lblNumberOfAgents.setBounds(10, 25, 150, 14);
		tabLayer3.add(lblNumberOfAgents);
		
		textField_NumAgents = new JTextField();
		textField_NumAgents.setText("4");
		textField_NumAgents.setBounds(150, 23, 40, 20);
		tabLayer3.add(textField_NumAgents);
		textField_NumAgents.setColumns(10);
		
		JButton btnUpdateAgents = new JButton("Update Agents");
		btnUpdateAgents.setForeground(Color.LIGHT_GRAY);
		btnUpdateAgents.setBounds(215, 23, 150, 20);
		tabLayer3.add(btnUpdateAgents);
		
		//************************************************************ User can change the color of the agents
		JLabel lblAgentsColor = new JLabel("Agents Color:");
		lblAgentsColor.setBounds(440, 25, 125, 14);
		tabLayer3.add(lblAgentsColor);
		//update the color of agents in board.agents[]
		JComboBox comboBox_AgentColor = new JComboBox();
		comboBox_AgentColor.setModel(new DefaultComboBoxModel(new String[] {"GREEN", "YELLOW", "ORANGE", "MAGENTA", "BLUE", "RED", "WHITE", "BLACK", "CYAN"}));
		Color[] agentColorList = new Color[]{Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA, Color.BLUE, Color.RED, Color.WHITE, Color.BLACK, Color.CYAN};
		comboBox_AgentColor.setBounds(568, 23, 100, 20);
		comboBox_AgentColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){

				JComboBox src = (JComboBox) e.getSource();
				agentColor =agentColorList[src.getSelectedIndex()];
				board.updateAgentColor(agentColor);
				whetherAgentsVisible = true;
				board.repaint();

				if (!timerStarted)
				{
					board.repaint();
				}
			}
		});
		tabLayer3.add(comboBox_AgentColor);
		//************************************************************ User can select how many changes the agent can make
		JLabel lblNumberOfChanges = new JLabel("Number of Changes:");
		lblNumberOfChanges.setForeground(Color.LIGHT_GRAY);
		lblNumberOfChanges.setBounds(10, 58, 150, 14);
		tabLayer3.add(lblNumberOfChanges);
		
		textField_NumAgentChanges = new JTextField();
		textField_NumAgentChanges.setText("-1");
		textField_NumAgentChanges.setBounds(150, 56, 40, 20);
		tabLayer3.add(textField_NumAgentChanges);
		textField_NumAgentChanges.setColumns(10);
		
		JButton btnUpdateChanges = new JButton("Update Changes");
		btnUpdateChanges.setForeground(Color.LIGHT_GRAY);
		btnUpdateChanges.setBounds(215, 56, 150, 20);
		tabLayer3.add(btnUpdateChanges);
		
		//************************************************************ User can choose how close an agent can get to another. 0 implies that many spaces between, thus they could overlap.
		JLabel lblAgentCloseness = new JLabel("Agent Closeness:");
		lblAgentCloseness.setForeground(Color.LIGHT_GRAY);
		lblAgentCloseness.setBounds(10, 92, 150, 14);
		tabLayer3.add(lblAgentCloseness);
		
		textField_AgentCloseness = new JTextField();
		textField_AgentCloseness.setText("0");
		textField_AgentCloseness.setBounds(150, 90, 40, 20);
		tabLayer3.add(textField_AgentCloseness);
		textField_AgentCloseness.setColumns(10);
		
		JButton btnUpdateCloseness = new JButton("Update Closeness");
		btnUpdateCloseness.setForeground(Color.LIGHT_GRAY);
		btnUpdateCloseness.setBounds(215, 90, 150, 20);
		tabLayer3.add(btnUpdateCloseness);
		
		//************************************************************ User changes the color of the pheromone trails on the board.
		JLabel lblPharamoneTrailColor = new JLabel("Pheromone Color:");
		lblPharamoneTrailColor.setForeground(Color.LIGHT_GRAY);
		lblPharamoneTrailColor.setBounds(440, 58, 125, 14);
		tabLayer3.add(lblPharamoneTrailColor);
		
		JComboBox comboBox_PheromoneColor = new JComboBox();
		comboBox_PheromoneColor.setModel(new DefaultComboBoxModel(new String[] {"WHITE", "GREEN", "YELLOW", "ORANGE", "MAGENTA", "BLUE", "RED", "BLACK"}));
		comboBox_PheromoneColor.setBounds(568, 56, 100, 20);
		tabLayer3.add(comboBox_PheromoneColor);
		
		//************************************************************ User can set how strongly the agents should follow the swarm.
		JLabel lblPheromoneStrength = new JLabel("Pheromone Strength:");
		lblPheromoneStrength.setForeground(Color.LIGHT_GRAY);
		lblPheromoneStrength.setBounds(10, 125, 150, 14);
		tabLayer3.add(lblPheromoneStrength);
		
		textField_PheromoneStrength = new JTextField();
		textField_PheromoneStrength.setText("1");
		textField_PheromoneStrength.setColumns(10);
		textField_PheromoneStrength.setBounds(150, 123, 40, 20);
		tabLayer3.add(textField_PheromoneStrength);
		
		JButton btnUpdatePStrength = new JButton("Update P Strength");
		btnUpdatePStrength.setForeground(Color.LIGHT_GRAY);
		btnUpdatePStrength.setBounds(215, 123, 150, 18);
		tabLayer3.add(btnUpdatePStrength);
		
		JToggleButton tglbtnViewAgents = new JToggleButton("View Agents");
		tglbtnViewAgents.setSelected(true);
		tglbtnViewAgents.setBounds(10, 495, 187, 69);
		tglbtnViewAgents.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				whetherAgentsVisible = !whetherAgentsVisible;
				if (!timerStarted) {
					board.repaint();
				}
			}
		});
		tabLayer3.add(tglbtnViewAgents);
		
		JButton btnSetPhrmnTrail = new JButton("Set Phrmn Trail");
		btnSetPhrmnTrail.setForeground(Color.LIGHT_GRAY);
		btnSetPhrmnTrail.setBounds(10, 272, 187, 30);
		tabLayer3.add(btnSetPhrmnTrail);
		
		JButton btnRemovePhrmnTrail = new JButton("Remove Phrmn Trail");
		btnRemovePhrmnTrail.setForeground(Color.LIGHT_GRAY);
		btnRemovePhrmnTrail.setBounds(10, 316, 187, 30);
		tabLayer3.add(btnRemovePhrmnTrail);
		
		JButton btnSetPhrmnZone = new JButton("Set Phrmn Zone");
		btnSetPhrmnZone.setForeground(Color.LIGHT_GRAY);
		btnSetPhrmnZone.setBounds(215, 272, 187, 30);
		tabLayer3.add(btnSetPhrmnZone);
		
		JButton btnRemovePhrmnZone = new JButton("Remove Phrmn Zone");
		btnRemovePhrmnZone.setForeground(Color.LIGHT_GRAY);
		btnRemovePhrmnZone.setBounds(215, 316, 187, 30);
		tabLayer3.add(btnRemovePhrmnZone);
		
		JToggleButton tglbtnAttractOrRepel = new JToggleButton("Attract");
		tglbtnAttractOrRepel.setSelected(true);
		tglbtnAttractOrRepel.setBounds(215, 495, 187, 69);
		tglbtnAttractOrRepel.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				attractOrRepel = !attractOrRepel;
				if (attractOrRepel) {
					tglbtnAttractOrRepel.setText("Attract");
				} else {
					tglbtnAttractOrRepel.setText("Repel");
				}
			}
		});
		tabLayer3.add(tglbtnAttractOrRepel);
		
		JToggleButton tglbtnWrapAgents = new JToggleButton("Bounce Agents");
		tglbtnWrapAgents.setSelected(true);
		tglbtnWrapAgents.setBounds(420, 495, 187, 69);
		tglbtnWrapAgents.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				wrap = !wrap;
				board.setWrap(wrap);
				if (wrap) {
					tglbtnWrapAgents.setText("Wrap Agents");
				} else {
					tglbtnWrapAgents.setText("Bounce Agents");
				}
			}
		});
		tabLayer3.add(tglbtnWrapAgents);
		
		
		//************************************************************ Global Zone Buttons + Slider ************************************************************ 
		//************************************************************ Just shows information
		JLabel lblBoardSizeGlobal = new JLabel("Board Size:");
		lblBoardSizeGlobal.setBounds(820, 621, 74, 14);
		frmProjectLegion.getContentPane().add(lblBoardSizeGlobal);
		
		
		lblBoardSizeInt.setBounds(910, 621, 46, 14);
		frmProjectLegion.getContentPane().add(lblBoardSizeInt);
		
		JLabel lblSwarmCount = new JLabel("Swarm Count:");
		lblSwarmCount.setBounds(1014, 621, 100, 14);
		frmProjectLegion.getContentPane().add(lblSwarmCount);
		
		
		lblSwarmCountInt.setBounds(1109, 621, 46, 14);
		frmProjectLegion.getContentPane().add(lblSwarmCountInt);
		
		JLabel lblSwarmRate = new JLabel("Swarm Rate:");
		lblSwarmRate.setBounds(820, 646, 90, 14);
		frmProjectLegion.getContentPane().add(lblSwarmRate);
		
		//************************************************************ Slider for the user to change how fast the board will step
		JSlider sliderSwarmSpeed = new JSlider(0,100,50);
		sliderSwarmSpeed.setBounds(953, 646, 450, 30);
		sliderSwarmSpeed.setMajorTickSpacing( 5 );
		sliderSwarmSpeed.setPaintLabels( true );
		//slider to change the speed of the agents in board.agents[]
		sliderSwarmSpeed.addChangeListener(new ChangeListener() {
			@Override public void stateChanged(ChangeEvent e) {
				JSlider src = (JSlider) e.getSource();
				agentSliderRate = src.getValue();
				board.setAgentRate(agentSliderRate);
			}
		});
		frmProjectLegion.getContentPane().add(sliderSwarmSpeed);

		JLabel lblSlow = new JLabel("Slow");
		lblSlow.setBounds(910, 646, 46, 14);
		frmProjectLegion.getContentPane().add(lblSlow);

		JLabel lblFast = new JLabel("Fast");
		lblFast.setBounds(1417, 646, 46, 14);
		frmProjectLegion.getContentPane().add(lblFast);
		
		//************************************************************ Buttons that start stop and do other things that they are clearly labeled for.
		//button to freeze swarm agents
		JButton btnStopSwarm = new JButton("Stop Swarm");
		btnStopSwarm.setBackground(new Color(255, 51, 51));
		btnStopSwarm.setBounds(1030, 726, 125, 23);
		btnStopSwarm.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				board.t.cancel();
				timerStarted = false;
			}
		});
		frmProjectLegion.getContentPane().add(btnStopSwarm);
		//button used to unfreeze swarm agents
		JButton btnStartSwarm = new JButton("Start Swarm");
		btnStartSwarm.setBackground(new Color(0, 255, 0));
		btnStartSwarm.setBounds(895, 726, 125, 23);
		btnStartSwarm.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				if(!timerStarted)
				{
					board.StartTimer();
					timerStarted = true;
				}
			}
		});
		frmProjectLegion.getContentPane().add(btnStartSwarm);

		JButton btnRecord = new JButton("Record");
		btnRecord.setForeground(Color.LIGHT_GRAY);
		btnRecord.setBackground(new Color(102, 255, 153));
		btnRecord.setBounds(895, 767, 125, 23);
		frmProjectLegion.getContentPane().add(btnRecord);

		JButton btnStopRecord = new JButton("Stop Record");
		btnStopRecord.setForeground(Color.LIGHT_GRAY);
		btnStopRecord.setBackground(new Color(255, 102, 102));
		btnStopRecord.setBounds(1030, 767, 125, 23);
		frmProjectLegion.getContentPane().add(btnStopRecord);
		
		JButton btnSaveRec = new JButton("Save Rec.");
		btnSaveRec.setForeground(Color.LIGHT_GRAY);
		btnSaveRec.setBackground(new Color(204, 51, 255));
		btnSaveRec.setBounds(1203, 767, 125, 23);
		frmProjectLegion.getContentPane().add(btnSaveRec);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.setForeground(Color.LIGHT_GRAY);
		btnRestart.setBackground(new Color(51, 204, 255));
		btnRestart.setBounds(1203, 726, 125, 23);
		frmProjectLegion.getContentPane().add(btnRestart);
		
		
		
		//************************************************************ This code will open a new JFrame that will ask the user the new dimentions for the new board.
		JButton btnNewScreenSave = new JButton("Screen Shot");
		btnNewScreenSave.setForeground(Color.LIGHT_GRAY);
		btnNewScreenSave.setBackground(new Color(204, 51, 255));
		btnNewScreenSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewScreenSave.setBounds(1338, 767, 125, 23);
		frmProjectLegion.getContentPane().add(btnNewScreenSave);
		
		JLabel lblSlowCycless = new JLabel("1/2 Cycle/s");
		lblSlowCycless.setBounds(910, 671, 100, 14);
		frmProjectLegion.getContentPane().add(lblSlowCycless);
		
		JLabel lblFastCycless = new JLabel("5 Cycles/s");
		lblFastCycless.setBounds(1417, 671, 100, 14);
		frmProjectLegion.getContentPane().add(lblFastCycless);
		//create new NewBoardWindow to make new board
		JButton btnNewBoard = new JButton("New Board");
		btnNewBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Object obj = new NewBoardWindow();	
				NewBoardWindow newBoardWindow = new NewBoardWindow(frmProjectLegion);
				newBoardWindow.setVisible(true);
				tglbtnWrapAgents.setSelected(true);
				comboGoalStrategy.setSelectedItem(goalStrategy);
				//lblBoardSizeInt.setText(String.valueOf(board.labelHandler.getInitBoardSize()));
			}
		});
		btnNewBoard.setBackground(new Color(51, 102, 255));
		btnNewBoard.setBounds(1338, 726, 125, 23);
		frmProjectLegion.getContentPane().add(btnNewBoard);
	}
	
	public static void setLblBoardSizeInt(int boardSize)
	{
		lblBoardSizeInt.setText(String.valueOf(boardSize));
	}
	
	public static void setLblSwarmSizeInt(int swarmSize)
	{
		lblSwarmCountInt.setText(String.valueOf(swarmSize));
	}


	public static void setLblIntWhiteCells(int whiteCellSize)
	{
		lblIntWhiteCells.setText(String.valueOf(whiteCellSize));
	}
	
	public static void setLblIntBlackCells(int blackCellSize)
	{
		lblIntBlackCells.setText(String.valueOf(blackCellSize));
	}
	
	public static void setLblCurrWhiteCells(int currWhiteCells)
	{
		lblCurrWhiteCells.setText(String.valueOf(currWhiteCells));
	}
	
	public static void setLblCurrBlackCells(int currBlackCells)
	{
		lblCurrBlackCells.setText(String.valueOf(currBlackCells));
	}

}
