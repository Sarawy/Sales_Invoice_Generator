package Model;

import java.util.ArrayList;
import java.util.Date;

public class invoiceHeader {
    private  int invoiceNumber;
    private String invoiceDate;
    private String customerName;
    private ArrayList<invoiceLine> invoiceItems;

    public invoiceHeader(int invoiceNumber, String invoiceDate, String customerName) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    public invoiceHeader(int invoiceNumber, String invoiceDate, String customerName, ArrayList<invoiceLine> invoiceItems) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.invoiceItems = invoiceItems;
    }
    public invoiceHeader(String []data, ArrayList<invoiceLine> invoiceItems) {
        this.invoiceNumber = Integer.parseInt(data[0]);
        this.invoiceDate = data[1];
        this.customerName = data[2];
        this.invoiceItems = invoiceItems;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<invoiceLine> getInvoiceItems() {
        return invoiceItems;
    }
}
