package Model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {
    private static ArrayList<invoiceHeader> invoiceHeaderArrayList = new ArrayList<invoiceHeader>();
    private static ArrayList <invoiceLine> invoiceLineArrayList;


    public ArrayList<invoiceHeader> readFile(String invoiceHeaderPath,String invoiceLinePath) throws IOException {
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

    public static void main (String [] args) throws IOException {
        FileOperations   data = new FileOperations();
        String path = "InvoiceHeader.csv";
        data.testRead("InvoiceHeader.csv","InvoiceLine.csv");
    }
}
