package Model;

import java.util.ArrayList;
import java.util.Date;

public class invoiceHeader {
    private  int invoiceNumber;
    private Date invoiceDate;
    private String customerName;
    private ArrayList<invoiceLine> invoiceItems;

    public invoiceHeader(int invoiceNumber, Date invoiceDate, String customerName) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    public invoiceHeader(int invoiceNumber, Date invoiceDate, String customerName, ArrayList<invoiceLine> invoiceItems) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.invoiceItems = invoiceItems;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<invoiceLine> getInvoiceItems() {
        return invoiceItems;
    }
}
