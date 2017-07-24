/**
 *  This class is only used to test various features of the application.
 */

package nv.bpcc;

import java.lang.invoke.MethodHandles;

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
		// Initialize application static fields.
		BPCC_Util.initStaticFields();
		
		// Clear the log file.
		BPCC_Logger.clearLogFile();
		
		// Initialize class global variables.
		initVars();
		
		// TODO:  Test block.
		
		// TODO:  Test block.
	}
		
	//-----------------------------------------------------------------//
	
}
