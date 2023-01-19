package View;

import Controller.MainController;
import Model.FileOperations;
import Model.invoiceHeader;
import Model.invoiceLine;
import View.Menubar.MenuBar;
import View.Tables.InvoiceTable;
import View.Tables.ItemsTable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
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
    private JDialog newItemDialog;
    public static FileOperations fileOperations = new FileOperations();
    public  static  int selectedRow=-1;
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

        createItemBtn = new JButton("Create Item");
        createItemBtn.setBounds(700, 725, 120, 20);
        add(createItemBtn);

        deleteItemBtn = new JButton("Delete Item");
        deleteItemBtn.setBounds(880, 725, 120, 20);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createItemBtn.setActionCommand("Create Item");
        deleteInvoiceBtn.setActionCommand("Delete Invoice");
        newInvoiceBtn.setActionCommand("New Invoice");
        deleteItemBtn.setActionCommand("Delete Item");
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
        initialize_Item_Dialog();
        loadFile();

    }
    JTextField  item_Name_TF_Dialog;
    JTextField  item_price_TF_Dialog ;
    JTextField  item_count_Tf_Dialog;
    private void initialize_Item_Dialog () {
        newItemDialog = new JDialog();
        JLabel itemNameLabelDialog = new JLabel("Item Name : ");
        JLabel itemPriceLabelDialog = new JLabel("Item Price : ");
        JLabel itemCountLabelDialog = new JLabel("Item Count : ");
          item_Name_TF_Dialog = new JTextField(30);
          item_price_TF_Dialog = new JTextField(30);
          item_count_Tf_Dialog = new JTextField(30);
        JButton   saveItemBtn = new JButton("Ok");
        JButton  cancelItemBTN = new JButton("Cancel");
        saveItemBtn.setActionCommand("Item Add");
        cancelItemBTN.setActionCommand("Item Cancel");
        saveItemBtn.addActionListener(this);
        cancelItemBTN.addActionListener(this);

        newItemDialog.setLayout(new GridLayout(4, 2));
        newItemDialog.add(itemNameLabelDialog);
        newItemDialog.add(item_Name_TF_Dialog);
        newItemDialog.add(itemCountLabelDialog);
        newItemDialog.add(item_count_Tf_Dialog);
        newItemDialog.add(itemPriceLabelDialog);
        newItemDialog.add(item_price_TF_Dialog);
        newItemDialog.add(saveItemBtn);
        newItemDialog.add(cancelItemBTN);
        newItemDialog.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Create Item":
                if(invoiceNumberLabel.getText()!="") {
                    addItem();
                    newItemDialog.setVisible(true);
                }
                else             JOptionPane.showMessageDialog(MainController.mainFrame,"Please Create a new Invoice first","Error",JOptionPane.ERROR_MESSAGE);
// saveInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();

                break;
            case "New Invoice":

                createInvoice();
                break;
            case "Delete Item":
                if(invoiceNumberLabel.getText()!="") {
                    if(selectedRow == -1) JOptionPane.showMessageDialog(MainController.mainFrame,"Please Choose an Item First","Error",JOptionPane.ERROR_MESSAGE);
else  deleteItem();
                } else   JOptionPane.showMessageDialog(MainController.mainFrame,"Please Choose an  Invoice first","Error",JOptionPane.ERROR_MESSAGE);

                break;
            case "Load":

                loadFile();
                break;
            case "Save File":
                fileOperations.writeFile(FileOperations.invoiceHeaderArrayList);

                break;
            case "Item Add":
                addItemTF_Dialog();
                break;
            case "Item Cancel":
                item_count_Tf_Dialog.setText("");
                item_price_TF_Dialog.setText("");
                item_Name_TF_Dialog.setText("");
                newItemDialog.dispose();
                break;

        }
    }
    ArrayList<invoiceLine> lineList = new ArrayList<invoiceLine>();

    private  void addItem(){
        lineList.clear();


       int rowsCount= itemsTable.getInvoiceItemsTable().getRowCount();
       System.out.println(rowsCount);

       for (int i=0;i<rowsCount;i++){
           invoiceLine line = new invoiceLine(Integer.parseInt(invoiceNumberLabel.getText()),itemsTable.getInvoiceItemsTable().getValueAt(i,1).toString(),Double.parseDouble(itemsTable.getInvoiceItemsTable().getValueAt(i,2).toString()),Integer.parseInt(itemsTable.getInvoiceItemsTable().getValueAt(i,3).toString()));
            lineList.add(line);
       }


    }
    private  void addItemTF_Dialog(){
        double total=0;
        int rowsCount= itemsTable.getInvoiceItemsTable().getRowCount();

        String[][] data = new String[rowsCount+1][5];

        invoiceLine invoiceLine = new invoiceLine(Integer.parseInt( invoiceNumberLabel.getText()),item_Name_TF_Dialog.getText(),Double.parseDouble(item_price_TF_Dialog.getText()),Integer.parseInt(item_count_Tf_Dialog.getText()));
        lineList.add(invoiceLine);
        int i =0;
        for (invoiceLine x :
                lineList) {
            data[i][0] = Integer.toString(i + 1);
            data[i][1] = x.getItemName();
            data[i][2] = Double.toString(x.getItemPrice());
            data[i][3] = Integer.toString(x.getItemCount());
            data[i][4] = Double.toString(x.getItemCount() * x.getItemPrice());
            total += (x.getItemCount() * x.getItemPrice());
            i++;
        }
        remove(itemsTable);
        remove(itemsTableScroll);

        itemsTable = new ItemsTable(data);
        itemsTableScroll = new JScrollPane(itemsTable.getInvoiceItemsTable());
        itemsTableScroll.setBounds(600, 283, 500, 402);
        add(itemsTableScroll);
        item_Name_TF_Dialog.setText("");
        item_price_TF_Dialog.setText("");
        item_count_Tf_Dialog.setText("");
        newItemDialog.dispose();
        invoicesTotalLabel.setText(Double.toString(total));


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
        String headerPath,linePath;
        JFileChooser fcInvoice = new JFileChooser();
        FileNameExtensionFilter xx = new FileNameExtensionFilter("Csv Files","csv");
        fcInvoice.setFileFilter(xx);
        JOptionPane.showMessageDialog(this,"Please Choose Invoice Header Csv Files","Invoice Header",JOptionPane.INFORMATION_MESSAGE);

        if(fcInvoice.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
            headerPath = fcInvoice.getSelectedFile().getPath();
            if (headerPath.endsWith(".csv")) {
                JOptionPane.showMessageDialog(this,"Please Choose Invoice Line Csv Files","Invoice Line",JOptionPane.INFORMATION_MESSAGE);

                if(fcInvoice.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
                    linePath=fcInvoice.getSelectedFile().getPath();
                    if (linePath.endsWith(".csv")) {
                        try {
                            ArrayList<invoiceHeader> readFile = fileOperations.readFile(headerPath, linePath);
                            String data[][] = getInvoiceData(readFile);
                            System.out.println(invoiceTable.getRowCount());
                            remove(invoiceTable);
                            remove(invoiceTableScroll);
                            invoiceTable = new InvoiceTable(data);
                            invoiceTableScroll = new JScrollPane(invoiceTable.getInvoicesTable());
                            invoiceTableScroll.setBounds(10, 35, 500, 650);
                            add(invoiceTableScroll);
                          //  JOptionPane.showMessageDialog(this,"Data Loaded Successfulyy form CSV files","Load Data",JOptionPane.INFORMATION_MESSAGE);



                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
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
        invoicesTotalLabel.setText("0");
        remove(itemsTable);
        remove(itemsTableScroll);


//        itemsTable = new ItemsTable(new String[][]{
//                {"", "", "", "", ""},
//                {"", "", "", "", ""},
//                {"", "", "", "", ""},
//                {"", "", "", "", ""},
//                {"", "", "", "", ""},
//
//        });
//        itemsTableScroll = new JScrollPane(itemsTable.getInvoiceItemsTable());
//        itemsTableScroll.setBounds(600, 283, 500, 402);
//        add(itemsTableScroll);
    }

    private void deleteItem() {
      //  System.out.println(selectedRow+"ssssssssssssssss");
        addItem();

        double total=0;
        lineList.remove(selectedRow);
        int rowsCount= lineList.size();

        String[][] data = new String[rowsCount][5];


        int i =0;
        for (invoiceLine x :
                lineList) {
            data[i][0] = Integer.toString(i + 1);
            data[i][1] = x.getItemName();
            data[i][2] = Double.toString(x.getItemPrice());
            data[i][3] = Integer.toString(x.getItemCount());
            data[i][4] = Double.toString(x.getItemCount() * x.getItemPrice());
            total += (x.getItemCount() * x.getItemPrice());
            i++;
        }

remove(itemsTableScroll);
        remove(itemsTable);

        itemsTable = new ItemsTable(data);
        itemsTableScroll = new JScrollPane(itemsTable.getInvoiceItemsTable());
        itemsTableScroll.setBounds(600, 283, 500, 402);
        add(itemsTableScroll);
        item_Name_TF_Dialog.setText("");
        item_price_TF_Dialog.setText("");
        item_count_Tf_Dialog.setText("");
        newItemDialog.dispose();
        invoicesTotalLabel.setText(Double.toString(total));

//        invoiceNumberLabel.setText("");
//        invoiceDateTextField.setText("");
//        customerNameTextField.setText("");
//        invoicesTotalLabel.setText("");
//        remove(itemsTable);
//        remove(itemsTableScroll);
//
//
//        itemsTable = new ItemsTable();
//        itemsTableScroll = new JScrollPane(itemsTable.getInvoiceItemsTable());
//        itemsTableScroll.setBounds(600, 283, 500, 402);
//        add(itemsTableScroll);
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
            if (itemsTable.getInvoiceItemsTable().getValueAt(z, 1).toString() != "" && itemsTable.getInvoiceItemsTable().getValueAt(z, 2).toString()!= "" && itemsTable.getInvoiceItemsTable().getValueAt(z, 3).toString() != "" ) {
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

