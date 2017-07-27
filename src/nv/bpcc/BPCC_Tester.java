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

public class BPCC_Tester {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final String classStringForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	//-----------------------------------------------------------------//
	
	/** Declare global variables **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Initialize global variables **/
	
	private void initVars() {
		
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
	
	
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private void start() {
		// Initialize BPCC_Util static fields.
		BPCC_Util.initStaticFields();
		
		// Set logging level desired for test.
		BPCC_Util.setLogLevel(LogLevelEnum.DEBUG);
		
		// Clear the log file.
		BPCC_Logger.clearLogFile();
		
		// Initialize class global variables.
		initVars();
		
		// Create a JFrame with a single button which will execute whatever is to be tested.
		JFrame frame = new JFrame("Test");
		BPCC_Util.setHubFrame(frame);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wE) {
				BPCC_Logger.logInfoMessage(classStringForLogger, "User closed the application.");
				System.exit(0);
			}
		});
		frame.setResizable(false);
		frame.getContentPane().add(buildPanel());
		frame.pack();
		frame.validate();
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameDim = frame.getSize();
		int heightWithoutTaskbar = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		frame.setLocation((screenDim.width - frameDim.width) / 2, (heightWithoutTaskbar - frameDim.height) / 2);		//Centers application in user's display
		frame.setVisible(true);
		BPCC_Logger.logInfoMessage(classStringForLogger, "Created test frame.");
	}
	
	private JPanel buildPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints panelConstraints = new GridBagConstraints();
		panelConstraints.insets = new Insets(100, 100, 100, 100);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeTest();
			}
		});
		
		panel.add(okButton, panelConstraints);
		
		return panel;
	}
	
	private void executeTest() {
		BPCC_Logger.logInfoMessage(classStringForLogger, "Executed test.");
		
		// TODO:  Test block.
		
		// TODO:  Test block.
	}
	
	//-----------------------------------------------------------------//
	
}
