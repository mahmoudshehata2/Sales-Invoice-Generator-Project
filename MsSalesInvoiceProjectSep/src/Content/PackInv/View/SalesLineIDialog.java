package Content.PackInv.View;

/**
 * 
 * @author IslamAmin
*/

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SalesLineIDialog extends JDialog{
    private JTextField itemNameF;
    private JTextField itemCountF;
    private JTextField itemPriceF;
    private JLabel itemNameL;
    private JLabel itemCountL;
    private JLabel itemPriceL;
    private JButton okJBTN;
    private JButton cancelJBTN;
    
    public SalesLineIDialog(ViewSInvJFrame InvFrame) {
        itemNameF = new JTextField(20);
        itemNameL = new JLabel("Item Name");
        
        itemCountF = new JTextField(20);
        itemCountL = new JLabel("Item Count");
        
        itemPriceF = new JTextField(20);
        itemPriceL = new JLabel("Item Price");
        
        okJBTN = new JButton("OK");
        cancelJBTN = new JButton("Cancel");
        
        okJBTN.setActionCommand("oKNewLineD");
        cancelJBTN.setActionCommand("cancelNewLineD");
        
        okJBTN.addActionListener(InvFrame.getExecuteActionController());
        cancelJBTN.addActionListener(InvFrame.getExecuteActionController());
        setLayout(new GridLayout(4,3));
        
        add(itemNameL);
        add(itemNameF);
        add(itemCountL);
        add(itemCountF);
        add(itemPriceL);
        add(itemPriceF);
        add(okJBTN);
        add(cancelJBTN);
        
        pack();
    }

    public JTextField getItemNameF() {
        return itemNameF;
    }

    public JTextField getItemCountF() {
        return itemCountF;
    }

    public JTextField getItemPriceF() {
        return itemPriceF;
    }
}
