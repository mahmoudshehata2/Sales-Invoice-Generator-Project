package Content.PackInv.Model;

/**
 * 
 * @author IslamAmin
*/

public class InvItemModels {
    private String nameOfItems;
    private double priceOfItems;
    private int CountOfItems;
    private InvDataModels invDataModels;

    public InvItemModels() {
    }

    public InvItemModels(String item, double price, int count, InvDataModels invDataModels) {
        this.nameOfItems = item;
        this.priceOfItems = price;
        this.CountOfItems = count;
        this.invDataModels = invDataModels;
    }

    public double getSalesItemTotal() {
        return priceOfItems * CountOfItems;
    }
    
    public int getCountOfItems() {
        return CountOfItems;
    }

    public void setCountOfItems(int count) {
        this.CountOfItems = count;
    }

    public String getNameOfItems() {
        return nameOfItems;
    }

    public void setNameOfItems(String item) {
        this.nameOfItems = item;
    }

    public double getPriceOfItems() {
        return priceOfItems;
    }

    public void setPriceOfItems(double price) {
        this.priceOfItems = price;
    }

    @Override
    public String toString() {
        return "InvItemModels{" + "num=" + invDataModels.getNumberOfCustomer() + ", item=" + nameOfItems + ", price=" + priceOfItems + ", count=" + CountOfItems + '}';
    }

    public InvDataModels getInvDataModels() {
        return invDataModels;
    }
    public String getCSVFile() {
        return invDataModels.getNumberOfCustomer() + "," + nameOfItems + "," + priceOfItems + "," + CountOfItems;
    }
    
    
    
}