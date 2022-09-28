package Content.PackInv.Model;

/**
 * 
 * @author IslamAmin
*/

import java.util.ArrayList;

public class InvDataModels {
    private int numberOfCustomer;
    private String date0fInvoice;
    private String customerName;
    private ArrayList<InvItemModels> invItemMod;
    
    public InvDataModels() {
    }

    public InvDataModels(int num, String date, String customer) {
        this.numberOfCustomer = num;
        this.date0fInvoice = date;
        this.customerName = customer;
    }

    public double salesInvTotals() {
        double total = 0.0;
        for (InvItemModels invItemModels : getInvItemMod()) {
            total += invItemModels.getSalesItemTotal();
        }
        return total;
    }
    
    public ArrayList<InvItemModels> getInvItemMod() {
        if (invItemMod == null) {
            invItemMod = new ArrayList<>();
        }
        return invItemMod;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customer) {
        this.customerName = customer;
    }

    public int getNumberOfCustomer() {
        return numberOfCustomer;
    }

    public void setNumberOfCustomer(int num) {
        this.numberOfCustomer = num;
    }

    public String getDate0fInvoice() {
        return date0fInvoice;
    }

    public void setDate0fInvoice(String date) {
        this.date0fInvoice = date;
    }

    @Override
    public String toString() {
        return "InvDataModels{" + "num=" + numberOfCustomer + ", date=" + date0fInvoice + ", customer=" + customerName + '}';
    }
        
        public String getCSVFile() {
        return numberOfCustomer + "," + date0fInvoice + "," + customerName;
    }
    }
    
   