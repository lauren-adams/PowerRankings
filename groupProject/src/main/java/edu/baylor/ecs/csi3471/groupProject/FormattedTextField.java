package edu.baylor.ecs.csi3471.groupProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FormattedTextField extends JPanel implements PropertyChangeListener {
    String animal;
    String id;
    String name;
    String age;
    String info;


    private JLabel animalLabel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel infoLabel;

    private static String animalString = "Animal: ";
    private static String idString = "ID: ";
    private static String nameString = "Name: ";
    private static String ageString = "Age: ";
    private static String infoString = "Info: ";

    private JFormattedTextField animalField;
    private JFormattedTextField idField;
    private JFormattedTextField nameField;
    private JFormattedTextField ageField;
    private JFormattedTextField infoField;

    public FormattedTextField(DefaultTableModel model, int modelRow){
        super(new BorderLayout());
        animal = (String) model.getValueAt(modelRow, 0);
        id = (String) model.getValueAt(modelRow, 1);
        name = (String) model.getValueAt(modelRow, 2);
        age = (String) model.getValueAt(modelRow, 3);
        info = (String) model.getValueAt(modelRow, 4);

        animalLabel = new JLabel(animalString);
        idLabel = new JLabel(idString);
        nameLabel = new JLabel(nameString);
        ageLabel = new JLabel(ageString);
        infoLabel = new JLabel(infoString);

        animalField = new JFormattedTextField();
        animalField.setValue(new String(animal));
        animalField.setColumns(10);
        animalField.addPropertyChangeListener("value", this);

        idField = new JFormattedTextField();
        idField.setValue(new String(id));
        idField.setColumns(10);
        idField.addPropertyChangeListener("value", this);

        nameField = new JFormattedTextField();
        nameField.setValue(new String(name));
        nameField.setColumns(10);
        nameField.addPropertyChangeListener("value", this);

        ageField = new JFormattedTextField();
        ageField.setValue(new String(age));
        ageField.setColumns(10);
        ageField.addPropertyChangeListener("value", this);

        infoField = new JFormattedTextField();
        infoField.setValue(new String(info));
        infoField.setColumns(10);
        infoField.addPropertyChangeListener("value", this);

        animalLabel.setLabelFor(animalField);
        idLabel.setLabelFor(idField);
        nameLabel.setLabelFor(nameField);
        ageLabel.setLabelFor(ageField);
        infoLabel.setLabelFor(infoField);

        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(animalLabel);
        labelPane.add(idLabel);
        labelPane.add(nameLabel);
        labelPane.add(ageLabel);
        labelPane.add(infoLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(animalField);
        fieldPane.add(idField);
        fieldPane.add(nameField);
        fieldPane.add(ageField);
        fieldPane.add(infoField);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
/*
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animal = String.valueOf(animalField);
                id = String.valueOf(idField);
                name = String.valueOf(nameField);
                age = String.valueOf(ageField);
                info = String.valueOf(infoField);
                //setVisible(false);
                final Window parentWindow = SwingUtilities.getWindowAncestor(FormattedTextField.this);
                parentWindow.dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                final Window parentWindow = SwingUtilities.getWindowAncestor(FormattedTextField.this);
                parentWindow.dispose();
            }
        });
        Box b = Box.createHorizontalBox();
        b.add(submit);
        b.add(cancel);
        b.add(Box.createHorizontalGlue());
        add(b);*/
        //add(submit);
        //add(cancel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == animalField) {
            animal = String.valueOf(animalField);
        }
        if (source == idField) {
            id = String.valueOf(idField);
        }
        if (source == nameField) {
            name = String.valueOf(nameField);
        }
        if (source == ageField) {
            age = String.valueOf(ageField);
        }
        if (source == infoField) {
            info = String.valueOf(infoField);
        }



    }

//    private static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("FormattedTextFieldDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Add contents to the window.
//        frame.add(new FormattedTextField());
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }
}
