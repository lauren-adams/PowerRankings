package edu.baylor.ecs.csi3471.groupProject;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Table extends JPanel{
    private JTable table;
    //dont think i need text field
    //dont think i need status text
    private TableRowSorter<DefaultTableModel> sorter;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }


    //Creating the frame of the table, have to actually build table using something else
    protected static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableFilterDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //Create and set up the content pane.
        Table newContentPane = new Table();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public Table(){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] columnNames = {"Name", "World", "Record", "Added By", "View", "Edit"};
        String[][] data = {{"yes", "no", "idk", "maybe"}};
        int rowNumber = 0;
        final DefaultTableModel model = new DefaultTableModel(null, columnNames);
        //File selectedFile = openCSV();

        try (BufferedReader br = new BufferedReader(
                new FileReader("CharacterFile.csv"))) {
            String line =  br.readLine();
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                //model.addRow(line.split(","));
                Object[] row = line.split("\t");

                model.addRow(row);
                model.setValueAt(row[0], rowNumber, 0);
                model.setValueAt(row[1], rowNumber, 1);
                System.out.println(row[3]);
                System.out.println(row[4]);
                Integer i = Integer.valueOf((String) row[3]);
                Integer ii = Integer.valueOf((String) row[4]);
                if (ii == 0){
                    ii = 1;
                }
                Double r = Double.valueOf(i /ii);
                String s = row[3] + "/" + row[4];
                s = String.valueOf(r);
                model.setValueAt(s, rowNumber, 2);
                model.setValueAt(row[7], rowNumber, 3);
                /*
                if(row[0].equals("Dog")){
                    model.setValueAt("", rowNumber, 4);
                    model.setValueAt(row[4], rowNumber, 5);
                }
                else if(row[0].equals("Cat")){
                    model.setValueAt("", rowNumber, 4);
                    model.setValueAt(row[4], rowNumber, 6);
                }*/
                model.setValueAt("View", rowNumber, 4);
                model.setValueAt("Edit", rowNumber, 5);

                rowNumber++;
            }
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(null, "Issue with loading file: " + exception.getMessage());
            exception.printStackTrace();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, "Issue with loading file: " + exception.getMessage());
            exception.printStackTrace();
        }




        sorter = new TableRowSorter<DefaultTableModel>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(800, 70));
        table.setFillsViewportHeight(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Box b = Box.createHorizontalBox();
        b.add(initMenu(model));
        b.add(Box.createHorizontalGlue());
        add(b);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);


        Action delete = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                /***FIX MEEE -> get character and call display function***/
                //((DefaultTableModel)table.getModel()).removeRow(modelRow);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String n = (String) model.getValueAt(modelRow, 0);
                String w = (String) model.getValueAt(modelRow, 1);
                Character c = new Character();
                Character ret = c.findChar(n, w);
                ret.displayChar();

