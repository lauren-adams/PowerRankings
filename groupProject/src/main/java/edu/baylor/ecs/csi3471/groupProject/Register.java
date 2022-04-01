package edu.baylor.ecs.csi3471.groupProject;

import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

import javax.swing.*;

public class Register extends JPanel {
	Integer passwordMinSize = 8;
	Integer passwordMaxSize = 20;
	Integer nameMinSize 	= 5;
	Integer nameMaxSize 	= 20;
	Integer minAge 			= 18;
	Integer maxAge 			= 200;
	Integer emailMinSize 	= 3;
	Integer emailMaxSize 	= 320;
	Integer IDSize			= 20;
	String delim = "/t";
	final RegisterForm registerForm = new RegisterForm(); 						// display registration form
	JFrame RegisterPage = new JFrame("Register"); 								// register page frame with name

	void beginRegistration() {
		RegisterPage.setExtendedState(JFrame.MAXIMIZED_BOTH); 					// make frame full screen

		RegisterPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JMenuBar registerMenuBar = new JMenuBar(); 								// title menu bar
		registerMenuBar.setOpaque(true); 										// makes title opaque
		registerMenuBar.setPreferredSize(new Dimension(200, 200)); 				// second parameter controls height

		JMenuItem title = new JMenuItem("REGISTRATION"); 						// title name
		title.setOpaque(true); 													// make opaque
		title.setFont(new Font("roboto condensed", Font.PLAIN, 50)); 			// font of title
		title.setBackground(Color.BLACK); 										// make title background black
		registerMenuBar.add(title); 											// add title to title menu bar

		verifyRegistrationForm(); 												// ensures user fills out form correctly

		RegisterPage.setJMenuBar(registerMenuBar);
		RegisterPage.getContentPane().add(registerForm);
		RegisterPage.setVisible(true);
	}

