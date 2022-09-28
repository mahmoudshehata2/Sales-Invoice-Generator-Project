package Content.PackInv.Model;

/**
 * 
 * @author IslamAmin
*/

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvTableDisplay extends AbstractTableModel {
    private ArrayList<InvDataModels> invData;
    private String[] tableColumn = {"No.", "Date", "Customer", "Total"};
    
    public InvTableDisplay(ArrayList<InvDataModels> invData) {
        this.invData = invData;
    }
    
    @Override
    public int getRowCount() {
        return invData.size();
    }

    @Override
    public int getColumnCount() {
        return tableColumn.length;
    }

    @Override
    public String getColumnName(int column) {
        return tableColumn[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvDataModels invDataModels = invData.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return invDataModels.getNumberOfCustomer();
            case 1: return invDataModels.getDate0fInvoice();
            case 2: return invDataModels.getCustomerName();
            case 3: return invDataModels.salesInvTotals();
            default : return "";
        }
    }
}