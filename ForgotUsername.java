import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ForgotUsername extends JPanel {
    public ForgotUsername(){
        JTextField emailField = new JTextField(30);
        JLabel username = new JLabel("");
        JButton submit = new JButton("Submit");

        this.add(new JLabel("Email: "));
        this.add(emailField);
        this.add(username);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean found = false;
                try {
                    Scanner scanner = new Scanner(new FileReader("UserFile.csv"));
                    while(scanner.hasNextLine()){
                        String line = scanner.nextLine();
                        String [] data = line.split(";");
                        if(data[2].equals(emailField.getText())){
                            username.setText(data[0]);
                            found = true;
                        }
                    }
                    if(!found){
                        username.setText("User not found");
                    }

                } catch (FileNotFoundException f) {
                    //idk anymore
                }
            }
        });

        this.add(submit);
        this.add(username);


    }



    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableFilterDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //Create and set up the content pane.
        ForgotUsername newContentPane = new ForgotUsername();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
