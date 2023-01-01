package Model;

import Controller.MainController;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class FileOperations {
    public static ArrayList<invoiceHeader> invoiceHeaderArrayList = new ArrayList<invoiceHeader>();
    public static ArrayList <invoiceLine> invoiceLineArrayList;


    public ArrayList<invoiceHeader> readFile(String invoiceHeaderPath,String invoiceLinePath) throws IOException {
        invoiceHeaderArrayList.clear();
        String line;
    BufferedReader br = new BufferedReader(new FileReader(invoiceHeaderPath));
    while((line= br.readLine())!=null){

        String[] invoiceData = line.split(",");
        invoiceLineArrayList = new ArrayList<invoiceLine>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(invoiceLinePath));
        String lineLine;
        while((lineLine=bufferedReader.readLine())!=null){
            String [] lineData = lineLine.split(",");
            if(invoiceData[0].equals(lineData[0])){

                invoiceLine invoiceLine = new invoiceLine(lineData);
                invoiceLineArrayList.add(invoiceLine);
            }
        }
        invoiceHeaderArrayList.add(new invoiceHeader(invoiceData,invoiceLineArrayList));

    }
    return invoiceHeaderArrayList;
    }

    public void testRead(String invoiceHeaderPath,String invoiceLinePath) throws IOException {

       this.readFile(invoiceHeaderPath,invoiceLinePath);
        for (invoiceHeader data:
             invoiceHeaderArrayList) {
            System.out.println(data.getInvoiceNumber()+"\n{\n"+data.getInvoiceDate()+", "+data.getCustomerName()+"\n");
            for (invoiceLine item :
                    data.getInvoiceItems()) {
                System.out.println(item.getItemName()+", "+item.getItemPrice()+", "+item.getItemCount()+"\n");
            }
            System.out.println("\n}\n\n\n");

        }

    }
    public void writeFile(ArrayList<invoiceHeader> data){
        File headerFile= new File("InvoiceHeader.csv");
        File lineFile = new File("InvoiceLine.csv");
        FileWriter fw1 = null;
        FileWriter fw2= null;
        StringBuilder headerI = new StringBuilder();
        StringBuilder lineI = new StringBuilder();

        try {

            for (invoiceHeader h:
                 data) {
                headerI.append(h.getInvoiceNumber()+",");
                headerI.append(h.getInvoiceDate()+",");
                headerI.append(h.getCustomerName()+",");
                headerI.append("\n");

                for (invoiceLine l:
                     h.getInvoiceItems()) {
                    lineI.append(h.getInvoiceNumber()+",");
                    lineI.append(l.getItemName()+",");
                    lineI.append(l.getItemPrice()+",");
                    lineI.append(l.getItemCount()+",");
                    lineI.append("\n");

                }
            }
            fw1 = new FileWriter(headerFile);
            fw2 = new FileWriter(lineFile);
            fw1.write(headerI.toString());
            fw2.write(lineI.toString());

            JOptionPane.showMessageDialog(MainController.mainFrame,"Data Saved Successfulyy to CSV files","Save Data",JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fw1.close();
                fw2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main (String [] args) throws IOException {
        FileOperations   data = new FileOperations();
        String path = "InvoiceHeader.csv";
        ArrayList<invoiceHeader> readFile= data.readFile("InvoiceHeader.csv","InvoiceLine.csv");
        data.writeFile(readFile);
        data.testRead("InvoiceHeader.csv","InvoiceLine.csv");
    }
}
