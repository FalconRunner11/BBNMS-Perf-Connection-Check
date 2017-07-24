/**
 *  This class contains any values, variables, and methods which may be used in a static context by other classes in the application.
 */

package nv.bpcc;

public class BPCC_Util {
	
	//-----------------------------------------------------------------//
	
	/** Declare enums **/
	
	protected enum LogLevelEnum {ERROR, DEBUG};
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final String classNameForLogger = this.getClass().getName().toString();
	
	private final static String versionNumber = "0.1.0";
	
	//-----------------------------------------------------------------//
	
	/** Declare static fields **/
	
	private static LogLevelEnum logLevel;
	
	//-----------------------------------------------------------------//
	
	/** Initialize static fields **/
	
	protected static void initStaticFields() {
		// TODO:  Call from the beginning of BPCC_Hub.
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
	
	protected static LogLevelEnum getLogLevel() {
		return logLevel;
	}
	
	//-----------------------------------------------------------------//
	
	/** Mutator methods **/
	
	protected static void setLogLevel(LogLevelEnum inc_logLevel) {
		logLevel = inc_logLevel;
	}
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	
	
	//-----------------------------------------------------------------//
	
}
