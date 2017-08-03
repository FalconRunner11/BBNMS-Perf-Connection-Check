/**
 *  This class presents the user with a dialog that displays information about any exceptions or general errors which are thrown by the application.
 */

package nv.bpcc;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class BPCC_Dialog_Error extends JOptionPane implements ActionListener {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final static String classNameForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	private final JFrame hubFrame = BPCC_Util.getHubFrame();
	
	private final String guiText_dialogTitle = BPCC_Util.getApplicationTitle();
	
	private final String guiText_dialogBorder = "Error/Exception Encountered!";
	
	private final String guiText_label_exceptionPrefix= "Exception:  ";
	private final String guiText_label_errorPrefix = "Application Error";
	
	private final String guiText_okButton = "OK";
	
	private final String logMessage_dialogCreated = "Error dialog created and displayed.";
	private final String logMessage_dialogClosed = "Error dialog closed.";
	
	private final String logMessage_okButton = "User clicked on \"OK\" button.";
	
	//-----------------------------------------------------------------//
	
	/** Declare global variables **/
	
	private JLabel label;
	private JDialog dialog;
	private JTextArea textArea;
	private JButton okButton;
	
	//-----------------------------------------------------------------//
	
	/** Initialize global variables **/
	
	private void initVars() {
		okButton = new JButton(guiText_okButton);
		okButton.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
	}
	
	//-----------------------------------------------------------------//
	
	/** Abstract methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Implemented methods **/
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			// Log user interaction with okButton.
			dialog.dispose();
			BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_okButton);
			BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_dialogClosed);
		}
	}
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	protected void setExceptionDialog(Exception inc_exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		inc_exception.printStackTrace(pw);
		String stackTrace = sw.toString();
		initVars();
		createAndShowGUI(guiText_label_exceptionPrefix + inc_exception.getClass(), stackTrace);
	}
	
	protected void setErrorDialog(String inc_errorMessage) {
		initVars();
		createAndShowGUI(guiText_label_errorPrefix, inc_errorMessage);
	}
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private void createAndShowGUI(String inc_labelText, String inc_messageText) {
		label = new JLabel(inc_labelText);
		textArea.setText(inc_messageText);
		final JOptionPane pane = new JOptionPane(buildMainPanel(), JOptionPane.ERROR_MESSAGE, JOptionPane.PLAIN_MESSAGE);
		pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
		pane.setMessageType(PLAIN_MESSAGE);
		pane.setOptions(new Object[] {});		// Removes default JOptionPane buttons, so that custom ones may be used.
		dialog = pane.createDialog(null, guiText_dialogTitle);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wE) {
				// Do nothing as the JDialog is closing.
			}
			public void windowClosed(WindowEvent wE) {
				// Do nothing once the JDialog is closed.
			}
		});
		dialog.pack();
		dialog.validate();
		dialog.setLocationRelativeTo(hubFrame);
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_dialogCreated);
		dialog.setVisible(true);
	}
	
	private JPanel buildMainPanel() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints mainPanelConstraints = new GridBagConstraints();
		int currentGridX;
		int currentGridY;
		mainPanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.RAISED), guiText_dialogBorder));
		
		// Error label
		currentGridX = 0;
		currentGridY = 0;
		mainPanelConstraints.gridx = currentGridX;
		mainPanelConstraints.gridy = currentGridY;
		mainPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		mainPanelConstraints.insets = new Insets(5, 10, 0, 10);
		mainPanel.add(label, mainPanelConstraints);
		
		// Error message scrollpane
		currentGridX = 0;
		currentGridY++;
		JScrollPane textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		textAreaScrollPane.setPreferredSize(new Dimension(300, 200));
		mainPanelConstraints.gridx = currentGridX;
		mainPanelConstraints.gridy = currentGridY;
		mainPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		mainPanelConstraints.insets = new Insets(0, 10, 10, 10);
		mainPanel.add(textAreaScrollPane, mainPanelConstraints);
		
		// OK button
		currentGridX = 0;
		currentGridY++;
		mainPanelConstraints.gridx = currentGridX;
		mainPanelConstraints.gridy = currentGridY;
		mainPanelConstraints.anchor = GridBagConstraints.PAGE_START;
		mainPanelConstraints.insets = new Insets(0, 10, 10, 10);
		mainPanel.add(okButton, mainPanelConstraints);
		
		return mainPanel;
	}
	
	//-----------------------------------------------------------------//
	
}
