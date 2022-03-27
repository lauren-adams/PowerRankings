
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;


public class LoginForm extends JPanel implements PropertyChangeListener{
	private String username = "";
	private String password = "";

    // Labels 
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    
    // Label names
    private static String usernameString 	= "Username: ";
    private static String passwordString 	= "Password: ";

    // text field for the input
    private JFormattedTextField usernameField;
	private JFormattedTextField passwordField;

	
	
    /**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the usernameLabel
	 */
	public JLabel getUsernameLabel() {
		return usernameLabel;
	}

	/**
	 * @param usernameLabel the usernameLabel to set
	 */
	public void setUsernameLabel(JLabel usernameLabel) {
		this.usernameLabel = usernameLabel;
	}

	/**
	 * @return the passwordLabel
	 */
	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	/**
	 * @param passwordLabel the passwordLabel to set
	 */
	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	/**
	 * @return the usernameString
	 */
	public static String getUsernameString() {
		return usernameString;
	}

	/**
	 * @param usernameString the usernameString to set
	 */
	public static void setUsernameString(String usernameString) {
		LoginForm.usernameString = usernameString;
	}

	/**
	 * @return the passwordString
	 */
	public static String getPasswordString() {
		return passwordString;
	}

	/**
	 * @param passwordString the passwordString to set
	 */
	public static void setPasswordString(String passwordString) {
		LoginForm.passwordString = passwordString;
	}

	/**
	 * @return the usernameField
	 */
	public JFormattedTextField getUsernameField() {
		return usernameField;
	}

	/**
	 * @param usernameField the usernameField to set
	 */
	public void setUsernameField(JFormattedTextField usernameField) {
		this.usernameField = usernameField;
	}

	/**
	 * @return the passwordField
	 */
	public JFormattedTextField getPasswordField() {
		return passwordField;
	}

	/**
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField(JFormattedTextField passwordField) {
		this.passwordField = passwordField;
	}

	public LoginForm() {
    	
    	super(new BorderLayout());
        
        usernameLabel 	= new JLabel(usernameString);
        passwordLabel 	= new JLabel(passwordString);
        
        usernameField 	= new JFormattedTextField();
        usernameField.setValue(new String(username));
        usernameField.setColumns(70);								// changes the width of textFields
        usernameField.addPropertyChangeListener("value", this);
        
        passwordField = new JFormattedTextField();
        passwordField.setValue(new String(password));
        passwordField.addPropertyChangeListener("value",this);
        
        usernameLabel.setLabelFor(usernameField);
        passwordLabel.setLabelFor(passwordField);
       
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(usernameLabel);
        labelPane.add(passwordLabel);
      
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(usernameField);
        fieldPane.add(passwordField);
        
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
    }
    
    public void propertyChange(PropertyChangeEvent e) {
       
    	Object source = e.getSource();
    	
        if (source == usernameField) {
            username = usernameField.getText(); 
        } 
        else if (source == passwordField) {
            password = passwordField.getText();
        } 
    }
}