/*
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to remove " + model.getValueAt(modelRow, 0) + " " + model.getValueAt(modelRow, 1) + "?", "Warning", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    model.removeRow(modelRow);
                }*/
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(table, delete, 4);
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        Action edit = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                JTextField animalField = new JTextField(10);
                JTextField idField = new JTextField(10);
                JTextField nameField = new JTextField(10);
                JTextField ageField = new JTextField(10);
                JTextField infoField = new JTextField(10);
                animalField.setText((String) model.getValueAt(modelRow, 0));
                idField.setText((String) model.getValueAt(modelRow, 1));
                nameField.setText((String) model.getValueAt(modelRow, 2));
                ageField.setText((String) model.getValueAt(modelRow, 3));
                if(animalField.getText().equalsIgnoreCase("dog")){
                    infoField.setText((String) model.getValueAt(modelRow, 5));
                }
                else if(animalField.getText().equalsIgnoreCase("cat")){
                    infoField.setText((String) model.getValueAt(modelRow, 6));
                }
                else{
                    infoField.setText((String) model.getValueAt(modelRow, 4));
                }
                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("Name: "));
                myPanel.add(animalField);
                myPanel.add(new JLabel("World: "));
                myPanel.add(idField);
                myPanel.add(new JLabel("Record: "));
                myPanel.add(nameField);
                myPanel.add(new JLabel("Added By: "));
                myPanel.add(ageField);
                /*myPanel.add(new JLabel("Info (Optional): "));
                myPanel.add(infoField);*/

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Enter values", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setValueAt(animalField.getText(), modelRow, 0);
                    model.setValueAt(idField.getText(), modelRow, 1);
                    model.setValueAt(nameField.getText(), modelRow, 2);
                    model.setValueAt(ageField.getText(), modelRow, 3);

                }

            }
        };

        ButtonColumn buttonColumn2 = new ButtonColumn(table, edit, 5);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);



    }
    private JMenuBar initMenu(DefaultTableModel model){
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Allows the user to have controls over menu.");
        menuBar.add(menu);

        menuItem = new JMenuItem("Remove", KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Meant to remove item");
        menuItem.addActionListener(new RemoveLineActionListener());
        menu.add(menuItem);

        menuItem = new JMenuItem("Add New Line");
        menuItem.addActionListener(new AddLineActionListener());
        menu.add(menuItem);


        menu.addSeparator();
        /*menuItem = new JMenuItem("Load CSV");
        menu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                model.setRowCount(0); //remove table
                File selectedFile = openCSV();

                try (BufferedReader br = new BufferedReader(
                        new FileReader(selectedFile))) {
                    String line;
                    int rowNumber = 0;
                    while ((line = br.readLine()) != null) {
                        Object[] row = line.split(",");
                        model.addRow(row);
                        if(row[0].equals("Dog")){
                            model.setValueAt("", rowNumber, 4);
                            model.setValueAt(row[4], rowNumber, 5);
                        }
                        else if(row[0].equals("Cat")){
                            model.setValueAt("", rowNumber, 4);
                            model.setValueAt(row[4], rowNumber, 6);
                        }
                        model.setValueAt("Edit", rowNumber, 7);
                        model.setValueAt("Remove", rowNumber, 8);
                        rowNumber++;
                    }
                } catch (FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(null, "Issue with loading file: " + exception.getMessage());
                    exception.printStackTrace();
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, "Issue with loading file: " + exception.getMessage());
                    exception.printStackTrace();
                }
            }


        });
        menu.addSeparator();
        menuItem = new JMenuItem("Import");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                model.setRowCount(0); //remove table


                //Open dialog to select what the separator is gonna be, comma, semicolon, or tab
                String separator = ","; //add the character as specified ny user
                JPanel myPanel = new JPanel();
                final int numButtons = 3;
                JRadioButton[] radioButtons = new JRadioButton[numButtons];
                final ButtonGroup group = new ButtonGroup();

                radioButtons[0] = new JRadioButton("Comma separated ");
                radioButtons[0].setActionCommand("comma");

                radioButtons[1] = new JRadioButton("Semicolon separated ");
                radioButtons[1].setActionCommand("semicolon");

                radioButtons[2] = new JRadioButton("Tab separated "
                        + "(in the programmer's words)");
                radioButtons[2].setActionCommand("tab");

                for (int i = 0; i < numButtons; i++) {
                    group.add(radioButtons[i]);
                }
                radioButtons[0].setSelected(true);
                for(int i = 0; i < numButtons; i++){
                    myPanel.add(radioButtons[i]);
                }

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Enter values", JOptionPane.OK_CANCEL_OPTION);
                String command = group.getSelection().getActionCommand();
                if (command == "comma") {
                    separator = ",";

                }
                else if (command == "semicolon") {
                    separator = ";";
                }
                else{
                    separator = "\t";
                }

                File selectedFile = openCSV();
                try (BufferedReader br = new BufferedReader(
                        new FileReader(selectedFile))) {
                    String line;
                    int rowNumber = 0;
                    while ((line = br.readLine()) != null) {
                        //FIXME SPLIT MIGHT HAVE TO DO TABS AND SEMICOLONS TOO
                        Object[] row = line.split(separator);
                        model.addRow(row);
                        if(row[0].equals("Dog")){
                            model.setValueAt("", rowNumber, 4);
                            model.setValueAt(row[4], rowNumber, 5);
                        }
                        else if(row[0].equals("Cat")){
                            model.setValueAt("", rowNumber, 4);
                            model.setValueAt(row[4], rowNumber, 6);
                        }
                        model.setValueAt("Edit", rowNumber, 7);
                        model.setValueAt("Remove", rowNumber, 8);
                        rowNumber++;
                    }
                } catch (FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(null, "Issue with loading file: " + exception.getMessage());
                    exception.printStackTrace();
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, "Issue with loading file: " + exception.getMessage());
                    exception.printStackTrace();
                }
            }


        });*/
        /***FIX MEEEE MAybe use to send out data when complete***/
        menu.add(menuItem);
        menuItem = new JMenuItem("Export");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                String separator = ","; //add the character as specified ny user
                JPanel myPanel = new JPanel();
                final int numButtons = 3;
                JRadioButton[] radioButtons = new JRadioButton[numButtons];
                final ButtonGroup group = new ButtonGroup();

                radioButtons[0] = new JRadioButton("Comma separated ");
                radioButtons[0].setActionCommand("comma");

                radioButtons[1] = new JRadioButton("Semicolon separated ");
                radioButtons[1].setActionCommand("semicolon");

                radioButtons[2] = new JRadioButton("Tab separated "
                        + "(in the programmer's words)");
                radioButtons[2].setActionCommand("tab");

                for (int i = 0; i < numButtons; i++) {
                    group.add(radioButtons[i]);
                }
                radioButtons[0].setSelected(true);
                for(int i = 0; i < numButtons; i++){
                    myPanel.add(radioButtons[i]);
                }

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Enter values", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION){
                    String command = group.getSelection().getActionCommand();
                    if (command == "comma") {
                        separator = ",";

                    }
                    else if (command == "semicolon") {
                        separator = ";";
                    }
                    else{
                        separator = "\t";
                    }
                    JFileChooser chooser = new JFileChooser();
                    int result2 = chooser.showSaveDialog(null);
                    String text = "testing testing 123" + separator + "more words";
                    if (result2 == JFileChooser.APPROVE_OPTION) {
                        try {
                            File file = chooser.getSelectedFile();
                            FileWriter writer = new FileWriter(file);
                            int rowNum = model.getRowCount();
                            //writer.write(Integer.toString(rowNum));
                            for(int i = 0; i < rowNum; i++){
                                writer.write((String)model.getValueAt(i, 0));
                                writer.write(separator);
                                writer.write((String)model.getValueAt(i, 1));
                                writer.write(separator);
                                writer.write((String)model.getValueAt(i, 2));
                                writer.write(separator);
                                writer.write((String)model.getValueAt(i, 3));
                                writer.write(separator);
                                if(((String)model.getValueAt(i, 0)).equalsIgnoreCase("dog")){
                                    writer.write((String)model.getValueAt(i, 5));
                                    writer.write(separator);
                                }
                                else if(((String)model.getValueAt(i, 0)).equalsIgnoreCase("cat")){

                                    writer.write((String)model.getValueAt(i, 6));
                                    writer.write(separator);
                                }
                                else if(((String)model.getValueAt(i, 0)).equalsIgnoreCase("pig")){
                                    writer.write((String)model.getValueAt(i, 4));
                                    writer.write(separator);
                                }
                                writer.write(System.lineSeparator());
                            }
                            writer.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }


                //String text = "JFileChooser, you're my only friend.";


            }

        });
        //menu.add(menuItem);
        return menuBar;
    }

    public File openCSV(){
        File selectedFile = null;
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fc.showOpenDialog(Table.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();

        }
        else{
            JOptionPane.showMessageDialog(null, "No file selected");
        }
        return selectedFile;
    }


    private final class AddLineActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField animalField = new JTextField(10);
            JTextField idField = new JTextField(10);
            JTextField nameField = new JTextField(10);
            JTextField ageField = new JTextField(10);
            JTextField infoField = new JTextField(10);
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Name: "));
            myPanel.add(animalField);
            myPanel.add(new JLabel("World: "));
            myPanel.add(idField);
            myPanel.add(new JLabel("Wins/Losses: "));
            myPanel.add(nameField);
            myPanel.add(new JLabel("Added By: "));
            myPanel.add(ageField);
            //**myPanel.add(new JLabel("Info (Optional): "));
            //myPanel.add(infoField);
            /*** Fix meeee to be able to add characters from csv***/



            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Enter values", JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION){
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.insertRow(0, new Object[]{ animalField.getText(), idField.getText(), nameField.getText(), ageField.getText(), "View", "Edit"});

            }
        }
    }
    private final class RemoveLineActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int viewRow = table.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(null, "No row selected");

            } else {
                int modelRow = table.convertRowIndexToModel(viewRow);
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                int answer = JOptionPane.showConfirmDialog(null, "Do you want to remove " + model.getValueAt(modelRow, 0) + " " + model.getValueAt(modelRow, 1) + "?", "Warning", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    model.removeRow(modelRow);
                }
            }
        }
    }
}
