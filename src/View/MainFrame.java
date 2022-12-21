package View;

import javax.swing.*;
import java.awt.*;

import View.Tables.InvoiceTable;
import View.Tables.ItemsTable;
import View.Menubar.MenuBar;

public class MainFrame extends JFrame {
    private JLabel invoicesNumberLabel;
    private JLabel invoicesNumberLabelSt;
    private JLabel invoicesDateLabelSt;
    private JLabel customerNameLabelSt;
    private JLabel invoiceTotalLabelSt;
    private JLabel invoiceItemsTableLabel;
    private JTextField invoiceDateTextField;
    private JTextField customerNameTextField;
    private JLabel invoiceTotalLabel;
    private JButton newInvoiceBtn;
    private JButton deleteInvoiceBtn;
    private JButton saveInvoiceBtn;
    private JButton cancelInvoiceBtn;
    InvoiceTable invoiceTable;
    JScrollPane invoiceTableScroll;
    private JLabel invoicesTableLabel;
    ItemsTable  itemsTable;
    JScrollPane itemsTableScroll;
    MenuBar menuBar;

    public MainFrame(){
        super("Sales Invoice Generator");
        setLayout(null);
        menuBar = new MenuBar();
        setJMenuBar(menuBar.getMenuBar());
        invoiceTable = new InvoiceTable();
        invoiceTableScroll= new JScrollPane(invoiceTable.getInvoicesTable());
        invoiceTableScroll.setBounds(10,35,500,650);
        add(invoiceTableScroll);
        setSize(1500,900);

        invoicesTableLabel = new JLabel("Invoices Table");
        invoicesTableLabel.setBounds(15,15,100,13);
        add(invoicesTableLabel);

        newInvoiceBtn = new JButton("Create New Invoice");
        newInvoiceBtn.setBounds(75,725,150,20);
        add(newInvoiceBtn);

        deleteInvoiceBtn = new JButton("Delete Invoice");
        deleteInvoiceBtn.setBounds(325,725,150,20);
        add(deleteInvoiceBtn);

        invoicesNumberLabelSt = new JLabel("Invoice Number");
        invoicesNumberLabelSt.setBounds(550,15,100,13);
        add(invoicesNumberLabelSt);

        invoicesNumberLabel = new JLabel();
        invoicesNumberLabel.setBounds(700,15,100,13);
        add(invoicesNumberLabel);

        invoicesDateLabelSt = new JLabel("Invoice Date");
        invoicesDateLabelSt.setBounds(550,65,100,13);
        add(invoicesDateLabelSt);

        invoiceDateTextField = new JTextField();
        invoiceDateTextField.setBounds(700,61,400,22);
        add(invoiceDateTextField);


        customerNameLabelSt = new JLabel("Customer Name");
        customerNameLabelSt.setBounds(550,115,100,13);
        add(customerNameLabelSt);


        customerNameTextField = new JTextField();
        customerNameTextField.setBounds(700,106,400,22);
        add(customerNameTextField);

        invoiceTotalLabelSt = new JLabel("Invoice Total");
        invoiceTotalLabelSt.setBounds(550,165,100,13);
        add(invoiceTotalLabelSt);


        invoicesNumberLabel = new JLabel();
        invoicesNumberLabel.setBounds(700,165,100,13);
        add(invoicesNumberLabel);

        invoiceItemsTableLabel = new JLabel("Invoice Items");
        invoiceItemsTableLabel.setBounds(580,250,100,13);
        add(invoiceItemsTableLabel);

        itemsTable = new ItemsTable();
        itemsTableScroll= new JScrollPane(itemsTable.getInvoiceItemsTable());
        itemsTableScroll.setBounds(600,283,500,402);
        add(itemsTableScroll);

        saveInvoiceBtn = new JButton("Save");
        saveInvoiceBtn.setBounds(700,725,120,20);
        add(saveInvoiceBtn);

        deleteInvoiceBtn = new JButton("Cancel");
        deleteInvoiceBtn.setBounds(880,725,120,20);
        add(deleteInvoiceBtn);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public  static  void main (String [] args){
        new MainFrame().setVisible(true);
    }
}
