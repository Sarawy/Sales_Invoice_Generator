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

public class testttt extends MouseAdapter {


    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        if (MainFrameController.mainFrame.invoiceTable.getInvoicesTable().getSelectedRow() > -1) {

            int invoiceIndex = MainFrameController.mainFrame.invoiceTable.getInvoicesTable().getSelectedRow();
            int invoiceNumber = Integer.parseInt(MainFrameController.mainFrame.invoiceTable.getInvoicesTable().getValueAt(invoiceIndex,0).toString());

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
            MainFrameController.mainFrame.remove(MainFrameController.mainFrame.itemsTable);
            MainFrameController.mainFrame.remove(MainFrameController.mainFrame.itemsTableScroll);
            MainFrameController.mainFrame.itemsTable = new ItemsTable(data);
            MainFrameController.mainFrame.itemsTableScroll = new JScrollPane(MainFrameController.mainFrame.itemsTable.getInvoiceItemsTable());
            MainFrameController.mainFrame.itemsTableScroll.setBounds(600, 283, 500, 402);
            MainFrameController.mainFrame.add(MainFrameController.mainFrame.itemsTableScroll);

            MainFrameController.mainFrame.customerNameTextField.setText(MainFrame.fileOperations.invoiceHeaderArrayList.get(invoiceIndex).getCustomerName());
            MainFrameController.mainFrame.invoiceDateTextField.setText(MainFrame.fileOperations.invoiceHeaderArrayList.get(invoiceIndex).getInvoiceDate());
            MainFrameController.mainFrame.invoiceNumberLabel.setText(Integer.toString(invoiceNumber));
//            MainFrameController.mainFrame.invoicesTotalLabel.setText();
            MainFrameController.mainFrame.invoicesTotalLabel.setText(String.valueOf( MainFrameController.mainFrame.invoiceTable.getInvoicesTable().getValueAt(invoiceIndex,3)));
//            MainFrameController.mainFrame.l
        }
    }
}
