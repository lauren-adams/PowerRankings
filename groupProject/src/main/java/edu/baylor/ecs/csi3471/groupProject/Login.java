package edu.baylor.ecs.csi3471.groupProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login {
	static Database d = new Database();
	final static private LoginForm loginForm = new LoginForm();
	final static private String delim = "\t";
	
    public void beginLoginProcess() {
        final JFrame loginPage = new JFrame("Fantasy Fight Club Login");	// login page frame with name
        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// closes frame upon clicking 'x'
        loginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);					// make full screen
    
        JMenuBar loginMenuBar = new JMenuBar();    							// title (menu bar because better formatting)
        loginMenuBar.setFont(new Font("sans-serif", Font.PLAIN, 12));		// title font
        loginMenuBar.setOpaque(true);										// make opaque
        loginMenuBar.setPreferredSize(new Dimension(200, 200));				// second parameter controls height of frame ignore first parameter

        JMenuItem title = new JMenuItem("LOGIN");							// title name
        title.setOpaque(true);									
        title.setFont(new Font("roboto condensed", Font.PLAIN, 50));		// title font
        title.setBackground(Color.decode("#051821"));						// title background color
        title.setForeground(Color.WHITE);									// title font color
      
        loginMenuBar.add(title);											

        final LoginForm loginForm = new LoginForm();
        loginForm.setPreferredSize(new Dimension(2000, 180));
        loginForm.setLayout(new GridLayout(5, 1));							// sets the number of rows, columns

        JButton submitButton = new JButton("Submit");						// buttons in the login page
        submitButton.setBackground(Color.decode("#1A4645"));
        submitButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
        submitButton.setForeground(Color.WHITE);
       
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(Color.decode("#266867"));
        registerButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
        registerButton.setForeground(Color.WHITE);
        
        JButton forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.setBackground(Color.decode("#F58800"));
        forgotPasswordButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
        forgotPasswordButton.setForeground(Color.WHITE);
        
        JButton forgotUsernameButton = new JButton("Forgot Username");
        forgotUsernameButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
        forgotUsernameButton.setBackground(Color.decode("#F8BC24"));
        forgotUsernameButton.setForeground(Color.WHITE);

        // confirms the users input
        submitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent submit) {
            	boolean fail = false;
            	try {
                	
                	if(validateLogin(loginForm.getUsernameField().getText(), loginForm.getPasswordField().getText())) {
                		loginPage.setVisible(false);

                        //create user here
                        FileInputStream fs= null;
                        try {
                            fs = new FileInputStream("UserFile.tsv");
                            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
                            for(int i = 0; i < Database.userLine; ++i){
                                br.readLine();
                            }

                            String lineIWant = br.readLine();
                            String [] data = lineIWant.split(delim);
                            Main.curUser = new User(data);

                        } 
                        catch (IOException e) {
                            e.printStackTrace();
                        }


                		HomePage h = new HomePage();
                		h.createAndShowGUI(loginForm.getUsernameField().getText());
                	}
                	else {
                        fail = true;
                	}
                } catch (NullPointerException e) { }

                if (loginForm.getUsernameField().getText().isEmpty()) {
                    fail = true;
                }
                else {
                    d.validateUsername(loginForm.getUsernameField().getText());
                }
                if(loginForm.getPasswordField().getText().isEmpty()) {
                    fail = true;
                }
                else {
                    d.validatePassword(loginForm.getUsernameField().getText(), loginForm.getPasswordField().getText());
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

                final JLabel label = new JLabel("Please enter username: ");
                label.setBounds(100, 110, 100, 40);

                JButton submit = new JButton("Submit");						// creating instance of JButton
                submit.setBounds(100, 70, 100, 40);							// x axis, y axis, width, height

                final JLabel username = new JLabel("");
                final JTextField emailField = new JTextField(30);

                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Boolean found = false;

                        try {
                            Scanner scanner = new Scanner(new FileReader("UserFile.tsv"));
                            
                            while(scanner.hasNextLine()){
                                String line 	= scanner.nextLine();
                                String [] data 	= line.split(";");
                                
                                if(data[2].equals(emailField.getText())){
                                    username.setText(data[0]);
                                    found = true;
                                }
                            }
                            if(!found){
                                username.setText("User not found");
                            }
                        } catch (FileNotFoundException f) { }
                    }
                });

                frame.add(label);
                frame.add(emailField);
                frame.add(submit);							// adding button in JFrame
            }
        });

        forgotUsernameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent forgotUsername) {
                ForgotUsername forgotUser = new ForgotUsername();
                forgotUser.createAndShowGUI();

            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                loginPage.setVisible(false);
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

    static boolean validateLogin(String username, String password) {
    	return d.validateUsername(username) && d.validatePassword(username, password);
    }
}
