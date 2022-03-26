import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class VotingBooth extends JPanel {
    JLabel label;

    JFrame frame;

    String simpleDialogDesc = "";

    public VotingBooth(JFrame frame) {
        this.frame = frame;
        JLabel title;

        // Create the components.
        JPanel choicePanel = createSimpleDialogBox();

        System.out.println("passed createSimpleDialogBox");

        title = new JLabel("Click the \"Vote\" button"
                + " once you have selected a candidate.", JLabel.CENTER);

        label = new JLabel("Vote now!", JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        choicePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        // Set the layout.
        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(label, BorderLayout.SOUTH);
        add(choicePanel, BorderLayout.CENTER);
    }

    void setLabel(String newText) {
        label.setText(newText);
    }

    private JPanel createSimpleDialogBox() {
        final int numButtons = 3;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];

        final ButtonGroup group = new ButtonGroup();

        JButton voteButton = null;

        final String defaultMessageCommand = "default";
        final String yesNoCommand = "yesno";
        final String yeahNahCommand = "yeahnah";

        radioButtons[0] = new JRadioButton(
                "Bert from Seasame Street");
        radioButtons[0].setActionCommand(defaultMessageCommand);

        radioButtons[1] = new JRadioButton(
                "Gandhi from Clone High");
        radioButtons[1].setActionCommand(yesNoCommand);

        radioButtons[2] = new JRadioButton(
                "SKIP");
        radioButtons[2].setActionCommand(yeahNahCommand);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        // Select the first button by default.
        radioButtons[0].setSelected(true);

        voteButton = new JButton("Vote");

        voteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                // ok dialog
                if (command == defaultMessageCommand) {
                    JOptionPane.showMessageDialog(frame,
                            "Thank you for choosing Bert!");
                    String value = JOptionPane.showInputDialog("Would you LIke to Wager?","0");
                    int totel = Integer.parseInt(value);
                    JOptionPane.showMessageDialog(null,"Totel Wager on Bert is:  " + totel);

                    // yes/no dialog
                } else if (command == yesNoCommand) {
                    JOptionPane.showMessageDialog(frame,
                            "Thank you for choosing Gandhi!");
                    String value = JOptionPane.showInputDialog("How Many Coins Would You Like the Wager","0");
                    int totel = Integer.parseInt(value);
                    JOptionPane.showMessageDialog(null,"Total Wager on Gandhi is:  " + totel + " coins");
                }
                else{
                    setVisible(false);
                    System.exit(1);
                }
                return;
            }
        });
        System.out.println("calling createPane");
        return createPane(simpleDialogDesc, radioButtons, voteButton);
    }

        private JPanel createPane(String description, JRadioButton[] radioButtons,
                                  JButton showButton) {
            int numChoices = radioButtons.length;
            JPanel box = new JPanel();
            JLabel label = new JLabel(description);

            box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
            box.add(label);

            for (int i = 0; i < numChoices; i++) {
                box.add(radioButtons[i]);
            }

            JPanel pane = new JPanel();
            pane.setLayout(new BorderLayout());
            pane.add(box, BorderLayout.NORTH);
            pane.add(showButton, BorderLayout.SOUTH);
            pane.add(new DrawMyImgs());
            System.out.println("returning pane");
            return pane;
        }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VoteDialog");


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));
        contentPane.add(new VotingBooth(frame));


        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
}

