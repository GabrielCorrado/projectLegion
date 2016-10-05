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

public class ProjectLegion {

	static int HEIGHT = 864;
	static int WIDTH = 1536;
	static int BOARDSIZE = 800;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
				
		JPanel board = new JPanel();
		board.setBackground(Color.WHITE);
		board.setBounds(10, (HEIGHT-BOARDSIZE)/8, BOARDSIZE, BOARDSIZE);
		//System.out.print((HEIGHT-BOARDSIZE)/8);
		frame.getContentPane().add(board);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(818, 10, 700, 600);
		frame.getContentPane().add(tabbedPane);
		
		JPanel tabLayer1 = new JPanel();
		tabLayer1.setBackground(new Color(211, 211, 211));
		tabbedPane.addTab("Layer 1", null, tabLayer1, null);
		tabLayer1.setLayout(null);
		
		JButton btnChangeBoard = new JButton("New Board Size");
		btnChangeBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnChangeBoard.setBounds(10, 529, 125, 35);
		tabLayer1.add(btnChangeBoard);
		
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
		
		JPanel tabLayer2 = new JPanel();
		tabLayer2.setBackground(new Color(211, 211, 211));
		tabbedPane.addTab("Layer 2", null, tabLayer2, null);
		tabLayer2.setLayout(null);
		
		JLabel lblPrimaryPolarityColor = new JLabel("Primary Polarity Color");
		lblPrimaryPolarityColor.setBounds(25, 25, 125, 14);
		tabLayer2.add(lblPrimaryPolarityColor);
		
		JComboBox comboPrimary = new JComboBox();
		comboPrimary.setModel(new DefaultComboBoxModel(new String[] {"BLUE", "RED", "GREEN", "CYAN", "BLACK", "WHITE"}));
		comboPrimary.setBounds(25, 50, 125, 22);
		String[] tempString = {"BLUE","RED","GREEN","CYAN","WHITE","BLACK"};
		tabLayer2.add(comboPrimary);
		
		JLabel lblSecondaryPolarityColor = new JLabel("Secondary Polarity Color");
		lblSecondaryPolarityColor.setBounds(238, 25, 150, 14);
		tabLayer2.add(lblSecondaryPolarityColor);
		
		JComboBox comboSecondary = new JComboBox();
		comboSecondary.setModel(new DefaultComboBoxModel(new String[] {"RED", "BLUE", "GREEN", "CYAN", "BLACK", "WHITE"}));
		comboSecondary.setBounds(238, 50, 125, 22);
		tabLayer2.add(comboSecondary);
		
		JLabel lblTertiaryPolarityColor = new JLabel("Tertiary Polarity Color");
		lblTertiaryPolarityColor.setBounds(450, 25, 125, 14);
		tabLayer2.add(lblTertiaryPolarityColor);
		
		JComboBox comboTertiary = new JComboBox();
		comboTertiary.setModel(new DefaultComboBoxModel(new String[] {"null", "BLUE", "RED", "GREEN", "CYAN", "BLACK", "WHITE"}));
		comboTertiary.setBounds(450, 50, 125, 22);
		tabLayer2.add(comboTertiary);
		