	// checks each text field in the form and ensures it meets the requirements
	void verifyRegistrationForm() {

		final JButton submitButton = new JButton("Submit");					// when clicked the form will be checked

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent submit) {
				boolean fail 	= false;
				boolean created = false;

				if (registerForm.getUserNameField().getText().isEmpty() || registerForm.getUserNameField().getText() == null) {
					fail = true;
				}
				else {
					created = usernameAnalysis();
				}
				if ((registerForm.getPasswordField().getText().isEmpty() || registerForm.getPasswordField().getText() == null) && created) {
					fail = true;
				}
				else {
					if(created) {
						created = passwordAnalysis();
					}
				}
				if ((registerForm.getAgeField().getText().isEmpty() || registerForm.getAgeField().getText() == null) && created) {
					fail = true;
				}
				else {
					if(created) {
						created = ageAnalysis(submitButton);
					}
				}
				if((registerForm.getEmailField().getText().isEmpty() || registerForm.getEmailField().getText() == null) && created) {
					fail = true;
				 }
				 else {
					 if(created) {
						 created = emailAnalysis();
					 }
				 }
				// if nickname field is not empty (this is optional so it can be empty)
				if (!registerForm.getNickNameField().getText().isEmpty() && created) {
					created = verifyName(registerForm.getUserNameField().getText());
					if (!created) {
						JOptionPane.showMessageDialog(registerForm,
								"Invalid  nick name: must be numbers and alphabetical characters with length >= 5 and length <= 20",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}

				if (fail) {
					JOptionPane.showMessageDialog(registerForm,
							"Mandatory fields: username, password, email, and age not inserted\n", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

				else {
					if(created) {
						int answer = JOptionPane.showConfirmDialog(null,
																   "Do you wish to confirm entered information?",
																   "Warning",
																    JOptionPane.YES_NO_OPTION);
						if(answer == 0) {
								//FileWriter out = new FileWriter("UserFile.tsv", true);

								//FIXME NO CHECK TO MAKE SURE USER ACTUALLY INPUT ITEMS
								//File tsvOut = new File("UserFile.tsv");

								User newUser = new User();
								newUser.setUsername(registerForm.getUserNameField().getText());
								newUser.setPassword(registerForm.getPasswordField().getText());
								newUser.setEmail(registerForm.getEmailField().getText());
								newUser.setName(registerForm.getNickNameField().getText());
								newUser.setAge(Integer.parseInt(registerForm.getAgeField().getText()));
								newUser.setBet(0);
								newUser.setCurrentVote("null");
								newUser.setFunds(500);
								newUser.setDescription("none");
								newUser.setAdmin(false);
								newUser.setVoted(false);


								RegisterDAO base = new RegisterDAO();
								base.writeToFile(newUser);
								///FUNCTION BEGIN



								///FUNCTION END

//								String [] data = new String[]{newUser.getUsername(), newUser.getPassword(), newUser.getEmail(), newUser.getName(),
//										String.valueOf(newUser.getAge()), String.valueOf(newUser.getFunds()), String.valueOf(newUser.getBet()),
//										String.valueOf(newUser.isVoted()), String.valueOf(newUser.isAdmin()), newUser.getDescription(),
//										newUser.getCurrentVote()};

								//out.append((String.join("\t", data)));
								//out.append("\n");

//								out.append(registerForm.getUserNameField().getText());
//								out.append(delim);
//								out.append(registerForm.getPasswordField().getText());
//								out.append(delim);
//								out.append(registerForm.getEmailField().getText());
//								out.append(delim);
//								out.append(registerForm.getNickNameField().getText());
//								out.append(delim);
//								out.append(registerForm.getAgeField().getText());
//								out.append(getUniqueId());
//								out.append('\n');
//								out.close();

						}
						RegisterPage.dispose();

//						Window win = SwingUtilities.getWindowAncestor(registerForm);
//						if (win != null) {
//							win.setVisible(false);
//						}
					}
				}
			}
		});

		submitButton.setBackground(Color.BLACK);
		registerForm.add(submitButton, BorderLayout.SOUTH);
	}

	// checks user name and nickname: ensures names meet requirements
	boolean verifyName(String name) {
		boolean created = verifyInputSizeIsValid(nameMinSize, nameMaxSize, name);
		if (created) {
			created = verifyInputIsDigitOrLetter(name);
		}
		return created;
	}

	// Checks password: ensures it meets requirements
	boolean verifyPassword(String password) {
		boolean created = verifyInputSizeIsValid(passwordMinSize, passwordMaxSize, password);
		if(created) {
			created = verifyInputIsDigitOrLetter(password);
		}
		return created;
	}

	// ensures: 18 >= age <= 200
	boolean verifyAge(String age) {
		boolean valid = verifyAgeLength(age);
		if(valid) {
			valid = verifyAgeIsNumeric(age);
			if(valid) {
				if(age.length() == 2) {
					valid = verifyOlderThanEighteen(age);
				}
				if(age.length() == 3) {
					valid = verifyYoungerThanTwoHundred(age);
				}
			}
		}
		return valid;
	}

	// ensures email is a letter, digit, '.', '@', or '_' and is within valid range
	boolean verifyEmail(String email) {
		boolean valid = verifyInputSizeIsValid(emailMinSize, emailMaxSize, email);
		if(!email.contains("@") || !email.contains(".")) {
			valid = false;
		}
		if(valid) {
			for (int i = 0; i < email.length(); i++) {
				if (!java.lang.Character.isLetterOrDigit(email.charAt(i)) && (!(email.charAt(i) == '.')) &&
						(!(email.charAt(i) == '@') && (!(email.charAt(i) == '_')))) {
					valid = false;
				}
			}
		}
		return valid;
	}

	// generates uniqueId associated with the user
	String getUniqueId() {
		String id = "";
		Random r = new Random();
		String validChars = "0123456789!@#$%&*abcdefghijklmnopqrstuvwzyzABCDEFGHIJKLMNOPQRSTVWXYZ";

		for(int i = 0; i < IDSize; i++) {
			id += validChars.charAt(r.nextInt(validChars.length()));
		}
		return id;
	}

	// checks input size and returns true if the input is within a valid range
	boolean verifyInputSizeIsValid(Integer minSize, Integer maxSize, String input) {
		boolean valid = false;
		if(input.length() >= minSize && input.length() <= maxSize) {
			valid = true;
		}
		return valid;
	}

	// ensures age is at least 2 characters and at most 3 characters
	boolean verifyAgeLength(String age) {
		boolean valid = true;
		if(age.length() < 2 || age.length() > 3) {
			return false;
		}
		return valid;
	}

	// ensures each character in the age is a digit
	boolean verifyAgeIsNumeric(String age) {
		boolean valid = true;
		for(int i = 0; i < age.length(); i++) {
			if (!java.lang.Character.isDigit(age.charAt(i))) {
				valid = false;
			}
		}
		return valid;
	}

	// ensures age is not less than 18
	boolean verifyOlderThanEighteen(String age) {
		boolean valid = true;
		if(age.charAt(0) == '1') {
			if(age.charAt(1) < '8') {
				valid = false;
			}
		}
		return valid;
	}

	// ensures age is not greater than 200
	boolean verifyYoungerThanTwoHundred(String age) {
		boolean valid = true;
		if(age.charAt(0) > '2') {
			valid = false;
		}
		return valid;
	}

	// checks each characters of a given text field (user name, password, or nick name)
	boolean verifyInputIsDigitOrLetter(String input) {
		boolean valid = true;
		for (int i = 0; i < input.length(); i++) {
			if (!java.lang.Character.isLetterOrDigit(input.charAt(i))) {
				valid = false;
			}
		}
		return valid;
	}

	// calls the name verification function: if invalid displays error message
	boolean usernameAnalysis() {
		boolean created =  verifyName(registerForm.getUserNameField().getText());
		if (!created) {
			JOptionPane.showMessageDialog(registerForm,
				"Invalid username: must be numbers and alphabetical characters with length >= 5 and length <= 20",
				"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return created;
	}

	// calls the password verification function: if invalid displays error message
	boolean passwordAnalysis() {
		boolean created = verifyPassword(registerForm.getPasswordField().getText());
		if (!created) {
			JOptionPane.showMessageDialog(registerForm,
				"Invalid password: must be numbers and alphabetical characters with length >= 8 and length <= 15",
				"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return created;
	}

	// calls the age verification function: if invalid displays error message and closes registration form
	boolean ageAnalysis(JButton sub) {
		boolean created = verifyAge(registerForm.getAgeField().getText());
		if(!created) {
			JOptionPane.showMessageDialog(registerForm,
					"Too young to register or entered bad age, goodbye!\n", "ERROR",
					JOptionPane.ERROR_MESSAGE);

//			Window win = SwingUtilities.getWindowAncestor(sub);
//
//			if (win != null) {
//				win.setVisible(false);
//			}
		}
		return created;
	}

	// calls the email verification function: if invalid displays error message
	boolean emailAnalysis() {
		boolean created = verifyEmail(registerForm.getEmailField().getText());
		if(!created) {
			JOptionPane.showMessageDialog(registerForm,
					"Invalid email\n", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		return created;
	}
}
