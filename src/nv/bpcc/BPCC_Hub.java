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
	
	//-----------------------------------------------------------------//
	
	/** Declare global variables **/
	
	JFrame hubFrame;
	
	//-----------------------------------------------------------------//
	
	/** Initialize global variables **/
	
	private void initVars() {
		hubFrame = new JFrame(BPCC_Util.getApplicationTitle());
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
		hubFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		hubFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wE) {
				end();
			}
		});
		hubFrame.setResizable(false);
		hubFrame.getContentPane().add(buildMainPanel());
		hubFrame.pack();
		hubFrame.validate();
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameDim = hubFrame.getSize();
		int heightWithoutTaskbar = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		hubFrame.setLocation((screenDim.width - frameDim.width) / 2, (heightWithoutTaskbar - frameDim.height) / 2);		//Centers application in user's display
		hubFrame.setVisible(true);
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
		// TODO:  Add a log message here for when use exits the application.
		System.exit(0);
	}
	
	//-----------------------------------------------------------------//
	
}
