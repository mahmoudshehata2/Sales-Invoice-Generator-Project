
package Content.PackInv.Model;
/**
 * 
 * @author IslamAmin
*/

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ItemTableDisplay extends AbstractTableModel {

    private ArrayList<InvItemModels> lineItems;
    private String[] tableColumn = {"No.","Item Name","Item Price","Count","Item Total"};

    public ItemTableDisplay(ArrayList<InvItemModels> invItemMod) {
        this.lineItems = invItemMod;
    }
    public ArrayList<InvItemModels> getInvItemMod() {
        return lineItems;
    }
    
    @Override
    public int getRowCount() {
        return lineItems.size();
    }

    @Override
    public int getColumnCount() {
        return tableColumn.length;
    }

    @Override
    public String getColumnName(int x) {
        return tableColumn[x];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvItemModels itemLines = lineItems.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return itemLines.getInvDataModels().getNumberOfCustomer();
            case 1: return itemLines.getNameOfItems();
            case 2: return itemLines.getPriceOfItems();
            case 3: return itemLines.getCountOfItems();
            case 4: return itemLines.getSalesItemTotal();
            default : return "";
        }
    }
    
}