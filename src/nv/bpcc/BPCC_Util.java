/**
 *  This class contains any values, variables, and methods which may be used in a static context by other classes in the application.
 *  LogLevelEnum values:
 *  	DEBUG:	Application errors and exceptions
 *  			User-GUI interaction events
 *  			Major application events
 *  			Minor application events
 *  	INFO:	Application errors and exceptions
 *  			Major application events
 *  	ERROR:	Application errors and exceptions
 */

package nv.bpcc;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

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
	
	private static LogLevelEnum logLevel;

	private static JFrame hubFrame;
	
	private static ArrayList<BPCC_AppUser> appUserList;
	
	//-----------------------------------------------------------------//
	
	/** Initialize static fields **/
	
	protected static void initStaticFields() {
		// Initialize logLevel without an accompanying log message.
		// TODO:  Decide what final default log level should be at release of application.
		logLevel = LogLevelEnum.INFO;
		
		hubFrame = null;
		
		// TODO:  Collect list of usernames from config file.
		appUserList = new ArrayList<BPCC_AppUser>();
		appUserList.add(new BPCC_AppUser("guest"));
		appUserList.add(new BPCC_AppUser("admin", "password"));
		
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_variablesInitialized);
	}
	
	//-----------------------------------------------------------------//
	
	/** Abstract methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Implemented methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Accessor methods **/
	
	protected static String getApplicationTitle() {
		return applicationTitle;
	}
	
	protected static JFrame getHubFrame() {
		return hubFrame;
	}
	
	protected static LogLevelEnum getLogLevel() {
		return logLevel;
	}
	
	protected static ArrayList<BPCC_AppUser> getAppUserList() {
		return appUserList;
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
