/**
 *  This class is only used to test various features of the application.
 */

package nv.bpcc;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.invoke.MethodHandles;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import nv.bpcc.BPCC_Util.LogLevelEnum;		// Import this enum from the BPCC_Util class for shorter references to its values.

public class BPCC_Tester implements ActionListener {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private static final String classNameForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	private final String guiText_frameTitle = "Test";
	
	private final String guiText_testButton = "Test";
	
	private final String guiText_exitButton = "Exit";
	
	private final String logMessage_frameCreated = "Test frame created and displayed.";
	private final String logMessage_frameClosed = "Test frame closed.";
	
	private final String logMessage_xButtonClicked = "User clicked on the \"X\" button.";
	
	private final String logMessage_testButtonClicked = "User clicked on the \"Test\" button.";
	
	private final String logMessage_exitButtonClicked = "User clicked on the \"Exit\" button.";
	
	private final String logMessage_executedTest = "Executed test.";
	
	//-----------------------------------------------------------------//
	
	/** Declare global variables **/
	
	private JFrame testFrame;
	
	private JButton testButton;
	
	private JButton exitButton;
	
	//-----------------------------------------------------------------//
	
	/** Initialize global variables **/
	
	private void initVars() {
		testFrame = new JFrame(guiText_frameTitle);
		BPCC_Util.setHubFrame(testFrame);
		
		testButton = new JButton(guiText_testButton);
		testButton.addActionListener(this);
		
		exitButton = new JButton(guiText_exitButton);
		exitButton.addActionListener(this);
	}
	
	//-----------------------------------------------------------------//
	
	/** Main method and class declaration/initialization **/
	
	public static void main(String[] args) {
		new BPCC_Tester().start();
	}
	
	//-----------------------------------------------------------------//
	
	/** Abstract methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Implemented methods **/
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == testButton) {
			BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_testButtonClicked);
			executeTest();
		}
		else if (e.getSource() == exitButton) {
			BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_exitButtonClicked);
			closeFrame();
		}
	}
		
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private void start() {
		// Clear the log file.
		BPCC_Logger.clearLogFile();
		
		// Initialize BPCC_Util static fields.
		BPCC_Util.initStaticFields();
		
		// Set logging level desired for test.
		BPCC_Util.setLogLevel(LogLevelEnum.DEBUG);
		
		// Initialize class global variables.
		initVars();
		
		// Create a JFrame with a single button which will execute whatever is to be tested.
		testFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		testFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wE) {
				// Log user interaction with X button.
				BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_xButtonClicked);
				closeFrame();
			}
			public void windowClosed(WindowEvent wE) {
				// Do nothing once testFrame is closed.
			}
		});
		testFrame.setResizable(false);
		testFrame.getContentPane().add(buildPanel());
		testFrame.pack();
		testFrame.validate();
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameDim = testFrame.getSize();
		int heightWithoutTaskbar = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		testFrame.setLocation((screenDim.width - frameDim.width) / 2, (heightWithoutTaskbar - frameDim.height) / 2);		//Centers application in user's display
		testFrame.setVisible(true);
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_frameCreated);
	}
	
	private void closeFrame() {
		// Log testFrame closed event.
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_frameClosed);
		System.exit(0);
	}
	
	private JPanel buildPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints panelConstraints = new GridBagConstraints();
		
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 0;
		panelConstraints.insets = new Insets(100, 100, 100, 5);
		panel.add(testButton, panelConstraints);
		
		panelConstraints.gridx = 1;
		panelConstraints.gridy = 0;
		panelConstraints.insets = new Insets(100, 5, 100, 100);
		panel.add(exitButton, panelConstraints);
		
		return panel;
	}
	
	private void executeTest() {
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_executedTest);
		
		// TODO:  Test block.
		BPCC_Dialog_Login loginDialog = new BPCC_Dialog_Login();
		loginDialog.showLoginDialog();
		if (loginDialog.getUsernameForLogin() == null) {
			closeFrame();
		}
		else {
			System.out.println("Logged in as " + loginDialog.getUsernameForLogin() + " .");
		}
		// TODO:  Test block.
	}

	//-----------------------------------------------------------------//
	
}
