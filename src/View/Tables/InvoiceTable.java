package View.Tables;

import javax.swing.*;

public class InvoiceTable extends  JTable{
    private JTable invoicesTable;
    private String [] cols= {"No.","Date","Customer", "Total"};
    private  String [] []data ={

    };

    public InvoiceTable(){
        invoicesTable = new JTable(data, cols);

    }

    public JTable getInvoicesTable() {
        return invoicesTable;
    }
}
