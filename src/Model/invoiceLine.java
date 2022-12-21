package Model;

public class invoiceLine {
    private  int invoiceNumber;
    private  String itemName;
    private Double itemPrice;
    private int itemCount;

    public invoiceLine(int invoiceNumber, String itemName, Double itemPrice, int itemCount) {
        this.invoiceNumber = invoiceNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }
}
