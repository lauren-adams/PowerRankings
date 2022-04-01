package edu.baylor.ecs.csi3471.groupProject;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Persistence.CharacterDAO;
import edu.baylor.ecs.csi3471.groupProject.UI.ButtonColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

public class Table extends JPanel {
    private JTable table;
    //dont think i need text field
    //dont think i need status text
    private TableRowSorter<DefaultTableModel> sorter;

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }


    //Creating the frame of the table, have to actually build table using something else
    protected static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableFilterDemo");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Create and set up the content pane.
        Table newContentPane = new Table();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public Table() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] columnNames = {"Name", "World", "Record", "Added By", "View", "Edit"};
        String[][] data = {{"yes", "no", "idk", "maybe"}};
        int rowNumber = 0;
        final DefaultTableModel model = new DefaultTableModel(null, columnNames);
        //File selectedFile = openCSV();
        CharacterDAO cdao = new CharacterDAO();
        ArrayList<Character> cList = cdao.makeList();
        for(int i = 0; i < cList.size(); i++){
            Object[] row = new Object[4];
            model.addRow(row);
            model.setValueAt(cList.get(i).getName(), i, 0);
            model.setValueAt(cList.get(i).getWorld(), i, 1);
            Double ii = cList.get(i).getWin() * 1.0;
            Double iii = cList.get(i).getLoss() * 1.0;
            Double r = 0.0;
            if (iii != 0) {
                r = Double.valueOf(ii / (ii + iii));
            } else {
                if(ii == 0){
                    r = 0.0;
                } else {
                    r = 1.0;
                }
            }
            r = Math.round(r * 100.0) / 100.0;
            String s = String.valueOf(r);
            model.setValueAt(s, i, 2);
            model.setValueAt(cList.get(i).getOwner(), i, 3);
            model.setValueAt("View", i, 4);
            model.setValueAt("Edit", i, 5);

            //rowNumber++;
        }
        /*
        try (BufferedReader br = new BufferedReader(
                new FileReader("CharacterFile.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                //model.addRow(line.split(","));
                Object[] row = line.split("\t");

                model.addRow(row);
                model.setValueAt(row[0], rowNumber, 0);
                model.setValueAt(row[1], rowNumber, 1);
                System.out.println(row[3]);
                System.out.println(row[4]);
                Double i = Integer.valueOf((String) row[3]) * 1.0;
                Double ii = Integer.valueOf((String) row[4]) * 1.0;
                Double r = 0.0;
                if (ii != 0) {
                    r = Double.valueOf(i / (i + ii));
                } else {
                    if(i == 0){
                        r = 0.0;
                    } else {
                        r = 1.0;
                    }
                }
                r = Math.round(r * 100.0) / 100.0;
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
                }*//*
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

*/
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
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                /***FIX MEEE -> get character and call display function***/
                //((DefaultTableModel)table.getModel()).removeRow(modelRow);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String n = (String) model.getValueAt(modelRow, 0);
                String w = (String) model.getValueAt(modelRow, 1);
                CharacterDAO c = new CharacterDAO();
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
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                JTextField animalField = new JTextField(10);
                JTextField idField = new JTextField(10);
                animalField.setText((String) model.getValueAt(modelRow, 0));
                idField.setText((String) model.getValueAt(modelRow, 1));
                CharacterDAO c = new CharacterDAO();
                Character cc = c.findChar(animalField.getText(), idField.getText());
                JTextField nameField = new JTextField(10);
                JTextField ageField = new JTextField(10);
                JTextField infoField = new JTextField(10);
                nameField.setText(cc.getDesc());
                ageField.setText(cc.getPicture());

                JPanel myPanel = new JPanel();
                myPanel.setBackground(Color.PINK);
                myPanel.add(new JLabel("Name: "));
                myPanel.add(animalField);
                myPanel.add(new JLabel("World: "));
                myPanel.add(idField);
                myPanel.add(new JLabel("Decription: "));
                myPanel.add(nameField);
                myPanel.add(new JLabel("Picture url: "));
                myPanel.add(ageField);
                /*myPanel.add(new JLabel("Info (Optional): "));
                myPanel.add(infoField);*/

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Enter values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setValueAt(animalField.getText(), modelRow, 0);
                    model.setValueAt(idField.getText(), modelRow, 1);
                    //model.setValueAt(nameField.getText(), modelRow, 2);
                    //model.setValueAt(ageField.getText(), modelRow, 3);
                    cc.setName(animalField.getText());
                    cc.setWorld(idField.getText());
                    cc.setDesc(nameField.getText());
                    cc.setPicture(ageField.getText());
                    c.updateCSV(c.getId());
                }

            }
        };

        ButtonColumn buttonColumn2 = new ButtonColumn(table, edit, 5);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);

        JButton submit = new JButton("Save");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(submit);


    }

    private JMenuBar initMenu(DefaultTableModel model) {
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
                DefaultTableModel model = (DefaultTableModel) table.getModel();
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
                for (int i = 0; i < numButtons; i++) {
                    myPanel.add(radioButtons[i]);
                }

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Enter values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String command = group.getSelection().getActionCommand();
                    if (command == "comma") {
                        separator = ",";

                    } else if (command == "semicolon") {
                        separator = ";";
                    } else {
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
                            for (int i = 0; i < rowNum; i++) {
                                writer.write((String) model.getValueAt(i, 0));
                                writer.write(separator);
                                writer.write((String) model.getValueAt(i, 1));
                                writer.write(separator);
                                writer.write((String) model.getValueAt(i, 2));
                                writer.write(separator);
                                writer.write((String) model.getValueAt(i, 3));
                                writer.write(separator);
                                if (((String) model.getValueAt(i, 0)).equalsIgnoreCase("dog")) {
                                    writer.write((String) model.getValueAt(i, 5));
                                    writer.write(separator);
                                } else if (((String) model.getValueAt(i, 0)).equalsIgnoreCase("cat")) {

                                    writer.write((String) model.getValueAt(i, 6));
                                    writer.write(separator);
                                } else if (((String) model.getValueAt(i, 0)).equalsIgnoreCase("pig")) {
                                    writer.write((String) model.getValueAt(i, 4));
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

    public File openCSV() {
        File selectedFile = null;
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fc.showOpenDialog(Table.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();

        } else {
            JOptionPane.showMessageDialog(null, "No file selected");
        }
        return selectedFile;
    }

    public void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);
    }


    private final class AddLineActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /*
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
            myPanel.add(ageField);*/
            //**myPanel.add(new JLabel("Info (Optional): "));
            //myPanel.add(infoField);


            JTextField nameW = new JTextField(20), worldW = new JTextField(10), descW = new JTextField(20);
            JTextField recordW = new JTextField(20);
            JTextField winsW = new JTextField(20);
            JTextField lossesW = new JTextField(20);
            JTextField ownerW = new JTextField(20);
            JTextField picW = new JTextField(20);

            JButton closeButton = new JButton("Add");
            JPanel panel1 = new JPanel();
            panel1.setBackground(Color.PINK);
            panel1.setLayout(new GridBagLayout());
            addItem(panel1, new JLabel("Name:"), 0, 0, 1, 1, GridBagConstraints.EAST);
            addItem(panel1, new JLabel("World:"), 0, 1, 1, 1, GridBagConstraints.EAST);
            addItem(panel1, new JLabel("Description:"), 0, 2, 1, 1, GridBagConstraints.EAST);
            addItem(panel1, new JLabel("Record:"), 0, 3, 1, 1, GridBagConstraints.EAST);
            addItem(panel1, new JLabel("Wins"), 0, 4, 1, 1, GridBagConstraints.EAST);
            addItem(panel1, new JLabel("Losses:"), 0, 5, 1, 1, GridBagConstraints.EAST);
            addItem(panel1, new JLabel("Owner:"), 0, 6, 1, 1, GridBagConstraints.EAST);
            addItem(panel1, new JLabel("Picture:"), 0, 7, 1, 1, GridBagConstraints.EAST);


            addItem(panel1, nameW, 1, 0, 2, 1, GridBagConstraints.WEST);
            //nameW.setText(c.getName());
            //nameW.setEditable(false);
            addItem(panel1, worldW, 1, 1, 1, 1, GridBagConstraints.WEST);
            //worldW.setText(c.getWorld());
            //worldW.setEditable(false);
            addItem(panel1, descW, 1, 2, 2, 1, GridBagConstraints.WEST);
            //descW.setText(c.getDesc());
            //descW.setEditable(false);
            addItem(panel1, recordW, 1, 3, 2, 1, GridBagConstraints.WEST);
            //String rec = "";
            //rec += c.getWin();
            //rec += "/";
            // rec += c.getLoss();
            //recordW.setText(rec);
            //recordW.setEditable(false);
            addItem(panel1, winsW, 1, 4, 2, 1, GridBagConstraints.WEST);
            //winsW.setText(String.valueOf(c.getWin()));
            //winsW.setEditable(false);
            addItem(panel1, lossesW, 1, 5, 2, 1, GridBagConstraints.WEST);
            //lossesW.setText(String.valueOf(c.getLoss()));
            // lossesW.setEditable(false);
            addItem(panel1, ownerW, 1, 6, 2, 1, GridBagConstraints.WEST);
            //ownerW.setText(c.getOwner());
            // ownerW.setEditable(false);
            addItem(panel1, picW, 1, 7, 2, 1, GridBagConstraints.WEST);


            /*** Fix meeee to be able to add characters from csv***/


            int result = JOptionPane.showConfirmDialog(null, panel1,
                    "Enter values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Integer i = Integer.valueOf(winsW.getText());
                Character c = new Character(nameW.getText(), worldW.getText(), descW.getText(), i, Integer.valueOf(lossesW.getText()), picW.getText(), ownerW.getText());
                String res = c.charToCSV();
                try {
                    RandomAccessFile raf = new RandomAccessFile("CharacterFile.csv","rw");
                    raf.seek(raf.length());
                    raf.writeBytes(res);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                model.insertRow(0, new Object[]{c.getName(), c.getWorld(),  String.valueOf(c.getRatio()), c.getOwner(),"View", "Edit"});
            }
            ;
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


