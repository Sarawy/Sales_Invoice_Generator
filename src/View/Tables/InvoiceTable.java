package View.Tables;

import Controller.MousaHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InvoiceTable extends  JTable{
    public JTable invoicesTable;
    private Object [] cols= {"No.","Date","Customer", "Total"};
    public   static MousaHandler testttt= new MousaHandler();
    private  static DefaultTableModel model = new DefaultTableModel();

    public InvoiceTable(){
        invoicesTable = new JTable();
        invoicesTable.addMouseListener(testttt);
        this.setRowSelectionAllowed(true);


    }
    public InvoiceTable(String data [] []){

        invoicesTable = new JTable(data,cols);
        invoicesTable.addMouseListener(testttt);
        this.setRowSelectionAllowed(true);
        invoicesTable.setShowGrid(true);
        invoicesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        System.out.println(invoicesTable.getRowCount());
        System.out.println(getCountt());


    }



    public JTable getInvoicesTable() {
        return invoicesTable;
    }
    public int getCountt(){
        return invoicesTable.getRowCount();
    }


}
