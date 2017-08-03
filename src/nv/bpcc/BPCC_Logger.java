/**
 *  This class is responsible for logging application events initiated by other classes.
 *  In the case of logging application error messages and exceptions, a BPCC_Dialog_Error object is launched, presenting the user with information 
 *  regarding the error or execption.
 *  Calls to log error and exception messages are accompanied by a flag of either 0 or -1.  A value of -1 causes the application to exit/shut down.  
 *  A flag of 0 allows program execution to continue.
 */

package nv.bpcc;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;

import org.joda.time.DateTime;

import nv.bpcc.BPCC_Util.LogLevelEnum;		// Import this enum from the BPCC_Util class for shorter references to its values.

public class BPCC_Logger {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final static String classNameForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	private final static String logFile = "bpcc.log";
	
	private final static BPCC_Dialog_Error errorDialog = new BPCC_Dialog_Error();
	
	private final static String logMessage_fatalError = "Application closed due to a fatal error";
	
	//-----------------------------------------------------------------//
	
	/** Declare static fields **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Initialize static fields **/
	
	protected static void initStaticFields() {
		
	}
	
	//-----------------------------------------------------------------//
	
	/** Abstract methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Implemented methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Accessor methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Mutator methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	protected static void clearLogFile() {
		// TODO:  Call from the beginning of BPCC_Hub.
		try {
			new PrintWriter(logFile).close();
		} catch (FileNotFoundException e) {
			BPCC_Logger.logErrorMessage(classNameForLogger, e, -1);
		}
	}
	
	protected static void logDebugMessage(String inc_className, String inc_debugMessageText) {
		// Log DEBUG message if logging level is set to DEBUG.
		if (BPCC_Util.getLogLevel() == LogLevelEnum.DEBUG) {
			writeToLog(inc_className, inc_debugMessageText);
		}
	}
	
	protected static void logInfoMessage(String inc_className, String inc_infoMessageText) {
		// Log INFO message if logging level is set to INFO or higher.
		if (BPCC_Util.getLogLevel() == LogLevelEnum.DEBUG || BPCC_Util.getLogLevel() == LogLevelEnum.INFO) {
			writeToLog(inc_className, inc_infoMessageText);
		}
	}
	
	protected static void logErrorMessage(String inc_className, String inc_errorMessage, int inc_errorCode) {
		// Log ERROR message if logging level is set to ERROR or higher.
		if (BPCC_Util.getLogLevel() == LogLevelEnum.DEBUG || BPCC_Util.getLogLevel() == LogLevelEnum.INFO || BPCC_Util.getLogLevel() == LogLevelEnum.ERROR) {
			writeToLog(inc_className, inc_errorMessage);
		}
		
		// Initiate error dialog box.
		errorDialog.setErrorDialog(inc_errorMessage);
		
		// Handle application shutdown errors.
		if (inc_errorCode == -1) {
			BPCC_Logger.logErrorMessage(inc_className, logMessage_fatalError, 0);
			System.exit(-1);
		}
	}
	
	protected static void logErrorMessage(String inc_className, Exception inc_exception, int inc_errorCode) {
		// Log exception (ERROR) message if logging level is set to ERROR or higher.
		if (BPCC_Util.getLogLevel() == LogLevelEnum.DEBUG || BPCC_Util.getLogLevel() == LogLevelEnum.INFO || BPCC_Util.getLogLevel() == LogLevelEnum.ERROR) {
			StringWriter sw = new StringWriter();
			inc_exception.printStackTrace(new PrintWriter(sw));
			writeToLog(inc_className, sw.toString());
		}
		
		// Initiate exception dialog box.
		errorDialog.setExceptionDialog(inc_exception);
		
		// Handle application shutdown errors.
		if (inc_errorCode == -1) {
			BPCC_Logger.logErrorMessage(inc_className, logMessage_fatalError, 0);
			System.exit(-1);
		}
	}
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private static void writeToLog(String inc_className, String inc_messageText) {
		// Format the timestamp and log message text.
		DateTime timestamp = new DateTime();
		String formattedMessageText = new String();
		formattedMessageText += formatTimestamp(timestamp) + " : " + inc_className;
		formattedMessageText += formatMessageText(inc_messageText);
		
		// Write the log entry to the log file.
		BufferedWriter outStream = null;
		try {
			outStream = new BufferedWriter(new FileWriter(logFile, true));
			outStream.write(formattedMessageText);
			outStream.newLine();
			outStream.newLine();
		} catch (IOException e) {
			BPCC_Logger.logErrorMessage(classNameForLogger, e, -1);
		} finally {
			try {
				outStream.close();
			} catch (IOException e) {
				BPCC_Logger.logErrorMessage(classNameForLogger, e, -1);
			}
		}
	}
	
	private static String formatTimestamp(DateTime inc_timestamp) {
		String formattedTimestampString = new String();
		formattedTimestampString += inc_timestamp.getYear() + "-";
		if (inc_timestamp.getMonthOfYear() < 10) {
			formattedTimestampString += "0";
		}
		formattedTimestampString += inc_timestamp.getMonthOfYear() + "-";
		if (inc_timestamp.getDayOfMonth() < 10) {
			formattedTimestampString += "0";
		}
		formattedTimestampString += inc_timestamp.getDayOfMonth() + " ";
		if (inc_timestamp.getHourOfDay() < 10) {
			formattedTimestampString += "0";
		}
		formattedTimestampString += inc_timestamp.getHourOfDay() + ":";
		if (inc_timestamp.getMinuteOfHour() < 10) {
			formattedTimestampString += "0";
		}
		formattedTimestampString += inc_timestamp.getMinuteOfHour() + ":";
		if (inc_timestamp.getSecondOfMinute() < 10) {
			formattedTimestampString += "0";
		}
		formattedTimestampString += inc_timestamp.getSecondOfMinute() + ".";
		if (inc_timestamp.getMillisOfSecond() < 10) {
			formattedTimestampString += "0";
		}
		if (inc_timestamp.getMillisOfSecond() < 100) {
			formattedTimestampString += "0";
		}
		formattedTimestampString += inc_timestamp.getMillisOfSecond();
		return formattedTimestampString;
	}
	
	private static String formatMessageText(String inc_messageText) {
		String formattedMessageText = "\r\n\t";
		for (int i = 0; i < inc_messageText.length(); i++) {
			if (inc_messageText.charAt(i) == '\n') {
				formattedMessageText += "\r\n\t";
			}
			else {
				formattedMessageText += inc_messageText.charAt(i);
			}
		}
		return formattedMessageText;
	}
	
	//-----------------------------------------------------------------//
	
}
