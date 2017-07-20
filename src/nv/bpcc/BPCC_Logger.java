/**
 *  
 */

package nv.bpcc;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.joda.time.DateTime;

public class BPCC_Logger {
	
	private enum Level {ERROR, DEBUG};
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final String classNameForLogger = this.getClass().getName().toString();
	
	private final static String logFile = "bpcc.log";
	
	//-----------------------------------------------------------------//
	
	/** Declare static fields **/
	
	private static Level logLevel;
	
	private static DateTime timestamp;
	
	//-----------------------------------------------------------------//
	
	/** Initialize static fields **/
	
	protected static void initStaticFields() {
		logLevel = Level.DEBUG;
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
			// TODO:  Exception handling.
		}
	}
	
	protected static void logMessage(String inc_class, String inc_standardLogMessage) {
		writeToLog(inc_class, inc_standardLogMessage);
	}
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private static void writeToLog(String inc_class, String inc_logEntryMessage) {
		// Format the timestamp and log message text.
		timestamp = new DateTime();
		String finalLogEntryMessage = "";
		finalLogEntryMessage += formatTimestamp(timestamp) + " : " + inc_class;
		finalLogEntryMessage += formatLogEntryMessage(inc_logEntryMessage);
		
		// Write the log entry to the log file.
		BufferedWriter outStream = null;
		try {
			outStream = new BufferedWriter(new FileWriter(logFile, true));
			outStream.write(finalLogEntryMessage);
			outStream.newLine();
		} catch (IOException e) {
			// TODO:  Exception handling.
		} finally {
			try {
				outStream.close();
			} catch (IOException e) {
				// TODO:  Exception handling.
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
	
	private static String formatLogEntryMessage(String inc_logEntryMessage) {
		String formattedLogEntry = "\r\n\t";
		for (int i = 0; i < inc_logEntryMessage.length(); i++) {
			if (inc_logEntryMessage.charAt(i) == '\n') {
				formattedLogEntry += "\r\n\t";
			}
			else {
				formattedLogEntry += inc_logEntryMessage.charAt(i);
			}
		}
		return formattedLogEntry;
	}
	
	//-----------------------------------------------------------------//
	
}
