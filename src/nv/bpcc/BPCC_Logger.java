/**
 *  
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
	
	private final static String fatalErrorMessageString = "Application closed due to a fatal error";
	
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
		try {
			new PrintWriter(logFile).close();
		} catch (FileNotFoundException e) {
			BPCC_Logger.logExceptionMessage(classNameForLogger, e, -1);
		}
	}
	
	protected static void logBasicMessage(String inc_class, String inc_basicMessageText) {
		// Only log basic messages when logging level is set to debug.
		if (BPCC_Util.getLogLevel() == LogLevelEnum.DEBUG) {
			writeToLog(inc_class, inc_basicMessageText);
		}
	}
	
	protected static void logErrorMessage(String inc_class, String inc_errorMessage, int inc_errorCode) {
		// Always log error messages regardless of logging level.
		
		
		if (BPCC_Util.getLogLevel() == LogLevelEnum.DEBUG || BPCC_Util.getLogLevel() == LogLevelEnum.ERROR) {
			writeToLog(inc_class, inc_errorMessage);
		}
		
		// Initiate error dialog box.
		errorDialog.setErrorDialog(inc_errorMessage);
		
		// Handle application shutdown errors.
		if (inc_errorCode == -1) {
			BPCC_Logger.logErrorMessage(inc_class, fatalErrorMessageString, 0);
			System.exit(-1);
		}
	}
	
	protected static void logExceptionMessage(String inc_class, Exception inc_exception, int inc_errorCode) {
		// Always log exception messages regardless of logging level.
		if (BPCC_Util.getLogLevel() == LogLevelEnum.DEBUG || BPCC_Util.getLogLevel() == LogLevelEnum.ERROR) {
			StringWriter sw = new StringWriter();
			inc_exception.printStackTrace(new PrintWriter(sw));
			writeToLog(inc_class, sw.toString());
		}
		
		// Initiate exception dialog box.
		errorDialog.setExceptionDialog(inc_exception);
		
		// Handle application shutdown errors.
		if (inc_errorCode == -1) {
			BPCC_Logger.logErrorMessage(inc_class, fatalErrorMessageString, 0);
			System.exit(-1);
		}
	}
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private static void writeToLog(String inc_class, String inc_messageText) {
		// Format the timestamp and log message text.
		DateTime timestamp = new DateTime();
		String formattedMessageText = "";
		formattedMessageText += formatTimestamp(timestamp) + " : " + inc_class;
		formattedMessageText += formatMessageText(inc_messageText);
		
		// Write the log entry to the log file.
		BufferedWriter outStream = null;
		try {
			outStream = new BufferedWriter(new FileWriter(logFile, true));
			outStream.write(formattedMessageText);
			outStream.newLine();
		} catch (IOException e) {
			BPCC_Logger.logExceptionMessage(classNameForLogger, e, -1);
		} finally {
			try {
				outStream.close();
			} catch (IOException e) {
				BPCC_Logger.logExceptionMessage(classNameForLogger, e, -1);
			}
		}
	}
	
	private static String formatTimestamp(DateTime inc_timestamp) {
		String formattedTimestampString = "";
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
