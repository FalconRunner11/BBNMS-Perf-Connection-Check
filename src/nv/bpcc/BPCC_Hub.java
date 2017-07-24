/**
 *  This class is the main GUI hub and driver of the application.
 *  Users will have to login as either a guest (no credentials) or as an admin (requiring username/password)
 */

package nv.bpcc;

import java.lang.invoke.MethodHandles;

import javax.swing.SwingUtilities;

public class BPCC_Hub {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final static String classNameForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	//-----------------------------------------------------------------//
	
	/** Declare global variables **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Initialize global variables **/
	
	private void initVars() {
		
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
		
	}
	
	//-----------------------------------------------------------------//
	
	/** Event handlers **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private void start() {
		initVars();
		createAndShowGUI();
	}
	
	//-----------------------------------------------------------------//
	
}
