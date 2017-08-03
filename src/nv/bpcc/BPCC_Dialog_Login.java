/**
 *  This class presents the user with a dialog that prompts for login credentials.
 *  Login credentials will be checked against credentials retrieved from a config file.		// TODO:  Name config file.
 */

package nv.bpcc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.invoke.MethodHandles;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class BPCC_Dialog_Login extends JOptionPane implements ActionListener {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	private final static String classNameForLogger = MethodHandles.lookup().lookupClass().getName().toString();
	
	private final String guiText_dialogTitle = BPCC_Util.getApplicationTitle();
	
	private final String guiText_mainPanelBorder = "Login";
	
	private final String guiText_usernameLabel = "Username:";
	
	private final String guiText_usernameComboBox = "XXXXXXXXXXX";
	
	private final String guiText_passwordLabel = "Password:";
	
	private final String guiText_loginButton = "Login";
	
	private final String guiText_cancelButton = "Cancel";
	
	private final String logMessage_dialogCreated = "Login dialog created and displayed.";
	private final String logMessage_dialogClosed = "Login dialog closed.";
	
	private final String logMessage_loginButtonClicked = "User clicked on \"Login\" button.";
	
	private final String logMessage_cancelButtonClicked = "User clicked on \"Cancel\" button.";
	
	//-----------------------------------------------------------------//
	
	/** Declare global variables **/
	
	private JOptionPane pane;
	
	private JDialog dialog;
	
	private JLabel usernameLabel;
	
	private JComboBox<String> usernameComboBox;
	
	private JLabel passwordLabel;
	
	private JTextField passwordTextField;
	
	private JButton loginButton;
	
	private JButton cancelButton;
	
	//-----------------------------------------------------------------//
	
	/** Initialize global variables **/
	
	private void initVars() {
		usernameLabel = new JLabel(guiText_usernameLabel);
		
		String[] usernameList = getUsernameList();
		usernameComboBox = new JComboBox<String>(usernameList);
		usernameComboBox.setPrototypeDisplayValue(guiText_usernameComboBox);
		// TODO:  Add an ItemChangedListener to usernameComboBox.
		
		passwordLabel = new JLabel(guiText_passwordLabel);
		
		passwordTextField = new JTextField(10);
		// TODO:  Add a FocusListener to passwordTextField.
		
		loginButton = new JButton(guiText_loginButton);
		loginButton.addActionListener(this);
		
		cancelButton = new JButton(guiText_cancelButton);
		cancelButton.addActionListener(this);
	}
	
	//-----------------------------------------------------------------//
	
	/** Abstract methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Implemented methods **/
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			dialog.dispose();
			// Log user interaction with loginButton.
			BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_loginButtonClicked);
			// Log dialog closed event.
			BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_dialogClosed);
			// TODO:  User to be logged in as should be returned and BPCC_Hub should initiate.
		}
		else if (e.getSource() == cancelButton) {
			dialog.dispose();
			// Log user interaction with cancelButton.
			BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_cancelButtonClicked);
			// Log dialog closed event.
			BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_dialogClosed);
			// TODO:  BPCC_Hub.end() should be called on return.
		}
	}
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	protected void showLoginDialog() {
		initVars();
		createAndShowGUI();
	}
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private void createAndShowGUI() {
		pane = new JOptionPane(buildMainPanel(), JOptionPane.ERROR_MESSAGE, JOptionPane.PLAIN_MESSAGE);
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
		dialog.setLocationRelativeTo(BPCC_Util.getHubFrame());
		// Log dialog created event.
		BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_dialogCreated);
		dialog.setVisible(true);
	}
	
	private JPanel buildMainPanel() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints mainPanelConstraints = new GridBagConstraints();
		int currentGridX;
		int currentGridY;
		
		// Login panel
		currentGridX = 0;
		currentGridY = 0;
		mainPanelConstraints.gridx = currentGridX;
		mainPanelConstraints.gridy = currentGridY;
		mainPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		mainPanel.add(buildLoginPanel(), mainPanelConstraints);
		
		// Button panel
		currentGridX = 0;
		currentGridY++;
		mainPanelConstraints.gridx = currentGridX;
		mainPanelConstraints.gridy = currentGridY;
		mainPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		mainPanel.add(buildButtonPanel(), mainPanelConstraints);
		
		return mainPanel;
	}
	
	private JPanel buildLoginPanel() {
		JPanel loginPanel = new JPanel(new GridBagLayout());
		GridBagConstraints loginPanelConstraints = new GridBagConstraints();
		int currentGridX;
		int currentGridY;
		loginPanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.RAISED), guiText_mainPanelBorder));
		
		// Username label
		currentGridX = 0;
		currentGridY = 0;
		loginPanelConstraints.gridx = currentGridX;
		loginPanelConstraints.gridy = currentGridY;
		loginPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		loginPanelConstraints.insets = new Insets(5, 10, 0, 10);
		loginPanel.add(usernameLabel, loginPanelConstraints);
		
		// Username combobox
		currentGridX = 0;
		currentGridY++;
		loginPanelConstraints.gridx = currentGridX;
		loginPanelConstraints.gridy = currentGridY;
		loginPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		loginPanelConstraints.insets = new Insets(0, 10, 10, 10);
		loginPanel.add(usernameComboBox, loginPanelConstraints);
		
		// Password label
		currentGridX = 0;
		currentGridY++;
		loginPanelConstraints.gridx = currentGridX;
		loginPanelConstraints.gridy = currentGridY;
		loginPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		loginPanelConstraints.insets = new Insets(10, 10, 10, 10);
		loginPanel.add(passwordLabel, loginPanelConstraints);
		
		// Password textfield
		currentGridX = 0;
		currentGridY++;
		loginPanelConstraints.gridx = currentGridX;
		loginPanelConstraints.gridy = currentGridY;
		loginPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		loginPanelConstraints.insets = new Insets(0, 10, 10, 10);
		loginPanel.add(passwordTextField, loginPanelConstraints);
		
		return loginPanel;
	}
	
	private JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints buttonPanelConstraints = new GridBagConstraints();
		int currentGridX;
		int currentGridY;
		
		// Login button
		currentGridX = 0;
		currentGridY = 0;
		buttonPanelConstraints.gridx = currentGridX;
		buttonPanelConstraints.gridy = currentGridY;
		buttonPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		buttonPanelConstraints.insets = new Insets(10, 10, 10, 5);
		buttonPanel.add(loginButton, buttonPanelConstraints);
		
		// Cancel button
		currentGridX++;
		buttonPanelConstraints.gridx = currentGridX;
		buttonPanelConstraints.gridy = currentGridY;
		buttonPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		buttonPanelConstraints.insets = new Insets(10, 5, 10, 10);
		buttonPanel.add(cancelButton, buttonPanelConstraints);
		
		return buttonPanel;
	}
	
	private String[] getUsernameList() {
		String[] usernameList = {"admin", "guest"};		// TODO:  Collect list of usernames from config file.
		return usernameList;
	}
	
	//-----------------------------------------------------------------//
	
}
