package View.Tables;

import javax.swing.*;

public class ItemsTable extends JTable {
    private JTable invoiceItemsTable;
    private String [] cols= {"No.","Item Name","Item Price", "Count", "Item Total"};
    private  String [] []data ={

    };
    public ItemsTable(){
        invoiceItemsTable = new JTable(data,cols);

    }
    public JTable getInvoiceItemsTable() {
        return invoiceItemsTable;
    }
}
