import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Login {
	 final static private LoginForm loginForm = new LoginForm();
	 final static private String delim = "\t";
	
	public static void beginLoginProcess() {
		
		final JFrame loginPage = new JFrame("Login");						// login page frame with name
		loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// closes frame upon clicking 'x'
		loginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);					// make frame full screen
		
		JMenuBar loginMenuBar = new JMenuBar();    							// title menu bar
		loginMenuBar.setOpaque(true);										// make opaque
		loginMenuBar.setPreferredSize(new Dimension(200, 200));				// second parameter controls height
	    		
	    JMenuItem title = new JMenuItem("LOGIN");							// title name
	    title.setOpaque(true);												// make opaque
	    title.setFont(new Font("roboto condensed", Font.PLAIN, 50));		// font of title
	    title.setBackground(Color.BLACK);									// make title background black
	    loginMenuBar.add(title);											// add title to title menu bar
	    
	    // make the login form 
	    loginForm.setPreferredSize(new Dimension(2000, 180));
	    loginForm.setLayout(new GridLayout(5, 1));							// sets the number of rows, columns 
		
	    JButton submitButton = new JButton("Submit");						// buttons in the login page
	    submitButton.setBackground(Color.black);
	    JButton registerButton = new JButton("Register");
	    registerButton.setBackground(Color.black);
	    JButton forgotPasswordButton = new JButton("Forgot Password");
	    forgotPasswordButton.setBackground(Color.black);
	    JButton forgotUsernameButton = new JButton("Forgot Username");
	    forgotUsernameButton.setBackground(Color.black);
	    
	    submitButton.addActionListener(new ActionListener() {				
	    	public void actionPerformed(ActionEvent submit) {
	    		boolean fail = false;
	    		boolean pass = false;
	    		
				if (loginForm.getUsernameField().getText().isEmpty() || loginForm.getUsernameField().getText() == null) {
					fail = true;
				} 
				else {
					pass = validateUsername(loginForm.getUsernameField().getText());
				}
				
				if(loginForm.getPasswordField().getText().isEmpty() || loginForm.getPasswordField().getText() == null) {
					fail = true;
				}
				else {
					validatePassword(loginForm.getPasswordField().getText());
				}
				if(pass) {
					JOptionPane.showMessageDialog(loginForm,"Creditials are correct!","WELCOME", JOptionPane.ERROR_MESSAGE);
					// user is now logged in activate who is online
				}
				
				if(fail) {
					JOptionPane.showMessageDialog(loginForm,"Invalid username or password","ERROR", JOptionPane.ERROR_MESSAGE);
				}
	    	}
	    });
	   
	    forgotPasswordButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent forgotPassword) {
	    		JFrame frame = new JFrame("Forgot Password");				// creating instance of JFrame
	    	    frame.setSize(500, 500);									// 500 width and 500 height
	    	    frame.setLayout(new GridLayout(3, 2));		
	    	    frame.setVisible(true);										// making the frame visible
	    	       
	    	    final JLabel label = new JLabel("Please enter username associated with your account: ");
	    	    label.setBounds(100, 110, 100, 40);
	    	    
	    	    JButton submit = new JButton("Submit");						// creating instance of JButton
	    	    submit.setBounds(100, 70, 100, 40);							// x axis, y axis, width, height
	    
	    	    final JLabel username = new JLabel("");
	    	    final JTextField userNameField = new JTextField(30);
	    	    
	    	    submit.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    Boolean found = false;
	                    try {
	                        Scanner scanner = new Scanner(new FileReader("UserFile.tsv"));
	                        while(scanner.hasNextLine()){
	                            String line 	= scanner.nextLine();
	                            String [] data 	= line.split(delim);
	                            
	                            if(data[0].equals(userNameField.getText())){
	                                username.setText(data[0]);
	                                found = true;
	                            }
	                        }
	                        if(!found){
	                            username.setText("User not found");
	                        }
	                        else {
	                        	// logging you into the system
	                        	JOptionPane.showMessageDialog(loginForm,"Logging you in :)","WELCOME", JOptionPane.PLAIN_MESSAGE);
	                        }

	                    } catch (FileNotFoundException f) {
	                       // BAD FILE
	                    }
	                }
	            });

	    	    frame.add(label);
	    	    frame.add(userNameField);
	    	    frame.add(submit);							// adding button in JFrame
	    	 
	    	  }
	    });
	    
	    
	    forgotUsernameButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent forgotUsername) {
	    		JFrame frame = new JFrame("Forgot Username");				// creating instance of JFrame
	    	    frame.setSize(500, 500);									// 500 width and 500 height
	    	    frame.setLayout(new GridLayout(3, 2));		
	    	    frame.setVisible(true);										// making the frame visible
	    	       
	    	    final JLabel label = new JLabel("Please enter Email associated with account:");
	    	    label.setBounds(100, 110, 100, 40);
	    	    
	    	    JButton submit = new JButton("Submit");						// creating instance of JButton
	    	    submit.setBounds(100, 70, 100, 40);							// x axis, y axis, width, height
	    	    
	    	    final JLabel email = new JLabel("");
	    	    final JTextField emailField = new JTextField(30);
	    	    
	    	    submit.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    Boolean found = false;
	                    try {
	                    	Scanner scanner = new Scanner(new FileReader("UserFile.csv"));
	                        while(scanner.hasNextLine()){
	                        	String line = scanner.nextLine();
	                            String [] data = line.split("\t");
	 
	                            if(data[2].equals(emailField.getText())){
	                                email.setText(data[0]);
	                                found = true;
	                            }
	                        }
	                        if(!found){
	                            email.setText("Email not found");
	                        }

	                    } catch (FileNotFoundException f) {}
	                }
	            });

	    	    frame.add(label);
	    	    frame.add(emailField);
	    	    frame.add(submit);				
	    	  }
	    });
	   
	    registerButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent r) {
	    		Register register = new Register();
				register.beginRegistration();
	    	}
	    });
	    
	    loginForm.add(submitButton);
	    loginForm.add(registerButton);
	    loginForm.add(forgotUsernameButton);
	    loginForm.add(forgotPasswordButton);
	    
	    loginPage.getContentPane().add(loginForm, BorderLayout.CENTER);
	    loginPage.setJMenuBar(loginMenuBar);
		loginPage.setVisible(true);
	}
	
	// searches database for user name returns true if user name is found
	private static boolean validateUsername(String username) {
		boolean found = false;
		try {
        	Scanner scanner = new Scanner(new FileReader("UserFile.tsv"));
            while(scanner.hasNextLine()){
            	String line = scanner.nextLine();
                String [] data = line.split(delim);
                if(data[0].equals(loginForm.getUsernameField().getText())){
                    found = true;
                }
            }
        } 
		catch (FileNotFoundException f) {}
		return found;
	}

	// searches data base for the password entered by the user
	private static boolean validatePassword(String password) {
		boolean found = false;
		try {
        	Scanner scanner = new Scanner(new FileReader("UserFile.tsv"));
            while(scanner.hasNextLine()){
            	String line = scanner.nextLine();
                String [] data = line.split(delim);
                if(data[1].equals(loginForm.getPasswordField().getText())){
                    found = true;
                }
            }
        } 
		catch (FileNotFoundException f) {}
		return found;
	}	
}
