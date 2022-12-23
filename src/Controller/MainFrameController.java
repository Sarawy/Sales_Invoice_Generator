package Controller;

import Model.invoiceHeader;
import View.MainFrame;
import View.Tables.InvoiceTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrameController extends MouseAdapter implements ActionListener {
public  static  MainFrame mainFrame = new MainFrame();
    public MainFrameController( ) {
    mainFrame.setVisible(true);
        MainFrameController.mainFrame.invoiceTable.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println("ssss");
    }

    @Override
    public void actionPerformed (ActionEvent e){
    System.out.println("ANy");
        switch (e.getActionCommand()) {
            case "Save Invoice":
                loadFile();

                break;
            case "Delete Invoice":
                System.out.println("ssssss");
                ///
                break;
            case "New Invoice":
                //
                JButton s = new JButton("sssssssss");
                s.setBounds(1200, 500, 200, 200);
                mainFrame.add(s);
                mainFrame.validate();
                break;
            case "Cancel":
                //
                System.out.println("Sssssssssssssssssssss");
                break;

        }
    }

    private String[][] getInvoiceData (ArrayList < invoiceHeader > headerArrayList) {
        String data[][] = new String[headerArrayList.size()][4];
        for (int i = 0; i < headerArrayList.size(); i++) {
            data[i][0] = Integer.toString(headerArrayList.get(i).getInvoiceNumber());
            data[i][1] = headerArrayList.get(i).getInvoiceDate();
            data[i][2] = headerArrayList.get(i).getCustomerName();
            double total = 0;
//            for(int j =0;j<headerArrayList.get(i).getInvoiceItems().size();j++){
//                total+=headerArrayList.get(i).getInvoiceItems().get(i).getItemPrice()*headerArrayList.get(i).getInvoiceItems().get(i).getItemCount();
//            }
            data[i][3] = Double.toString(total);
        }
        return data;
    }
    private void loadFile () {
        try {
            ArrayList<invoiceHeader> readFile = mainFrame.fileOperations.readFile("InvoiceHeader.csv", "InvoiceLine.csv");
            String data[][] = getInvoiceData(readFile);
            System.out.println(MainFrameController.mainFrame.invoiceTable.getRowCount());
            mainFrame. remove(mainFrame.invoiceTable);
            mainFrame.remove(mainFrame.invoiceTableScroll);
            mainFrame.invoiceTable = new InvoiceTable(data);
            mainFrame.invoiceTableScroll = new JScrollPane(mainFrame.invoiceTable.getInvoicesTable());
            mainFrame.invoiceTableScroll.setBounds(10, 35, 500, 650);
            mainFrame.add(mainFrame.invoiceTableScroll);


            //setVisible(false);

//            revalidate();
//            repaint();
//            setVisible(true);


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public  static  void main (String [] args) {
        new MainFrame().setVisible(true);    }

}
