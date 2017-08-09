/**
 *  This class presents the user with a dialog that prompts for login credentials.
 *  Available login credentials will be retrieved from BPCC_Util.
 */

package nv.bpcc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
	
	private final String logMessage_xButtonClicked = "User clicked on the \"X\" button.";
	
	private final String logMessage_loginButtonClicked = "User clicked on the \"Login\" button.";
	
	private final String logMessage_cancelButtonClicked = "User clicked on the \"Cancel\" button.";
	
	private final String logMessage_incorrectPassword = "Incorrect password.";
	
	//-----------------------------------------------------------------//
	
	/** Declare global variables **/
	
	private JOptionPane pane;
	
	private JDialog dialog;
	
	private ArrayList<BPCC_AppUser> appUserList;
	private BPCC_AppUser selectedAppUser;
	
	private JLabel usernameLabel;
	
	private JComboBox<String> usernameComboBox;
	
	private JLabel passwordLabel;
	
	private JPasswordField passwordTextField;
	
	private JButton loginButton;
	
	private JButton cancelButton;
	
	private String usernameForLogin;
	
	//-----------------------------------------------------------------//
	
	/** Initialize global variables **/
	
	private void initVars() {
		appUserList = BPCC_Util.getAppUserList();
		selectedAppUser = appUserList.get(0);
		
		usernameLabel = new JLabel(guiText_usernameLabel);
		
		usernameComboBox = new JComboBox<String>(getUsernameList());
		usernameComboBox.setPrototypeDisplayValue(guiText_usernameComboBox);
		usernameComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedAppUser = appUserList.get(usernameComboBox.getSelectedIndex());
				if (selectedAppUser.hasPassword()) {
					passwordTextField.setText("");
					passwordTextField.setEditable(true);
				}
				else {
					passwordTextField.setText("");
					passwordTextField.setEditable(false);
				}
			}
		});
		
		passwordLabel = new JLabel(guiText_passwordLabel);
		
		passwordTextField = new JPasswordField(10);
		if (selectedAppUser.hasPassword()) {
			passwordTextField.setText("");
			passwordTextField.setEditable(true);
		}
		else {
			passwordTextField.setText("");
			passwordTextField.setEditable(false);
		}
		
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
			// Log user interaction with loginButton.
			BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_loginButtonClicked);
			closeDialog(1);
		}
		
		else if (e.getSource() == cancelButton) {
			// Log user interaction with cancelButton.
			BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_cancelButtonClicked);
			closeDialog(0);
		}
	}
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	protected void showLoginDialog() {
		initVars();
		createAndShowGUI();
	}
	
	protected String getUsernameForLogin() {
		return usernameForLogin;
	}
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	private void closeDialog(int inc_closeCondition) {
		if (inc_closeCondition == 1) {
			// Log in to application.
			if (selectedAppUser.hasPassword()) {
				if (selectedAppUser.getPassword().equals(String.valueOf(passwordTextField.getPassword()))) {
					usernameForLogin = selectedAppUser.getUsername();
					// Log dialog closed event.
					BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_dialogClosed);
					dialog.dispose();
				}
				else {
					// Log incorrect password error message.
					BPCC_Logger.logErrorMessage(classNameForLogger, logMessage_incorrectPassword, 0);
				}
			}
			else {
				usernameForLogin = selectedAppUser.getUsername();
				// Log dialog closed event.
				BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_dialogClosed);
				dialog.dispose();
			}
		}
		else if (inc_closeCondition == 0) {
			// Exit application, usernameForLogin will be left null.
			// Log dialog closed event.
			BPCC_Logger.logInfoMessage(classNameForLogger, logMessage_dialogClosed);
			dialog.dispose();
		}
	}
	
	private void createAndShowGUI() {
		pane = new JOptionPane(buildMainPanel(), JOptionPane.ERROR_MESSAGE, JOptionPane.PLAIN_MESSAGE);
		pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
		pane.setMessageType(PLAIN_MESSAGE);
		pane.setOptions(new Object[] {});		// Removes default JOptionPane buttons, so that custom ones may be used.
		
		dialog = pane.createDialog(null, guiText_dialogTitle);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wE) {
				// Log user interaction with X button.
				BPCC_Logger.logDebugMessage(classNameForLogger, logMessage_xButtonClicked);
				closeDialog(0);
			}
			
			public void windowClosed(WindowEvent wE) {
				// Do nothing once dialog is closed.
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
		
		// Password label
		currentGridX++;
		loginPanelConstraints.gridx = currentGridX;
		loginPanelConstraints.gridy = currentGridY;
		loginPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		loginPanelConstraints.insets = new Insets(5, 10, 0, 10);
		loginPanel.add(passwordLabel, loginPanelConstraints);
		
		// Username combobox
		currentGridX = 0;
		currentGridY++;
		loginPanelConstraints.gridx = currentGridX;
		loginPanelConstraints.gridy = currentGridY;
		loginPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		loginPanelConstraints.insets = new Insets(0, 10, 10, 10);
		loginPanel.add(usernameComboBox, loginPanelConstraints);
		
		// Password textfield
		currentGridX++;
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
		buttonPanelConstraints.insets = new Insets(10, 10, 0, 5);
		buttonPanel.add(loginButton, buttonPanelConstraints);
		
		// Cancel button
		currentGridX++;
		buttonPanelConstraints.gridx = currentGridX;
		buttonPanelConstraints.gridy = currentGridY;
		buttonPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		buttonPanelConstraints.insets = new Insets(10, 5, 0, 10);
		buttonPanel.add(cancelButton, buttonPanelConstraints);
		
		return buttonPanel;
	}
	
	private String[] getUsernameList() {
		String[] usernameList = new String[BPCC_Util.getAppUserList().size()];
		for (int i = 0; i < usernameList.length; i++) {
			usernameList[i] = BPCC_Util.getAppUserList().get(i).getUsername();
		}
		return usernameList;
	}
	
	//-----------------------------------------------------------------//
	
}
