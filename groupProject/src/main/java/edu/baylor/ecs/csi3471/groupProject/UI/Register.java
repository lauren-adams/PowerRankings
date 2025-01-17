package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.Persistence.RegisterDAO;
import javax.swing.*;

public class Register {
	Integer passwordMinSize = 8;
	Integer passwordMaxSize = 20;
	Integer nameMinSize = 5;
	Integer nameMaxSize = 20;
	Integer minAge = 18;
	Integer maxAge = 200;
	Integer emailMinSize = 3;
	Integer emailMaxSize = 320;
	Integer IDSize = 20;
	String delim = "/t";
	private String username = RegisterPage.registerForm.getUserNameField().getText();
	private String password = RegisterPage.registerForm.getPasswordField().getText();
	private String age = RegisterPage.registerForm.getAgeField().getText();
	private String email = RegisterPage.registerForm.getEmailField().getText();
	private String nickname = RegisterPage.registerForm.getNickNameField().getText();

	public void beginRegistration() {
		boolean fail = false;
		boolean created = false;

		if (username.isEmpty() || username == null) {
			Runner.logger.warning("username is empty/null");
			fail = true;
		} else {
			created = usernameAnalysis();
		}

		if (password.isEmpty() || password == null) {
			Runner.logger.warning("password is empty/null");
			fail = true;
		} else {
			if (created) {
				created = passwordAnalysis();
			}
		}

		if (age.isEmpty() || age == null) {
			Runner.logger.warning("age is empty/null");
			fail = true;
		} else {
			if (created) {
				created = ageAnalysis();
			}
		}

		if (email.isEmpty() || email == null) {
			Runner.logger.warning("email is empty/null");
			fail = true;
		} else {
			if (created) {
				created = emailAnalysis();
			}
		}

		if (!nickname.isEmpty() && created) {
			created = verifyName(nickname);
			if (!created) {
				JOptionPane.showMessageDialog(RegisterPage.registerForm,
						"Invalid  nick name: must be numbers and alphabetical characters with length >= 5 and length <= 20",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (fail) {
			JOptionPane.showMessageDialog(RegisterPage.registerForm,
					"Mandatory fields: username, password, email, and age not inserted\n", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

		else {
			if (created) {
				int answer = JOptionPane.showConfirmDialog(null, "Do you wish to confirm entered information?",
						"Warning", JOptionPane.YES_NO_OPTION);
				
				if (answer == 0) {
					Runner.logger.info("Created new User " + username);
					User newUser = new User();
					newUser.setUsername(username);
					newUser.setPassword(password);
					newUser.setEmail(email);
					newUser.setName(nickname);
					newUser.setAge(Integer.parseInt(age));
					newUser.setBet(0);
					newUser.setCurrentVote("null");
					newUser.setFunds(500);
					newUser.setDescription("none");
					newUser.setAdmin(false);
					newUser.setVoted(false);

					RegisterDAO base = new RegisterDAO();
					base.writeToFile(newUser);
					
					RegisterPage.registerPage.setVisible(false);
				}
			}
		}
	}

	/**
	 * @param name user input name
	 * @return verification for if the name is valid
	 */
	// checks user name and nickname: ensures names meet requirements
	boolean verifyName(String name) {
		boolean created = verifyInputSizeIsValid(nameMinSize, nameMaxSize, name);
		if (created) {
			created = verifyInputIsDigitOrLetter(name);
		}
		return created;
	}

	// Checks password: ensures it meets requirements
	/**
	 * @param password user input password
	 * @return verification for if the password is valid
	 */
	boolean verifyPassword(String password) {
		boolean created = verifyInputSizeIsValid(passwordMinSize, passwordMaxSize, password);
		if (created) {
			created = verifyInputIsDigitOrLetter(password);
		}
		return created;
	}

	// ensures: 18 >= age <= 200
	/**
	 * @param age user input age
	 * @return verification for if the age is valid
	 */
	boolean verifyAge(String age) {
		boolean valid = verifyAgeLength(age);
		if (valid) {
			valid = verifyAgeIsNumeric(age);
			if (valid) {
				if (age.length() == 2) {
					valid = verifyOlderThanEighteen(age);
				}
				if (age.length() == 3) {
					valid = verifyYoungerThanTwoHundred(age);
				}
			}
		}
		return valid;
	}

	// ensures email is a letter, digit, '.', '@', or '_' and is within valid range
	/**
	 * @param email user input password
	 * @return verification for if the email is valid
	 */
	boolean verifyEmail(String email) {
		boolean valid = verifyInputSizeIsValid(emailMinSize, emailMaxSize, email);
		if (!email.contains("@") || !email.contains(".")) {
			valid = false;
		}
		if (valid) {
			for (int i = 0; i < email.length(); i++) {
				if (!java.lang.Character.isLetterOrDigit(email.charAt(i)) && (!(email.charAt(i) == '.'))
						&& (!(email.charAt(i) == '@') && (!(email.charAt(i) == '_')))) {
					valid = false;
				}
			}
		}
		return valid;
	}

	// checks input size and returns true if the input is within a valid range

	/**
	 * @param minSize length is greater than this number
	 * @param maxSize length is less than this number
	 * @param input user input
	 * @return returns if the length of input is good
	 */
	boolean verifyInputSizeIsValid(Integer minSize, Integer maxSize, String input) {
		boolean valid = false;
		if (input.length() >= minSize && input.length() <= maxSize) {
			valid = true;
		}
		return valid;
	}

	// ensures age is at least 2 characters and at most 3 characters

	/**
	 * @param age user input age
	 * @return if the age is long enough or short enough
	 */
	boolean verifyAgeLength(String age) {
		boolean valid = true;
		if (age.length() < 2 || age.length() > 3) {
			return false;
		}
		return valid;
	}

	// ensures each character in the age is a digit
	/**
	 * @param age user input age
	 * @return verifies if the age is a number
	 */
	boolean verifyAgeIsNumeric(String age) {
		boolean valid = true;
		for (int i = 0; i < age.length(); i++) {
			if (!java.lang.Character.isDigit(age.charAt(i))) {
				valid = false;
			}
		}
		return valid;
	}

	// ensures age is not less than 18
	/**
	 * @param age user input age
	 * @return verifies that the age is above 18
	 */
	boolean verifyOlderThanEighteen(String age) {
		boolean valid = true;
		if (age.charAt(0) == '1') {
			if (age.charAt(1) < '8') {
				valid = false;
			}
		}
		return valid;
	}

	// ensures age is not greater than 200
	/**
	 * @param age user input age
	 * @return verifies the user is younger than 200
	 */
	boolean verifyYoungerThanTwoHundred(String age) {
		boolean valid = true;
		if (age.charAt(0) > '2') {
			valid = false;
		}
		return valid;
	}

	/**
	 * @param input user input
	 * @return makes sure the input is alphanumeric
	 */
	// checks each characters of a given text field (user name, password, or nick
	// name)
	boolean verifyInputIsDigitOrLetter(String input) {
		boolean valid = true;
		for (int i = 0; i < input.length(); i++) {
			if (!java.lang.Character.isLetterOrDigit(input.charAt(i))) {
				valid = false;
			}
		}
		return valid;
	}

	/**
	 * @return ensures the username is valid
	 */
	// calls the name verification function: if invalid displays error message
	boolean usernameAnalysis() {
		boolean created = verifyName(username);
		if (!created) {
			JOptionPane.showMessageDialog(RegisterPage.registerForm,
					"Invalid username: must be numbers and alphabetical characters with length >= 5 and length <= 20",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return created;
	}

	/**
	 * @return ensures password is valid
	 */
	// calls the password verification function: if invalid displays error message
	boolean passwordAnalysis() {
		boolean created = verifyPassword(password);
		if (!created) {
			JOptionPane.showMessageDialog(RegisterPage.registerForm,
					"Invalid password: must be numbers and alphabetical characters with length >= 8 and length <= 15",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return created;
	}

	/**
	 * @return ensures age is valid
	 */
	// calls the age verification function: if invalid displays error message and
	// closes registration form
	boolean ageAnalysis() {
		boolean created = verifyAge(age);
		if (!created) {
			JOptionPane.showMessageDialog(RegisterPage.registerForm,
					"Too young to register or entered bad age, goodbye!\n", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return created;
	}

	/**
	 * @return ensures email is valid
	 */
	// calls the email verification function: if invalid displays error message
	boolean emailAnalysis() {
		boolean created = verifyEmail(email);
		if (!created) {
			JOptionPane.showMessageDialog(RegisterPage.registerForm, "Invalid email\n", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		return created;
	}
}
