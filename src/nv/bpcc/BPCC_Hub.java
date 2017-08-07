/**
 *  This class is the main GUI hub and driver of the application.
 *  Users will have to login as either a guest (no password) or as an admin (requiring password).
 */

package nv.bpcc;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.invoke.MethodHandles;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import nv.bpcc.BPCC_Util.LogLevelEnum;

public class BPCC_Hub {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final static String classNameForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	private final BPCC_Dialog_Login loginDialog = new BPCC_Dialog_Login();
	
	private final String logMessage_applicationFrameCreated = "Application frame created and displayed.";
	private final String logMessage_applicationFrameClosed = "Application frame closed.";
	
	private final String logMessage_xButtonClicked = "User clicked on the \"X\" button.";
	
	//-----------------------------------------------------------------//
	
	/** Declare global variables **/
	
	private JFrame applicationFrame;
	
	//-----------------------------------------------------------------//
	
	/** Initialize global variables **/
	
	private void initVars() {
		applicationFrame = new JFrame(BPCC_Util.getApplicationTitle());
		BPCC_Util.setHubFrame(applicationFrame);
	}
	
	//-----------------------------------------------------------------//
	
	/** Main method and class declaration/initialization **/
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BPCC_Hub().start();
			}
		});
	}
	
	//-----------------------------------------------------------------//
	
	/** Abstract methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Implemented methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Event handlers **/
	
	
	
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
		
		createAndShowGUI();
	}
	
	private void exitApplication() {
		// Log applicationFrame closed event.
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_applicationFrameClosed);
		System.exit(0);
	}
	
	private void createAndShowGUI() {
		applicationFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		applicationFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wE) {
				// Log user interaction with X button.
				BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_xButtonClicked);
				exitApplication();
			}
			public void windowClosed(WindowEvent wE) {
				// Do nothing once the JFrame is closed.
			}
		});
		applicationFrame.setResizable(false);
		applicationFrame.getContentPane().add(buildMainPanel());
		applicationFrame.pack();
		applicationFrame.validate();
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameDim = applicationFrame.getSize();
		int heightWithoutTaskbar = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		applicationFrame.setLocation((screenDim.width - frameDim.width) / 2, (heightWithoutTaskbar - frameDim.height) / 2);		// Centers application in user's display
		
		// Display login dialog.
		loginDialog.showLoginDialog();
		if (loginDialog.getUsernameForLogin() == null) {
			exitApplication();
		}
		else {
			BPCC_Util.setActiveUser(loginDialog.getUsernameForLogin());
		}
		
		// Log applicationFrame created event.
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_applicationFrameCreated);
		applicationFrame.setVisible(true);
	}
	
	private JPanel buildMainPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		
		// TODO:  Build main panel here.
		
		
		return panel;
	}
	
	//-----------------------------------------------------------------//
	
}
