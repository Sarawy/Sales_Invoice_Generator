package Controller;

import Model.FileOperations;
import Model.invoiceHeader;
import Model.invoiceLine;
import View.MainFrame;
import View.Tables.ItemsTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MousaHandler extends MouseAdapter {


    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        MainFrame.selectedRow=MainController.mainFrame.itemsTable.getInvoiceItemsTable().getSelectedRow();
        if (MainController.mainFrame.invoiceTable.getInvoicesTable().getSelectedRow() > -1) {

            int invoiceIndex = MainController.mainFrame.invoiceTable.getInvoicesTable().getSelectedRow();
            int invoiceNumber = Integer.parseInt(MainController.mainFrame.invoiceTable.getInvoicesTable().getValueAt(invoiceIndex,0).toString());

            String[][] data = new String[MainFrame.fileOperations.invoiceHeaderArrayList.get(invoiceIndex).getInvoiceItems().size()][5];
            ArrayList<invoiceLine> invoiceItems = new ArrayList<>();
            for (invoiceHeader h:
                    FileOperations.invoiceHeaderArrayList) {
                if(h.getInvoiceNumber()==invoiceNumber) invoiceItems=h.getInvoiceItems();

            }

            int i = 0;
            for (invoiceLine x :
                    invoiceItems) {
                data[i][0] = Integer.toString(i + 1);
                data[i][1] = x.getItemName();
                data[i][2] = Double.toString(x.getItemPrice());
                data[i][3] = Integer.toString(x.getItemCount());
                data[i][4] = Double.toString(x.getItemCount() * x.getItemPrice());
                i++;
            }
            MainController.mainFrame.remove(MainController.mainFrame.itemsTable);
            MainController.mainFrame.remove(MainController.mainFrame.itemsTableScroll);
            MainController.mainFrame.itemsTable = new ItemsTable(data);
            MainController.mainFrame.itemsTableScroll = new JScrollPane(MainController.mainFrame.itemsTable.getInvoiceItemsTable());
            MainController.mainFrame.itemsTableScroll.setBounds(600, 283, 500, 402);
            MainController.mainFrame.add(MainController.mainFrame.itemsTableScroll);

            MainController.mainFrame.customerNameTextField.setText(MainFrame.fileOperations.invoiceHeaderArrayList.get(invoiceIndex).getCustomerName());
            MainController.mainFrame.invoiceDateTextField.setText(MainFrame.fileOperations.invoiceHeaderArrayList.get(invoiceIndex).getInvoiceDate());
            MainController.mainFrame.invoiceNumberLabel.setText(Integer.toString(invoiceNumber));
            MainController.mainFrame.invoicesTotalLabel.setText(String.valueOf( MainController.mainFrame.invoiceTable.getInvoicesTable().getValueAt(invoiceIndex,3)));
        }

    }
}
