/**
 *  This class is only used to test various features of the application.
 */

package nv.bpcc;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.invoke.MethodHandles;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
		
		// Clear the log file.
		BPCC_Logger.clearLogFile();
		
		// Initialize class global variables.
		initVars();
		
		// TODO:  Create a JFrame with a single button which will execute whatever is to be tested.
		
		// TODO:  Test block.
		JFrame frame = new JFrame("Test");
		BPCC_Util.setHubFrame(frame);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wE) {
				System.exit(0);
			}
		});
		frame.setResizable(false);
		frame.getContentPane().add(new JLabel("test"));
		frame.pack();
		frame.validate();
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameDim = frame.getSize();
		int heightWithoutTaskbar = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		frame.setLocation((screenDim.width - frameDim.width) / 2, (heightWithoutTaskbar - frameDim.height) / 2);		//Centers application in user's display
		frame.setVisible(true);
		BPCC_Logger.logBasicMessage(classStringForLogger, "Performed an action");
		try {
			int x = 1 / 0;
		}
		catch (Exception e) {
			BPCC_Logger.logExceptionMessage(classStringForLogger, e, 0);
		}
		// TODO:  Test block.
	}
		
	//-----------------------------------------------------------------//
	
}
