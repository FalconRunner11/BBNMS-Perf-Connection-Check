/**
 *  This class is only used to test various features of the application.
 */

package nv.bpcc;

public class BPCC_Tester {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final String classStringForLogger = this.getClass().getName().toString();
	
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
		// Clear the log file.
		BPCC_Logger.clearLogFile();
		
		initVars();
		
		// TODO:  Test block.
		BPCC_Logger.logMessage(classStringForLogger, "Logged a message!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BPCC_Logger.logMessage(classStringForLogger, "Logged another message!");
		// TODO:  Test block.
	}
		
	//-----------------------------------------------------------------//
	
}