		JLabel lblReginalStability = new JLabel("Reginal Stability");
		lblReginalStability.setBounds(25, 200, 125, 14);
		tabLayer2.add(lblReginalStability);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"50/50", "60/40", "70/30", "80/20", "90/10", "100/0"}));
		comboBox.setBounds(25, 225, 125, 22);
		tabLayer2.add(comboBox);
		
		JRadioButton radioButton = new JRadioButton("0_0");
		radioButton.setSelected(true);
		radioButton.setBounds(238, 300, 50, 50);
		tabLayer2.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("0_1");
		radioButton_1.setBounds(288, 301, 50, 50);
		tabLayer2.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("0_2");
		radioButton_2.setSelected(true);
		radioButton_2.setBounds(338, 301, 50, 50);
		tabLayer2.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("1_0");
		radioButton_3.setBounds(238, 350, 50, 50);
		tabLayer2.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("1_1");
		radioButton_4.setSelected(true);
		radioButton_4.setBounds(288, 350, 50, 50);
		tabLayer2.add(radioButton_4);
		
		JRadioButton radioButton_5 = new JRadioButton("1_2");
		radioButton_5.setBounds(338, 350, 50, 50);
		tabLayer2.add(radioButton_5);
		
		JRadioButton radioButton_6 = new JRadioButton("2_0");
		radioButton_6.setSelected(true);
		radioButton_6.setBounds(238, 400, 50, 50);
		tabLayer2.add(radioButton_6);
		
		JRadioButton radioButton_7 = new JRadioButton("2_1");
		radioButton_7.setBounds(288, 400, 50, 50);
		tabLayer2.add(radioButton_7);
		
		JRadioButton radioButton_8 = new JRadioButton("2_2");
		radioButton_8.setSelected(true);
		radioButton_8.setBounds(338, 400, 50, 50);
		tabLayer2.add(radioButton_8);
		
		JLabel lblPolarity = new JLabel("Polarity");
		lblPolarity.setHorizontalAlignment(SwingConstants.CENTER);
		lblPolarity.setBounds(238, 200, 150, 14);
		tabLayer2.add(lblPolarity);
		
		JButton btnUpdatePolarity = new JButton("Update Polarity");
		btnUpdatePolarity.setBounds(238, 225, 150, 22);
		tabLayer2.add(btnUpdatePolarity);
		
		JPanel tabLayer3 = new JPanel();
		tabLayer3.setBackground(new Color(211, 211, 211));
		tabbedPane.addTab("Layer 3", null, tabLayer3, null);
		tabLayer3.setLayout(null);
		
		JLabel lblNumberOfAgents = new JLabel("Number of Agents:");
		lblNumberOfAgents.setBounds(10, 25, 150, 14);
		tabLayer3.add(lblNumberOfAgents);
		
		textField = new JTextField();
		textField.setText("4");
		textField.setBounds(150, 23, 40, 20);
		tabLayer3.add(textField);
		textField.setColumns(10);
		
		JButton btnUpdateAgents = new JButton("Update Agents");
		btnUpdateAgents.setBounds(215, 23, 150, 20);
		tabLayer3.add(btnUpdateAgents);
		
		JLabel lblAgentsColor = new JLabel("Agents Color:");
		lblAgentsColor.setBounds(440, 25, 125, 14);
		tabLayer3.add(lblAgentsColor);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(568, 23, 100, 20);
		tabLayer3.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"GREEN", "YELLOW", "ORANGE", "MAGENTA", "BLUE", "RED", "WHITE", "BLACK"}));
		
		JLabel lblNumberOfChanges = new JLabel("Number of Changes:");
		lblNumberOfChanges.setBounds(10, 58, 150, 14);
		tabLayer3.add(lblNumberOfChanges);
		
		textField_1 = new JTextField();
		textField_1.setText("-1");
		textField_1.setBounds(150, 56, 40, 20);
		tabLayer3.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnUpdateChanges = new JButton("Update Changes");
		btnUpdateChanges.setBounds(215, 56, 150, 20);
		tabLayer3.add(btnUpdateChanges);
		
		JLabel lblAgentCloseness = new JLabel("Agent Closeness:");
		lblAgentCloseness.setBounds(10, 92, 150, 14);
		tabLayer3.add(lblAgentCloseness);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setBounds(150, 90, 40, 20);
		tabLayer3.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnUpdateCloseness = new JButton("Update Closeness");
		btnUpdateCloseness.setBounds(215, 90, 150, 20);
		tabLayer3.add(btnUpdateCloseness);
		
		JLabel lblPharamoneTrailColor = new JLabel("Pharamone Color:");
		lblPharamoneTrailColor.setBounds(440, 58, 125, 14);
		tabLayer3.add(lblPharamoneTrailColor);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"WHITE", "GREEN", "YELLOW", "ORANGE", "MAGENTA", "BLUE", "RED", "BLACK"}));
		comboBox_2.setBounds(568, 56, 100, 20);
		tabLayer3.add(comboBox_2);
		
		JLabel lblPharamoneStrength = new JLabel("Pharamone Strength:");
		lblPharamoneStrength.setBounds(10, 125, 150, 14);
		tabLayer3.add(lblPharamoneStrength);
		
		textField_3 = new JTextField();
		textField_3.setText("1");
		textField_3.setColumns(10);
		textField_3.setBounds(150, 123, 40, 20);
		tabLayer3.add(textField_3);
		
		JButton btnUpdatePStrength = new JButton("Update P Strength");
		btnUpdatePStrength.setBounds(215, 123, 150, 18);
		tabLayer3.add(btnUpdatePStrength);
		
		JLabel lblBoardSize = new JLabel("Board Size:");
		lblBoardSize.setBounds(820, 621, 74, 14);
		frame.getContentPane().add(lblBoardSize);
		
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
		
		JSlider sliderSwarmSpeed = new JSlider();
		sliderSwarmSpeed.setBounds(953, 646, 450, 24);
		frame.getContentPane().add(sliderSwarmSpeed);
		
		JLabel lblSlow = new JLabel("Slow");
		lblSlow.setBounds(910, 646, 46, 14);
		frame.getContentPane().add(lblSlow);
		
		JLabel lblFast = new JLabel("Fast");
		lblFast.setBounds(1417, 646, 46, 14);
		frame.getContentPane().add(lblFast);
		
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
		
		JButton btnNewSwarm = new JButton("New Swarm");
		btnNewSwarm.setBackground(new Color(51, 102, 255));
		btnNewSwarm.setBounds(1338, 726, 125, 23);
		frame.getContentPane().add(btnNewSwarm);
		
		JButton btnNewScreenSave = new JButton("Screen Shot");
		btnNewScreenSave.setBackground(new Color(204, 51, 255));
		btnNewScreenSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewScreenSave.setBounds(1338, 767, 125, 23);
		frame.getContentPane().add(btnNewScreenSave);
		
		JLabel lblCycless = new JLabel("1/2 Cycle/s");
		lblCycless.setBounds(910, 671, 100, 14);
		frame.getContentPane().add(lblCycless);
		
		JLabel lblCycless_1 = new JLabel("5 Cycles/s");
		lblCycless_1.setBounds(1417, 671, 100, 14);
		frame.getContentPane().add(lblCycless_1);
		
		
	}
}
