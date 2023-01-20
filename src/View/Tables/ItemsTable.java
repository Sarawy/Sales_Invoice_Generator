package View.Tables;

import javax.swing.*;

public class ItemsTable extends JTable {
    private JTable invoiceItemsTable;
    private String [] cols= {"No.","Item Name","Item Price", "Count", "Item Total"};

    public ItemsTable(){
        invoiceItemsTable = new JTable();
        invoiceItemsTable.addMouseListener(InvoiceTable.testttt);

    }
    public ItemsTable(String [] [] data){
        invoiceItemsTable = new JTable(data,cols);
        invoiceItemsTable.addMouseListener(InvoiceTable.testttt);


    }
    public JTable getInvoiceItemsTable() {
        return invoiceItemsTable;
    }
}
