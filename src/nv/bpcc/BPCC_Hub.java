/**
 *  This class is the main GUI hub and driver of the application.
 *  Users will have to login as either a guest (no credentials) or as an admin (requiring username/password).
 */

package nv.bpcc;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.invoke.MethodHandles;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BPCC_Hub {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final static String classNameForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	private final String logMessage_frameCreated = "Application frame created and displayed.";
	private final String logMessage_frameClosed = "Application frame closed.";
	
	//-----------------------------------------------------------------//
	
	/** Declare global variables **/
	
	JFrame applicationFrame;
	
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
	
	/** Create and manage GUI components **/
	
	private void createAndShowGUI() {
		applicationFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		applicationFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wE) {
				end();
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
		applicationFrame.setLocation((screenDim.width - frameDim.width) / 2, (heightWithoutTaskbar - frameDim.height) / 2);		//Centers application in user's display
		
		// TODO:  Create and display login dialog.
		
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_frameCreated);
		applicationFrame.setVisible(true);
	}
	
	private JPanel buildMainPanel() {
		JPanel panel = new JPanel();
		
		// TODO:  Build main panel here.
		
		return panel;
	}
	
	//-----------------------------------------------------------------//
	
	/** Event handlers **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private void start() {
		BPCC_Util.initStaticFields();
		initVars();
		createAndShowGUI();
	}
	
	private void end() {
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_frameClosed);
		System.exit(0);
	}
	
	//-----------------------------------------------------------------//
	
}
