package View;

import Model.FileOperations;
import Model.invoiceHeader;
import Model.invoiceLine;
import View.Menubar.MenuBar;
import View.Tables.InvoiceTable;
import View.Tables.ItemsTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {
    public JLabel invoicesTotalLabel;
    private JLabel invoicesNumberLabelSt;
    private JLabel invoicesDateLabelSt;
    private JLabel customerNameLabelSt;
    private JLabel invoiceTotalLabelSt;
    private JLabel invoiceItemsTableLabel;
    public JTextField invoiceDateTextField;
    public JTextField customerNameTextField;
    public JLabel invoiceNumberLabel;
    private JButton newInvoiceBtn;
    private JButton deleteInvoiceBtn;
    private JButton createItemBtn;
    private JButton deleteItemBtn;
    public InvoiceTable invoiceTable;
    public JScrollPane invoiceTableScroll;
    private JLabel invoicesTableLabel;
    public ItemsTable itemsTable;
    public JScrollPane itemsTableScroll;
    MenuBar menuBar;
    public static FileOperations fileOperations = new FileOperations();

    public MainFrame() {
        super("Sales Invoice Generator");
        setLayout(null);
        menuBar = new MenuBar();
        setJMenuBar(menuBar.getMenuBar());
        invoiceTable = new InvoiceTable();


        invoiceTableScroll = new JScrollPane(invoiceTable.getInvoicesTable());
        invoiceTableScroll.setBounds(10, 35, 500, 650);
        add(invoiceTableScroll);
        setSize(1500, 900);

        invoicesTableLabel = new JLabel("Invoices Table");
        invoicesTableLabel.setBounds(15, 15, 100, 13);
        add(invoicesTableLabel);

        newInvoiceBtn = new JButton("Create New Invoice");
        newInvoiceBtn.setBounds(75, 725, 150, 20);
        add(newInvoiceBtn);

        deleteInvoiceBtn = new JButton("Delete Invoice");
        deleteInvoiceBtn.setBounds(325, 725, 150, 20);
        add(deleteInvoiceBtn);

        invoicesNumberLabelSt = new JLabel("Invoice Number");
        invoicesNumberLabelSt.setBounds(550, 15, 100, 13);
        add(invoicesNumberLabelSt);

        invoiceNumberLabel = new JLabel();
        invoiceNumberLabel.setBounds(700, 15, 100, 13);
        add(invoiceNumberLabel);


        invoicesDateLabelSt = new JLabel("Invoice Date");
        invoicesDateLabelSt.setBounds(550, 65, 100, 13);
        add(invoicesDateLabelSt);

        invoiceDateTextField = new JTextField();
        invoiceDateTextField.setBounds(700, 61, 400, 22);
        add(invoiceDateTextField);


        customerNameLabelSt = new JLabel("Customer Name");
        customerNameLabelSt.setBounds(550, 115, 100, 13);
        add(customerNameLabelSt);


        customerNameTextField = new JTextField();
        customerNameTextField.setBounds(700, 106, 400, 22);
        add(customerNameTextField);

        invoiceTotalLabelSt = new JLabel("Invoice Total");
        invoiceTotalLabelSt.setBounds(550, 165, 100, 13);
        add(invoiceTotalLabelSt);


        invoicesTotalLabel = new JLabel();
        invoicesTotalLabel.setBounds(700, 165, 100, 13);
        add(invoicesTotalLabel);

        invoiceItemsTableLabel = new JLabel("Invoice Items");
        invoiceItemsTableLabel.setBounds(580, 250, 100, 13);
        add(invoiceItemsTableLabel);

        itemsTable = new ItemsTable();

        itemsTableScroll = new JScrollPane(itemsTable.getInvoiceItemsTable());
        itemsTableScroll.setBounds(600, 283, 500, 402);
        add(itemsTableScroll);

        createItemBtn = new JButton("Save");
        createItemBtn.setBounds(700, 725, 120, 20);
        add(createItemBtn);

        deleteItemBtn = new JButton("Cancel");
        deleteItemBtn.setBounds(880, 725, 120, 20);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createItemBtn.setActionCommand("Save Invoice");
        deleteInvoiceBtn.setActionCommand("Delete Invoice");
        newInvoiceBtn.setActionCommand("New Invoice");
        deleteItemBtn.setActionCommand("Cancel");
        createItemBtn.addActionListener(this::actionPerformed);
        deleteInvoiceBtn.addActionListener(this::actionPerformed);
        newInvoiceBtn.addActionListener(this::actionPerformed);
        deleteItemBtn.addActionListener(this::actionPerformed);
        add(deleteItemBtn);
        menuBar.loadFileMenu.setActionCommand("Load");
        menuBar.loadFileMenu.addActionListener(this::actionPerformed);
//        invoiceTable.addMouseListener(new testttt());
//        addMouseListener(new testttt());
        menuBar.saveFileMenu.setActionCommand("Save File");
        menuBar.saveFileMenu.addActionListener(this::actionPerformed);
    loadFile();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save Invoice":
                saveInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();

                break;
            case "New Invoice":

                createInvoice();
                break;
            case "Cancel":
                cancel();
                break;
            case "Load":
                loadFile();
                JOptionPane.showMessageDialog(this,"Data Loaded Successfulyy form CSV files","Load Data",JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Save File":
                fileOperations.writeFile(FileOperations.invoiceHeaderArrayList);
                JOptionPane.showMessageDialog(this,"Data Saved Successfulyy to CSV files","Save Data",JOptionPane.INFORMATION_MESSAGE);

                break;

        }
    }

    private String[][] getInvoiceData(ArrayList<invoiceHeader> headerArrayList) {
        String data[][] = new String[headerArrayList.size()][4];
        for (int i = 0; i < headerArrayList.size(); i++) {
            data[i][0] = Integer.toString(headerArrayList.get(i).getInvoiceNumber());
            data[i][1] = headerArrayList.get(i).getInvoiceDate();
            data[i][2] = headerArrayList.get(i).getCustomerName();
            double total = 0;
            for (int j = 0; j < headerArrayList.get(i).getInvoiceItems().size(); j++) {
                total += headerArrayList.get(i).getInvoiceItems().get(j).getItemPrice() * headerArrayList.get(i).getInvoiceItems().get(j).getItemCount();
            }
            data[i][3] = Double.toString(total);
        }
        return data;
    }

    private void loadFile() {
        try {
            ArrayList<invoiceHeader> readFile = fileOperations.readFile("InvoiceHeader.csv", "InvoiceLine.csv");
            String data[][] = getInvoiceData(readFile);
            System.out.println(invoiceTable.getRowCount());
            remove(invoiceTable);
            remove(invoiceTableScroll);
            invoiceTable = new InvoiceTable(data);
            invoiceTableScroll = new JScrollPane(invoiceTable.getInvoicesTable());
            invoiceTableScroll.setBounds(10, 35, 500, 650);
            add(invoiceTableScroll);


            //setVisible(false);

//            revalidate();
//            repaint();
//            setVisible(true);


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void deleteInvoice() {
        int index = invoiceTable.getInvoicesTable().getSelectedRow();
        if (index > -1) {
            int invoiceNumber = Integer.parseInt(String.valueOf(invoiceTable.getInvoicesTable().getValueAt(index, 0)));

//            invoiceTable.getInvoicesTable().remove(index);
            FileOperations.invoiceHeaderArrayList.remove(index);
            String data[][] = getInvoiceData(FileOperations.invoiceHeaderArrayList);

            int i = 0;
            for (invoiceLine x :
                    FileOperations.invoiceLineArrayList) {
                if (x.getInvoiceNumber() == invoiceNumber) {
                    FileOperations.invoiceLineArrayList.remove(i);
                }
                i++;
            }
            remove(invoiceTable);
            remove(invoiceTableScroll);

            invoiceTable = new InvoiceTable(data);
            invoiceTableScroll = new JScrollPane(invoiceTable.getInvoicesTable());
            invoiceTableScroll.setBounds(10, 35, 500, 650);
            add(invoiceTableScroll);
            invoiceNumberLabel.setText("");
            invoiceDateTextField.setText("");
            customerNameTextField.setText("");
            invoicesTotalLabel.setText("");
            remove(itemsTable);
            remove(itemsTableScroll);


            itemsTable = new ItemsTable(new String[][]{
                    {"", "", "", "", ""},
                    {"", "", "", "", ""},
                    {"", "", "", "", ""},
                    {"", "", "", "", ""},
                    {"", "", "", "", ""},

            });
            itemsTableScroll = new JScrollPane(itemsTable.getInvoiceItemsTable());
            itemsTableScroll.setBounds(600, 283, 500, 402);
            add(itemsTableScroll);
            JOptionPane.showMessageDialog(this, "Invoice was deleted Succesfully", "Done", JOptionPane.INFORMATION_MESSAGE);

        } else
            JOptionPane.showMessageDialog(this, "Please a choose a valid Invoice!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void createInvoice() {
        int rowCount = invoiceTable.getInvoicesTable().getRowCount();
        int invoiceNum = 0;
        if (rowCount != 0) {
            invoiceNum = Integer.parseInt(String.valueOf(invoiceTable.getInvoicesTable().getValueAt(rowCount - 1, 0))) + 1;

        } else invoiceNum = 1;

        invoiceNumberLabel.setText(Integer.toString(invoiceNum));
        invoiceDateTextField.setText("");
        customerNameTextField.setText("");
        invoicesTotalLabel.setText("");
        remove(itemsTable);
        remove(itemsTableScroll);


        itemsTable = new ItemsTable(new String[][]{
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""},

        });
        itemsTableScroll = new JScrollPane(itemsTable.getInvoiceItemsTable());
        itemsTableScroll.setBounds(600, 283, 500, 402);
        add(itemsTableScroll);
    }

    private void cancel() {


        invoiceNumberLabel.setText("");
        invoiceDateTextField.setText("");
        customerNameTextField.setText("");
        invoicesTotalLabel.setText("");
        remove(itemsTable);
        remove(itemsTableScroll);


        itemsTable = new ItemsTable();
        itemsTableScroll = new JScrollPane(itemsTable.getInvoiceItemsTable());
        itemsTableScroll.setBounds(600, 283, 500, 402);
        add(itemsTableScroll);
    }

    private void saveInvoice() {
        int invoiceNum = Integer.parseInt(invoiceNumberLabel.getText());
        int i = 0;
        for (invoiceHeader h :
                FileOperations.invoiceHeaderArrayList) {
            if (h.getInvoiceNumber() == invoiceNum) {
                FileOperations.invoiceHeaderArrayList.remove(i);
                break;
            }

            i++;
            int j = 0;
            for (invoiceLine l :
                    FileOperations.invoiceLineArrayList) {
                if (invoiceNum == l.getInvoiceNumber()) {
                    FileOperations.invoiceLineArrayList.remove(j);
                }
                j++;
            }

        }
        ArrayList<invoiceLine> lines = new ArrayList<>();
        for (int z = 0; z < itemsTable.getInvoiceItemsTable().getRowCount(); z++) {
            System.out.println("Row " +z);
            if (itemsTable.getInvoiceItemsTable().getValueAt(z, 1).toString() != "" && itemsTable.getInvoiceItemsTable().getValueAt(z, 2).toString()!= "" && itemsTable.getInvoiceItemsTable().getValueAt(z, 3).toString() != "" ) {
                System.out.println("sssssssssssssss");
            System.out.println(itemsTable.getInvoiceItemsTable().getRowCount());
                invoiceLine l = new invoiceLine(invoiceNum, itemsTable.getInvoiceItemsTable().getValueAt(z, 1).toString(), Double.parseDouble(itemsTable.getInvoiceItemsTable().getValueAt(z, 2).toString()),Integer.valueOf ((String) itemsTable.getInvoiceItemsTable().getValueAt(z, 3)));
                FileOperations.invoiceLineArrayList.add(l);
                lines.add(l);
            }
        }
            invoiceHeader header = new invoiceHeader(invoiceNum, invoiceDateTextField.getText(), customerNameTextField.getText(), lines);
            FileOperations.invoiceHeaderArrayList.add(header);
            String dataH[][] = getInvoiceData(FileOperations.invoiceHeaderArrayList);

            remove(invoiceTable);
            remove(invoiceTableScroll);
            invoiceTable = new InvoiceTable(dataH);
            invoiceTableScroll = new JScrollPane(invoiceTable.getInvoicesTable());
            invoiceTableScroll.setBounds(10, 35, 500, 650);
            add(invoiceTableScroll);

            invoiceNumberLabel.setText("");
            invoiceDateTextField.setText("");
            customerNameTextField.setText("");
            invoicesTotalLabel.setText("");
            remove(itemsTable);
            remove(itemsTableScroll);


            itemsTable = new ItemsTable();
            itemsTableScroll = new JScrollPane(itemsTable.getInvoiceItemsTable());
            itemsTableScroll.setBounds(600, 283, 500, 402);
            add(itemsTableScroll);



    }


    }

