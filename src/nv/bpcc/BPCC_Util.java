/**
 *  This class contains any values, variables, and methods which may be used in a static context by other classes in the application.
 *  LogLevelEnum values:
 *  	DEBUG:	Application errors and exceptions
 *  			User-GUI interaction events
 *  			Major application events
 *  			Minor application events
 *  	INFO:	Application errors and exceptions
 *  			User-GUI interaction events
 *  			Major application events
 *  	ERROR:	Application errors and exceptions
 */

package nv.bpcc;

import java.lang.invoke.MethodHandles;

import javax.swing.JFrame;

public class BPCC_Util {
	
	//-----------------------------------------------------------------//
	
	/** Declare enums **/
	
	protected enum LogLevelEnum {DEBUG, INFO, ERROR};
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final static String classNameForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	private final static String versionNumber = "0.1.0";
	private final static String applicationTitle = "BBNMS Perf Connection Check v" + versionNumber;
	
	private final static String logMessage_variablesInitialized = "Initialized application variables.";
	
	private final static String logMessage_logLevelSet = "Logging level set to ";
	
	private final static String logMessage_period = ".";
	
	//-----------------------------------------------------------------//
	
	/** Declare static fields **/
	
	private static JFrame hubFrame;
	
	private static LogLevelEnum logLevel;
	
	//-----------------------------------------------------------------//
	
	/** Initialize static fields **/
	
	protected static void initStaticFields() {
		hubFrame = null;
		
		// Initialize logLevel without an accompanying log message.
		// TODO:  Decide what final default log level should be at release of application.
		logLevel = LogLevelEnum.INFO;
		
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_variablesInitialized);
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
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_logLevelSet + inc_logLevel + logMessage_period);
		logLevel = inc_logLevel;
	}
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	
	
	//-----------------------------------------------------------------//
	
}
