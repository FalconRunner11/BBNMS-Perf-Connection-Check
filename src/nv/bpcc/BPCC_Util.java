/**
 *  This class contains any values, variables, and methods which may be used in a static context by other classes in the application.
 */

package nv.bpcc;

import java.lang.invoke.MethodHandles;

import javax.swing.JFrame;

public class BPCC_Util {
	
	//-----------------------------------------------------------------//
	
	/** Declare enums **/
	
	protected enum LogLevelEnum {ERROR, DEBUG};
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final String classNameForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	private final static String versionNumber = "0.1.0";
	private final static String applicationTitle = "BBNMS Perf Connection Check v" + versionNumber;
	
	//-----------------------------------------------------------------//
	
	/** Declare static fields **/
	
	private static JFrame hubFrame;
	
	private static LogLevelEnum logLevel;
	
	//-----------------------------------------------------------------//
	
	/** Initialize static fields **/
	
	protected static void initStaticFields() {
		// TODO:  Call from the beginning of BPCC_Hub.
		
		hubFrame = null;
		
		logLevel = LogLevelEnum.DEBUG;
	}
	
	//-----------------------------------------------------------------//
	
	/** Abstract methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Implemented methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Accessor methods **/
	
	protected static String getVersionNumber() {
		return versionNumber;
	}
	
	protected static JFrame getHubFrame() {
		return hubFrame;
	}
	
	protected static String getApplicationTitle() {
		return applicationTitle;
	}
	
	protected static LogLevelEnum getLogLevel() {
		return logLevel;
	}
	
	//-----------------------------------------------------------------//
	
	/** Mutator methods **/
	
	protected static void setHubFrame(JFrame inc_frame) {
		// TODO:  Must be called from BPCC_Hub early on in execution.
		hubFrame = inc_frame;
	}
	
	protected static void setLogLevel(LogLevelEnum inc_logLevel) {
		logLevel = inc_logLevel;
	}
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	
	
	//-----------------------------------------------------------------//
	
}
