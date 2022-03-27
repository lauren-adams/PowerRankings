import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class RegisterForm extends JPanel implements PropertyChangeListener {

	// Default form data
	private String userName = "";
	private String nickName = "";
	private String age = "";
	private String email = "";

	// Labels
	private JLabel userNameLabel;
	private JLabel nickNameLabel;
	private JLabel ageLabel;
	private JLabel emailLabel;

	// Label names
	private static String userNameString = "Username: ";
	private static String nickNameString = "Optional Nickname: ";
	private static String ageString = "Age: ";
	private static String emailString = "Email: ";

	// text field for the input
	private JFormattedTextField userNameField;
	private JFormattedTextField nickNameField;
	private JFormattedTextField ageField;
	private JFormattedTextField emailField;
	private JFormattedTextField passwordField;

	
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userNameLabel
	 */
	public JLabel getUserNameLabel() {
		return userNameLabel;
	}

	/**
	 * @param userNameLabel the userNameLabel to set
	 */
	public void setUserNameLabel(JLabel userNameLabel) {
		this.userNameLabel = userNameLabel;
	}

	/**
	 * @return the nickNameLabel
	 */
	public JLabel getNickNameLabel() {
		return nickNameLabel;
	}

	/**
	 * @param nickNameLabel the nickNameLabel to set
	 */
	public void setNickNameLabel(JLabel nickNameLabel) {
		this.nickNameLabel = nickNameLabel;
	}

	/**
	 * @return the ageLabel
	 */
	public JLabel getAgeLabel() {
		return ageLabel;
	}

	/**
	 * @param ageLabel the ageLabel to set
	 */
	public void setAgeLabel(JLabel ageLabel) {
		this.ageLabel = ageLabel;
	}

	/**
	 * @return the emailLabel
	 */
	public JLabel getEmailLabel() {
		return emailLabel;
	}

	/**
	 * @param emailLabel the emailLabel to set
	 */
	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
	}

	/**
	 * @return the userNameString
	 */
	public static String getUserNameString() {
		return userNameString;
	}

	/**
	 * @param userNameString the userNameString to set
	 */
	public static void setUserNameString(String userNameString) {
		RegisterForm.userNameString = userNameString;
	}

	/**
	 * @return the nickNameString
	 */
	public static String getNickNameString() {
		return nickNameString;
	}

	/**
	 * @param nickNameString the nickNameString to set
	 */
	public static void setNickNameString(String nickNameString) {
		RegisterForm.nickNameString = nickNameString;
	}

	/**
	 * @return the ageString
	 */
	public static String getAgeString() {
		return ageString;
	}

	/**
	 * @param ageString the ageString to set
	 */
	public static void setAgeString(String ageString) {
		RegisterForm.ageString = ageString;
	}

	/**
	 * @return the emailString
	 */
	public static String getEmailString() {
		return emailString;
	}

	/**
	 * @param emailString the emailString to set
	 */
	public static void setEmailString(String emailString) {
		RegisterForm.emailString = emailString;
	}

	/**
	 * @return the userNameField
	 */
	public JFormattedTextField getUserNameField() {
		return userNameField;
	}

	/**
	 * @param userNameField the userNameField to set
	 */
	public void setUserNameField(JFormattedTextField userNameField) {
		this.userNameField = userNameField;
	}

	/**
	 * @return the nickNameField
	 */
	public JFormattedTextField getNickNameField() {
		return nickNameField;
	}

	/**
	 * @param nickNameField the nickNameField to set
	 */
	public void setNickNameField(JFormattedTextField nickNameField) {
		this.nickNameField = nickNameField;
	}

	/**
	 * @return the ageField
	 */
	public JFormattedTextField getAgeField() {
		return ageField;
	}

	/**
	 * @param ageField the ageField to set
	 */
	public void setAgeField(JFormattedTextField ageField) {
		this.ageField = ageField;
	}

	/**
	 * @return the emailField
	 */
	public JFormattedTextField getEmailField() {
		return emailField;
	}

	/**
	 * @param emailField the emailField to set
	 */
	public void setEmailField(JFormattedTextField emailField) {
		this.emailField = emailField;
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

	public RegisterForm() {
		super(new BorderLayout());

		// generates all labels
		userNameLabel = new JLabel(userNameString);
		nickNameLabel = new JLabel(nickNameString);
		ageLabel = new JLabel(ageString);
		emailLabel = new JLabel(emailString);

		userNameField = new JFormattedTextField();
		userNameField.setValue(new String(userName));
		userNameField.setColumns(30);
		userNameField.addPropertyChangeListener("value", (PropertyChangeListener) this);

		nickNameField = new JFormattedTextField();
		nickNameField.setValue(new String(nickName));
		nickNameField.addPropertyChangeListener("value", (PropertyChangeListener) this);

		emailField = new JFormattedTextField();
		emailField.setValue(new String(email));
		emailField.addPropertyChangeListener("value", (PropertyChangeListener) this);

		ageField = new JFormattedTextField();
		ageField.setValue(new String(age));

		userNameLabel.setLabelFor(userNameField);
		emailLabel.setLabelFor(emailField);
		ageLabel.setLabelFor(ageField);
		nickNameLabel.setLabelFor(nickNameField);

		// generate the label panel
		JPanel labelPane = new JPanel(new GridLayout(0, 1));
		labelPane.add(userNameLabel);
		labelPane.add(nickNameLabel);
		labelPane.add(emailLabel);
		labelPane.add(ageLabel);

		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(userNameField);
		fieldPane.add(nickNameField);
		fieldPane.add(ageField);
		fieldPane.add(emailField);

		// Put the panels in this panel, labels on left, text fields on right.
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(labelPane, BorderLayout.CENTER);
		add(fieldPane, BorderLayout.LINE_END);
	}

	public void propertyChange(PropertyChangeEvent e) {

		Object source = e.getSource();

		if (source == userNameField) {
			userName = userNameField.getText();
		} else if (source == nickNameField) {
			nickName = nickNameField.getText();
		} else if (source == emailField) {
			email = emailField.getText();
		} else if (source == ageField) {
			age = ageField.getText();
		}
	}

}
